package net.reikeb.not_enough_gamerules.events;

import net.minecraft.entity.player.PlayerEntity;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class PlayerTickEvent {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            PlayerEntity entity = event.player;
            if (entity.getFoodData().needsFood()
                    && !entity.level.getLevelData().getGameRules().getBoolean(Gamerules.NATURAL_HUNGER)) {
                entity.getFoodData().setFoodLevel(10);
            }
        }
    }
}
