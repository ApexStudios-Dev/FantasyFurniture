package dev.apexstudios.fantasyfurniture.venthyr.common;

import dev.apexstudios.apexcore.api.util.WoodTypeBuilder;
import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.block.FurnitureDoorBlock;
import dev.apexstudios.fantasyfurniture.common.ctm.CtmPacks;
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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;

@Mod(VenthyrFurnitureSet.ID)
public class VenthyrFurnitureSet {
    public static final String ID = "fantasyfurniture_venthyr";
    public static final Registree REGISTREE = Registree.create(ID);

    public static final Supplier<WoodType> WOOD_TYPE = WoodTypeBuilder.builder()
            .copy(WoodType.OAK)
            .build(ID + ":venthyr");

    public static final Supplier<BlockSetType> BLOCK_SET_TYPE = () -> WOOD_TYPE.get().setType();

    public static final DeferredBlock<Block> PLANKS = FurnitureUtil.planks(REGISTREE, Block::new).register();
    public static final DeferredBlock<Block> WOOL = FurnitureUtil.wool(REGISTREE, Block::new).register();
    public static final DeferredBlock<CarpetBlock> CARPET = FurnitureUtil.carpet(REGISTREE, CarpetBlock::new).register();
    public static final DeferredBlock<VenthyrDresserBlock> DRESSER = FurnitureUtil.dresser(REGISTREE, VenthyrDresserBlock::new).register();
    public static final DeferredBlock<VenthyrStoolBlock> STOOL = FurnitureUtil.stool(REGISTREE, VenthyrStoolBlock::new).register();
    public static final DeferredBlock<VenthyrCushionBlock> CUSION = FurnitureUtil.cushion(REGISTREE, VenthyrCushionBlock::new).register();
    public static final DeferredBlock<VenthyrLockBoxBlock> LOCKBOX = FurnitureUtil.lockbox(REGISTREE, VenthyrLockBoxBlock::new).register();
    public static final DeferredBlock<VenthyrDrawerBlock> DRAWER = FurnitureUtil.drawer(REGISTREE, VenthyrDrawerBlock::new).register();
    public static final DeferredBlock<VenthyrChairBlock> CHAIR = FurnitureUtil.chair(REGISTREE, VenthyrChairBlock::new).register();
    public static final DeferredBlock<VenthyrBookshelfBlock> BOOKSHELF = FurnitureUtil.bookshelf(REGISTREE, VenthyrBookshelfBlock::new).register();
    public static final DeferredBlock<VenthyrBedSingleBlock> BED_SINGLE = FurnitureUtil.bedSingle(REGISTREE, VenthyrBedSingleBlock::new).register();
    public static final DeferredBlock<VenthyrBedDoubleBlock> BED_DOUBLE = FurnitureUtil.bedDouble(REGISTREE, VenthyrBedDoubleBlock::new).register();
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_SINGLE = FurnitureUtil.doorSingle(REGISTREE, BLOCK_SET_TYPE, FurnitureDoorBlock::new).register();
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_DOUBLE = FurnitureUtil.doorDouble(REGISTREE, BLOCK_SET_TYPE, FurnitureDoorBlock::new).register();
    public static final DeferredBlock<VenthyrDeskLeftBlock> DESK_LEFT = FurnitureUtil.desk(REGISTREE, true, VenthyrDeskLeftBlock::new).register();
    public static final DeferredBlock<VenthyrDeskRightBlock> DESK_RIGHT = FurnitureUtil.desk(REGISTREE, false, VenthyrDeskRightBlock::new).register();
    public static final DeferredBlock<VenthyrPaintingWideBlock> PAINTING_WIDE = FurnitureUtil.paintingWide(REGISTREE, VenthyrPaintingWideBlock::new).register();
    public static final DeferredBlock<VenthyrPaintingSmallBlock> PAINTING_SMALL = FurnitureUtil.paintingSmall(REGISTREE, VenthyrPaintingSmallBlock::new).register();
    public static final DeferredBlock<VenthyrOvenBlock> OVEN = FurnitureUtil.oven(REGISTREE, VenthyrOvenBlock::new).register();
    public static final DeferredBlock<VenthyrChestBlock> CHEST = FurnitureUtil.chest(REGISTREE, VenthyrChestBlock::new).register();
    public static final DeferredBlock<VenthyrFloorLightBlock> FLOOR_LIGHT = FurnitureUtil.floorLight(REGISTREE, VenthyrFloorLightBlock::new).register();
    public static final DeferredBlock<VenthyrChandelierBlock> CHANDELIER = FurnitureUtil.chandelier(REGISTREE, VenthyrChandelierBlock::new).register();
    public static final DeferredBlock<VenthyrShelfBlock> SHELF = FurnitureUtil.shelf(REGISTREE, VenthyrShelfBlock::new).register();
    public static final DeferredBlock<VenthyrSofaBlock> SOFA = FurnitureUtil.sofa(REGISTREE, VenthyrSofaBlock::new).register();
    public static final DeferredBlock<VenthyrCounterBlock> COUNTER = FurnitureUtil.counter(REGISTREE, VenthyrCounterBlock::new).register();
    public static final DeferredBlock<VenthyrWallLightBlock> WALL_LIGHT = FurnitureUtil.wallLight(REGISTREE, VenthyrWallLightBlock::new).register();
    public static final DeferredBlock<VenthyrBenchBlock> BENCH = FurnitureUtil.bench(REGISTREE, VenthyrBenchBlock::new).register();
    public static final DeferredBlock<VenthyrWardrobeBlock> WARDROBE = FurnitureUtil.wardrobe(REGISTREE, VenthyrWardrobeBlock::new).register();
    public static final DeferredBlock<VenthyrTableBlock> TABLE = FurnitureUtil.table(REGISTREE, VenthyrTableBlock::new).register();
    public static final DeferredBlock<StairBlock> STAIRS = FurnitureUtil.stairs(REGISTREE, PLANKS).register();
    public static final DeferredBlock<SlabBlock> SLAB = FurnitureUtil.slab(REGISTREE).register();
    public static final DeferredBlock<FenceBlock> FENCE = FurnitureUtil.fence(REGISTREE).register();
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE = FurnitureUtil.fenceGate(REGISTREE, WOOD_TYPE).register();
    public static final DeferredBlock<TrapDoorBlock> TRAPDOOR = FurnitureUtil.trapdoor(REGISTREE, BLOCK_SET_TYPE).register();
    public static final DeferredBlock<PressurePlateBlock> PRESSURE_PLATE = FurnitureUtil.pressurePlate(REGISTREE, BLOCK_SET_TYPE).register();
    public static final FurnitureUtil.SignPair<CeilingHangingSignBlock, WallHangingSignBlock> HANGING_SIGN = FurnitureUtil.hangingSign(REGISTREE, WOOD_TYPE);
    public static final FurnitureUtil.SignPair<StandingSignBlock, WallSignBlock> SIGN = FurnitureUtil.sign(REGISTREE, WOOD_TYPE);

    public static final DeferredBlock<VenthyrTableBlock> TABLE_CLOTH = REGISTREE.block(FurnitureUtil.Names.TABLE + "_cloth", VenthyrTableBlock::new)
            .initialProperties(() -> BlockBehaviour.Properties.ofFullCopy(TABLE.value()))
            .item()
            .register();

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, () -> new ItemStack(BED_SINGLE.value()));

    public VenthyrFurnitureSet(IEventBus modBus) {
        FantasyFurniture.FURNITURE_MODS.add(ID);
        CtmPacks.register(REGISTREE);
        REGISTREE.register(modBus);
    }
}
