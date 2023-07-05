package net.reikeb.notenoughgamerules;

import net.minecraft.entity.damage.DamageSource;

public class DamageSources {

    public static final DamageSource EXPLOSION = newDamageSource("explosion", true);
    public static final DamageSource SKY_HIGH = newDamageSource("sky_high", true);

    public static DamageSource newDamageSource(String name, boolean bypassArmor) {
        DamageSource damageSource = new DamageSource(name);
        return bypassArmor ? damageSource.setBypassesArmor() : damageSource;
    }
}
