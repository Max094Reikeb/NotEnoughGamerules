package net.reikeb.not_enough_gamerules.mixin.player;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.mixin.entity.EntityMixin;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerMixin extends EntityMixin {

    @Shadow
    public float experienceProgress;

    @Shadow
    public int experienceLevel;

    @Shadow
    public int totalExperience;

    @Redirect(method = "getExperienceReward", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/GameRules;RULE_KEEPINVENTORY:Lnet/minecraft/world/level/GameRules$Key;", opcode = Opcodes.GETSTATIC))
    private GameRules.Key<GameRules.BooleanValue> getXpToDrop() {
        return Gamerules.KEEP_XP;
    }

    @Inject(method = "actuallyHurt", at = @At("HEAD"), cancellable = true)
    private void actuallyHurt(DamageSource damageSource, float f, CallbackInfo ci) {
        if (!this.level.getGameRules().getBoolean(Gamerules.CAN_PLAYER_TAKE_DAMAGE)) ci.cancel();
    }
}
