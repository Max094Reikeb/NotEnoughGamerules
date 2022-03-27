package net.reikeb.not_enough_gamerules.mixin.player;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;

import net.reikeb.not_enough_gamerules.Gamerules;

import org.objectweb.asm.Opcodes;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Player.class)
public class PlayerMixin {

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
}
