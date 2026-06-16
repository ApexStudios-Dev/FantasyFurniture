package dev.apexstudios.fantasyfurniture.common.ctm;

import com.google.common.collect.Lists;
import dev.apexstudios.apexcore.api.data.ProviderTypes;
import dev.apexstudios.apexcore.api.data.ResourceGenerator;
import dev.apexstudios.apexcore.api.data.pack.PackGenerator;
import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.registree.BaseRegistree;
import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.util.Util;
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

            CtmPack.builder(ConTexProvider.XFACT_ID)
                    .displayName("ConTex")
                    .packId("ctm-contex")
                    .providing(ConTexProvider.XFACT_PROVIDER_TYPE, ConTexProvider::with)
                    .build(),

            CtmPack.builder(ConTexProvider.SOARYN_ID)
                    .displayName("ConText Matters")
                    .packId("ctm-context-matters")
                    .providing(ConTexProvider.SOARYN_PROVIDER_TYPE, ConTexProvider::with)
                    .build(),

            // 3D-Doors pack added via ctm-packing system
            CtmPack.builder(FantasyFurniture.ID)
                    .description("Restores Fantasy's Furniture old 3D Door Item Models")
                    .displayName("3D Doors")
                    .packId("3d-doors")
                    .providing(ProviderTypes.MODELS, () -> LegacyDoorsProvider::register)
                    .build()
    );

    static final List<BaseRegistree<?>> REFERENCES = Lists.newArrayList();

    public static void register(BaseRegistree<?> registree) {
        REFERENCES.add(registree);
    }

    public static void register() {
        FantasyFurniture.REGISTREE.event(AddPackFindersEvent.class, event -> PACKS.stream().filter(CtmPack::isEnabled).forEach(pack -> event.addPackFinders(
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

    public static void registerDataGen(BaseRegistree<?> registree, PackGenerator<?> pack, boolean dyeable) {
        pack.providing(TextureProvider.PROVIDER_TYPE, (context, provider) -> FurnitureUtil.Names.block(registree, FurnitureUtil.Names.WOOL, block -> provider.with(block, dyeable)));
    }
}
