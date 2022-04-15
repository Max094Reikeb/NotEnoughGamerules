package net.reikeb.notenoughgamerules.mixin.entities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.player.PlayerEntity;

import net.reikeb.notenoughgamerules.Gamerules;
import net.reikeb.notenoughgamerules.IronGolemInterface;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(TargetPredicate.class)
public abstract class TargetPredicateMixin implements IronGolemInterface {

    public UUID getNeg$owner() {
        return null;
    }

    public void setNeg$owner(UUID uuid) {}

    public boolean isPlayerCreated() {
        return false;
    }

    @Inject(method = "test", at = @At("HEAD"), cancellable = true)
    private void test(LivingEntity baseEntity, LivingEntity targetEntity, CallbackInfoReturnable<Boolean> cir) {
        if ((baseEntity instanceof IronGolemInterface ironGolemEntity) && (targetEntity instanceof PlayerEntity playerEntity)
                && (playerEntity.world.getGameRules().getBoolean(Gamerules.ONLY_GOLEMS_OWNER_FRIENDLY)
                && ironGolemEntity.isPlayerCreated() && (playerEntity.getUuid() == ironGolemEntity.getNeg$owner()))) {
            if (playerEntity.world.getGameRules().getBoolean(Gamerules.ONLY_GOLEMS_OWNER_FRIENDLY)) cir.setReturnValue(false);
        }
    }
}
