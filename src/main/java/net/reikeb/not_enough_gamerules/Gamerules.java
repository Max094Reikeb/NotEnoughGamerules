package net.reikeb.not_enough_gamerules;

import net.minecraft.world.level.GameRules;

public class Gamerules {

    public static final GameRules.Key<GameRules.BooleanValue> ALWAYS_SPAWN_DRAGON_EGG = GameRules
            .register("alwaysSpawnDragonEgg", GameRules.Category.MOBS, GameRules.BooleanValue.create(false));

    public static final GameRules.Key<GameRules.BooleanValue> ANVIL_DAMAGE = GameRules
            .register("anvilDamage", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));

    public static final GameRules.Key<GameRules.BooleanValue> CAN_ENTITY_INTERACT_WITH_BLOCK = GameRules
            .register("canEntityInteractWithBlock", GameRules.Category.MOBS, GameRules.BooleanValue.create(true));

    public static final GameRules.Key<GameRules.BooleanValue> CAN_ENTITY_INTERACT_WITH_ENTITIES = GameRules
            .register("canEntityInteractWithEntities", GameRules.Category.MOBS, GameRules.BooleanValue.create(true));

    public static final GameRules.Key<GameRules.BooleanValue> CAN_ENTITY_INTERACT_WITH_WORLD = GameRules
            .register("canEntityInteractWithWorld", GameRules.Category.MOBS, GameRules.BooleanValue.create(true));

    public static final GameRules.Key<GameRules.BooleanValue> CAN_HURT_PET_MOBS = GameRules
            .register("canHurtPetMobs", GameRules.Category.MOBS, GameRules.BooleanValue.create(true));

    public static final GameRules.Key<GameRules.BooleanValue> CAN_PLAYER_SLEEP = GameRules
            .register("canPlayerSleep", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));

    public static final GameRules.Key<GameRules.BooleanValue> CAN_PLAYER_TAKE_DAMAGE = GameRules
            .register("canPlayerTakeDamage", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));

    public static final GameRules.Key<GameRules.BooleanValue> DISABLE_CHAT = GameRules
            .register("disableChat", GameRules.Category.PLAYER, GameRules.BooleanValue.create(false));

    public static final GameRules.Key<GameRules.BooleanValue> DISABLE_DIMENSION_CHANGE = GameRules
            .register("disableDimensionChange", GameRules.Category.PLAYER, GameRules.BooleanValue.create(false));

    public static final GameRules.Key<GameRules.BooleanValue> DISABLE_KNOCKBACK = GameRules
            .register("disableKnockback", GameRules.Category.PLAYER, GameRules.BooleanValue.create(false));

    public static final GameRules.Key<GameRules.BooleanValue> DISABLE_PISTONS = GameRules
            .register("disablePistons", GameRules.Category.UPDATES, GameRules.BooleanValue.create(false));

    public static final GameRules.Key<GameRules.BooleanValue> DO_BABIES_SPAWN = GameRules
            .register("doBabiesSpawn", GameRules.Category.SPAWNING, GameRules.BooleanValue.create(true));

    public static final GameRules.Key<GameRules.BooleanValue> DO_CORAL_NEED_WATER = GameRules
            .register("doCoralNeedWater", GameRules.Category.UPDATES, GameRules.BooleanValue.create(true));

    public static final GameRules.Key<GameRules.BooleanValue> DO_ICE_FORM = GameRules
            .register("doIceForm", GameRules.Category.UPDATES, GameRules.BooleanValue.create(true));

    public static final GameRules.Key<GameRules.BooleanValue> DO_ICE_MELT = GameRules
            .register("doIceMelt", GameRules.Category.UPDATES, GameRules.BooleanValue.create(true));

    public static final GameRules.Key<GameRules.BooleanValue> DO_SNOW_FORM = GameRules
            .register("doSnowForm", GameRules.Category.UPDATES, GameRules.BooleanValue.create(true));

    public static final GameRules.Key<GameRules.BooleanValue> DO_SNOW_MELT = GameRules
            .register("doSnowMelt", GameRules.Category.UPDATES, GameRules.BooleanValue.create(true));

    public static final GameRules.Key<GameRules.BooleanValue> DO_TRANSFORMATIONS = GameRules
            .register("doTransformations", GameRules.Category.MOBS, GameRules.BooleanValue.create(true));

    public static final GameRules.Key<GameRules.IntegerValue> DRAGON_BREATH_DAMAGE = GameRules
            .register("dragonBreathDamage", GameRules.Category.PLAYER, GameRules.IntegerValue.create(-1));

    public static final GameRules.Key<GameRules.IntegerValue> EXPLOSION_DAMAGE = GameRules
            .register("explosionDamage", GameRules.Category.PLAYER, GameRules.IntegerValue.create(-1));

    public static final GameRules.Key<GameRules.BooleanValue> FALLING_BLOCKS_DAMAGE = GameRules
            .register("fallingBlocksDamage", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));

    public static final GameRules.Key<GameRules.BooleanValue> KEEP_EFFECTS = GameRules
            .register("keepEffects", GameRules.Category.PLAYER, GameRules.BooleanValue.create(false));

    public static final GameRules.Key<GameRules.BooleanValue> KEEP_XP = GameRules
            .register("keepXp", GameRules.Category.PLAYER, GameRules.BooleanValue.create(false));

    public static final GameRules.Key<GameRules.IntegerValue> LIGHTNING_DAMAGE = GameRules
            .register("lightningDamage", GameRules.Category.PLAYER, GameRules.IntegerValue.create(-1));

    public static final GameRules.Key<GameRules.IntegerValue> NATURAL_HUNGER = GameRules
            .register("naturalHunger", GameRules.Category.PLAYER, GameRules.IntegerValue.create(-1));

    public static final GameRules.Key<GameRules.IntegerValue> POISON_HEALTH = GameRules
            .register("poisonHealth", GameRules.Category.PLAYER, GameRules.IntegerValue.create(1));

    public static final GameRules.Key<GameRules.BooleanValue> PVP = GameRules
            .register("pvp", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));

    public static final GameRules.Key<GameRules.IntegerValue> RAW_MEAT_HUNGER = GameRules
            .register("rawMeatHunger", GameRules.Category.PLAYER, GameRules.IntegerValue.create(30));

    public static final GameRules.Key<GameRules.IntegerValue> SKY_HIGH = GameRules
            .register("skyHigh", GameRules.Category.MISC, GameRules.IntegerValue.create(-64));

    public static final GameRules.Key<GameRules.BooleanValue> STALACTITE_DAMAGE = GameRules
            .register("stalactiteDamage", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true));

    public static final GameRules.Key<GameRules.BooleanValue> TNT_EXPLODES = GameRules
            .register("tntExplodes", GameRules.Category.MISC, GameRules.BooleanValue.create(true));

    public static final GameRules.Key<GameRules.IntegerValue> VILLAGER_CONVERSION = GameRules
            .register("villagerConversion", GameRules.Category.MOBS, GameRules.IntegerValue.create(50));
}
