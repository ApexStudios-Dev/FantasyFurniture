package dev.apexstudios.fantasyfurniture.venthyr.common;

import dev.apexstudios.apexcore.api.util.WoodTypeBuilder;
import dev.apexstudios.fantasyfurniture.common.block.FurnitureDoorBlock;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrBedDoubleBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrBedSingleBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrBenchBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrBookshelfBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrChairBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrChandelierBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrChestBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrCounterBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrCushionBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrDeskLeftBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrDeskRightBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrDrawerBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrDresserBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrFloorLightBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrLockBoxBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrOvenBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrPaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrPaintingWideBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrShelfBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrSofaBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrStoolBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrTableBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrWallLightBlock;
import dev.apexstudios.fantasyfurniture.venthyr.common.block.VenthyrWardrobeBlock;
import dev.apexstudios.registree.Registree;
import dev.apexstudios.registree.registrar.BlockRegistrar;
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

@Mod(VenthyrFurnitureSet.ID)
public class VenthyrFurnitureSet {
    public static final String ID = "fantasyfurniture_venthyr";
    public static final Registree REGISTREE = Registree.create(ID);
    public static final BlockRegistrar BLOCKS = REGISTREE.blocks();

    public static final Supplier<WoodType> WOOD_TYPE = WoodTypeBuilder.builder()
            .copy(WoodType.OAK)
            .build(ID + ":venthyr");

    public static final Supplier<BlockSetType> BLOCK_SET_TYPE = () -> WOOD_TYPE.get().setType();

    public static final DeferredBlock<Block> PLANKS = FurnitureUtil.planks(BLOCKS, Block::new).register();
    public static final DeferredBlock<Block> WOOL = FurnitureUtil.wool(BLOCKS, Block::new).register();
    public static final DeferredBlock<CarpetBlock> CARPET = FurnitureUtil.carpet(BLOCKS, CarpetBlock::new).register();
    public static final DeferredBlock<VenthyrDresserBlock> DRESSER = FurnitureUtil.dresser(BLOCKS, VenthyrDresserBlock::new).register();
    public static final DeferredBlock<VenthyrStoolBlock> STOOL = FurnitureUtil.stool(BLOCKS, VenthyrStoolBlock::new).register();
    public static final DeferredBlock<VenthyrCushionBlock> CUSION = FurnitureUtil.cushion(BLOCKS, VenthyrCushionBlock::new).register();
    public static final DeferredBlock<VenthyrLockBoxBlock> LOCKBOX = FurnitureUtil.lockbox(BLOCKS, VenthyrLockBoxBlock::new).register();
    public static final DeferredBlock<VenthyrDrawerBlock> DRAWER = FurnitureUtil.drawer(BLOCKS, VenthyrDrawerBlock::new).register();
    public static final DeferredBlock<VenthyrChairBlock> CHAIR = FurnitureUtil.chair(BLOCKS, VenthyrChairBlock::new).register();
    public static final DeferredBlock<VenthyrBookshelfBlock> BOOKSHELF = FurnitureUtil.bookshelf(BLOCKS, VenthyrBookshelfBlock::new).register();
    public static final DeferredBlock<VenthyrBedSingleBlock> BED_SINGLE = FurnitureUtil.bedSingle(BLOCKS, VenthyrBedSingleBlock::new).register();
    public static final DeferredBlock<VenthyrBedDoubleBlock> BED_DOUBLE = FurnitureUtil.bedDouble(BLOCKS, VenthyrBedDoubleBlock::new).register();
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_SINGLE = FurnitureUtil.doorSingle(BLOCKS, BLOCK_SET_TYPE, FurnitureDoorBlock::new).register();
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_DOUBLE = FurnitureUtil.doorDouble(BLOCKS, BLOCK_SET_TYPE, FurnitureDoorBlock::new).register();
    public static final DeferredBlock<VenthyrDeskLeftBlock> DESK_LEFT = FurnitureUtil.desk(BLOCKS, true, VenthyrDeskLeftBlock::new).register();
    public static final DeferredBlock<VenthyrDeskRightBlock> DESK_RIGHT = FurnitureUtil.desk(BLOCKS, false, VenthyrDeskRightBlock::new).register();
    public static final DeferredBlock<VenthyrPaintingWideBlock> PAINTING_WIDE = FurnitureUtil.paintingWide(BLOCKS, VenthyrPaintingWideBlock::new).register();
    public static final DeferredBlock<VenthyrPaintingSmallBlock> PAINTING_SMALL = FurnitureUtil.paintingSmall(BLOCKS, VenthyrPaintingSmallBlock::new).register();
    public static final DeferredBlock<VenthyrOvenBlock> OVEN = FurnitureUtil.oven(BLOCKS, VenthyrOvenBlock::new).register();
    public static final DeferredBlock<VenthyrChestBlock> CHEST = FurnitureUtil.chest(BLOCKS, VenthyrChestBlock::new).register();
    public static final DeferredBlock<VenthyrFloorLightBlock> FLOOR_LIGHT = FurnitureUtil.floorLight(BLOCKS, VenthyrFloorLightBlock::new).register();
    public static final DeferredBlock<VenthyrChandelierBlock> CHANDELIER = FurnitureUtil.chandelier(BLOCKS, VenthyrChandelierBlock::new).register();
    public static final DeferredBlock<VenthyrShelfBlock> SHELF = FurnitureUtil.shelf(BLOCKS, VenthyrShelfBlock::new).register();
    public static final DeferredBlock<VenthyrSofaBlock> SOFA = FurnitureUtil.sofa(BLOCKS, VenthyrSofaBlock::new).register();
    public static final DeferredBlock<VenthyrCounterBlock> COUNTER = FurnitureUtil.counter(BLOCKS, VenthyrCounterBlock::new).register();
    public static final DeferredBlock<VenthyrWallLightBlock> WALL_LIGHT = FurnitureUtil.wallLight(BLOCKS, VenthyrWallLightBlock::new).register();
    public static final DeferredBlock<VenthyrBenchBlock> BENCH = FurnitureUtil.bench(BLOCKS, VenthyrBenchBlock::new).register();
    public static final DeferredBlock<VenthyrWardrobeBlock> WARDROBE = FurnitureUtil.wardrobe(BLOCKS, VenthyrWardrobeBlock::new).register();
    public static final DeferredBlock<VenthyrTableBlock> TABLE = FurnitureUtil.table(BLOCKS, VenthyrTableBlock::new).register();
    public static final DeferredBlock<StairBlock> STAIRS = FurnitureUtil.stairs(BLOCKS, PLANKS).register();
    public static final DeferredBlock<SlabBlock> SLAB = FurnitureUtil.slab(BLOCKS).register();
    public static final DeferredBlock<FenceBlock> FENCE = FurnitureUtil.fence(BLOCKS).register();
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE = FurnitureUtil.fenceGate(BLOCKS, WOOD_TYPE).register();
    public static final DeferredBlock<TrapDoorBlock> TRAPDOOR = FurnitureUtil.trapdoor(BLOCKS, BLOCK_SET_TYPE).register();
    public static final DeferredBlock<PressurePlateBlock> PRESSURE_PLATE = FurnitureUtil.pressurePlate(BLOCKS, BLOCK_SET_TYPE).register();
    public static final FurnitureUtil.SignPair<CeilingHangingSignBlock, WallHangingSignBlock> HANGING_SIGN = FurnitureUtil.hangingSign(REGISTREE, WOOD_TYPE);
    public static final FurnitureUtil.SignPair<StandingSignBlock, WallSignBlock> SIGN = FurnitureUtil.sign(REGISTREE, WOOD_TYPE);

    public static final DeferredBlock<VenthyrTableBlock> TABLE_CLOTH = FurnitureUtil.table(BLOCKS, FurnitureUtil.Names.TABLE + "_cloth", VenthyrTableBlock::new).register();

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, () -> new ItemStack(BED_SINGLE.value()));

    public VenthyrFurnitureSet(IEventBus modBus) {
        FurnitureUtil.registerEvents(modBus, REGISTREE);
    }
}
