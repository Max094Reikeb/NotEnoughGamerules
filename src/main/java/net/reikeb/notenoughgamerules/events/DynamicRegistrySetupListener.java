package net.reikeb.notenoughgamerules.events;

import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.fabricmc.fabric.api.event.registry.DynamicRegistryView;
import net.minecraft.registry.RegistryKeys;
import net.reikeb.notenoughgamerules.NEGDamageTypes;

public class DynamicRegistrySetupListener implements DynamicRegistrySetupCallback {

    @Override
    public void onRegistrySetup(DynamicRegistryView registryView) {
        registryView.getOptional(RegistryKeys.DAMAGE_TYPE).ifPresent(NEGDamageTypes::register);
    }
}
