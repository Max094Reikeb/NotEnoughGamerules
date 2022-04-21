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

import org.jetbrains.annotations.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

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

    /**
     * @author Mojang
     */
    @Overwrite
    public @Nullable Vec3d findVillagerPos() {
        ServerWorld serverWorld = (ServerWorld) this.mob.world;
        List<VillagerEntity> villagerList = serverWorld.getEntitiesByType(EntityType.VILLAGER, this.mob.getBoundingBox().expand(32.0), villager -> villager.canSummonGolem(this.mob.world.getTime()));
        if ((this.mob instanceof IronGolemInterface ironGolem) && this.mob.world.getGameRules().getBoolean(Gamerules.ONLY_GOLEMS_OWNER_FRIENDLY)
                && ironGolem.isPlayerCreated()) {
            List<PlayerEntity> playerList = serverWorld.getEntitiesByType(EntityType.PLAYER, this.mob.getBoundingBox().expand(32.0), player -> player.getUuid() == this.getNeg$owner());
            List<LivingEntity> list = new ArrayList<>();
            if (!playerList.isEmpty()) list.addAll(playerList);
            if (!villagerList.isEmpty()) list.addAll(villagerList);
            if (list.isEmpty()) return null;
            LivingEntity livingEntity = list.get(this.mob.world.random.nextInt(list.size()));
            return FuzzyTargeting.findTo(this.mob, 10, 7, livingEntity.getPos());
        } else {
            if (villagerList.isEmpty()) return null;
            VillagerEntity villagerEntity = villagerList.get(this.mob.world.random.nextInt(villagerList.size()));
            return FuzzyTargeting.findTo(this.mob, 10, 7, villagerEntity.getPos());
        }
    }
}
