package net.reikeb.not_enough_gamerules.mixin.entity;

import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.monster.Zombie;
import net.reikeb.not_enough_gamerules.Gamerules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(Zombie.class)
public class ZombieMixin extends EntityMixin {

    @Redirect(method = "killed", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextBoolean()Z"))
    private boolean removeBoolean(Random instance) {
        float randomFloat = instance.nextFloat();
        float villagerConversion = (float) this.level.getGameRules().getInt(Gamerules.VILLAGER_CONVERSION) / 100;
        if (level.getDifficulty() == Difficulty.NORMAL) {
            return randomFloat >= villagerConversion;
        }
        return randomFloat < villagerConversion;
    }
}
