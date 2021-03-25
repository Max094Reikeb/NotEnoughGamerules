package net.reikeb.not_enough_gamerules;

import net.minecraft.world.GameRules;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;

@Mod(NotEnoughGamerules.MODID)
public class NotEnoughGamerules {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    // Register the modid
    public static final String MODID = "not_enough_gamerules";

    public NotEnoughGamerules() {

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(new Gamerules());
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static GameRules.RuleType<GameRules.BooleanValue> create(boolean defaultValue) {
        try {
            Method createGameruleMethod = ObfuscationReflectionHelper.findMethod(GameRules.BooleanValue.class, "create", boolean.class);
            createGameruleMethod.setAccessible(true);
            return (GameRules.RuleType<GameRules.BooleanValue>) createGameruleMethod.invoke(null, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static GameRules.RuleType<GameRules.IntegerValue> create(int defaultValue) {
        try {
            Method createGameruleMethod = ObfuscationReflectionHelper.findMethod(GameRules.IntegerValue.class, "create", int.class);
            createGameruleMethod.setAccessible(true);
            return (GameRules.RuleType<GameRules.IntegerValue>) createGameruleMethod.invoke(null, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
