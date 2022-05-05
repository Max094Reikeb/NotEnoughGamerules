package net.reikeb.notenoughgamerules.mixin.entities;

import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(WanderAroundGoal.class)
public class WanderGoalMixin {
    @Final
    @Shadow
    protected PathAwareEntity mob;
}
