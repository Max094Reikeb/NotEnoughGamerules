package net.reikeb.not_enough_gamerules.mixin.blocks;

import net.minecraft.advancements.critereon.SummonedEntityTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.CarvedPumpkinBlock;

import net.reikeb.not_enough_gamerules.IronGolemInterface;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.UUID;

@Mixin(CarvedPumpkinBlock.class)
public class CarvedPumpkinBlockMixin implements IronGolemInterface {

    public UUID getNeg$owner() {
        return null;
    }

    public void setNeg$owner(UUID uuid) {}

    public boolean isPlayerCreated() {
        return false;
    }

    @Redirect(method = "trySpawnGolem", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancements/critereon/SummonedEntityTrigger;trigger(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/world/entity/Entity;)V"))
    private void changeCriteria(SummonedEntityTrigger instance, ServerPlayer serverPlayer, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            if (livingEntity instanceof IronGolemInterface ironGolemEntity) {
                instance.trigger(serverPlayer, livingEntity);
                ironGolemEntity.setNeg$owner(serverPlayer.getUUID());
            }
        }
    }
}
