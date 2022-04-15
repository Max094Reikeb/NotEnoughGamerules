package net.reikeb.notenoughgamerules.mixin.entities.iron_golem;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.nbt.NbtCompound;

import net.reikeb.notenoughgamerules.Gamerules;
import net.reikeb.notenoughgamerules.IronGolemInterface;
import net.reikeb.notenoughgamerules.mixin.entities.EntityMixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(IronGolemEntity.class)
public abstract class IronGolemMixin extends EntityMixin implements IronGolemInterface {

    private EntityType<?> entityType;

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

    @Inject(method = "canTarget", at = @At("HEAD"))
    private void canTarget(EntityType<?> type, CallbackInfoReturnable<Boolean> cir) {
        this.entityType = type;
    }

    @Redirect(method = "canTarget", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/IronGolemEntity;isPlayerCreated()Z"))
    private boolean changeIsPlayerCreated(IronGolemEntity instance) {
        if (this.entityType != EntityType.PLAYER) {
            return true;
        } else {
            if (instance.isPlayerCreated()) {
                return !instance.getWorld().getGameRules().getBoolean(Gamerules.ONLY_GOLEMS_OWNER_FRIENDLY);
            }
            return false;
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putUuid("Owner", this.getNeg$owner());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        this.setNeg$owner(nbt.getUuid("Owner"));
    }
}
