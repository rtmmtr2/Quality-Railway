package com.qualityrailway.qr;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.qualityrailway.qr.blocks.tickets.*;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, qr.MODID);

    // 注册闸机方块实体类型
    public static final RegistryObject<BlockEntityType<ArriveGateBlockRightEntity>> ArriveGateBlockRightEntity =
            BLOCK_ENTITIES.register("arrive_gate_block_right_entity",
                    () -> BlockEntityType.Builder.of(ArriveGateBlockRightEntity::new,
                            ModBlocks.ArriveGateBlockRight.get()).build(null));

    public static final RegistryObject<BlockEntityType<DepartGateBlockRightEntity>> DepartGateBlockRightEntity =
            BLOCK_ENTITIES.register("depart_gate_block_right_entity",
                    () -> BlockEntityType.Builder.of(DepartGateBlockRightEntity::new,
                            ModBlocks.DepartGateBlockRight.get()).build(null));

    public static final RegistryObject<BlockEntityType<TicketMachineBlockEntity>> TicketMachineBlockEntity =
            BLOCK_ENTITIES.register("ticket_machine_block_entity",
                    () -> BlockEntityType.Builder.of(TicketMachineBlockEntity::new,
                            ModBlocks.TicketMachineBlock.get()).build(null));
}