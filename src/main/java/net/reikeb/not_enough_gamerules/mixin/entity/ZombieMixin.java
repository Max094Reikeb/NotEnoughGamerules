package net.reikeb.not_enough_gamerules.mixin.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.monster.Zombie;

import net.reikeb.not_enough_gamerules.Gamerules;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(Zombie.class)
public class ZombieMixin extends EntityMixin {

    @Redirect(method = "killed", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;getDifficulty()Lnet/minecraft/world/Difficulty;", ordinal = 2))
    private Difficulty removeDifficulty(ServerLevel instance) {
        return Difficulty.EASY;
    }

    @Redirect(method = "killed", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextBoolean()Z"))
    private boolean removeBoolean(Random instance) {
        float villagerConversion = (float) this.level.getGameRules().getInt(Gamerules.VILLAGER_CONVERSION) / 100;
        return (instance.nextFloat() < villagerConversion) || (this.level.getDifficulty() == Difficulty.EASY);
    }
}
