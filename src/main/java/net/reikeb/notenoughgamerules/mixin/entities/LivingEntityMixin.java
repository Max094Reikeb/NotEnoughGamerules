package net.reikeb.notenoughgamerules.mixin.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.reikeb.notenoughgamerules.Gamerules;
import net.reikeb.notenoughgamerules.NotEnoughGamerules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin {

    @Shadow
    protected ItemStack activeItemStack;

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity entity = this.getWorld().getEntityById(this.getId());
        assert entity != null;
        NotEnoughGamerules.damageGamerule(entity, source, cir);
    }

    @Inject(method = "takeKnockback", at = @At("HEAD"), cancellable = true)
    private void takeKnockback(double strength, double x, double z, CallbackInfo ci) {
        if (this.getWorld().getGameRules().getBoolean(Gamerules.DISABLE_KNOCKBACK)) ci.cancel();
    }

    @Inject(method = "consumeItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;finishUsing(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;)Lnet/minecraft/item/ItemStack;", shift = At.Shift.AFTER))
    private void consumeItem(CallbackInfo ci) {
        ItemStack itemStack = this.activeItemStack;
        if (this.getWorld().getEntityById(this.getId()) instanceof PlayerEntity playerEntity) {
            if ((Math.random() <= ((float) (this.getWorld().getGameRules().getInt(Gamerules.RAW_MEAT_HUNGER) / 100)))
                    && ((itemStack.getItem() == Items.BEEF) || (itemStack.getItem() == Items.CHICKEN)
                    || (itemStack.getItem() == Items.COD) || (itemStack.getItem() == Items.MUTTON)
                    || (itemStack.getItem() == Items.RABBIT) || (itemStack.getItem() == Items.PORKCHOP)
                    || (itemStack.getItem() == Items.SALMON))) {
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 1));
            }
        }
    }
}
