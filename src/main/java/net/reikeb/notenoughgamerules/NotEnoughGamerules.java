package net.reikeb.notenoughgamerules;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.util.Identifier;
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
        if (gameRules.getInt(Gamerules.EXPLOSION_DAMAGE) > -1 && source.isIn(DamageTypeTags.IS_EXPLOSION)) {
            entity.damage(entity.getDamageSources().create(NEGDamageTypes.EXPLOSION), (float) gameRules.getInt(Gamerules.EXPLOSION_DAMAGE));
            cir.cancel();
        }
        if (!gameRules.getBoolean(Gamerules.ANVIL_DAMAGE) && source.isOf(DamageTypes.FALLING_ANVIL)) {
            cir.cancel();
        }
        if (!gameRules.getBoolean(Gamerules.FALLING_BLOCKS_DAMAGE) && source.isOf(DamageTypes.FALLING_BLOCK)) {
            cir.cancel();
        }
        if (!gameRules.getBoolean(Gamerules.STALACTITE_DAMAGE) && source.isOf(DamageTypes.FALLING_STALACTITE)) {
            cir.cancel();
        }
        if (gameRules.getInt(Gamerules.DRAGON_BREATH_DAMAGE) > -1 && source.isOf(DamageTypes.DRAGON_BREATH)) {
            entity.damage(entity.getDamageSources().dragonBreath(), (float) gameRules.getInt(Gamerules.DRAGON_BREATH_DAMAGE));
            cir.cancel();
        }
    }

    public static String id() {
        return "not-enough-gamerules";
    }

    public static Identifier createId(String path) {
        return new Identifier(NotEnoughGamerules.id(), path);
    }
}
