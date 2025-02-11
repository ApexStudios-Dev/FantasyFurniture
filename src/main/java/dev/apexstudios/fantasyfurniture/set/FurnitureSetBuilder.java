package dev.apexstudios.fantasyfurniture.set;

import com.google.common.collect.Sets;
import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.apexcore.lib.util.BlockSetTypeBuilder;
import dev.apexstudios.apexcore.lib.util.WoodTypeBuilder;
import java.util.Collections;
import java.util.Set;
import java.util.function.Consumer;

public final class FurnitureSetBuilder {
    final Registree registree;
    final String name;
    final WoodTypeBuilder woodType = WoodTypeBuilder.builder();
    final Set<BlockType<?>> blockTypes = Sets.newLinkedHashSet();

    FurnitureSetBuilder(Registree registree, String name) {
        this.registree = registree;
        this.name = name;
    }

    public FurnitureSetBuilder with(BlockType<?> blockType) {
        blockTypes.add(blockType);
        return this;
    }

    public FurnitureSetBuilder with(BlockType<?> blockType, BlockType<?>... blockTypes) {
        this.blockTypes.add(blockType);
        Collections.addAll(this.blockTypes, blockTypes);
        return this;
    }

    public FurnitureSetBuilder with(Iterable<BlockType<?>> blockTypes) {
        blockTypes.forEach(this::with);
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
}
