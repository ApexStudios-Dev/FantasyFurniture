package dev.apexstudios.fantasyfurniture.set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.function.Consumers;
import org.jetbrains.annotations.Nullable;

public final class FurnitureSet {
    private static final Map<ResourceLocation, FurnitureSet> REGISTRY = Maps.newHashMap();

    final Registree registree;
    private final String name;
    private final Set<BlockType<?>> blockTypes;
    private final WoodType woodType;
    private final ResourceKey<CreativeModeTab> creativeModeTab;
    private final boolean usesPrefix;
    final Supplier<? extends BlockBehaviour> baseBlock;
    private final TagKey<Block> mineableTag;
    private final BlockType<?> coreBlockType;
    private final TagKey<Block> blockTag;
    private final TagKey<Item> itemTag;
    @Nullable private final ItemLike woolItem;
    private final Supplier<? extends ParticleOptions> flameParticle;

    private FurnitureSet(FurnitureSetBuilder builder) {
        registree = builder.registree;
        name = builder.name;
        blockTypes = blockTypes(builder);
        woodType = builder.woodType.build(name + "_wood_type", name + "_block_set");
        usesPrefix = builder.usesPrefix;
        baseBlock = builder.baseBlock;
        mineableTag = builder.mineableTag;
        coreBlockType = builder.coreBlockType;
        woolItem = builder.woolItem;
        flameParticle = builder.flameParticle;

        blockTag = TagKey.create(Registries.BLOCK, FantasyFurniture.identifier(name));
        itemTag = TagKey.create(Registries.ITEM, FantasyFurniture.identifier(name));

        // TODO: look into having the tab icon cycle between all registered blocks
        creativeModeTab = registree.registerCreativeModeTab(name, () -> new ItemStack(getOrThrow(BlockTypes.BED_SINGLE)), (parameters, output) -> {
            blockTypes.forEach(blockType -> {
                var block = getOrThrow(blockType);

                if(block.isEnabled(parameters.enabledFeatures()))
                    output.accept(block);
            });
        });

        var registryName = registree.registryName(name);

        if(REGISTRY.putIfAbsent(registryName, this) != null)
            throw new IllegalStateException("Duplicate FurnitureSet: " + registryName);
    }

    String registrationName(String registrationName) {
        return usesPrefix ? name + '_' + registrationName : registrationName;
    }

    public void register(IEventBus modBus) {
        for(var blockType : blockTypes) {
            blockType.register(modBus, this);
        }

        modBus.addListener(FMLClientSetupEvent.class, event -> event.enqueueWork(() -> {
            // vanilla uses the wood type id to construct the texture location
            // our wood type id == '${furniture_set}_wood_type'
            // but we want only '$furniture_set' for the texture name
            //
            // this also registers both ground & hanging sign materials
            // when furniture set potentially only needs 1 of them
            // Sheets.addWoodType(woodType);

            // we register the sign materials manually to match our desired texture path
            // and only register materials when the matching block types are registered
            if(isRegistered(BlockTypes.HANGING_SIGN) || isRegistered(BlockTypes.WALL_HANGING_SIGN))
                Sheets.HANGING_SIGN_MATERIALS.put(woodType, Sheets.HANGING_SIGN_MAPPER.apply(registree.registryName(name)));
            if(isRegistered(BlockTypes.SIGN) || isRegistered(BlockTypes.WALL_SIGN))
                Sheets.SIGN_MATERIALS.put(woodType, Sheets.SIGN_MAPPER.apply(registree.registryName(name)));

            ifRegistered(BlockTypes.TRAP_DOOR, block -> ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutout()));
        }));
    }

    public void registerDataGen(ResourceGenerator generator) {
        var pack = generator.pack();

        for(var blockType : blockTypes) {
            blockType.registerDataGen(pack, this);
        }

        pack.providing(ProviderTypes.LANGUAGE, (context, provider) -> {
            var englishName = StringUtils.capitalize(name);

            provider.addCreativeModeTab(creativeModeTab, englishName);
            provider.add(blockTag, englishName + " (Blocks)");
            provider.add(itemTag, englishName + " (Items)");
        });

        pack.providing(ProviderTypes.BLOCK_TAGS, (context, provider) -> {
            for(var blockType : blockTypes) {
                provider.tag(blockTag).withElement(getOrThrow(blockType));

                if(blockType.usesMineableTag)
                    provider.tag(mineableTag).withElement(getOrThrow(blockType));
            }
        });

        pack.providing(ProviderTypes.ITEM_TAGS, (context, provider) -> {
            for(var blockType : blockTypes) {
                var item = getOrThrow(blockType).asItem();

                if(item != Items.AIR)
                    provider.tag(itemTag).withElement(item);
            }
        });
    }

    public String ownerNamespace() {
        return registree.namespace();
    }

    public String name() {
        return name;
    }

    public Set<BlockType<?>> blockTypes() {
        return blockTypes;
    }

    public BlockSetType blockSet() {
        return woodType.setType();
    }

    public WoodType woodType() {
        return woodType;
    }

    public ResourceKey<CreativeModeTab> creativeModeTab() {
        return creativeModeTab;
    }

    public boolean isRegistered(BlockType<?> blockType) {
        return registree.containsKey(Registries.BLOCK, registrationName(blockType.registryName));
    }

    public <TBlock extends Block> Optional<TBlock> get(BlockType<TBlock> blockType) {
        return registree.getOptional(Registries.BLOCK, registrationName(blockType.registryName)).map(value -> (TBlock) value);
    }

    public <TBlock extends Block> TBlock getOrThrow(BlockType<TBlock> blockType) {
        return get(blockType).orElseThrow(() -> new NullPointerException());
    }

    public <TBlock extends Block> void ifRegistered(BlockType<TBlock> blockType, Consumer<TBlock> consumer) {
        get(blockType).ifPresent(consumer);
    }

    public BlockType<?> coreBlockType() {
        return coreBlockType;
    }

    public Block getCoreBlock() {
        return getOrThrow(coreBlockType);
    }

    @Nullable
    public ItemLike getWool() {
        if(woolItem != null)
            return woolItem;

        return get(BlockTypes.WOOL).orElse(null);
    }

    public ParticleOptions flameParticle() {
        return flameParticle.get();
    }

    public boolean is(ItemStack stack) {
        return stack.is(itemTag);
    }

    public boolean is(BlockState blockState) {
        return blockState.is(blockTag);
    }

    public boolean is(Block block) {
        return block.builtInRegistryHolder().is(blockTag);
    }

    public boolean is(Item item) {
        return item.builtInRegistryHolder().is(itemTag);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj instanceof FurnitureSet other)
            return ownerNamespace().equals(other.ownerNamespace()) && name.equals(other.name);
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerNamespace(), name);
    }

    @Override
    public String toString() {
        return "FurnitureSet{" + ownerNamespace() + ResourceLocation.NAMESPACE_SEPARATOR + name + '}';
    }

    public static FurnitureSet create(Registree registree, String name, Consumer<FurnitureSetBuilder> consumer) {
        var builder = new FurnitureSetBuilder(registree, name);
        consumer.accept(builder);
        return new FurnitureSet(builder);
    }

    public static FurnitureSet createWoodLike(Registree registree, String name, Consumer<FurnitureSetBuilder> consumer) {
        return create(registree, name, builder -> {
            builder.baseBlock(() -> Blocks.OAK_PLANKS)
                    .mineable(BlockTags.MINEABLE_WITH_AXE)
                    .baseBlockType(BlockTypes.PLANKS)
                    .woodType(woodType -> woodType.copy(WoodType.OAK))
                    .with(FurnitureSetBuilder.WOODEN_BLOCK_TYPES);

            consumer.accept(builder);
        });
    }

    public static FurnitureSet createWoodLike(Registree registree, String name) {
        return createWoodLike(registree, name, Consumers.nop());
    }

    public static FurnitureSet createStoneLike(Registree registree, String name, Consumer<FurnitureSetBuilder> consumer) {
        return createWoodLike(registree, name, builder -> {
            builder.baseBlock(() -> Blocks.STONE)
                    .mineable(BlockTags.MINEABLE_WITH_PICKAXE)
                    .baseBlockType(BlockTypes.BRICKS)
                    .blockSet(blockSet -> blockSet.copy(BlockSetType.STONE))
                    .remove(BlockTypes.PLANKS);

            consumer.accept(builder);
        });
    }

    public static FurnitureSet createStoneLike(Registree registree, String name) {
        return createStoneLike(registree, name, Consumers.nop());
    }

    @Nullable
    public static FurnitureSet get(ResourceLocation registryName) {
        return REGISTRY.get(registryName);
    }

    public static Set<ResourceLocation> getIds() {
        return Collections.unmodifiableSet(REGISTRY.keySet());
    }

    private static Set<BlockType<?>> blockTypes(FurnitureSetBuilder builder) {
        var blockTypes = Sets.newLinkedHashSet(builder.blockTypes.values());
        // core block type is required and must always be registered first
        blockTypes.addFirst(builder.coreBlockType);
        return Collections.unmodifiableSet(blockTypes);
    }
}
