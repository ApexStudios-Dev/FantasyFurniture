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
import dev.apexstudios.registree.api.Registree;
import dev.apexstudios.registree.api.holder.DeferredBlock;
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

@Mod(DunmerFurnitureSet.ID)
public class DunmerFurnitureSet {
    public static final String ID = "fantasyfurniture_dunmer";
    public static final Registree REGISTREE = Registree.create(ID);

    public static final Supplier<WoodType> WOOD_TYPE = WoodTypeBuilder.builder()
            .copy(WoodType.OAK)
            .build(ID + ":dunmer");

    public static final Supplier<BlockSetType> BLOCK_SET_TYPE = () -> WOOD_TYPE.get().setType();

    public static final DeferredBlock<Block> PLANKS = FurnitureUtil.planks(REGISTREE, Block::new);
    public static final DeferredBlock<Block> WOOL = FurnitureUtil.wool(REGISTREE, Block::new);
    public static final DeferredBlock<CarpetBlock> CARPET = FurnitureUtil.carpet(REGISTREE, CarpetBlock::new);
    public static final DeferredBlock<DunmerDresserBlock> DRESSER = FurnitureUtil.dresser(REGISTREE, DunmerDresserBlock::new);
    public static final DeferredBlock<DunmerStoolBlock> STOOL = FurnitureUtil.stool(REGISTREE, DunmerStoolBlock::new);
    public static final DeferredBlock<DunmerCushionBlock> CUSION = FurnitureUtil.cushion(REGISTREE, DunmerCushionBlock::new);
    public static final DeferredBlock<DunmerLockBoxBlock> LOCKBOX = FurnitureUtil.lockbox(REGISTREE, DunmerLockBoxBlock::new);
    public static final DeferredBlock<DunmerDrawerBlock> DRAWER = FurnitureUtil.drawer(REGISTREE, DunmerDrawerBlock::new);
    public static final DeferredBlock<DunmerChairBlock> CHAIR = FurnitureUtil.chair(REGISTREE, DunmerChairBlock::new);
    public static final DeferredBlock<DunmerBookshelfBlock> BOOKSHELF = FurnitureUtil.bookshelf(REGISTREE, DunmerBookshelfBlock::new);
    public static final DeferredBlock<DunmerBedSingleBlock> BED_SINGLE = FurnitureUtil.bedSingle(REGISTREE, DunmerBedSingleBlock::new);
    public static final DeferredBlock<DunmerBedDoubleBlock> BED_DOUBLE = FurnitureUtil.bedDouble(REGISTREE, DunmerBedDoubleBlock::new);
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_SINGLE = FurnitureUtil.doorSingle(REGISTREE, BLOCK_SET_TYPE, FurnitureDoorBlock::new);
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_DOUBLE = FurnitureUtil.doorDouble(REGISTREE, BLOCK_SET_TYPE, FurnitureDoorBlock::new);
    public static final DeferredBlock<DunmerDeskLeftBlock> DESK_LEFT = FurnitureUtil.desk(REGISTREE, true, DunmerDeskLeftBlock::new);
    public static final DeferredBlock<DunmerDeskRightBlock> DESK_RIGHT = FurnitureUtil.desk(REGISTREE, false, DunmerDeskRightBlock::new);
    public static final DeferredBlock<DunmerPaintingWideBlock> PAINTING_WIDE = FurnitureUtil.paintingWide(REGISTREE, DunmerPaintingWideBlock::new);
    public static final DeferredBlock<DunmerPaintingSmallBlock> PAINTING_SMALL = FurnitureUtil.paintingSmall(REGISTREE, DunmerPaintingSmallBlock::new);
    public static final DeferredBlock<DunmerOvenBlock> OVEN = FurnitureUtil.oven(REGISTREE, DunmerOvenBlock::new);
    public static final DeferredBlock<DunmerChestBlock> CHEST = FurnitureUtil.chest(REGISTREE, DunmerChestBlock::new);
    public static final DeferredBlock<DunmerFloorLightBlock> FLOOR_LIGHT = FurnitureUtil.floorLight(REGISTREE, DunmerFloorLightBlock::new);
    public static final DeferredBlock<DunmerChandelierBlock> CHANDELIER = FurnitureUtil.chandelier(REGISTREE, DunmerChandelierBlock::new);
    public static final DeferredBlock<DunmerShelfBlock> SHELF = FurnitureUtil.shelf(REGISTREE, DunmerShelfBlock::new);
    public static final DeferredBlock<DunmerSofaBlock> SOFA = FurnitureUtil.sofa(REGISTREE, DunmerSofaBlock::new);
    public static final DeferredBlock<DunmerCounterBlock> COUNTER = FurnitureUtil.counter(REGISTREE, DunmerCounterBlock::new);
    public static final DeferredBlock<DunmerWallLightBlock> WALL_LIGHT = FurnitureUtil.wallLight(REGISTREE, DunmerWallLightBlock::new);
    public static final DeferredBlock<DunmerBenchBlock> BENCH = FurnitureUtil.bench(REGISTREE, DunmerBenchBlock::new);
    public static final DeferredBlock<DunmerWardrobeBlock> WARDROBE = FurnitureUtil.wardrobe(REGISTREE, DunmerWardrobeBlock::new);
    public static final DeferredBlock<DunmerTableBlock> TABLE = FurnitureUtil.table(REGISTREE, DunmerTableBlock::new);
    public static final DeferredBlock<StairBlock> STAIRS = FurnitureUtil.stairs(REGISTREE, PLANKS);
    public static final DeferredBlock<SlabBlock> SLAB = FurnitureUtil.slab(REGISTREE);
    public static final DeferredBlock<FenceBlock> FENCE = FurnitureUtil.fence(REGISTREE);
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE = FurnitureUtil.fenceGate(REGISTREE, WOOD_TYPE);
    public static final DeferredBlock<TrapDoorBlock> TRAPDOOR = FurnitureUtil.trapdoor(REGISTREE, BLOCK_SET_TYPE);
    public static final DeferredBlock<PressurePlateBlock> PRESSURE_PLATE = FurnitureUtil.pressurePlate(REGISTREE, BLOCK_SET_TYPE);
    public static final FurnitureUtil.SignPair<CeilingHangingSignBlock, WallHangingSignBlock> HANGING_SIGN = FurnitureUtil.hangingSign(REGISTREE, WOOD_TYPE);
    public static final FurnitureUtil.SignPair<StandingSignBlock, WallSignBlock> SIGN = FurnitureUtil.sign(REGISTREE, WOOD_TYPE);

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, () -> new ItemStack(BED_SINGLE.value()));

    public DunmerFurnitureSet(IEventBus modBus) {
        FurnitureUtil.registerEvents(modBus, REGISTREE, WOOD_TYPE);
    }
}
