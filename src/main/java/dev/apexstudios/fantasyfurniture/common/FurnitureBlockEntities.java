package dev.apexstudios.fantasyfurniture.common;

import dev.apexstudios.apexcore.api.block.entity.InventoryBlockEntity;
import dev.apexstudios.fantasyfurniture.common.block.entity.BookshelfBlockEntity;
import dev.apexstudios.fantasyfurniture.common.block.entity.FurnitureInventoryBlockEntity;
import dev.apexstudios.registree.holder.DeferredBlockEntity;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public interface FurnitureBlockEntities {
    DeferredBlockEntity<FurnitureInventoryBlockEntity> INVENTORY = FantasyFurniture.REGISTREE.blockEntity("inventory", FurnitureInventoryBlockEntity::new).register();
    DeferredBlockEntity<BookshelfBlockEntity> BOOKSHELF = FantasyFurniture.REGISTREE.blockEntity("bookshelf", BookshelfBlockEntity::new).register();

    static void register() {
        FantasyFurniture.REGISTREE.event(RegisterCapabilitiesEvent.class, event -> {
            InventoryBlockEntity.registerCapabilities(event, INVENTORY);
            InventoryBlockEntity.registerCapabilities(event, BOOKSHELF);
        });
    }
}
