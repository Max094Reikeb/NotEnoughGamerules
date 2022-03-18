package net.reikeb.not_enough_gamerules;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;

import net.reikeb.not_enough_gamerules.events.PlayerSleepsListener;

public class NotEnoughGamerules implements ModInitializer {

    @Override
    public void onInitialize() {
        Gamerules.setupGamerules();
        EntitySleepEvents.ALLOW_SLEEPING.register(new PlayerSleepsListener());
    }
}
