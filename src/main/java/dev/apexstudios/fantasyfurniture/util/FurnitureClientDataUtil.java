package dev.apexstudios.fantasyfurniture.util;

import com.mojang.math.Quadrant;
import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.apexcore.lib.data.pack.PackGenerator;
import dev.apexstudios.apexcore.lib.data.provider.LanguageProvider;
import dev.apexstudios.apexcore.lib.data.provider.model.ModelProvider;
import dev.apexstudios.apexcore.lib.multiblock.MultiBlock;
import dev.apexstudios.fantasyfurniture.block.TableBlock;
import dev.apexstudios.fantasyfurniture.block.property.CounterConnection;
import dev.apexstudios.fantasyfurniture.block.property.ShelfConnection;
import dev.apexstudios.fantasyfurniture.block.property.SofaConnection;
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
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
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
        provider.fromRegistree(context.registree());

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.PLANKS, blockModels::createTrivialCube);
        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.BRICKS, blockModels::createTrivialCube);
        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.WOOL, blockModels::createTrivialCube);

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.CARPET, block -> {
            var wool = context.registree().getValueOrThrow(Registries.BLOCK, FurnitureUtil.Names.WOOL);
            var variant = BlockModelGenerators.plainVariant(TexturedModel.CARPET.get(wool).create(block, blockModels.modelOutput));
            blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, variant));
        });

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.DRESSER, block -> {
            blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                    .with(createMultiBlockPropertyDispatch(block, index -> ModelLocationUtils.getModelLocation(block, index == 0 ? "_left" : "_right")))
                    .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
            );

            registerSimpleBlockItemModel(block, blockModels);
        });

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.STOOL, blockModels::createNonTemplateHorizontalBlock);
        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.CUSHION, blockModels::createNonTemplateHorizontalBlock);
        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.LOCKBOX, blockModels::createNonTemplateHorizontalBlock);
        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.DRAWER, blockModels::createNonTemplateHorizontalBlock);

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.CHAIR, block -> {
            blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                    .with(createMultiBlockPropertyDispatch(block, index -> ModelLocationUtils.getModelLocation(block, index == 0 ? "_bottom" : "_top")))
                    .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
            );

            registerSimpleBlockItemModel(block, blockModels);
        });

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.BOOKSHELF, block -> {
            blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                    .with(createMultiBlockPropertyDispatch(block, index -> ModelLocationUtils.getModelLocation(block, switch (index) {
                        case 0 -> "_bottom_left";
                        case 1 -> "_bottom_right";
                        case 2 -> "_top_left";
                        default -> "_top_right";
                    })))
                    .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
            );

            registerSimpleBlockItemModel(block, blockModels);
        });

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.BED_SINGLE, block -> {
            blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                    .with(PropertyDispatch.initial(BedBlock.PART)
                            .select(BedPart.HEAD, BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, "_top")))
                            .select(BedPart.FOOT, BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, "_bottom")))
                    )
                    .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING_ALT)
            );

            registerSimpleBlockItemModel(block, blockModels);
        });

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.BED_DOUBLE, block -> {
            blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                    .with(createMultiBlockPropertyDispatch(block, index -> ModelLocationUtils
                            .getModelLocation(block, switch (index) {
                                case 1 -> "_top_left";
                                case 2 -> "_top_right";
                                case 3 -> "_bottom_right";
                                default -> "_bottom_left";
                            })
                    )).with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING_ALT)
            );

            registerSimpleBlockItemModel(block, blockModels);
        });

        // TODO: Add optional pack to restore 3d door item models
        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.DOOR_SINGLE, block -> createDoorModel(block, blockModels));
        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.DOOR_DOUBLE, block -> createDoorModel(block, blockModels));

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.DESK_LEFT, block -> {
            blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                    .with(createMultiBlockPropertyDispatch(block, index -> ModelLocationUtils.getModelLocation(block,  index == 0 ? "_left" : "_right")))
                    .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
            );

            registerSimpleBlockItemModel(block, blockModels);
        });

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.DESK_RIGHT, block -> {
            blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                    .with(createMultiBlockPropertyDispatch(block, index -> ModelLocationUtils.getModelLocation(block,  index == 0 ? "_left" : "_right")))
                    .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
            );

            registerSimpleBlockItemModel(block, blockModels);
        });

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.PAINTING_WIDE, block -> {
            blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                    .with(createMultiBlockPropertyDispatch(block, index -> ModelLocationUtils.getModelLocation(block, index == 0 ? "_left" : "_right")))
                    .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
            );

            registerSimpleBlockItemModel(block, blockModels);
        });

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.PAINTING_SMALL, blockModels::createNonTemplateHorizontalBlock);
        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.OVEN, blockModels::createNonTemplateHorizontalBlock);

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.CHEST, block -> {
            blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                    .with(createMultiBlockPropertyDispatch(block, index -> ModelLocationUtils.getModelLocation(block, index == 0 ? "_left" : "_right")))
                    .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
            );

            registerSimpleBlockItemModel(block, blockModels);
        });

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.FLOOR_LIGHT, block -> {
            blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                    .with(createMultiBlockPropertyDispatch(block, index -> ModelLocationUtils.getModelLocation(block, index == 0 ? "_bottom" : "_top")))
                    .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
            );

            registerSimpleBlockItemModel(block, blockModels);
        });

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.CHANDELIER, blockModels::createNonTemplateModelBlock);

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.SHELF, block -> {
            blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                    .with(PropertyDispatch.initial(ShelfConnection.PROPERTY).generate(connection -> BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, connection.getModelSuffix()))))
                    .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
            );

            blockModels.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block, ShelfConnection.NONE.getModelSuffix()));
        });

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.SOFA, block -> {
            blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                    .with(PropertyDispatch.initial(SofaConnection.PROPERTY).generate(connection -> BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, connection.getModelSuffix()))))
                    .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
            );

            blockModels.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block, SofaConnection.NONE.getModelSuffix()));
        });

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.COUNTER, block -> {
            blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                    .with(PropertyDispatch.initial(CounterConnection.PROPERTY).generate(connection -> BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, connection.getModelSuffix()))))
                    .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
            );

            blockModels.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block, CounterConnection.NONE.getModelSuffix()));
        });

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.WALL_LIGHT, blockModels::createNonTemplateModelBlock); // TODO

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.BENCH, block -> {
            blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                    .with(createMultiBlockPropertyDispatch(block, index -> ModelLocationUtils.getModelLocation(block, index == 0 ? "_left" : "_right")))
                    .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
            );

            registerSimpleBlockItemModel(block, blockModels);
        });

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.WARDROBE, block -> {
            blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                    .with(createMultiBlockPropertyDispatch(block, index -> ModelLocationUtils.getModelLocation(block, switch (index) {
                        case 0 -> "_bottom_left";
                        case 1 -> "_bottom_right";
                        case 2 -> "_middle_left";
                        case 3 -> "_middle_right";
                        case 4 -> "_top_left";
                        default -> "_top_right";
                    })))
                    .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING)
            );

            registerSimpleBlockItemModel(block, blockModels);
        });

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.TABLE, block -> blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
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
                            .with(variant -> variant.withYRot(switch (rotation) {
                                case CLOCKWISE_90 -> Quadrant.R90;
                                case CLOCKWISE_180 -> Quadrant.R180;
                                case COUNTERCLOCKWISE_90 -> Quadrant.R270;
                                default -> Quadrant.R0;
                            }));
                }))
        ));

        FurnitureUtil.Names.block(context.registree(), FurnitureUtil.Names.HANGING_SIGN, block -> {
            var hangingSign = context.registree().getValueOrThrow(Registries.BLOCK, FurnitureUtil.Names.WALL_HANGING_SIGN);
            blockModels.createHangingSign(context.family().getBaseBlock(), block, hangingSign);
        });

        blockModels.familyWithExistingFullBlock(context.family().getBaseBlock()).generateFor(context.family());
    }

    static void createDoorModel(Block block, BlockModelGenerators blockModels) {
        var leftBottomClosed = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, "_left_bottom_closed"));
        var leftBottomOpen = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, "_left_bottom_open"));
        var rightBottomClosed = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, "_right_bottom_closed"));
        var rightBottomOpen = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, "_right_bottom_open"));
        var leftTopClosed = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, "_left_top_closed"));
        var leftTopOpen = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, "_left_top_open"));
        var rightTopClosed = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, "_right_top_closed"));
        var rightTopOpen = BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(block, "_right_top_open"));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createDoor(block, leftBottomClosed, leftBottomOpen, rightBottomClosed, rightBottomOpen, leftTopClosed, leftTopOpen, rightTopClosed, rightTopOpen));
        blockModels.registerSimpleFlatItemModel(block.asItem());
    }

    static void registerSimpleBlockItemModel(Block block, BlockModelGenerators blockModels) {
        blockModels.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block.asItem()));
    }

    static PropertyDispatch<MultiVariant> createMultiBlockPropertyDispatch(Block block, Int2ObjectFunction<ResourceLocation> modelLookup) {
        var multiBlock = (MultiBlock) block;
        var property = multiBlock.getMultiBlockProperty();
        return PropertyDispatch.initial(property).generate(index -> BlockModelGenerators.plainVariant(modelLookup.apply(index)));
    }

    static void registerLanguage(FurnitureDataUtil.DataGenContext context, LanguageProvider provider) {
        FurnitureUtil.Names.creativeModeTab(context.registree(), key -> provider.addCreativeModeTab(key, "Fantasy's Furniture - " + context.englishName()));

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
        FurnitureUtil.Names.block(context.registree(), name, block -> provider.add(block, context.englishName() + ' ' + englishName));
    }
}
