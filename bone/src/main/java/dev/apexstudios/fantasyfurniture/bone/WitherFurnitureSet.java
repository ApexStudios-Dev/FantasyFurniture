package dev.apexstudios.fantasyfurniture.bone;

import java.util.Objects;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

@Mod(WitherFurnitureSet.ID)
public final class WitherFurnitureSet extends BoneFurnitureSet {
    public static final String ID = BoneFurnitureSet.ID + "_wither";
    @Nullable private static WitherFurnitureSet INSTANCE = null;

    public WitherFurnitureSet(IEventBus modBus) {
        super(modBus, ID, "wither");

        INSTANCE = this;
    }

    public static WitherFurnitureSet get() {
        return Objects.requireNonNull(INSTANCE);
    }
}
