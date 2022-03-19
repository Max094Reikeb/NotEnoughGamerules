package net.reikeb.notenoughgamerules.mixin.entities;

import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.world.GameRules;

import net.reikeb.notenoughgamerules.Gamerules;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieVillagerEntity.class)
public abstract class ZombieVillagerMixin extends MobEntityMixin {
    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)
    private void tick(CallbackInfo ci) {
        GameRules gameRules = this.world.getGameRules();
        if (!gameRules.getBoolean(Gamerules.DO_TRANSFORMATIONS)) {
            super.tick();
            ci.cancel();
        }
    }
}
