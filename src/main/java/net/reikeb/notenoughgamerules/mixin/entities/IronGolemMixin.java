package net.reikeb.notenoughgamerules.mixin.entities;

import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

import net.reikeb.notenoughgamerules.mixin.IronGolemInterface;

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

    public UUID getNeg$ownerUuid() {
        return this.neg$owner;
    }

    public PlayerEntity getNeg$owner() {
        if (this.world.getServer() == null) return null;
        return this.world.getServer().getPlayerManager().getPlayer(this.neg$owner);
    }

    public void setNeg$owner(UUID uuid) {
        this.neg$owner = uuid;
    }

    public void setNeg$owner(PlayerEntity playerEntity) {
        this.neg$owner = playerEntity.getUuid();
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putUuid("Owner", this.getNeg$ownerUuid());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        this.setNeg$owner(nbt.getUuid("Owner"));
    }
}
