package dev.apexstudios.fantasyfurniture.necrolord.common;

import dev.apexstudios.apexcore.api.util.WoodTypeBuilder;
import dev.apexstudios.fantasyfurniture.common.block.FurnitureDoorBlock;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordBedDoubleBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordBedSingleBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordBenchBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordBookshelfBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordChairBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordChandelierBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordChestBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordCounterBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordCushionBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordDeskLeftBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordDeskRightBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordDrawerBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordDresserBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordFloorLightBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordLockBoxBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordOvenBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordPaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordPaintingWideBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordShelfBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordSofaBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordStoolBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordTableBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordWallLightBlock;
import dev.apexstudios.fantasyfurniture.necrolord.common.block.NecrolordWardrobeBlock;
import dev.apexstudios.registree.Registree;
import dev.apexstudios.registree.holder.DeferredParticleType;
import dev.apexstudios.registree.registrar.BlockRegistrar;
import dev.apexstudios.registree.registrar.ParticleTypeRegistrar;
import java.util.function.Supplier;
import net.minecraft.core.particles.SimpleParticleType;
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

@Mod(NecrolordFurnitureSet.ID)
public class NecrolordFurnitureSet {
    public static final String ID = "fantasyfurniture_necrolord";
    public static final Registree REGISTREE = Registree.create(ID);
    public static final BlockRegistrar BLOCKS = REGISTREE.blocks();
    public static final ParticleTypeRegistrar PARTICLE_TYPES = REGISTREE.particleTypes();

    public static final Supplier<WoodType> WOOD_TYPE = WoodTypeBuilder.builder()
            .copy(WoodType.OAK)
            .blockSetType(blockSet -> blockSet
                    .copy(BlockSetType.STONE)
            )
            .build(ID + ":necrolord");

    public static final Supplier<BlockSetType> BLOCK_SET_TYPE = () -> WOOD_TYPE.get().setType();

    public static final DeferredBlock<Block> BRICKS = FurnitureUtil.bricks(BLOCKS, Block::new).register();
    public static final DeferredBlock<Block> WOOL = FurnitureUtil.wool(BLOCKS, Block::new).register();
    public static final DeferredBlock<CarpetBlock> CARPET = FurnitureUtil.carpet(BLOCKS, CarpetBlock::new).register();
    public static final DeferredBlock<NecrolordDresserBlock> DRESSER = FurnitureUtil.dresser(BLOCKS, NecrolordDresserBlock::new).register();
    public static final DeferredBlock<NecrolordStoolBlock> STOOL = FurnitureUtil.stool(BLOCKS, NecrolordStoolBlock::new).register();
    public static final DeferredBlock<NecrolordCushionBlock> CUSION = FurnitureUtil.cushion(BLOCKS, NecrolordCushionBlock::new).register();
    public static final DeferredBlock<NecrolordLockBoxBlock> LOCKBOX = FurnitureUtil.lockbox(BLOCKS, NecrolordLockBoxBlock::new).register();
    public static final DeferredBlock<NecrolordDrawerBlock> DRAWER = FurnitureUtil.drawer(BLOCKS, NecrolordDrawerBlock::new).register();
    public static final DeferredBlock<NecrolordChairBlock> CHAIR = FurnitureUtil.chair(BLOCKS, NecrolordChairBlock::new).register();
    public static final DeferredBlock<NecrolordBookshelfBlock> BOOKSHELF = FurnitureUtil.bookshelf(BLOCKS, NecrolordBookshelfBlock::new).register();
    public static final DeferredBlock<NecrolordBedSingleBlock> BED_SINGLE = FurnitureUtil.bedSingle(BLOCKS, NecrolordBedSingleBlock::new).register();
    public static final DeferredBlock<NecrolordBedDoubleBlock> BED_DOUBLE = FurnitureUtil.bedDouble(BLOCKS, NecrolordBedDoubleBlock::new).register();
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_SINGLE = FurnitureUtil.doorSingle(BLOCKS, BLOCK_SET_TYPE, FurnitureDoorBlock::new).register();
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_DOUBLE = FurnitureUtil.doorDouble(BLOCKS, BLOCK_SET_TYPE, FurnitureDoorBlock::new).register();
    public static final DeferredBlock<NecrolordDeskLeftBlock> DESK_LEFT = FurnitureUtil.desk(BLOCKS, true, NecrolordDeskLeftBlock::new).register();
    public static final DeferredBlock<NecrolordDeskRightBlock> DESK_RIGHT = FurnitureUtil.desk(BLOCKS, false, NecrolordDeskRightBlock::new).register();
    public static final DeferredBlock<NecrolordPaintingWideBlock> PAINTING_WIDE = FurnitureUtil.paintingWide(BLOCKS, NecrolordPaintingWideBlock::new).register();
    public static final DeferredBlock<NecrolordPaintingSmallBlock> PAINTING_SMALL = FurnitureUtil.paintingSmall(BLOCKS, NecrolordPaintingSmallBlock::new).register();
    public static final DeferredBlock<NecrolordOvenBlock> OVEN = FurnitureUtil.oven(BLOCKS, NecrolordOvenBlock::new).register();
    public static final DeferredBlock<NecrolordChestBlock> CHEST = FurnitureUtil.chest(BLOCKS, NecrolordChestBlock::new).register();
    public static final DeferredBlock<NecrolordFloorLightBlock> FLOOR_LIGHT = FurnitureUtil.floorLight(BLOCKS, NecrolordFloorLightBlock::new).register();
    public static final DeferredBlock<NecrolordChandelierBlock> CHANDELIER = FurnitureUtil.chandelier(BLOCKS, NecrolordChandelierBlock::new).register();
    public static final DeferredBlock<NecrolordShelfBlock> SHELF = FurnitureUtil.shelf(BLOCKS, NecrolordShelfBlock::new).register();
    public static final DeferredBlock<NecrolordSofaBlock> SOFA = FurnitureUtil.sofa(BLOCKS, NecrolordSofaBlock::new).register();
    public static final DeferredBlock<NecrolordCounterBlock> COUNTER = FurnitureUtil.counter(BLOCKS, NecrolordCounterBlock::new).register();
    public static final DeferredBlock<NecrolordWallLightBlock> WALL_LIGHT = FurnitureUtil.wallLight(BLOCKS, NecrolordWallLightBlock::new).register();
    public static final DeferredBlock<NecrolordBenchBlock> BENCH = FurnitureUtil.bench(BLOCKS, NecrolordBenchBlock::new).register();
    public static final DeferredBlock<NecrolordWardrobeBlock> WARDROBE = FurnitureUtil.wardrobe(BLOCKS, NecrolordWardrobeBlock::new).register();
    public static final DeferredBlock<NecrolordTableBlock> TABLE = FurnitureUtil.table(BLOCKS, NecrolordTableBlock::new).register();
    public static final DeferredBlock<StairBlock> STAIRS = FurnitureUtil.stairs(BLOCKS, BRICKS).register();
    public static final DeferredBlock<SlabBlock> SLAB = FurnitureUtil.slab(BLOCKS).register();
    public static final DeferredBlock<FenceBlock> FENCE = FurnitureUtil.fence(BLOCKS).register();
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE = FurnitureUtil.fenceGate(BLOCKS, WOOD_TYPE).register();
    public static final DeferredBlock<TrapDoorBlock> TRAPDOOR = FurnitureUtil.trapdoor(BLOCKS, BLOCK_SET_TYPE).register();
    public static final DeferredBlock<PressurePlateBlock> PRESSURE_PLATE = FurnitureUtil.pressurePlate(BLOCKS, BLOCK_SET_TYPE).register();
    public static final FurnitureUtil.SignPair<CeilingHangingSignBlock, WallHangingSignBlock> HANGING_SIGN = FurnitureUtil.hangingSign(REGISTREE, WOOD_TYPE);
    public static final FurnitureUtil.SignPair<StandingSignBlock, WallSignBlock> SIGN = FurnitureUtil.sign(REGISTREE, WOOD_TYPE);

    public static final DeferredParticleType<SimpleParticleType> FLAME_PARTICLE = PARTICLE_TYPES.register("flame");

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, () -> new ItemStack(BED_SINGLE.value()));

    public NecrolordFurnitureSet(IEventBus modBus) {
        FurnitureUtil.registerEvents(modBus, REGISTREE);
    }
}
