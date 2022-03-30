package xyz.apex.forge.fantasyfurniture.init;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.util.entry.RegistryEntry;

import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.item.BedItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.ITag;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.event.RegistryEvent;

import xyz.apex.forge.apexcore.lib.multiblock.*;
import xyz.apex.forge.fantasyfurniture.FantasyFurniture;
import xyz.apex.forge.fantasyfurniture.block.*;
import xyz.apex.forge.fantasyfurniture.block.decorations.BerryBasketBlock;
import xyz.apex.forge.fantasyfurniture.block.entity.DrawerBlockEntity;
import xyz.apex.forge.fantasyfurniture.block.nordic.*;
import xyz.apex.forge.utility.registrator.builder.BlockBuilder;
import xyz.apex.forge.utility.registrator.builder.ItemBuilder;
import xyz.apex.forge.utility.registrator.entry.BlockEntry;
import xyz.apex.forge.utility.registrator.factory.BlockFactory;
import xyz.apex.forge.utility.registrator.factory.item.BlockItemFactory;
import xyz.apex.java.utility.nullness.NonnullUnaryOperator;

import static xyz.apex.forge.utility.registrator.AbstractRegistrator.LANG_EXT_PROVIDER;
import static xyz.apex.forge.utility.registrator.provider.RegistrateLangExtProvider.EN_GB;
import static com.tterrag.registrate.providers.ProviderType.LANG;

/*
 * TODO: Look into using this system for multi blocks
 *  Credit goes to `50ap5ud5#8701` from Forge Discord
 *
 * https://gitlab.com/Spectre0987/TardisMod-1-14/-/blob/1.16/src/main/java/net/tardis/mod/blocks/MultiblockBlock.java
 * https://gitlab.com/Spectre0987/TardisMod-1-14/-/blob/1.16/src/main/java/net/tardis/mod/tileentities/MultiblockTile.java
 * https://gitlab.com/Spectre0987/TardisMod-1-14/-/blob/1.16/src/main/java/net/tardis/mod/items/MultiblockBlockItem.java
 * https://gitlab.com/Spectre0987/TardisMod-1-14/-/blob/1.16/src/main/java/net/tardis/mod/tileentities/MultiblockMasterTile.java
 * https://gitlab.com/Spectre0987/TardisMod-1-14/-/blob/1.16/src/main/java/net/tardis/mod/blocks/multiblock/MultiblockPatterns.java
 */
public final class FFBlocks
{
	private static final FFRegistry REGISTRY = FFRegistry.getInstance();

	public static final MultiBlockPattern PATTERN_1X2 = MultiBlockPattern
			.builder()
				.layer("##")

				.setSpacesFor4Way()
				.placeSoundPerBlock()
			.build();

	public static final MultiBlockPattern PATTERN_2X2 = MultiBlockPattern
			.builder()
				.layer("##")
				.layer("##")

				.setSpacesFor4Way()
				.placeSoundPerBlock()
			.build();

	// region: Nordic
	public static final BlockEntry<NordicBedDoubleBlock> NORDIC_BED_DOUBLE = bedDouble("nordic", NordicBedDoubleBlock::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	public static final BlockEntry<NordicBedSingleBlock> NORDIC_BED_SINGLE = bedSingle("nordic", NordicBedSingleBlock::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	public static final BlockEntry<NordicChairBlock> NORDIC_CHAIR = chair("nordic", NordicChairBlock::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	public static final BlockEntry<NordicCushionBlock> NORDIC_CUSHION = cushion("nordic", NordicCushionBlock::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	public static final BlockEntry<NordicPaintingWideBlock> NORDIC_PAINTING_WIDE = paintingWide("nordic", NordicPaintingWideBlock::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	public static final BlockEntry<NordicPaintingSmallBlock> NORDIC_PAINTING_SMALL = paintingSmall("nordic", NordicPaintingSmallBlock::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	public static final BlockEntry<NordicShelfBlock> NORDIC_SHELF = shelf("nordic", NordicShelfBlock::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	public static final BlockEntry<NordicStoolBlock> NORDIC_STOOL = stool("nordic", NordicStoolBlock::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	public static final BlockEntry<NordicSofaBlock> NORDIC_SOFA = sofa("nordic", NordicSofaBlock::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	public static final BlockEntry<NordicTableSmall> NORDIC_TABLE_SMALL = tableSmall("nordic", NordicTableSmall::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	public static final BlockEntry<NordicTableWideBlock> NORDIC_TABLE_WIDE = tableWide("nordic", PATTERN_1X2, NordicTableWideBlock::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	public static final BlockEntry<NordicWallLight> NORDIC_WALL_LIGHT = wallLight("nordic", NordicWallLight::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	public static final BlockEntry<Block> NORDIC_WOOL = wool("nordic", Block::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	public static final BlockEntry<BaseCarpetBlock> NORDIC_CARPET = carpet("nordic", BaseCarpetBlock::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	public static final BlockEntry<NordicDrawerBlock> NORDIC_DRAWER = drawer("nordic", NordicDrawerBlock::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	public static final BlockEntry<NordicWardrobeBlock> NORDIC_WARDROBE = wardrobe("nordic", PATTERN_2X2, NordicWardrobeBlock::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	public static final BlockEntry<NordicWardrobeTopperBlock> NORDIC_WARDROBE_TOPPER = wardrobeTopper("nordic", PATTERN_1X2, NordicWardrobeTopperBlock::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();

	// WIP
	// public static final BlockEntry<Block> NORDIC_BENCH = bench("nordic", Block::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	// public static final BlockEntry<Block> NORDIC_BOOKSHELF = bookShelf("nordic", Block::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	// public static final BlockEntry<Block> NORDIC_CHEST = chest("nordic", Block::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	// public static final BlockEntry<Block> NORDIC_DRAWER = drawer("nordic", Block::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	// public static final BlockEntry<Block> NORDIC_TABLE_LARGE = tableLarge("nordic", Block::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	// public static final BlockEntry<Block> NORDIC_TABLE_LONG = tableLong("nordic", Block::new, FFTags.Blocks.NORDIC, FFTags.Items.NORDIC).register();
	// endregion

	// region: Decorations
	public static final BlockEntry<BerryBasketBlock> BERRY_BASKET = berryBasket();
	// endregion

	static void bootstrap()
	{
		FantasyFurniture.registerPoiBlock(PointOfInterestType.HOME, NORDIC_BED_SINGLE, blockState -> blockState.getValue(BaseBedBlock.PART) == BedPart.HEAD);
		FantasyFurniture.registerPoiBlock(PointOfInterestType.HOME, NORDIC_BED_DOUBLE, blockState -> blockState.getValue(BaseBedBlock.PART) == BedPart.HEAD);

		REGISTRY.addDataGenerator(LANG, provider -> {
			REGISTRY.getAll(Block.class)
			        .stream()
			        .filter(RegistryEntry::isPresent)
			        .map(RegistryEntry::get)
			        .filter(BaseSeatBlock.class::isInstance)
			        .map(Block::getDescriptionId)
			        .forEach(s -> provider.add(s + ".occupied", "This seat is occupied"));
		});

		REGISTRY.addDataGenerator(LANG_EXT_PROVIDER, provider -> {
			REGISTRY.getAll(Block.class)
			        .stream()
			        .filter(RegistryEntry::isPresent)
			        .map(RegistryEntry::get)
			        .filter(BaseSeatBlock.class::isInstance)
			        .map(Block::getDescriptionId)
			        .forEach(s -> provider.add(EN_GB, s + ".occupied", "This seat is occupied"));
		});
	}

	private static <BLOCK extends Block, ITEM extends BlockItem> BlockBuilder<FFRegistry, BLOCK, FFRegistry> baseTypedBlock(String type, String blockType, BlockFactory<BLOCK> blockFactory, BlockItemFactory<BLOCK, ITEM> itemFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag, NonnullUnaryOperator<ItemBuilder<FFRegistry, ITEM, BlockBuilder<FFRegistry, BLOCK, FFRegistry>>> itemModifier)
	{
		String internalName = type + '/' + blockType;
		String englishName = RegistrateLangProvider.toEnglishName(internalName.replace('/', '_'));
		ResourceLocation modelLocation = REGISTRY.id("block/" + internalName);

		BlockBuilder<FFRegistry, BLOCK, FFRegistry> blockBuilder = REGISTRY
				.block(internalName, blockFactory)
					.lang(englishName)
					.lang(EN_GB, englishName)
					.initialProperties(Material.WOOD, MaterialColor.WOOD)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)

					.blockState((ctx, provider) -> provider.simpleBlock(ctx.get(), provider.models().getExistingFile(modelLocation)))
					.tag(blockTag)
					.addRenderType(() -> RenderType::cutout)

					.mapping("1.16.5", internalName.replace('/', '_'), RegistryEvent.MissingMappings.Action.REMAP)
		;

		ItemBuilder<FFRegistry, ITEM, BlockBuilder<FFRegistry, BLOCK, FFRegistry>> itemBuilder = blockBuilder
				.item(itemFactory)
					// .stacksTo(1)
					.model((ctx, provider) -> provider.withExistingParent("item/" + ctx.getName(), modelLocation))
					.tag(itemTag)
		;

		itemBuilder = itemModifier.apply(itemBuilder);
		blockBuilder = itemBuilder.build();
		return blockBuilder;
	}

	private static <BLOCK extends Block, ITEM extends BlockItem> BlockBuilder<FFRegistry, BLOCK, FFRegistry> baseTypedBlock(String type, String blockType, BlockFactory<BLOCK> blockFactory, BlockItemFactory<BLOCK, ITEM> itemFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedBlock(type, blockType, blockFactory, itemFactory, blockTag, itemTag, NonnullUnaryOperator.identity());
	}

	private static <BLOCK extends MultiBlock, ITEM extends BlockItem> MultiBlockBuilder<FFRegistry, BLOCK, FFRegistry> baseTypedMultiBlock(String type, String blockType, MultiBlockPattern pattern, MultiBlockFactory<BLOCK> blockFactory, BlockItemFactory<BLOCK, ITEM> itemFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag, NonnullUnaryOperator<ItemBuilder<FFRegistry, ITEM, MultiBlockBuilder<FFRegistry, BLOCK, FFRegistry>>> itemModifier)
	{
		String internalName = type + '/' + blockType;
		String englishName = RegistrateLangProvider.toEnglishName(internalName.replace('/', '_'));
		ResourceLocation modelLocation = REGISTRY.id("block/" + internalName);

		MultiBlockBuilder<FFRegistry, BLOCK, FFRegistry> blockBuilder = REGISTRY
				.multiBlock(internalName, blockFactory, pattern)
					.lang(englishName)
					.lang(EN_GB, englishName)
					.initialProperties(Material.WOOD, MaterialColor.WOOD)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)

					.blockState((ctx, provider) -> provider.simpleBlock(ctx.get(), provider.models().getExistingFile(modelLocation)))
					.tag(blockTag)
					.addRenderType(() -> RenderType::cutout)

					.mapping("1.16.5", internalName.replace('/', '_'), RegistryEvent.MissingMappings.Action.REMAP)
		;

		ItemBuilder<FFRegistry, ITEM, MultiBlockBuilder<FFRegistry, BLOCK, FFRegistry>> itemBuilder = blockBuilder
				.item(itemFactory)
					// .stacksTo(1)
					.model((ctx, provider) -> provider.withExistingParent("item/" + ctx.getName(), modelLocation))
					.tag(itemTag)
		;

		itemBuilder = itemModifier.apply(itemBuilder);
		blockBuilder = itemBuilder.build();
		return blockBuilder;
	}

	private static <BLOCK extends MultiBlock, ITEM extends BlockItem> MultiBlockBuilder<FFRegistry, BLOCK, FFRegistry> baseTypedMultiBlock(String type, String blockType, MultiBlockPattern pattern, MultiBlockFactory<BLOCK> blockFactory, BlockItemFactory<BLOCK, ITEM> itemFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedMultiBlock(type, blockType, pattern, blockFactory, itemFactory, blockTag, itemTag, NonnullUnaryOperator.identity());
	}

	private static <BLOCK extends BaseBedBlockDouble> BlockBuilder<FFRegistry, BLOCK, FFRegistry> bedDouble(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedBlock(type, "bed_double", blockFactory, BedItem::new, blockTag, itemTag, item -> item.tag(FFTags.Items.BEDS_DOUBLE))
					.initialProperties(Material.WOOL, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(.2F)
					.noOcclusion()

					.blockState((ctx, provider) -> horizontalBlockState(ctx, provider, type, "bed_double", 0))
					.loot((lootTables, block) -> lootTables.add(block, createBedDoubleConditionTable(block)))
					.tag(FFTags.Blocks.BEDS_DOUBLE)
		;
	}

	private static <BLOCK extends BaseBedBlock> BlockBuilder<FFRegistry, BLOCK, FFRegistry> bedSingle(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedBlock(type, "bed_single", blockFactory, BedItem::new, blockTag, itemTag, item -> item.tag(FFTags.Items.BEDS_SINGLE))
					.initialProperties(Material.WOOL, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(.2F)
					.noOcclusion()

					.blockState((ctx, provider) -> horizontalBlockState(ctx, provider, type, "bed_single", 0))
					.loot((lootTables, block) -> lootTables.add(block, createSingleBedConditionTable(block)))
					.tag(FFTags.Blocks.BEDS_SINGLE)
		;
	}

	private static <BLOCK extends Block> BlockBuilder<FFRegistry, BLOCK, FFRegistry> bench(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedBlock(type, "bench", blockFactory, BlockItem::new, blockTag, itemTag, item -> item.tag(FFTags.Items.BENCHES))
					.initialProperties(Material.WOOD, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(.2F)
					.noOcclusion()

					// .blockState((ctx, provider) -> horizontalBlockState(ctx, provider, type, "bench", 0))
					// .loot((lootTables, block) -> lootTables.add(block, createSinglePropConditionTable(block, NordicBedSingleBlock.PART, BedPart.HEAD)))
					.tag(FFTags.Blocks.BENCHES)
		;
	}

	/*private static <BLOCK extends Block> BlockBuilder<FFRegistry, BLOCK, FFRegistry> bookShelf(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedBlock(type, "bookshelf", blockFactory, BlockItem::new, blockTag, itemTag, item -> item.tag(FFTags.Items.BOOKSHELVES))
					.initialProperties(Material.WOOD, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(.2F)
					.noOcclusion()

					// .blockState((ctx, provider) -> horizontalBlockState(ctx, provider, type, "bookshelf", 0))
					// .loot((lootTables, block) -> lootTables.add(block, createSinglePropConditionTable(block, NordicBedSingleBlock.PART, BedPart.HEAD)))
					.tag(FFTags.Blocks.BOOKSHELVES)
		;
	}*/

	private static <BLOCK extends BaseSeatBlock> BlockBuilder<FFRegistry, BLOCK, FFRegistry> chair(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedBlock(type, "chair", blockFactory, BlockItem::new, blockTag, itemTag, item -> item.tag(FFTags.Items.CHAIRS))
					.initialProperties(Material.WOOD, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noOcclusion()

					.blockState((ctx, provider) -> horizontalBlockState(ctx, provider, type, "chair", 180))
					.loot((lootTables, block) -> lootTables.add(block, BlockLootTables.createSinglePropConditionTable(block, BaseSeatDoubleBlock.HALF, DoubleBlockHalf.LOWER)))
					.tag(FFTags.Blocks.CHAIRS)
		;
	}

	private static <BLOCK extends Block> BlockBuilder<FFRegistry, BLOCK, FFRegistry> chest(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedBlock(type, "chest", blockFactory, BlockItem::new, blockTag, itemTag, item -> item.tag(FFTags.Items.CHESTS_WOODEN))
					.initialProperties(Material.WOOD, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noOcclusion()

					// .blockState((ctx, provider) -> horizontalBlockState(ctx, provider, type, "chest", 0))
					// .loot((lootTables, block) -> lootTables.add(block, createSinglePropConditionTable(block, BaseSeatDoubleBlock.HALF, DoubleBlockHalf.LOWER)))
					.tag(FFTags.Blocks.CHESTS_WOODEN)
		;
	}

	private static <BLOCK extends BaseSeatBlock> BlockBuilder<FFRegistry, BLOCK, FFRegistry> cushion(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedBlock(type, "cushion", blockFactory, BlockItem::new, blockTag, itemTag, item -> item.tag(FFTags.Items.CUSHIONS))
					.initialProperties(Material.WOOL, MaterialColor.WOOL)
					.sound(SoundType.WOOL)
					.strength(2F, 3F)
					.noOcclusion()

					.blockState((ctx, provider) -> horizontalBlockState(ctx, provider, type, "cushion", 0))
					.tag(FFTags.Blocks.CUSHIONS)
		;
	}

	/*private static <BLOCK extends Block> BlockBuilder<FFRegistry, BLOCK, FFRegistry> desk(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedBlock(type, "desk", blockFactory, BlockItem::new, blockTag, itemTag, item -> item.tag(FFTags.Items.DESKS))
					.initialProperties(Material.WOOD, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noOcclusion()

					// .blockState((ctx, provider) -> horizontalBlockState(ctx, provider, type, "chest", 0))
					// .loot((lootTables, block) -> lootTables.add(block, createSinglePropConditionTable(block, BaseSeatDoubleBlock.HALF, DoubleBlockHalf.LOWER)))
					.tag(FFTags.Blocks.DESKS)
		;
	}*/

	private static <BLOCK extends SimpleFourWayBlockEntityBlock<DrawerBlockEntity>> BlockBuilder<FFRegistry, BLOCK, FFRegistry> drawer(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedBlock(type, "drawer", blockFactory, BlockItem::new, blockTag, itemTag, item -> item.tag(FFTags.Items.DRAWERS))
					.initialProperties(Material.WOOD, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noOcclusion()

					.blockState((ctx, provider) -> horizontalBlockState(ctx, provider, type, "drawer", 180))
					// .loot((lootTables, block) -> lootTables.add(block, createSinglePropConditionTable(block, BaseSeatDoubleBlock.HALF, DoubleBlockHalf.LOWER)))
					.tag(FFTags.Blocks.DRAWERS)

					.simpleBlockEntity(DrawerBlockEntity::new)
		;
	}

	private static <BLOCK extends BasePaintingBlock> BlockBuilder<FFRegistry, BLOCK, FFRegistry> paintingSmall(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedBlock(type, "painting_small", blockFactory, BlockItem::new, blockTag, itemTag, item -> item.tag(FFTags.Items.PAINTINGS))
					.initialProperties(Material.WOOD, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noOcclusion()
					.noCollission()

					.blockState((ctx, provider) -> horizontalBlockState(ctx, provider, type, "painting_small", 180))
					.tag(FFTags.Blocks.PAINTINGS)
		;
	}

	private static <BLOCK extends BasePaintingDoubleBlock> BlockBuilder<FFRegistry, BLOCK, FFRegistry> paintingWide(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedBlock(type, "painting_wide", blockFactory, BlockItem::new, blockTag, itemTag, item -> item.tag(FFTags.Items.PAINTINGS))
					.initialProperties(Material.WOOD, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noOcclusion()
					.noCollission()

					.blockState((ctx, provider) -> horizontalBlockState(ctx, provider, type, "painting_wide", 180))
					.loot((lootTables, block) -> lootTables.add(block, BlockLootTables.createSinglePropConditionTable(block, BasePaintingDoubleBlock.TYPE, BasePaintingDoubleBlock.Type.MAIN)))
					.tag(FFTags.Blocks.PAINTINGS)
		;
	}

	private static <BLOCK extends BaseShelfBlock> BlockBuilder<FFRegistry, BLOCK, FFRegistry> shelf(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedBlock(type, "shelf", blockFactory, BlockItem::new, blockTag, itemTag, item -> item.tag(FFTags.Items.SHELVES))
					.initialProperties(Material.WOOD, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noOcclusion()
					.randomTicks()

					.blockState((ctx, provider) -> provider.horizontalBlock(ctx.get(), blockState -> {
						BaseShelfBlock.ConnectionType connectionType = blockState.getValue(BaseShelfBlock.CONNECTION_TYPE);
						String suffix;

						if(connectionType == BaseShelfBlock.ConnectionType.LEFT || connectionType == BaseShelfBlock.ConnectionType.RIGHT)
							suffix = "_" + connectionType.getSerializedName();
						else if(connectionType == BaseShelfBlock.ConnectionType.BOTH)
							suffix = "_center";
						else
							suffix = "";

						return provider.models().getExistingFile(REGISTRY.id("block/" + type + "/shelf" + suffix));
					}, 180))
					.tag(FFTags.Blocks.SHELVES)
		;
	}

	private static <BLOCK extends BaseSeatBlock> BlockBuilder<FFRegistry, BLOCK, FFRegistry> stool(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedBlock(type, "stool", blockFactory, BlockItem::new, blockTag, itemTag, item -> item.tag(FFTags.Items.STOOLS))
					.initialProperties(Material.WOOD, MaterialColor.WOOD)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noOcclusion()

					.blockState((ctx, provider) -> horizontalBlockState(ctx, provider, type, "stool", 0))
					.tag(FFTags.Blocks.STOOLS)
		;
	}

	private static <BLOCK extends Block> BlockBuilder<FFRegistry, BLOCK, FFRegistry> tableLarge(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedBlock(type, "table_large", blockFactory, BlockItem::new, blockTag, itemTag, item -> item.tag(FFTags.Items.TABLES_LARGE))
					.initialProperties(Material.WOOD, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noOcclusion()

					// .blockState((ctx, provider) -> horizontalBlockState(ctx, provider, type, "table_large", 0))
					// .loot((lootTables, block) -> lootTables.add(block, createSinglePropConditionTable(block, BaseTableWideBlock.TYPE, BaseTableWideBlock.Type.MAIN)))
					.tag(FFTags.Blocks.TABLES_LARGE)
		;
	}

	private static <BLOCK extends Block> BlockBuilder<FFRegistry, BLOCK, FFRegistry> tableLong(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedBlock(type, "table_long", blockFactory, BlockItem::new, blockTag, itemTag, item -> item.tag(FFTags.Items.TABLES_LONG))
					.initialProperties(Material.WOOD, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noOcclusion()

					// .blockState((ctx, provider) -> horizontalBlockState(ctx, provider, type, "table_long", 0))
					// .loot((lootTables, block) -> lootTables.add(block, createSinglePropConditionTable(block, BaseTableWideBlock.TYPE, BaseTableWideBlock.Type.MAIN)))
					.tag(FFTags.Blocks.TABLES_LONG)
		;
	}

	private static <BLOCK extends Block> BlockBuilder<FFRegistry, BLOCK, FFRegistry> tableSmall(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedBlock(type, "table_small", blockFactory, BlockItem::new, blockTag, itemTag, item -> item.tag(FFTags.Items.TABLES_SMALL))
					.initialProperties(Material.WOOD, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noOcclusion()
					.tag(FFTags.Blocks.TABLES_SMALL)
		;
	}

	private static <BLOCK extends BaseTableWideBlock> MultiBlockBuilder<FFRegistry, BLOCK, FFRegistry> tableWide(String type, MultiBlockPattern pattern, MultiBlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedMultiBlock(type, "table_wide", pattern, blockFactory, BlockItem::new, blockTag, itemTag, item -> item.tag(FFTags.Items.TABLES_WIDE))
					.initialProperties(Material.WOOD, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noOcclusion()

					.blockState((ctx, provider) -> horizontalMultiBlockState(ctx, provider, type, "table_wide", 180))
					// .loot((lootTables, block) -> lootTables.add(block, BlockLootTables.createSinglePropConditionTable(block, BaseTableWideBlock.TYPE, BaseTableWideBlock.Type.MAIN)))
					.tag(FFTags.Blocks.TABLES_WIDE)
		;
	}

	private static <BLOCK extends BaseWallLight> BlockBuilder<FFRegistry, BLOCK, FFRegistry> wallLight(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedBlock(type, "wall_light", blockFactory, BlockItem::new, blockTag, itemTag, item -> item.tag(FFTags.Items.TORCHES))
					.initialProperties(Material.DECORATION)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noCollission()
					.instabreak()
					.lightLevel(blockState -> 14)
					.tag(FFTags.Blocks.TORCHES)
					.blockState((ctx, provider) -> horizontalBlockState(ctx, provider, type, "wall_light", 180))
		;
	}

	private static <BLOCK extends BaseWardrobeBlock> MultiBlockBuilder<FFRegistry, BLOCK, FFRegistry> wardrobe(String type, MultiBlockPattern pattern, MultiBlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedMultiBlock(type, "wardrobe", pattern, blockFactory, BlockItem::new, blockTag, itemTag, item -> item.tag(FFTags.Items.WARDROBES))
					.initialProperties(Material.WOOD, MaterialColor.WOOD)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noOcclusion()

					.blockState((ctx, provider) -> horizontalMultiBlockState(ctx, provider, type, "wardrobe", 180))
					// .loot((lootTables, block) -> lootTables.add(block, createSinglePropConditionTable(block, BaseTableWideBlock.TYPE, BaseTableWideBlock.Type.MAIN)))
					.tag(FFTags.Blocks.WARDROBES)
		;
	}

	private static <BLOCK extends BaseWardrobeTopperBlock> MultiBlockBuilder<FFRegistry, BLOCK, FFRegistry> wardrobeTopper(String type, MultiBlockPattern pattern, MultiBlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedMultiBlock(type, "wardrobe_topper", pattern, blockFactory, BlockItem::new, blockTag, itemTag, item -> item.tag(FFTags.Items.WARDROBE_TOPPERS))
					.initialProperties(Material.WOOD, MaterialColor.WOOD)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noOcclusion()

					.blockState((ctx, provider) -> horizontalMultiBlockState(ctx, provider, type, "wardrobe_topper", 180))
					// .loot((lootTables, block) -> lootTables.add(block, createSinglePropConditionTable(block, BaseTableWideBlock.TYPE, BaseTableWideBlock.Type.MAIN)))
					.tag(FFTags.Blocks.WARDROBE_TOPPERS)
		;
	}

	private static <BLOCK extends BaseSofaBlock> BlockBuilder<FFRegistry, BLOCK, FFRegistry> sofa(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		return baseTypedBlock(type, "sofa", blockFactory, BlockItem::new, blockTag, itemTag, item -> item
						.tag(FFTags.Items.SOFAS)
						.model((ctx, provider) -> provider.withExistingParent("item/" + ctx.getName(), REGISTRY.id("block/" + type + "/sofa_single")))
					)
					.initialProperties(Material.WOOD, MaterialColor.WOOD)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.getVariantBuilder(ctx.get()).forAllStates(blockState -> {
						Direction facing = blockState.getValue(BaseSofaBlock.FACING);
						BaseSofaBlock.ConnectionType connectionType = blockState.getValue(BaseSofaBlock.CONNECTION_TYPE);

						return ConfiguredModel
								.builder()
									.modelFile(provider.models().getExistingFile(REGISTRY.id("block/" + type + "/sofa_" + connectionType.getSerializedName())))
									.rotationY(((int) facing.toYRot() + 180) % 360)
								.build();
					}))
					.tag(FFTags.Blocks.SOFAS)
		;
	}

	private static <BLOCK extends Block> BlockBuilder<FFRegistry, BLOCK, FFRegistry> wool(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		String internalName = type + "/wool";
		String englishName = RegistrateLangProvider.toEnglishName(internalName.replace('/', '_'));
		String location = REGISTRY.idString("block/" + internalName);

		return REGISTRY
				.block(internalName, blockFactory)
					.lang(englishName)
					.lang(EN_GB, englishName)
					.initialProperties(Material.WOOL, MaterialColor.WOOL)
					.sound(SoundType.WOOL)
					.strength(.8F)

					.blockState((ctx, provider) -> provider.simpleBlock(ctx.get(), provider.models().cubeAll(location, new ResourceLocation(location))))
					.tag(FFTags.Blocks.WOOLS)

					.item()
						.model((ctx, provider) -> provider.withExistingParent("item/" + ctx.getName(), location))
						.tag(FFTags.Items.WOOLS)
					.build()
		;
	}

	private static <BLOCK extends BaseCarpetBlock> BlockBuilder<FFRegistry, BLOCK, FFRegistry> carpet(String type, BlockFactory<BLOCK> blockFactory, ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag)
	{
		String internalName = type + "/carpet";
		String englishName = RegistrateLangProvider.toEnglishName(internalName.replace('/', '_'));
		ResourceLocation locationWool = REGISTRY.id("block/" + type + "/wool");
		String location = REGISTRY.idString("block/" + internalName);

		return REGISTRY
				.block(internalName, blockFactory)
					.lang(englishName)
					.lang(EN_GB, englishName)
					.initialProperties(Material.CLOTH_DECORATION, MaterialColor.WOOL)
					.sound(SoundType.WOOL)
					.strength(.1F)

					.blockState((ctx, provider) -> provider.simpleBlock(ctx.get(), provider.models().carpet(location, locationWool)))
					.tag(FFTags.Blocks.CARPETS)

					.item()
						.model((ctx, provider) -> provider.withExistingParent("item/" + ctx.getName(), location))
						.tag(FFTags.Items.CARPETS)
					.build()
		;
	}

	private static BlockEntry<BerryBasketBlock> berryBasket()
	{
		return REGISTRY
				.block("berry_basket", BerryBasketBlock::new)
					.lang("Berry Basket")
					.lang(EN_GB, "Berry Basket")

					.initialProperties(Material.WOOD, MaterialColor.WOOL)
					.sound(SoundType.WOOD)
					.strength(2F, 3F)
					.noOcclusion()

					.blockState((ctx, provider) -> provider.horizontalBlock(ctx.get(), provider.models().getExistingFile(REGISTRY.id("block/decorations/berry_basket"))))
					// .loot((lootTables, block) -> lootTables.add(block, createSinglePropConditionTable(block, BaseTableWideBlock.TYPE, BaseTableWideBlock.Type.MAIN)))
					.recipe((ctx, provider) -> {
						// TODO:
					})
					.tag(FFTags.Blocks.DECORATIONS)

					.addRenderType(() -> RenderType::cutout)

					.item()
						.stacksTo(1)

						.model((ctx, provider) -> provider.withExistingParent(ctx.getName(), REGISTRY.id("block/decorations/berry_basket")))
						.tag(FFTags.Items.DECORATIONS)
					.build()
				.register()
				;
	}

	private static <BLOCK extends HorizontalBlock> void horizontalBlockState(DataGenContext<Block, BLOCK> ctx, RegistrateBlockstateProvider provider, ResourceLocation modelLocation, int angleOffset)
	{
		provider.horizontalBlock(ctx.get(), provider.models().getExistingFile(modelLocation), angleOffset);
	}

	private static <BLOCK extends HorizontalBlock> void horizontalBlockState(DataGenContext<Block, BLOCK> ctx, RegistrateBlockstateProvider provider, String type, String blockType, int angleOffset)
	{
		ResourceLocation modelLocation = REGISTRY.id("block/" + type + '/' + blockType);
		horizontalBlockState(ctx, provider, modelLocation, angleOffset);
	}

	private static <BLOCK extends MultiBlockFourWay> void horizontalMultiBlockState(DataGenContext<Block, BLOCK> ctx, RegistrateBlockstateProvider provider, ResourceLocation modelLocation, int angleOffset)
	{
		provider.horizontalBlock(ctx.get(), provider.models().getExistingFile(modelLocation), angleOffset);
	}

	private static <BLOCK extends MultiBlockFourWay> void horizontalMultiBlockState(DataGenContext<Block, BLOCK> ctx, RegistrateBlockstateProvider provider, String type, String blockType, int angleOffset)
	{
		ResourceLocation modelLocation = REGISTRY.id("block/" + type + '/' + blockType);
		horizontalMultiBlockState(ctx, provider, modelLocation, angleOffset);
	}

	private static LootTable.Builder createSingleBedConditionTable(BaseBedBlock block)
	{
		return BlockLootTables.createSinglePropConditionTable(block, BaseBedBlock.PART, BedPart.FOOT);
	}

	private static LootTable.Builder createBedDoubleConditionTable(BaseBedBlockDouble block)
	{
		return LootTable
				.lootTable()
				.withPool(
						BlockLootTables.applyExplosionCondition(block, LootPool
								.lootPool()
								.setRolls(ConstantRange.exactly(1))
								.add(
										ItemLootEntry
												.lootTableItem(block)
												.when(
														BlockStateProperty
																.hasBlockStateProperties(block)
																.setProperties(
																		StatePropertiesPredicate.Builder
																				.properties()
																				.hasProperty(BaseBedBlock.PART, BedPart.FOOT)
																				.hasProperty(BaseBedBlockDouble.BED_SIDE, BaseBedBlockDouble.BedSide.MAIN)
																)
												)
								)
						)
				);
	}
}