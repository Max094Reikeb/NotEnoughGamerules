package net.reikeb.not_enough_gamerules.events;

import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class ExplodesEvent {

    @SubscribeEvent
    public static void onExplosion(ExplosionEvent.Start event) {
        if ((!event.getLevel().getLevelData().getGameRules().getBoolean(Gamerules.TNT_EXPLODES)) && (event.getExplosion().getExploder() instanceof PrimedTnt)) {
            event.setCanceled(event.isCancelable());
        }
    }
}
