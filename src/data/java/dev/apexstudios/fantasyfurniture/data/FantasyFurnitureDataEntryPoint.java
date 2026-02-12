package dev.apexstudios.fantasyfurniture.data;

import dev.apexstudios.apexcore.api.data.ProviderTypes;
import dev.apexstudios.apexcore.api.data.ResourceGenerator;
import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.ctm.CtmPacks;
import dev.apexstudios.fantasyfurniture.common.station.FurnitureStationSetup;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

@Mod(FantasyFurniture.ID)
public final class FantasyFurnitureDataEntryPoint {
    public FantasyFurnitureDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> {
            generator.pack()
                    .providing(ProviderTypes.ITEM_TAGS, (context, provider) -> {
                        provider.tag(ItemTags.PLANKS).withOptionalTag(FantasyFurniture.FURNITURE_PLANKS);
                        provider.tag(ItemTags.WOOL).withOptionalTag(FantasyFurniture.FURNITURE_WOOL);

                        provider.tag(FurnitureStationSetup.BINDING_AGENT).withElement(Items.CLAY_BALL);
                    })
                    .providing(ProviderTypes.LOOT_TABLE, (context, provider) -> provider
                            .knownElements(Registries.BLOCK, FantasyFurniture.BLOCKS::holders)
                            .block(blocks -> blocks.dropSelf(FurnitureStationSetup.BLOCK.value()))
                    )
                    .providing(ProviderTypes.LANGUAGE, (context, provider) -> provider
                            .addBlock(FurnitureStationSetup.BLOCK, "Furniture Station")
                            .add(FantasyFurniture.FURNITURE_PLANKS, "Planks (Furniture Input)")
                            .add(FantasyFurniture.FURNITURE_WOOL, "Wools (Furniture Input)")
                            .add(FantasyFurniture.FURNITURE_BRICKS, "Bricks (Furniture Input)")
                            .add(FurnitureStationSetup.BINDING_AGENT, "Furniture Binding Agents")
                            .add(FantasyFurniture.LOADING_ISSUE_KEY,
                                    formatted("No Furniture Set modules detected.", ChatFormatting.BOLD, ChatFormatting.UNDERLINE, ChatFormatting.RED) +
                                            "\n\nFantasy's Furniture does nothing on it's own, " +
                                            formatted("at least 1", ChatFormatting.BOLD) +
                                            " Furniture Set module must be installed."
                            )
                    )
                    .providing(ProviderTypes.MODELS, (context, provider) -> {
                        provider.knownBlocks(FantasyFurniture.BLOCKS::holders).knownItems(FantasyFurniture.ITEMS::holders);
                        provider.blockModels().createNonTemplateHorizontalBlock(FurnitureStationSetup.BLOCK.value());
                    })
                    .providing(ProviderTypes.BLOCK_TAGS, (context, provider) -> provider
                            .tag(BlockTags.MINEABLE_WITH_AXE)
                            .withElement(FurnitureStationSetup.BLOCK)
                    )
                    .providing(ProviderTypes.RECIPES, (context, provider) -> provider
                            .shapeless(RecipeCategory.MISC, FurnitureStationSetup.BLOCK.value())
                            .requires(Tags.Items.PLAYER_WORKSTATIONS_CRAFTING_TABLES)
                            .requires(Tags.Items.LEATHERS)
                            .unlockedBy("has_crafting_table", provider.has(Tags.Items.PLAYER_WORKSTATIONS_CRAFTING_TABLES))
                            .save(provider.output())
                    );

            CtmPacks.registerMainDataGen(generator);
        });
    }

    private String formatted(String str, ChatFormatting... codes) {
        if(str.isBlank())
            return "";
        if(codes.length == 0)
            return str;

        var nonNull = Stream.of(codes).filter(Objects::nonNull).toList();
        var colors = nonNull.stream().filter(ChatFormatting::isColor).map(Object::toString).collect(Collectors.joining());
        var formats = nonNull.stream().filter(ChatFormatting::isFormat).map(Object::toString).collect(Collectors.joining());
        return colors + formats + str + ChatFormatting.RESET;
    }
}
