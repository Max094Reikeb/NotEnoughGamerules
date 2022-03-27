package net.reikeb.not_enough_gamerules.mixin.player;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameRules;

import net.reikeb.not_enough_gamerules.Gamerules;

import org.objectweb.asm.Opcodes;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends PlayerMixin {

    @Shadow
    public abstract ServerLevel getLevel();

    @Inject(method = "restoreFrom", at = @At("TAIL"))
    private void restoreFrom(ServerPlayer serverPlayer, boolean alive, CallbackInfo ci) {
        GameRules gameRules = this.getLevel().getGameRules();
        if (gameRules.getBoolean(Gamerules.KEEP_XP)) {
            this.experienceLevel = serverPlayer.experienceLevel;
            this.totalExperience = serverPlayer.totalExperience;
            this.experienceProgress = serverPlayer.experienceProgress;
        }
    }

    @Redirect(method = "restoreFrom", at = @At(value = "FIELD", target = "Lnet/minecraft/server/level/ServerPlayer;experienceLevel:I", opcode = Opcodes.PUTFIELD, ordinal = 1))
    private void restoreFromExperienceLevel(ServerPlayer instance, int value) {}

    @Redirect(method = "restoreFrom", at = @At(value = "FIELD", target = "Lnet/minecraft/server/level/ServerPlayer;totalExperience:I", opcode = Opcodes.PUTFIELD, ordinal = 1))
    private void restoreFromExperience(ServerPlayer instance, int value) {}

    @Redirect(method = "restoreFrom", at = @At(value = "FIELD", target = "Lnet/minecraft/server/level/ServerPlayer;experienceProgress:F", opcode = Opcodes.PUTFIELD, ordinal = 1))
    private void restoreFromExperienceProgress(ServerPlayer instance, float value) {}
}
