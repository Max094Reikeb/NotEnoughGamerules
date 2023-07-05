package net.reikeb.notenoughgamerules.mixin;

import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.reikeb.notenoughgamerules.Gamerules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ChatMixin {

    @Shadow
    public ServerPlayerEntity player;

    @Shadow
    private static boolean hasIllegalCharacter(String message) {
        return false;
    }

    @Inject(method = "onChatMessage", at = @At("HEAD"), cancellable = true)
    private void onChatMessage(ChatMessageC2SPacket packet, CallbackInfo ci) {
        if (!hasIllegalCharacter(packet.chatMessage())) {
            if (!packet.chatMessage().startsWith("/") && (this.player.getWorld().getGameRules().getBoolean(Gamerules.DISABLE_CHAT)))
                ci.cancel();
        }
    }
}
