package com.smd.tcongreedyaddon.config;


import net.minecraftforge.common.config.Configuration;
import com.smd.tcongreedyaddon.TConGreedyAddon;
import com.smd.tcongreedyaddon.plugin.IModule;
import com.smd.tcongreedyaddon.plugin.oldweapons.OldWeapons;

import java.util.ArrayList;

public class ModConfig {
    public static boolean oldWeapons = true;

    public static void readConfig(ArrayList<IModule> Modules) {
        Configuration cfg = TConGreedyAddon.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
        } catch (Exception e) {
            TConGreedyAddon.logger.warn("Problem loading config file!", e);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initGeneralConfig(Configuration cfg) {

        cfg.addCustomCategoryComment("Enabled Modules", "What Modules are enabled");
         oldWeapons = cfg.getBoolean("Old Weapons Enabled?", "Enabled Modules", true, "Should the old 1.7 weapons be added, born anew");

        TConGreedyAddon.Modules.add(new OldWeapons(oldWeapons));

        for (IModule module : TConGreedyAddon.Modules) {
            module.buildConfig(cfg);
        }
    }
}
