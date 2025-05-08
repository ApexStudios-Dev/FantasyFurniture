package dev.apexstudios.fantasyfurniture.venthyr;

import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlock;
import dev.apexstudios.apexcore.lib.util.WoodTypeBuilder;
import dev.apexstudios.fantasyfurniture.block.BookshelfBlock;
import dev.apexstudios.fantasyfurniture.block.CounterBlock;
import dev.apexstudios.fantasyfurniture.block.DrawerBlock;
import dev.apexstudios.fantasyfurniture.block.FurnitureDoorBlock;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;
import dev.apexstudios.fantasyfurniture.block.PaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.block.PaintingWideBlock;
import dev.apexstudios.fantasyfurniture.block.WardrobeBlock;
import dev.apexstudios.fantasyfurniture.util.FurnitureUtil;
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

@Mod(VenthyrFurnitureSet.ID)
public class VenthyrFurnitureSet {
    public static final String ID = "fantasyfurniture_venthyr";
    public static final Registree REGISTREE = new Registree(ID);

    public static final WoodType WOOD_TYPE = WoodTypeBuilder.builder()
            .copy(WoodType.OAK)
            .build(ID + ":wood_type");

    public static final DeferredBlock<Block> PLANKS = FurnitureUtil.planks(REGISTREE, Block::new);
    public static final DeferredBlock<Block> WOOL = FurnitureUtil.wool(REGISTREE, Block::new);
    public static final DeferredBlock<CarpetBlock> CARPET = FurnitureUtil.carpet(REGISTREE, CarpetBlock::new);
    public static final DeferredBlock<VenthyrDresserBlock> DRESSER = FurnitureUtil.dresser(REGISTREE, VenthyrDresserBlock::new);
    public static final DeferredBlock<VenthyrStoolBlock> STOOL = FurnitureUtil.stool(REGISTREE, VenthyrStoolBlock::new);
    public static final DeferredBlock<VenthyrCushionBlock> CUSION = FurnitureUtil.cushion(REGISTREE, VenthyrCushionBlock::new);
    public static final DeferredBlock<VenthyrLockBoxBlock> LOCKBOX = FurnitureUtil.lockbox(REGISTREE, VenthyrLockBoxBlock::new);
    public static final DeferredBlock<DrawerBlock> DRAWER = FurnitureUtil.drawer(REGISTREE, DrawerBlock::new);
    public static final DeferredBlock<VenthyrChairBlock> CHAIR = FurnitureUtil.chair(REGISTREE, VenthyrChairBlock::new);
    public static final DeferredBlock<BookshelfBlock> BOOKSHELF = FurnitureUtil.bookshelf(REGISTREE, BookshelfBlock::new);
    public static final DeferredBlock<VenthyrBedSingleBlock> BED_SINGLE = FurnitureUtil.bedSingle(REGISTREE, VenthyrBedSingleBlock::new);
    public static final DeferredBlock<VenthyrBedDoubleBlock> BED_DOUBLE = FurnitureUtil.bedDouble(REGISTREE, VenthyrBedDoubleBlock::new);
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_SINGLE = FurnitureUtil.doorSingle(REGISTREE, WOOD_TYPE.setType(), FurnitureDoorBlock::new);
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_DOUBLE = FurnitureUtil.doorDouble(REGISTREE, WOOD_TYPE.setType(), FurnitureDoorBlock::new);
    public static final DeferredBlock<VenthyrDeskBlock> DESK_LEFT = FurnitureUtil.desk(REGISTREE, true, VenthyrDeskBlock::new);
    public static final DeferredBlock<VenthyrDeskBlock> DESK_RIGHT = FurnitureUtil.desk(REGISTREE, false, VenthyrDeskBlock::new);
    public static final DeferredBlock<PaintingWideBlock> PAINTING_WIDE = FurnitureUtil.paintingWide(REGISTREE, PaintingWideBlock::new);
    public static final DeferredBlock<PaintingSmallBlock> PAINTING_SMALL = FurnitureUtil.paintingSmall(REGISTREE, PaintingSmallBlock::new);
    public static final DeferredBlock<OvenBlock> OVEN = FurnitureUtil.oven(REGISTREE, OvenBlock::new);
    public static final DeferredBlock<VenthyrChestBlock> CHEST = FurnitureUtil.chest(REGISTREE, VenthyrChestBlock::new);
    public static final DeferredBlock<VenthyrFloorLightBlock> FLOOR_LIGHT = FurnitureUtil.floorLight(REGISTREE, VenthyrFloorLightBlock::new);
    public static final DeferredBlock<VenthyrChandelierBlock> CHANDELIER = FurnitureUtil.chandelier(REGISTREE, VenthyrChandelierBlock::new);
    public static final DeferredBlock<VenthyrShelfBlock> SHELF = FurnitureUtil.shelf(REGISTREE, VenthyrShelfBlock::new);
    public static final DeferredBlock<VenthyrSofaBlock> SOFA = FurnitureUtil.sofa(REGISTREE, VenthyrSofaBlock::new);
    public static final DeferredBlock<CounterBlock> COUNTER = FurnitureUtil.counter(REGISTREE, CounterBlock::new);
    public static final DeferredBlock<VenthyrWallLightBlock> WALL_LIGHT = FurnitureUtil.wallLight(REGISTREE, VenthyrWallLightBlock::new);
    public static final DeferredBlock<VenthyrBenchBlock> BENCH = FurnitureUtil.bench(REGISTREE, VenthyrBenchBlock::new);
    public static final DeferredBlock<WardrobeBlock> WARDROBE = FurnitureUtil.wardrobe(REGISTREE, WardrobeBlock::new);
    public static final DeferredBlock<VenthyrTableBlock> TABLE = FurnitureUtil.table(REGISTREE, VenthyrTableBlock::new);
    public static final DeferredBlock<StairBlock> STAIRS = FurnitureUtil.stairs(REGISTREE, PLANKS);
    public static final DeferredBlock<SlabBlock> SLAB = FurnitureUtil.slab(REGISTREE);
    public static final DeferredBlock<FenceBlock> FENCE = FurnitureUtil.fence(REGISTREE);
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE = FurnitureUtil.fenceGate(REGISTREE, WOOD_TYPE);
    public static final DeferredBlock<TrapDoorBlock> TRAPDOOR = FurnitureUtil.trapdoor(REGISTREE, WOOD_TYPE.setType());
    public static final DeferredBlock<PressurePlateBlock> PRESSURE_PLATE = FurnitureUtil.pressurePlate(REGISTREE, WOOD_TYPE.setType());
    public static final FurnitureUtil.SignPair<CeilingHangingSignBlock, WallHangingSignBlock> HANGING_SIGN = FurnitureUtil.hangingSign(REGISTREE, WOOD_TYPE);
    public static final FurnitureUtil.SignPair<StandingSignBlock, WallSignBlock> SIGN = FurnitureUtil.sign(REGISTREE, WOOD_TYPE);

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, BED_SINGLE);

    public VenthyrFurnitureSet(IEventBus modBus) {
        FurnitureUtil.registerEvents(modBus, REGISTREE, WOOD_TYPE);
    }
}
