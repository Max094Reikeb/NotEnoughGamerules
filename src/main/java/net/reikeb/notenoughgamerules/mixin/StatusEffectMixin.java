package net.reikeb.notenoughgamerules.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;

import net.reikeb.notenoughgamerules.Gamerules;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StatusEffect.class)
public class StatusEffectMixin {
    private LivingEntity entity;

    @Inject(method = "applyUpdateEffect", at = @At("HEAD"))
    private void applyUpdateEffectInjection(LivingEntity entity, int amplifier, CallbackInfo ci) {
        this.entity = entity;
    }

    @ModifyConstant(method = "applyUpdateEffect", constant = @Constant(floatValue = 1.0F, ordinal = 1))
    private float applyUpdateEffectConstantModifier(float constant) {
        return (float) entity.world.getGameRules().getInt(Gamerules.POISON_HEALTH);
    }
}
