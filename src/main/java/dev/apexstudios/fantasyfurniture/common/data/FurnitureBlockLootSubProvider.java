package dev.apexstudios.fantasyfurniture.common.data;

import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BedPart;

public class FurnitureBlockLootSubProvider extends VanillaBlockLoot {
    private final DataGenContext furniture;

    public FurnitureBlockLootSubProvider(Context output, DataGenContext furniture) {
        super(output);

        this.furniture = furniture;
    }

    @Override
    protected void generate() {
        addFurnitureBlocks();
    }

    @Override
    public Iterable<Block> getKnownBlocks() {
        return furniture.registree().listElements(Registries.BLOCK).map(Holder::value).toList();
    }

    protected void addFurnitureBlocks() {
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.PLANKS, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.BRICKS, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.WOOL, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.CARPET, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.DRESSER, block -> add(block, this::createNameableBlockEntityTable));
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.STOOL, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.CUSHION, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.LOCKBOX, block -> add(block, this::createNameableBlockEntityTable));
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.DRAWER, block -> add(block, this::createNameableBlockEntityTable));
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.CHAIR, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.BOOKSHELF, block -> add(block, this::createNameableBlockEntityTable));
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.BED_SINGLE, block -> add(block, $ -> createSinglePropConditionTable($, BedBlock.PART, BedPart.HEAD)));
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.BED_DOUBLE, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.DOOR_SINGLE, block -> add(block, this::createDoorTable));
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.DOOR_DOUBLE, block -> add(block, this::createDoorTable));
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.DESK_LEFT, block -> add(block, this::createNameableBlockEntityTable));
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.DESK_RIGHT, block -> add(block, this::createNameableBlockEntityTable));
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.PAINTING_WIDE, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.PAINTING_SMALL, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.OVEN, block -> add(block, this::createNameableBlockEntityTable));
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.CHEST, block -> add(block, this::createNameableBlockEntityTable));
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.FLOOR_LIGHT, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.CHANDELIER, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.SHELF, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.SOFA, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.COUNTER, block -> add(block, this::createNameableBlockEntityTable));
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.WALL_LIGHT, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.BENCH, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.WARDROBE, block -> add(block, this::createNameableBlockEntityTable));
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.TABLE, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.STAIRS, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.SLAB, block -> add(block, this::createSlabItemTable));
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.FENCE, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.FENCE_GATE, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.TRAPDOOR, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.PRESSURE_PLATE, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.BUTTON, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.HANGING_SIGN, this::dropSelf);
        // furniture.block(DataGenType.LOOT_TABLE, Names.WALL_HANGING_SIGN, this::dropSelf);
        furniture.block(DataGenType.LOOT_TABLE, FurnitureUtil.Names.SIGN, this::dropSelf);
        // furniture.block(DataGenType.LOOT_TABLE, Names.WALL_SIGN, this::dropSelf);
    }
}
