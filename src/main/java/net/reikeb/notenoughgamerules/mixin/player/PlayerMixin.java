package net.reikeb.notenoughgamerules.mixin.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.world.GameRules;

import net.reikeb.notenoughgamerules.DamageSources;
import net.reikeb.notenoughgamerules.Gamerules;
import net.reikeb.notenoughgamerules.NotEnoughGamerules;
import net.reikeb.notenoughgamerules.mixin.entities.LivingEntityMixin;

import org.objectweb.asm.Opcodes;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerMixin extends LivingEntityMixin {
    @Shadow
    public float experienceProgress;
    @Shadow
    public int experienceLevel;
    @Shadow
    public int totalExperience;
    @Shadow
    protected HungerManager hungerManager;

    @Shadow
    public abstract void sendMessage(Text message, boolean actionBar);

    @Shadow
    public abstract boolean damage(DamageSource source, float amount);

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!this.world.getGameRules().getBoolean(Gamerules.CAN_PLAYER_TAKE_DAMAGE)) cir.cancel();
        Entity entity = this.world.getEntityById(this.getId());
        assert entity != null;
        NotEnoughGamerules.damageGamerule(entity, source, cir);
    }

    @Inject(method = "tick", at = @At("TAIL"), cancellable = true)
    private void tick(CallbackInfo ci) {
        if (this.world.isClient) ci.cancel();

        int naturalHunger = this.world.getGameRules().getInt(Gamerules.NATURAL_HUNGER);
        if ((naturalHunger > -1) && (this.hungerManager.getFoodLevel() < naturalHunger)) {
            this.hungerManager.setFoodLevel(20);
        }
        if (this.getY() < this.world.getGameRules().getInt(Gamerules.SKY_HIGH)) {
            this.sendMessage(new TranslatableText("message.not_enough_gamerules.sky_high_warning"), true);

            if (this.world.getTime() % 200 == 0 && this.age > 199) {
                this.damage(DamageSources.SKY_HIGH, (float) 10);
            }
        }
    }

    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    private void interact(CallbackInfoReturnable<ActionResult> cir) {
        if (!this.world.getGameRules().getBoolean(Gamerules.CAN_PLAYER_INTERACT_WITH_ENTITY)) cir.setReturnValue(ActionResult.FAIL);
    }

    @Redirect(method = "getXpToDrop", at = @At(value = "FIELD", target = "Lnet/minecraft/world/GameRules;KEEP_INVENTORY:Lnet/minecraft/world/GameRules$Key;", opcode = Opcodes.GETSTATIC))
    private GameRules.Key<GameRules.BooleanRule> getXpToDrop() {
        return Gamerules.KEEP_XP;
    }
}
