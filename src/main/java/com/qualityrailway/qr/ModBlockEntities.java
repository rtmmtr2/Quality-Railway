package com.qualityrailway.qr;

import com.qualityrailway.qr.blockentity.*;
import com.qualityrailway.qr.blockentity.signals.ChinaHighspeedSignalBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, qr.MODID);

    public static final RegistryObject<BlockEntityType<TrainDoorBlockEntity>> TRAIN_DOOR =
            BLOCK_ENTITIES.register("train_door",
                    () -> BlockEntityType.Builder.of(
                            TrainDoorBlockEntity::new,
                            ModBlocks.TRAIN_DOOR.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<AdvancedSignBlockEntity>> ADVANCED_SIGN =
            BLOCK_ENTITIES.register("advanced_sign",
                    () -> BlockEntityType.Builder.of(AdvancedSignBlockEntity::new,
                            ModBlocks.ADVANCED_SIGN.get()).build(null));

    public static final RegistryObject<BlockEntityType<ChinaHighspeedSignalBlockEntity>> CHINA_HIGHSPEED_SIGNAL =
            BLOCK_ENTITIES.register("china_highspeed_signal",
                    () -> BlockEntityType.Builder.of(ChinaHighspeedSignalBlockEntity::new,
                            ModBlocks.CHINA_HIGHSPEED_SIGNAL.get()).build(null));


}
