package net.reikeb.notenoughgamerules.mixin.player;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;

import net.reikeb.notenoughgamerules.Gamerules;

import org.objectweb.asm.Opcodes;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerMixin extends PlayerMixin {
    @Shadow
    public abstract ServerWorld getWorld();

    @Inject(at = @At("HEAD"), method = "moveToWorld", cancellable = true)
    private void moveToWorld(ServerWorld destination, CallbackInfoReturnable<Entity> cir) {
        GameRules gameRules = destination.getGameRules();
        if (gameRules.getBoolean(Gamerules.DISABLE_DIMENSION_CHANGE)) cir.cancel();
    }

    @Inject(at = @At("HEAD"), method = "teleport", cancellable = true)
    private void teleport(ServerWorld targetWorld, double x, double y, double z, float yaw, float pitch, CallbackInfo ci) {
        GameRules gameRules = this.getWorld().getGameRules();
        if ((targetWorld != this.getWorld()) && (gameRules.getBoolean(Gamerules.DISABLE_DIMENSION_CHANGE))) ci.cancel();
    }

    @Inject(at = @At("TAIL"), method = "copyFrom")
    private void copyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci) {
        GameRules gameRules = this.getWorld().getGameRules();
        if (gameRules.getBoolean(Gamerules.KEEP_XP)) {
            this.experienceLevel = oldPlayer.experienceLevel;
            this.totalExperience = oldPlayer.totalExperience;
            this.experienceProgress = oldPlayer.experienceProgress;
        }
    }

    @Redirect(at = @At(value = "FIELD", target = "Lnet/minecraft/server/network/ServerPlayerEntity;experienceLevel:I", opcode = Opcodes.PUTFIELD, ordinal = 1), method = "copyFrom")
    private void copyFromExperienceLevel(ServerPlayerEntity instance, int value) {}

    @Redirect(at = @At(value = "FIELD", target = "Lnet/minecraft/server/network/ServerPlayerEntity;totalExperience:I", opcode = Opcodes.PUTFIELD, ordinal = 1), method = "copyFrom")
    private void copyFromTotalExperience(ServerPlayerEntity instance, int value) {}

    @Redirect(at = @At(value = "FIELD", target = "Lnet/minecraft/server/network/ServerPlayerEntity;experienceProgress:F", opcode = Opcodes.PUTFIELD, ordinal = 1), method = "copyFrom")
    private void copyFromExperienceProgress(ServerPlayerEntity instance, float value) {}
}
