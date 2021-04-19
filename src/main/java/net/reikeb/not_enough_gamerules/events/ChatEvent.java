package net.reikeb.not_enough_gamerules.events;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.GameRules;

import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class ChatEvent {

    @SubscribeEvent
    public static void onChat(ServerChatEvent event) {
        ServerPlayerEntity playerEntity = event.getPlayer();
        GameRules gameRules = playerEntity.level.getLevelData().getGameRules();
        if (gameRules.getBoolean(Gamerules.DISABLE_CHAT)) {
            event.setCanceled(event.isCancelable());
        }
    }
}
