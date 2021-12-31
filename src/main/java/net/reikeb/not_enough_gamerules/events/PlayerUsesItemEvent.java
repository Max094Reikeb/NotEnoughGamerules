package net.reikeb.not_enough_gamerules.events;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class PlayerUsesItemEvent {

    @SubscribeEvent
    public static void onUseItem(LivingEntityUseItemEvent.Finish event) {
        if (event != null && event.getEntity() != null) {
            Entity entity = event.getEntity();
            ItemStack itemstack = event.getItem();
            if (!(entity instanceof Player)) return;
            if ((Math.random() <= ((float) (entity.level.getLevelData().getGameRules().getInt(Gamerules.RAW_MEAT_HUNGER) / 100)))
                    && ((itemstack.getItem() == Items.BEEF) || (itemstack.getItem() == Items.CHICKEN)
                    || (itemstack.getItem() == Items.COD) || (itemstack.getItem() == Items.MUTTON)
                    || (itemstack.getItem() == Items.RABBIT) || (itemstack.getItem() == Items.PORKCHOP)
                    || (itemstack.getItem() == Items.SALMON))) {
                ((Player) entity).addEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 1));
            }
        }
    }
}
