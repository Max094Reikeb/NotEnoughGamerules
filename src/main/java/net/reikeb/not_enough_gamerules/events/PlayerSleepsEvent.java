package net.reikeb.not_enough_gamerules.events;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;

import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class PlayerSleepsEvent {

    @SubscribeEvent
    public static void onPlayerSleeps(PlayerSleepInBedEvent event) {
        GameRules gameRules = event.getPlayer().level.getLevelData().getGameRules();
        if (!gameRules.getBoolean(Gamerules.CAN_PLAYER_SLEEP)) {
            event.setResult(Player.BedSleepingProblem.NOT_POSSIBLE_NOW);
            event.setCanceled(event.isCancelable());
        }
    }
}