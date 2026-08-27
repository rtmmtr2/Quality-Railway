package com.qualityrailway.qr.blocks.tools;
import com.qualityrailway.qr.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class bell extends Block {
    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 16, 16);

    public bell(Properties properties) {
        super(properties);
    }


    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE; // 返回预先定义的碰撞箱形状
    }

    /**
     * 当相邻方块更新时触发（用于检测红石信号变化）
     * @param state 当前方块状态
     * @param level 世界对象
     * @param pos 当前方块位置
     * @param neighborBlock 发生变化的相邻方块
     * @param neighborPos 相邻方块位置
     * @param moved 是否由移动引起的更新
     */
    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos,
                                Block neighborBlock, BlockPos neighborPos, boolean moved) {
        // 只在服务器端执行逻辑（避免客户端重复执行）
        if (!level.isClientSide) {
            // 检测当前方块是否接收到红石信号
            boolean hasSignal = level.hasNeighborSignal(pos);

            // 当检测到有效红石信号时
            if (hasSignal) {

                level.playSound(
                        null,                   // 无特定触发玩家
                        pos,                    // 声音源位置
                        ModSounds.bell.get(), // 使用注册的声音事件
                        SoundSource.BLOCKS,     // 归类为方块声音
                        2.0F,                   // 音量设置
                        1.0F                    // 保持正常音高
                );
            }
        }
        // 调用父类方法处理基础逻辑
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, moved);
    }


    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.block(); // 使用完整的方块形状进行渲染
    }
}