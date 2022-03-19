package net.reikeb.notenoughgamerules.mixin.entities;

import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;

import net.reikeb.notenoughgamerules.Gamerules;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerEntity.class)
public abstract class VillagerMixin extends EntityMixin {
    @Inject(at = @At("HEAD"), method = "onStruckByLightning", cancellable = true)
    private void onStruckByLightning(ServerWorld world, LightningEntity lightning, CallbackInfo ci) {
        GameRules gameRules = world.getGameRules();
        if (!gameRules.getBoolean(Gamerules.DO_TRANSFORMATIONS)) {
            super.onStruckByLightning(world, lightning);
            ci.cancel();
        }
    }
}
