package net.reikeb.notenoughgamerules.mixin.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.CoralBlockBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import net.reikeb.notenoughgamerules.Gamerules;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(CoralBlockBlock.class)
public class CoralBlockBlockMixin {
    @Inject(method = "scheduledTick", at = @At("HEAD"), cancellable = true)
    private void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (!world.getGameRules().getBoolean(Gamerules.DO_CORAL_NEED_WATER)) ci.cancel();
    }
}