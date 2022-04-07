package net.reikeb.notenoughgamerules.mixin.entities;

import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.nbt.NbtCompound;

import net.reikeb.notenoughgamerules.IronGolemInterface;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(IronGolemEntity.class)
public abstract class IronGolemMixin extends EntityMixin implements IronGolemInterface {
    @Unique
    public UUID neg$owner;

    public UUID getNeg$owner() {
        return this.neg$owner;
    }

    public void setNeg$owner(UUID uuid) {
        this.neg$owner = uuid;
    }

    public boolean isPlayerCreated() {
        return this.isPlayerCreated();
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
