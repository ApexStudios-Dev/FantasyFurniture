package dev.apexstudios.fantasyfurniture.common.data;

import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import org.jspecify.annotations.Nullable;

public class FurnitureItemTagsProvider extends ItemTagsProvider {
    private final DataGenContext furniture;

    public FurnitureItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, DataGenContext furniture) {
        super(output, lookupProvider, furniture.registree().namespace());

        this.furniture = furniture;
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        addFurnitureTags();
    }

    protected void addFurnitureTags() {
        furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.PLANKS, item -> tag(item, FantasyFurniture.FURNITURE_PLANKS));
        furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.BRICKS, item -> tag(item, FantasyFurniture.FURNITURE_BRICKS));
        furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.WOOL, item -> tag(item, FantasyFurniture.FURNITURE_WOOL));
        furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.CARPET, item -> tag(item, ItemTags.WOOL_CARPETS));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.DRESSER, item -> tag(item, ));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.LOCKBOX, item -> tag(item, ));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.DRAWER, item -> tag(item, ));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.CHAIR, item -> tag(item, ));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.BOOKSHELF, item -> tag(item, ));
        furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.BED_SINGLE, item -> tag(item, ItemTags.BEDS));
        furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.BED_DOUBLE, item -> tag(item, ItemTags.BEDS));
        furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.DOOR_SINGLE, item -> tag(item, furniture.doorTag().item()));
        furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.DOOR_DOUBLE, item -> tag(item, furniture.doorTag().item()));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.DESK_LEFT, item -> tag(item, ));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.DESK_RIGHT, item -> tag(item, ));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.PAINTING_WIDE, item -> tag(item, ));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.PAINTING_SMALL, item -> tag(item, ));
        furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.OVEN, item -> tag(item, Tags.Items.PLAYER_WORKSTATIONS_FURNACES));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.CHEST, item -> tag(item, ));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.FLOOR_LIGHT, item -> tag(item, ));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.CHANDELIER, item -> tag(item, ));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.SHELF, item -> tag(item, ));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.SOFA, item -> tag(item, ));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.COUNTER, item -> tag(item, ));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.WALL_LIGHT, item -> tag(item, ));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.BENCH, item -> tag(item, ));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.WARDROBE, item -> tag(item, ));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.TABLE, item -> tag(item, ));
        furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.STAIRS, item -> tag(item, furniture.stairsTag().item()));
        furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.SLAB, item -> tag(item, furniture.slabTag().item()));
        furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.FENCE, item -> tag(item, furniture.fenceTag().item()));
        furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.FENCE_GATE, item -> tag(item, furniture.fenceGateTag().item()));
        furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.TRAPDOOR, item -> tag(item, furniture.trapdoorTag().item()));
        furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.PRESSURE_PLATE, item -> tag(item, furniture.pressurePlateTag().item()));
        furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.BUTTON, item -> tag(item, furniture.buttonTag().item()));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.HANGING_SIGN, item -> tag(item, ));
        furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.WALL_HANGING_SIGN, item -> tag(item, ItemTags.HANGING_SIGNS));
        furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.SIGN, item -> tag(item, ItemTags.SIGNS));
        // furniture.item(DataGenType.ITEM_TAG, FurnitureUtil.Names.WALL_SIGN, item -> tag(item, ));
    }

    @SafeVarargs
    protected final void tag(Item element, @Nullable TagKey<Item>... tags) {
        for(var tag : tags) {
            if(tag != null) {
                tag(tag).add(element.builtInRegistryHolder().key());
            }
        }
    }
}
