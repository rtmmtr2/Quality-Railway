// java
package com.qualityrailway.qr.blocks.gates;

import com.qualityrailway.qr.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.Tags;
import net.minecraft.util.RandomSource;

public class TicketMachineBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    private static final VoxelShape SHAPE_SOUTH = Shapes.box(0, 0, 0.1875, 1, 1.75, 1);
    private static final VoxelShape SHAPE_NORTH = Shapes.box(0, 0, 0, 1, 1.75, 0.8125);
    private static final VoxelShape SHAPE_WEST = Shapes.box(0, 0, 0, 0.8125, 1.75, 1);
    private static final VoxelShape SHAPE_EAST = Shapes.box(0.1875, 0, 0, 1, 1.75, 1);

    public TicketMachineBlock(Properties properties) {
        super(properties);

        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {

        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {

        Direction direction = state.getValue(FACING);
        switch (direction) {
            case SOUTH: return SHAPE_SOUTH;
            case EAST: return SHAPE_EAST;
            case WEST: return SHAPE_WEST;
            default: return SHAPE_NORTH;
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            ServerLevel serverLevel = (ServerLevel) level;


            int emeraldCount = 0;
            for (ItemStack stack : player.getInventory().items) {
                if (stack.is(Tags.Items.GEMS_EMERALD)) {
                    emeraldCount += stack.getCount();
                }
            }

            if (emeraldCount >= 3) {

                int toRemove = 3;
                for (int i = 0; i < player.getInventory().getContainerSize() && toRemove > 0; i++) {
                    ItemStack stack = player.getInventory().getItem(i);
                    if (stack.is(Tags.Items.GEMS_EMERALD)) {
                        int removeFromStack = Math.min(stack.getCount(), toRemove);
                        stack.shrink(removeFromStack);
                        toRemove -= removeFromStack;
                    }
                }


                RandomSource random = level.getRandom();
                ItemStack ticket;
                if (random.nextBoolean()) {
                    ticket = new ItemStack(ModItems.RedTicket.get());
                } else {
                    ticket = new ItemStack(ModItems.BlueTicket.get());
                }


                if (!player.addItem(ticket)) {

                    player.drop(ticket, false);
                }


                level.playSound(null, pos, SoundEvents.EXPERIENCE_ORB_PICKUP,
                        SoundSource.BLOCKS, 1.0F, 1.0F);

                return InteractionResult.SUCCESS;
            } else {

                player.displayClientMessage(
                        Component.translatable("qr.message.ticket_machine_block").withStyle(ChatFormatting.DARK_GREEN), true
                );
                return InteractionResult.FAIL;
            }
        }
        return InteractionResult.SUCCESS;
    }
}
