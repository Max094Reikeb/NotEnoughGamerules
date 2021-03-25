package net.reikeb.not_enough_gamerules;

import net.minecraft.world.GameRules;

public class Gamerules {

    public static final GameRules.RuleKey<GameRules.BooleanValue> DEATH_EXPLOSION = GameRules
            .register("deathExplosion", GameRules.Category.MISC, NotEnoughGamerules.create(false));

    public static final GameRules.RuleKey<GameRules.IntegerValue> DEATH_EXPLOSION_TIME = GameRules
            .register("deathExplosionTime", GameRules.Category.MISC, NotEnoughGamerules.create(10));

    public static final GameRules.RuleKey<GameRules.BooleanValue> NATURAL_HUNGER = GameRules
            .register("naturalHunger", GameRules.Category.PLAYER, NotEnoughGamerules.create(true));
}
