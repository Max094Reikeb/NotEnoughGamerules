package net.reikeb.notenoughgamerules.mixin.entities;

import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.reikeb.notenoughgamerules.Gamerules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractPiglinEntity.class)
public abstract class PiglinMixin extends MobEntityMixin {
    @Inject(method = "mobTick", at = @At("HEAD"), cancellable = true)
    private void mobTick(CallbackInfo ci) {
        if (!this.getWorld().getGameRules().getBoolean(Gamerules.DO_TRANSFORMATIONS)) {
            super.mobTick();
            ci.cancel();
        }
    }
}
