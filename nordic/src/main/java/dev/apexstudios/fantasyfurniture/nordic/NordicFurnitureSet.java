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
            // region: Dresser
            .blockType(BlockType.DRESSER, block -> block.shape(() -> ApexShapes.join(
                    Block.box(-15D, 0D, 1D, 15D, 16D, 15D),
                    Block.box(-16D, 13D, 14D, 16D, 16D, 16D),
                    Block.box(-16D, 13D, 0D, 16D, 16D, 2D)
            )))
            // endregion
            // region: Cushion
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
            // endregion
            // region: Stool
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
            // endregion
            // region: Lockbox
            .blockType(BlockType.LOCKBOX, block -> block.shape(() -> ApexShapes.join(
                    Block.box(2D, 0D, 3D, 14D, 9D, 13D),
                    Block.box(2D, 9D, 5D, 14D, 10D, 11D)
            )))
            // endregion
            // region: Drawer
            .blockType(BlockType.DRAWER, block -> block.shape(() -> ApexShapes.join(
                    Block.box(1D, 0D, 1D, 15D, 13D, 15D),
                    Block.box(0D, 13D, 0D, 16D, 16D, 16D)
            )))
            // endregion
            // region: Chair
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
            // endregion
            // region: Bookshelf
            .blockType(BlockType.BOOKSHELF, block -> block.shape(() -> ApexShapes.join(
                    Block.box(-15D, 0D, 1D, 15D, 30D, 15D),
                    Block.box(-16D, 30D, 0D, 16D, 32D, 16D)
            )))
            // endregion
            // region: Bed Single
            .blockType(BlockType.BED_SINGLE, block -> block.shape(() -> ApexShapes.join(
                    Block.box(0D, 0D, 0D, 16D, 14D, 2D),
                    Block.box(0D, 0D, 30D, 16D, 14D, 32D),
                    Block.box(0D, 3D, 2D, 16D, 5D, 30D),
                    Block.box(1D, 5D, 2D, 15D, 8D, 30D)
            )))
            // endregion
            // region: Bed Double
            .blockType(BlockType.BED_DOUBLE, block -> block.shape(() -> ApexShapes.join(
                    Block.box(-16D, 3D, 2D, 16D, 5D, 30D),
                    Block.box(-14D, 5D, 2D, 14D, 8D, 30D),
                    Block.box(-16D, 3D, 0D, 16D, 5D, 2D),
                    Block.box(-16D, 0D, 0D, -14D, 8D, 2D),
                    Block.box(14D, 0D, 0D, 16D, 8D, 2D),
                    Block.box(-16D, 12D, 0D, -8D, 14D, 2D),
                    Block.box(8D, 12D, 0D, 16D, 14D, 2D),
                    Block.box(-10D, 12D, 0D, 10D, 16D, 2D),
                    Block.box(-15D, 5D, 0D, 15D, 12D, 2D),
                    Block.box(-15D, 5D, 30D, 15D, 12D, 32D),
                    Block.box(-16D, 3D, 30D, 16D, 5D, 32D),
                    Block.box(-16D, 0D, 30D, -14D, 8D, 32D),
                    Block.box(14D, 0D, 30D, 16D, 8D, 32D),
                    Block.box(-16D, 12D, 30D, -8D, 14D, 32D),
                    Block.box(8D, 12D, 30D, 16D, 14D, 32D),
                    Block.box(-10D, 12D, 30D, 10D, 16D, 32D)
            )))
            // endregion
            // region: Door Double
            .blockType(BlockType.DOOR_DOUBLE, block -> block.shape(() -> Block.box(0, 0, 0, 3, 32, 16)))
            // endregion
            // region: Door Single
            .blockType(BlockType.DOOR_SINGLE, block -> block.shape(() -> Block.box(0, 0, 0, 3, 32, 16)))
            // endregion
            // region: Desk Left
            .blockType(BlockType.DESK_LEFT, block -> block.shape(() -> ApexShapes.join(
                    Block.box(13D, 0D, 0D, 15D, 9D, 2D),
                    Block.box(13D, 7D, 1D, 15D, 13D, 3D),
                    Block.box(13D, 7D, 13D, 15D, 13D, 15D),
                    Block.box(-15D, 7D, 13D, -13D, 13D, 15D),
                    Block.box(-15D, 0D, 0D, -13D, 9D, 2D),
                    Block.box(-15D, 0D, 14D, -13D, 9D, 16D),
                    Block.box(13D, 0D, 14D, 15D, 9D, 16D),
                    Block.box(-16D, 13D, 0D, 16D, 16D, 16D),
                    Block.box(-15D, 7D, 1D, -13D, 13D, 3D),
                    Block.box(5D, 9D, 2D, 12D, 13D, 11D)
            )))
            // endregion
            // region: Desk Right
            .blockType(BlockType.DESK_RIGHT, block -> block.shape(() -> ApexShapes.join(
                    Block.box(13D, 0D, 0D, 15D, 9D, 2D),
                    Block.box(13D, 7D, 1D, 15D, 13D, 3D),
                    Block.box(13D, 7D, 13D, 15D, 13D, 15D),
                    Block.box(-15D, 7D, 13D, -13D, 13D, 15D),
                    Block.box(-15D, 0D, 0D, -13D, 9D, 2D),
                    Block.box(-15D, 0D, 14D, -13D, 9D, 16D),
                    Block.box(13D, 0D, 14D, 15D, 9D, 16D),
                    Block.box(-16D, 13D, 0D, 16D, 16D, 16D),
                    Block.box(-15D, 7D, 1D, -13D, 13D, 3D),
                    Block.box(-12D, 9D, 2D, -5D, 13D, 11D)
            )))
    // endregion
            // region: Painting Wide
            .blockType(BlockType.PAINTING_WIDE, block -> block.shape(() -> Block.box(-16D, 0D, 14D, 16D, 16D, 16D)))
            // endregion
            // region: Painting Small
            .blockType(BlockType.PAINTING_SMALL, block -> block.shape(() -> Block.box(0D, 0D, 14D, 16D, 16D, 16D)))
            // endregion
    );

    public NordicFurnitureSet(IEventBus modBus) {
        FURNITURE_SET.register(modBus);
    }
}
