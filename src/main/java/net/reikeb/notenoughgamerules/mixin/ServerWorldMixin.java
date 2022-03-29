package net.reikeb.notenoughgamerules.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;

import net.reikeb.notenoughgamerules.Gamerules;

import org.jetbrains.annotations.NotNull;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {
    @Shadow @NotNull public abstract MinecraftServer getServer();

    @Redirect(method = "tickChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome;canSetIce(Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z"))
    private boolean tickChunk(Biome instance, WorldView world, BlockPos blockPos) {
        return instance.canSetIce(world, blockPos) && this.getServer().getGameRules().getBoolean(Gamerules.DO_ICE_FORM);
    }
}
