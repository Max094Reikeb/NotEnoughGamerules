package net.reikeb.not_enough_gamerules;

import net.minecraft.world.damagesource.DamageSource;

public class DamageSources {

    public static final DamageSource DRAGON_BREATH = new DamageSource("dragonBreath").bypassArmor();
    public static final DamageSource EXPLOSION = new DamageSource("explosion").bypassArmor();
    public static final DamageSource SKY_HIGH = new DamageSource("sky_high").bypassArmor();
}
