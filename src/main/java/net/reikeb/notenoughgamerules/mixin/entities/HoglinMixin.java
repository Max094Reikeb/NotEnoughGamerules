package net.reikeb.notenoughgamerules.mixin.entities;

import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.world.GameRules;

import net.reikeb.notenoughgamerules.Gamerules;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HoglinEntity.class)
public abstract class HoglinMixin extends EntityMixin {
    @Inject(at = @At("RETURN"), method = "canConvert", cancellable = true)
    private void canConvert(CallbackInfoReturnable<Boolean> cir) {
        GameRules gameRules = this.world.getGameRules();
        if (!gameRules.getBoolean(Gamerules.DO_TRANSFORMATIONS)) cir.setReturnValue(false);
    }
}
