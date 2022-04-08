package net.reikeb.not_enough_gamerules.mixin.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.IronGolem;

import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.IronGolemInterface;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(IronGolem.class)
public abstract class IronGolemMixin extends EntityMixin implements IronGolemInterface {
    @Unique
    public UUID neg$owner;

    @Shadow
    public abstract boolean isPlayerCreated();

    public UUID getNeg$owner() {
        return this.neg$owner;
    }

    public void setNeg$owner(UUID uuid) {
        this.neg$owner = uuid;
    }

    @Inject(method = "canAttackType", at = @At("HEAD"), cancellable = true)
    private void changeAttackType(EntityType<?> type, CallbackInfoReturnable<Boolean> cir) {
        if (this.isPlayerCreated() && type == EntityType.PLAYER
                && (!this.level.getGameRules().getBoolean(Gamerules.ONLY_GOLEMS_OWNER_FRIENDLY))) {
            cir.setReturnValue(false);
        }
    }

    @Redirect(method = "canAttackType", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/IronGolem;isPlayerCreated()Z"))
    private boolean changeIsPlayerCreated(IronGolem instance) {
        return false;
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void addAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        tag.putUUID("Owner", this.getNeg$owner());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        this.setNeg$owner(tag.getUUID("Owner"));
    }
}