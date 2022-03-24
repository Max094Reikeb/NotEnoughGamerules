package net.reikeb.not_enough_gamerules.events;

import net.minecraft.potion.EffectInstance;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class EntityDiesEvent {

    @SubscribeEvent
    public static void onEntityCloned(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) return;
        if (event.getEntity().level.isClientSide()) return;
        if (!event.getEntity().level.getGameRules().getBoolean(Gamerules.KEEP_EFFECTS)) return;
        for (int i = 0; i < event.getOriginal().getActiveEffects().size(); i++) {
            EffectInstance effectInstance = event.getOriginal().getActiveEffects().stream().toList().get(i);
            event.getPlayer().addEffect(effectInstance);
        }
    }
}
