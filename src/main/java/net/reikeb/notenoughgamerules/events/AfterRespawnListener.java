package net.reikeb.notenoughgamerules.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameRules;
import net.reikeb.notenoughgamerules.Gamerules;

public class AfterRespawnListener implements ServerPlayerEvents.AfterRespawn {

    @Override
    public void afterRespawn(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        GameRules gameRules = newPlayer.getEntityWorld().getGameRules();
        if (gameRules.getBoolean(Gamerules.KEEP_EFFECTS)) {
            for (int i = 0; i < oldPlayer.getStatusEffects().size(); i++) {
                StatusEffectInstance statusEffectInstance = oldPlayer.getStatusEffects().stream().toList().get(i);
                newPlayer.addStatusEffect(statusEffectInstance, oldPlayer);
            }
        }
    }
}
