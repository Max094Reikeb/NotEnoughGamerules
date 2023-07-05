package net.reikeb.notenoughgamerules.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import net.reikeb.notenoughgamerules.Gamerules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {

    @Shadow
    public abstract MinecraftServer getServer();

    @Redirect(method = "tickChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome;canSetIce(Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z"))
    private boolean tickChunkIceRedirection(Biome instance, WorldView world, BlockPos blockPos) {
        return instance.canSetIce(world, blockPos) && this.getServer().getGameRules().getBoolean(Gamerules.DO_ICE_FORM);
    }

    @Redirect(method = "tickChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome;canSetSnow(Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z"))
    private boolean tickChunkSnowRedirection(Biome instance, WorldView world, BlockPos blockPos) {
        return instance.canSetSnow(world, blockPos) && this.getServer().getGameRules().getBoolean(Gamerules.DO_SNOW_MELT);
    }
}
