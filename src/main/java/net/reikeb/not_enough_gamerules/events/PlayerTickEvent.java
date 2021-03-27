package net.reikeb.not_enough_gamerules.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TranslationTextComponent;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class PlayerTickEvent {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !event.player.level.isClientSide) {
            PlayerEntity entity = event.player;
            if ((entity.getFoodData().getFoodLevel() < 20)
                    && !entity.level.getLevelData().getGameRules().getBoolean(Gamerules.NATURAL_HUNGER)) {
                entity.getFoodData().setFoodLevel(20);
            }
            if (entity.getY() < entity.level.getLevelData().getGameRules().getInt(Gamerules.SKY_HIGH)) {
                entity.displayClientMessage(new TranslationTextComponent("message.not_enough_gamerules.sky_high_warning"), true);

                if (entity.level.getGameTime() % 200 == 0 && entity.tickCount > 199) {
                    entity.hurt(new DamageSource("sky_high").bypassArmor(), (float) 10);
                }
            }
        }
    }
}
