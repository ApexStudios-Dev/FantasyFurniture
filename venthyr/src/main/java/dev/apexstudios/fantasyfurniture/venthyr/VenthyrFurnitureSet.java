package dev.apexstudios.fantasyfurniture.venthyr;

import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(VenthyrFurnitureSet.ID)
public class VenthyrFurnitureSet {
    public static final String ID = "fantasyfurniture_venthyr";
    public static final Registree REGISTREE = new Registree(ID);

    public static final FurnitureSet FURNITURE_SET = FurnitureSet.create(REGISTREE, "venthyr", builder -> builder
            .withDefault()
            // .with(BlockTypes.DRESSER.extend(BlockFactory.wrapping(VenthyrDresserBlock::new)))
            // .with(BlockTypes.CUSHION.extend(BlockFactory.wrapping(VenthyrCushionBlock::new)))
            // .with(BlockTypes.STOOL.extend(BlockFactory.wrapping(VenthyrStoolBlock::new)))
            // .with(BlockTypes.LOCKBOX.extend(BlockFactory.wrapping(VenthyrLockBoxBlock::new)))
            // .with(BlockTypes.DRAWER.extend(BlockFactory.wrapping(VenthyrDrawerBlock::new)))
            // .with(BlockTypes.CHAIR.extend(BlockFactory.wrapping(VenthyrChairBlock::new)))
            // .with(BlockTypes.BOOKSHELF.extend(BlockFactory.wrapping(VenthyrBookshelfBlock::new)))
            // .with(BlockTypes.BED_SINGLE.extend(BlockFactory.wrapping(VenthyrBedSingleBlock::new)))
            // .with(BlockTypes.BED_DOUBLE.extend(BlockFactory.wrapping(VenthyrBedDoubleBlock::new)))
            // .with(BlockTypes.DOOR_DOUBLE.extend(BlockFactory.wrapping(VenthyrDoorBlock::new)))
            // .with(BlockTypes.DOOR_SINGLE.extend(BlockFactory.wrapping(VenthyrDoorBlock::new)))
            // .with(BlockTypes.DESK_LEFT.extend((furnitureSet, properties) -> new VenthyrDeskBlock(properties, true)))
            // .with(BlockTypes.DESK_RIGHT.extend((furnitureSet, properties) -> new VenthyrDeskBlock(properties, false)))
            // .with(BlockTypes.PAINTING_WIDE.extend(BlockFactory.wrapping(VenthyrPaintingWideBlock::new)))
            // .with(BlockTypes.PAINTING_SMALL.extend(BlockFactory.wrapping(VenthyrPaintingSmallBlock::new)))
            // .with(BlockTypes.CHEST.extend(BlockFactory.wrapping(VenthyrChestBlock::new)))
            // .with(BlockTypes.FLOOR_LIGHT.extend(BlockFactory.wrapping(VenthyrFloorLightBlock::new)))
            // .with(BlockTypes.CHANDELIER.extend(BlockFactory.wrapping(VenthyrChandelierBlock::new)))
            // .with(BlockTypes.SHELF.extend(BlockFactory.wrapping(VenthyrShelfBlock::new)))
            // .with(BlockTypes.SOFA.extend(BlockFactory.wrapping(VenthyrSofaBlock::new)))
            // .with(BlockTypes.COUNTER.extend(BlockFactory.wrapping(VenthyrCounterBlock::new)))
            // .with(BlockTypes.WALL_LIGHT.extend(BlockFactory.wrapping(VenthyrWallLightBlock::new)))
            // .with(BlockTypes.BENCH.extend(BlockFactory.wrapping(VenthyrBenchBlock::new)))
            // .with(BlockTypes.WARDROBE.extend(BlockFactory.wrapping(VenthyrWardrobeBlock::new)))
            // .with(BlockTypes.TABLE.extend(BlockFactory.wrapping(VenthyrTableBlock::new)))
    );

    public VenthyrFurnitureSet(IEventBus modBus) {
        FURNITURE_SET.register(modBus);
        REGISTREE.registerEvents(modBus);
    }
}
