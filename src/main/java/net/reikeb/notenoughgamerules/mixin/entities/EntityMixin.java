package net.reikeb.notenoughgamerules.mixin.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import net.reikeb.notenoughgamerules.Gamerules;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    public int age;

    @Shadow
    public World world;

    @Shadow
    public abstract int getId();

    @Shadow
    public void onStruckByLightning(ServerWorld world, LightningEntity lightning) {
    }

    @Shadow
    public abstract boolean damage(DamageSource source, float amount);

    @Shadow
    public abstract double getY();

    @Inject(method = "onStruckByLightning", at = @At("HEAD"), cancellable = true)
    private void onStruckByLightning(ServerWorld world, LightningEntity lightning, CallbackInfo ci) {
        GameRules gameRules = lightning.getWorld().getGameRules();
        if (gameRules.getInt(Gamerules.LIGHTNING_DAMAGE) > -1) {
            this.damage(DamageSource.LIGHTNING_BOLT, (float) gameRules.getInt(Gamerules.LIGHTNING_DAMAGE));
            ci.cancel();
        }
    }
}
