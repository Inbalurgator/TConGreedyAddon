package com.smd.tcongreedyaddon;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.smd.tcongreedyaddon.config.ModConfig;
import com.smd.tcongreedyaddon.plugin.IModule;
import com.smd.tcongreedyaddon.plugin.oldweapons.OldWeapons;
import com.smd.tcongreedyaddon.proxy.CommonProxy;

import java.io.File;
import java.util.ArrayList;

import static com.smd.tcongreedyaddon.plugin.oldweapons.OldWeapons.greatbladeCoreCraftable;


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

    @SubscribeEvent
    public static void modifyLootTables(LootTableLoadEvent e) {
        if (e.getName().toString().equals("minecraft:chests/end_city_treasure") && OldWeapons.greatbladeCoreFromEndShip.getBoolean() && greatbladeCoreCraftable.getBoolean()) {
            LootEntry entry = new LootEntryTable(new ResourceLocation("tcongreedyaddon:inject/end_city_treasure"), 1, 0, new LootCondition[0], Tags.MOD_ID + ":fullGuardPatternVillageBlacksmith");
            LootPool pool = new LootPool(new LootEntry[]{entry}, new LootCondition[0], new RandomValueRange(1F, 1F), new RandomValueRange(0F, 0F), Tags.MOD_ID + ":fullGuardPatternVillageBlacksmith");
            e.getTable().addPool(pool);
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
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
        proxy.initToolGuis();
        for (IModule module : TConGreedyAddon.Modules) {
            if (module.isEnabled()) {
                module.postInit(event);
            }
        }
    }
}
