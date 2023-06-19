package net.reikeb.not_enough_gamerules.events;

import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class PlayerHurtEvent {

    @SubscribeEvent
    public static void onEntityAttacked(LivingAttackEvent event) {
        if (event == null || event.getEntity() == null) return;

        LivingEntity entity = event.getEntity();
        GameRules gamerules = entity.level.getLevelData().getGameRules();

        if (event.getSource().getEntity() instanceof LivingEntity sourceentity) {
            if ((!gamerules.getBoolean(Gamerules.CAN_HURT_PET_MOBS)) && (sourceentity instanceof Player) &&
                    (entity instanceof TamableAnimal tamedEntity) && (tamedEntity.isTame()) && (tamedEntity.getOwner() == sourceentity)) {
                event.setCanceled(event.isCancelable());
            }
            if ((!gamerules.getBoolean(Gamerules.PVP)) && (entity instanceof Player) && (sourceentity instanceof Player)) {
                event.setCanceled(event.isCancelable());
            }
        }
        if ((gamerules.getInt(Gamerules.EXPLOSION_DAMAGE) > -1) && (event.getSource().is(DamageTypes.EXPLOSION))) {
            event.setCanceled(event.isCancelable());
            entity.hurt(entity.damageSources().explosion(entity, null), (float) gamerules.getInt(Gamerules.EXPLOSION_DAMAGE));
        }
        if ((!gamerules.getBoolean(Gamerules.ANVIL_DAMAGE)) && (event.getSource().is(DamageTypes.FALLING_ANVIL))) {
            event.setCanceled(event.isCancelable());
        }
        if ((!gamerules.getBoolean(Gamerules.FALLING_BLOCKS_DAMAGE)) && (event.getSource().is(DamageTypes.FALLING_BLOCK))) {
            event.setCanceled(event.isCancelable());
        }
        if ((!gamerules.getBoolean(Gamerules.STALACTITE_DAMAGE)) && (event.getSource().is(DamageTypes.STALAGMITE))) {
            event.setCanceled(event.isCancelable());
        }
        if ((gamerules.getInt(Gamerules.DRAGON_BREATH_DAMAGE) > -1) && (event.getSource().is(DamageTypes.DRAGON_BREATH))) {
            event.setCanceled(event.isCancelable());
            entity.hurt(entity.damageSources().dragonBreath(), (float) gamerules.getInt(Gamerules.DRAGON_BREATH_DAMAGE));
        }
    }
}
