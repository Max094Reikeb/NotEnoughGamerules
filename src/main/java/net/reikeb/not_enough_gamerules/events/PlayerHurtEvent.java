package net.reikeb.not_enough_gamerules.events;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;

import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.reikeb.not_enough_gamerules.DamageSources;
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
            if ((!gamerules.getBoolean(Gamerules.PVP)) && (entity instanceof Player) && (sourceentity instanceof Player)) {
                event.setCanceled(event.isCancelable());
            }
            if ((gamerules.getInt(Gamerules.EXPLOSION_DAMAGE) != -1) && (event.getSource().isExplosion())) {
                event.setCanceled(event.isCancelable());
                entity.hurt(DamageSources.EXPLOSION, (float) gamerules.getInt(Gamerules.EXPLOSION_DAMAGE));
            }
            if ((!gamerules.getBoolean(Gamerules.ANVIL_DAMAGE)) && (event.getSource() == DamageSource.ANVIL)) {
                event.setCanceled(event.isCancelable());
            }
            if ((!gamerules.getBoolean(Gamerules.DRAGON_BREATH_DAMAGE)) && (event.getSource() == DamageSource.DRAGON_BREATH)) {
                event.setCanceled(event.isCancelable());
            }
        }
    }
}
