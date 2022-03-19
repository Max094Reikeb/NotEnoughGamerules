package net.reikeb.notenoughgamerules.mixin.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

import net.reikeb.notenoughgamerules.NotEnoughGamerules;
import net.reikeb.notenoughgamerules.mixin.entities.LivingEntityMixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerMixin extends LivingEntityMixin {
    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity entity = this.world.getEntityById(this.getId());
        assert entity != null;
        NotEnoughGamerules.damageGamerule(entity, source, cir);
    }
}
