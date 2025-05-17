package dev.apexstudios.fantasyfurniture.dunmer;

import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlock;
import dev.apexstudios.apexcore.lib.util.WoodTypeBuilder;
import dev.apexstudios.fantasyfurniture.block.FurnitureDoorBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerBedDoubleBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerBedSingleBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerBenchBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerBookshelfBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerChairBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerChandelierBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerChestBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerCounterBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerCushionBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerDeskBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerDrawerBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerDresserBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerFloorLightBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerLockBoxBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerOvenBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerPaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerPaintingWideBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerShelfBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerSofaBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerStoolBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerTableBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerWallLightBlock;
import dev.apexstudios.fantasyfurniture.dunmer.block.DunmerWardrobeBlock;
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

@Mod(DunmerFurnitureSet.ID)
public class DunmerFurnitureSet {
    public static final String ID = "fantasyfurniture_dunmer";
    public static final Registree REGISTREE = new Registree(ID);

    public static final WoodType WOOD_TYPE = WoodTypeBuilder.builder()
            .copy(WoodType.OAK)
            .build(ID + ":dunmer");

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
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_SINGLE = FurnitureUtil.doorSingle(REGISTREE, WOOD_TYPE.setType(), FurnitureDoorBlock::new);
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_DOUBLE = FurnitureUtil.doorDouble(REGISTREE, WOOD_TYPE.setType(), FurnitureDoorBlock::new);
    public static final DeferredBlock<DunmerDeskBlock> DESK_LEFT = FurnitureUtil.desk(REGISTREE, true, DunmerDeskBlock::new);
    public static final DeferredBlock<DunmerDeskBlock> DESK_RIGHT = FurnitureUtil.desk(REGISTREE, false, DunmerDeskBlock::new);
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
    public static final DeferredBlock<TrapDoorBlock> TRAPDOOR = FurnitureUtil.trapdoor(REGISTREE, WOOD_TYPE.setType());
    public static final DeferredBlock<PressurePlateBlock> PRESSURE_PLATE = FurnitureUtil.pressurePlate(REGISTREE, WOOD_TYPE.setType());
    public static final FurnitureUtil.SignPair<CeilingHangingSignBlock, WallHangingSignBlock> HANGING_SIGN = FurnitureUtil.hangingSign(REGISTREE, WOOD_TYPE);
    public static final FurnitureUtil.SignPair<StandingSignBlock, WallSignBlock> SIGN = FurnitureUtil.sign(REGISTREE, WOOD_TYPE);

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, BED_SINGLE);

    public DunmerFurnitureSet(IEventBus modBus) {
        FurnitureUtil.registerEvents(modBus, REGISTREE, WOOD_TYPE);
    }
}
