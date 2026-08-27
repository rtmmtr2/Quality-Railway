package com.qualityrailway.qr.screen;

import com.qualityrailway.qr.ModMenuTypes;
import com.qualityrailway.qr.blockentity.AdvancedSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

public class AdvancedSignMenu extends AbstractContainerMenu {
    
    private final AdvancedSignBlockEntity blockEntity;
    private final ContainerLevelAccess levelAccess;
    
    // 客户端构造函数
    public AdvancedSignMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory, extraData.readBlockPos());
    }
    
    // 通过位置创建菜单
    public AdvancedSignMenu(int containerId, Inventory playerInventory, BlockPos pos) {
        super(ModMenuTypes.ADVANCED_SIGN_MENU.get(), containerId);
        
        BlockEntity blockEntity = playerInventory.player.level.getBlockEntity(pos);
        if (blockEntity instanceof AdvancedSignBlockEntity) {
            this.blockEntity = (AdvancedSignBlockEntity) blockEntity;
            this.levelAccess = ContainerLevelAccess.create(playerInventory.player.level, pos);
        } else {
            throw new IllegalStateException("在位置 " + pos + " 处的方块实体不是 AdvancedSignBlockEntity");
        }
    }
    
    // 服务器端构造函数
    public AdvancedSignMenu(int containerId, Inventory playerInventory, @Nullable BlockEntity blockEntity) {
        super(ModMenuTypes.ADVANCED_SIGN_MENU.get(), containerId);
        
        if (blockEntity instanceof AdvancedSignBlockEntity advancedSignBlockEntity) {
            this.blockEntity = advancedSignBlockEntity;
            this.levelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
        } else {
            throw new IllegalStateException("错误的方块实体类型：" + blockEntity);
        }
    }
    
    public AdvancedSignBlockEntity getBlockEntity() {
        return blockEntity;
    }
    
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
    
    @Override
    public boolean stillValid(Player player) {
        return stillValid(levelAccess, player, blockEntity.getBlockState().getBlock());
    }
}