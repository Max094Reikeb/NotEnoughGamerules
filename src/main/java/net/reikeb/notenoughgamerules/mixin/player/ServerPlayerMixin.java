package net.reikeb.notenoughgamerules.mixin.player;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;

import net.reikeb.notenoughgamerules.Gamerules;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerMixin {
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
}
