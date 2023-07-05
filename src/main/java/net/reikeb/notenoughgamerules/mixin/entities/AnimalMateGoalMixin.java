package net.reikeb.notenoughgamerules.mixin.entities;

import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.passive.AnimalEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AnimalMateGoal.class)
public class AnimalMateGoalMixin {

    @Shadow @Final protected AnimalEntity animal;
}
