package net.reikeb.notenoughgamerules.mixin.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends EntityMixin {
    @Shadow
    protected void mobTick() {
    }

    @Shadow
    public void tick() {
    }

    public abstract boolean canTarget(EntityType<?> type);

    public boolean canTargetEntity(EntityType<?> type) {
        return this.canTarget(type);
    }
}
