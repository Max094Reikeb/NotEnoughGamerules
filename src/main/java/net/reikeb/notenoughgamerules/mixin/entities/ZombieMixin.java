package net.reikeb.notenoughgamerules.mixin.entities;

import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.reikeb.notenoughgamerules.Gamerules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieEntity.class)
public abstract class ZombieMixin extends MobEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void tick(CallbackInfo ci) {
        if (!this.world.getGameRules().getBoolean(Gamerules.DO_TRANSFORMATIONS)) {
            super.tick();
            ci.cancel();
        }
    }

    @Redirect(method = "onKilledOther", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/random/Random;nextBoolean()Z"))
    private boolean changeBoolean(Random instance) {
        float randomFloat = instance.nextFloat();
        float villagerConversion = (float) this.world.getGameRules().getInt(Gamerules.VILLAGER_CONVERSION) / 100;
        if (world.getDifficulty() == Difficulty.NORMAL) {
            return randomFloat >= villagerConversion;
        }
        return randomFloat < villagerConversion;
    }
}
