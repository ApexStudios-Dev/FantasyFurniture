package dev.apexstudios.fantasyfurniture.common.station;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.registree.holder.DeferredMenuType;
import dev.apexstudios.registree.holder.DeferredRecipeBookCategory;
import dev.apexstudios.registree.holder.DeferredRecipeSerializer;
import dev.apexstudios.registree.holder.DeferredRecipeType;
import java.util.stream.Stream;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.registries.DeferredBlock;

public interface FurnitureStationSetup {
    DeferredBlock<FurnitureStationBlock> BLOCK = FantasyFurniture.BLOCKS.builder("furniture_station", FurnitureStationBlock::new)
            .properties(properties -> properties
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2F, 3F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
            )
            .item(item -> item.creativeModeTab(CreativeModeTabs.FUNCTIONAL_BLOCKS))
            .register();

    DeferredMenuType<FurnitureStationMenu> MENU = FantasyFurniture.MENU_TYPES.register("furniture_station", FurnitureStationMenu::new, () -> () -> FurnitureStationScreen::new);

    DeferredRecipeSerializer<FurnitureStationRecipe> RECIPE_SERIALIZER = FantasyFurniture.RECIPE_SERIALIZERS.register(
            "furniture_station",
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Codec.STRING.optionalFieldOf("group", "").forGetter(FurnitureStationRecipe::group),
                    Ingredient.CODEC.fieldOf("planks").forGetter(FurnitureStationRecipe::planks),
                    Ingredient.CODEC.optionalFieldOf("wool").forGetter(FurnitureStationRecipe::wool),
                    Ingredient.CODEC.fieldOf("binding_agent").forGetter(FurnitureStationRecipe::bindingAgent),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(FurnitureStationRecipe::result)
            ).apply(instance, FurnitureStationRecipe::new)),
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8, FurnitureStationRecipe::group,
                    Ingredient.CONTENTS_STREAM_CODEC, FurnitureStationRecipe::planks,
                    ByteBufCodecs.optional(Ingredient.CONTENTS_STREAM_CODEC), FurnitureStationRecipe::wool,
                    Ingredient.CONTENTS_STREAM_CODEC, FurnitureStationRecipe::bindingAgent,
                    ItemStackTemplate.STREAM_CODEC, FurnitureStationRecipe::result,
                    FurnitureStationRecipe::new
            )
    );

    DeferredRecipeType<FurnitureStationRecipe> RECIPE_TYPE = FantasyFurniture.RECIPE_TYPES.register("furniture_station");
    DeferredRecipeBookCategory RECIPE_BOOK_CATEGORY = FantasyFurniture.RECIPE_BOOK_CATEGORIES.register("furniture_station");

    TagKey<Item> BINDING_AGENT = FantasyFurniture.ITEMS.tag("binding_agent");

    int SLOT_PLANKS = 0;
    int SLOT_WOOL = 1;
    int SLOT_BINDING_AGENT = 2;
    int SLOTS = 3;

    static Stream<RecipeHolder<FurnitureStationRecipe>> recipes(Level level) {
        if(level instanceof ServerLevel sLevel)
            return sLevel.recipeAccess().recipeMap().byType(RECIPE_TYPE.value()).stream();
        if(FMLEnvironment.getDist().isClient())
            return FurnitureStationClientSetup.RECIPES.stream();

        return Stream.empty();
    }

    static Stream<RecipeHolder<FurnitureStationRecipe>> recipes(FurnitureStationRecipeInput input, Level level) {
        return recipes(level)
                .filter(recipe -> recipe.value().matches(input, level));
    }

    static void register() {
        NeoForge.EVENT_BUS.addListener(OnDatapackSyncEvent.class, event -> event.sendRecipes(RECIPE_TYPE.value()));
    }
}
