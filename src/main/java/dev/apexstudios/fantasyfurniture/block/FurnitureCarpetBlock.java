package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.fantasyfurniture.set.BlockType;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import net.minecraft.world.level.block.CarpetBlock;

public class FurnitureCarpetBlock extends CarpetBlock {
    protected final FurnitureSet furnitureSet;
    protected final BlockType<?, ?> blockType;

    public FurnitureCarpetBlock(Properties properties) {
        super(properties);

        var injector = (FurnitureBlock.Injector) properties;
        furnitureSet = injector.FantasyFurniture$getFurnitureSet();
        blockType = injector.FantasyFurniture$getBlockType();
    }
}
