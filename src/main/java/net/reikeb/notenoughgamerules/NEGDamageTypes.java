package net.reikeb.notenoughgamerules;

import net.minecraft.entity.damage.DamageScaling;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.*;

public class NEGDamageTypes {

    public static final RegistryKey<DamageType> SKY_HIGH = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, NotEnoughGamerules.createId("sky_high"));
    public static final RegistryKey<DamageType> DIFFERED_EXPLOSION = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, NotEnoughGamerules.createId("differed_explosion"));

    public static void register(Registry<DamageType> damageTypeRegistry) {
        Registry.register(damageTypeRegistry, NEGDamageTypes.SKY_HIGH, new DamageType("skyHigh", DamageScaling.ALWAYS, 0.1f));
        Registry.register(damageTypeRegistry, NEGDamageTypes.DIFFERED_EXPLOSION, new DamageType("explosion", DamageScaling.ALWAYS, 0.1f));
    }
}
