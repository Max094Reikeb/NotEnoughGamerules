package net.reikeb.not_enough_gamerules.events;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.reikeb.not_enough_gamerules.Gamerules;
import net.reikeb.not_enough_gamerules.NotEnoughGamerules;

@Mod.EventBusSubscriber(modid = NotEnoughGamerules.MODID)
public class LightningEvent {

    @SubscribeEvent
    public static void onLightning(EntityStruckByLightningEvent event) {
        int lightningDamage = event.getLightning().level.getLevelData().getGameRules().getInt(Gamerules.LIGHTNING_DAMAGE);
        if (lightningDamage > -1) {
            event.setCanceled(event.isCancelable());
            event.getEntity().hurt(DamageSource.LIGHTNING_BOLT, (float) lightningDamage);
        }
    }
}
