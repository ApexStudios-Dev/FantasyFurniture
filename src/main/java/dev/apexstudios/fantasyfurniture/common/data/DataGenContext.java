package dev.apexstudios.fantasyfurniture.common.data;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.mojang.datafixers.util.Function3;
import dev.apexstudios.apexcore.api.util.TagPair;
import dev.apexstudios.registree.api.Registree;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.MultiRegistryBootstrap;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public record DataGenContext(
        Registree registree,
        String englishName,
        BlockFamily family,
        TagKey<Block> mineableTag,
        TagPair doorTag,
        TagKey<Block> stairsTag,
        TagPair buttonTag,
        TagPair pressurePlateTag,
        TagPair trapdoorTag,
        TagPair fenceTag,
        TagPair slabTag,
        Multimap<DataGenType, String> exclusions
) {
    public DataGenContext {
        exclusions = Multimaps.unmodifiableMultimap(exclusions);
    }

    public DataGenContext(
            Registree registree,
            String englishName,
            BlockFamily family,
            TagKey<Block> mineableTag,
            TagPair doorTag,
            TagKey<Block> stairsTag,
            TagPair buttonTag,
            TagPair pressurePlateTag,
            TagPair trapdoorTag,
            TagPair fenceTag,
            TagPair slabTag
    ) {
        this(registree, englishName, family, mineableTag, doorTag, stairsTag, buttonTag, pressurePlateTag, trapdoorTag, fenceTag, slabTag, HashMultimap.create());
    }

    public DataGenContext(
            Registree registree,
            String englishName,
            BlockFamily family,
            TagKey<Block> mineableTag,
            TagPair doorTag,
            TagKey<Block> stairsTag,
            TagPair buttonTag,
            TagPair pressurePlateTag,
            TagPair trapdoorTag,
            TagPair fenceTag,
            TagPair slabTag,
            Consumer<Multimap<DataGenType, String>> exclusions
    ) {
        this(registree, englishName, family, mineableTag, doorTag, stairsTag, buttonTag, pressurePlateTag, trapdoorTag, fenceTag, slabTag, Util.make(HashMultimap.create(), exclusions));
    }

    public boolean excluded(DataGenType dataType, String name) {
        return exclusions.get(dataType).contains(name);
    }

    public void ifAllowed(DataGenType dataType, String name, Runnable runnable) {
        if(!excluded(dataType, name)) {
            runnable.run();
        }
    }

    public <TRegistry> boolean ifPresent(DataGenType dataType, ResourceKey<? extends Registry<TRegistry>> registryType, String name, Consumer<? super TRegistry> action) {
        if(excluded(dataType, name)) {
            return true;
        }

        var value = registree.getValue(registryType, name);

        if(value != null) {
            action.accept(value);
            return true;
        }

        return false;
    }

    public boolean block(DataGenType dataType, String name, Consumer<? super Block> action) {
        return ifPresent(dataType, Registries.BLOCK, name, action);
    }

    public boolean item(DataGenType dataType, String name, Consumer<? super Item> action) {
        if(ifPresent(dataType, Registries.ITEM, name, action)) {
            return true;
        }

        return block(dataType, name, block -> action.accept(block.asItem()));
    }

    public <T extends DataProvider> GatherDataEvent.DataProviderFromOutput<T> fromOutput(BiFunction<PackOutput, DataGenContext, T> factory) {
        return output -> factory.apply(output, this);
    }

    public <T extends DataProvider> GatherDataEvent.DataProviderFromOutputLookup<T> fromOutputLookup(Function3<PackOutput, CompletableFuture<HolderLookup.Provider>, DataGenContext, T> factory) {
        return (output, lookupProvider) -> factory.apply(output, lookupProvider, this);
    }

    public MultiRegistryBootstrap forBootstrap(Function3<BootstrapContext<Recipe<?>>, BootstrapContext<Advancement>, DataGenContext, FurnitureRecipeProvider> factory) {
        return RecipeProvider.asBootstrap((recipes, advancements) -> factory.apply(recipes, advancements, this));
    }

    public LootTableProvider.SubProviderEntry forLootTable(BiFunction<LootTableSubProvider.Context, DataGenContext, FurnitureBlockLootSubProvider> factory) {
        return new LootTableProvider.SubProviderEntry(context -> factory.apply(context, this), LootContextParamSets.BLOCK);
    }

    public void registerBasicDataGen(GatherDataEvent event) {
        event.createReloadableRegistryObjects(new RegistrySetBuilder()
                .add(Registries.LOOT_TABLE, new LootTableProvider(Set.of(), List.of(forLootTable(FurnitureBlockLootSubProvider::new))))
                .add(forBootstrap(FurnitureRecipeProvider::new))
        );

        event.createProvider(fromOutput(FurnitureLanguageProviderUS::new));
        event.createProvider(fromOutput(FurnitureModelProvider::new));
        event.createProvider(fromOutputLookup(FurnitureBlockTagsProvider::new));
        event.createProvider(fromOutputLookup(FurnitureItemTagsProvider::new));
        event.createProvider(output -> PackMetadataGenerator.forFeaturePack(output, Component.literal(englishName + " Furniture Set resources")));
    }
}
