package net.reikeb.not_enough_gamerules.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameRules;

import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class PlayerHurtEvent {

    @SubscribeEvent
    public static void onEntityAttacked(LivingAttackEvent event) {
        if (event != null && event.getEntity() != null) {
            Entity entity = event.getEntity();
            Entity sourceentity = event.getSource().getEntity();
            GameRules gamerules = entity.level.getLevelData().getGameRules();
            if ((!gamerules.getBoolean(Gamerules.PVP))
                    && (entity instanceof PlayerEntity) && (sourceentity instanceof PlayerEntity)) {
                event.setCanceled(event.isCancelable());
            }
            if ((!gamerules.getBoolean(Gamerules.EXPLOSION_DAMAGE))
                    && (event.getSource().isExplosion())) {
                event.setCanceled(event.isCancelable());
            }
        }
    }
}
