package net.reikeb.not_enough_gamerules.events;

import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class ChatEvent {

    @SubscribeEvent
    public static void onChat(ServerChatEvent event) {
        if (event.getPlayer() == null) return;
        if (event.getPlayer().level.getLevelData().getGameRules().getBoolean(Gamerules.DISABLE_CHAT)) {
            event.setCanceled(event.isCancelable());
        }
    }
}
