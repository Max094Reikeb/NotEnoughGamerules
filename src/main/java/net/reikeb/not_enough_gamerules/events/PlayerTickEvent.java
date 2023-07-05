package net.reikeb.not_enough_gamerules.events;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class PlayerTickEvent {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.player.level().isClientSide) return;

        Player player = event.player;
        int naturalHunger = player.level().getLevelData().getGameRules().getInt(Gamerules.NATURAL_HUNGER);

        if (naturalHunger > -1 && player.getFoodData().getFoodLevel() < naturalHunger)
            player.getFoodData().setFoodLevel(20);

        if (player.getY() < player.level().getLevelData().getGameRules().getInt(Gamerules.SKY_HIGH)) {
            player.displayClientMessage(Component.translatable("message.not_enough_gamerules.sky_high_warning"), true);

            if (player.level().getGameTime() % 200 == 0 && player.tickCount > 199) {
                player.hurt(NotEnoughGamerules.damageSource(player.level(), "sky_high"), (float) 10);
            }
        }
    }
}
