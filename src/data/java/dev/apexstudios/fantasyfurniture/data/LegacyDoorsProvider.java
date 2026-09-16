package dev.apexstudios.fantasyfurniture.data;

import dev.apexstudios.apexcore.client.DyeColorItemTintSource;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;

final class LegacyDoorsProvider implements DataProvider {
    private final PackOutput output;
    private final ResourceManager resourceManager;
    private final Map<Identifier, ClientItem> models = new HashMap<>();

    LegacyDoorsProvider(PackOutput output, ResourceManager resourceManager) {
        this.output = output;
        this.resourceManager = resourceManager;
    }

    private void registerModels() {
        FantasyFurnitureDataEntryPoint.forEachModule((modId, dyeable) -> {
            process(Identifier.fromNamespaceAndPath(modId, FurnitureUtil.Names.DOOR_SINGLE), dyeable);
            process(Identifier.fromNamespaceAndPath(modId, FurnitureUtil.Names.DOOR_DOUBLE), dyeable);
        });
    }

    private void process(Identifier itemId, boolean dyeable) {
        var legacyModel = itemId.withSuffix("_3d");

        if(FantasyFurnitureDataEntryPoint.loadExisting(resourceManager, "models/item", itemId) == null) {
            return;
        }

        var corrected = legacyModel.withPrefix("item/");
        models.put(itemId, new ClientItem(dyeable ? ItemModelUtils.tintedModel(corrected, new DyeColorItemTintSource()) : ItemModelUtils.plainModel(corrected), ClientItem.Properties.DEFAULT));
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        registerModels();
        var pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "items");

        return DataProvider.saveAll(
                cache,
                ClientItem.CODEC,
                pathProvider::json,
                models
        );
    }

    @Override
    public String getName() {
        return "legacy-doors";
    }
}
