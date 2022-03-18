package net.reikeb.notenoughgamerules;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;

import net.reikeb.notenoughgamerules.events.PlayerSleepsListener;

public class NotEnoughGamerules implements ModInitializer {

    @Override
    public void onInitialize() {
        Gamerules.setupGamerules();
        EntitySleepEvents.ALLOW_SLEEPING.register(new PlayerSleepsListener());
    }
}
