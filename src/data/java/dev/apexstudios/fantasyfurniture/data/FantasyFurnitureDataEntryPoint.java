package dev.apexstudios.fantasyfurniture.data;

import dev.apexstudios.apexcore.api.data.ProviderTypes;
import dev.apexstudios.apexcore.api.data.ResourceGenerator;
import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.ctm.CtmPacks;
import dev.apexstudios.fantasyfurniture.common.station.FurnitureStationSetup;
import net.minecraft.ChatFormatting;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.network.chat.Component;
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
            var experimentalDescKey = FantasyFurniture.EXPERIMENTAL_FLAG_KEY + ".desc";

            generator.pack()
                    .providing(ProviderTypes.ITEM_TAGS, (context, provider) -> {
                        provider.tag(ItemTags.PLANKS).withOptionalTag(FantasyFurniture.FURNITURE_PLANKS);
                        provider.tag(ItemTags.WOOL).withOptionalTag(FantasyFurniture.FURNITURE_WOOL);

                        provider.tag(FurnitureStationSetup.BINDING_AGENT).withElement(Items.CLAY_BALL);
                    })
                    .providing(ProviderTypes.LOOT_TABLE, (context, provider) -> provider
                            .fromRegistree(FantasyFurniture.REGISTREE)
                            .block(blocks -> blocks.dropSelf(FurnitureStationSetup.BLOCK.value()))
                    )
                    .providing(ProviderTypes.LANGUAGE, (context, provider) -> provider
                            .addBlock(FurnitureStationSetup.BLOCK, "Furniture Station")
                            .add(FantasyFurniture.FURNITURE_PLANKS, "Planks (Furniture Input)")
                            .add(FantasyFurniture.FURNITURE_WOOL, "Wools (Furniture Input)")
                            .add(FantasyFurniture.FURNITURE_BRICKS, "Bricks (Furniture Input)")
                            .add(FurnitureStationSetup.BINDING_AGENT, "Furniture Binding Agents")
                            .add(FantasyFurniture.LOADING_ISSUE_KEY, "" +
                                    ChatFormatting.RED + ChatFormatting.BOLD + ChatFormatting.UNDERLINE +
                                    "No Furniture Set modules detected." +
                                    ChatFormatting.RESET +
                                    "\n\nFantasy's Furniture does nothing on it's own, " +
                                    ChatFormatting.BOLD +
                                    "at least 1" +
                                    ChatFormatting.RESET +
                                    " Furniture Set module must be installed."
                            )
                            .add(FantasyFurniture.EXPERIMENTAL_FLAG_KEY, "Fantasy's Furniture - Experimental")
                            .add(experimentalDescKey, "Experimental Furniture Blocks")
                    )
                    .providing(ProviderTypes.MODELS, (context, provider) -> {
                        provider.fromRegistree(FantasyFurniture.REGISTREE);
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

            generator.pack("experimental")
                    .enabling(FantasyFurniture.EXPERIMENTAL)
                    .description(Component.translatable(experimentalDescKey));

            CtmPacks.registerMainDataGen(generator);
        });
    }
}
