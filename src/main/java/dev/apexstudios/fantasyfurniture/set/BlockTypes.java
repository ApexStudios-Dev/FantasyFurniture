package dev.apexstudios.fantasyfurniture.set;

import com.mojang.math.Quadrant;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.DoorBlockComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.types.BedBlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.MultiBlockComponent;
import dev.apexstudios.apexcore.lib.data.provider.RecipeProvider;
import dev.apexstudios.apexcore.lib.data.provider.model.ModelUtil;
import dev.apexstudios.apexcore.lib.placement.PlacementRenderEvent;
import dev.apexstudios.apexcore.lib.util.ApexTags;
import dev.apexstudios.apexcore.lib.util.ApexUtil;
import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.FurnitureBlockEntities;
import dev.apexstudios.fantasyfurniture.block.BedDoubleBlock;
import dev.apexstudios.fantasyfurniture.block.BedSingleBlock;
import dev.apexstudios.fantasyfurniture.block.BenchBlock;
import dev.apexstudios.fantasyfurniture.block.BookshelfBlock;
import dev.apexstudios.fantasyfurniture.block.ChairBlock;
import dev.apexstudios.fantasyfurniture.block.ChandelierBlock;
import dev.apexstudios.fantasyfurniture.block.ChestBlock;
import dev.apexstudios.fantasyfurniture.block.CounterBlock;
import dev.apexstudios.fantasyfurniture.block.CushionBlock;
import dev.apexstudios.fantasyfurniture.block.DeskBlock;
import dev.apexstudios.fantasyfurniture.block.DrawerBlock;
import dev.apexstudios.fantasyfurniture.block.DresserBlock;
import dev.apexstudios.fantasyfurniture.block.FloorLightBlock;
import dev.apexstudios.fantasyfurniture.block.LockBoxBlock;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;
import dev.apexstudios.fantasyfurniture.block.PaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.block.PaintingWideBlock;
import dev.apexstudios.fantasyfurniture.block.ShelfBlock;
import dev.apexstudios.fantasyfurniture.block.SofaBlock;
import dev.apexstudios.fantasyfurniture.block.TableBlock;
import dev.apexstudios.fantasyfurniture.block.WallLightBlock;
import dev.apexstudios.fantasyfurniture.block.WardrobeBlock;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureDoorBlockComponentHolder;
import dev.apexstudios.fantasyfurniture.block.base.SeatBlock;
import dev.apexstudios.fantasyfurniture.block.property.CounterConnection;
import dev.apexstudios.fantasyfurniture.block.property.ShelfConnection;
import dev.apexstudios.fantasyfurniture.block.property.SofaConnection;
import dev.apexstudios.fantasyfurniture.set.function.BlockFactory;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationRecipeBuilder;
import dev.apexstudios.fantasyfurniture.station.FurnitureStationSetup;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Objects;
import java.util.stream.Collectors;
import net.minecraft.SharedConstants;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.Direction;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.NeoForgeConditions;
import net.neoforged.neoforge.common.crafting.DifferenceIngredient;
import org.jetbrains.annotations.ApiStatus;

public interface BlockTypes {
    // region: Planks
    BlockType.WithItem<Block, BlockItem> PLANKS = BlockType.withItem(
            "planks",
            BlockFactory.wrapping(Block::new),
            builder -> builder
                    .baseBlock(() -> Blocks.OAK_PLANKS)
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> models.createTrivialCube(block))
                    .translation("Planks")
                    .blockTags(BlockTags.PLANKS)
                    .itemTags(FantasyFurniture.FURNITURE_PLANKS)
                    .recipe((provider, furnitureSet, item) -> SingleItemRecipeBuilder
                            .stonecutting(
                                    DifferenceIngredient.of(provider.tag(ItemTags.PLANKS), provider.tag(FantasyFurniture.FURNITURE_PLANKS)),
                                    RecipeCategory.MISC,
                                    item
                            )
                            .unlockedBy("has_planks", provider.has(item))
                            .save(provider.output(), RecipeProvider.recipeKeyWithPrefix(item, "conversion/"))
                    )
    );
    // endregion

    // region: Bricks
    BlockType.WithItem<Block, BlockItem> BRICKS = BlockType.withItem(
            "bricks",
            BlockFactory.wrapping(Block::new),
            builder -> builder
                    .baseBlock(() -> Blocks.STONE)
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> models.createTrivialCube(block))
                    .translation("Bricks")
                    .blockTags(Tags.Blocks.STONES)
                    .itemTags(FantasyFurniture.FURNITURE_BRICKS)
                    .recipe((provider, furnitureSet, item) -> SingleItemRecipeBuilder
                            .stonecutting(
                                    DifferenceIngredient.of(provider.tag(ItemTags.STONE_CRAFTING_MATERIALS), provider.tag(FantasyFurniture.FURNITURE_BRICKS)),
                                    RecipeCategory.MISC,
                                    item
                            )
                            .unlockedBy("has_bricks", provider.has(item))
                            .save(provider.output(), RecipeProvider.recipeKeyWithPrefix(item, "conversion/"))
                    )
    );
    // endregion

    // region: Wool
    BlockType.WithItem<Block, BlockItem> WOOL = BlockType.withItem(
            "wool",
            BlockFactory.wrapping(Block::new),
            builder -> builder
                    .baseBlock(() -> Blocks.WHITE_WOOL)
                    .blockProperties(properties -> properties.sound(SoundType.WOOL))
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> models.createTrivialCube(block))
                    .translation("Wool")
                    .noMineableTag()
                    .blockTags(BlockTags.WOOL)
                    .itemTags(FantasyFurniture.FURNITURE_WOOL)
                    .recipe((provider, furnitureSet, item) -> SingleItemRecipeBuilder
                            .stonecutting(
                                    DifferenceIngredient.of(provider.tag(ItemTags.WOOL), provider.tag(FantasyFurniture.FURNITURE_WOOL)),
                                    RecipeCategory.MISC,
                                    item
                            )
                            .unlockedBy("has_wool", provider.has(item))
                            .save(provider.output(), RecipeProvider.recipeKeyWithPrefix(item, "conversion/"))
                    )
    );
    // endregion

    // region: Carpet
    BlockType.WithItem<CarpetBlock, BlockItem> CARPET = BlockType.withItem(
            "carpet",
            BlockFactory.wrapping(CarpetBlock::new),
            builder -> builder
                    .baseBlock(() -> Blocks.WHITE_CARPET)
                    .blockProperties(properties -> properties.sound(SoundType.WOOL))
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> carpetModel(block, furnitureSet, models))
                    .translation("Carpet")
                    .noMineableTag()
                    .blockTags(BlockTags.WOOL_CARPETS)
                    .itemTags(ItemTags.WOOL_CARPETS)
                    .recipe((provider, furnitureSet, item) -> provider.carpet(item, furnitureSet.getOrThrow(WOOL)))
    );
    // endregion

    // region: Dresser
    BlockType.WithItem<DresserBlock, BlockItem> DRESSER = BlockType.withItem(
            "dresser",
            BlockFactory.wrapping(DresserBlock::new),
            builder -> builder
                    .baseBlock(() -> Blocks.CHEST)
                    .blockProperties(properties -> properties.pushReaction(PushReaction.BLOCK))
                    .blockEntity(FurnitureBlockEntities.DRESSER)
                    .lootTable((blocks, furnitureSet, block) -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        dresserModel(block, models);
                        ModelUtil.registerBlockItemModel(block, models);
                    })
                    .translation("Dresser")
                    .blockTags(ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED)
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Stool
    BlockType.WithItem<SeatBlock, BlockItem> STOOL = BlockType.withItem(
            "stool",
            BlockFactory.wrapping(SeatBlock::new),
            builder -> builder
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> ModelUtil.horizontalFacingBlock(block, models))
                    .translation("Stool")
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Cushion
    BlockType.WithItem<CushionBlock, BlockItem> CUSHION = BlockType.withItem(
            "cushion",
            BlockFactory.wrapping(CushionBlock::new),
            builder -> builder
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> ModelUtil.horizontalFacingBlock(block, models))
                    .translation("Cushion")
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: LockBox
    BlockType.WithItem<LockBoxBlock, BlockItem> LOCKBOX = BlockType.withItem(
            "lockbox",
            BlockFactory.wrapping(LockBoxBlock::new),
            builder -> builder
                    .baseBlock(() -> Blocks.CHEST)
                    .blockEntity(FurnitureBlockEntities.LOCKBOX)
                    .lootTable((blocks, furnitureSet, block) -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)))
                    .model(() -> (context, models, furnitureSet, block) -> ModelUtil.horizontalFacingBlock(block, models))
                    .translation("Lockbox")
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Drawer
    BlockType.WithItem<DrawerBlock, BlockItem> DRAWER = BlockType.withItem(
            "drawer",
            BlockFactory.wrapping(DrawerBlock::new),
            builder -> builder
                    .baseBlock(() -> Blocks.CHEST)
                    .blockProperties(properties -> properties.pushReaction(PushReaction.BLOCK))
                    .blockEntity(FurnitureBlockEntities.DRAWER)
                    .lootTable((blocks, furnitureSet, block) -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)))
                    .model(() -> (context, models, furnitureSet, block) -> ModelUtil.horizontalFacingBlock(block, models))
                    .translation("Drawer")
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Chair
    BlockType.WithItem<ChairBlock, BlockItem> CHAIR = BlockType.withItem(
            "chair",
            BlockFactory.wrapping(ChairBlock::new),
            builder -> builder
                    .blockProperties(properties -> properties.pushReaction(PushReaction.BLOCK))
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        chairModel(block, models);
                        ModelUtil.registerBlockItemModel(block, models);
                    })
                    .translation("Chair")
                    .blockTags(ApexTags.Blocks.SEAT_ORIGIN_ONLY, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED)
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Bookshelf
    BlockType.WithItem<BookshelfBlock, BlockItem> BOOKSHELF = BlockType.withItem(
            "bookshelf",
            BlockFactory.wrapping(BookshelfBlock::new),
            builder -> builder
                    .baseBlock(() -> Blocks.CHEST)
                    .blockProperties(properties -> properties.pushReaction(PushReaction.BLOCK))
                    .blockEntity(FurnitureBlockEntities.BOOKSHELF)
                    .lootTable((blocks, furnitureSet, block) -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        bookshelfModel(block, models);
                        ModelUtil.registerBlockItemModel(block, models);
                    })
                    .translation("Bookshelf")
                    .blockTags(ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED, BlockTags.ENCHANTMENT_POWER_PROVIDER)
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Bed Single
    BlockType.WithItem<BedSingleBlock, BlockItem> BED_SINGLE = BlockType.withItem(
            "bed_single",
            BlockFactory.wrapping(BedSingleBlock::new),
            builder -> builder
                    .blockProperties(properties -> properties.pushReaction(PushReaction.BLOCK))
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        bedSingleModel(block, models);
                        ModelUtil.registerBlockItemModel(block, models);
                    })
                    .translation("Bed Single")
                    .blockTags(BlockTags.BEDS, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED)
                    .itemTags(ItemTags.BEDS)
                    .recipe(BlockTypes::furnitureStationRecipe)
                    .onRegister(block -> BedBlockComponent.registerPoi(block), true)
    );
    // endregion

    // region: Bed Double
    BlockType.WithItem<BedDoubleBlock, BlockItem> BED_DOUBLE = BlockType.withItem(
            "bed_double",
            BlockFactory.wrapping(BedDoubleBlock::new),
            builder -> builder
                    .blockProperties(properties -> properties.pushReaction(PushReaction.BLOCK))
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        bedDoubleModel(block, models);
                        ModelUtil.registerBlockItemModel(block, models);
                    })
                    .translation("Bed Double")
                    .blockTags(BlockTags.BEDS, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED)
                    .itemTags(ItemTags.BEDS)
                    .recipe(BlockTypes::furnitureStationRecipe)
                    .onRegister(block -> BedBlockComponent.registerPoi(block), true)
    );
    // endregion

    // region: Door Single
    BlockType.WithItem<FurnitureDoorBlockComponentHolder, BlockItem> DOOR_SINGLE = BlockType.withItem(
            "door_single",
            FurnitureDoorBlockComponentHolder::new,
            builder -> builder
                    .baseBlock(() -> Blocks.OAK_DOOR)
                    .blockProperties(properties -> properties.pushReaction(PushReaction.BLOCK))
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        doorModel(block, models);
                        models.registerSimpleFlatItemModel(block.asItem());
                    })
                    .translation("Door Single")
                    .blockTags(BlockTags.WOODEN_DOORS, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED)
                    .itemTags(ItemTags.WOODEN_DOORS)
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Door Double
    BlockType.WithItem<FurnitureDoorBlockComponentHolder, BlockItem> DOOR_DOUBLE = BlockType.withItem(
            "door_double",
            FurnitureDoorBlockComponentHolder::new,
            builder -> builder
                    .baseBlock(() -> Blocks.OAK_DOOR)
                    .blockProperties(properties -> properties.pushReaction(PushReaction.BLOCK))
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        doorModel(block, models);
                        models.registerSimpleFlatItemModel(block.asItem());
                    })
                    .translation("Door Double")
                    .blockTags(BlockTags.WOODEN_DOORS, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED)
                    .itemTags(ItemTags.WOODEN_DOORS)
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Desk Left
    BlockType.WithItem<DeskBlock, BlockItem> DESK_LEFT = BlockType.withItem(
            "desk_left",
            BlockFactory.wrapping(properties -> new DeskBlock(properties, true)),
            builder -> builder
                    .baseBlock(() -> Blocks.CHEST)
                    .blockProperties(properties -> properties.pushReaction(PushReaction.BLOCK))
                    .blockEntity(FurnitureBlockEntities.DESK)
                    .lootTable((blocks, furnitureSet, block) -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        deskModel(block, models);
                        ModelUtil.registerBlockItemModel(block, models);
                    })
                    .translation("Desk Left")
                    .blockTags(ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED)
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Desk Right
    BlockType.WithItem<DeskBlock, BlockItem> DESK_RIGHT = BlockType.withItem(
            "desk_right",
            BlockFactory.wrapping(properties -> new DeskBlock(properties, false)),
            builder -> builder
                    .baseBlock(() -> Blocks.CHEST)
                    .blockProperties(properties -> properties.pushReaction(PushReaction.BLOCK))
                    .blockEntity(FurnitureBlockEntities.DESK)
                    .lootTable((blocks, furnitureSet, block) -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        deskModel(block, models);
                        ModelUtil.registerBlockItemModel(block, models);
                    })
                    .translation("Desk Right")
                    .blockTags(ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED)
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Painting Wide
    BlockType.WithItem<PaintingWideBlock, BlockItem> PAINTING_WIDE = BlockType.withItem(
            "painting_wide",
            BlockFactory.wrapping(PaintingWideBlock::new),
            builder -> builder
                    .blockProperties(properties -> properties.pushReaction(PushReaction.BLOCK))
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        paintingWideModel(block, models);
                        ModelUtil.registerBlockItemModel(block, models);
                    })
                    .translation("Painting Wide")
                    .blockTags(ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED)
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Painting Small
    BlockType.WithItem<PaintingSmallBlock, BlockItem> PAINTING_SMALL = BlockType.withItem(
            "painting_small",
            BlockFactory.wrapping(PaintingSmallBlock::new),
            builder -> builder
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> ModelUtil.horizontalFacingBlock(block, models))
                    .translation("Painting Small")
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Oven
    BlockType.WithItem<OvenBlock, BlockItem> OVEN = BlockType.withItem(
            "oven",
            BlockFactory.wrapping(OvenBlock::new),
            builder -> builder
                    .baseBlock(() -> Blocks.SMOKER)
                    .blockProperties(BlockBehaviour.Properties::noOcclusion)
                    .blockEntity(FurnitureBlockEntities.OVEN)
                    .lootTable((blocks, furnitureSet, block) -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)))
                    .model(() -> (context, models, furnitureSet, block) -> ModelUtil.horizontalFacingBlock(block, models))
                    .translation("Oven")
                    .noMineableTag() // forcefully use the pickaxe tag
                    .blockTags(BlockTags.MINEABLE_WITH_PICKAXE, Tags.Blocks.PLAYER_WORKSTATIONS_FURNACES)
                    .itemTags(Tags.Items.PLAYER_WORKSTATIONS_FURNACES)
                    .recipe(BlockTypes::furnitureStationRecipe)
                    .onRegister(block -> ApexUtil.registerPoiBlockStates(PoiTypes.BUTCHER, block), true)
    );
    // endregion

    // region: Chest
    BlockType.WithItem<ChestBlock, BlockItem> CHEST = BlockType.withItem(
            "chest",
            BlockFactory.wrapping(ChestBlock::new),
            builder -> builder
                    .baseBlock(() -> Blocks.CHEST)
                    .blockProperties(properties -> properties.pushReaction(PushReaction.BLOCK))
                    .blockEntity(FurnitureBlockEntities.CHEST)
                    .lootTable((blocks, furnitureSet, block) -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        chestModel(block, models);
                        ModelUtil.registerBlockItemModel(block, models);
                    })
                    .translation("Chest")
                    .blockTags(ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED)
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Floor Light
    BlockType.WithItem<FloorLightBlock, BlockItem> FLOOR_LIGHT = BlockType.withItem(
            "floor_light",
            FloorLightBlock::new,
            builder -> builder
                    .blockProperties(properties -> properties.pushReaction(PushReaction.BLOCK).lightLevel(blockState -> 14))
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        floorLightModel(block, models);
                        ModelUtil.registerBlockItemModel(block, models);
                    })
                    .translation("Floor Light")
                    .blockTags(ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED)
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Chandelier
    BlockType.WithItem<ChandelierBlock, BlockItem> CHANDELIER = BlockType.withItem(
            "chandelier",
            ChandelierBlock::new,
            builder -> builder
                    .blockProperties(properties -> properties.lightLevel(blockState -> 14))
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> ModelUtil.horizontalFacingBlock(block, models))
                    .translation("Chandelier")
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Shelf
    BlockType.WithItem<ShelfBlock, BlockItem> SHELF = BlockType.withItem(
            "shelf",
            BlockFactory.wrapping(ShelfBlock::new),
            builder -> builder
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        shelfModel(block, models);
                        models.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block, "_single"));
                    })
                    .translation("Shelf")
                    .blockTags(ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST)
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Sofa
    BlockType.WithItem<SofaBlock, BlockItem> SOFA = BlockType.withItem(
            "sofa",
            BlockFactory.wrapping(SofaBlock::new),
            builder -> builder
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        sofaModel(block, models);
                        models.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block, "_single"));
                    })
                    .translation("Sofa")
                    .blockTags(ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST)
                    .recipe(BlockTypes::furnitureStationRecipe)
                    .onRegister(block -> NeoForge.EVENT_BUS.addListener(PlacementRenderEvent.DefaultBlockState.class, event -> {
                        var blockState = event.defaultBlockState();

                        if(blockState.is(block)) {
                            var facingComponent = block.getComponentOrThrow(BlockComponentTypes.FACING);
                            event.setDefaultBlockState(SofaConnection.setConnection(event.level(), event.pos(), blockState, facingComponent::get, facingComponent::set));
                        }
                    }))
    );
    // endregion

    // region: Counter
    BlockType.WithItem<CounterBlock, BlockItem> COUNTER = BlockType.withItem(
            "counter",
            BlockFactory.wrapping(CounterBlock::new),
            builder -> builder
                    .blockEntity(FurnitureBlockEntities.COUNTER)
                    .lootTable((blocks, furnitureSet, block) -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        counterModel(block, models);
                        models.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block, "_single"));
                    })
                    .translation("Counter")
                    .blockTags(ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST)
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Wall Light
    BlockType.WithItem<WallLightBlock, BlockItem> WALL_LIGHT = BlockType.withItem(
            "wall_light",
            WallLightBlock::new,
            builder -> builder
                    .blockProperties(properties -> properties.pushReaction(PushReaction.DESTROY).lightLevel(blockState -> 14).noCollission().instabreak())
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> ModelUtil.horizontalFacingBlock(block, models))
                    .translation("Wall Light")
                    .blockTags(ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST)
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Bench
    BlockType.WithItem<BenchBlock, BlockItem> BENCH = BlockType.withItem(
            "bench",
            BlockFactory.wrapping(BenchBlock::new),
            builder -> builder
                    .blockProperties(properties -> properties.pushReaction(PushReaction.BLOCK))
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        benchModel(block, models);
                        ModelUtil.registerBlockItemModel(block, models);
                    })
                    .translation("Bench")
                    .blockTags(ApexTags.Blocks.SEAT_ORIGIN_ONLY, ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED)
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Wardrobe
    BlockType.WithItem<WardrobeBlock, BlockItem> WARDROBE = BlockType.withItem(
            "wardrobe",
            BlockFactory.wrapping(WardrobeBlock::new),
            builder -> builder
                    .baseBlock(() -> Blocks.CHEST)
                    .blockProperties(properties -> properties.pushReaction(PushReaction.BLOCK))
                    .blockEntity(FurnitureBlockEntities.WARDROBE)
                    .lootTable((blocks, furnitureSet, block) -> blocks.accept(block, blocks.createNameableBlockEntityTable(block)))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        wardrobeModel(block, models);
                        ModelUtil.registerBlockItemModel(block, models);
                    })
                    .translation("Wardrobe")
                    .blockTags(ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST, Tags.Blocks.RELOCATION_NOT_SUPPORTED)
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Table
    BlockType.WithItem<TableBlock, BlockItem> TABLE = BlockType.withItem(
            "table",
            BlockFactory.wrapping(TableBlock::new),
            builder -> builder
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> tableModel(block, models))
                    .translation("Table")
                    .blockTags(ApexTags.Blocks.RENDER_PLACEMENT_WHITELIST)
                    .recipe(BlockTypes::furnitureStationRecipe)
    );
    // endregion

    // region: Stairs
    BlockType.WithItem<StairBlock, BlockItem> STAIRS = BlockType.withItem(
            "stairs",
            (furnitureSet, properties) -> new StairBlock(furnitureSet.getCoreBlock().defaultBlockState(), properties),
            builder -> builder
                    .baseBlock(() -> Blocks.OAK_STAIRS)
                    .blockProperties(properties -> properties.requiredFeatures(FantasyFurniture.EXPERIMENTAL))
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        var textures = TextureMapping.cube(furnitureSet.getCoreBlock());
                        var straightModel = ModelTemplates.STAIRS_STRAIGHT.create(block, textures, models.modelOutput);

                        models.blockStateOutput.accept(BlockModelGenerators.createStairs(
                                block,
                                BlockModelGenerators.plainVariant(ModelTemplates.STAIRS_INNER.create(block, textures, models.modelOutput)),
                                BlockModelGenerators.plainVariant(straightModel),
                                BlockModelGenerators.plainVariant(ModelTemplates.STAIRS_OUTER.create(block, textures, models.modelOutput))
                        ));

                        models.registerSimpleItemModel(block, straightModel);
                    })
                    .translation("Stairs")
                    .blockTags(BlockTags.WOODEN_STAIRS)
                    .itemTags(ItemTags.STAIRS)
                    .recipe((provider, furnitureSet, item) -> provider
                            .stairBuilder(item, Ingredient.of(furnitureSet.getCoreBlock()))
                            .unlockedBy("has_core_block", provider.has(furnitureSet.getCoreBlock()))
                            .save(provider.output().withConditions(NeoForgeConditions.featureFlagsEnabled(FantasyFurniture.EXPERIMENTAL)))
                    )
    );
    // endregion

    // region: Slab
    BlockType.WithItem<SlabBlock, BlockItem> SLAB = BlockType.withItem(
            "slab",
            BlockFactory.wrapping(SlabBlock::new),
            builder -> builder
                    .baseBlock(() -> Blocks.OAK_SLAB)
                    .blockProperties(properties -> properties.requiredFeatures(FantasyFurniture.EXPERIMENTAL))
                    .lootTable((blocks, furnitureSet, block) -> blocks.accept(block, blocks.createSlabItemTable(block)))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        var textures = TextureMapping.cube(furnitureSet.getCoreBlock());
                        var bottomModel = ModelTemplates.SLAB_BOTTOM.create(block, textures, models.modelOutput);

                        models.blockStateOutput.accept(BlockModelGenerators.createSlab(
                                block,
                                BlockModelGenerators.plainVariant(bottomModel),
                                BlockModelGenerators.plainVariant(ModelTemplates.SLAB_TOP.create(block, textures, models.modelOutput)),
                                BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(furnitureSet.getCoreBlock()))
                        ));

                        models.registerSimpleItemModel(block, bottomModel);
                    })
                    .translation("Slab")
                    .blockTags(BlockTags.WOODEN_SLABS)
                    .itemTags(ItemTags.WOODEN_SLABS)
                    .recipe((provider, furnitureSet, item) -> provider
                            .slabBuilder(RecipeCategory.BUILDING_BLOCKS, item, Ingredient.of(furnitureSet.getCoreBlock()))
                            .unlockedBy("has_core_block", provider.has(furnitureSet.getCoreBlock()))
                            .save(provider.output().withConditions(NeoForgeConditions.featureFlagsEnabled(FantasyFurniture.EXPERIMENTAL)))
                    )
    );
    // endregion

    // region: Fence
    BlockType.WithItem<FenceBlock, BlockItem> FENCE = BlockType.withItem(
            "fence",
            BlockFactory.wrapping(FenceBlock::new),
            builder -> builder
                    .baseBlock(() -> Blocks.OAK_FENCE)
                    .blockProperties(properties -> properties.requiredFeatures(FantasyFurniture.EXPERIMENTAL))
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        var textures = TextureMapping.cube(furnitureSet.getCoreBlock());

                        models.blockStateOutput.accept(BlockModelGenerators.createFence(
                                block,
                                BlockModelGenerators.plainVariant(ModelTemplates.FENCE_POST.create(block, textures, models.modelOutput)),
                                BlockModelGenerators.plainVariant(ModelTemplates.FENCE_SIDE.create(block, textures, models.modelOutput))
                        ));

                        models.registerSimpleItemModel(block, ModelTemplates.FENCE_INVENTORY.create(block, textures, models.modelOutput));
                    })
                    .translation("Fence")
                    .blockTags(BlockTags.WOODEN_FENCES)
                    .itemTags(ItemTags.WOODEN_FENCES)
                    .recipe((provider, furnitureSet, item) -> provider
                            .fenceBuilder(item, Ingredient.of(furnitureSet.getCoreBlock()))
                            .unlockedBy("has_core_block", provider.has(furnitureSet.getCoreBlock()))
                            .save(provider.output().withConditions(NeoForgeConditions.featureFlagsEnabled(FantasyFurniture.EXPERIMENTAL)))
                    )
    );
    // endregion

    // region: Fence Gate
    BlockType.WithItem<FenceGateBlock, BlockItem> FENCE_GATE = BlockType.withItem(
            "fence_gate",
            (furnitureSet, properties) -> new FenceGateBlock(furnitureSet.woodType(), properties),
            builder -> builder
                    .baseBlock(() -> Blocks.OAK_FENCE_GATE)
                    .blockProperties(properties -> properties.requiredFeatures(FantasyFurniture.EXPERIMENTAL))
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        var textures = TextureMapping.cube(furnitureSet.getCoreBlock());

                        models.blockStateOutput.accept(BlockModelGenerators.createFenceGate(
                                block,
                                BlockModelGenerators.plainVariant(ModelTemplates.FENCE_GATE_OPEN.create(block, textures, models.modelOutput)),
                                BlockModelGenerators.plainVariant(ModelTemplates.FENCE_GATE_CLOSED.create(block, textures, models.modelOutput)),
                                BlockModelGenerators.plainVariant(ModelTemplates.FENCE_GATE_WALL_OPEN.create(block, textures, models.modelOutput)),
                                BlockModelGenerators.plainVariant(ModelTemplates.FENCE_GATE_WALL_CLOSED.create(block, textures, models.modelOutput)),
                                true
                        ));
                    })
                    .translation("Fence Gate")
                    .blockTags(BlockTags.FENCE_GATES)
                    .itemTags(ItemTags.FENCE_GATES)
                    .recipe((provider, furnitureSet, item) -> provider
                            .fenceGateBuilder(item, Ingredient.of(furnitureSet.getCoreBlock()))
                            .unlockedBy("has_core_block", provider.has(furnitureSet.getCoreBlock()))
                            .save(provider.output().withConditions(NeoForgeConditions.featureFlagsEnabled(FantasyFurniture.EXPERIMENTAL)))
                    )
    );
    // endregion

    // region: Trapdoor
    BlockType.WithItem<TrapDoorBlock, BlockItem> TRAP_DOOR = BlockType.withItem(
            "trapdoor",
            (furnitureSet, properties) -> new TrapDoorBlock(furnitureSet.blockSet(), properties),
            builder -> builder
                    .baseBlock(() -> Blocks.OAK_TRAPDOOR)
                    .blockProperties(properties -> properties.requiredFeatures(FantasyFurniture.EXPERIMENTAL))
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> models.createOrientableTrapdoor(block))
                    .translation("Trapdoor")
                    .blockTags(BlockTags.WOODEN_TRAPDOORS)
                    .itemTags(ItemTags.WOODEN_TRAPDOORS)
                    .recipe((provider, furnitureSet, item) -> provider
                            .trapdoorBuilder(item, Ingredient.of(furnitureSet.getCoreBlock()))
                            .unlockedBy("has_core_block", provider.has(furnitureSet.getCoreBlock()))
                            .save(provider.output().withConditions(NeoForgeConditions.featureFlagsEnabled(FantasyFurniture.EXPERIMENTAL)))
                    )
    );
    // endregion

    // region: Pressure Plate
    BlockType.WithItem<PressurePlateBlock, BlockItem> PRESSURE_PLATE = BlockType.withItem(
            "pressure_plate",
            (furnitureSet, properties) -> new PressurePlateBlock(furnitureSet.blockSet(), properties),
            builder -> builder
                    .baseBlock(() -> Blocks.OAK_PRESSURE_PLATE)
                    .blockProperties(properties -> properties.requiredFeatures(FantasyFurniture.EXPERIMENTAL))
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        var textures = TextureMapping.cube(furnitureSet.getCoreBlock());

                        models.blockStateOutput.accept(BlockModelGenerators.createPressurePlate(
                                block,
                                BlockModelGenerators.plainVariant(ModelTemplates.PRESSURE_PLATE_UP.create(block, textures, models.modelOutput)),
                                BlockModelGenerators.plainVariant(ModelTemplates.PRESSURE_PLATE_DOWN.create(block, textures, models.modelOutput))
                        ));
                    })
                    .translation("Pressure Plate")
                    .blockTags(BlockTags.WOODEN_PRESSURE_PLATES)
                    .itemTags(ItemTags.WOODEN_PRESSURE_PLATES)
                    .recipe((provider, furnitureSet, block) -> provider
                            .pressurePlateBuilder(RecipeCategory.REDSTONE, block, Ingredient.of(furnitureSet.getCoreBlock()))
                            .unlockedBy(RecipeProvider.getHasName(furnitureSet.getCoreBlock()), provider.has(furnitureSet.getCoreBlock()))
                            .save(provider.output().withConditions(NeoForgeConditions.featureFlagsEnabled(FantasyFurniture.EXPERIMENTAL)))
                    )
    );
    // endregion

    // region: Button
    BlockType.WithItem<ButtonBlock, BlockItem> BUTTON = BlockType.withItem(
            "button",
            // SharedConstants.TICKS_PER_SECOND + 10 -> same as OAK -> 30 ticks
            (furnitureSet, properties) -> new ButtonBlock(furnitureSet.blockSet(), SharedConstants.TICKS_PER_SECOND + 10, properties),
            builder -> builder
                    .baseBlock(() -> Blocks.OAK_BUTTON)
                    .blockProperties(properties -> properties.requiredFeatures(FantasyFurniture.EXPERIMENTAL))
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        var textures = TextureMapping.cube(furnitureSet.getCoreBlock());

                        models.blockStateOutput.accept(BlockModelGenerators.createButton(
                                block,
                                BlockModelGenerators.plainVariant(ModelTemplates.BUTTON.create(block, textures, models.modelOutput)),
                                BlockModelGenerators.plainVariant(ModelTemplates.BUTTON_PRESSED.create(block, textures, models.modelOutput))
                        ));

                        models.registerSimpleItemModel(
                                block,
                                ModelTemplates.BUTTON_INVENTORY.create(block, textures, models.modelOutput)
                        );
                    })
                    .translation("Button")
                    .blockTags(BlockTags.WOODEN_BUTTONS)
                    .itemTags(ItemTags.WOODEN_BUTTONS)
                    .recipe((provider, furnitureSet, item) -> provider
                            .buttonBuilder(item, Ingredient.of(furnitureSet.getCoreBlock()))
                            .unlockedBy("has_core_block", provider.has(furnitureSet.getCoreBlock()))
                            .save(provider.output().withConditions(NeoForgeConditions.featureFlagsEnabled(FantasyFurniture.EXPERIMENTAL)))
                    )
    );
    // endregion

    // region: Hanging Sign
    BlockType.WithItem<CeilingHangingSignBlock, HangingSignItem> HANGING_SIGN = BlockType.withItem(
            "hanging_sign",
            (furnitureSet, properties) -> new CeilingHangingSignBlock(furnitureSet.woodType(), properties),
            (furnitureSet, block, properties) -> new HangingSignItem(block, wallHangingSign(furnitureSet), properties),
            builder -> builder
                    .baseBlock(() -> Blocks.OAK_HANGING_SIGN)
                    .itemProperties(properties -> properties.stacksTo(16))
                    .blockProperties(properties -> properties.requiredFeatures(FantasyFurniture.EXPERIMENTAL))
                    .blockEntity(() -> BlockEntityType.HANGING_SIGN)
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> models.createHangingSign(furnitureSet.getCoreBlock(), block, wallHangingSign(furnitureSet)))
                    .translation("Hanging Sign")
                    .blockTags(BlockTags.CEILING_HANGING_SIGNS)
                    .itemTags(ItemTags.HANGING_SIGNS)
                    .recipe((provider, furnitureSet, item) -> {
                        // provider.hangingSign(item, furnitureSet.getCoreBlock());

                        var material = furnitureSet.getCoreBlock();

                        // match hangingSign()
                        provider.shaped(RecipeCategory.DECORATIONS, item, 6)
                                .group("hanging_sign")
                                .define('#', material)
                                .define('X', Items.CHAIN)
                                .pattern("X X")
                                .pattern("###")
                                .pattern("###")
                                .unlockedBy("has_stripped_logs", provider.has(material))
                                .save(provider.output().withConditions(NeoForgeConditions.featureFlagsEnabled(FantasyFurniture.EXPERIMENTAL)));
                    })
                    // .require(WALL_HANGING_SIGN)
    );
    // endregion

    // region: Wall Hanging Sign
    BlockType.NoItem<WallHangingSignBlock> WALL_HANGING_SIGN = BlockType.noItem(
            "wall_hanging_sign",
            (furnitureSet, properties) -> new WallHangingSignBlock(furnitureSet.woodType(), properties),
            builder -> builder
                    .baseBlock(() -> Blocks.OAK_WALL_HANGING_SIGN)
                    .blockProperties((furnitureSet, properties) -> properties.overrideLootTable(furnitureSet.getOrThrow(HANGING_SIGN).getLootTable()).requiredFeatures(FantasyFurniture.EXPERIMENTAL))
                    .blockEntity(() -> BlockEntityType.HANGING_SIGN)
                    .blockTags(BlockTags.WALL_HANGING_SIGNS)
    );
    // endregion

    // region: Sign
    BlockType.WithItem<StandingSignBlock, SignItem> SIGN = BlockType.withItem(
            "sign",
            (furnitureSet, properties) -> new StandingSignBlock(furnitureSet.woodType(), properties),
            (furnitureSet, block, properties) -> new SignItem(block, wallSign(furnitureSet), properties),
            builder -> builder
                    .baseBlock(() -> Blocks.OAK_SIGN)
                    .itemProperties(properties -> properties.stacksTo(16))
                    .blockProperties(properties -> properties.requiredFeatures(FantasyFurniture.EXPERIMENTAL))
                    .blockEntity(() -> BlockEntityType.SIGN)
                    .lootTable((blocks, furnitureSet, block) -> blocks.dropSelf(block))
                    .model(() -> (context, models, furnitureSet, block) -> {
                        var model = ModelTemplates.PARTICLE_ONLY.create(block, TextureMapping.particle(furnitureSet.getCoreBlock()), models.modelOutput);
                        models.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(model)));
                        models.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(wallSign(furnitureSet), BlockModelGenerators.plainVariant(model)));
                        models.registerSimpleFlatItemModel(block.asItem());
                    })
                    .translation("Sign")
                    .blockTags(BlockTags.STANDING_SIGNS)
                    .itemTags(ItemTags.SIGNS)
                    .recipe((provider, furnitureSet, item) -> provider
                            .signBuilder(item, Ingredient.of(furnitureSet.getCoreBlock()))
                            .unlockedBy("has_core_block", provider.has(furnitureSet.getCoreBlock()))
                            .save(provider.output().withConditions(NeoForgeConditions.featureFlagsEnabled(FantasyFurniture.EXPERIMENTAL)))
                    )
                    // .require(WALL_SIGN)
    );
    // endregion

    // region: Wall Sign
    BlockType.NoItem<WallSignBlock> WALL_SIGN = BlockType.noItem(
            "wall_sign",
            (furnitureSet, properties) -> new WallSignBlock(furnitureSet.woodType(), properties),
            builder -> builder
                    .baseBlock(() -> Blocks.OAK_WALL_SIGN)
                    .blockProperties((furnitureSet, properties) -> properties.overrideLootTable(furnitureSet.getOrThrow(SIGN).getLootTable()).requiredFeatures(FantasyFurniture.EXPERIMENTAL))
                    .blockEntity(() -> BlockEntityType.SIGN)
                    .blockTags(BlockTags.WALL_SIGNS)
    );
    // endregion

    static void carpetModel(CarpetBlock block, FurnitureSet furnitureSet, BlockModelGenerators models) {
        models.blockStateOutput.accept(
                BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(TexturedModel.CARPET.get(furnitureSet.getOrThrow(WOOL)).create(block, models.modelOutput)))
        );
    }

    static void dresserModel(DresserBlock block, BlockModelGenerators models) {
        ModelUtil.multiBlockModelSuffix(block, models, index -> index == MultiBlockComponent.ORIGIN_INDEX ? "_left" : "_right");
    }

    static void chairModel(ChairBlock block, BlockModelGenerators models) {
        ModelUtil.multiBlockModelSuffix(block, models, index -> index == MultiBlockComponent.ORIGIN_INDEX ? "_bottom" : "_top");
    }

    static void bookshelfModel(BookshelfBlock block, BlockModelGenerators models) {
        ModelUtil.multiBlockModelSuffix(block, models, index -> switch (index) {
            case 1 -> "_bottom_right";
            case 2 -> "_top_right";
            case 3 -> "_top_left";
            default -> "_bottom_left";
        });
    }

    static void bedSingleModel(BedSingleBlock block, BlockModelGenerators models) {
        ModelUtil.multiBlockModelSuffix(block, models, index -> index == MultiBlockComponent.ORIGIN_INDEX ? "_bottom" : "_top");
    }

    static void bedDoubleModel(BedDoubleBlock block, BlockModelGenerators models) {
        ModelUtil.multiBlockModelSuffix(block, models, index -> switch (index) {
            case 1 -> "_top_left";
            case 2 -> "_top_right";
            case 3 -> "_bottom_right";
            default -> "_bottom_left";
        });
    }

    static void doorModel(FurnitureDoorBlockComponentHolder block, BlockModelGenerators blockModels) {
        var multiBlockProperty = block.getComponentOrThrow(BlockComponentTypes.MULTI_BLOCK).property();
        var facingProperty = block.getComponentOrThrow(BlockComponentTypes.FACING).getProperty();

        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                .with(PropertyDispatch.initial(facingProperty, multiBlockProperty, DoorBlockComponentHolder.HINGE, DoorBlockComponentHolder.OPEN).generate((facing, index, hinge, open) -> {
                    var indexName = index == MultiBlockComponent.ORIGIN_INDEX ? "bottom" : "top";
                    var openName = open ? "open" : "closed";
                    var modelPath = ModelLocationUtils.getModelLocation(block, '_' + hinge.getSerializedName() + '_' + indexName + '_' + openName);

                    if(open)
                        facing = hinge == DoorHingeSide.LEFT ? facing.getClockWise() : facing.getCounterClockWise();

                    var rot = switch (facing) {
                        case NORTH -> Quadrant.R270;
                        case SOUTH -> Quadrant.R90;
                        case WEST -> Quadrant.R180;
                        default -> Quadrant.R0;
                    };

                    return BlockModelGenerators.plainVariant(modelPath)
                            .with(variant -> variant.withYRot(rot));
                }))
        );
    }

    static void deskModel(DeskBlock block, BlockModelGenerators models) {
        ModelUtil.multiBlockModelSuffix(block, models, index -> index == MultiBlockComponent.ORIGIN_INDEX ? "_left" : "_right");
    }

    static void paintingWideModel(PaintingWideBlock block, BlockModelGenerators models) {
        ModelUtil.multiBlockModelSuffix(block, models, index -> index == MultiBlockComponent.ORIGIN_INDEX ? "_left" : "_right");
    }

    static void chestModel(ChestBlock block, BlockModelGenerators models) {
        ModelUtil.multiBlockModelSuffix(block, models, index -> index == MultiBlockComponent.ORIGIN_INDEX ? "_left" : "_right");
    }

    static void floorLightModel(FloorLightBlock block, BlockModelGenerators models) {
        ModelUtil.multiBlockModelSuffix(block, models, index -> index == MultiBlockComponent.ORIGIN_INDEX ? "_bottom" : "_top");
    }

    static void shelfModel(ShelfBlock block, BlockModelGenerators models) {
        ModelUtil.facingPropertyModelSuffix(block, models, ShelfConnection.PROPERTY, ShelfConnection::getModelSuffix);
    }

    static void sofaModel(SofaBlock block, BlockModelGenerators models) {
        ModelUtil.facingPropertyModelSuffix(block, models, SofaConnection.PROPERTY, SofaConnection::getModelSuffix);
    }

    static void counterModel(CounterBlock block, BlockModelGenerators models) {
        ModelUtil.facingPropertyModelSuffix(block, models, CounterConnection.PROPERTY, CounterConnection::getModelSuffix);
    }

    static void benchModel(BenchBlock block, BlockModelGenerators models) {
        ModelUtil.multiBlockModelSuffix(block, models, index -> index == MultiBlockComponent.ORIGIN_INDEX ? "_left" : "_right");
    }

    static void wardrobeModel(WardrobeBlock block, BlockModelGenerators models) {
        ModelUtil.multiBlockModelSuffix(block, models, index -> switch (index) {
            case 1 -> "_bottom_right";
            case 2 -> "_middle_right";
            case 3 -> "_middle_left";
            case 4 -> "_top_right";
            case 5 -> "_top_left";
            default -> "_bottom_left";
        });
    }

    static void tableModel(TableBlock block, BlockModelGenerators blockModels) {
        var facingProperty = block.getComponentOrThrow(BlockComponentTypes.FACING).getProperty();

        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                .with(PropertyDispatch.initial(facingProperty, TableBlock.NORTH, TableBlock.EAST, TableBlock.SOUTH, TableBlock.WEST).generate((facing, north, east, south, west) -> {
                    var connections = EnumSet.noneOf(Direction.class);
                    var facingForConnection = TableBlock.getFacingForConnection(facing);

                    if(north)
                        connections.add(Direction.NORTH);
                    if(east)
                        connections.add(Direction.EAST);
                    if(south)
                        connections.add(Direction.SOUTH);
                    if(west)
                        connections.add(Direction.WEST);

                    var rotation = ModelUtil.facingToRotation(facingForConnection);

                    // might not be the best way to do this but its datagen so who cares about performance
                    // collects connected sides
                    // rotates them to be in correct orientation for facing direction
                    // sorts them into N<-E<-S<-W order
                    // truncates down to single letter per direction
                    // joins entries to single string
                    var suffix = connections.stream().map(rotation::rotate).sorted(Comparator.comparingInt(connection -> switch (connection) {
                        case NORTH -> 0;
                        case EAST -> 1;
                        case SOUTH -> 2;
                        case WEST -> 3;
                        default -> -1;
                    })).map(connection -> switch (connection) {
                        case NORTH -> 'n';
                        case EAST -> 'e';
                        case SOUTH -> 's';
                        case WEST -> 'w';
                        default -> null;
                    }).filter(Objects::nonNull).map(String::valueOf).collect(Collectors.joining(""));

                    return BlockModelGenerators.variant(BlockModelGenerators.plainModel(ModelLocationUtils.getModelLocation(block, suffix.isBlank() ? "" : '_' + suffix)))
                            .with(variant -> variant.withYRot(ModelUtil.facingToModelRotation(facing)));
                }))
        );
    }

    static <TItem extends Item> void furnitureStationRecipe(RecipeProvider provider, FurnitureSet furnitureSet, TItem item) {
        var wool = furnitureSet.getWool();

        FurnitureStationRecipeBuilder
                .builder(RecipeCategory.MISC, Ingredient.of(furnitureSet.getCoreBlock()), wool == null ? null : Ingredient.of(wool), provider.tag(FurnitureStationSetup.BINDING_AGENT), item)
                .unlockedBy("has_core_block", provider.has(furnitureSet.getCoreBlock()))
                .save(provider.output(), RecipeProvider.recipeKeyWithPrefix(item, "furniture_station/"));
    }

    // stupid workarounds to reference fields before they are initialized
    // only to be used from within lambda contexts
    private static WallHangingSignBlock wallHangingSign(FurnitureSet furnitureSet) {
        return furnitureSet.getOrThrow(WALL_HANGING_SIGN);
    }

    private static WallSignBlock wallSign(FurnitureSet furnitureSet) {
        return furnitureSet.getOrThrow(WALL_SIGN);
    }

    @ApiStatus.Internal
    static void register() {

    }
}
