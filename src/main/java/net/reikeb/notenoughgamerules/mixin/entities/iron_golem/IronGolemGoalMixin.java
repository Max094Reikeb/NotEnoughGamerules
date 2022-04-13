package net.reikeb.notenoughgamerules.mixin.entities.iron_golem;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.FuzzyTargeting;
import net.minecraft.entity.ai.goal.IronGolemWanderAroundGoal;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import net.reikeb.notenoughgamerules.Gamerules;
import net.reikeb.notenoughgamerules.IronGolemInterface;
import net.reikeb.notenoughgamerules.mixin.entities.WanderGoalMixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mixin(IronGolemWanderAroundGoal.class)
public abstract class IronGolemGoalMixin extends WanderGoalMixin implements IronGolemInterface {

    public UUID getNeg$owner() {
        return null;
    }

    public void setNeg$owner(UUID uuid) {
    }

    public boolean isPlayerCreated() {
        return false;
    }

    @Shadow
    protected abstract boolean canVillagerSummonGolem(VillagerEntity villager);

    private boolean isPlayerOwner(PlayerEntity player) {
        return player.getUuid() == this.getNeg$owner();
    }

    @Inject(method = "findVillagerPos", at = @At("HEAD"), cancellable = true)
    private void findVillagerPos(CallbackInfoReturnable<Vec3d> cir) {
        if ((this.mob instanceof IronGolemInterface ironGolem) && this.mob.world.getGameRules().getBoolean(Gamerules.ONLY_GOLEMS_OWNER_FRIENDLY)
                && ironGolem.isPlayerCreated()) {
            ServerWorld serverWorld = (ServerWorld) this.mob.world;
            List<PlayerEntity> playerList = serverWorld.getEntitiesByType(EntityType.PLAYER, this.mob.getBoundingBox().expand(32.0), this::isPlayerOwner);
            List<VillagerEntity> villagerList = serverWorld.getEntitiesByType(EntityType.VILLAGER, this.mob.getBoundingBox().expand(32.0), this::canVillagerSummonGolem);
            List<LivingEntity> list = new ArrayList<>(playerList);
            list.addAll(villagerList);
            if (list.isEmpty()) cir.setReturnValue(null);
            LivingEntity livingEntity = list.get(this.mob.world.random.nextInt(list.size()));
            cir.setReturnValue(FuzzyTargeting.findTo(this.mob, 10, 7, livingEntity.getPos()));
        }
    }
}
