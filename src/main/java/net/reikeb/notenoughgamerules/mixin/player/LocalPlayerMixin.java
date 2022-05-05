package net.reikeb.notenoughgamerules.mixin.player;

import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.reikeb.notenoughgamerules.NotEnoughGamerules;
import net.reikeb.notenoughgamerules.mixin.entities.LivingEntityMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OtherClientPlayerEntity.class)
public abstract class LocalPlayerMixin extends LivingEntityMixin {
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity entity = this.world.getEntityById(this.getId());
        assert entity != null;
        NotEnoughGamerules.damageGamerule(entity, source, cir);
    }
}
