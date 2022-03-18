package net.reikeb.not_enough_gamerules;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;

import net.minecraft.world.GameRules;

public class Gamerules {

    public static GameRules.Key<GameRules.BooleanRule> ANVIL_DAMAGE;
    public static GameRules.Key<GameRules.BooleanRule> CAN_PLAYER_SLEEP;
    public static GameRules.Key<GameRules.BooleanRule> DISABLE_CHAT;
    public static GameRules.Key<GameRules.BooleanRule> DISABLE_DIMENSION_CHANGE;
    public static GameRules.Key<GameRules.BooleanRule> DISABLE_KNOCKBACK;
    public static GameRules.Key<GameRules.BooleanRule> DISABLE_PISTONS;
    public static GameRules.Key<GameRules.BooleanRule> DO_BABIES_SPAWN;
    public static GameRules.Key<GameRules.BooleanRule> DO_TRANSFORMATIONS;
    public static GameRules.Key<GameRules.IntRule> DRAGON_BREATH_DAMAGE;
    public static GameRules.Key<GameRules.IntRule> EXPLOSION_DAMAGE;
    public static GameRules.Key<GameRules.IntRule> LIGHTNING_DAMAGE;
    public static GameRules.Key<GameRules.IntRule> NATURAL_HUNGER;
    public static GameRules.Key<GameRules.BooleanRule> PVP;
    public static GameRules.Key<GameRules.IntRule> RAW_MEAT_HUNGER;
    public static GameRules.Key<GameRules.IntRule> SKY_HIGH;
    public static GameRules.Key<GameRules.BooleanRule> TNT_EXPLODES;

    public static void setupGamerules() {
        ANVIL_DAMAGE = GameRuleRegistry.register("anvilDamage", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        CAN_PLAYER_SLEEP = GameRuleRegistry.register("canPlayerSleep", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        DISABLE_CHAT = GameRuleRegistry.register("disableChat", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(false));
        DISABLE_DIMENSION_CHANGE = GameRuleRegistry.register("disableDimensionChange", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(false));
        DISABLE_KNOCKBACK = GameRuleRegistry.register("disableKnockback", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(false));
        DISABLE_PISTONS = GameRuleRegistry.register("disablePistons", GameRules.Category.UPDATES, GameRuleFactory.createBooleanRule(false));
        DO_BABIES_SPAWN = GameRuleRegistry.register("doBabiesSpawn", GameRules.Category.SPAWNING, GameRuleFactory.createBooleanRule(true));
        DO_TRANSFORMATIONS = GameRuleRegistry.register("doTransformations", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));
        DRAGON_BREATH_DAMAGE = GameRuleRegistry.register("dragonBreathDamage", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(-1));
        EXPLOSION_DAMAGE = GameRuleRegistry.register("explosionDamage", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(-1));
        LIGHTNING_DAMAGE = GameRuleRegistry.register("lightningDamage", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(-1));
        NATURAL_HUNGER = GameRuleRegistry.register("naturalHunger", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(-1));
        PVP = GameRuleRegistry.register("pvp", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
        RAW_MEAT_HUNGER = GameRuleRegistry.register("rawMeatHunger", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(30));
        SKY_HIGH = GameRuleRegistry.register("skyHigh", GameRules.Category.MISC, GameRuleFactory.createIntRule(-64));
        TNT_EXPLODES = GameRuleRegistry.register("tntExplodes", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));
    }
}
