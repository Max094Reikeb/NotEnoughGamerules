package net.reikeb.not_enough_gamerules.events;

import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class KnockbackEvent {

    @SubscribeEvent
    public static void onKnockback(LivingKnockBackEvent event) {
        if (event.getEntity().level().getLevelData().getGameRules().getBoolean(Gamerules.DISABLE_KNOCKBACK)) {
            event.setCanceled(event.isCancelable());
        }
    }
}
