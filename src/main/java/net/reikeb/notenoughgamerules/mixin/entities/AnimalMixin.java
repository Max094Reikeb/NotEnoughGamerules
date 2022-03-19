package net.reikeb.notenoughgamerules.mixin.entities;

import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;

import net.reikeb.notenoughgamerules.Gamerules;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnimalEntity.class)
public class AnimalMixin {
    @Inject(at = @At("HEAD"), method = "breed", cancellable = true)
    private void breed(ServerWorld world, AnimalEntity other, CallbackInfo ci) {
        GameRules gameRules = world.getGameRules();
        if (!gameRules.getBoolean(Gamerules.DO_BABIES_SPAWN)) ci.cancel();
    }
}
