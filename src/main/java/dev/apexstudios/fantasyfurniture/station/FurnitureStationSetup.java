package dev.apexstudios.fantasyfurniture.station;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlock;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredItem;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredMenu;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredRecipeSerializer;
import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.TagKey;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
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
                    Ingredient.CODEC.fieldOf("wool").forGetter(FurnitureStationRecipe::wool),
                    Ingredient.CODEC.fieldOf("binding_agent").forGetter(FurnitureStationRecipe::bindingAgent),
                    ItemStack.STRICT_CODEC.fieldOf("result").forGetter(FurnitureStationRecipe::result)
            ).apply(instance, FurnitureStationRecipe::new)),
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8, FurnitureStationRecipe::group,
                    Ingredient.CONTENTS_STREAM_CODEC, FurnitureStationRecipe::planks,
                    Ingredient.CONTENTS_STREAM_CODEC, FurnitureStationRecipe::wool,
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

    static void register(IEventBus modBus) {
        modBus.addListener(RegisterMenuScreensEvent.class, event -> event.register(MENU.value(), FurnitureStationScreen::new));

        modBus.addListener(BuildCreativeModeTabContentsEvent.class, event -> {
            if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS)
                event.accept(BLOCK_ITEM);
        });
    }
}
