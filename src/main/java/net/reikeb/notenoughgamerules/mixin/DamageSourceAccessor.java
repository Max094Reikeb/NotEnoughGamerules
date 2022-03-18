package net.reikeb.notenoughgamerules.mixin;

import net.minecraft.entity.damage.DamageSource;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DamageSource.class)
public interface DamageSourceAccessor {
    @Invoker("<init>")
    public static DamageSource invokeConstructor(String name) {
        throw new AssertionError("mixin failed to apply");
    }

    @Invoker("setBypassesArmor")
    DamageSource invokeSetBypassesArmor();
}
