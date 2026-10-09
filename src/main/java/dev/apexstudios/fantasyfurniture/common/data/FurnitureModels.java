package dev.apexstudios.fantasyfurniture.common.data;

import com.mojang.math.Quadrant;
import dev.apexstudios.apexcore.api.multiblock.MultiBlock;
import dev.apexstudios.fantasyfurniture.common.block.TableBlock;
import dev.apexstudios.fantasyfurniture.common.block.property.CounterConnection;
import dev.apexstudios.fantasyfurniture.common.block.property.ShelfConnection;
import dev.apexstudios.fantasyfurniture.common.block.property.SofaConnection;
import it.unimi.dsi.fastutil.ints.Int2ObjectFunction;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.stream.Collectors;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.properties.BedPart;

public interface FurnitureModels {
    static void createWardrobeModel(Block block, BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(multiBlockVariantGenerator(block, index -> switch (index) {
            case 0 -> "_bottom_left";
            case 1 -> "_bottom_right";
            case 2 -> "_middle_left";
            case 3 -> "_middle_right";
            case 4 -> "_top_left";
            default -> "_top_right";
        }).with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING));
    }

    static void createWallLightModel(Block block, BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(MultiVariantGenerator
                .dispatch(block, BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block)))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );
    }

    static void createCounterModel(Block block, BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                .with(PropertyDispatch.initial(CounterConnection.PROPERTY).generate(connection -> BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, connection.getModelSuffix()))))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );
    }

    static void createSofaModel(Block block, BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                .with(PropertyDispatch.initial(SofaConnection.PROPERTY).generate(connection -> BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, connection.getModelSuffix()))))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );
    }

    static void createShelfModel(Block block, BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                .with(PropertyDispatch.initial(ShelfConnection.PROPERTY).generate(connection -> BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, connection.getModelSuffix()))))
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
        );
    }

    static void createBedDoubleModel(Block block, BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(multiBlockVariantGenerator(block, index -> switch (index) {
            case 1 -> "_top_left";
            case 2 -> "_top_right";
            case 3 -> "_bottom_right";
            default -> "_bottom_left";
        }).with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING_ALT));
    }

    static void createBedSingleModel(Block block, BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                .with(PropertyDispatch.initial(BedBlock.PART)
                        .select(BedPart.HEAD, BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, "_top")))
                        .select(BedPart.FOOT, BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, "_bottom")))
                )
                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING_ALT)
        );
    }

    static void createBookshelfModel(Block block, BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(multiBlockVariantGenerator(block, index -> switch (index) {
            case 0 -> "_bottom_left";
            case 1 -> "_bottom_right";
            case 2 -> "_top_left";
            default -> "_top_right";
        }).with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING));
    }

    static void createTableModel(Block block, BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                .with(PropertyDispatch.initial(HorizontalDirectionalBlock.FACING, TableBlock.NORTH, TableBlock.EAST, TableBlock.SOUTH, TableBlock.WEST).generate((facing, north, east, south, west) -> {
                    var connections = EnumSet.noneOf(Direction.class);
                    var facingForConnection = TableBlock.getFacingForConnection(facing);
                    var rotation = switch (facingForConnection) {
                        case EAST -> Rotation.CLOCKWISE_90;
                        case SOUTH -> Rotation.CLOCKWISE_180;
                        case WEST -> Rotation.COUNTERCLOCKWISE_90;
                        default -> Rotation.NONE;
                    };

                    if(north)
                        connections.add(Direction.NORTH);
                    if(east)
                        connections.add(Direction.EAST);
                    if(south)
                        connections.add(Direction.SOUTH);
                    if(west)
                        connections.add(Direction.WEST);

                    var connectionId = connections.stream().map(rotation::rotate).sorted(Comparator.comparingInt(connection -> switch (connection) {
                        case NORTH -> 0;
                        case EAST -> 1;
                        case SOUTH -> 2;
                        case WEST -> 3;
                        default -> -1;
                    })).map(Direction::getSerializedName).map(String::toLowerCase).map(str -> str.substring(0, 1)).collect(Collectors.joining());

                    var suffix = connectionId.isBlank() ? "" : '_' + connectionId;
                    return BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, suffix))
                            .with(variant -> variant.withYRot(switch (facing) {
                                case EAST -> Quadrant.R90;
                                case SOUTH -> Quadrant.R180;
                                case WEST -> Quadrant.R270;
                                default -> Quadrant.R0;
                            }));
                }))
        );
    }

    static void createLeftRightModel(Block block, BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(multiBlockVariantGenerator(block, index -> switch (index) {
            case 0 -> "_left";
            default -> "_right";
        }).with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING));
    }

    static void createBottomTopModel(Block block, BlockModelGenerators blockModels) {
        blockModels.blockStateOutput.accept(multiBlockVariantGenerator(block, index -> switch (index) {
            case 0 -> "_bottom";
            default -> "_top";
        }).with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING));
    }

    static void createCarpetModel(Block block, Block wool, BlockModelGenerators blockModels) {
        var variant = BlockModelGenerators.plainVariant(TexturedModel.CARPET.get(wool).create(block, blockModels.modelOutput));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, variant));
    }

    static void createDoorModel(Block block, BlockModelGenerators blockModels) {
        var bottomClosed = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, "_bottom_closed"));
        var bottomOpen = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, "_bottom_open"));
        var topClosed = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, "_top_closed"));
        var topOpen = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, "_top_open"));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createDoor(block, bottomClosed, bottomOpen, bottomOpen, bottomClosed, topClosed, topOpen, topOpen, topClosed));
    }

    static void registerSimpleBlockItemModel(Block block, BlockModelGenerators blockModels) {
        blockModels.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block.asItem()));
    }

    static MultiVariantGenerator multiBlockVariantGenerator(Block block, Int2ObjectFunction<String> suffixLookup) {
        return MultiVariantGenerator.dispatch(block)
                .with(multiBlockPropertyDispatch(block, suffixLookup));
    }

    static PropertyDispatch<MultiVariant> multiBlockPropertyDispatch(Block block, Int2ObjectFunction<String> suffixLookup) {
        return PropertyDispatch.initial(((MultiBlock) block).getMultiBlockProperty())
                .generate(index -> BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, suffixLookup.apply(index))));
    }
}
