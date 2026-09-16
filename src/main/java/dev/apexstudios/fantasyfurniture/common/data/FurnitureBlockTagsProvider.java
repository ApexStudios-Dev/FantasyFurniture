package dev.apexstudios.fantasyfurniture.common.data;

import dev.apexstudios.apexcore.api.util.ApexTags;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import org.jspecify.annotations.Nullable;

public class FurnitureBlockTagsProvider extends BlockTagsProvider {
    protected final DataGenContext furniture;

    public FurnitureBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, DataGenContext furniture) {
        super(output, lookupProvider, furniture.registree().namespace());

        this.furniture = furniture;
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        addFurnitureTags();
    }

    protected void addFurnitureTags() {
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.PLANKS, block -> tag(block, furniture.mineableTag(), BlockTags.PLANKS));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.BRICKS, block -> tag(block, furniture.mineableTag(), Tags.Blocks.STONES));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.WOOL, block -> tag(block, BlockTags.WOOL));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.CARPET, block -> tag(block, BlockTags.WOOL_CARPETS));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.DRESSER, block -> tag(block, furniture.mineableTag(), Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.LOCKBOX, block -> tag(block, furniture.mineableTag()));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.DRAWER, block -> tag(block, furniture.mineableTag()));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.CHAIR, block -> tag(block, furniture.mineableTag(), Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.BOOKSHELF, block -> tag(block, furniture.mineableTag(), Tags.Blocks.RELOCATION_NOT_SUPPORTED, BlockTags.ENCHANTMENT_POWER_PROVIDER));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.BED_SINGLE, block -> tag(block, furniture.mineableTag(), BlockTags.BEDS, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.BED_DOUBLE, block -> tag(block, furniture.mineableTag(), BlockTags.BEDS, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.DOOR_SINGLE, block -> tag(block, furniture.mineableTag(), furniture.doorTag().block(), Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.DOOR_DOUBLE, block -> tag(block, furniture.mineableTag(), furniture.doorTag().block(), Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.DESK_LEFT, block -> tag(block, furniture.mineableTag(), Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.DESK_RIGHT, block -> tag(block, furniture.mineableTag(), Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.PAINTING_WIDE, block -> tag(block, furniture.mineableTag(), Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.PAINTING_SMALL, block -> tag(block, furniture.mineableTag()));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.OVEN, block -> tag(block, BlockTags.MINEABLE_WITH_PICKAXE, Tags.Blocks.PLAYER_WORKSTATIONS_FURNACES));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.CHEST, block -> tag(block, furniture.mineableTag(), Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.FLOOR_LIGHT, block -> tag(block, furniture.mineableTag(), Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.CHANDELIER, block -> tag(block, furniture.mineableTag()));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.SHELF, block -> tag(block, furniture.mineableTag()));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.SOFA, block -> tag(block, furniture.mineableTag()));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.COUNTER, block -> tag(block, furniture.mineableTag()));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.WALL_LIGHT, block -> tag(block, furniture.mineableTag()));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.BENCH, block -> tag(block, furniture.mineableTag(), ApexTags.Blocks.SEAT_PER_BLOCK, Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.WARDROBE, block -> tag(block, furniture.mineableTag(), Tags.Blocks.RELOCATION_NOT_SUPPORTED));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.TABLE, block -> tag(block, furniture.mineableTag()));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.STAIRS, block -> tag(block, furniture.mineableTag(), furniture.stairsTag()));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.SLAB, block -> tag(block, furniture.mineableTag(), furniture.slabTag().block()));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.FENCE, block -> tag(block, furniture.mineableTag(), furniture.fenceTag().block()));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.FENCE_GATE, block -> tag(block, furniture.mineableTag(), BlockTags.FENCE_GATES));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.TRAPDOOR, block -> tag(block, furniture.mineableTag(), furniture.trapdoorTag().block()));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.PRESSURE_PLATE, block -> tag(block, furniture.mineableTag(), furniture.pressurePlateTag().block()));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.BUTTON, block -> tag(block, furniture.mineableTag(), furniture.buttonTag().block()));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.HANGING_SIGN, block -> tag(block, furniture.mineableTag(), BlockTags.CEILING_HANGING_SIGNS));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.WALL_HANGING_SIGN, block -> tag(block, furniture.mineableTag(), BlockTags.WALL_HANGING_SIGNS));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.SIGN, block -> tag(block, furniture.mineableTag(), BlockTags.STANDING_SIGNS));
        furniture.block(DataGenType.BLOCK_TAG, FurnitureUtil.Names.WALL_SIGN, block -> tag(block, furniture.mineableTag(), BlockTags.WALL_SIGNS));
    }

    @SafeVarargs
    protected final void tag(Block element, @Nullable TagKey<Block>... tags) {
        for(var tag : tags) {
            if(tag != null) {
                tag(tag).add(element.builtInRegistryHolder().key());
            }
        }
    }
}
