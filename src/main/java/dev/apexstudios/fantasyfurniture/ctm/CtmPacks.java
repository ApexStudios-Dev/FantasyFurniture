package dev.apexstudios.fantasyfurniture.ctm;

import com.google.common.collect.Lists;
import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.apexcore.lib.data.pack.PackGenerator;
import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.util.FurnitureUtil;
import dev.apexstudios.registree.api.Registree;
import java.util.List;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.AddPackFindersEvent;

public final class CtmPacks {
    private static final List<CtmPack> PACKS = List.of(
            // TODO: Re-enable once Athena updates, generated output needs testing
            /*CtmPack.builder(AthenaProvider.ID)
                    .displayName("Athena")
                    .providing(AthenaProvider.PROVIDER_TYPE, AthenaProvider::with)
                    .build(),*/

            CtmPack.builder(FusionProvider.ID)
                    .displayName("Fusion")
                    .providing(FusionProvider.PROVIDER_TYPE, FusionProvider::with)
                    .build(),

            CtmPack.builder(ConTexProvider.ID)
                    .displayName("ConTex")
                    .packId("ctm-context")
                    .providing(ConTexProvider.PROVIDER_TYPE, ConTexProvider::with)
                    .build(),

            // 3D-Doors pack added via ctm-packing system
            CtmPack.builder(FantasyFurniture.ID)
                    .description("Restores Fantasy's Furniture old 3D Door Item Models")
                    .displayName("3D Doors")
                    .packId("3d-doors")
                    .providing(ProviderTypes.MODELS, () -> LegacyDoorsProvider::register)
                    .build()
    );

    static final List<Registree> REFERENCES = Lists.newArrayList();

    public static void register(Registree registree) {
        REFERENCES.add(registree);
    }

    public static void register(IEventBus modBus) {
        modBus.addListener(AddPackFindersEvent.class, event -> PACKS.stream().filter(CtmPack::isEnabled).forEach(pack -> event.addPackFinders(
                FantasyFurniture.identifier("packs/" + pack.packId()),
                PackType.CLIENT_RESOURCES,
                Component.literal(pack.displayName()),
                PackSource.BUILT_IN,
                false,
                Pack.Position.TOP
        )));
    }

    public static void registerMainDataGen(ResourceGenerator generator) {
        PACKS.forEach(pack -> Util.make(generator
                .pack(pack.packId())
                .description(pack.description())
                .packType(PackType.CLIENT_RESOURCES), pack::provide
        ));
    }

    public static void registerDataGen(Registree registree, PackGenerator<?> pack, boolean dyeable) {
        pack.providing(TextureProvider.PROVIDER_TYPE, (context, provider) -> FurnitureUtil.Names.block(registree, FurnitureUtil.Names.WOOL, block -> provider.with(block, dyeable)));
    }
}
