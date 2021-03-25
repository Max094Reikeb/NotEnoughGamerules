package net.reikeb.not_enough_gamerules.gamerules;

import net.minecraft.world.GameRules;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Method;

public class DeathExplosion {

    public static final GameRules.RuleKey<GameRules.BooleanValue> DEATH_EXPLOSION = GameRules
            .register("deathExplosion", GameRules.Category.MISC, create(false));

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
}
