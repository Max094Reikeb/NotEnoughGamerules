package net.reikeb.not_enough_gamerules.events;

import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.world.GameRules;

import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class LightningEvent {

    @SubscribeEvent
    public static void onLightning(EntityStruckByLightningEvent event) {
        LightningBoltEntity boltEntity = event.getLightning();
        GameRules gameRules = boltEntity.level.getLevelData().getGameRules();
        if (!gameRules.getBoolean(Gamerules.LIGHTNING_DAMAGE)) {
            event.setCanceled(event.isCancelable());
        }
    }
}
