package com.smd.tcongreedyaddon.event;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.smd.tcongreedyaddon.plugin.oldweapons.OldWeapons;

public class BattleaxeHandler {
    public static final BattleaxeHandler INSTANCE = new BattleaxeHandler();
    @SubscribeEvent
    public void bonusAxeToSelf(LivingHurtEvent event) {
        if (event.getEntityLiving().getHeldItemMainhand().getItem().equals(OldWeapons.battleaxe)) {
            event.setAmount(event.getAmount() * 2.0f);
        }
    }
}
