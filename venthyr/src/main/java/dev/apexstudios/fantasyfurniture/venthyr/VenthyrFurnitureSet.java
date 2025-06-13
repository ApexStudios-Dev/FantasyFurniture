package dev.apexstudios.fantasyfurniture.venthyr;

import dev.apexstudios.apexcore.lib.placement.PlacementRenderEvent;
import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlock;
import dev.apexstudios.apexcore.lib.util.WoodTypeBuilder;
import dev.apexstudios.fantasyfurniture.block.FurnitureDoorBlock;
import dev.apexstudios.fantasyfurniture.block.TableBlock;
import dev.apexstudios.fantasyfurniture.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrBedDoubleBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrBedSingleBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrBenchBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrBookshelfBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrChairBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrChandelierBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrChestBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrCounterBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrCushionBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrDeskLeftBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrDeskRightBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrDrawerBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrDresserBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrFloorLightBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrLockBoxBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrOvenBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrPaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrPaintingWideBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrShelfBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrSofaBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrStoolBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrTableBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrWallLightBlock;
import dev.apexstudios.fantasyfurniture.venthyr.block.VenthyrWardrobeBlock;
import net.minecraft.Util;
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
import net.neoforged.neoforge.common.NeoForge;

@Mod(VenthyrFurnitureSet.ID)
public class VenthyrFurnitureSet {
    public static final String ID = "fantasyfurniture_venthyr";
    public static final Registree REGISTREE = new Registree(ID);

    public static final WoodType WOOD_TYPE = WoodTypeBuilder.builder()
            .copy(WoodType.OAK)
            .build(ID + ":venthyr");

    public static final DeferredBlock<Block> PLANKS = FurnitureUtil.planks(REGISTREE, Block::new);
    public static final DeferredBlock<Block> WOOL = FurnitureUtil.wool(REGISTREE, Block::new);
    public static final DeferredBlock<CarpetBlock> CARPET = FurnitureUtil.carpet(REGISTREE, CarpetBlock::new);
    public static final DeferredBlock<VenthyrDresserBlock> DRESSER = FurnitureUtil.dresser(REGISTREE, VenthyrDresserBlock::new);
    public static final DeferredBlock<VenthyrStoolBlock> STOOL = FurnitureUtil.stool(REGISTREE, VenthyrStoolBlock::new);
    public static final DeferredBlock<VenthyrCushionBlock> CUSION = FurnitureUtil.cushion(REGISTREE, VenthyrCushionBlock::new);
    public static final DeferredBlock<VenthyrLockBoxBlock> LOCKBOX = FurnitureUtil.lockbox(REGISTREE, VenthyrLockBoxBlock::new);
    public static final DeferredBlock<VenthyrDrawerBlock> DRAWER = FurnitureUtil.drawer(REGISTREE, VenthyrDrawerBlock::new);
    public static final DeferredBlock<VenthyrChairBlock> CHAIR = FurnitureUtil.chair(REGISTREE, VenthyrChairBlock::new);
    public static final DeferredBlock<VenthyrBookshelfBlock> BOOKSHELF = FurnitureUtil.bookshelf(REGISTREE, VenthyrBookshelfBlock::new);
    public static final DeferredBlock<VenthyrBedSingleBlock> BED_SINGLE = FurnitureUtil.bedSingle(REGISTREE, VenthyrBedSingleBlock::new);
    public static final DeferredBlock<VenthyrBedDoubleBlock> BED_DOUBLE = FurnitureUtil.bedDouble(REGISTREE, VenthyrBedDoubleBlock::new);
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_SINGLE = FurnitureUtil.doorSingle(REGISTREE, WOOD_TYPE.setType(), FurnitureDoorBlock::new);
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_DOUBLE = FurnitureUtil.doorDouble(REGISTREE, WOOD_TYPE.setType(), FurnitureDoorBlock::new);
    public static final DeferredBlock<VenthyrDeskLeftBlock> DESK_LEFT = FurnitureUtil.desk(REGISTREE, true, VenthyrDeskLeftBlock::new);
    public static final DeferredBlock<VenthyrDeskRightBlock> DESK_RIGHT = FurnitureUtil.desk(REGISTREE, false, VenthyrDeskRightBlock::new);
    public static final DeferredBlock<VenthyrPaintingWideBlock> PAINTING_WIDE = FurnitureUtil.paintingWide(REGISTREE, VenthyrPaintingWideBlock::new);
    public static final DeferredBlock<VenthyrPaintingSmallBlock> PAINTING_SMALL = FurnitureUtil.paintingSmall(REGISTREE, VenthyrPaintingSmallBlock::new);
    public static final DeferredBlock<VenthyrOvenBlock> OVEN = FurnitureUtil.oven(REGISTREE, VenthyrOvenBlock::new);
    public static final DeferredBlock<VenthyrChestBlock> CHEST = FurnitureUtil.chest(REGISTREE, VenthyrChestBlock::new);
    public static final DeferredBlock<VenthyrFloorLightBlock> FLOOR_LIGHT = FurnitureUtil.floorLight(REGISTREE, VenthyrFloorLightBlock::new);
    public static final DeferredBlock<VenthyrChandelierBlock> CHANDELIER = FurnitureUtil.chandelier(REGISTREE, VenthyrChandelierBlock::new);
    public static final DeferredBlock<VenthyrShelfBlock> SHELF = FurnitureUtil.shelf(REGISTREE, VenthyrShelfBlock::new);
    public static final DeferredBlock<VenthyrSofaBlock> SOFA = FurnitureUtil.sofa(REGISTREE, VenthyrSofaBlock::new);
    public static final DeferredBlock<VenthyrCounterBlock> COUNTER = FurnitureUtil.counter(REGISTREE, VenthyrCounterBlock::new);
    public static final DeferredBlock<VenthyrWallLightBlock> WALL_LIGHT = FurnitureUtil.wallLight(REGISTREE, VenthyrWallLightBlock::new);
    public static final DeferredBlock<VenthyrBenchBlock> BENCH = FurnitureUtil.bench(REGISTREE, VenthyrBenchBlock::new);
    public static final DeferredBlock<VenthyrWardrobeBlock> WARDROBE = FurnitureUtil.wardrobe(REGISTREE, VenthyrWardrobeBlock::new);
    public static final DeferredBlock<VenthyrTableBlock> TABLE = FurnitureUtil.table(REGISTREE, VenthyrTableBlock::new);
    public static final DeferredBlock<StairBlock> STAIRS = FurnitureUtil.stairs(REGISTREE, PLANKS);
    public static final DeferredBlock<SlabBlock> SLAB = FurnitureUtil.slab(REGISTREE);
    public static final DeferredBlock<FenceBlock> FENCE = FurnitureUtil.fence(REGISTREE);
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE = FurnitureUtil.fenceGate(REGISTREE, WOOD_TYPE);
    public static final DeferredBlock<TrapDoorBlock> TRAPDOOR = FurnitureUtil.trapdoor(REGISTREE, WOOD_TYPE.setType());
    public static final DeferredBlock<PressurePlateBlock> PRESSURE_PLATE = FurnitureUtil.pressurePlate(REGISTREE, WOOD_TYPE.setType());
    public static final FurnitureUtil.SignPair<CeilingHangingSignBlock, WallHangingSignBlock> HANGING_SIGN = FurnitureUtil.hangingSign(REGISTREE, WOOD_TYPE);
    public static final FurnitureUtil.SignPair<StandingSignBlock, WallSignBlock> SIGN = FurnitureUtil.sign(REGISTREE, WOOD_TYPE);

    public static final DeferredBlock<VenthyrTableBlock> TABLE_CLOTH = Util.make(REGISTREE.registerBlock(FurnitureUtil.Names.TABLE + "_cloth", VenthyrTableBlock::new, FurnitureUtil.TABLE_PROPERTIES), REGISTREE::registerSimpleBlockItem);

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, BED_SINGLE);

    public VenthyrFurnitureSet(IEventBus modBus) {
        FurnitureUtil.registerEvents(modBus, REGISTREE, WOOD_TYPE);

        NeoForge.EVENT_BUS.addListener(PlacementRenderEvent.DefaultBlockState.class, event -> {
            if(TABLE_CLOTH.is(event.defaultBlockState()))
                event.setDefaultBlockState(TableBlock.get(event.level(), event.pos(), event.defaultBlockState()));
        });
    }
}
