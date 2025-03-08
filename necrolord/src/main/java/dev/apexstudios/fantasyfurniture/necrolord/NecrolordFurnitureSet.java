package dev.apexstudios.fantasyfurniture.necrolord;

import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.fantasyfurniture.necrolord.block.*;
import dev.apexstudios.fantasyfurniture.set.BlockTypes;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import dev.apexstudios.fantasyfurniture.set.function.BlockFactory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NecrolordFurnitureSet.ID)
public class NecrolordFurnitureSet {
    public static final String ID = "fantasyfurniture_necrolord";
    public static final Registree REGISTREE = new Registree(ID);

    public static final FurnitureSet FURNITURE_SET = FurnitureSet.createStoneLike(REGISTREE, "necrolord", $ -> $
            .with(BlockTypes.BED_DOUBLE.extend(BlockFactory.wrapping(NecrolordBedDoubleBlock::new)))
            .with(BlockTypes.BED_SINGLE.extend(BlockFactory.wrapping(NecrolordBedSingleBlock::new)))
            .with(BlockTypes.BENCH.extend(BlockFactory.wrapping(NecrolordBenchBlock::new)))
            .with(BlockTypes.BOOKSHELF.extend(BlockFactory.wrapping(NecrolordBookshelfBlock::new)))
            .with(BlockTypes.CHAIR.extend(BlockFactory.wrapping(NecrolordChairBlock::new)))
            .with(BlockTypes.CHANDELIER.extend(BlockFactory.wrapping(NecrolordChandelierBlock::new)))
            .with(BlockTypes.CHEST.extend(BlockFactory.wrapping(NecrolordChestBlock::new)))
            .with(BlockTypes.CUSHION.extend(BlockFactory.wrapping(NecrolordCushionBlock::new)))
            .with(BlockTypes.DESK_LEFT.extend((furnitureSet, properties) -> new NecrolordDeskBlock(properties, true)))
            .with(BlockTypes.DESK_RIGHT.extend((furnitureSet, properties) -> new NecrolordDeskBlock(properties, false)))
            .with(BlockTypes.DRAWER.extend(BlockFactory.wrapping(NecrolordDrawerBlock::new)))
            .with(BlockTypes.DRESSER.extend(BlockFactory.wrapping(NecrolordDresserBlock::new)))
            .with(BlockTypes.FLOOR_LIGHT.extend(BlockFactory.wrapping(NecrolordFloorLightBlock::new)))
            .with(BlockTypes.LOCKBOX.extend(BlockFactory.wrapping(NecrolordLockBoxBlock::new)))
            .with(BlockTypes.PAINTING_SMALL.extend(BlockFactory.wrapping(NecrolordPaintingSmallBlock::new)))
            .with(BlockTypes.PAINTING_WIDE.extend(BlockFactory.wrapping(NecrolordPaintingWideBlock::new)))
            .with(BlockTypes.SHELF.extend(BlockFactory.wrapping(NecrolordShelfBlock::new)))
            .with(BlockTypes.SOFA.extend(BlockFactory.wrapping(NecrolordSofaBlock::new)))
            .with(BlockTypes.STOOL.extend(BlockFactory.wrapping(NecrolordStoolBlock::new)))
            .with(BlockTypes.TABLE.extend(BlockFactory.wrapping(NecrolordTableBlock::new)))
            .with(BlockTypes.WALL_LIGHT.extend(BlockFactory.wrapping(NecrolordWallLightBlock::new)))
            .with(BlockTypes.WARDROBE.extend(BlockFactory.wrapping(NecrolordWardrobeBlock::new)))
    );

    public NecrolordFurnitureSet(IEventBus modBus) {
        FURNITURE_SET.register(modBus);
        REGISTREE.registerEvents(modBus);
    }
}
