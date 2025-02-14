package dev.apexstudios.fantasyfurniture.nordic;

import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicBedDoubleBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicBedSingleBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicBenchBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicBookshelfBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicChairBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicChandelierBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicChestBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicCounterBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicCushionBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicDeskBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicDoorBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicDrawerBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicDresserBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicFloorLightBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicLockBoxBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicPaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicPaintingWideBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicShelfBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicSofaBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicStoolBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicTableBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicWallLightBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicWardrobeBlock;
import dev.apexstudios.fantasyfurniture.set.BlockTypes;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import dev.apexstudios.fantasyfurniture.set.function.BlockFactory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NordicFurnitureSet.ID)
public class NordicFurnitureSet {
    public static final String ID = "fantasyfurniture_nordic";
    public static final Registree REGISTREE = new Registree(ID);

    public static final FurnitureSet FURNITURE_SET = FurnitureSet.create(REGISTREE, "nordic", builder -> builder
            .withDefault()
            .with(BlockTypes.DRESSER.extend(BlockFactory.wrapping(NordicDresserBlock::new)))
            .with(BlockTypes.CUSHION.extend(BlockFactory.wrapping(NordicCushionBlock::new)))
            .with(BlockTypes.STOOL.extend(BlockFactory.wrapping(NordicStoolBlock::new)))
            .with(BlockTypes.LOCKBOX.extend(BlockFactory.wrapping(NordicLockBoxBlock::new)))
            .with(BlockTypes.DRAWER.extend(BlockFactory.wrapping(NordicDrawerBlock::new)))
            .with(BlockTypes.CHAIR.extend(BlockFactory.wrapping(NordicChairBlock::new)))
            .with(BlockTypes.BOOKSHELF.extend(BlockFactory.wrapping(NordicBookshelfBlock::new)))
            .with(BlockTypes.BED_SINGLE.extend(BlockFactory.wrapping(NordicBedSingleBlock::new)))
            .with(BlockTypes.BED_DOUBLE.extend(BlockFactory.wrapping(NordicBedDoubleBlock::new)))
            .with(BlockTypes.DOOR_DOUBLE.extend(BlockFactory.wrapping(NordicDoorBlock::new)))
            .with(BlockTypes.DOOR_SINGLE.extend(BlockFactory.wrapping(NordicDoorBlock::new)))
            .with(BlockTypes.DESK_LEFT.extend((furnitureSet, properties) -> new NordicDeskBlock(properties, true)))
            .with(BlockTypes.DESK_RIGHT.extend((furnitureSet, properties) -> new NordicDeskBlock(properties, false)))
            .with(BlockTypes.PAINTING_WIDE.extend(BlockFactory.wrapping(NordicPaintingWideBlock::new)))
            .with(BlockTypes.PAINTING_SMALL.extend(BlockFactory.wrapping(NordicPaintingSmallBlock::new)))
            .with(BlockTypes.CHEST.extend(BlockFactory.wrapping(NordicChestBlock::new)))
            .with(BlockTypes.FLOOR_LIGHT.extend(BlockFactory.wrapping(NordicFloorLightBlock::new)))
            .with(BlockTypes.CHANDELIER.extend(BlockFactory.wrapping(NordicChandelierBlock::new)))
            .with(BlockTypes.SHELF.extend(BlockFactory.wrapping(NordicShelfBlock::new)))
            .with(BlockTypes.SOFA.extend(BlockFactory.wrapping(NordicSofaBlock::new)))
            .with(BlockTypes.COUNTER.extend(BlockFactory.wrapping(NordicCounterBlock::new)))
            .with(BlockTypes.WALL_LIGHT.extend(BlockFactory.wrapping(NordicWallLightBlock::new)))
            .with(BlockTypes.BENCH.extend(BlockFactory.wrapping(NordicBenchBlock::new)))
            .with(BlockTypes.WARDROBE.extend(BlockFactory.wrapping(NordicWardrobeBlock::new)))
            .with(BlockTypes.TABLE.extend(BlockFactory.wrapping(NordicTableBlock::new)))
    );

    public NordicFurnitureSet(IEventBus modBus) {
        FURNITURE_SET.register(modBus);
        REGISTREE.registerEvents(modBus);
    }
}
