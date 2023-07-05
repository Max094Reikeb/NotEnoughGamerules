package net.reikeb.not_enough_gamerules.mixin;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.reikeb.not_enough_gamerules.Gamerules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEffect.class)
public class MobEffectMixin {
    private LivingEntity entity;

    @Inject(method = "applyEffectTick", at = @At("HEAD"))
    private void applyUpdateEffectInjection(LivingEntity entity, int amplifier, CallbackInfo ci) {
        this.entity = entity;
    }

    @ModifyConstant(method = "applyEffectTick", constant = @Constant(floatValue = 1.0F, ordinal = 1))
    private float applyUpdateEffectConstantModifier(float constant) {
        return (float) entity.level().getGameRules().getInt(Gamerules.POISON_HEALTH);
    }
}
