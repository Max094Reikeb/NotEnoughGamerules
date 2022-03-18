package net.reikeb.not_enough_gamerules.events;

import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;

import net.reikeb.not_enough_gamerules.Gamerules;

import org.jetbrains.annotations.Nullable;

public class PlayerSleepsListener implements EntitySleepEvents.AllowSleeping {

    @Override
    public PlayerEntity.@Nullable SleepFailureReason allowSleep(PlayerEntity player, BlockPos sleepingPos) {
        GameRules gameRules = player.getWorld().getGameRules();
        if (!gameRules.getBoolean(Gamerules.CAN_PLAYER_SLEEP)) {
            return PlayerEntity.SleepFailureReason.NOT_POSSIBLE_NOW;
        }
        return null;
    }
}
