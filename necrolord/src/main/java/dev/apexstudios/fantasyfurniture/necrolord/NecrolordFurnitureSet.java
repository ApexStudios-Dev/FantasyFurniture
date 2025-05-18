package dev.apexstudios.fantasyfurniture.necrolord;

import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlock;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredParticleType;
import dev.apexstudios.apexcore.lib.util.WoodTypeBuilder;
import dev.apexstudios.fantasyfurniture.block.FurnitureDoorBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordBedDoubleBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordBedSingleBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordBenchBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordBookshelfBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordChairBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordChandelierBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordChestBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordCounterBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordCushionBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordDeskLeftBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordDeskRightBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordDrawerBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordDresserBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordFloorLightBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordLockBoxBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordOvenBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordPaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordPaintingWideBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordShelfBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordSofaBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordStoolBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordTableBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordWallLightBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordWardrobeBlock;
import dev.apexstudios.fantasyfurniture.util.FurnitureUtil;
import net.minecraft.core.particles.SimpleParticleType;
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

@Mod(NecrolordFurnitureSet.ID)
public class NecrolordFurnitureSet {
    public static final String ID = "fantasyfurniture_necrolord";
    public static final Registree REGISTREE = new Registree(ID);

    public static final WoodType WOOD_TYPE = WoodTypeBuilder.builder()
            .copy(WoodType.OAK)
            .blockSetType(blockSet -> blockSet
                    .copy(BlockSetType.STONE)
            )
            .build(ID + ":necrolord");

    public static final DeferredBlock<Block> BRICKS = FurnitureUtil.bricks(REGISTREE, Block::new);
    public static final DeferredBlock<Block> WOOL = FurnitureUtil.wool(REGISTREE, Block::new);
    public static final DeferredBlock<CarpetBlock> CARPET = FurnitureUtil.carpet(REGISTREE, CarpetBlock::new);
    public static final DeferredBlock<NecrolordDresserBlock> DRESSER = FurnitureUtil.dresser(REGISTREE, NecrolordDresserBlock::new);
    public static final DeferredBlock<NecrolordStoolBlock> STOOL = FurnitureUtil.stool(REGISTREE, NecrolordStoolBlock::new);
    public static final DeferredBlock<NecrolordCushionBlock> CUSION = FurnitureUtil.cushion(REGISTREE, NecrolordCushionBlock::new);
    public static final DeferredBlock<NecrolordLockBoxBlock> LOCKBOX = FurnitureUtil.lockbox(REGISTREE, NecrolordLockBoxBlock::new);
    public static final DeferredBlock<NecrolordDrawerBlock> DRAWER = FurnitureUtil.drawer(REGISTREE, NecrolordDrawerBlock::new);
    public static final DeferredBlock<NecrolordChairBlock> CHAIR = FurnitureUtil.chair(REGISTREE, NecrolordChairBlock::new);
    public static final DeferredBlock<NecrolordBookshelfBlock> BOOKSHELF = FurnitureUtil.bookshelf(REGISTREE, NecrolordBookshelfBlock::new);
    public static final DeferredBlock<NecrolordBedSingleBlock> BED_SINGLE = FurnitureUtil.bedSingle(REGISTREE, NecrolordBedSingleBlock::new);
    public static final DeferredBlock<NecrolordBedDoubleBlock> BED_DOUBLE = FurnitureUtil.bedDouble(REGISTREE, NecrolordBedDoubleBlock::new);
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_SINGLE = FurnitureUtil.doorSingle(REGISTREE, WOOD_TYPE.setType(), FurnitureDoorBlock::new);
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_DOUBLE = FurnitureUtil.doorDouble(REGISTREE, WOOD_TYPE.setType(), FurnitureDoorBlock::new);
    public static final DeferredBlock<NecrolordDeskLeftBlock> DESK_LEFT = FurnitureUtil.desk(REGISTREE, true, NecrolordDeskLeftBlock::new);
    public static final DeferredBlock<NecrolordDeskRightBlock> DESK_RIGHT = FurnitureUtil.desk(REGISTREE, false, NecrolordDeskRightBlock::new);
    public static final DeferredBlock<NecrolordPaintingWideBlock> PAINTING_WIDE = FurnitureUtil.paintingWide(REGISTREE, NecrolordPaintingWideBlock::new);
    public static final DeferredBlock<NecrolordPaintingSmallBlock> PAINTING_SMALL = FurnitureUtil.paintingSmall(REGISTREE, NecrolordPaintingSmallBlock::new);
    public static final DeferredBlock<NecrolordOvenBlock> OVEN = FurnitureUtil.oven(REGISTREE, NecrolordOvenBlock::new);
    public static final DeferredBlock<NecrolordChestBlock> CHEST = FurnitureUtil.chest(REGISTREE, NecrolordChestBlock::new);
    public static final DeferredBlock<NecrolordFloorLightBlock> FLOOR_LIGHT = FurnitureUtil.floorLight(REGISTREE, NecrolordFloorLightBlock::new);
    public static final DeferredBlock<NecrolordChandelierBlock> CHANDELIER = FurnitureUtil.chandelier(REGISTREE, NecrolordChandelierBlock::new);
    public static final DeferredBlock<NecrolordShelfBlock> SHELF = FurnitureUtil.shelf(REGISTREE, NecrolordShelfBlock::new);
    public static final DeferredBlock<NecrolordSofaBlock> SOFA = FurnitureUtil.sofa(REGISTREE, NecrolordSofaBlock::new);
    public static final DeferredBlock<NecrolordCounterBlock> COUNTER = FurnitureUtil.counter(REGISTREE, NecrolordCounterBlock::new);
    public static final DeferredBlock<NecrolordWallLightBlock> WALL_LIGHT = FurnitureUtil.wallLight(REGISTREE, NecrolordWallLightBlock::new);
    public static final DeferredBlock<NecrolordBenchBlock> BENCH = FurnitureUtil.bench(REGISTREE, NecrolordBenchBlock::new);
    public static final DeferredBlock<NecrolordWardrobeBlock> WARDROBE = FurnitureUtil.wardrobe(REGISTREE, NecrolordWardrobeBlock::new);
    public static final DeferredBlock<NecrolordTableBlock> TABLE = FurnitureUtil.table(REGISTREE, NecrolordTableBlock::new);
    public static final DeferredBlock<StairBlock> STAIRS = FurnitureUtil.stairs(REGISTREE, BRICKS);
    public static final DeferredBlock<SlabBlock> SLAB = FurnitureUtil.slab(REGISTREE);
    public static final DeferredBlock<FenceBlock> FENCE = FurnitureUtil.fence(REGISTREE);
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE = FurnitureUtil.fenceGate(REGISTREE, WOOD_TYPE);
    public static final DeferredBlock<TrapDoorBlock> TRAPDOOR = FurnitureUtil.trapdoor(REGISTREE, WOOD_TYPE.setType());
    public static final DeferredBlock<PressurePlateBlock> PRESSURE_PLATE = FurnitureUtil.pressurePlate(REGISTREE, WOOD_TYPE.setType());
    public static final FurnitureUtil.SignPair<CeilingHangingSignBlock, WallHangingSignBlock> HANGING_SIGN = FurnitureUtil.hangingSign(REGISTREE, WOOD_TYPE);
    public static final FurnitureUtil.SignPair<StandingSignBlock, WallSignBlock> SIGN = FurnitureUtil.sign(REGISTREE, WOOD_TYPE);

    public static final DeferredParticleType<SimpleParticleType, SimpleParticleType> FLAME_PARTICLE = REGISTREE.registerSimpleParticle("flame", false);

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, BED_SINGLE);

    public NecrolordFurnitureSet(IEventBus modBus) {
        FurnitureUtil.registerEvents(modBus, REGISTREE, WOOD_TYPE);
    }
}
