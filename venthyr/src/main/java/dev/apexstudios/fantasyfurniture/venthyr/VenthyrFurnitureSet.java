package dev.apexstudios.fantasyfurniture.venthyr;

import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.fantasyfurniture.block.TableBlock;
import dev.apexstudios.fantasyfurniture.set.BlockType;
import dev.apexstudios.fantasyfurniture.set.BlockTypes;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import dev.apexstudios.fantasyfurniture.set.function.BlockFactory;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrBedDoubleBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrBedSingleBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrBenchBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrChairBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrChandelierBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrChestBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrCushionBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrDeskBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrDresserBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrFloorLightBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrLockBoxBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrShelfBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrSofaBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrStoolBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrTableBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrWallLightBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(VenthyrFurnitureSet.ID)
public class VenthyrFurnitureSet {
    public static final String ID = "fantasyfurniture_venthyr";
    public static final Registree REGISTREE = new Registree(ID);

    public static final BlockType<TableBlock> TABLE_CLOTH = BlockTypes.TABLE.copy(copier -> copier
            .registryName("table_cloth")
            .blockFactory(BlockFactory.wrapping(VenthyrTableBlock::new))
            .builder(builder -> builder.translation("Table Cloth"))
    );

    public static final FurnitureSet FURNITURE_SET = FurnitureSet.create(REGISTREE, "venthyr", builder -> builder
            .withDefault()
            .with(TABLE_CLOTH)
            .with(BlockTypes.DRESSER.extend(BlockFactory.wrapping(VenthyrDresserBlock::new)))
            .with(BlockTypes.CUSHION.extend(BlockFactory.wrapping(VenthyrCushionBlock::new)))
            .with(BlockTypes.STOOL.extend(BlockFactory.wrapping(VenthyrStoolBlock::new)))
            .with(BlockTypes.LOCKBOX.extend(BlockFactory.wrapping(VenthyrLockBoxBlock::new)))
            .with(BlockTypes.CHAIR.extend(BlockFactory.wrapping(VenthyrChairBlock::new)))
            .with(BlockTypes.BED_SINGLE.extend(BlockFactory.wrapping(VenthyrBedSingleBlock::new)))
            .with(BlockTypes.BED_DOUBLE.extend(BlockFactory.wrapping(VenthyrBedDoubleBlock::new)))
            .with(BlockTypes.DESK_LEFT.extend((furnitureSet, properties) -> new VenthyrDeskBlock(properties, true)))
            .with(BlockTypes.DESK_RIGHT.extend((furnitureSet, properties) -> new VenthyrDeskBlock(properties, false)))
            .with(BlockTypes.CHEST.extend(BlockFactory.wrapping(VenthyrChestBlock::new)))
            .with(BlockTypes.FLOOR_LIGHT.extend(BlockFactory.wrapping(VenthyrFloorLightBlock::new)))
            .with(BlockTypes.CHANDELIER.extend(BlockFactory.wrapping(VenthyrChandelierBlock::new)))
            .with(BlockTypes.SHELF.extend(BlockFactory.wrapping(VenthyrShelfBlock::new)))
            .with(BlockTypes.SOFA.extend(BlockFactory.wrapping(VenthyrSofaBlock::new)))
            .with(BlockTypes.WALL_LIGHT.extend(BlockFactory.wrapping(VenthyrWallLightBlock::new)))
            .with(BlockTypes.BENCH.extend(BlockFactory.wrapping(VenthyrBenchBlock::new)))
            .with(BlockTypes.TABLE.extend(BlockFactory.wrapping(VenthyrTableBlock::new)))
    );

    public VenthyrFurnitureSet(IEventBus modBus) {
        FURNITURE_SET.register(modBus);
        REGISTREE.registerEvents(modBus);
    }
}
