package dev.apexstudios.fantasyfurniture.nordic.common;

import dev.apexstudios.apexcore.api.util.WoodTypeBuilder;
import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.ctm.CtmPacks;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicBedDoubleBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicBedSingleBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicBenchBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicBookshelfBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicChairBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicChandelierBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicChestBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicCounterBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicCushionBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicDeskLeftBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicDeskRightBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicDoorBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicDrawerBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicDresserBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicFloorLightBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicLockBoxBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicOvenBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicPaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicPaintingWideBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicShelfBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicSofaBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicStoolBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicTableBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicWallLightBlock;
import dev.apexstudios.fantasyfurniture.nordic.common.block.NordicWardrobeBlock;
import dev.apexstudios.registree.Registree;
import java.util.function.Supplier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
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
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;

@Mod(NordicFurnitureSet.ID)
public class NordicFurnitureSet {
    public static final String ID = "fantasyfurniture_nordic";
    public static final Registree REGISTREE = Registree.create(ID);

    public static final Supplier<WoodType> WOOD_TYPE = WoodTypeBuilder.builder()
            .copy(WoodType.OAK)
            .build(ID + ":nordic");

    public static final Supplier<BlockSetType> BLOCK_SET_TYPE = () -> WOOD_TYPE.get().setType();

    public static final DeferredBlock<Block> PLANKS = FurnitureUtil.planks(REGISTREE, Block::new).register();
    public static final DeferredBlock<Block> WOOL = FurnitureUtil.wool(REGISTREE, Block::new).register();
    public static final DeferredBlock<CarpetBlock> CARPET = FurnitureUtil.carpet(REGISTREE, CarpetBlock::new).register();
    public static final DeferredBlock<NordicDresserBlock> DRESSER = FurnitureUtil.dresser(REGISTREE, NordicDresserBlock::new).register();
    public static final DeferredBlock<NordicStoolBlock> STOOL = FurnitureUtil.stool(REGISTREE, NordicStoolBlock::new).register();
    public static final DeferredBlock<NordicCushionBlock> CUSION = FurnitureUtil.cushion(REGISTREE, NordicCushionBlock::new).register();
    public static final DeferredBlock<NordicLockBoxBlock> LOCKBOX = FurnitureUtil.lockbox(REGISTREE, NordicLockBoxBlock::new).register();
    public static final DeferredBlock<NordicDrawerBlock> DRAWER = FurnitureUtil.drawer(REGISTREE, NordicDrawerBlock::new).register();
    public static final DeferredBlock<NordicChairBlock> CHAIR = FurnitureUtil.chair(REGISTREE, NordicChairBlock::new).register();
    public static final DeferredBlock<NordicBookshelfBlock> BOOKSHELF = FurnitureUtil.bookshelf(REGISTREE, NordicBookshelfBlock::new).register();
    public static final DeferredBlock<NordicBedSingleBlock> BED_SINGLE = FurnitureUtil.bedSingle(REGISTREE, NordicBedSingleBlock::new).register();
    public static final DeferredBlock<NordicBedDoubleBlock> BED_DOUBLE = FurnitureUtil.bedDouble(REGISTREE, NordicBedDoubleBlock::new).register();
    public static final DeferredBlock<NordicDoorBlock> DOOR_SINGLE = FurnitureUtil.doorSingle(REGISTREE, BLOCK_SET_TYPE, NordicDoorBlock::new).register();
    public static final DeferredBlock<NordicDoorBlock> DOOR_DOUBLE = FurnitureUtil.doorDouble(REGISTREE, BLOCK_SET_TYPE, NordicDoorBlock::new).register();
    public static final DeferredBlock<NordicDeskLeftBlock> DESK_LEFT = FurnitureUtil.desk(REGISTREE, true, NordicDeskLeftBlock::new).register();
    public static final DeferredBlock<NordicDeskRightBlock> DESK_RIGHT = FurnitureUtil.desk(REGISTREE, false, NordicDeskRightBlock::new).register();
    public static final DeferredBlock<NordicPaintingWideBlock> PAINTING_WIDE = FurnitureUtil.paintingWide(REGISTREE, NordicPaintingWideBlock::new).register();
    public static final DeferredBlock<NordicPaintingSmallBlock> PAINTING_SMALL = FurnitureUtil.paintingSmall(REGISTREE, NordicPaintingSmallBlock::new).register();
    public static final DeferredBlock<NordicOvenBlock> OVEN = FurnitureUtil.oven(REGISTREE, NordicOvenBlock::new).register();
    public static final DeferredBlock<NordicChestBlock> CHEST = FurnitureUtil.chest(REGISTREE, NordicChestBlock::new).register();
    public static final DeferredBlock<NordicFloorLightBlock> FLOOR_LIGHT = FurnitureUtil.floorLight(REGISTREE, NordicFloorLightBlock::new).register();
    public static final DeferredBlock<NordicChandelierBlock> CHANDELIER = FurnitureUtil.chandelier(REGISTREE, NordicChandelierBlock::new).register();
    public static final DeferredBlock<NordicShelfBlock> SHELF = FurnitureUtil.shelf(REGISTREE, NordicShelfBlock::new).register();
    public static final DeferredBlock<NordicSofaBlock> SOFA = FurnitureUtil.sofa(REGISTREE, NordicSofaBlock::new).register();
    public static final DeferredBlock<NordicCounterBlock> COUNTER = FurnitureUtil.counter(REGISTREE, NordicCounterBlock::new).register();
    public static final DeferredBlock<NordicWallLightBlock> WALL_LIGHT = FurnitureUtil.wallLight(REGISTREE, NordicWallLightBlock::new).register();
    public static final DeferredBlock<NordicBenchBlock> BENCH = FurnitureUtil.bench(REGISTREE, NordicBenchBlock::new).register();
    public static final DeferredBlock<NordicWardrobeBlock> WARDROBE = FurnitureUtil.wardrobe(REGISTREE, NordicWardrobeBlock::new).register();
    public static final DeferredBlock<NordicTableBlock> TABLE = FurnitureUtil.table(REGISTREE, NordicTableBlock::new).register();
    public static final DeferredBlock<StairBlock> STAIRS = FurnitureUtil.stairs(REGISTREE, PLANKS).register();
    public static final DeferredBlock<SlabBlock> SLAB = FurnitureUtil.slab(REGISTREE).register();
    public static final DeferredBlock<FenceBlock> FENCE = FurnitureUtil.fence(REGISTREE).register();
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE = FurnitureUtil.fenceGate(REGISTREE, WOOD_TYPE).register();
    public static final DeferredBlock<TrapDoorBlock> TRAPDOOR = FurnitureUtil.trapdoor(REGISTREE, BLOCK_SET_TYPE).register();
    public static final DeferredBlock<PressurePlateBlock> PRESSURE_PLATE = FurnitureUtil.pressurePlate(REGISTREE, BLOCK_SET_TYPE).register();
    public static final FurnitureUtil.SignPair<CeilingHangingSignBlock, WallHangingSignBlock> HANGING_SIGN = FurnitureUtil.hangingSign(REGISTREE, WOOD_TYPE);
    public static final FurnitureUtil.SignPair<StandingSignBlock, WallSignBlock> SIGN = FurnitureUtil.sign(REGISTREE, WOOD_TYPE);

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, () -> new ItemStack(BED_SINGLE.value()));

    public NordicFurnitureSet(IEventBus modBus) {
        FantasyFurniture.FURNITURE_MODS.add(ID);
        CtmPacks.register(REGISTREE);
        REGISTREE.register(modBus);
    }
}
