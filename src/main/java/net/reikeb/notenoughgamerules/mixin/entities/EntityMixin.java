package net.reikeb.notenoughgamerules.mixin.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    public World world;

    @Shadow
    public abstract int getId();

    @Shadow
    public void onStruckByLightning(ServerWorld world, LightningEntity lightning) {
    }
}
