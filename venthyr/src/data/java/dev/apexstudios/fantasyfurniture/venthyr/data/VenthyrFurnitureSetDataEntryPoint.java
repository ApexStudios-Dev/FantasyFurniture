package dev.apexstudios.fantasyfurniture.venthyr.data;

import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.apexcore.lib.util.ApexTags;
import dev.apexstudios.apexcore.lib.util.TagPair;
import dev.apexstudios.fantasyfurniture.util.FurnitureClientDataUtil;
import dev.apexstudios.fantasyfurniture.util.FurnitureDataUtil;
import dev.apexstudios.fantasyfurniture.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.venthyr.VenthyrFurnitureSet;
import net.minecraft.data.BlockFamily;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(VenthyrFurnitureSet.ID)
public final class VenthyrFurnitureSetDataEntryPoint {
    public VenthyrFurnitureSetDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> {
            var pack = generator.pack();
            var context = new FurnitureDataUtil.DataGenContext(
                    VenthyrFurnitureSet.REGISTREE,
                    "Venthyr",
                    new BlockFamily.Builder(VenthyrFurnitureSet.PLANKS.value())
                            .recipeGroupPrefix("venthyr")
                            .recipeUnlockedBy("has_" + FurnitureUtil.Names.PLANKS)
                            .stairs(VenthyrFurnitureSet.STAIRS.value())
                            .slab(VenthyrFurnitureSet.SLAB.value())
                            .fence(VenthyrFurnitureSet.FENCE.value())
                            .fenceGate(VenthyrFurnitureSet.FENCE_GATE.value())
                            .trapdoor(VenthyrFurnitureSet.TRAPDOOR.value())
                            .pressurePlate(VenthyrFurnitureSet.PRESSURE_PLATE.value())
                            .sign(VenthyrFurnitureSet.SIGN.sign().value(), VenthyrFurnitureSet.SIGN.wall().value())
                    .getFamily(),
                    BlockTags.MINEABLE_WITH_AXE,
                    new TagPair(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS),
                    BlockTags.WOODEN_STAIRS,
                    new TagPair(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS),
                    new TagPair(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES),
                    new TagPair(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS),
                    new TagPair(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES),
                    new TagPair(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS)
            );

            FurnitureDataUtil.registerDataGen(context, pack);
            FurnitureClientDataUtil.registerDataGen(context, pack);

            pack.providing(ProviderTypes.LOOT_TABLE, (ctx, provider) -> provider
                    .block(blocks -> blocks.dropSelf(VenthyrFurnitureSet.TABLE_CLOTH.value()))
            ).providing(ProviderTypes.BLOCK_TAGS, (ctx, provider) -> FurnitureDataUtil
                    .tag(provider, VenthyrFurnitureSet.TABLE_CLOTH.value(), context.mineableTag(), ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST)
            ).providing(ProviderTypes.RECIPES, (ctx, provider) -> FurnitureDataUtil.furnitureStationRecipe(
                    context, VenthyrFurnitureSet.TABLE_CLOTH, provider, ctx.enabledFeatures()
            )).providing(ProviderTypes.MODELS, (ctx, provider) -> FurnitureClientDataUtil
                    .createTableModel(VenthyrFurnitureSet.TABLE_CLOTH.value(), provider.blockModels())
            ).providing(ProviderTypes.LANGUAGE, (ctx, provider) -> provider
                    .addBlock(VenthyrFurnitureSet.TABLE_CLOTH, context.englishName() + " Table Cloth")
            );
        });
    }
}
