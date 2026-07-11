package dev.apexstudios.fantasyfurniture.necrolord.common;

import dev.apexstudios.apexcore.api.util.WoodTypeBuilder;
import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.block.FurnitureDoorBlock;
import dev.apexstudios.fantasyfurniture.common.ctm.CtmPacks;
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
import dev.apexstudios.registree.holder.DeferredParticle;
import java.util.function.Supplier;
import net.minecraft.client.particle.FlameParticle;
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

    public static final Supplier<WoodType> WOOD_TYPE = WoodTypeBuilder.builder()
            .copy(WoodType.OAK)
            .blockSetType(blockSet -> blockSet
                    .copy(BlockSetType.STONE)
            )
            .build(ID + ":necrolord");

    public static final Supplier<BlockSetType> BLOCK_SET_TYPE = () -> WOOD_TYPE.get().setType();

    public static final DeferredBlock<Block> BRICKS = FurnitureUtil.bricks(REGISTREE, Block::new).register();
    public static final DeferredBlock<Block> WOOL = FurnitureUtil.wool(REGISTREE, Block::new).register();
    public static final DeferredBlock<CarpetBlock> CARPET = FurnitureUtil.carpet(REGISTREE, CarpetBlock::new).register();
    public static final DeferredBlock<NecrolordDresserBlock> DRESSER = FurnitureUtil.dresser(REGISTREE, NecrolordDresserBlock::new).register();
    public static final DeferredBlock<NecrolordStoolBlock> STOOL = FurnitureUtil.stool(REGISTREE, NecrolordStoolBlock::new).register();
    public static final DeferredBlock<NecrolordCushionBlock> CUSION = FurnitureUtil.cushion(REGISTREE, NecrolordCushionBlock::new).register();
    public static final DeferredBlock<NecrolordLockBoxBlock> LOCKBOX = FurnitureUtil.lockbox(REGISTREE, NecrolordLockBoxBlock::new).register();
    public static final DeferredBlock<NecrolordDrawerBlock> DRAWER = FurnitureUtil.drawer(REGISTREE, NecrolordDrawerBlock::new).register();
    public static final DeferredBlock<NecrolordChairBlock> CHAIR = FurnitureUtil.chair(REGISTREE, NecrolordChairBlock::new).register();
    public static final DeferredBlock<NecrolordBookshelfBlock> BOOKSHELF = FurnitureUtil.bookshelf(REGISTREE, NecrolordBookshelfBlock::new).register();
    public static final DeferredBlock<NecrolordBedSingleBlock> BED_SINGLE = FurnitureUtil.bedSingle(REGISTREE, NecrolordBedSingleBlock::new).register();
    public static final DeferredBlock<NecrolordBedDoubleBlock> BED_DOUBLE = FurnitureUtil.bedDouble(REGISTREE, NecrolordBedDoubleBlock::new).register();
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_SINGLE = FurnitureUtil.doorSingle(REGISTREE, BLOCK_SET_TYPE, FurnitureDoorBlock::new).register();
    public static final DeferredBlock<FurnitureDoorBlock> DOOR_DOUBLE = FurnitureUtil.doorDouble(REGISTREE, BLOCK_SET_TYPE, FurnitureDoorBlock::new).register();
    public static final DeferredBlock<NecrolordDeskLeftBlock> DESK_LEFT = FurnitureUtil.desk(REGISTREE, true, NecrolordDeskLeftBlock::new).register();
    public static final DeferredBlock<NecrolordDeskRightBlock> DESK_RIGHT = FurnitureUtil.desk(REGISTREE, false, NecrolordDeskRightBlock::new).register();
    public static final DeferredBlock<NecrolordPaintingWideBlock> PAINTING_WIDE = FurnitureUtil.paintingWide(REGISTREE, NecrolordPaintingWideBlock::new).register();
    public static final DeferredBlock<NecrolordPaintingSmallBlock> PAINTING_SMALL = FurnitureUtil.paintingSmall(REGISTREE, NecrolordPaintingSmallBlock::new).register();
    public static final DeferredBlock<NecrolordOvenBlock> OVEN = FurnitureUtil.oven(REGISTREE, NecrolordOvenBlock::new).register();
    public static final DeferredBlock<NecrolordChestBlock> CHEST = FurnitureUtil.chest(REGISTREE, NecrolordChestBlock::new).register();
    public static final DeferredBlock<NecrolordFloorLightBlock> FLOOR_LIGHT = FurnitureUtil.floorLight(REGISTREE, NecrolordFloorLightBlock::new).register();
    public static final DeferredBlock<NecrolordChandelierBlock> CHANDELIER = FurnitureUtil.chandelier(REGISTREE, NecrolordChandelierBlock::new).register();
    public static final DeferredBlock<NecrolordShelfBlock> SHELF = FurnitureUtil.shelf(REGISTREE, NecrolordShelfBlock::new).register();
    public static final DeferredBlock<NecrolordSofaBlock> SOFA = FurnitureUtil.sofa(REGISTREE, NecrolordSofaBlock::new).register();
    public static final DeferredBlock<NecrolordCounterBlock> COUNTER = FurnitureUtil.counter(REGISTREE, NecrolordCounterBlock::new).register();
    public static final DeferredBlock<NecrolordWallLightBlock> WALL_LIGHT = FurnitureUtil.wallLight(REGISTREE, NecrolordWallLightBlock::new).register();
    public static final DeferredBlock<NecrolordBenchBlock> BENCH = FurnitureUtil.bench(REGISTREE, NecrolordBenchBlock::new).register();
    public static final DeferredBlock<NecrolordWardrobeBlock> WARDROBE = FurnitureUtil.wardrobe(REGISTREE, NecrolordWardrobeBlock::new).register();
    public static final DeferredBlock<NecrolordTableBlock> TABLE = FurnitureUtil.table(REGISTREE, NecrolordTableBlock::new).register();
    public static final DeferredBlock<StairBlock> STAIRS = FurnitureUtil.stairs(REGISTREE, BRICKS).register();
    public static final DeferredBlock<SlabBlock> SLAB = FurnitureUtil.slab(REGISTREE).register();
    public static final DeferredBlock<FenceBlock> FENCE = FurnitureUtil.fence(REGISTREE).register();
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE = FurnitureUtil.fenceGate(REGISTREE, WOOD_TYPE).register();
    public static final DeferredBlock<TrapDoorBlock> TRAPDOOR = FurnitureUtil.trapdoor(REGISTREE, BLOCK_SET_TYPE).register();
    public static final DeferredBlock<PressurePlateBlock> PRESSURE_PLATE = FurnitureUtil.pressurePlate(REGISTREE, BLOCK_SET_TYPE).register();
    public static final FurnitureUtil.SignPair<CeilingHangingSignBlock, WallHangingSignBlock> HANGING_SIGN = FurnitureUtil.hangingSign(REGISTREE, WOOD_TYPE);
    public static final FurnitureUtil.SignPair<StandingSignBlock, WallSignBlock> SIGN = FurnitureUtil.sign(REGISTREE, WOOD_TYPE);

    public static final DeferredParticle<SimpleParticleType, SimpleParticleType> FLAME_PARTICLE = REGISTREE.particle("flame")
            .spriteProvider(() -> () -> FlameParticle.Provider::new)
            .register();

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, () -> new ItemStack(BED_SINGLE.value()));

    public NecrolordFurnitureSet(IEventBus modBus) {
        FantasyFurniture.FURNITURE_MODS.add(ID);
        CtmPacks.register(REGISTREE);
        REGISTREE.register(modBus);
    }
}
