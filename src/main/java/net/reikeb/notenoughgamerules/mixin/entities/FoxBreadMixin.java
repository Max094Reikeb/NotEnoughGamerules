package net.reikeb.notenoughgamerules.mixin.entities;

import net.reikeb.notenoughgamerules.Gamerules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.entity.passive.FoxEntity$MateGoal")
public class FoxBreadMixin extends AnimalMateGoalMixin {

    @Inject(method = "breed", at = @At("HEAD"), cancellable = true)
    private void bread(CallbackInfo ci) {
        if (!this.animal.getWorld().getGameRules().getBoolean(Gamerules.DO_BABIES_SPAWN)) ci.cancel();
    }
}
