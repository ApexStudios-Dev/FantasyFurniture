package apexstudios.fantasyfurniture.bone;

import apexstudios.fantasyfurniture.bone.block.BoneBedDoubleBlock;
import apexstudios.fantasyfurniture.bone.block.BoneBedSingleBlock;
import apexstudios.fantasyfurniture.bone.block.BoneBenchBlock;
import apexstudios.fantasyfurniture.bone.block.BoneBookshelfBlock;
import apexstudios.fantasyfurniture.bone.block.BoneChairBlock;
import apexstudios.fantasyfurniture.bone.block.BoneChandelierBlock;
import apexstudios.fantasyfurniture.bone.block.BoneChestBlock;
import apexstudios.fantasyfurniture.bone.block.BoneCushionBlock;
import apexstudios.fantasyfurniture.bone.block.BoneDeskBlock;
import apexstudios.fantasyfurniture.bone.block.BoneDrawerBlock;
import apexstudios.fantasyfurniture.bone.block.BoneDresserBlock;
import apexstudios.fantasyfurniture.bone.block.BoneFloorLightBlock;
import apexstudios.fantasyfurniture.bone.block.BoneLockBoxBlock;
import apexstudios.fantasyfurniture.bone.block.BonePaintingSmallBlock;
import apexstudios.fantasyfurniture.bone.block.BonePaintingWideBlock;
import apexstudios.fantasyfurniture.bone.block.BoneShelfBlock;
import apexstudios.fantasyfurniture.bone.block.BoneSofaBlock;
import apexstudios.fantasyfurniture.bone.block.BoneStoolBlock;
import apexstudios.fantasyfurniture.bone.block.BoneTableBlock;
import apexstudios.fantasyfurniture.bone.block.BoneWallLightBlock;
import apexstudios.fantasyfurniture.bone.block.BoneWardrobeBlock;
import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlock;
import dev.apexstudios.apexcore.lib.util.WoodTypeBuilder;
import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.block.CounterBlock;
import dev.apexstudios.fantasyfurniture.block.FurnitureDoorBlock;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;
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
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(SkeletonFurnitureSet.ID)
public final class SkeletonFurnitureSet {
    public static final String ID = FantasyFurniture.ID + "_bone_skeleton";
    public static final Registree REGISTREE = new Registree(ID);

    public static final WoodType WOOD_TYPE = WoodTypeBuilder.builder()
            .copy(WoodType.OAK)
            .blockSetType(blockSet -> blockSet
                    .copy(BlockSetType.STONE)
            )
            .build(ID + ":wood_type");

    public static final DeferredBlock<Block> BRICKS = FurnitureUtil.bricks(REGISTREE, Block::new);
    public static final DeferredBlock<Block> WOOL = FurnitureUtil.wool(REGISTREE, Block::new);
    public static final DeferredBlock<CarpetBlock> CARPET = FurnitureUtil.carpet(REGISTREE, CarpetBlock::new);
    public static final DeferredBlock<BoneDresserBlock> DRESSER = FurnitureUtil.dresser(REGISTREE, BoneDresserBlock::new);
    public static final DeferredBlock<BoneStoolBlock> STOOL = FurnitureUtil.stool(REGISTREE, BoneStoolBlock::new);
    public static final DeferredBlock<BoneCushionBlock> CUSION = FurnitureUtil.cushion(REGISTREE, BoneCushionBlock::new);
    public static final DeferredBlock<BoneLockBoxBlock> LOCKBOX = FurnitureUtil.lockbox(REGISTREE, BoneLockBoxBlock::new);
    public static final DeferredBlock<BoneDrawerBlock> DRAWER = FurnitureUtil.drawer(REGISTREE, BoneDrawerBlock::new);
    public static final DeferredBlock<BoneChairBlock> CHAIR = FurnitureUtil.chair(REGISTREE, BoneChairBlock::new);
    public static final DeferredBlock<BoneBookshelfBlock> BOOKSHELF = FurnitureUtil.bookshelf(REGISTREE, BoneBookshelfBlock::new);
    public static final DeferredBlock<BoneBedSingleBlock> BED_SINGLE = FurnitureUtil.bedSingle(REGISTREE, BoneBedSingleBlock::new);
    public static final DeferredBlock<BoneBedDoubleBlock> BED_DOUBLE = FurnitureUtil.bedDouble(REGISTREE, BoneBedDoubleBlock::new);
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_SINGLE = FurnitureUtil.doorSingle(REGISTREE, WOOD_TYPE.setType(), FurnitureDoorBlock::new);
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_DOUBLE = FurnitureUtil.doorDouble(REGISTREE, WOOD_TYPE.setType(), FurnitureDoorBlock::new);
    public static final DeferredBlock<BoneDeskBlock> DESK_LEFT = FurnitureUtil.desk(REGISTREE, true, BoneDeskBlock::new);
    public static final DeferredBlock<BoneDeskBlock> DESK_RIGHT = FurnitureUtil.desk(REGISTREE, false, BoneDeskBlock::new);
    public static final DeferredBlock<BonePaintingWideBlock> PAINTING_WIDE = FurnitureUtil.paintingWide(REGISTREE, BonePaintingWideBlock::new);
    public static final DeferredBlock<BonePaintingSmallBlock> PAINTING_SMALL = FurnitureUtil.paintingSmall(REGISTREE, BonePaintingSmallBlock::new);
    public static final DeferredBlock<OvenBlock> OVEN = FurnitureUtil.oven(REGISTREE, OvenBlock::new);
    public static final DeferredBlock<BoneChestBlock> CHEST = FurnitureUtil.chest(REGISTREE, BoneChestBlock::new);
    public static final DeferredBlock<BoneFloorLightBlock> FLOOR_LIGHT = FurnitureUtil.floorLight(REGISTREE, BoneFloorLightBlock::new);
    public static final DeferredBlock<BoneChandelierBlock> CHANDELIER = FurnitureUtil.chandelier(REGISTREE, BoneChandelierBlock::new);
    public static final DeferredBlock<BoneShelfBlock> SHELF = FurnitureUtil.shelf(REGISTREE, BoneShelfBlock::new);
    public static final DeferredBlock<BoneSofaBlock> SOFA = FurnitureUtil.sofa(REGISTREE, BoneSofaBlock::new);
    public static final DeferredBlock<CounterBlock> COUNTER = FurnitureUtil.counter(REGISTREE, CounterBlock::new);
    public static final DeferredBlock<BoneWallLightBlock> WALL_LIGHT = FurnitureUtil.wallLight(REGISTREE, BoneWallLightBlock::new);
    public static final DeferredBlock<BoneBenchBlock> BENCH = FurnitureUtil.bench(REGISTREE, BoneBenchBlock::new);
    public static final DeferredBlock<BoneWardrobeBlock> WARDROBE = FurnitureUtil.wardrobe(REGISTREE, BoneWardrobeBlock::new);
    public static final DeferredBlock<BoneTableBlock> TABLE = FurnitureUtil.table(REGISTREE, BoneTableBlock::new);
    public static final DeferredBlock<StairBlock> STAIRS = FurnitureUtil.stairs(REGISTREE, BRICKS);
    public static final DeferredBlock<SlabBlock> SLAB = FurnitureUtil.slab(REGISTREE);
    public static final DeferredBlock<FenceBlock> FENCE = FurnitureUtil.fence(REGISTREE);
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE = FurnitureUtil.fenceGate(REGISTREE, WOOD_TYPE);
    public static final DeferredBlock<TrapDoorBlock> TRAPDOOR = FurnitureUtil.trapdoor(REGISTREE, WOOD_TYPE.setType());
    public static final DeferredBlock<PressurePlateBlock> PRESSURE_PLATE = FurnitureUtil.pressurePlate(REGISTREE, WOOD_TYPE.setType());
    public static final FurnitureUtil.SignPair<CeilingHangingSignBlock, WallHangingSignBlock> HANGING_SIGN = FurnitureUtil.hangingSign(REGISTREE, WOOD_TYPE);
    public static final FurnitureUtil.SignPair<StandingSignBlock, WallSignBlock> SIGN = FurnitureUtil.sign(REGISTREE, WOOD_TYPE);

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, BED_SINGLE);

    public SkeletonFurnitureSet(IEventBus modBus) {
        FurnitureUtil.registerEvents(modBus, REGISTREE, WOOD_TYPE);
    }
}
