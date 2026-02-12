package dev.apexstudios.fantasyfurniture.royal.common;

import dev.apexstudios.apexcore.api.util.WoodTypeBuilder;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalBedDoubleBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalBedSingleBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalBenchBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalBookshelfBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalCarpetBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalChairBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalChandelierBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalChestBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalCounterBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalCushionBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalDeskLeftBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalDeskRightBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalDoorBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalDrawerBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalDresserBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalFloorLightBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalLockBoxBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalOvenBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalPaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalPaintingWideBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalShelfBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalSofaBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalStoolBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalTableBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalWallLightBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalWardrobeBlock;
import dev.apexstudios.fantasyfurniture.royal.common.block.RoyalWoolBlock;
import dev.apexstudios.registree.Registree;
import dev.apexstudios.registree.registrar.BlockRegistrar;
import java.util.function.Supplier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
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

@Mod(RoyalFurnitureSet.ID)
public class RoyalFurnitureSet {
    public static final String ID = "fantasyfurniture_royal";
    public static final Registree REGISTREE = Registree.create(ID);
    public static final BlockRegistrar BLOCKS = REGISTREE.blocks();

    public static final Supplier<WoodType> WOOD_TYPE = WoodTypeBuilder.builder()
            .copy(WoodType.OAK)
            .blockSetType(blockSet -> blockSet
                    .copy(BlockSetType.STONE)
            )
            .build(ID + ":royal");

    public static final Supplier<BlockSetType> BLOCK_SET_TYPE = () -> WOOD_TYPE.get().setType();

    public static final DeferredBlock<Block> BRICKS = FurnitureUtil.bricks(BLOCKS, Block::new).register();
    public static final DeferredBlock<RoyalWoolBlock> WOOL = FurnitureUtil.wool(BLOCKS, RoyalWoolBlock::new).register();
    public static final DeferredBlock<RoyalCarpetBlock> CARPET = FurnitureUtil.carpet(BLOCKS, RoyalCarpetBlock::new).register();
    public static final DeferredBlock<RoyalDresserBlock> DRESSER = FurnitureUtil.dresser(BLOCKS, RoyalDresserBlock::new).register();
    public static final DeferredBlock<RoyalStoolBlock> STOOL = FurnitureUtil.stool(BLOCKS, RoyalStoolBlock::new).register();
    public static final DeferredBlock<RoyalCushionBlock> CUSION = FurnitureUtil.cushion(BLOCKS, RoyalCushionBlock::new).register();
    public static final DeferredBlock<RoyalLockBoxBlock> LOCKBOX = FurnitureUtil.lockbox(BLOCKS, RoyalLockBoxBlock::new).register();
    public static final DeferredBlock<RoyalDrawerBlock> DRAWER = FurnitureUtil.drawer(BLOCKS, RoyalDrawerBlock::new).register();
    public static final DeferredBlock<RoyalChairBlock> CHAIR = FurnitureUtil.chair(BLOCKS, RoyalChairBlock::new).register();
    public static final DeferredBlock<RoyalBookshelfBlock> BOOKSHELF = FurnitureUtil.bookshelf(BLOCKS, RoyalBookshelfBlock::new).register();
    public static final DeferredBlock<RoyalBedSingleBlock> BED_SINGLE = FurnitureUtil.bedSingle(BLOCKS, RoyalBedSingleBlock::new).register();
    public static final DeferredBlock<RoyalBedDoubleBlock> BED_DOUBLE = FurnitureUtil.bedDouble(BLOCKS, RoyalBedDoubleBlock::new).register();
    public static final DeferredBlock<RoyalDoorBlock> DOOR_SINGLE = FurnitureUtil.doorSingle(BLOCKS, BLOCK_SET_TYPE, RoyalDoorBlock::new).register();
    public static final DeferredBlock<RoyalDoorBlock> DOOR_DOUBLE = FurnitureUtil.doorDouble(BLOCKS, BLOCK_SET_TYPE, RoyalDoorBlock::new).register();
    public static final DeferredBlock<RoyalDeskLeftBlock> DESK_LEFT = FurnitureUtil.desk(BLOCKS, true, RoyalDeskLeftBlock::new).register();
    public static final DeferredBlock<RoyalDeskRightBlock> DESK_RIGHT = FurnitureUtil.desk(BLOCKS, false, RoyalDeskRightBlock::new).register();
    public static final DeferredBlock<RoyalPaintingWideBlock> PAINTING_WIDE = FurnitureUtil.paintingWide(BLOCKS, RoyalPaintingWideBlock::new).register();
    public static final DeferredBlock<RoyalPaintingSmallBlock> PAINTING_SMALL = FurnitureUtil.paintingSmall(BLOCKS, RoyalPaintingSmallBlock::new).register();
    public static final DeferredBlock<RoyalOvenBlock> OVEN = FurnitureUtil.oven(BLOCKS, RoyalOvenBlock::new).register();
    public static final DeferredBlock<RoyalChestBlock> CHEST = FurnitureUtil.chest(BLOCKS, RoyalChestBlock::new).register();
    public static final DeferredBlock<RoyalFloorLightBlock> FLOOR_LIGHT = FurnitureUtil.floorLight(BLOCKS, RoyalFloorLightBlock::new).register();
    public static final DeferredBlock<RoyalChandelierBlock> CHANDELIER = FurnitureUtil.chandelier(BLOCKS, RoyalChandelierBlock::new).register();
    public static final DeferredBlock<RoyalShelfBlock> SHELF = FurnitureUtil.shelf(BLOCKS, RoyalShelfBlock::new).register();
    public static final DeferredBlock<RoyalSofaBlock> SOFA = FurnitureUtil.sofa(BLOCKS, RoyalSofaBlock::new).register();
    public static final DeferredBlock<RoyalCounterBlock> COUNTER = FurnitureUtil.counter(BLOCKS, RoyalCounterBlock::new).register();
    public static final DeferredBlock<RoyalWallLightBlock> WALL_LIGHT = FurnitureUtil.wallLight(BLOCKS, RoyalWallLightBlock::new).register();
    public static final DeferredBlock<RoyalBenchBlock> BENCH = FurnitureUtil.bench(BLOCKS, RoyalBenchBlock::new).register();
    public static final DeferredBlock<RoyalWardrobeBlock> WARDROBE = FurnitureUtil.wardrobe(BLOCKS, RoyalWardrobeBlock::new).register();
    public static final DeferredBlock<RoyalTableBlock> TABLE = FurnitureUtil.table(BLOCKS, RoyalTableBlock::new).register();
    public static final DeferredBlock<StairBlock> STAIRS = FurnitureUtil.stairs(BLOCKS, BRICKS).register();
    public static final DeferredBlock<SlabBlock> SLAB = FurnitureUtil.slab(BLOCKS).register();
    public static final DeferredBlock<FenceBlock> FENCE = FurnitureUtil.fence(BLOCKS).register();
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE = FurnitureUtil.fenceGate(BLOCKS, WOOD_TYPE).register();
    public static final DeferredBlock<TrapDoorBlock> TRAPDOOR = FurnitureUtil.trapdoor(BLOCKS, BLOCK_SET_TYPE).register();
    public static final DeferredBlock<PressurePlateBlock> PRESSURE_PLATE = FurnitureUtil.pressurePlate(BLOCKS, BLOCK_SET_TYPE).register();
    public static final FurnitureUtil.SignPair<CeilingHangingSignBlock, WallHangingSignBlock> HANGING_SIGN = FurnitureUtil.hangingSign(REGISTREE, WOOD_TYPE);
    public static final FurnitureUtil.SignPair<StandingSignBlock, WallSignBlock> SIGN = FurnitureUtil.sign(REGISTREE, WOOD_TYPE);

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, () -> new ItemStack(BED_SINGLE.value()));

    public RoyalFurnitureSet(IEventBus modBus) {
        FurnitureUtil.registerEvents(modBus, REGISTREE);
    }
}
