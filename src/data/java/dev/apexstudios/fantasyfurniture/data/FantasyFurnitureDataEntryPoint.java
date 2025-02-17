package dev.apexstudios.fantasyfurniture.data;

import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationSetup;
import java.util.function.BiFunction;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.core.Direction;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

@Mod(value = FantasyFurniture.ID, dist = Dist.CLIENT)
public final class FantasyFurnitureDataEntryPoint {
    public FantasyFurnitureDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> generator
                .pack()
                .providing(ProviderTypes.ITEM_TAGS, (context, provider) -> {
                    provider.tag(ItemTags.PLANKS).withOptionalTag(FantasyFurniture.FURNITURE_PLANKS);
                    provider.tag(ItemTags.WOOL).withOptionalTag(FantasyFurniture.FURNITURE_WOOL);

                    provider.tag(FurnitureStationSetup.BINDING_AGENT).withElement(Items.CLAY_BALL);
                })
                .providing(ProviderTypes.LOOT_TABLE, (context, provider) -> {
                    provider.fromRegistree(FantasyFurniture.REGISTREE);
                    provider.block(blocks -> blocks.dropSelf(FurnitureStationSetup.BLOCK.value()));
                })
                .providing(ProviderTypes.LANGUAGE, (context, provider) -> provider
                        .addBlock(FurnitureStationSetup.BLOCK, "Furniture Station")
                        .add(FantasyFurniture.FURNITURE_PLANKS, "Planks (Furniture Input)")
                        .add(FantasyFurniture.FURNITURE_WOOL, "Wools (Furniture Input)")
                        .add(FurnitureStationSetup.BINDING_AGENT, "Furniture Binding Agents")
                )
                .providing(ProviderTypes.MODELS, (context, provider) -> {
                    provider.fromRegistree(FantasyFurniture.REGISTREE);

                    var block = FurnitureStationSetup.BLOCK.value();
                    var facingComponent = block.getComponentOrThrow(BlockComponentTypes.FACING);

                    provider.blockModels().blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
                            .with(createHorizontalFacingDispatch(facingComponent.getProperty(), (facing, variant) -> variant.with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block))))
                    );
                })
                .providing(ProviderTypes.BLOCK_TAGS, (context, provider) -> provider.tag(BlockTags.MINEABLE_WITH_AXE).withElement(FurnitureStationSetup.BLOCK))
                .providing(ProviderTypes.RECIPES, (context, provider) -> provider
                        .shapeless(RecipeCategory.MISC, FurnitureStationSetup.BLOCK)
                        .requires(Tags.Items.PLAYER_WORKSTATIONS_CRAFTING_TABLES)
                        .requires(Tags.Items.LEATHERS)
                        .unlockedBy("has_crafting_table", provider.has(Tags.Items.PLAYER_WORKSTATIONS_CRAFTING_TABLES))
                        .save(provider.output())
                )
        );
    }

    private PropertyDispatch createHorizontalFacingDispatch(Property<Direction> property, BiFunction<Direction, Variant, Variant> modifier) {
        return PropertyDispatch.property(property)
                .select(Direction.EAST, modifier.apply(Direction.EAST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)))
                .select(Direction.SOUTH, modifier.apply(Direction.SOUTH, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)))
                .select(Direction.WEST, modifier.apply(Direction.WEST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)))
                .select(Direction.NORTH, modifier.apply(Direction.NORTH, Variant.variant()));
    }
}
