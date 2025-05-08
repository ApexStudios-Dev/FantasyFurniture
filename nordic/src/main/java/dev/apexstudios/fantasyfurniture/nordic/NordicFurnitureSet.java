package dev.apexstudios.fantasyfurniture.nordic;

import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlock;
import dev.apexstudios.apexcore.lib.util.WoodTypeBuilder;
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
import dev.apexstudios.fantasyfurniture.nordic.block.NordicOvenBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicPaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicPaintingWideBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicShelfBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicSofaBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicStoolBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicTableBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicWallLightBlock;
import dev.apexstudios.fantasyfurniture.nordic.block.NordicWardrobeBlock;
import dev.apexstudios.fantasyfurniture.util.FurnitureUtil;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NordicFurnitureSet.ID)
public class NordicFurnitureSet {
    public static final String ID = "fantasyfurniture_nordic";
    public static final Registree REGISTREE = new Registree(ID);

    public static final WoodType WOOD_TYPE = WoodTypeBuilder.builder()
            .copy(WoodType.OAK)
            .build(ID + ":wood_type");

    public static final DeferredBlock<Block> PLANKS = FurnitureUtil.planks(REGISTREE, Block::new);
    public static final DeferredBlock<Block> WOOL = FurnitureUtil.wool(REGISTREE, Block::new);
    public static final DeferredBlock<CarpetBlock> CARPET = FurnitureUtil.carpet(REGISTREE, CarpetBlock::new);
    public static final DeferredBlock<NordicDresserBlock> DRESSER = FurnitureUtil.dresser(REGISTREE, NordicDresserBlock::new);
    public static final DeferredBlock<NordicStoolBlock> STOOL = FurnitureUtil.stool(REGISTREE, NordicStoolBlock::new);
    public static final DeferredBlock<NordicCushionBlock> CUSION = FurnitureUtil.cushion(REGISTREE, NordicCushionBlock::new);
    public static final DeferredBlock<NordicLockBoxBlock> LOCKBOX = FurnitureUtil.lockbox(REGISTREE, NordicLockBoxBlock::new);
    public static final DeferredBlock<NordicDrawerBlock> DRAWER = FurnitureUtil.drawer(REGISTREE, NordicDrawerBlock::new);
    public static final DeferredBlock<NordicChairBlock> CHAIR = FurnitureUtil.chair(REGISTREE, NordicChairBlock::new);
    public static final DeferredBlock<NordicBookshelfBlock> BOOKSHELF = FurnitureUtil.bookshelf(REGISTREE, NordicBookshelfBlock::new);
    public static final DeferredBlock<NordicBedSingleBlock> BED_SINGLE = FurnitureUtil.bedSingle(REGISTREE, NordicBedSingleBlock::new);
    public static final DeferredBlock<NordicBedDoubleBlock> BED_DOUBLE = FurnitureUtil.bedDouble(REGISTREE, NordicBedDoubleBlock::new);
    public static final DeferredBlock<NordicDoorBlock> DOOR_SINGLE = FurnitureUtil.doorSingle(REGISTREE, WOOD_TYPE.setType(), NordicDoorBlock::new);
    public static final DeferredBlock<NordicDoorBlock> DOOR_DOUBLE = FurnitureUtil.doorDouble(REGISTREE, WOOD_TYPE.setType(), NordicDoorBlock::new);
    public static final DeferredBlock<NordicDeskBlock> DESK_LEFT = FurnitureUtil.desk(REGISTREE, true, NordicDeskBlock::new);
    public static final DeferredBlock<NordicDeskBlock> DESK_RIGHT = FurnitureUtil.desk(REGISTREE, false, NordicDeskBlock::new);
    public static final DeferredBlock<NordicPaintingWideBlock> PAINTING_WIDE = FurnitureUtil.paintingWide(REGISTREE, NordicPaintingWideBlock::new);
    public static final DeferredBlock<NordicPaintingSmallBlock> PAINTING_SMALL = FurnitureUtil.paintingSmall(REGISTREE, NordicPaintingSmallBlock::new);
    public static final DeferredBlock<NordicOvenBlock> OVEN = FurnitureUtil.oven(REGISTREE, NordicOvenBlock::new);
    public static final DeferredBlock<NordicChestBlock> CHEST = FurnitureUtil.chest(REGISTREE, NordicChestBlock::new);
    public static final DeferredBlock<NordicFloorLightBlock> FLOOR_LIGHT = FurnitureUtil.floorLight(REGISTREE, NordicFloorLightBlock::new);
    public static final DeferredBlock<NordicChandelierBlock> CHANDELIER = FurnitureUtil.chandelier(REGISTREE, NordicChandelierBlock::new);
    public static final DeferredBlock<NordicShelfBlock> SHELF = FurnitureUtil.shelf(REGISTREE, NordicShelfBlock::new);
    public static final DeferredBlock<NordicSofaBlock> SOFA = FurnitureUtil.sofa(REGISTREE, NordicSofaBlock::new);
    public static final DeferredBlock<NordicCounterBlock> COUNTER = FurnitureUtil.counter(REGISTREE, NordicCounterBlock::new);
    public static final DeferredBlock<NordicWallLightBlock> WALL_LIGHT = FurnitureUtil.wallLight(REGISTREE, NordicWallLightBlock::new);
    public static final DeferredBlock<NordicBenchBlock> BENCH = FurnitureUtil.bench(REGISTREE, NordicBenchBlock::new);
    public static final DeferredBlock<NordicWardrobeBlock> WARDROBE = FurnitureUtil.wardrobe(REGISTREE, NordicWardrobeBlock::new);
    public static final DeferredBlock<NordicTableBlock> TABLE = FurnitureUtil.table(REGISTREE, NordicTableBlock::new);
    public static final DeferredBlock<StairBlock> STAIRS = FurnitureUtil.stairs(REGISTREE, PLANKS);
    public static final DeferredBlock<SlabBlock> SLAB = FurnitureUtil.slab(REGISTREE);
    public static final DeferredBlock<FenceBlock> FENCE = FurnitureUtil.fence(REGISTREE);
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE = FurnitureUtil.fenceGate(REGISTREE, WOOD_TYPE);
    public static final DeferredBlock<TrapDoorBlock> TRAPDOOR = FurnitureUtil.trapdoor(REGISTREE, WOOD_TYPE.setType());
    public static final DeferredBlock<PressurePlateBlock> PRESSURE_PLATE = FurnitureUtil.pressurePlate(REGISTREE, WOOD_TYPE.setType());
    public static final FurnitureUtil.SignPair<CeilingHangingSignBlock, WallHangingSignBlock> HANGING_SIGN = FurnitureUtil.hangingSign(REGISTREE, WOOD_TYPE);
    public static final FurnitureUtil.SignPair<StandingSignBlock, WallSignBlock> SIGN = FurnitureUtil.sign(REGISTREE, WOOD_TYPE);

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, BED_SINGLE);

    public NordicFurnitureSet(IEventBus modBus) {
        FurnitureUtil.registerEvents(modBus, REGISTREE, WOOD_TYPE);
    }
}
