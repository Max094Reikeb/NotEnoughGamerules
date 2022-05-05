package net.reikeb.notenoughgamerules.mixin;

import net.minecraft.server.filter.TextStream;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.reikeb.notenoughgamerules.Gamerules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ChatMixin {
    @Shadow
    public ServerPlayerEntity player;

    @Inject(method = "handleMessage", at = @At("HEAD"), cancellable = true)
    private void handleMessage(TextStream.Message message, CallbackInfo ci) {
        if ((!message.getRaw().startsWith("/")) && (this.player.getWorld().getGameRules().getBoolean(Gamerules.DISABLE_CHAT)))
            ci.cancel();
    }
}
