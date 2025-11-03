package dev.apexstudios.fantasyfurniture.decorations;

import dev.apexstudios.registree.api.Registree;
import java.util.function.UnaryOperator;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DecorationsFurnitureModule.ID)
public class DecorationsFurnitureModule {
    public static final String ID = "fantasyfurniture_decorations";
    public static final Registree REGISTREE = Registree.create(ID);

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = REGISTREE.registerCreativeModeTab("items", UnaryOperator.identity());

    public DecorationsFurnitureModule(IEventBus modBus) {
        REGISTREE.registerEvents(modBus);
    }
}
