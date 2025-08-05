package com.smd.tcongreedyaddon.plugin.oldweapons;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.event.RegistryEvent;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.Pattern;
import slimeknights.tconstruct.library.tools.ToolPart;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.tools.ranged.TinkerRangedWeapons;
import com.smd.tcongreedyaddon.plugin.IModule;
import com.smd.tcongreedyaddon.TConGreedyAddon;
import com.smd.tcongreedyaddon.init.TotalTinkersRegister;
import com.smd.tcongreedyaddon.tools.oldweapons.WeaponBattleAxe;
import com.smd.tcongreedyaddon.tools.oldweapons.WeaponCrossbowOveride;
import com.smd.tcongreedyaddon.tools.oldweapons.WeaponDagger;
import com.smd.tcongreedyaddon.tools.oldweapons.WeaponGreatblade;

public class OldWeapons extends IModule {
    final static String CategoryName = "Old Tools";

    public static Property battleaxeEnabled;
    public static Property daggerEnabled;
    public static Property isReplacingCrossbow;
    public static Property crossbowOldCrosshair;
    public static Property autoCrossbowReload;
    public static Property autoCrossbowDualWield;
    public static Property greatbladeEnabled;
    public static Property greatbladeCoreCraftable;
    public static Property greatbladeCoreFromEndShip;

    public static WeaponBattleAxe battleaxe;
    public static WeaponDagger dagger;
    public static ToolPart greatbladeCore;
    public static WeaponGreatblade greatblade;

    public OldWeapons(boolean isEnabled) {
        super(isEnabled);
    }

    @Override
    public void buildConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CategoryName, "Configuration for the old 1.7 weapons, reborn anew");

        battleaxeEnabled = cfg.get(CategoryName, "Battle Axe Enabled?", true, "If the swirling whirlwind of death will slay");
        daggerEnabled = cfg.get(CategoryName, "Dagger Enabled?", true, "Should the rouge's weapon, the dagger, be enabled");

        isReplacingCrossbow = cfg.get(CategoryName, "Replace Tinker's Crossbow", false, "Should the crossbow be replaced by a custom version. This is REQUIRED for any of the crossbow tweaks");

        crossbowOldCrosshair = cfg.get(CategoryName, "Crossbow Crosshair", true, "If the old crossbow cursor should be used");
        autoCrossbowReload = cfg.get(CategoryName, "Crossbow Automatic Reload After Shooting", false, "If enabled, the crossbow will automatically reload after being shot.");
        autoCrossbowDualWield = cfg.get(CategoryName, "Crossbow Apply automatic behavior to dual wield", true, "If enabled, the crossbow will still automatically reload while in offhand");

        greatbladeEnabled = cfg.get(CategoryName, "Greatblade enabled", true, "If the percentage hp monster of death is enabled");
        greatbladeCoreCraftable = cfg.get(CategoryName, "Greatblade Core Craftable", true, "If the greatblade core should be obtainable via one of the two normal methods. If you wish to add your own recipe, set this to false.");
        greatbladeCoreFromEndShip = cfg.get(CategoryName, "Greatblade Core Stencil from End Cities?", true, "Should the greatblade core pattern come from end cities. Disable to make it craftable in the stencil table.");
    }

    @Override
    public void initItems(RegistryEvent.Register<Item> event) {
        if (OldWeapons.battleaxeEnabled.getBoolean()) {
            OldWeapons.battleaxe = new WeaponBattleAxe();
            TotalTinkersRegister.initForgeTool(OldWeapons.battleaxe, event);
        }
        if (OldWeapons.daggerEnabled.getBoolean()) {
            OldWeapons.dagger = new WeaponDagger();
            event.getRegistry().register(OldWeapons.dagger);
            TinkerRegistry.registerToolForgeCrafting(OldWeapons.dagger);
            TinkerRegistry.registerToolStationCrafting(OldWeapons.dagger);
            TConGreedyAddon.proxy.registerToolModel(OldWeapons.dagger);
        }
        if (OldWeapons.isReplacingCrossbow.getBoolean()) {
            TinkerRangedWeapons.crossBow = new WeaponCrossbowOveride();
            TotalTinkersRegister.initForgeTool(TinkerRangedWeapons.crossBow, event);
        }
        if (OldWeapons.greatbladeEnabled.getBoolean()) {
            greatbladeCore = new ToolPart(Material.VALUE_Ingot * 12);
            greatbladeCore.setTranslationKey("greatbladeCore").setRegistryName("greatbladeCore");
            event.getRegistry().register(greatbladeCore);
            TinkerRegistry.registerToolPart(greatbladeCore);
            TConGreedyAddon.proxy.registerToolPartModel(greatbladeCore);
            if (!OldWeapons.greatbladeCoreFromEndShip.getBoolean() && greatbladeCoreCraftable.getBoolean()) {
                TinkerRegistry.registerStencilTableCrafting(Pattern.setTagForPart(new ItemStack(TinkerTools.pattern), greatbladeCore));
            }
            greatblade = new WeaponGreatblade();
            TotalTinkersRegister.initForgeTool(greatblade, event);
        }
    }
}
