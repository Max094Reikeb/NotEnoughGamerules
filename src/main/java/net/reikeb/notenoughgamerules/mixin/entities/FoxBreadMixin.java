package net.reikeb.notenoughgamerules.mixin.entities;

import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.world.GameRules;

import net.reikeb.notenoughgamerules.Gamerules;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.entity.passive.FoxEntity$MateGoal")
public class FoxBreadMixin {
    @Shadow
    @Final
    FoxEntity field_17973;

    @Inject(at = @At("HEAD"), method = "breed", cancellable = true)
    private void bread(CallbackInfo ci) {
        GameRules gameRules = this.field_17973.world.getGameRules();
        if (!gameRules.getBoolean(Gamerules.DO_BABIES_SPAWN)) ci.cancel();
    }
}
