package net.reikeb.not_enough_gamerules.mixin.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.CoralFanBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.reikeb.not_enough_gamerules.Gamerules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CoralFanBlock.class)
public class CoralFanBlockMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source, CallbackInfo ci) {
        if (!level.getGameRules().getBoolean(Gamerules.DO_CORAL_NEED_WATER)) ci.cancel();
    }
}
