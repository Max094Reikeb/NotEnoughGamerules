package net.reikeb.not_enough_gamerules;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

import net.reikeb.not_enough_gamerules.gamerules.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(NotEnoughGamerules.MODID)
public class NotEnoughGamerules {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    // Register the modid
    public static final String MODID = "not_enough_gamerules";

    public NotEnoughGamerules() {

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(new DeathExplosion());
        MinecraftForge.EVENT_BUS.register(this);
    }
}
