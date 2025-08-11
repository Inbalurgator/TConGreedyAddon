package com.smd.tcongreedyaddon.plugin.oldweapons;

import com.smd.tcongreedyaddon.init.TraitRegistry;
import com.smd.tcongreedyaddon.tools.oldweapons.*;
import com.smd.tcongreedyaddon.traits.modifiers.ModTest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.Pattern;
import slimeknights.tconstruct.library.tools.ToolPart;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.tools.ranged.TinkerRangedWeapons;
import com.smd.tcongreedyaddon.plugin.IModule;
import com.smd.tcongreedyaddon.TConGreedyAddon;
import com.smd.tcongreedyaddon.init.TotalTinkersRegister;

public class OldWeapons implements IModule {

    @Override
    public String getModuleName() { return "OldWeapons"; }

    public static ToolPart greatbladeCore;

    public static WeaponBattleAxe battleaxe;
    public static WeaponDagger dagger;
    public static WeaponGreatblade greatblade;
    public static AllInOneTool allinonetool;

    public static ModTest test;

    @Override
    public boolean isModAvailable() {
        return Loader.isModLoaded("tconstruct");
    }

    @Override
    public void initItems(RegistryEvent.Register<Item> event) {

            greatbladeCore = new ToolPart(Material.VALUE_Ingot * 24);
            greatbladeCore.setTranslationKey("greatbladeCore").setRegistryName("greatbladeCore");
            event.getRegistry().register(greatbladeCore);
            TinkerRegistry.registerToolPart(greatbladeCore);
            TConGreedyAddon.proxy.registerToolPartModel(greatbladeCore);

            TinkerRegistry.registerStencilTableCrafting(Pattern.setTagForPart(new ItemStack(TinkerTools.pattern), greatbladeCore));

            OldWeapons.battleaxe = new WeaponBattleAxe();
            TotalTinkersRegister.initForgeTool(OldWeapons.battleaxe, event);

            OldWeapons.allinonetool = new AllInOneTool();
            TotalTinkersRegister.initForgeTool(OldWeapons.allinonetool, event);

            OldWeapons.dagger = new WeaponDagger();
            event.getRegistry().register(OldWeapons.dagger);
            TinkerRegistry.registerToolForgeCrafting(OldWeapons.dagger);
            TinkerRegistry.registerToolStationCrafting(OldWeapons.dagger);
            TConGreedyAddon.proxy.registerToolModel(OldWeapons.dagger);

            TinkerRangedWeapons.crossBow = new WeaponCrossbowOveride();
            TotalTinkersRegister.initForgeTool(TinkerRangedWeapons.crossBow, event);

            greatblade = new WeaponGreatblade();
            TotalTinkersRegister.initForgeTool(greatblade, event);
    }

    @Override
    public void init() {
        test = new ModTest();
        TraitRegistry.REGISTERED_TRAITS.add(test);
        test.addItem("stone", 1, 1);
    }

    @Override
    public void preInit() {
    }

    @Override
    public void postInit() {
    }
}
