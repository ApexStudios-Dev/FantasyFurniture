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
import java.util.function.Supplier;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Nullable;

public final class FurnitureSetBuilder {
    static final Set<BlockType<?>> WOODEN_BLOCK_TYPES = linked(
            BlockTypes.PLANKS, BlockTypes.WOOL, BlockTypes.CARPET, BlockTypes.DRESSER,
            BlockTypes.STOOL, BlockTypes.CUSHION, BlockTypes.LOCKBOX, BlockTypes.DRAWER,
            BlockTypes.CHAIR, BlockTypes.BOOKSHELF, BlockTypes.BED_SINGLE, BlockTypes.BED_DOUBLE,
            BlockTypes.DOOR_SINGLE, BlockTypes.DOOR_DOUBLE, BlockTypes.DESK_LEFT, BlockTypes.DESK_RIGHT,
            BlockTypes.PAINTING_WIDE, BlockTypes.PAINTING_SMALL, BlockTypes.OVEN, BlockTypes.CHEST,
            BlockTypes.FLOOR_LIGHT, BlockTypes.CHANDELIER, BlockTypes.SHELF, BlockTypes.SOFA,
            BlockTypes.COUNTER, BlockTypes.WALL_LIGHT, BlockTypes.BENCH, BlockTypes.WARDROBE,
            BlockTypes.TABLE, BlockTypes.STAIRS, BlockTypes.SLAB, BlockTypes.FENCE,
            BlockTypes.FENCE_GATE, BlockTypes.TRAP_DOOR, BlockTypes.PRESSURE_PLATE, BlockTypes.BUTTON,
            BlockTypes.HANGING_SIGN, BlockTypes.WALL_HANGING_SIGN, BlockTypes.SIGN, BlockTypes.WALL_SIGN
    );

    final Registree registree;
    final String name;
    final WoodTypeBuilder woodType = WoodTypeBuilder.builder();
    final Map<String, BlockType<?>> blockTypes = Maps.newLinkedHashMap();
    Supplier<? extends BlockBehaviour> baseBlock = () -> Blocks.OAK_PLANKS;
    boolean usesPrefix = false;
    TagKey<Block> mineableTag = BlockTags.MINEABLE_WITH_AXE;
    BlockType<?> coreBlockType = BlockTypes.PLANKS;
    @Nullable ItemLike woolItem = null;
    Supplier<? extends ParticleOptions> flameParticle = () -> ParticleTypes.FLAME;

    FurnitureSetBuilder(Registree registree, String name) {
        this.registree = registree;
        this.name = name;
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

    public FurnitureSetBuilder remove(BlockType<?> blockType) {
        blockTypes.remove(blockType.registryName);
        return this;
    }

    public FurnitureSetBuilder remove(BlockType<?> blockType, BlockType<?>... blockTypes) {
        remove(blockType);

        for(var other : blockTypes) {
            remove(other);
        }

        return this;
    }

    public FurnitureSetBuilder woodType(Consumer<WoodTypeBuilder> builder) {
        builder.accept(woodType);
        return this;
    }

    public FurnitureSetBuilder blockSet(Consumer<BlockSetTypeBuilder> builder) {
        woodType.blockSetType(builder);
        return this;
    }

    public FurnitureSetBuilder baseBlock(Supplier<? extends BlockBehaviour> baseBlock) {
        this.baseBlock = baseBlock;
        return this;
    }

    public FurnitureSetBuilder usesPrefix(boolean usesPrefix) {
        this.usesPrefix = usesPrefix;
        return this;
    }

    public FurnitureSetBuilder usesPrefix() {
        return usesPrefix(true);
    }

    public FurnitureSetBuilder mineable(TagKey<Block> mineableTag) {
        this.mineableTag = mineableTag;
        return this;
    }

    public FurnitureSetBuilder baseBlockType(BlockType<?> coreBlockType) {
        this.coreBlockType = coreBlockType;
        return this;
    }

    public FurnitureSetBuilder wool(ItemLike woolItem) {
        this.woolItem = woolItem;
        return this;
    }

    public FurnitureSetBuilder flame(Supplier<? extends ParticleOptions> flameParticle) {
        this.flameParticle = flameParticle;
        return this;
    }

    private static Set<BlockType<?>> linked(BlockType<?>... blockTypes) {
        var result = Sets.<BlockType<?>>newLinkedHashSetWithExpectedSize(blockTypes.length);
        Collections.addAll(result, blockTypes);
        return result;
    }
}
