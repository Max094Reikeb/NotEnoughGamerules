package net.reikeb.not_enough_gamerules.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.Explosion;

import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class PlayerDiesEvent {

    @SubscribeEvent
    public static void onPlayerDies(LivingDeathEvent event) {
        if (event != null && event.getEntity() instanceof PlayerEntity) {
            if (!event.getSource().isBypassInvul()) {
                PlayerEntity player = (PlayerEntity) event.getEntity();
                if (player.level.getLevelData().getGameRules().getBoolean(Gamerules.DEATH_EXPLOSION)) {

                    int counter = player.level.getLevelData().getGameRules().getInt(Gamerules.DEATH_EXPLOSION_TIME) * 20;
                    while(counter > 0) {
                        counter--;
                    }
                    player.level.explode(null, player.getX(), player.getY(), player.getZ(), 10, Explosion.Mode.BREAK);
                }
            }
        }
    }
}
