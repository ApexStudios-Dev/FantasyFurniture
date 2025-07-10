package dev.apexstudios.fantasyfurniture.decorations;

import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.fantasyfurniture.util.FurnitureUtil;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(DecorationsModule.ID)
public class DecorationsModule {
    public static final String ID = "fantasyfurniture_decorations";
    public static final Registree REGISTREE = new Registree(ID);

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = FurnitureUtil.creativeModeTab(REGISTREE, Items.BARRIER);

    public DecorationsModule(IEventBus modBus) {
        REGISTREE.registerEvents(modBus);
    }
}
