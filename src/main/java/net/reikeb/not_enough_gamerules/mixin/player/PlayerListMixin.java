package net.reikeb.not_enough_gamerules.mixin.player;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;

import net.reikeb.not_enough_gamerules.Gamerules;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerList.class)
public class PlayerListMixin {

    @Inject(method = "respawn", at = @At("TAIL"))
    private void respawn(ServerPlayer s, boolean f1, CallbackInfoReturnable<ServerPlayer> cir) {
        Player newPlayer = cir.getReturnValue();
        GameRules gameRules = newPlayer.getLevel().getGameRules();
        if (gameRules.getBoolean(Gamerules.KEEP_EFFECTS)) {
            for (int i = 0; i < s.getActiveEffects().size(); i++) {
                MobEffectInstance effectInstance = s.getActiveEffects().stream().toList().get(i);
                newPlayer.addEffect(effectInstance, s);
            }
        }
    }
}
