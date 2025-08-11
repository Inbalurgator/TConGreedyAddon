package com.smd.tcongreedyaddon.plugin;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.event.RegistryEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ModuleManager {
    private static final Map<String, IModule> modules = new HashMap<>();
    private static final Set<String> activeModules = new HashSet<>();
    private static Configuration config;

    public static void registerModule(IModule module) {
        modules.put(module.getModuleName(), module);
    }

    public static void setupConfig(Configuration config) {
        ModuleManager.config = config;
        config.addCustomCategoryComment("modules", "Enable/disable integration modules");
    }

    public static void initActiveModules() {
        for (IModule module : modules.values()) {
            Property prop = config.get(
                    "modules",
                    module.getModuleName(),
                    module.isEnabledByDefault(),
                    "Enable " + module.getModuleName() + " integration"
            );

            if (prop.getBoolean() && module.isModAvailable()) {
                module.init();
                activeModules.add(module.getModuleName());
            }
        }

        if (config.hasChanged()) config.save();
    }

    public static void preInitActiveModules() {
        for (IModule module : modules.values()) {
            Property prop = config.get(
                    "modules",
                    module.getModuleName(),
                    module.isEnabledByDefault(),
                    "Enable " + module.getModuleName() + " integration"
            );

            if (prop.getBoolean() && module.isModAvailable()) {
                module.preInit();
                activeModules.add(module.getModuleName());
            }
        }

        if (config.hasChanged()) config.save();
    }

    public static void postInitActiveModules() {
        for (IModule module : modules.values()) {
            Property prop = config.get(
                    "modules",
                    module.getModuleName(),
                    module.isEnabledByDefault(),
                    "Enable " + module.getModuleName() + " integration"
            );

            if (prop.getBoolean() && module.isModAvailable()) {
                module.postInit();
                activeModules.add(module.getModuleName());
            }
        }

        if (config.hasChanged()) config.save();
    }

    public static boolean isModuleActive(String name) {
        return activeModules.contains(name);
    }

    public static void initItems(RegistryEvent.Register<Item> event) {
        for (IModule module : modules.values()) {
            boolean enabled = config.get(
                    "modules",
                    module.getModuleName(),
                    module.isEnabledByDefault(),
                    "Enable " + module.getModuleName() + " integration"
            ).getBoolean();

            if (enabled && module.isModAvailable()) {
                if (module instanceof IModule) {
                    module.initItems(event);
                }
            }
        }

        if (config.hasChanged()) config.save();
    }
}


