package net.reikeb.notenoughgamerules;

import net.minecraft.entity.damage.DamageSource;
import net.reikeb.notenoughgamerules.mixin.DamageSourceAccessor;

public class DamageSources {

    public static final DamageSource EXPLOSION = newDamageSource("explosion", true);
    public static final DamageSource SKY_HIGH = newDamageSource("sky_high", true);

    public static DamageSource newDamageSource(String name, boolean bypassArmor) {
        if (bypassArmor)
            return ((DamageSourceAccessor) DamageSourceAccessor.invokeConstructor(name)).invokeSetBypassesArmor();
        return DamageSourceAccessor.invokeConstructor(name);
    }
}
