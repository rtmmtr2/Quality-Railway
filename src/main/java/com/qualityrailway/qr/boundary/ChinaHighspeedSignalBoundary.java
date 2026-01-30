package com.qualityrailway.qr.boundary;

import com.qualityrailway.qr.blockentity.signals.ChinaHighspeedSignalBlockEntity;
import com.qualityrailway.qr.signals.SignalStates;
import com.qualityrailway.qr.signals.SignalTypes;
import com.google.common.base.Objects;
import com.simibubi.create.Create;
import com.simibubi.create.content.trains.graph.DimensionPalette;
import com.simibubi.create.content.trains.graph.EdgePointType;
import com.simibubi.create.content.trains.graph.TrackGraph;
import com.simibubi.create.content.trains.graph.TrackNode;
import com.simibubi.create.content.trains.signal.SignalEdgeGroup;
import com.simibubi.create.foundation.utility.Couple;
import com.simibubi.create.foundation.utility.Iterate;
import com.simibubi.create.foundation.utility.NBTHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.*;
import java.util.Map.Entry;

public class ChinaHighspeedSignalBoundary extends com.simibubi.create.content.trains.signal.SignalBoundary {

    public enum OverlayState {
        RENDER, SKIP, DUAL
    }

    public Couple<Map<BlockPos, Boolean>> blockEntities;
    public Couple<SignalTypes> types;
    public Couple<UUID> groups;
    public Couple<Boolean> sidesToUpdate;
    public Couple<SignalStates> cachedStates;

    private Couple<Map<UUID, Boolean>> chainedSignals;
    private Map<UUID, List<UUID>> rearSignalGroupsCache;

    public ChinaHighspeedSignalBoundary() {
        blockEntities = Couple.create(HashMap::new);
        chainedSignals = Couple.create(null, null);
        groups = Couple.create(null, null);
        sidesToUpdate = Couple.create(true, true);
        types = Couple.create(() -> SignalTypes.CHINA_HIGHSPEED_SIGNAL);
        cachedStates = Couple.create(() -> SignalStates.INVALID);
        rearSignalGroupsCache = new HashMap<>();
    }

    public void setGroup(boolean primary, UUID groupId) {
        UUID previous = groups.get(primary);
        groups.set(primary, groupId);

        UUID opposite = groups.get(!primary);
        Map<UUID, SignalEdgeGroup> signalEdgeGroups = Create.RAILWAYS.signalEdgeGroups;

        if (opposite != null && signalEdgeGroups.containsKey(opposite)) {
            SignalEdgeGroup oppositeGroup = signalEdgeGroups.get(opposite);
            if (previous != null)
                oppositeGroup.removeAdjacent(previous);
            if (groupId != null)
                oppositeGroup.putAdjacent(groupId);
        }

        if (groupId != null && signalEdgeGroups.containsKey(groupId)) {
            SignalEdgeGroup group = signalEdgeGroups.get(groupId);
            if (opposite != null)
                group.putAdjacent(opposite);
        }
    }

    public void setGroupAndUpdate(TrackNode side, UUID groupId) {
        boolean primary = isPrimary(side);
        setGroup(primary, groupId);
        sidesToUpdate.set(primary, false);
        chainedSignals.set(primary, null);
        rearSignalGroupsCache.clear();
    }

    @Override
    public boolean canMerge() {
        return true;
    }

    @Override
    public void invalidate(LevelAccessor level) {
        blockEntities.forEach(s -> s.keySet()
                .forEach(p -> invalidateAt(level, p)));
        groups.forEach(uuid -> {
            if (Create.RAILWAYS.signalEdgeGroups.remove(uuid) != null)
                Create.RAILWAYS.sync.edgeGroupRemoved(uuid);
        });
    }

    @Override
    public boolean canCoexistWith(EdgePointType<?> otherType, boolean front) {
        return otherType == getType();
    }

    @Override
    public void blockEntityAdded(BlockEntity blockEntity, boolean front) {
        Map<BlockPos, Boolean> blockEntitiesOnSide = blockEntities.get(front);
        if (blockEntitiesOnSide.isEmpty())
            blockEntity.getBlockState()
                    .getOptionalValue(com.qualityrailway.qr.blocks.signals.ChinaHighspeedSignalBlock.TYPE)
                    .ifPresent(type -> types.set(front, type));
        blockEntitiesOnSide.put(blockEntity.getBlockPos(),
                blockEntity instanceof ChinaHighspeedSignalBlockEntity ste && ste.getReportedPower());
    }

    public void updateBlockEntityPower(ChinaHighspeedSignalBlockEntity blockEntity) {
        for (boolean front : Iterate.trueAndFalse)
            blockEntities.get(front)
                    .computeIfPresent(blockEntity.getBlockPos(),
                            (p, c) -> blockEntity.getReportedPower());
    }

    @Override
    public void blockEntityRemoved(BlockPos blockEntityPos, boolean front) {
        blockEntities.forEach(s -> s.remove(blockEntityPos));
        if (blockEntities.both(Map::isEmpty))
            removeFromAllGraphs();
    }

    @Override
    public void onRemoved(TrackGraph graph) {
        super.onRemoved(graph);
        ChinaHighspeedSignalPropagator.onSignalRemoved(graph, this);
    }

    public void queueUpdate(TrackNode side) {
        sidesToUpdate.set(isPrimary(side), true);
        rearSignalGroupsCache.clear();
    }

    public UUID getGroup(TrackNode side) {
        return groups.get(isPrimary(side));
    }

    @Override
    public boolean canNavigateVia(TrackNode side) {
        return !blockEntities.get(isPrimary(side)).isEmpty();
    }

    // Renamed methods to avoid conflicts
    public OverlayState getCustomOverlayFor(BlockPos blockEntity) {
        for (boolean first : Iterate.trueAndFalse) {
            Map<BlockPos, Boolean> set = blockEntities.get(first);
            for (BlockPos blockPos : set.keySet()) {
                if (blockPos.equals(blockEntity))
                    return blockEntities.get(!first).isEmpty() ? OverlayState.RENDER : OverlayState.DUAL;
                return OverlayState.SKIP;
            }
        }
        return OverlayState.SKIP;
    }

    // Renamed methods to avoid conflicts
    public SignalTypes getCustomTypeFor(BlockPos blockEntity) {
        return types.get(blockEntities.getFirst().containsKey(blockEntity));
    }

    // Renamed methods to avoid conflicts
    public SignalStates getCustomStateFor(BlockPos blockEntity) {
        for (boolean first : Iterate.trueAndFalse) {
            Map<BlockPos, Boolean> set = blockEntities.get(first);
            if (set.containsKey(blockEntity))
                return cachedStates.get(first);
        }
        return SignalStates.INVALID;
    }

    @Override
    public void tick(TrackGraph graph, boolean preTrains) {
        super.tick(graph, preTrains);
        if (!preTrains) {
            tickState(graph);
            return;
        }
        for (boolean front : Iterate.trueAndFalse) {
            if (!sidesToUpdate.get(front))
                continue;
            sidesToUpdate.set(front, false);
            ChinaHighspeedSignalPropagator.propagateSignalGroup(graph, this, front);
            chainedSignals.set(front, null);
            rearSignalGroupsCache.clear();
        }
    }

    private void tickState(TrackGraph graph) {
        for (boolean current : Iterate.trueAndFalse) {
            Map<BlockPos, Boolean> set = blockEntities.get(current);
            if (set.isEmpty())
                continue;

            boolean forcedRed = isForcedRed(current);
            UUID group = groups.get(current);

            if (Objects.equal(group, groups.get(!current))) {
                cachedStates.set(current, SignalStates.INVALID);
                continue;
            }

            Map<UUID, SignalEdgeGroup> signalEdgeGroups = Create.RAILWAYS.signalEdgeGroups;
            SignalEdgeGroup signalEdgeGroup = signalEdgeGroups.get(group);
            if (signalEdgeGroup == null) {
                cachedStates.set(current, SignalStates.INVALID);
                continue;
            }

            boolean occupiedUnlessBySelf = forcedRed || signalEdgeGroup.isOccupiedUnless(this);
            if (occupiedUnlessBySelf) {
                cachedStates.set(current, SignalStates.RED);
            } else {
                cachedStates.set(current, resolveChinaHighspeedSignal(graph, current));
            }
        }
    }

    private SignalStates resolveChinaHighspeedSignal(TrackGraph graph, boolean side) {
        SignalTypes signalType = types.get(side);

        if (signalType == SignalTypes.ENTRY_SIGNAL) {
            return SignalStates.GREEN;
        }

        if (signalType == SignalTypes.CROSS_SIGNAL) {
            return resolveCrossSignal(graph, side);
        }

        // 中国高铁自动信号机逻辑
        if (signalType == SignalTypes.AUTOMATIC_SIGNAL || signalType == SignalTypes.AUTOMATIC_SPEED_SIGNAL
                || signalType == SignalTypes.CHINA_HIGHSPEED_SIGNAL) {
            return resolveChinaHighspeedSignalLogic(graph, side);
        }

        return SignalStates.INVALID;
    }

    private SignalStates resolveChinaHighspeedSignalLogic(TrackGraph graph, boolean side) {
        // 获取后方闭塞区间（最多3段）
        List<List<UUID>> rearGroups = collectRearSignalGroups(graph, side, 3);

        if (rearGroups.isEmpty()) {
            return SignalStates.WHITE; // 搜索不到闭塞区间
        }

        // 检查道岔情况
        if (hasSwitchBehind(graph, side)) {
            return resolveSwitchSignal(graph, side);
        }

        // 中国高铁信号逻辑
        boolean section1Occupied = isAnySectionOccupied(rearGroups.get(0));
        boolean section2Occupied = false;
        boolean section3Occupied = false;

        if (rearGroups.size() > 1) {
            section2Occupied = isAnySectionOccupied(rearGroups.get(1));
        }
        if (rearGroups.size() > 2) {
            section3Occupied = isAnySectionOccupied(rearGroups.get(2));
        }

        // 按照中国高铁信号规则
        if (section1Occupied) {
            return SignalStates.RED; // 第一段闭塞区间被占用
        } else if (section2Occupied) {
            return SignalStates.YELLOW; // 第二段闭塞区间被占用
        } else if (section3Occupied) {
            return SignalStates.GREEN_YELLOW; // 第三段闭塞区间被占用
        } else {
            return SignalStates.GREEN; // 所有三段都空闲
        }
    }

    private SignalStates resolveSwitchSignal(TrackGraph graph, boolean side) {
        List<List<UUID>> rearGroups = collectRearSignalGroups(graph, side, 1);

        if (rearGroups.isEmpty() || rearGroups.get(0).isEmpty()) {
            return SignalStates.WHITE;
        }

        // 道岔后方第一段的所有闭塞区间
        List<UUID> firstSections = rearGroups.get(0);

        boolean allFree = true;
        boolean allOccupied = true;

        for (UUID sectionId : firstSections) {
            boolean occupied = isSectionOccupied(sectionId);
            allFree &= !occupied;
            allOccupied &= occupied;
        }

        if (allFree) {
            return SignalStates.GREEN;
        } else if (allOccupied) {
            return SignalStates.RED;
        } else {
            return SignalStates.YELLOW; // 对于道岔，部分占用显示黄灯
        }
    }

    private SignalStates resolveCrossSignal(TrackGraph graph, boolean side) {
        if (chainedSignals.get(side) == null)
            chainedSignals.set(side, ChinaHighspeedSignalPropagator.collectChainedSignals(graph, this, side));

        boolean allPathsFree = true;
        boolean noPathsFree = true;
        boolean invalid = false;

        for (Entry<UUID, Boolean> entry : chainedSignals.get(side).entrySet()) {
            UUID uuid = entry.getKey();
            boolean sideOfOther = entry.getValue();
            ChinaHighspeedSignalBoundary otherSignal = (ChinaHighspeedSignalBoundary) graph.getPoint(EdgePointType.SIGNAL, uuid);
            if (otherSignal == null) {
                invalid = true;
                break;
            }
            if (otherSignal.blockEntities.get(sideOfOther).isEmpty())
                continue;
            SignalStates otherState = otherSignal.cachedStates.get(sideOfOther);
            allPathsFree &= otherState == SignalStates.GREEN || otherState == SignalStates.INVALID;
            noPathsFree &= otherState == SignalStates.RED;
        }

        if (invalid) {
            chainedSignals.set(side, null);
            return SignalStates.INVALID;
        }
        if (allPathsFree)
            return SignalStates.GREEN;
        if (noPathsFree)
            return SignalStates.RED;
        return SignalStates.YELLOW;
    }

    private List<List<UUID>> collectRearSignalGroups(TrackGraph graph, boolean side, int depth) {
        UUID cacheKey = UUID.randomUUID();
        String cacheStr = graph.id.toString() + side + depth;
        cacheKey = UUID.nameUUIDFromBytes(cacheStr.getBytes());

        if (rearSignalGroupsCache.containsKey(cacheKey)) {
            List<UUID> cached = rearSignalGroupsCache.get(cacheKey);
            List<List<UUID>> result = new ArrayList<>();
            // 简单分割缓存结果
            for (int i = 0; i < depth; i++) {
                result.add(new ArrayList<>());
            }
            return result;
        }

        List<List<UUID>> result = new ArrayList<>();
        for (int i = 0; i < depth; i++) {
            result.add(new ArrayList<>());
        }

        UUID currentGroup = groups.get(side);
        if (currentGroup == null) {
            return result;
        }

        Set<UUID> visited = new HashSet<>();
        Queue<Pair<UUID, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(currentGroup, 0));

        while (!queue.isEmpty()) {
            Pair<UUID, Integer> current = queue.poll();
            UUID groupId = current.getFirst();
            int level = current.getSecond();

            if (level >= depth || visited.contains(groupId)) {
                continue;
            }

            visited.add(groupId);
            result.get(level).add(groupId);

            SignalEdgeGroup group = Create.RAILWAYS.signalEdgeGroups.get(groupId);
            if (group != null) {
                for (UUID adjacent : group.adjacent) {
                    // 避免向前搜索
                    if (!adjacent.equals(groups.get(!side))) {
                        queue.add(new Pair<>(adjacent, level + 1));
                    }
                }
            }
        }

        // 缓存结果
        rearSignalGroupsCache.put(cacheKey, flattenResult(result));
        return result;
    }

    private List<UUID> flattenResult(List<List<UUID>> result) {
        List<UUID> flattened = new ArrayList<>();
        for (List<UUID> list : result) {
            flattened.addAll(list);
        }
        return flattened;
    }

    private boolean hasSwitchBehind(TrackGraph graph, boolean side) {
        List<List<UUID>> rearGroups = collectRearSignalGroups(graph, side, 1);
        if (rearGroups.isEmpty() || rearGroups.get(0).isEmpty()) {
            return false;
        }

        // 如果第一段有多个闭塞区间，说明有道岔
        return rearGroups.get(0).size() > 1;
    }

    private boolean isAnySectionOccupied(List<UUID> sectionIds) {
        for (UUID sectionId : sectionIds) {
            if (isSectionOccupied(sectionId)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSectionOccupied(UUID sectionId) {
        SignalEdgeGroup group = Create.RAILWAYS.signalEdgeGroups.get(sectionId);
        if (group == null) {
            return false;
        }
        return group.isOccupiedUnless((com.simibubi.create.content.trains.signal.SignalBoundary) null);
    }

    public boolean isForcedRed(TrackNode side) {
        return isForcedRed(isPrimary(side));
    }

    public boolean isForcedRed(boolean primary) {
        Collection<Boolean> values = blockEntities.get(primary).values();
        for (Boolean b : values)
            if (b)
                return true;
        return false;
    }

    @Override
    public void read(CompoundTag nbt, boolean migration, DimensionPalette dimensions) {
        super.read(nbt, migration, dimensions);
        if (migration) return;

        blockEntities = Couple.create(HashMap::new);
        groups = Couple.create(null, null);

        for (int i = 1; i <= 2; i++)
            if (nbt.contains("Tiles" + i)) {
                boolean first = i == 1;
                NBTHelper.iterateCompoundList(nbt.getList("Tiles" + i, Tag.TAG_COMPOUND),
                        c -> blockEntities.get(first).put(NbtUtils.readBlockPos(c), c.getBoolean("Power")));
            }

        for (int i = 1; i <= 2; i++)
            if (nbt.contains("Group" + i))
                groups.set(i == 1, nbt.getUUID("Group" + i));
        for (int i = 1; i <= 2; i++)
            sidesToUpdate.set(i == 1, nbt.contains("Update" + i));
        for (int i = 1; i <= 2; i++)
            types.set(i == 1, NBTHelper.readEnum(nbt, "Type" + i, SignalTypes.class));
        for (int i = 1; i <= 2; i++)
            cachedStates.set(i == 1, NBTHelper.readEnum(nbt, "State" + i, SignalStates.class));
    }

    @Override
    public void read(FriendlyByteBuf buffer, DimensionPalette dimensions) {
        super.read(buffer, dimensions);
        for (int i = 1; i <= 2; i++) {
            if (buffer.readBoolean())
                groups.set(i == 1, buffer.readUUID());
        }
    }

    @Override
    public void write(CompoundTag nbt, DimensionPalette dimensions) {
        super.write(nbt, dimensions);
        for (int i = 1; i <= 2; i++)
            if (!blockEntities.get(i == 1).isEmpty())
                nbt.put("Tiles" + i, NBTHelper.writeCompoundList(blockEntities.get(i == 1).entrySet(),
                        e -> {
                            CompoundTag c = NbtUtils.writeBlockPos(e.getKey());
                            c.putBoolean("Power", e.getValue());
                            return c;
                        }));
        for (int i = 1; i <= 2; i++)
            if (groups.get(i == 1) != null)
                nbt.putUUID("Group" + i, groups.get(i == 1));
        for (int i = 1; i <= 2; i++)
            if (sidesToUpdate.get(i == 1))
                nbt.putBoolean("Update" + i, true);
        for (int i = 1; i <= 2; i++)
            NBTHelper.writeEnum(nbt, "Type" + i, types.get(i == 1));
        for (int i = 1; i <= 2; i++)
            NBTHelper.writeEnum(nbt, "State" + i, cachedStates.get(i == 1));
    }

    @Override
    public void write(FriendlyByteBuf buffer, DimensionPalette dimensions) {
        super.write(buffer, dimensions);
        for (int i = 1; i <= 2; i++) {
            boolean hasGroup = groups.get(i == 1) != null;
            buffer.writeBoolean(hasGroup);
            if (hasGroup)
                buffer.writeUUID(groups.get(i == 1));
        }
    }

    public void cycleSignalType(BlockPos pos) {
        SignalTypes current = getCustomTypeFor(pos);
        SignalTypes[] values = SignalTypes.values();
        int nextIndex = (current.ordinal() + 1) % values.length;
        types.set(blockEntities.getFirst().containsKey(pos), values[nextIndex]);
        rearSignalGroupsCache.clear();
    }

    private static class Pair<K, V> {
        private final K first;
        private final V second;

        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        public K getFirst() { return first; }
        public V getSecond() { return second; }
    }
}