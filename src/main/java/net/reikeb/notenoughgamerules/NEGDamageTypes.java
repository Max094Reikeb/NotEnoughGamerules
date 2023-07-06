package net.reikeb.notenoughgamerules;

import net.minecraft.entity.damage.DamageScaling;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.*;

public class NEGDamageTypes {

    public static final RegistryKey<DamageType> EXPLOSION = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, NotEnoughGamerules.createId("explosion"));
    public static final RegistryKey<DamageType> SKY_HIGH = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, NotEnoughGamerules.createId("sky_high"));

    public static void register(Registerable<DamageType> damageTypeRegisterable) {
        damageTypeRegisterable.register(NEGDamageTypes.EXPLOSION, new DamageType("explosion", DamageScaling.ALWAYS, 0.1f));
        damageTypeRegisterable.register(NEGDamageTypes.SKY_HIGH, new DamageType("skyHigh", DamageScaling.ALWAYS, 0.1f));
    }
}
