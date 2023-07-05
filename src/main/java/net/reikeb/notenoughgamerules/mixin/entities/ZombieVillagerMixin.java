package net.reikeb.notenoughgamerules.mixin.entities;

import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.reikeb.notenoughgamerules.Gamerules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieVillagerEntity.class)
public abstract class ZombieVillagerMixin extends MobEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void tick(CallbackInfo ci) {
        if (!this.world.getGameRules().getBoolean(Gamerules.DO_TRANSFORMATIONS)) {
            super.tick();
            ci.cancel();
        }
    }
}
