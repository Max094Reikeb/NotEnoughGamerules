package net.reikeb.not_enough_gamerules.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.TNTEntity;

import net.minecraft.world.GameRules;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class ExplodesEvent {

    @SubscribeEvent
    public static void onExplosion(ExplosionEvent.Start event) {
        Entity source = event.getExplosion().getExploder();
        GameRules gameRules = event.getWorld().getLevelData().getGameRules();
        if ((!gameRules.getBoolean(Gamerules.TNT_EXPLODES)) && (source instanceof TNTEntity)) {
            event.setCanceled(event.isCancelable());
        }
    }
}
