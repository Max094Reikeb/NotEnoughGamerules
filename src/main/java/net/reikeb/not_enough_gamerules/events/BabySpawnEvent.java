package net.reikeb.not_enough_gamerules.events;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.world.GameRules;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class BabySpawnEvent {

    @SubscribeEvent
    public static void onBabySpawn(BabyEntitySpawnEvent event) {
        AgeableEntity baby = event.getChild();
        if (baby == null) return;
        GameRules gameRules = baby.level.getLevelData().getGameRules();
        if (!gameRules.getBoolean(Gamerules.DO_BABIES_SPAWN)) {
            event.setCanceled(event.isCancelable());
        }
    }
}
