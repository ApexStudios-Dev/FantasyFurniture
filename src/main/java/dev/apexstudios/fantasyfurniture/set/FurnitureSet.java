package dev.apexstudios.fantasyfurniture.set;

import com.google.common.collect.Sets;
import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.apexcore.lib.registree.Registree;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import org.apache.commons.lang3.StringUtils;

public final class FurnitureSet {
    private static final Map<String, CtmPack> CTM_PACKS = Map.of(
            "athena", new CtmPack("ctm-athena", "Athena CTM"),
            "fusion", new CtmPack("ctm-fusion", "Fusion CTM"),
            "ctm", new CtmPack("ctm", "CTM")
    );

    private final Registree registree;
    private final String name;
    private final Set<BlockType<?>> blockTypes;
    private final WoodType woodType;
    private final ResourceKey<CreativeModeTab> creativeModeTab;

    private FurnitureSet(FurnitureSetBuilder builder) {
        registree = builder.registree;
        name = builder.name;
        blockTypes = Collections.unmodifiableSet(Sets.newLinkedHashSet(builder.blockTypes.values()));
        woodType = builder.woodType.build(name + "_wood_type", name + "_block_set");

        creativeModeTab = registree.registerCreativeModeTab(name, () -> new ItemStack(getOrThrow(BlockTypes.WOOL)), (parameters, output) -> {
            blockTypes.forEach(blockType -> {
                var block = getOrThrow(blockType);

                if(block.isEnabled(parameters.enabledFeatures()))
                    output.accept(block);
            });
        });
    }

    public void register(IEventBus modBus) {
        for(var blockType : blockTypes) {
            blockType.register(modBus, this, registree);
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
                Sheets.HANGING_SIGN_MATERIALS.put(woodType, Sheets.createHangingSignMaterial(registree.registryName(name)));
            if(isRegistered(BlockTypes.SIGN) || isRegistered(BlockTypes.WALL_SIGN))
                Sheets.SIGN_MATERIALS.put(woodType, Sheets.createSignMaterial(registree.registryName(name)));

            ifRegistered(BlockTypes.TRAP_DOOR, block -> ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutout()));
        }));

        modBus.addListener(AddPackFindersEvent.class, event -> CTM_PACKS.entrySet().stream()
                .filter(entry -> ModList.get().isLoaded(entry.getKey()))
                .map(Map.Entry::getValue)
                .forEach(pack -> event.addPackFinders(
                        registree.registryName("packs/" + pack.packId),
                        PackType.CLIENT_RESOURCES,
                        Component.literal(pack.packName + " (" + StringUtils.capitalize(name) + ')'),
                        PackSource.BUILT_IN,
                        false,
                        Pack.Position.TOP
                ))
        );
    }

    public void registerDataGen(ResourceGenerator generator) {
        var pack = generator.pack();

        for(var blockType : blockTypes) {
            blockType.registerDataGen(pack, this);
        }

        pack.providing(ProviderTypes.LANGUAGE, (context, provider) -> provider.addCreativeModeTab(creativeModeTab, StringUtils.capitalize(name)));

        CTM_PACKS.forEach((modId, ctm) -> {
            generator.pack(ctm.packId)
                    .description("Enables " + ctm.packName + " support");
        });
    }

    public String ownerNamespace() {
        return registree.namespace();
    }

    public String name() {
        return name;
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
        return registree.containsKey(Registries.BLOCK, blockType.registryName);
    }

    public <TBlock extends Block> Optional<TBlock> get(BlockType<TBlock> blockType) {
        return registree.getOptional(Registries.BLOCK, blockType.registryName).map(value -> (TBlock) value);
    }

    public <TBlock extends Block> TBlock getOrThrow(BlockType<TBlock> blockType) {
        return get(blockType).orElseThrow();
    }

    public <TBlock extends Block> void ifRegistered(BlockType<TBlock> blockType, Consumer<TBlock> consumer) {
        get(blockType).ifPresent(consumer);
    }

    public boolean is(ItemStack stack) {
        return registree.listElements(Registries.ITEM).anyMatch(stack::is);
    }

    public boolean is(BlockState blockState) {
        return registree.listElements(Registries.BLOCK).anyMatch(blockState::is);
    }

    public boolean is(Block block) {
        return registree.listElements(Registries.BLOCK).map(Holder::value).anyMatch(value -> value == block);
    }

    public boolean is(Item item) {
        return registree.listElements(Registries.ITEM).map(Holder::value).anyMatch(value -> value == item);
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

    public static FurnitureSet create(Registree registree, String name, UnaryOperator<FurnitureSetBuilder> builder) {
        return builder.andThen(FurnitureSet::new).apply(new FurnitureSetBuilder(registree, name));
    }

    public static FurnitureSet create(Registree registree, String name) {
        return create(registree, name, UnaryOperator.identity());
    }

    private record CtmPack(String packId, String packName) { }
}
