package dev.apexstudios.fantasyfurniture;

import dev.apexstudios.apexcore.lib.component.block.entity.types.InventoryBlockEntityComponent;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlockEntity;
import dev.apexstudios.fantasyfurniture.block.entity.DresserBlockEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public interface FurnitureBlockEntities {
    DeferredBlockEntity<DresserBlockEntity> DRESSER = FantasyFurniture.REGISTREE.registerBlockEntity("dresser", DresserBlockEntity::new);

    static void register(IEventBus modBus) {
        modBus.addListener(RegisterCapabilitiesEvent.class, event -> {
            InventoryBlockEntityComponent.registerCapability(DRESSER.value(), event);
        });
    }
}
