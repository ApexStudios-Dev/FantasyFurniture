package dev.apexstudios.fantasyfurniture;

import dev.apexstudios.apexcore.lib.component.ComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.entity.BlockEntityComponent;
import dev.apexstudios.apexcore.lib.component.block.entity.types.InventoryBlockEntityComponent;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlockEntity;
import dev.apexstudios.fantasyfurniture.block.entity.BookshelfBlockEntity;
import dev.apexstudios.fantasyfurniture.block.entity.ChestBlockEntity;
import dev.apexstudios.fantasyfurniture.block.entity.CounterBlockEntity;
import dev.apexstudios.fantasyfurniture.block.entity.DeskBlockEntity;
import dev.apexstudios.fantasyfurniture.block.entity.DrawerBlockEntity;
import dev.apexstudios.fantasyfurniture.block.entity.DresserBlockEntity;
import dev.apexstudios.fantasyfurniture.block.entity.LockBoxBlockEntity;
import dev.apexstudios.fantasyfurniture.block.entity.WardrobeBlockEntity;
import dev.apexstudios.fantasyfurniture.oven.OvenBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public interface FurnitureBlockEntities {
    DeferredBlockEntity<DresserBlockEntity> DRESSER = FantasyFurniture.REGISTREE.registerBlockEntity("dresser", DresserBlockEntity::new);
    DeferredBlockEntity<LockBoxBlockEntity> LOCKBOX = FantasyFurniture.REGISTREE.registerBlockEntity("lockbox", LockBoxBlockEntity::new);
    DeferredBlockEntity<DrawerBlockEntity> DRAWER = FantasyFurniture.REGISTREE.registerBlockEntity("drawer", DrawerBlockEntity::new);
    DeferredBlockEntity<BookshelfBlockEntity> BOOKSHELF = FantasyFurniture.REGISTREE.registerBlockEntity("bookshelf", BookshelfBlockEntity::new);
    DeferredBlockEntity<DeskBlockEntity> DESK = FantasyFurniture.REGISTREE.registerBlockEntity("desk", DeskBlockEntity::new);
    DeferredBlockEntity<OvenBlockEntity> OVEN = FantasyFurniture.REGISTREE.registerBlockEntity("oven", OvenBlockEntity::new);
    DeferredBlockEntity<ChestBlockEntity> CHEST = FantasyFurniture.REGISTREE.registerBlockEntity("chest", ChestBlockEntity::new);
    DeferredBlockEntity<CounterBlockEntity> COUNTER = FantasyFurniture.REGISTREE.registerBlockEntity("counter", CounterBlockEntity::new);
    DeferredBlockEntity<WardrobeBlockEntity> WARDROBE = FantasyFurniture.REGISTREE.registerBlockEntity("wardrobe", WardrobeBlockEntity::new);

    static void register(IEventBus modBus) {
        modBus.addListener(RegisterCapabilitiesEvent.class, event -> capabilities(event,
                DRESSER, LOCKBOX, DRAWER, BOOKSHELF, DESK, OVEN, CHEST, COUNTER, WARDROBE
        ));
    }

    @SafeVarargs
    private static <TBlockEntity extends BlockEntity & ComponentHolder<BlockEntityComponent, BlockEntity>> void capabilities(RegisterCapabilitiesEvent event, DeferredBlockEntity<? extends TBlockEntity>... holders) {
        for(var holder : holders) {
            InventoryBlockEntityComponent.registerCapability(holder.value(), event);
        }
    }
}
