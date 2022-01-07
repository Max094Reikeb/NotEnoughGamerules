package net.reikeb.not_enough_gamerules;

import net.minecraft.world.level.GameRules;

public class Gamerules {

    public static final GameRules.Key<GameRules.BooleanValue> ANVIL_DAMAGE = GameRules
            .register("anvilDamage", GameRules.Category.PLAYER, NotEnoughGamerules.create(true));

    public static final GameRules.Key<GameRules.BooleanValue> DISABLE_CHAT = GameRules
            .register("disableChat", GameRules.Category.PLAYER, NotEnoughGamerules.create(false));

    public static final GameRules.Key<GameRules.BooleanValue> DISABLE_DIMENSION_CHANGE = GameRules
            .register("disableDimensionChange", GameRules.Category.PLAYER, NotEnoughGamerules.create(false));

    public static final GameRules.Key<GameRules.BooleanValue> DISABLE_KNOCKBACK = GameRules
            .register("disableKnockback", GameRules.Category.PLAYER, NotEnoughGamerules.create(false));

    public static final GameRules.Key<GameRules.BooleanValue> DISABLE_PISTONS = GameRules
            .register("disablePistons", GameRules.Category.UPDATES, NotEnoughGamerules.create(false));

    public static final GameRules.Key<GameRules.BooleanValue> DO_BABIES_SPAWN = GameRules
            .register("doBabiesSpawn", GameRules.Category.SPAWNING, NotEnoughGamerules.create(true));

    public static final GameRules.Key<GameRules.BooleanValue> DO_TRANSFORMATIONS = GameRules
            .register("doTransformations", GameRules.Category.MOBS, NotEnoughGamerules.create(true));

    public static final GameRules.Key<GameRules.BooleanValue> DRAGON_BREATH_DAMAGE = GameRules
            .register("dragonBreathDamage", GameRules.Category.PLAYER, NotEnoughGamerules.create(true));

    public static final GameRules.Key<GameRules.IntegerValue> EXPLOSION_DAMAGE = GameRules
            .register("explosionDamage", GameRules.Category.PLAYER, NotEnoughGamerules.create(-1));

    public static final GameRules.Key<GameRules.BooleanValue> LIGHTNING_DAMAGE = GameRules
            .register("lightningDamage", GameRules.Category.PLAYER, NotEnoughGamerules.create(true));

    public static final GameRules.Key<GameRules.BooleanValue> NATURAL_HUNGER = GameRules
            .register("naturalHunger", GameRules.Category.PLAYER, NotEnoughGamerules.create(true));

    public static final GameRules.Key<GameRules.BooleanValue> PVP = GameRules
            .register("pvp", GameRules.Category.PLAYER, NotEnoughGamerules.create(true));

    public static final GameRules.Key<GameRules.IntegerValue> RAW_MEAT_HUNGER = GameRules
            .register("rawMeatHunger", GameRules.Category.PLAYER, NotEnoughGamerules.create(30));

    public static final GameRules.Key<GameRules.IntegerValue> SKY_HIGH = GameRules
            .register("skyHigh", GameRules.Category.MISC, NotEnoughGamerules.create(-64));

    public static final GameRules.Key<GameRules.BooleanValue> TNT_EXPLODES = GameRules
            .register("tntExplodes", GameRules.Category.MISC, NotEnoughGamerules.create(true));
}
