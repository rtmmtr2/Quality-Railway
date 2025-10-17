package com.qualityrailway.qr.blocks.tickets;
import com.qualityrailway.qr.ModBlockEntities;
import com.qualityrailway.qr.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;
public class TicketMachineBlockEntity extends BlockEntity {
    // 物品处理器，用于管理物品
    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    // 随机数生成器，用于随机选择车票
    private final Random random = new Random();

    public TicketMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TicketMachineBlockEntity.get(), pos, state);
    }

    // 创建物品处理器
    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) { // 1个槽位，用于临时存储
            @Override
            protected void onContentsChanged(int slot) {
                setChanged(); // 标记为已更改，确保数据保存
            }
        };
    }

    // 核心交互逻辑
    public void interact(Player player) {
        // 检查玩家是否有至少3个绿宝石
        if (player.getInventory().countItem(Items.EMERALD) >= 3) {
            // 消耗3个绿宝石
            player.getInventory().clearOrCountMatchingItems(p -> p.getItem() == Items.EMERALD, 3, player.inventoryMenu.getCraftSlots());

            // 随机选择车票类型
            ItemStack ticket;
            if (random.nextBoolean()) {
                ticket = new ItemStack(ModItems.RedTicket.get());
            } else {
                ticket = new ItemStack(ModItems.BlueTicket.get());
            }

            // 给予玩家车票
            if (!player.getInventory().add(ticket)) {
                // 如果背包已满，在玩家位置掉落车票
                player.drop(ticket, false);
            }
        }
    }

    // 保存数据
    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("inv", itemHandler.serializeNBT());
    }

    // 加载数据
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("inv"));
    }

    // 提供能力
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return super.getCapability(cap, side);
    }

    // 移除能力
    @Override
    public void invalidateCaps() {
        handler.invalidate();
        super.invalidateCaps();
    }
}