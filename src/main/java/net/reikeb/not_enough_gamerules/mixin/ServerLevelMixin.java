package net.reikeb.not_enough_gamerules.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.chunk.LevelChunk;

import net.reikeb.not_enough_gamerules.Gamerules;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {

    @Shadow
    public abstract ServerLevel getLevel();

    @Inject(method = "tickChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;isAreaLoaded(Lnet/minecraft/core/BlockPos;I)Z", shift = At.Shift.AFTER))
    private void tickChunk(LevelChunk difficultyInstance, int flag1, CallbackInfo ci) {
        if (this.getLevel().getGameRules().getBoolean(Gamerules.DO_ICE_FORM));
    }
}
