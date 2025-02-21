package dev.apexstudios.fantasyfurniture.set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.apexcore.lib.util.BlockSetTypeBuilder;
import dev.apexstudios.apexcore.lib.util.WoodTypeBuilder;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public final class FurnitureSetBuilder {
    private static final Set<BlockType<?>> REQUIRED = linked(
            BlockTypes.PLANKS, BlockTypes.WOOL
    );

    private static final Set<BlockType<?>> DEFAULT_BLOCK_TYPES = linked(
            BlockTypes.CARPET, BlockTypes.DRESSER, BlockTypes.STOOL, BlockTypes.CUSHION,
            BlockTypes.LOCKBOX, BlockTypes.DRAWER, BlockTypes.CHAIR, BlockTypes.BOOKSHELF,
            BlockTypes.BED_SINGLE, BlockTypes.BED_DOUBLE, BlockTypes.DOOR_SINGLE, BlockTypes.DOOR_DOUBLE,
            BlockTypes.DESK_LEFT, BlockTypes.DESK_RIGHT, BlockTypes.PAINTING_WIDE, BlockTypes.PAINTING_SMALL,
            BlockTypes.OVEN, BlockTypes.CHEST, BlockTypes.FLOOR_LIGHT, BlockTypes.CHANDELIER,
            BlockTypes.SHELF, BlockTypes.SOFA, BlockTypes.COUNTER, BlockTypes.WALL_LIGHT,
            BlockTypes.BENCH, BlockTypes.WARDROBE, BlockTypes.TABLE, BlockTypes.STAIRS,
            BlockTypes.SLAB, BlockTypes.FENCE, BlockTypes.FENCE_GATE, BlockTypes.TRAP_DOOR,
            BlockTypes.PRESSURE_PLATE, BlockTypes.BUTTON, BlockTypes.HANGING_SIGN, BlockTypes.WALL_HANGING_SIGN,
            BlockTypes.SIGN, BlockTypes.WALL_SIGN
    );

    final Registree registree;
    final String name;
    final WoodTypeBuilder woodType = WoodTypeBuilder.builder();
    final Map<String, BlockType<?>> blockTypes = Maps.newLinkedHashMap();

    FurnitureSetBuilder(Registree registree, String name) {
        this.registree = registree;
        this.name = name;

        with(REQUIRED);
    }

    public FurnitureSetBuilder with(BlockType<?> blockType) {
        blockTypes.put(blockType.registryName, blockType);
        return this;
    }

    public FurnitureSetBuilder with(BlockType<?> blockType, BlockType<?>... blockTypes) {
        with(blockType);

        for(var other : blockTypes) {
            with(other);
        }

        return this;
    }

    public FurnitureSetBuilder with(Iterable<BlockType<?>> blockTypes) {
        blockTypes.forEach(this::with);
        return this;
    }

    public FurnitureSetBuilder withDefault() {
        return with(DEFAULT_BLOCK_TYPES);
    }

    public FurnitureSetBuilder woodType(Consumer<WoodTypeBuilder> builder) {
        builder.accept(woodType);
        return this;
    }

    public FurnitureSetBuilder blockSet(Consumer<BlockSetTypeBuilder> builder) {
        woodType.blockSetType(builder);
        return this;
    }

    private static Set<BlockType<?>> linked(BlockType<?>... blockTypes) {
        var result = Sets.<BlockType<?>>newLinkedHashSetWithExpectedSize(blockTypes.length);
        Collections.addAll(result, blockTypes);
        return result;
    }
}
