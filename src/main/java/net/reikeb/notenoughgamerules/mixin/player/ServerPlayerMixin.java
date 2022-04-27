package net.reikeb.notenoughgamerules.mixin.player;

import net.minecraft.entity.Entity;
import net.minecraft.screen.NamedScreenHandlerFactory;
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

import java.util.OptionalInt;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerMixin extends PlayerMixin {
    @Shadow
    public abstract ServerWorld getWorld();

    @Inject(method = "moveToWorld", at = @At("HEAD"), cancellable = true)
    private void moveToWorld(ServerWorld destination, CallbackInfoReturnable<Entity> cir) {
        GameRules gameRules = destination.getGameRules();
        if (gameRules.getBoolean(Gamerules.DISABLE_DIMENSION_CHANGE)) cir.cancel();
    }

    @Inject(method = "teleport", at = @At("HEAD"), cancellable = true)
    private void teleport(ServerWorld targetWorld, double x, double y, double z, float yaw, float pitch, CallbackInfo ci) {
        GameRules gameRules = this.getWorld().getGameRules();
        if ((targetWorld != this.getWorld()) && (gameRules.getBoolean(Gamerules.DISABLE_DIMENSION_CHANGE))) ci.cancel();
    }

    @Inject(method = "copyFrom", at = @At("TAIL"))
    private void copyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci) {
        GameRules gameRules = this.getWorld().getGameRules();
        if (gameRules.getBoolean(Gamerules.KEEP_XP)) {
            this.experienceLevel = oldPlayer.experienceLevel;
            this.totalExperience = oldPlayer.totalExperience;
            this.experienceProgress = oldPlayer.experienceProgress;
        }
    }

    @Inject(method = "openHandledScreen", at = @At(value = "HEAD"), cancellable = true)
    private void openHandledScreen(NamedScreenHandlerFactory factory, CallbackInfoReturnable<OptionalInt> cir) {
        if (!this.world.getGameRules().getBoolean(Gamerules.CAN_PLAYER_INTERACT_WITH_BLOCK)) cir.setReturnValue(OptionalInt.empty());
    }

    @Redirect(method = "copyFrom", at = @At(value = "FIELD", target = "Lnet/minecraft/server/network/ServerPlayerEntity;experienceLevel:I", opcode = Opcodes.PUTFIELD, ordinal = 1))
    private void copyFromExperienceLevel(ServerPlayerEntity instance, int value) {}

    @Redirect(method = "copyFrom", at = @At(value = "FIELD", target = "Lnet/minecraft/server/network/ServerPlayerEntity;totalExperience:I", opcode = Opcodes.PUTFIELD, ordinal = 1))
    private void copyFromTotalExperience(ServerPlayerEntity instance, int value) {}

    @Redirect(method = "copyFrom", at = @At(value = "FIELD", target = "Lnet/minecraft/server/network/ServerPlayerEntity;experienceProgress:F", opcode = Opcodes.PUTFIELD, ordinal = 1))
    private void copyFromExperienceProgress(ServerPlayerEntity instance, float value) {}
}
