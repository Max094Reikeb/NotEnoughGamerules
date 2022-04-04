package net.reikeb.notenoughgamerules.mixin.entities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;

import net.reikeb.notenoughgamerules.Gamerules;
import net.reikeb.notenoughgamerules.IronGolemInterface;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TargetPredicate.class)
public abstract class TargetPredicateMixin implements IronGolemInterface {

    @Inject(method = "test", at = @At("HEAD"))
    private void test(LivingEntity baseEntity, LivingEntity targetEntity, CallbackInfoReturnable<Boolean> cir) {
        if (!(baseEntity instanceof IronGolemEntity ironGolemEntity)) return;
        if (!(targetEntity instanceof PlayerEntity playerEntity)) return;
        if (playerEntity.world.getGameRules().getBoolean(Gamerules.ONLY_GOLEMS_OWNER_FRIENDLY) &&
                ironGolemEntity.isPlayerCreated() && playerEntity.getUuid() == this.getNeg$owner().getUuid())
            cir.setReturnValue(false);
    }
}
