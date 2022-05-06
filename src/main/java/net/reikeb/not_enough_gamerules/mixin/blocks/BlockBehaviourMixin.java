package net.reikeb.not_enough_gamerules.mixin.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.BlockHitResult;
import net.reikeb.not_enough_gamerules.Gamerules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public class BlockBehaviourMixin {

    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    private void entityInside(Level level, BlockPos blockPos, Entity entity, CallbackInfo ci) {
        if (!level.getGameRules().getBoolean(Gamerules.CAN_ENTITY_INTERACT_WITH_WORLD)) ci.cancel();
    }

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void use(Level level, Player player, InteractionHand hand, BlockHitResult hit, CallbackInfoReturnable<InteractionResult> cir) {
        if (!level.getGameRules().getBoolean(Gamerules.CAN_ENTITY_INTERACT_WITH_BLOCK))
            cir.setReturnValue(InteractionResult.PASS);
    }
}
