package net.reikeb.notenoughgamerules.mixin;

import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;
import net.reikeb.notenoughgamerules.Gamerules;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnderDragonFight.class)
public class EnderDragonFightMixin {
    @Shadow private boolean previouslyKilled;

    @Shadow @Final private ServerWorld world;

    @Redirect(method = "dragonKilled", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/boss/dragon/EnderDragonFight;previouslyKilled:Z", opcode = Opcodes.GETFIELD))
    private boolean dragonKilled(EnderDragonFight instance) {
        GameRules gameRules = this.world.getGameRules();
        return this.previouslyKilled && !gameRules.getBoolean(Gamerules.ALWAYS_SPAWN_DRAGON_EGG);
    }
}
