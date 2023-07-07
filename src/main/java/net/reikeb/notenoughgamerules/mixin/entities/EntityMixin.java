package net.reikeb.notenoughgamerules.mixin.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.reikeb.notenoughgamerules.Gamerules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow
    public int age;

    @Shadow
    public abstract int getId();

    @Shadow
    public void onStruckByLightning(ServerWorld world, LightningEntity lightning) {}

    @Shadow
    public abstract boolean damage(DamageSource source, float amount);

    @Shadow
    public abstract double getY();

    @Shadow public abstract DamageSources getDamageSources();

    @Shadow public abstract World getWorld();

    @Inject(method = "onStruckByLightning", at = @At("HEAD"), cancellable = true)
    private void onStruckByLightning(ServerWorld world, LightningEntity lightning, CallbackInfo ci) {
        int lightningDamage = lightning.getWorld().getGameRules().getInt(Gamerules.LIGHTNING_DAMAGE);
        if (lightningDamage > -1) {
            this.damage(this.getDamageSources().lightningBolt(), (float) lightningDamage);
            ci.cancel();
        }
    }
}
