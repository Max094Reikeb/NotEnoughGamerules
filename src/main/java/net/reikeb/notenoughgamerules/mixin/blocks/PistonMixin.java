package net.reikeb.notenoughgamerules.mixin.blocks;

import net.minecraft.block.PistonBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RedstoneView;
import net.minecraft.world.World;
import net.reikeb.notenoughgamerules.Gamerules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonBlock.class)
public class PistonMixin {
    @Inject(method = "shouldExtend", at = @At("HEAD"), cancellable = true)
    private void shouldExtend(RedstoneView view, BlockPos pos, Direction pistonFace, CallbackInfoReturnable<Boolean> cir) {
        if (view instanceof World world && world.getGameRules().getBoolean(Gamerules.DISABLE_PISTONS)) cir.setReturnValue(false);
    }
}
