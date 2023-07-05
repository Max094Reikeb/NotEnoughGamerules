package net.reikeb.not_enough_gamerules.events;

import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class BabySpawnEvent {

    @SubscribeEvent
    public static void onBabySpawn(BabyEntitySpawnEvent event) {
        if (event.getChild() == null) return;
        if (!event.getChild().level().getLevelData().getGameRules().getBoolean(Gamerules.DO_BABIES_SPAWN)) {
            event.setCanceled(event.isCancelable());
        }
    }
}
