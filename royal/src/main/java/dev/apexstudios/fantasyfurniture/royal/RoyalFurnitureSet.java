package dev.apexstudios.fantasyfurniture.royal;

import dev.apexstudios.apexcore.lib.util.WoodTypeBuilder;
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
import dev.apexstudios.fantasyfurniture.royal.block.RoyalDeskLeftBlock;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalDeskRightBlock;
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
import dev.apexstudios.fantasyfurniture.util.FurnitureUtil;
import dev.apexstudios.registree.api.Registree;
import dev.apexstudios.registree.api.holder.DeferredBlock;
import java.util.function.Supplier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
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

@Mod(RoyalFurnitureSet.ID)
public class RoyalFurnitureSet {
    public static final String ID = "fantasyfurniture_royal";
    public static final Registree REGISTREE = Registree.create(ID);

    public static final Supplier<WoodType> WOOD_TYPE = WoodTypeBuilder.builder()
            .copy(WoodType.OAK)
            .blockSetType(blockSet -> blockSet
                    .copy(BlockSetType.STONE)
            )
            .build(ID + ":royal");

    public static final Supplier<BlockSetType> BLOCK_SET_TYPE = () -> WOOD_TYPE.get().setType();

    public static final DeferredBlock<Block> BRICKS = FurnitureUtil.bricks(REGISTREE, Block::new);
    public static final DeferredBlock<RoyalWoolBlock> WOOL = FurnitureUtil.wool(REGISTREE, RoyalWoolBlock::new);
    public static final DeferredBlock<RoyalCarpetBlock> CARPET = FurnitureUtil.carpet(REGISTREE, RoyalCarpetBlock::new);
    public static final DeferredBlock<RoyalDresserBlock> DRESSER = FurnitureUtil.dresser(REGISTREE, RoyalDresserBlock::new);
    public static final DeferredBlock<RoyalStoolBlock> STOOL = FurnitureUtil.stool(REGISTREE, RoyalStoolBlock::new);
    public static final DeferredBlock<RoyalCushionBlock> CUSION = FurnitureUtil.cushion(REGISTREE, RoyalCushionBlock::new);
    public static final DeferredBlock<RoyalLockBoxBlock> LOCKBOX = FurnitureUtil.lockbox(REGISTREE, RoyalLockBoxBlock::new);
    public static final DeferredBlock<RoyalDrawerBlock> DRAWER = FurnitureUtil.drawer(REGISTREE, RoyalDrawerBlock::new);
    public static final DeferredBlock<RoyalChairBlock> CHAIR = FurnitureUtil.chair(REGISTREE, RoyalChairBlock::new);
    public static final DeferredBlock<RoyalBookshelfBlock> BOOKSHELF = FurnitureUtil.bookshelf(REGISTREE, RoyalBookshelfBlock::new);
    public static final DeferredBlock<RoyalBedSingleBlock> BED_SINGLE = FurnitureUtil.bedSingle(REGISTREE, RoyalBedSingleBlock::new);
    public static final DeferredBlock<RoyalBedDoubleBlock> BED_DOUBLE = FurnitureUtil.bedDouble(REGISTREE, RoyalBedDoubleBlock::new);
    public static final DeferredBlock<RoyalDoorBlock> DOOR_SINGLE = FurnitureUtil.doorSingle(REGISTREE, BLOCK_SET_TYPE, RoyalDoorBlock::new);
    public static final DeferredBlock<RoyalDoorBlock> DOOR_DOUBLE = FurnitureUtil.doorDouble(REGISTREE, BLOCK_SET_TYPE, RoyalDoorBlock::new);
    public static final DeferredBlock<RoyalDeskLeftBlock> DESK_LEFT = FurnitureUtil.desk(REGISTREE, true, RoyalDeskLeftBlock::new);
    public static final DeferredBlock<RoyalDeskRightBlock> DESK_RIGHT = FurnitureUtil.desk(REGISTREE, false, RoyalDeskRightBlock::new);
    public static final DeferredBlock<RoyalPaintingWideBlock> PAINTING_WIDE = FurnitureUtil.paintingWide(REGISTREE, RoyalPaintingWideBlock::new);
    public static final DeferredBlock<RoyalPaintingSmallBlock> PAINTING_SMALL = FurnitureUtil.paintingSmall(REGISTREE, RoyalPaintingSmallBlock::new);
    public static final DeferredBlock<RoyalOvenBlock> OVEN = FurnitureUtil.oven(REGISTREE, RoyalOvenBlock::new);
    public static final DeferredBlock<RoyalChestBlock> CHEST = FurnitureUtil.chest(REGISTREE, RoyalChestBlock::new);
    public static final DeferredBlock<RoyalFloorLightBlock> FLOOR_LIGHT = FurnitureUtil.floorLight(REGISTREE, RoyalFloorLightBlock::new);
    public static final DeferredBlock<RoyalChandelierBlock> CHANDELIER = FurnitureUtil.chandelier(REGISTREE, RoyalChandelierBlock::new);
    public static final DeferredBlock<RoyalShelfBlock> SHELF = FurnitureUtil.shelf(REGISTREE, RoyalShelfBlock::new);
    public static final DeferredBlock<RoyalSofaBlock> SOFA = FurnitureUtil.sofa(REGISTREE, RoyalSofaBlock::new);
    public static final DeferredBlock<RoyalCounterBlock> COUNTER = FurnitureUtil.counter(REGISTREE, RoyalCounterBlock::new);
    public static final DeferredBlock<RoyalWallLightBlock> WALL_LIGHT = FurnitureUtil.wallLight(REGISTREE, RoyalWallLightBlock::new);
    public static final DeferredBlock<RoyalBenchBlock> BENCH = FurnitureUtil.bench(REGISTREE, RoyalBenchBlock::new);
    public static final DeferredBlock<RoyalWardrobeBlock> WARDROBE = FurnitureUtil.wardrobe(REGISTREE, RoyalWardrobeBlock::new);
    public static final DeferredBlock<RoyalTableBlock> TABLE = FurnitureUtil.table(REGISTREE, RoyalTableBlock::new);
    public static final DeferredBlock<StairBlock> STAIRS = FurnitureUtil.stairs(REGISTREE, BRICKS);
    public static final DeferredBlock<SlabBlock> SLAB = FurnitureUtil.slab(REGISTREE);
    public static final DeferredBlock<FenceBlock> FENCE = FurnitureUtil.fence(REGISTREE);
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE = FurnitureUtil.fenceGate(REGISTREE, WOOD_TYPE);
    public static final DeferredBlock<TrapDoorBlock> TRAPDOOR = FurnitureUtil.trapdoor(REGISTREE, BLOCK_SET_TYPE);
    public static final DeferredBlock<PressurePlateBlock> PRESSURE_PLATE = FurnitureUtil.pressurePlate(REGISTREE, BLOCK_SET_TYPE);
    public static final FurnitureUtil.SignPair<CeilingHangingSignBlock, WallHangingSignBlock> HANGING_SIGN = FurnitureUtil.hangingSign(REGISTREE, WOOD_TYPE);
    public static final FurnitureUtil.SignPair<StandingSignBlock, WallSignBlock> SIGN = FurnitureUtil.sign(REGISTREE, WOOD_TYPE);

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, BED_SINGLE);

    public RoyalFurnitureSet(IEventBus modBus) {
        FurnitureUtil.registerEvents(modBus, REGISTREE, WOOD_TYPE);
    }
}
