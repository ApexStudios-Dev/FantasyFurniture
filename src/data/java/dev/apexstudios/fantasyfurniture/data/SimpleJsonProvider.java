package dev.apexstudios.fantasyfurniture.data;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dev.apexstudios.registree.api.Registree;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.block.Block;

public abstract class SimpleJsonProvider implements DataProvider {
    private static final Gson GSON = new GsonBuilder().create();

    private final Map<Block, Consumer<JsonObject>> map = Maps.newHashMap();
    protected final PackOutput output;
    protected final ResourceManager resourceManager;

    protected SimpleJsonProvider(PackOutput output, ResourceManager resourceManager) {
        this.output = output;
        this.resourceManager = resourceManager;
    }

    protected abstract Path path(Block block);

    protected JsonObject json(Block block, Consumer<JsonObject> consumer) {
        var root = new JsonObject();
        consumer.accept(root);
        return root;
    }

    protected final void with(Block block, Consumer<JsonObject> consumer) {
        if(map.putIfAbsent(block, consumer) != null)
            throw new IllegalStateException("Duplicate Model registration: " + block);
    }

    protected Path path(PackOutput.Target target, Identifier identifier) {
        return output.getOutputFolder(target)
                .resolve(identifier.getNamespace())
                .resolve(identifier.getPath());
    }

    protected JsonObject existing(Identifier path) {
        try(var reader = resourceManager.openAsReader(path)) {
            return GsonHelper.fromJson(GSON, reader, JsonObject.class);
        } catch (IOException e) {
            return new JsonObject();
        }
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        var futures = Lists.<CompletableFuture<?>>newArrayList();
        map.forEach((block, consumer) -> futures.add(DataProvider.saveStable(cache, json(block, consumer), path(block))));
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    public static Identifier registryName(Block block) {
        return block.builtInRegistryHolder().key().identifier();
    }

    public static void copyInto(JsonObject from, JsonObject into) {
        from.keySet().forEach(key -> into.add(key, from.get(key).deepCopy()));
    }

    public static boolean isDyeable(Registree registree) {
        // TODO: Better check for dyeable blocks
        return registree.namespace().contains("royal");
    }
}
