package net.reikeb.not_enough_gamerules.events;

import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class EntityDimensionChangeEvent {

    @SubscribeEvent
    public static void onDimensionChange(EntityTravelToDimensionEvent event) {
        if (event.getEntity().level().getLevelData().getGameRules().getBoolean(Gamerules.DISABLE_DIMENSION_CHANGE)) {
            event.setCanceled(event.isCancelable());
        }
    }
}
