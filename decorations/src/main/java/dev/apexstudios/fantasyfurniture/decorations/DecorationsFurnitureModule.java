package dev.apexstudios.fantasyfurniture.decorations;

import dev.apexstudios.apexcore.lib.registree.Registree;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DecorationsFurnitureModule.ID)
public class DecorationsFurnitureModule {
    public static final String ID = "fantasyfurniture_decorations";
    public static final Registree REGISTREE = new Registree(ID);

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB_KEY = REGISTREE.registerCreativeModeTab("items", () -> ItemStack.EMPTY, (parameters, output) -> REGISTREE
            .stream(Registries.ITEM)
            .filter(item -> item.isEnabled(parameters.enabledFeatures()))
            .forEach(output::accept)
    );

    public DecorationsFurnitureModule(IEventBus modBus) {
        DecoBlocks.register();
        DecorItems.register();

        REGISTREE.registerEvents(modBus);
    }
}
