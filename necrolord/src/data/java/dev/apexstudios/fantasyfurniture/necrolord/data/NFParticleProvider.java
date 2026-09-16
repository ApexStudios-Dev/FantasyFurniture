package dev.apexstudios.fantasyfurniture.necrolord.data;

import dev.apexstudios.fantasyfurniture.necrolord.common.NecrolordFurnitureSet;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.data.ParticleDescriptionProvider;

final class NFParticleProvider extends ParticleDescriptionProvider {
    NFParticleProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void addDescriptions() {
        spriteSet(NecrolordFurnitureSet.FLAME_PARTICLE.value(), NecrolordFurnitureSet.FLAME_PARTICLE.getId());
    }
}
