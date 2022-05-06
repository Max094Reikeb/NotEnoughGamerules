package net.reikeb.not_enough_gamerules.events;

import net.minecraftforge.event.world.PistonEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class PistonActiveEvent {

    @SubscribeEvent
    public static void onPistonEvent(PistonEvent.Pre event) {
        if (event.getWorld().getLevelData().getGameRules().getBoolean(Gamerules.DISABLE_PISTONS)) {
            event.setCanceled(event.isCancelable());
        }
    }
}
