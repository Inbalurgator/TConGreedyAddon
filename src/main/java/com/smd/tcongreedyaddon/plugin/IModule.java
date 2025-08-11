package com.smd.tcongreedyaddon.plugin;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;


public interface IModule {
    String getModuleName();
    boolean isModAvailable();
    void preInit();
    void init();
    void postInit();
    void initItems(RegistryEvent.Register<Item> event);
    default boolean isEnabledByDefault() {
        return true;
    }
}