package net.reikeb.notenoughgamerules.mixin.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.GameRules;

import net.reikeb.notenoughgamerules.Gamerules;
import net.reikeb.notenoughgamerules.NotEnoughGamerules;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin {
    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity entity = this.world.getEntityById(this.getId());
        assert entity != null;
        NotEnoughGamerules.damageGamerule(entity, source, cir);
    }

    @Inject(at = @At("HEAD"), method = "takeKnockback", cancellable = true)
    private void takeKnockback(double strength, double x, double z, CallbackInfo ci) {
        GameRules gameRules = this.world.getGameRules();
        if (gameRules.getBoolean(Gamerules.DISABLE_KNOCKBACK)) ci.cancel();
    }
}
