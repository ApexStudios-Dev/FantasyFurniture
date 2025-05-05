package dev.apexstudios.fantasyfurniture;

import dev.apexstudios.apexcore.lib.block.entity.InventoryBlockEntity;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlockEntity;
import dev.apexstudios.fantasyfurniture.block.entity.BookshelfBlockEntity;
import dev.apexstudios.fantasyfurniture.block.entity.FurnitureInventoryBlockEntity;
import dev.apexstudios.fantasyfurniture.oven.OvenBlockEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public interface FurnitureBlockEntities {
    DeferredBlockEntity<FurnitureInventoryBlockEntity> INVENTORY = FantasyFurniture.REGISTREE.registerBlockEntity("inventory", FurnitureInventoryBlockEntity::new);
    DeferredBlockEntity<FurnitureInventoryBlockEntity> BOOKSHELF = FantasyFurniture.REGISTREE.registerBlockEntity("bookshelf", BookshelfBlockEntity::new);
    DeferredBlockEntity<OvenBlockEntity> OVEN = FantasyFurniture.REGISTREE.registerBlockEntity("oven", OvenBlockEntity::new);

    static void register(IEventBus modBus) {
        modBus.addListener(RegisterCapabilitiesEvent.class, event -> {
            InventoryBlockEntity.registerCapabilities(event, OVEN);
            InventoryBlockEntity.registerCapabilities(event, INVENTORY);
            InventoryBlockEntity.registerCapabilities(event, BOOKSHELF);
        });
    }
}
