package com.qualityrailway.qr.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = "qr", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SignalModelConfig extends SimplePreparableReloadListener<Map<String, JsonObject>> {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static SignalModelConfig instance;
    private final Map<String, SignalModelDefinition> models = new HashMap<>();

    public static SignalModelConfig getInstance() {
        if (instance == null) {
            instance = new SignalModelConfig();
        }
        return instance;
    }

    public SignalModelDefinition getModelDefinition(String modelName) {
        return models.getOrDefault(modelName, models.get("default"));
    }

    @Override
    protected Map<String, JsonObject> prepare(ResourceManager resourceManager, ProfilerFiller profiler) {
        Map<String, JsonObject> configs = new HashMap<>();
        String path = "signal_models";

        for (ResourceLocation resourceLocation : resourceManager.listResources(path,
                loc -> {
                    // 使用 toString() 然后检查后缀
                    String pathStr = loc.toString();
                    return pathStr.endsWith(".json") || pathStr.contains(".json");
                })) {
            try {
                Resource resource = resourceManager.getResource(resourceLocation);
                try (InputStream inputStream = resource.getInputStream();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                    JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                    
                    // 备选：使用 toString() 并移除命名空间
                    String fullString = resourceLocation.toString();
                    // ResourceLocation 的 toString() 格式为 "namespace:path"
                    String fullPath;
                    if (fullString.contains(":")) {
                        fullPath = fullString.substring(fullString.indexOf(':') + 1);
                    } else {
                        fullPath = fullString;
                    }
                    
                    String modelName = extractModelName(fullPath, path);
                    configs.put(modelName, json);

                }
            } catch (IOException e) {
                LOGGER.error("Failed to load signal model config: {}", resourceLocation, e);
            }
        }

        return configs;
    }

    private String extractModelName(String fullPath, String basePath) {
        // 移除路径前缀和 .json 后缀
        // 示例: textures/signal_models/highspeed_standard.json -> highspeed_standard
        int baseIndex = fullPath.indexOf(basePath + "/");
        if (baseIndex != -1) {
            String relativePath = fullPath.substring(baseIndex + basePath.length() + 1);
            int dotIndex = relativePath.lastIndexOf('.');
            if (dotIndex != -1) {
                return relativePath.substring(0, dotIndex);
            }
            return relativePath;
        }
        return fullPath;
    }

    @Override
    protected void apply(Map<String, JsonObject> configs, ResourceManager resourceManager, ProfilerFiller profiler) {
        models.clear();

        for (Map.Entry<String, JsonObject> entry : configs.entrySet()) {
            try {
                SignalModelDefinition definition = GSON.fromJson(entry.getValue(), SignalModelDefinition.class);
                models.put(entry.getKey(), definition);
                LOGGER.info("Loaded signal model: {}", entry.getKey());
            } catch (Exception e) {
                LOGGER.error("Failed to parse signal model config: {}", entry.getKey(), e);
            }
        }

        // 确保有默认模型
        if (!models.containsKey("default")) {
            models.put("default", createDefaultModel());
        }
    }

    private SignalModelDefinition createDefaultModel() {
        SignalModelDefinition definition = new SignalModelDefinition();
        definition.modelType = "highspeed_standard";
        definition.lightPositions = new SignalLightPosition[] {
                new SignalLightPosition(0, 0.2f, "red"),      // 上红灯
                new SignalLightPosition(0, 0.1f, "yellow"),   // 上黄灯
                new SignalLightPosition(0, 0, "green"),       // 绿灯
                new SignalLightPosition(0, -0.1f, "yellow2"), // 下黄灯
                new SignalLightPosition(0, -0.2f, "white")    // 白灯
        };
        definition.scale = 0.15f;
        definition.heightOffset = 1.2f;
        return definition;
    }

    @SubscribeEvent
    public static void onRegisterReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(getInstance());
    }

    public static class SignalModelDefinition {
        public String modelType;
        public SignalLightPosition[] lightPositions;
        public float scale;
        public float heightOffset;
        public ResourceLocation modelLocation;
        public ResourceLocation textureLocation;
    }

    public static class SignalLightPosition {
        public float x;
        public float y;
        public String type; // red, yellow, green, white, etc.

        public SignalLightPosition() {}

        public SignalLightPosition(float x, float y, String type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}