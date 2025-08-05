package com.smd.tcongreedyaddon.init;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.tools.ToolCore;
import com.smd.tcongreedyaddon.plugin.IModule;
import com.smd.tcongreedyaddon.TConGreedyAddon;

@Mod.EventBusSubscriber
public class TotalTinkersRegister {

    @SubscribeEvent
    public static void initItems(RegistryEvent.Register<Item> event) {
        for (IModule module : TConGreedyAddon.Modules) {
            if (module.isEnabled()) {
                module.initItems(event);
            }
        }
    }

    public static void initForgeTool(ToolCore core, RegistryEvent.Register<Item> event) {
        event.getRegistry().register(core);
        TinkerRegistry.registerToolForgeCrafting(core);
        TConGreedyAddon.proxy.registerToolModel(core);
    }
}