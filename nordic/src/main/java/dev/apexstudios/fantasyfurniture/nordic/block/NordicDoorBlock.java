package dev.apexstudios.fantasyfurniture.nordic.block;

import dev.apexstudios.fantasyfurniture.block.FurnitureDoorBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class NordicDoorBlock extends FurnitureDoorBlock {
    public static final VoxelShape SHAPE = box(0D, 0D, 0D, 3D, 32D, 16D);

    public NordicDoorBlock(Properties properties, BlockSetType blockSet) {
        super(properties, blockSet);
    }
}
