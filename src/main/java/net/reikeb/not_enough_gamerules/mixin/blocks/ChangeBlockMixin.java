package net.reikeb.not_enough_gamerules.mixin.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockState;

import net.reikeb.not_enough_gamerules.Gamerules;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(ChangeOverTimeBlock.class)
public class ChangeBlockMixin {

    @Inject(method = "applyChangeOverTime", at = @At("HEAD"), cancellable = true)
    private void applyChangeOverTime(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, Random random, CallbackInfo ci) {
        if (!serverLevel.getGameRules().getBoolean(Gamerules.CAN_COPPER_OXIDE)) ci.cancel();
    }
}
