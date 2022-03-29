package net.reikeb.notenoughgamerules.mixin;

import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(World.class)
public abstract class WorldMixin {
    @Shadow public abstract GameRules getGameRules();
}
