package net.reikeb.notenoughgamerules.mixin;

import net.minecraft.entity.player.PlayerEntity;

public interface IronGolemInterface {

    PlayerEntity getNeg$owner();

    void setNeg$owner(PlayerEntity playerEntity);
}