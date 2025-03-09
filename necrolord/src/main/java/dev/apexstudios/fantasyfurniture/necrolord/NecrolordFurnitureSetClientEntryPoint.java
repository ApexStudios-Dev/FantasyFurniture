package dev.apexstudios.fantasyfurniture.necrolord;

import net.minecraft.client.particle.FlameParticle;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@Mod(value = NecrolordFurnitureSet.ID, dist = Dist.CLIENT)
public final class NecrolordFurnitureSetClientEntryPoint {
    public NecrolordFurnitureSetClientEntryPoint(IEventBus modBus) {
        modBus.addListener(RegisterParticleProvidersEvent.class, event -> event
                .registerSpriteSet(NecrolordFurnitureSet.FLAME_PARTICLE.value(), FlameParticle.Provider::new)
        );
    }
}
