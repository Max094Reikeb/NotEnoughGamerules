package net.reikeb.not_enough_gamerules.events;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.reikeb.not_enough_gamerules.DamageSources;
import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class PlayerHurtEvent {

    @SubscribeEvent
    public static void onEntityAttacked(LivingAttackEvent event) {
        if (event == null) return;

        LivingEntity entity = event.getEntity();
        if (entity == null) return;

        DamageSource source = event.getSource();
        Entity sourceEntity = source.getEntity(); //May be null

        GameRules gamerules = entity.level.getLevelData().getGameRules();
        boolean cancelable = event.isCancelable();

        if (!gamerules.getBoolean(Gamerules.CAN_HURT_PET_MOBS) &&
                sourceEntity instanceof Player &&
                entity instanceof TamableAnimal tamedEntity &&
                tamedEntity.isTame() && Objects.equals(tamedEntity.getOwner(), sourceEntity)
        ) {
            event.setCanceled(cancelable);
            return;
        }

        if (!gamerules.getBoolean(Gamerules.PVP) && entity instanceof Player && sourceEntity instanceof Player) {
            event.setCanceled(cancelable);
        } else if (gamerules.getInt(Gamerules.EXPLOSION_DAMAGE) > -1 && source.isExplosion()) {
            event.setCanceled(cancelable);
            entity.hurt(DamageSources.EXPLOSION, (float) gamerules.getInt(Gamerules.EXPLOSION_DAMAGE));
        } else if (!gamerules.getBoolean(Gamerules.ANVIL_DAMAGE) && source == DamageSource.ANVIL) {
            event.setCanceled(cancelable);
        } else if (gamerules.getInt(Gamerules.DRAGON_BREATH_DAMAGE) > -1 && source == DamageSource.DRAGON_BREATH) {
            event.setCanceled(cancelable);
            entity.hurt(DamageSource.DRAGON_BREATH, (float) gamerules.getInt(Gamerules.DRAGON_BREATH_DAMAGE));
        }
    }

}
