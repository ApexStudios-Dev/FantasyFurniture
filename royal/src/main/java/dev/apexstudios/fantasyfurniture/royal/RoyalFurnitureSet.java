package dev.apexstudios.fantasyfurniture.royal;

import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalBedDoubleBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalBedSingleBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalBenchBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalBookshelfBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalCarpetBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalChairBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalChandelierBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalChestBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalCounterBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalCushionBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalDeskBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalDoorBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalDrawerBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalDresserBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalFloorLightBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalLockBoxBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalOvenBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalPaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalPaintingWideBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalShelfBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalSofaBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalStoolBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalTableBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalWallLightBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalWardrobeBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalWoolBlock;
import dev.apexstudios.fantasyfurniture.set.BlockTypeBuilder;
import dev.apexstudios.fantasyfurniture.set.BlockTypes;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import dev.apexstudios.fantasyfurniture.set.function.BlockFactory;
import dev.apexstudios.fantasyfurniture.set.function.ModelProviderListener;
import java.util.function.Supplier;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

@Mod(RoyalFurnitureSet.ID)
public class RoyalFurnitureSet {
    public static final String ID = "fantasyfurniture_royal";
    public static final Registree REGISTREE = new Registree(ID);
    public static final FurnitureSet FURNITURE_SET = FurnitureSet.createStoneLike(REGISTREE, "royal", $ -> $
            .remove(BlockTypes.FENCE_GATE)
            .with(BlockTypes.WOOL.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalWoolBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::woolModel))
            ))
            .with(BlockTypes.CARPET.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalCarpetBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::carpetModel))
            ))
            .with(BlockTypes.BED_DOUBLE.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalBedDoubleBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::bedDoubleModel))
            ))
            .with(BlockTypes.BED_SINGLE.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalBedSingleBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::bedSingleModel))
            ))
            .with(BlockTypes.BENCH.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalBenchBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::benchModel))
            ))
            .with(BlockTypes.BOOKSHELF.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalBookshelfBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::bookshelfModel))
            ))
            .with(BlockTypes.CHAIR.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalChairBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::chairModel))
            ))
            .with(BlockTypes.CHANDELIER.extend(BlockFactory.wrapping(RoyalChandelierBlock::new)))
            .with(BlockTypes.CHEST.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalChestBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::chestModel))
            ))
            .with(BlockTypes.COUNTER.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalCounterBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::counterModel))
            ))
            .with(BlockTypes.CUSHION.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalCushionBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::cushionModel))
            ))
            .with(BlockTypes.DESK_LEFT.copy($$ -> $$
                    .blockFactory((furnitureSet, properties) -> new RoyalDeskBlock(properties, true))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::deskModel))
            ))
            .with(BlockTypes.DESK_RIGHT.copy($$ -> $$
                    .blockFactory((furnitureSet, properties) -> new RoyalDeskBlock(properties, false))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::deskModel))
            ))
            .with(BlockTypes.DOOR_SINGLE.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalDoorBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::doorModel))
            ))
            .with(BlockTypes.DOOR_DOUBLE.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalDoorBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::doorModel))
            ))
            .with(BlockTypes.DRAWER.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalDrawerBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::drawerModel))
            ))
            .with(BlockTypes.DRESSER.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalDresserBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::dresserModel))
            ))
            .with(BlockTypes.FLOOR_LIGHT.extend(BlockFactory.wrapping(RoyalFloorLightBlock::new)))
            .with(BlockTypes.LOCKBOX.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalLockBoxBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::lockboxModel))
            ))
            .with(BlockTypes.OVEN.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalOvenBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::ovenModel))
            ))
            .with(BlockTypes.PAINTING_WIDE.extend(BlockFactory.wrapping(RoyalPaintingWideBlock::new)))
            .with(BlockTypes.PAINTING_SMALL.extend(BlockFactory.wrapping(RoyalPaintingSmallBlock::new)))
            .with(BlockTypes.SHELF.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalShelfBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::shelfModel))
            ))
            .with(BlockTypes.SOFA.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalSofaBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::sofaModel))
            ))
            .with(BlockTypes.STOOL.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalStoolBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::stoolModel))
            ))
            .with(BlockTypes.TABLE.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalTableBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::tableModel))
            ))
            .with(BlockTypes.WALL_LIGHT.extend(BlockFactory.wrapping(RoyalWallLightBlock::new)))
            .with(BlockTypes.WARDROBE.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalWardrobeBlock::new))
                    .builder($$$ -> dyedSetup($$$, () -> RoyalFurnitureSetClientSetup::wardrobeModel))
            ))
    );

    public RoyalFurnitureSet(IEventBus modBus) {
        FURNITURE_SET.register(modBus);
        REGISTREE.registerEvents(modBus);
    }

    private static <TBlock extends Block, TItem extends Item> BlockTypeBuilder.WithItem<TBlock, TItem> dyedSetup(BlockTypeBuilder.WithItem<TBlock, TItem> builder, Supplier<ModelProviderListener<TBlock>> model) {
        return builder
                .model(model)
                .blockTags(Tags.Blocks.DYED)
                .itemTags(Tags.Items.DYED)
                .itemProperties(properties -> properties.component(DataComponents.BASE_COLOR, DyeColor.WHITE));
    }
}
