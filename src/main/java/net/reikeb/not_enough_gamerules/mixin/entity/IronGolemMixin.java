package net.reikeb.not_enough_gamerules.mixin.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;

import net.reikeb.not_enough_gamerules.IronGolemInterface;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(IronGolem.class)
public abstract class IronGolemMixin extends EntityMixin implements IronGolemInterface {
    @Unique
    public UUID neg$owner;

    public UUID getNeg$ownerUuid() {
        return this.neg$owner;
    }

    public Player getNeg$owner() {
        if (this.level.getServer() == null) return null;
        return this.level.getServer().overworld().getPlayerByUUID(this.neg$owner);
    }

    public void setNeg$owner(UUID uuid) {
        this.neg$owner = uuid;
    }

    public void setNeg$owner(Player player) {
        this.neg$owner = player.getUUID();
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void addAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        tag.putUUID("Owner", this.getNeg$ownerUuid());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readAdditionalSaveData(CompoundTag tag, CallbackInfo ci) {
        this.setNeg$owner(tag.getUUID("Owner"));
    }
}