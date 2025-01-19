package dev.apexstudios.fantasyfurniture.nordic;

import dev.apexstudios.apexcore.lib.util.shapes.ApexShapes;
import dev.apexstudios.fantasyfurniture.set.BlockType;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NordicFurnitureSet.ID)
public class NordicFurnitureSet {
    public static final String ID = "fantasyfurniture_nordic";

    public static final FurnitureSet FURNITURE_SET = FurnitureSet.create(ID, builder -> builder
            .blockType(BlockType.DRESSER, block -> block.shape(() -> ApexShapes.join(
                    Block.box(-15D, 0D, 1D, 15D, 16D, 15D),
                    Block.box(-16D, 13D, 14D, 16D, 16D, 16D),
                    Block.box(-16D, 13D, 0D, 16D, 16D, 2D)
            )))
            .blockType(BlockType.CUSHION, block -> block.shape(() -> ApexShapes.join(
                    Block.box(2D, 0D, 2D, 4D, 2D, 4D),
                    Block.box(2D, 0D, 12D, 4D, 2D, 14D),
                    Block.box(12D, 0D, 12D, 14D, 2D, 14D),
                    Block.box(12D, 0D, 2D, 14D, 2D, 4D),
                    Block.box(2D, 5D, 2.25D, 14D, 7D, 13.75D),
                    Block.box(1.75D, 4D, 2D, 14.25D, 5D, 14D),
                    Block.box(2D, 2D, 2.5D, 4D, 4D, 4.5D),
                    Block.box(12D, 2D, 2.5D, 14D, 4D, 4.5D),
                    Block.box(12D, 2D, 11.5D, 14D, 4D, 13.5D),
                    Block.box(2D, 2D, 11.5D, 4D, 4D, 13.5D),
                    Block.box(2.5D, 2.5D, 4.5D, 3.5D, 3.5D, 11.5D),
                    Block.box(12.5D, 2.5D, 4.5D, 13.5D, 3.5D, 11.5D)
            )))
            .blockType(BlockType.STOOL, block -> block.shape(() -> ApexShapes.join(
                    Block.box(2D, 0D, 2D, 4D, 3D, 4D),
                    Block.box(12D, 0D, 12D, 14D, 3D, 14D),
                    Block.box(12D, 0D, 2D, 14D, 3D, 4D),
                    Block.box(2D, 0D, 12D, 4D, 3D, 14D),
                    Block.box(2D, 3D, 11.5D, 4D, 5D, 13.5D),
                    Block.box(12D, 3D, 11.5D, 14D, 5, 13.5D),
                    Block.box(12D, 3D, 2.5D, 14D, 5D, 4.5D),
                    Block.box(1.5D, 5D, 1.75D, 14.5D, 7D, 14.25D),
                    Block.box(2D, 3D, 2.5D, 4D, 5D, 4.5D),
                    Block.box(2.5D, 3.5D, 4.5D, 3.5D, 4.5D, 11.5D),
                    Block.box(12.5D, 3.5D, 4.5D, 13.5D, 4.5D, 11.5D)
            )))
            .blockType(BlockType.LOCKBOX, block -> block.shape(() -> ApexShapes.join(
                    Block.box(2D, 0D, 3D, 14D, 9D, 13D),
                    Block.box(2D, 9D, 5D, 14D, 10D, 11D)
            )))
            .blockType(BlockType.DRAWER, block -> block.shape(() -> ApexShapes.join(
                    Block.box(1D, 0D, 1D, 15D, 13D, 15D),
                    Block.box(0D, 13D, 0D, 16D, 16D, 16D)
            )))
            .blockType(BlockType.CHAIR, block -> block.shape(() -> ApexShapes.join(
                    Block.box(2D, 0D, 2D, 4D, 4D, 4D),
                    Block.box(2.5D, 4.5D, 4.5D, 3.5D, 5.5D, 11.5D),
                    Block.box(12.5D, 4.5D, 4.5D, 13.5D, 5.5D, 11.5D),
                    Block.box(12D, 0D, 2D, 14D, 4D, 4D),
                    Block.box(2D, 0D, 12D, 4D, 4D, 14D),
                    Block.box(2D, 7D, 2D, 14D, 9D, 14D),
                    Block.box(2D, 9D, 13D, 14D, 25D, 14D),
                    Block.box(12D, 0D, 12D, 14D, 4D, 14D),
                    Block.box(2D, 4D, 11.5D, 4D, 7D, 13.5D),
                    Block.box(12D, 4D, 11.5D, 14D, 7D, 13.5D),
                    Block.box(2D, 4D, 2.5D, 4D, 7D, 4.5D),
                    Block.box(12D, 4D, 2.5D, 14D, 7D, 4.5D)
            )))
    );

    public NordicFurnitureSet(IEventBus modBus) {
        FURNITURE_SET.register(modBus);
    }
}
