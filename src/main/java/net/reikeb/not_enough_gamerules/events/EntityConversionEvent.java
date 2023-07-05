package net.reikeb.not_enough_gamerules.events;

import net.minecraftforge.event.entity.living.LivingConversionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class EntityConversionEvent {

    @SubscribeEvent
    public static void entityTransforms(LivingConversionEvent.Pre event) {
        if (!event.getEntity().level().getLevelData().getGameRules().getBoolean(Gamerules.DO_TRANSFORMATIONS)) {
            event.setCanceled(event.isCancelable());
        }
    }
}
