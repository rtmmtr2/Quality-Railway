package com.qualityrailway.qr.blocks.Doors.SlidingDoor;

import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorBlock;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.item.TooltipModifier;
import com.qualityrailway.qr.blocks.Doors.SlidingDoor.TrainSlidingDoorBlockEntity;
import com.qualityrailway.qr.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import static com.qualityrailway.qr.qr.REGISTRATE;

public class TrainSlidingDoorBlock extends SlidingDoorBlock {

    public static final IntegerProperty DOOR_SOUND = IntegerProperty.create("door_sound", 0, 15);

    public TrainSlidingDoorBlock(Properties properties, boolean isFolding, int defaultSound) {
        super(properties, isFolding);

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(DOOR_SOUND, defaultSound)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(DOOR_SOUND);
    }

    public static boolean isDoubleDoor(DoorHingeSide hinge, BlockPos pos, Direction facing, MovementContext context) {
        if (hinge == DoorHingeSide.LEFT) {
            BlockPos posH2 = getPlaceDirectionLeft(pos, facing);
            StructureTemplate.StructureBlockInfo structureBlockInfo = context.contraption.getBlocks().get(posH2);
            if (structureBlockInfo == null)
                return false;
            if (structureBlockInfo.state.getBlock() instanceof TrainSlidingDoorBlock) {
                return structureBlockInfo.state.getValue(HINGE) == DoorHingeSide.RIGHT;
            }
        } else {
            BlockPos posH2 = getPlaceDirectionRight(pos, facing);
            StructureTemplate.StructureBlockInfo structureBlockInfo = context.contraption.getBlocks().get(posH2);
            if (structureBlockInfo == null)
                return false;
            if (structureBlockInfo.state.getBlock() instanceof TrainSlidingDoorBlock) {
                return structureBlockInfo.state.getValue(HINGE) == DoorHingeSide.LEFT;
            }
        }
        return false;
    }

    private static BlockPos getPlaceDirectionLeft(BlockPos pos, Direction facing) {
        return pos.relative(facing.getClockWise());
    }

    private static BlockPos getPlaceDirectionRight(BlockPos pos, Direction facing) {
        return pos.relative(facing.getCounterClockWise());
    }

    @Override
    public BlockEntityType<? extends TrainSlidingDoorBlockEntity> getBlockEntityType() {
        return ModBlockEntities.SLIDING_DOOR.get();
    }
}