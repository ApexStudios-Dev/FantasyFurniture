package dev.apexstudios.fantasyfurniture.ctm;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dev.apexstudios.apexcore.core.data.provider.BaseProvider;
import dev.apexstudios.apexcore.lib.data.provider.context.ProviderOutputContext;
import dev.apexstudios.registree.api.Registree;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.block.Block;

public abstract class SimpleJsonProvider implements BaseProvider {
    private static final Gson GSON = new GsonBuilder().create();

    private final Map<Block, Consumer<JsonObject>> map = Maps.newHashMap();

    protected abstract Path path(ProviderOutputContext context, Block block);

    protected JsonObject json(ProviderOutputContext context, Block block, Consumer<JsonObject> consumer) {
        var root = new JsonObject();
        consumer.accept(root);
        return root;
    }

    protected final void with(Block block, Consumer<JsonObject> consumer) {
        if(map.putIfAbsent(block, consumer) != null)
            throw new IllegalStateException("Duplicate Model registration: " + block);
    }

    @Override
    public CompletableFuture<?> generate(CachedOutput cache, ProviderOutputContext context) {
        var futures = Lists.<CompletableFuture<?>>newArrayList();
        map.forEach((block, consumer) -> futures.add(DataProvider.saveStable(cache, json(context, block, consumer), path(context, block))));
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    public static Identifier registryName(Block block) {
        return block.builtInRegistryHolder().key().identifier();
    }

    public static JsonObject existing(ResourceManager resourceManager, Identifier path) {
        try(var reader = resourceManager.openAsReader(path)) {
            return GsonHelper.fromJson(GSON, reader, JsonObject.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void copyInto(JsonObject from, JsonObject into) {
        from.keySet().forEach(key -> into.add(key, from.get(key).deepCopy()));
    }

    public static boolean isDyeable(Registree registree) {
        // TODO: Better check for dyeable blocks
        return registree.namespace().contains("royal");
    }
}
