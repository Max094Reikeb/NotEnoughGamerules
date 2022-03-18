package net.reikeb.notenoughgamerules;

import net.minecraft.entity.damage.DamageSource;

import net.reikeb.notenoughgamerules.mixin.DamageSourceAccessor;

public class DamageSources {

    public static final DamageSource EXPLOSION = ((DamageSourceAccessor) newDamageSource("explosion")).invokeSetBypassesArmor();
    public static final DamageSource SKY_HIGH = ((DamageSourceAccessor) newDamageSource("sky_high")).invokeSetBypassesArmor();

    public static DamageSource newDamageSource(String name) {
        return DamageSourceAccessor.invokeConstructor(name);
    }
}
