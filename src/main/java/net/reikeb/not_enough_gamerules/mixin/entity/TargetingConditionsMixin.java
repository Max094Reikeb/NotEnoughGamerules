package net.reikeb.not_enough_gamerules.mixin.entity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;

import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.IronGolemInterface;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(TargetingConditions.class)
public abstract class TargetingConditionsMixin implements IronGolemInterface {

    public UUID getNeg$owner() {
        return null;
    }

    public void setNeg$owner(UUID uuid) {}

    public boolean isPlayerCreated() {
        return false;
    }

    @Inject(method = "test", at = @At("HEAD"), cancellable = true)
    private void test(LivingEntity baseEntity, LivingEntity targetEntity, CallbackInfoReturnable<Boolean> cir) {
        if ((baseEntity instanceof IronGolemInterface ironGolem) && (targetEntity instanceof Player player)) {
            if (player.level.getGameRules().getBoolean(Gamerules.ONLY_GOLEMS_OWNER_FRIENDLY) &&
                    ironGolem.isPlayerCreated() && player.getUUID() == this.getNeg$owner()) {
                cir.setReturnValue(false);
            }
        }
    }
}