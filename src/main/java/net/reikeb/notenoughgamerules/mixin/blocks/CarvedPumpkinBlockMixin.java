package net.reikeb.notenoughgamerules.mixin.blocks;

import net.minecraft.advancement.criterion.SummonedEntityCriterion;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import net.reikeb.notenoughgamerules.IronGolemInterface;

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

    @Redirect(method = "trySpawnEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/criterion/SummonedEntityCriterion;trigger(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/entity/Entity;)V"))
    private void changeCriteria(SummonedEntityCriterion instance, ServerPlayerEntity serverPlayer, Entity entity) {
        if ((entity instanceof LivingEntity livingEntity) && (livingEntity instanceof IronGolemInterface ironGolemEntity)) {
            instance.trigger(serverPlayer, livingEntity);
            ironGolemEntity.setNeg$owner(serverPlayer.getUuid());
        }
    }
}
