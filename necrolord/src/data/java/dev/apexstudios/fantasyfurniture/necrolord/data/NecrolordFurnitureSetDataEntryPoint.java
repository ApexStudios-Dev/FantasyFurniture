package dev.apexstudios.fantasyfurniture.necrolord.data;

import dev.apexstudios.apexcore.api.data.ProviderTypes;
import dev.apexstudios.apexcore.api.data.ResourceGenerator;
import dev.apexstudios.fantasyfurniture.common.ctm.CtmPacks;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureClientDataUtil;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureDataUtil;
import dev.apexstudios.fantasyfurniture.necrolord.common.NecrolordFurnitureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NecrolordFurnitureSet.ID)
public final class NecrolordFurnitureSetDataEntryPoint {
    public NecrolordFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> {
            var pack = generator.pack();
            var context = FurnitureDataUtil.context(NecrolordFurnitureSet.REGISTREE, "Necrolord", "necrolord")
                    .stoneLike()
                    .build();

            context.register(pack);
            FurnitureClientDataUtil.registerDataGen(context, pack);
            CtmPacks.registerDataGen(NecrolordFurnitureSet.REGISTREE, pack, false);

            pack.providing(ProviderTypes.PARTICLES, (ctx, provider) -> provider.sprite(NecrolordFurnitureSet.FLAME_PARTICLE.value(), NecrolordFurnitureSet.FLAME_PARTICLE.getId()));
        });
    }
}
