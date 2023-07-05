package net.reikeb.notenoughgamerules;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameRules;
import net.reikeb.notenoughgamerules.events.AfterRespawnListener;
import net.reikeb.notenoughgamerules.events.PlayerSleepsListener;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class NotEnoughGamerules implements ModInitializer {

    @Override
    public void onInitialize() {
        Gamerules.setupGamerules();
        EntitySleepEvents.ALLOW_SLEEPING.register(new PlayerSleepsListener());
        ServerPlayerEvents.AFTER_RESPAWN.register(new AfterRespawnListener());
    }

    public static void damageGamerule(Entity entity, DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (entity == null) return;
        GameRules gameRules = entity.getWorld().getGameRules();
        if (!gameRules.getBoolean(Gamerules.CAN_HURT_PET_MOBS) && source.getSource() instanceof PlayerEntity &&
                entity instanceof TameableEntity tameableEntity && tameableEntity.isTamed() && tameableEntity.getOwner() == source.getSource()) {
            cir.cancel();
        }
        if (!gameRules.getBoolean(Gamerules.PVP) && entity instanceof PlayerEntity && source.getSource() instanceof PlayerEntity) {
            cir.cancel();
        }
        if (gameRules.getInt(Gamerules.EXPLOSION_DAMAGE) > -1 && source.isExplosive()) {
            entity.damage(DamageSources.EXPLOSION, (float) gameRules.getInt(Gamerules.EXPLOSION_DAMAGE));
            cir.cancel();
        }
        if (!gameRules.getBoolean(Gamerules.ANVIL_DAMAGE) && source == DamageSource.anvil(source.getSource())) {
            cir.cancel();
        }
        if (gameRules.getInt(Gamerules.DRAGON_BREATH_DAMAGE) > -1 && source == DamageSource.DRAGON_BREATH) {
            entity.damage(DamageSource.DRAGON_BREATH, (float) gameRules.getInt(Gamerules.DRAGON_BREATH_DAMAGE));
            cir.cancel();
        }
    }
}
