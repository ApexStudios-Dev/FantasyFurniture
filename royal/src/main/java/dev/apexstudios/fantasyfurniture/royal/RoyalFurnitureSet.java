package dev.apexstudios.fantasyfurniture.royal;

import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalBedDoubleBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalBedSingleBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalBenchBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalBookshelfBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalChairBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalChandelierBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalChestBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalCounterBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalCushionBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalDeskBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalDrawerBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalFloorLightBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalLockBoxBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalPaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalPaintingWideBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalShelfBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalSofaBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalStoolBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalTableBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalWallLightBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalWardrobeBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalWoolBlock;
import dev.apexstudios.fantasyfurniture.set.BlockTypes;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import dev.apexstudios.fantasyfurniture.set.function.BlockFactory;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.DyeColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(RoyalFurnitureSet.ID)
public class RoyalFurnitureSet {
    public static final String ID = "fantasyfurniture_royal";
    public static final Registree REGISTREE = new Registree(ID);
    public static final FurnitureSet FURNITURE_SET = FurnitureSet.createStoneLike(REGISTREE, "royal", $ -> $
            .remove(BlockTypes.FENCE_GATE)
            .with(BlockTypes.WOOL.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalWoolBlock::new))
                    .builder($$$ -> $$$
                            .model(() -> RoyalFurnitureSetClientSetup::woolModel)
                            .itemProperties(properties -> properties.component(DataComponents.BASE_COLOR, DyeColor.WHITE))
                    )
            ))
            .with(BlockTypes.BED_DOUBLE.extend(BlockFactory.wrapping(RoyalBedDoubleBlock::new)))
            .with(BlockTypes.BED_SINGLE.extend(BlockFactory.wrapping(RoyalBedSingleBlock::new)))
            .with(BlockTypes.BENCH.extend(BlockFactory.wrapping(RoyalBenchBlock::new)))
            .with(BlockTypes.BOOKSHELF.extend(BlockFactory.wrapping(RoyalBookshelfBlock::new)))
            .with(BlockTypes.CHAIR.extend(BlockFactory.wrapping(RoyalChairBlock::new)))
            .with(BlockTypes.CHANDELIER.extend(BlockFactory.wrapping(RoyalChandelierBlock::new)))
            .with(BlockTypes.CHEST.extend(BlockFactory.wrapping(RoyalChestBlock::new)))
            .with(BlockTypes.COUNTER.extend(BlockFactory.wrapping(RoyalCounterBlock::new)))
            .with(BlockTypes.CUSHION.extend(BlockFactory.wrapping(RoyalCushionBlock::new)))
            .with(BlockTypes.DESK_LEFT.extend((furnitureSet, properties) -> new RoyalDeskBlock(properties, true)))
            .with(BlockTypes.DESK_RIGHT.extend((furnitureSet, properties) -> new RoyalDeskBlock(properties, false)))
            .with(BlockTypes.DRAWER.extend(BlockFactory.wrapping(RoyalDrawerBlock::new)))
            .with(BlockTypes.FLOOR_LIGHT.extend(BlockFactory.wrapping(RoyalFloorLightBlock::new)))
            .with(BlockTypes.LOCKBOX.extend(BlockFactory.wrapping(RoyalLockBoxBlock::new)))
            .with(BlockTypes.PAINTING_WIDE.extend(BlockFactory.wrapping(RoyalPaintingWideBlock::new)))
            .with(BlockTypes.PAINTING_SMALL.extend(BlockFactory.wrapping(RoyalPaintingSmallBlock::new)))
            .with(BlockTypes.SHELF.extend(BlockFactory.wrapping(RoyalShelfBlock::new)))
            .with(BlockTypes.SOFA.extend(BlockFactory.wrapping(RoyalSofaBlock::new)))
            .with(BlockTypes.STOOL.extend(BlockFactory.wrapping(RoyalStoolBlock::new)))
            .with(BlockTypes.TABLE.extend(BlockFactory.wrapping(RoyalTableBlock::new)))
            .with(BlockTypes.WALL_LIGHT.extend(BlockFactory.wrapping(RoyalWallLightBlock::new)))
            .with(BlockTypes.WARDROBE.extend(BlockFactory.wrapping(RoyalWardrobeBlock::new)))
    );

    public RoyalFurnitureSet(IEventBus modBus) {
        FURNITURE_SET.register(modBus);
        REGISTREE.registerEvents(modBus);
    }
}
