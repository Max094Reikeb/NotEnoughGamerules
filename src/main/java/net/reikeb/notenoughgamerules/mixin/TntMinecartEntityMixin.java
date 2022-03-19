package net.reikeb.notenoughgamerules.mixin;

import net.minecraft.entity.vehicle.TntMinecartEntity;
import net.minecraft.world.GameRules;

import net.reikeb.notenoughgamerules.Gamerules;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TntMinecartEntity.class)
public abstract class TntMinecartEntityMixin extends EntityMixin {
    @Inject(at = @At("HEAD"), method = "explode", cancellable = true)
    private void explode(CallbackInfo ci) {
        GameRules gameRules = this.world.getGameRules();
        if (!gameRules.getBoolean(Gamerules.TNT_EXPLODES)) ci.cancel();
    }
}
