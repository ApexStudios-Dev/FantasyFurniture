package dev.apexstudios.fantasyfurniture.venthyr.data;

import dev.apexstudios.apexcore.api.data.ProviderTypes;
import dev.apexstudios.apexcore.api.data.ResourceGenerator;
import dev.apexstudios.fantasyfurniture.common.ctm.CtmPacks;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureClientDataUtil;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureDataUtil;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(VenthyrFurnitureSet.ID)
public final class VenthyrFurnitureSetDataEntryPoint {
    public VenthyrFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> {
            var pack = generator.pack();
            var context = FurnitureDataUtil.createContext(VenthyrFurnitureSet.REGISTREE, "Venthyr", "venthyr");

            context.register(pack);
            FurnitureClientDataUtil.registerDataGen(context, pack);
            CtmPacks.registerDataGen(VenthyrFurnitureSet.REGISTREE, pack, false);

            pack.providing(ProviderTypes.LOOT_TABLE, (ctx, provider) -> provider
                    .block(blocks -> blocks.dropSelf(VenthyrFurnitureSet.TABLE_CLOTH.value()))
            ).providing(ProviderTypes.BLOCK_TAGS, (ctx, provider) -> FurnitureDataUtil
                    .tag(provider, VenthyrFurnitureSet.TABLE_CLOTH.value(), context.mineableTag(FurnitureUtil.Names.TABLE))
            ).providing(ProviderTypes.RECIPES, (ctx, provider) -> context.furnitureStationRecipe(
                    VenthyrFurnitureSet.TABLE_CLOTH.value(), provider
            )).providing(ProviderTypes.MODELS, (ctx, provider) -> FurnitureClientDataUtil
                    .createTableModel(VenthyrFurnitureSet.TABLE_CLOTH.value(), provider.blockModels())
            ).providing(ProviderTypes.LANGUAGE, (ctx, provider) -> provider
                    .addBlock(VenthyrFurnitureSet.TABLE_CLOTH, context.englishName + " Table Cloth")
            );
        });
    }
}
