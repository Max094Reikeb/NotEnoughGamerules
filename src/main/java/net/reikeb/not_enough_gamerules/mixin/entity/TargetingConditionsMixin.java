package net.reikeb.not_enough_gamerules.mixin.entity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;

import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.IronGolemInterface;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TargetingConditions.class)
public abstract class TargetingConditionsMixin implements IronGolemInterface {

    @Inject(method = "test", at = @At("HEAD"))
    private void test(LivingEntity baseEntity, LivingEntity targetEntity, CallbackInfoReturnable<Boolean> cir) {
        if (!(baseEntity instanceof IronGolem ironGolem)) return;
        if (!(targetEntity instanceof Player player)) return;
        if (player.level.getGameRules().getBoolean(Gamerules.ONLY_GOLEMS_OWNER_FRIENDLY) &&
                ironGolem.isPlayerCreated() && player.getUUID() == this.getNeg$owner().getUUID()) {
            cir.setReturnValue(false);
        }
    }
}