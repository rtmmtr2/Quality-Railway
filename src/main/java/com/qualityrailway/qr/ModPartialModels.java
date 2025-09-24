package com.qualityrailway.qr;

import com.jozufozu.flywheel.core.PartialModel;
import com.simibubi.create.foundation.utility.Couple;
import com.qualityrailway.qr.qr;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class ModPartialModels {

    public static final Map<ResourceLocation, Couple<PartialModel>> FOLDING_DOORS = new HashMap<>();

    static {
        putFoldingDoor("door_industrial_iron");
        putFoldingDoor("door_industrial_iron_window");

        ////////////////
        putFoldingDoor("door_industrial_iron");
        putFoldingDoor("door_industrial_iron_window");
    }

    private static void putFoldingDoor(String path) {
        FOLDING_DOORS.put(qr.asResource(path),
                Couple.create(block(path + "/fold_left"), block(path + "/fold_right")));
    }

    private static PartialModel block(String path) {
        return new PartialModel(qr.asResource("block/" + path));
    }

    public static void init() {
    }
}
