package dev.apexstudios.fantasyfurniture.mixin;

import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlock;
import dev.apexstudios.fantasyfurniture.set.BlockType;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import java.util.Objects;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BlockBehaviour.Properties.class)
public class BlockPropertiesMixin implements FurnitureBlock.Injector {
    @Unique
    @Nullable
    private FurnitureSet FantasyFurniture$furnitureSet = null;

    @Unique
    @Nullable
    private BlockType<?, ?> FantasyFurniture$blockType = null;

    @Override
    public void FantasyFurniture$setFurnitureSet(FurnitureSet furnitureSet) {
        FantasyFurniture$furnitureSet = furnitureSet;
    }

    @Override
    public FurnitureSet FantasyFurniture$getFurnitureSet() {
        return Objects.requireNonNull(FantasyFurniture$furnitureSet);
    }

    @Override
    public void FantasyFurniture$setBlockType(BlockType<?, ?> blockType) {
        FantasyFurniture$blockType = blockType;
    }

    @Override
    public BlockType<?, ?> FantasyFurniture$getBlockType() {
        return Objects.requireNonNull(FantasyFurniture$blockType);
    }
}
