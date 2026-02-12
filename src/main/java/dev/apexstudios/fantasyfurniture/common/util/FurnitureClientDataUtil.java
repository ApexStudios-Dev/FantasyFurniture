package dev.apexstudios.fantasyfurniture.common.util;

import com.mojang.math.Quadrant;
import dev.apexstudios.apexcore.api.data.ProviderTypes;
import dev.apexstudios.apexcore.api.data.pack.PackGenerator;
import dev.apexstudios.apexcore.api.data.provider.LanguageProvider;
import dev.apexstudios.apexcore.api.data.provider.model.ModelProvider;
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

public interface FurnitureClientDataUtil {
    static void registerDataGen(FurnitureDataUtil.DataGenContext context, PackGenerator<?> pack) {
        pack.providing(ProviderTypes.MODELS, ($, provider) -> registerModels(context, provider))
                .providing(ProviderTypes.LANGUAGE, ($, provider) -> registerLanguage(context, provider));
    }

    static void registerModels(FurnitureDataUtil.DataGenContext context, ModelProvider provider) {
        var blockModels = provider.blockModels();
        provider.knownBlocks(() -> context.registree().blocks().holders());
        provider.knownItems(() -> context.registree().items().holders());

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.PLANKS, blockModels::createTrivialCube);
        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.BRICKS, blockModels::createTrivialCube);
        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.WOOL, blockModels::createTrivialCube);

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.CARPET, block -> {
            var wool = context.registree().blocks().getValueOrThrow(FurnitureUtil.Names.WOOL);
            createCarpetModel(block, wool, blockModels);
        });

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.DRESSER, block -> {
            createLeftRightModel(block, blockModels);
            registerSimpleBlockItemModel(block, blockModels);
        });

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.STOOL, blockModels::createNonTemplateHorizontalBlock);
        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.CUSHION, blockModels::createNonTemplateHorizontalBlock);
        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.LOCKBOX, blockModels::createNonTemplateHorizontalBlock);
        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.DRAWER, blockModels::createNonTemplateHorizontalBlock);

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.CHAIR, block -> {
            createBottomTopModel(block, blockModels);
            registerSimpleBlockItemModel(block, blockModels);
        });

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.BOOKSHELF, block -> {
            createBookshelfModel(block, blockModels);
            registerSimpleBlockItemModel(block, blockModels);
        });

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.BED_SINGLE, block -> {
            createBedSingleModel(block, blockModels);
            registerSimpleBlockItemModel(block, blockModels);
        });

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.BED_DOUBLE, block -> {
            createBedDoubleModel(block, blockModels);
            registerSimpleBlockItemModel(block, blockModels);
        });

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.DOOR_SINGLE, block -> {
            createDoorModel(block, blockModels);
            blockModels.registerSimpleFlatItemModel(block.asItem());
        });

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.DOOR_DOUBLE, block -> {
            createDoorModel(block, blockModels);
            blockModels.registerSimpleFlatItemModel(block.asItem());
        });

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.DESK_LEFT, block -> {
            createLeftRightModel(block, blockModels);
            registerSimpleBlockItemModel(block, blockModels);
        });

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.DESK_RIGHT, block -> {
            createLeftRightModel(block, blockModels);
            registerSimpleBlockItemModel(block, blockModels);
        });

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.PAINTING_WIDE, block -> {
            createLeftRightModel(block, blockModels);
            registerSimpleBlockItemModel(block, blockModels);
        });

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.PAINTING_SMALL, blockModels::createNonTemplateHorizontalBlock);
        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.OVEN, blockModels::createNonTemplateHorizontalBlock);

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.CHEST, block -> {
            createLeftRightModel(block, blockModels);
            registerSimpleBlockItemModel(block, blockModels);
        });

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.FLOOR_LIGHT, block -> {
            createBottomTopModel(block, blockModels);
            registerSimpleBlockItemModel(block, blockModels);
        });

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.CHANDELIER, blockModels::createNonTemplateModelBlock);

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.SHELF, block -> {
            createShelfModel(block, blockModels);
            blockModels.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block, ShelfConnection.NONE.getModelSuffix()));
        });

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.SOFA, block -> {
            createSofaModel(block, blockModels);
            blockModels.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block, SofaConnection.NONE.getModelSuffix()));
        });

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.COUNTER, block -> {
            createCounterModel(block, blockModels);
            blockModels.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block, CounterConnection.NONE.getModelSuffix()));
        });

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.WALL_LIGHT, block -> createWallLightModel(block, blockModels));

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.BENCH, block -> {
            createLeftRightModel(block, blockModels);
            registerSimpleBlockItemModel(block, blockModels);
        });

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.WARDROBE, block -> {
            createWardrobeModel(block, blockModels);
            registerSimpleBlockItemModel(block, blockModels);
        });

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.TABLE, block -> createTableModel(block, blockModels));

        context.block(FurnitureDataUtil.DataType.MODEL, FurnitureUtil.Names.HANGING_SIGN, block -> {
            var hangingSign = context.registree().blocks().getValueOrThrow(FurnitureUtil.Names.WALL_HANGING_SIGN);
            blockModels.createHangingSign(context.family().getBaseBlock(), block, hangingSign);
        });

        blockModels.familyWithExistingFullBlock(context.family().getBaseBlock()).generateFor(context.family());
    }

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

    static void registerLanguage(FurnitureDataUtil.DataGenContext context, LanguageProvider provider) {
        FurnitureUtil.Names.creativeModeTab(context.registree().creativeModeTabs(), key -> provider.addCreativeModeTab(key, "Fantasy's Furniture - " + context.englishName()));

        registerLanguage(context, FurnitureUtil.Names.PLANKS, "Planks", provider);
        registerLanguage(context, FurnitureUtil.Names.BRICKS, "Bricks", provider);
        registerLanguage(context, FurnitureUtil.Names.WOOL, "Wool", provider);
        registerLanguage(context, FurnitureUtil.Names.CARPET, "Carpet", provider);
        registerLanguage(context, FurnitureUtil.Names.DRESSER, "Dresser", provider);
        registerLanguage(context, FurnitureUtil.Names.STOOL, "Stool", provider);
        registerLanguage(context, FurnitureUtil.Names.CUSHION, "Cushion", provider);
        registerLanguage(context, FurnitureUtil.Names.LOCKBOX, "Lockbox", provider);
        registerLanguage(context, FurnitureUtil.Names.DRAWER, "Drawer", provider);
        registerLanguage(context, FurnitureUtil.Names.CHAIR, "Chair", provider);
        registerLanguage(context, FurnitureUtil.Names.BOOKSHELF, "Bookshelf", provider);
        registerLanguage(context, FurnitureUtil.Names.BED_SINGLE, "Bed Single", provider);
        registerLanguage(context, FurnitureUtil.Names.BED_DOUBLE, "Bed Double", provider);
        registerLanguage(context, FurnitureUtil.Names.DOOR_SINGLE, "Door Single", provider);
        registerLanguage(context, FurnitureUtil.Names.DOOR_DOUBLE, "Door Double", provider);
        registerLanguage(context, FurnitureUtil.Names.DESK_LEFT, "Desk Left", provider);
        registerLanguage(context, FurnitureUtil.Names.DESK_RIGHT, "Desk Right", provider);
        registerLanguage(context, FurnitureUtil.Names.PAINTING_WIDE, "Painting Wide", provider);
        registerLanguage(context, FurnitureUtil.Names.PAINTING_SMALL, "Painting Small", provider);
        registerLanguage(context, FurnitureUtil.Names.OVEN, "Oven", provider);
        registerLanguage(context, FurnitureUtil.Names.CHEST, "Chest", provider);
        registerLanguage(context, FurnitureUtil.Names.FLOOR_LIGHT, "Floor Light", provider);
        registerLanguage(context, FurnitureUtil.Names.CHANDELIER, "Chandelier", provider);
        registerLanguage(context, FurnitureUtil.Names.SHELF, "Shelf", provider);
        registerLanguage(context, FurnitureUtil.Names.SOFA, "Sofa", provider);
        registerLanguage(context, FurnitureUtil.Names.COUNTER, "Counter", provider);
        registerLanguage(context, FurnitureUtil.Names.WALL_LIGHT, "Wall Light", provider);
        registerLanguage(context, FurnitureUtil.Names.BENCH, "Bench", provider);
        registerLanguage(context, FurnitureUtil.Names.WARDROBE, "Wardrobe", provider);
        registerLanguage(context, FurnitureUtil.Names.TABLE, "Table", provider);
        registerLanguage(context, FurnitureUtil.Names.STAIRS, "Stairs", provider);
        registerLanguage(context, FurnitureUtil.Names.SLAB, "Slab", provider);
        registerLanguage(context, FurnitureUtil.Names.FENCE, "Fence", provider);
        registerLanguage(context, FurnitureUtil.Names.FENCE_GATE, "Fence Gate", provider);
        registerLanguage(context, FurnitureUtil.Names.TRAPDOOR, "Trapdoor", provider);
        registerLanguage(context, FurnitureUtil.Names.PRESSURE_PLATE, "Pressure Plate", provider);
        registerLanguage(context, FurnitureUtil.Names.BUTTON, "Button", provider);
        registerLanguage(context, FurnitureUtil.Names.HANGING_SIGN, "Hanging Sign", provider);
        registerLanguage(context, FurnitureUtil.Names.WALL_HANGING_SIGN, "Wall Hanging Sign", provider);
        registerLanguage(context, FurnitureUtil.Names.SIGN, "Sign", provider);
        registerLanguage(context, FurnitureUtil.Names.WALL_SIGN, "Wall Sign", provider);
    }

    private static void registerLanguage(FurnitureDataUtil.DataGenContext context, String name, String englishName, LanguageProvider provider) {
        context.block(FurnitureDataUtil.DataType.LANGUAGE, name, block -> provider.add(block, context.englishName() + ' ' + englishName));
    }
}
