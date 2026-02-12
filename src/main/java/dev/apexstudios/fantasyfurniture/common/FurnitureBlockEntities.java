package dev.apexstudios.fantasyfurniture.common;

import dev.apexstudios.apexcore.api.block.entity.InventoryBlockEntity;
import dev.apexstudios.fantasyfurniture.common.block.entity.BookshelfBlockEntity;
import dev.apexstudios.fantasyfurniture.common.block.entity.FurnitureInventoryBlockEntity;
import dev.apexstudios.registree.holder.DeferredBlockEntityType;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public interface FurnitureBlockEntities {
    DeferredBlockEntityType<FurnitureInventoryBlockEntity> INVENTORY = FantasyFurniture.BLOCK_ENTITY_TYPES.register("inventory", FurnitureInventoryBlockEntity::new);
    DeferredBlockEntityType<FurnitureInventoryBlockEntity> BOOKSHELF = FantasyFurniture.BLOCK_ENTITY_TYPES.register("bookshelf", BookshelfBlockEntity::new);

    static void register() {
        FantasyFurniture.REGISTREE.event(RegisterCapabilitiesEvent.class, event -> {
            InventoryBlockEntity.registerCapabilities(event, INVENTORY);
            InventoryBlockEntity.registerCapabilities(event, BOOKSHELF);
        });
    }
}
