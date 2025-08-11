package com.smd.tcongreedyaddon;

import com.smd.tcongreedyaddon.init.TraitRegistry;
import com.smd.tcongreedyaddon.plugin.AbyssalCraft.AbyssalCraft;
import com.smd.tcongreedyaddon.plugin.ModuleManager;
import com.smd.tcongreedyaddon.plugin.oldweapons.OldWeapons;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import com.smd.tcongreedyaddon.proxy.CommonProxy;


@Mod.EventBusSubscriber
@Mod(name = Tags.MOD_NAME, modid = Tags.MOD_ID, version = Tags.VERSION, dependencies = "after:tconstruct")
public class TConGreedyAddon {

    public static Configuration config;

    @SidedProxy(serverSide = "com.smd.tcongreedyaddon.proxy.CommonProxy",
                clientSide = "com.smd.tcongreedyaddon.proxy.ClientProxy")

    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
        ModuleManager.setupConfig(config);

        ModuleManager.registerModule(new OldWeapons());
        ModuleManager.registerModule(new AbyssalCraft());

        ModuleManager.preInitActiveModules();

        proxy.registerSubscriptions();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModuleManager.initActiveModules();
        proxy.initToolGuis();
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ModuleManager.postInitActiveModules();
        proxy.registerBookData();
        proxy.initToolGuis();

    }
}
