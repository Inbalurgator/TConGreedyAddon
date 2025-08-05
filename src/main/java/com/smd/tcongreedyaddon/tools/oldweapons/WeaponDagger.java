package com.smd.tcongreedyaddon.tools.oldweapons;

import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.SwordCore;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.List;

public class WeaponDagger extends SwordCore {

    public WeaponDagger() {
        super(PartMaterialType.handle(TinkerTools.toolRod),
              PartMaterialType.head(TinkerTools.knifeBlade),
              PartMaterialType.extra(TinkerTools.crossGuard));

        addCategory(Category.WEAPON);

        setTranslationKey("dagger").setRegistryName("dagger");
    }

    @Override
    public float damagePotential() {
        return 0.9F;
    }

    @Override
    public double attackSpeed() {
        return 2.5F;
    }

    @Override
    public int[] getRepairParts() {
        return new int[]{1};
    }

    @Override
    protected ToolNBT buildTagData(List<Material> materials) {
        HandleMaterialStats handle = materials.get(0).getStatsOrUnknown(MaterialTypes.HANDLE);
        HeadMaterialStats head = materials.get(1).getStatsOrUnknown(MaterialTypes.HEAD);
        ExtraMaterialStats extra = materials.get(2).getStatsOrUnknown(MaterialTypes.EXTRA);

        ToolNBT data = new ToolNBT();
        data.head(head);
        data.handle(handle);
        data.extra(extra);

        data.durability *= 0.75;
        return data;
    }
}
