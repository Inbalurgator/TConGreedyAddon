package com.smd.tcongreedyaddon;

import com.smd.tcongreedyaddon.init.TraitRegistry;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.smd.tcongreedyaddon.config.ModConfig;
import com.smd.tcongreedyaddon.plugin.IModule;
import com.smd.tcongreedyaddon.proxy.CommonProxy;

import java.io.File;
import java.util.ArrayList;


@Mod.EventBusSubscriber
@Mod(name = Tags.MOD_NAME, modid = Tags.MOD_ID, version = Tags.VERSION, dependencies = "after:tconstruct")
public class TConGreedyAddon {

    public static Configuration config;

    public static File directory;

    public static Logger logger = LogManager.getLogger(Tags.MOD_ID);

    @SidedProxy(serverSide = "com.smd.tcongreedyaddon.proxy.CommonProxy",
                clientSide = "com.smd.tcongreedyaddon.proxy.ClientProxy")

    public static CommonProxy proxy;
        
    public static ArrayList<IModule> Modules = new ArrayList<>();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        TraitRegistry.init();
        directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "tcongreedyaddon.cfg"));
        ModConfig.readConfig(Modules);
        proxy.registerSubscriptions();
        for (IModule module : TConGreedyAddon.Modules) {
            if (module.isEnabled()) {
                module.preInit(e);
            }
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.initToolGuis();
        proxy.init();
        for (IModule module : TConGreedyAddon.Modules) {
            if (module.isEnabled()) {
                module.init(event);
            }
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.registerBookData();
        proxy.initToolGuis();
        for (IModule module : TConGreedyAddon.Modules) {
            if (module.isEnabled()) {
                module.postInit(event);
            }
        }
    }
}
