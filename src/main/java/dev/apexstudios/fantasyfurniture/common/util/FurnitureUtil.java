package dev.apexstudios.fantasyfurniture.common.util;

import com.google.common.collect.Lists;
import dev.apexstudios.apexcore.api.multiblock.MultiBlock;
import dev.apexstudios.fantasyfurniture.common.FurnitureBlockEntities;
import dev.apexstudios.fantasyfurniture.common.block.BedDoubleBlock;
import dev.apexstudios.fantasyfurniture.common.block.BedSingleBlock;
import dev.apexstudios.fantasyfurniture.common.block.BenchBlock;
import dev.apexstudios.fantasyfurniture.common.block.BookshelfBlock;
import dev.apexstudios.fantasyfurniture.common.block.ChairBlock;
import dev.apexstudios.fantasyfurniture.common.block.ChandelierBlock;
import dev.apexstudios.fantasyfurniture.common.block.ChestBlock;
import dev.apexstudios.fantasyfurniture.common.block.CounterBlock;
import dev.apexstudios.fantasyfurniture.common.block.CushionBlock;
import dev.apexstudios.fantasyfurniture.common.block.DeskBlock;
import dev.apexstudios.fantasyfurniture.common.block.DrawerBlock;
import dev.apexstudios.fantasyfurniture.common.block.DresserBlock;
import dev.apexstudios.fantasyfurniture.common.block.FloorLightBlock;
import dev.apexstudios.fantasyfurniture.common.block.FurnitureDoorBlock;
import dev.apexstudios.fantasyfurniture.common.block.LockBoxBlock;
import dev.apexstudios.fantasyfurniture.common.block.OvenBlock;
import dev.apexstudios.fantasyfurniture.common.block.PaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.common.block.PaintingWideBlock;
import dev.apexstudios.fantasyfurniture.common.block.ShelfBlock;
import dev.apexstudios.fantasyfurniture.common.block.SofaBlock;
import dev.apexstudios.fantasyfurniture.common.block.StoolBlock;
import dev.apexstudios.fantasyfurniture.common.block.TableBlock;
import dev.apexstudios.fantasyfurniture.common.block.WallLightBlock;
import dev.apexstudios.fantasyfurniture.common.block.WardrobeBlock;
import dev.apexstudios.registree.BaseRegistree;
import dev.apexstudios.registree.builder.BlockBuilder;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.minecraft.world.level.block.entity.SmokerBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.transfer.item.WorldlyContainerWrapper;

public interface FurnitureUtil {
    static <TBlock extends Block> BlockBuilder<TBlock> planks(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.PLANKS, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .item();
    }

    static <TBlock extends Block> BlockBuilder<TBlock> bricks(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.BRICKS, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.STONE))
                .item();
    }

    static <TBlock extends Block> BlockBuilder<TBlock> wool(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.WOOL, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.WOOL.white()))
                .item();
    }

    static <TBlock extends CarpetBlock> BlockBuilder<TBlock> carpet(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.CARPET, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.CARPET.white()))
                .item();
    }

    static <TBlock extends DresserBlock> BlockBuilder<TBlock> dresser(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.DRESSER, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.CHEST))
                .properties(properties -> properties.pushReaction(PushReaction.BLOCK))
                .blockEntity(FurnitureBlockEntities.INVENTORY)
                .item();
    }

    static <TBlock extends StoolBlock> BlockBuilder<TBlock> stool(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.STOOL, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .properties(properties -> properties.bounceRestitution(.75F))
                .item();
    }

    static <TBlock extends CushionBlock> BlockBuilder<TBlock> cushion(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.CUSHION, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .properties(properties -> properties.bounceRestitution(.75F))
                .item();
    }

    static <TBlock extends LockBoxBlock> BlockBuilder<TBlock> lockbox(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.LOCKBOX, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.CHEST))
                .properties(properties -> properties.pushReaction(PushReaction.BLOCK))
                .blockEntity(FurnitureBlockEntities.INVENTORY)
                .item();
    }

    static <TBlock extends DrawerBlock> BlockBuilder<TBlock> drawer(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.DRAWER, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.CHEST))
                .properties(properties -> properties.pushReaction(PushReaction.BLOCK))
                .blockEntity(FurnitureBlockEntities.INVENTORY)
                .item();
    }

    static <TBlock extends ChairBlock> BlockBuilder<TBlock> chair(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.CHAIR, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .properties(properties -> properties
                        .bounceRestitution(.75F)
                        .pushReaction(PushReaction.BLOCK)
                )
                .item();
    }

    static <TBlock extends BookshelfBlock> BlockBuilder<TBlock> bookshelf(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.BOOKSHELF, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.CHEST))
                .properties(properties -> properties.pushReaction(PushReaction.BLOCK))
                .blockEntity(FurnitureBlockEntities.BOOKSHELF)
                .item();
    }

    static <TBlock extends BedSingleBlock> BlockBuilder<TBlock> bedSingle(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.BED_SINGLE, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.BED.white()))
                .properties(properties -> properties.pushReaction(PushReaction.BLOCK))
                .poiType(PoiTypes.HOME, BedBlock.PART, BedPart.HEAD)
                .item();
    }

    static <TBlock extends BedDoubleBlock> BlockBuilder<TBlock> bedDouble(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.BED_DOUBLE, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.BED.white()))
                .properties(properties -> properties.pushReaction(PushReaction.BLOCK))
                .poiType(PoiTypes.HOME, BedBlock.PART, BedPart.HEAD)
                .item();
    }

    static <TBlock extends FurnitureDoorBlock> BlockBuilder<TBlock> door(BaseRegistree<?> registree, String identifier, Supplier<BlockSetType> blockSet, BiFunction<BlockBehaviour.Properties, BlockSetType, TBlock> factory) {
        return registree.block(identifier, properties -> factory.apply(properties, blockSet.get()))
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_DOOR))
                .properties(properties -> properties.pushReaction(PushReaction.BLOCK))
                .item();
    }

    static <TBlock extends FurnitureDoorBlock> BlockBuilder<TBlock> doorSingle(BaseRegistree<?> registree, Supplier<BlockSetType> blockSet, BiFunction<BlockBehaviour.Properties, BlockSetType, TBlock> factory) {
        return door(registree, Names.DOOR_SINGLE, blockSet, factory);
    }

    static <TBlock extends FurnitureDoorBlock> BlockBuilder<TBlock> doorDouble(BaseRegistree<?> registree, Supplier<BlockSetType> blockSet, BiFunction<BlockBehaviour.Properties, BlockSetType, TBlock> factory) {
        return door(registree, Names.DOOR_DOUBLE, blockSet, factory);
    }

    static <TBlock extends DeskBlock> BlockBuilder<TBlock> desk(BaseRegistree<?> registree, boolean left, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(left ? Names.DESK_LEFT : Names.DESK_RIGHT, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.CHEST))
                .properties(properties -> properties.pushReaction(PushReaction.BLOCK))
                .blockEntity(FurnitureBlockEntities.INVENTORY)
                .item();
    }

    static <TBlock extends PaintingWideBlock> BlockBuilder<TBlock> paintingWide(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.PAINTING_WIDE, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .properties(properties -> properties
                        .bounceRestitution(.75F)
                        .pushReaction(PushReaction.BLOCK)
                )
                .item();
    }

    static <TBlock extends PaintingSmallBlock> BlockBuilder<TBlock> paintingSmall(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.PAINTING_SMALL, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .item();
    }

    static <TBlock extends OvenBlock> BlockBuilder<TBlock> oven(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.OVEN, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.SMOKER))
                .capability(Capabilities.Item.BLOCK, (level, pos, blockState, blockEntity, side) -> {
                    if(blockEntity == null) {
                        blockEntity = MultiBlock.getBlockEntity(level, pos, blockState);
                    }

                    if(!(blockEntity instanceof SmokerBlockEntity smoker)) {
                        return null;
                    }

                    return new WorldlyContainerWrapper(smoker, side);
                })
                .poiType(PoiTypes.BUTCHER)
                .blockEntity(() -> BlockEntityTypes.SMOKER)
                .item();
    }

    static <TBlock extends ChestBlock> BlockBuilder<TBlock> chest(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.CHEST, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.CHEST))
                .properties(properties -> properties.pushReaction(PushReaction.BLOCK))
                .blockEntity(FurnitureBlockEntities.INVENTORY)
                .item();
    }

    static <TBlock extends FloorLightBlock> BlockBuilder<TBlock> floorLight(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.FLOOR_LIGHT, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .properties(properties -> properties
                        .pushReaction(PushReaction.BLOCK)
                        .lightLevel(blockState -> MultiBlock.getIndex(blockState) == 1 ? 14 : 0)
                )
                .item();
    }

    static <TBlock extends ChandelierBlock> BlockBuilder<TBlock> chandelier(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.CHANDELIER, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .properties(properties -> properties.lightLevel(blockState -> 14))
                .item();
    }

    static <TBlock extends ShelfBlock> BlockBuilder<TBlock> shelf(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.SHELF, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .item();
    }

    static <TBlock extends SofaBlock> BlockBuilder<TBlock> sofa(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.SOFA, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .properties(properties -> properties.bounceRestitution(.75F))
                .item();
    }

    static <TBlock extends CounterBlock> BlockBuilder<TBlock> counter(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.COUNTER, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.CHEST))
                .properties(properties -> properties.pushReaction(PushReaction.BLOCK))
                .blockEntity(FurnitureBlockEntities.INVENTORY)
                .item();
    }

    static <TBlock extends WallLightBlock> BlockBuilder<TBlock> wallLight(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.WALL_LIGHT, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.WALL_TORCH))
                .item();
    }

    static <TBlock extends BenchBlock> BlockBuilder<TBlock> bench(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.BENCH, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .properties(properties -> properties
                        .bounceRestitution(.75F)
                        .pushReaction(PushReaction.BLOCK)
                )
                .item();
    }

    static <TBlock extends WardrobeBlock> BlockBuilder<TBlock> wardrobe(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.WARDROBE, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.CHEST))
                .properties(properties -> properties.pushReaction(PushReaction.BLOCK))
                .blockEntity(FurnitureBlockEntities.INVENTORY)
                .item();
    }

    static <TBlock extends TableBlock> BlockBuilder<TBlock> table(BaseRegistree<?> registree, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block(Names.TABLE, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .item();
    }

    static BlockBuilder<StairBlock> stairs(BaseRegistree<?> registree, Supplier<? extends Block> baseBlock) {
        return registree.block(Names.STAIRS, properties -> new StairBlock(baseBlock.get().defaultBlockState(), properties))
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_STAIRS))
                .item();
    }

    static BlockBuilder<SlabBlock> slab(BaseRegistree<?> registree) {
        return registree.block(Names.SLAB, SlabBlock::new)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_SLAB))
                .item();
    }

    static BlockBuilder<FenceBlock> fence(BaseRegistree<?> registree) {
        return registree.block(Names.FENCE, FenceBlock::new)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_FENCE))
                .item();
    }

    static BlockBuilder<FenceGateBlock> fenceGate(BaseRegistree<?> registree, Supplier<WoodType> woodType) {
        return registree.block(Names.FENCE_GATE, properties -> new FenceGateBlock(woodType.get(), properties))
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_FENCE_GATE))
                .item();
    }

    static BlockBuilder<TrapDoorBlock> trapdoor(BaseRegistree<?> registree, Supplier<BlockSetType> blockSet) {
        return registree.block(Names.TRAPDOOR, properties -> new TrapDoorBlock(blockSet.get(), properties))
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_TRAPDOOR))
                .item();
    }

    static BlockBuilder<PressurePlateBlock> pressurePlate(BaseRegistree<?> registree, Supplier<BlockSetType> blockSet) {
        return registree.block(Names.PRESSURE_PLATE, properties -> new PressurePlateBlock(blockSet.get(), properties))
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PRESSURE_PLATE))
                .item();
    }

    static SignPair<CeilingHangingSignBlock, WallHangingSignBlock> hangingSign(BaseRegistree<?> registree, Supplier<WoodType> woodType) {
        var ceilingSign = registree.block(Names.HANGING_SIGN, properties -> new CeilingHangingSignBlock(woodType.get(), properties))
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_HANGING_SIGN))
                .blockEntity(() -> BlockEntityTypes.HANGING_SIGN)
                .register();

        var wallSign = registree.block(Names.WALL_HANGING_SIGN, properties -> new WallHangingSignBlock(woodType.get(), properties))
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_WALL_HANGING_SIGN))
                .properties(properties -> properties.overrideLootTable(ceilingSign.value().getLootTable()))
                .blockEntity(() -> BlockEntityTypes.HANGING_SIGN)
                .register();

        registree.item(Names.HANGING_SIGN, properties -> new HangingSignItem(ceilingSign.value(), wallSign.value(), properties))
                .properties(properties -> properties
                        .stacksTo(16)
                        .useBlockDescriptionPrefix()
                )
                .register();

        return new SignPair<>(ceilingSign, wallSign);
    }

    static SignPair<StandingSignBlock, WallSignBlock> sign(BaseRegistree<?> registree, Supplier<WoodType> woodType) {
        var standingSign = registree.block(Names.SIGN, properties -> new StandingSignBlock(woodType.get(), properties))
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_SIGN))
                .blockEntity(() -> BlockEntityTypes.SIGN)
                .register();

        var wallSign = registree.block(Names.WALL_SIGN, properties -> new WallSignBlock(woodType.get(), properties))
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_WALL_SIGN))
                .properties(properties -> properties.overrideLootTable(standingSign.value().getLootTable()))
                .blockEntity(() -> BlockEntityTypes.SIGN)
                .register();

        registree.item(Names.SIGN, properties -> new SignItem(standingSign.value(), wallSign.value(), properties))
                .properties(properties -> properties
                        .stacksTo(16)
                        .useBlockDescriptionPrefix()
                )
                .register();

        return new SignPair<>(standingSign, wallSign);
    }

    static ResourceKey<CreativeModeTab> creativeModeTab(BaseRegistree<?> registree, Supplier<ItemStack> displayItem) {
        return registree.creativeModeTab(Names.CREATIVE_MODE_TAB, builder -> builder.icon(displayItem));
    }

    static VoxelShape getShape(VoxelShape shape, BlockState blockState, BlockPos worldPos) {
        return MultiBlock.fixShape(shape, blockState, worldPos);
    }

    static VoxelShape getShape(Map<Direction, VoxelShape> shapes, BlockState blockState, BlockPos worldPos) {
        var facing = blockState.getValueOrElse(HorizontalDirectionalBlock.FACING, Direction.NORTH);
        return getShape(shapes.get(facing), blockState, worldPos);
    }

    record SignPair<TSign extends SignBlock, TWall extends SignBlock>(
            DeferredBlock<TSign> sign,
            DeferredBlock<TWall> wall
    ) {
        public boolean isSign(BlockState blockState) {
            return blockState.is(sign);
        }

        public boolean isWall(BlockState blockState) {
            return blockState.is(wall);
        }

        public boolean isEither(BlockState blockState) {
            return isSign(blockState) || isWall(blockState);
        }
    }

    interface Names {
        String PLANKS = "planks";
        String BRICKS = "bricks";
        String WOOL = "wool";
        String CARPET = "carpet";
        String DRESSER = "dresser";
        String STOOL = "stool";
        String CUSHION = "cushion";
        String LOCKBOX = "lockbox";
        String DRAWER = "drawer";
        String CHAIR = "chair";
        String BOOKSHELF = "bookshelf";
        String BED_SINGLE = "bed_single";
        String BED_DOUBLE = "bed_double";
        String DOOR_SINGLE = "door_single";
        String DOOR_DOUBLE = "door_double";
        String DESK_LEFT = "desk_left";
        String DESK_RIGHT = "desk_right";
        String PAINTING_WIDE = "painting_wide";
        String PAINTING_SMALL = "painting_small";
        String OVEN = "oven";
        String CHEST = "chest";
        String FLOOR_LIGHT = "floor_light";
        String CHANDELIER = "chandelier";
        String SHELF = "shelf";
        String SOFA = "sofa";
        String COUNTER = "counter";
        String WALL_LIGHT = "wall_light";
        String BENCH = "bench";
        String WARDROBE = "wardrobe";
        String TABLE = "table";
        String STAIRS = "stairs";
        String SLAB = "slab";
        String FENCE = "fence";
        String FENCE_GATE = "fence_gate";
        String TRAPDOOR = "trapdoor";
        String PRESSURE_PLATE = "pressure_plate";
        String BUTTON = "button";
        String HANGING_SIGN = "hanging_sign";
        String WALL_HANGING_SIGN = "wall_hanging_sign";
        String SIGN = "sign";
        String WALL_SIGN = "wall_sign";

        String CREATIVE_MODE_TAB = "furniture_set";

        static void block(BaseRegistree<?> registree, String name, Consumer<? super Block> action) {
            var block = registree.getValue(Registries.BLOCK, name);

            if(block != null)
                action.accept(block);
        }

        static Block blockOrThrow(BaseRegistree<?> registree, String name) {
            return registree.getValueOrThrow(Registries.BLOCK, name);
        }

        static void creativeModeTab(BaseRegistree<?> registree, Consumer<ResourceKey<CreativeModeTab>> action) {
            if(registree.isRegistered(Registries.CREATIVE_MODE_TAB, CREATIVE_MODE_TAB)) {
                action.accept(registree.registryKey(Registries.CREATIVE_MODE_TAB, CREATIVE_MODE_TAB));
            }
        }

        static Block[] blocks(BaseRegistree<?> registree, String... names) {
            var blocks = Lists.<Block>newArrayList();

            for(var name : names) {
                block(registree, name, blocks::add);
            }

            return blocks.toArray(Block[]::new);
        }
    }
}
