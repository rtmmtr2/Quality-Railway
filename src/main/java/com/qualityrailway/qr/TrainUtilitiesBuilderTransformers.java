package com.qualityrailway.qr;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import com.qualityrailway.qr.blocks.Behaviour.DoorBlock.DoorBlockMovementBehaviour;
import com.qualityrailway.qr.blocks.Behaviour.DoorBlock.DoorBlockMovingInteraction;
import com.qualityrailway.qr.blocks.Behaviour.SlidingDoor.TrainSlidingDoorMovementBehaviour;
import com.qualityrailway.qr.blocks.Behaviour.SlidingDoor.TrainSlidingDoorMovingInteraction;

import com.qualityrailway.qr.blocks.Doors.SlidingDoor.TrainSlidingDoorBlock;

import net.minecraft.client.renderer.RenderType;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MaterialColor;

import static com.qualityrailway.qr.qr.REGISTRATE;
import static com.simibubi.create.AllInteractionBehaviours.interactionBehaviour;
import static com.simibubi.create.AllMovementBehaviours.movementBehaviour;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;

@SuppressWarnings({"unused","removal"})
public class TrainUtilitiesBuilderTransformers {



    public static <B extends DoorBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> defaultDoor(String type) {
        return b -> b.initialProperties(() -> Blocks.OAK_DOOR) // for villager AI..
                .properties(p -> p.strength(3.0F, 6.0F))
                .addLayer(() -> RenderType::cutout)
                .transform(pickaxeOnly())
                .onRegister(interactionBehaviour(new DoorBlockMovingInteraction()))
                .onRegister(movementBehaviour(new DoorBlockMovementBehaviour()))
                .tag(BlockTags.DOORS)
                .tag(ModTags.AllBlockTags.DOORS.tag)
                .tag(AllTags.AllBlockTags.NON_DOUBLE_DOOR.tag)
                .item()
                .tag(ItemTags.DOORS)
                .tag(ModTags.AllItemTags.DOORS.tag)
                .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
                .build();
    }

    public static BlockEntry<DoorBlock> DefaultMinecraftDoor(String type, MaterialColor colour) {
        return REGISTRATE.block("door_" + type, DoorBlock::new)
                .initialProperties(AllBlocks.FRAMED_GLASS_DOOR)
                .transform(TrainUtilitiesBuilderTransformers.defaultDoor(type))
                .properties(BlockBehaviour.Properties::noOcclusion)
                .register();
    }

    public static <B extends TrainSlidingDoorBlock, P> NonNullUnaryOperator<BlockBuilder<B, P>> slidingDoor(String type) {
        return b -> b.initialProperties(() -> Blocks.OAK_DOOR) // for villager AI..
                .properties(p -> p.strength(3.0F, 6.0F))
                .transform(pickaxeOnly())
                .onRegister(interactionBehaviour(new TrainSlidingDoorMovingInteraction()))
                .onRegister(movementBehaviour(new TrainSlidingDoorMovementBehaviour(type)))
                .tag(BlockTags.DOORS)
                .tag(ModTags.AllBlockTags.DOORS.tag)
                .tag(AllTags.AllBlockTags.NON_DOUBLE_DOOR.tag)
                .item()
                .tag(ItemTags.DOORS)
                .tag(ModTags.AllItemTags.DOORS.tag)
                .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
                .build();
    }

    public static BlockEntry<TrainSlidingDoorBlock> TrainSlidingDoor(String type, boolean folds, MaterialColor colour) {
        return REGISTRATE.block("door_" + type, p -> new TrainSlidingDoorBlock(p, folds,GetSlidingDoorProperties(0)))
                .initialProperties(AllBlocks.FRAMED_GLASS_DOOR)
                .addLayer(() -> RenderType::cutout)
                .transform(TrainUtilitiesBuilderTransformers.slidingDoor(type))
                .properties(BlockBehaviour.Properties::noOcclusion)
                .register();
    }

    public static BlockEntry<TrainSlidingDoorBlock> TintedTrainSlidingDoor(String type, boolean folds, MaterialColor colour) {
        return REGISTRATE.block("door_" + type, p -> new TrainSlidingDoorBlock(p, folds,GetSlidingDoorProperties(0)))
                .initialProperties(AllBlocks.FRAMED_GLASS_DOOR)
                .addLayer(() -> RenderType::translucent)
                .transform(TrainUtilitiesBuilderTransformers.slidingDoor(type))
                .properties(BlockBehaviour.Properties::noOcclusion)
                .register();
    }



    public static int GetSlidingDoorProperties(int sound) {
        return 0;
    }
}
