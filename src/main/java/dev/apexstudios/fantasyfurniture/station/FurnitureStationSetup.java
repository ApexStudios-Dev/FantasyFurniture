package dev.apexstudios.fantasyfurniture.station;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import dev.apexstudios.registree.api.holder.DeferredBlock;
import dev.apexstudios.registree.api.holder.DeferredItem;
import dev.apexstudios.registree.api.holder.DeferredMenu;
import dev.apexstudios.registree.api.holder.DeferredRecipeSerializer;
import java.util.stream.Stream;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

public interface FurnitureStationSetup {
    DeferredBlock<FurnitureStationBlock> BLOCK = FantasyFurniture.REGISTREE.registerBlock("furniture_station", FurnitureStationBlock::new, BlockBehaviour.Properties.of()
            .mapColor(MapColor.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2F, 3F)
            .sound(SoundType.WOOD)
            .ignitedByLava()
    );

    DeferredItem<BlockItem> BLOCK_ITEM = FantasyFurniture.REGISTREE.registerSimpleBlockItem(BLOCK);
    DeferredMenu<FurnitureStationMenu> MENU = FantasyFurniture.REGISTREE.registerMenu("furniture_station", (MenuType.MenuSupplier<FurnitureStationMenu>) FurnitureStationMenu::new);

    DeferredRecipeSerializer<FurnitureStationRecipe> RECIPE_SERIALIZER = FantasyFurniture.REGISTREE.registerRecipeSerializer(
            "furniture_station",
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Codec.STRING.optionalFieldOf("group", "").forGetter(FurnitureStationRecipe::group),
                    Ingredient.CODEC.fieldOf("planks").forGetter(FurnitureStationRecipe::planks),
                    Ingredient.CODEC.optionalFieldOf("wool").forGetter(FurnitureStationRecipe::wool),
                    Ingredient.CODEC.fieldOf("binding_agent").forGetter(FurnitureStationRecipe::bindingAgent),
                    ItemStack.STRICT_CODEC.fieldOf("result").forGetter(FurnitureStationRecipe::result)
            ).apply(instance, FurnitureStationRecipe::new)),
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8, FurnitureStationRecipe::group,
                    Ingredient.CONTENTS_STREAM_CODEC, FurnitureStationRecipe::planks,
                    ByteBufCodecs.optional(Ingredient.CONTENTS_STREAM_CODEC), FurnitureStationRecipe::wool,
                    Ingredient.CONTENTS_STREAM_CODEC, FurnitureStationRecipe::bindingAgent,
                    ItemStack.STREAM_CODEC, FurnitureStationRecipe::result,
                    FurnitureStationRecipe::new
            )
    );

    DeferredHolder<RecipeType<?>, RecipeType<FurnitureStationRecipe>> RECIPE_TYPE = FantasyFurniture.REGISTREE.registerForHolder(Registries.RECIPE_TYPE, "furniture_station", RecipeType::simple);
    DeferredHolder<RecipeBookCategory, RecipeBookCategory> RECIPE_BOOK_CATEGORY = FantasyFurniture.REGISTREE.registerForHolder(Registries.RECIPE_BOOK_CATEGORY, "furniture_station", RecipeBookCategory::new);

    TagKey<Item> BINDING_AGENT = FantasyFurniture.REGISTREE.tag(Registries.ITEM, "binding_agent");

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

    static void register(IEventBus modBus) {
        modBus.addListener(RegisterMenuScreensEvent.class, event -> event.register(MENU.value(), FurnitureStationScreen::new));

        modBus.addListener(BuildCreativeModeTabContentsEvent.class, event -> {
            if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS)
                event.accept(BLOCK_ITEM);
        });

        NeoForge.EVENT_BUS.addListener(OnDatapackSyncEvent.class, event -> event.sendRecipes(RECIPE_TYPE.value()));
    }
}
