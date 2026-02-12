package dev.apexstudios.fantasyfurniture.dunmer.common;

import dev.apexstudios.apexcore.api.util.WoodTypeBuilder;
import dev.apexstudios.fantasyfurniture.common.block.FurnitureDoorBlock;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerBedDoubleBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerBedSingleBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerBenchBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerBookshelfBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerChairBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerChandelierBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerChestBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerCounterBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerCushionBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerDeskLeftBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerDeskRightBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerDrawerBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerDresserBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerFloorLightBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerLockBoxBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerOvenBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerPaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerPaintingWideBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerShelfBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerSofaBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerStoolBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerTableBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerWallLightBlock;
import dev.apexstudios.fantasyfurniture.dunmer.common.block.DunmerWardrobeBlock;
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

@Mod(DunmerFurnitureSet.ID)
public class DunmerFurnitureSet {
    public static final String ID = "fantasyfurniture_dunmer";
    public static final Registree REGISTREE = Registree.create(ID);
    public static final BlockRegistrar BLOCKS = REGISTREE.blocks();

    public static final Supplier<WoodType> WOOD_TYPE = WoodTypeBuilder.builder()
            .copy(WoodType.OAK)
            .build(ID + ":dunmer");

    public static final Supplier<BlockSetType> BLOCK_SET_TYPE = () -> WOOD_TYPE.get().setType();

    public static final DeferredBlock<Block> PLANKS = FurnitureUtil.planks(BLOCKS, Block::new).register();
    public static final DeferredBlock<Block> WOOL = FurnitureUtil.wool(BLOCKS, Block::new).register();
    public static final DeferredBlock<CarpetBlock> CARPET = FurnitureUtil.carpet(BLOCKS, CarpetBlock::new).register();
    public static final DeferredBlock<DunmerDresserBlock> DRESSER = FurnitureUtil.dresser(BLOCKS, DunmerDresserBlock::new).register();
    public static final DeferredBlock<DunmerStoolBlock> STOOL = FurnitureUtil.stool(BLOCKS, DunmerStoolBlock::new).register();
    public static final DeferredBlock<DunmerCushionBlock> CUSION = FurnitureUtil.cushion(BLOCKS, DunmerCushionBlock::new).register();
    public static final DeferredBlock<DunmerLockBoxBlock> LOCKBOX = FurnitureUtil.lockbox(BLOCKS, DunmerLockBoxBlock::new).register();
    public static final DeferredBlock<DunmerDrawerBlock> DRAWER = FurnitureUtil.drawer(BLOCKS, DunmerDrawerBlock::new).register();
    public static final DeferredBlock<DunmerChairBlock> CHAIR = FurnitureUtil.chair(BLOCKS, DunmerChairBlock::new).register();
    public static final DeferredBlock<DunmerBookshelfBlock> BOOKSHELF = FurnitureUtil.bookshelf(BLOCKS, DunmerBookshelfBlock::new).register();
    public static final DeferredBlock<DunmerBedSingleBlock> BED_SINGLE = FurnitureUtil.bedSingle(BLOCKS, DunmerBedSingleBlock::new).register();
    public static final DeferredBlock<DunmerBedDoubleBlock> BED_DOUBLE = FurnitureUtil.bedDouble(BLOCKS, DunmerBedDoubleBlock::new).register();
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_SINGLE = FurnitureUtil.doorSingle(BLOCKS, BLOCK_SET_TYPE, FurnitureDoorBlock::new).register();
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_DOUBLE = FurnitureUtil.doorDouble(BLOCKS, BLOCK_SET_TYPE, FurnitureDoorBlock::new).register();
    public static final DeferredBlock<DunmerDeskLeftBlock> DESK_LEFT = FurnitureUtil.desk(BLOCKS, true, DunmerDeskLeftBlock::new).register();
    public static final DeferredBlock<DunmerDeskRightBlock> DESK_RIGHT = FurnitureUtil.desk(BLOCKS, false, DunmerDeskRightBlock::new).register();
    public static final DeferredBlock<DunmerPaintingWideBlock> PAINTING_WIDE = FurnitureUtil.paintingWide(BLOCKS, DunmerPaintingWideBlock::new).register();
    public static final DeferredBlock<DunmerPaintingSmallBlock> PAINTING_SMALL = FurnitureUtil.paintingSmall(BLOCKS, DunmerPaintingSmallBlock::new).register();
    public static final DeferredBlock<DunmerOvenBlock> OVEN = FurnitureUtil.oven(BLOCKS, DunmerOvenBlock::new).register();
    public static final DeferredBlock<DunmerChestBlock> CHEST = FurnitureUtil.chest(BLOCKS, DunmerChestBlock::new).register();
    public static final DeferredBlock<DunmerFloorLightBlock> FLOOR_LIGHT = FurnitureUtil.floorLight(BLOCKS, DunmerFloorLightBlock::new).register();
    public static final DeferredBlock<DunmerChandelierBlock> CHANDELIER = FurnitureUtil.chandelier(BLOCKS, DunmerChandelierBlock::new).register();
    public static final DeferredBlock<DunmerShelfBlock> SHELF = FurnitureUtil.shelf(BLOCKS, DunmerShelfBlock::new).register();
    public static final DeferredBlock<DunmerSofaBlock> SOFA = FurnitureUtil.sofa(BLOCKS, DunmerSofaBlock::new).register();
    public static final DeferredBlock<DunmerCounterBlock> COUNTER = FurnitureUtil.counter(BLOCKS, DunmerCounterBlock::new).register();
    public static final DeferredBlock<DunmerWallLightBlock> WALL_LIGHT = FurnitureUtil.wallLight(BLOCKS, DunmerWallLightBlock::new).register();
    public static final DeferredBlock<DunmerBenchBlock> BENCH = FurnitureUtil.bench(BLOCKS, DunmerBenchBlock::new).register();
    public static final DeferredBlock<DunmerWardrobeBlock> WARDROBE = FurnitureUtil.wardrobe(BLOCKS, DunmerWardrobeBlock::new).register();
    public static final DeferredBlock<DunmerTableBlock> TABLE = FurnitureUtil.table(BLOCKS, DunmerTableBlock::new).register();
    public static final DeferredBlock<StairBlock> STAIRS = FurnitureUtil.stairs(BLOCKS, PLANKS).register();
    public static final DeferredBlock<SlabBlock> SLAB = FurnitureUtil.slab(BLOCKS).register();
    public static final DeferredBlock<FenceBlock> FENCE = FurnitureUtil.fence(BLOCKS).register();
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE = FurnitureUtil.fenceGate(BLOCKS, WOOD_TYPE).register();
    public static final DeferredBlock<TrapDoorBlock> TRAPDOOR = FurnitureUtil.trapdoor(BLOCKS, BLOCK_SET_TYPE).register();
    public static final DeferredBlock<PressurePlateBlock> PRESSURE_PLATE = FurnitureUtil.pressurePlate(BLOCKS, BLOCK_SET_TYPE).register();
    public static final FurnitureUtil.SignPair<CeilingHangingSignBlock, WallHangingSignBlock> HANGING_SIGN = FurnitureUtil.hangingSign(REGISTREE, WOOD_TYPE);
    public static final FurnitureUtil.SignPair<StandingSignBlock, WallSignBlock> SIGN = FurnitureUtil.sign(REGISTREE, WOOD_TYPE);

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, () -> new ItemStack(BED_SINGLE.value()));

    public DunmerFurnitureSet(IEventBus modBus) {
        FurnitureUtil.registerEvents(modBus, REGISTREE);
    }
}
