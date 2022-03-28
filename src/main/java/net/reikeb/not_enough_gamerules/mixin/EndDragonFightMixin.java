package net.reikeb.not_enough_gamerules.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import net.reikeb.not_enough_gamerules.Gamerules;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EndDragonFight.class)
public class EndDragonFightMixin {
    @Shadow
    private boolean previouslyKilled;
    @Shadow
    @Final
    private ServerLevel level;

    @Redirect(method = "setDragonKilled", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/dimension/end/EndDragonFight;previouslyKilled:Z", opcode = Opcodes.GETFIELD))
    private boolean getDragonKilled(EndDragonFight instance) {
        GameRules gameRules = this.level.getGameRules();
        return this.previouslyKilled && !gameRules.getBoolean(Gamerules.ALWAYS_SPAWN_DRAGON_EGG);
    }
}
