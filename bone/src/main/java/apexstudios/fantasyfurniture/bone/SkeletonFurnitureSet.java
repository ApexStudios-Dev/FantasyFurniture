package apexstudios.fantasyfurniture.bone;

import java.util.Objects;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

@Mod(SkeletonFurnitureSet.ID)
public final class SkeletonFurnitureSet extends BoneFurnitureSet {
    public static final String ID = BoneFurnitureSet.ID + "_skeleton";
    @Nullable
    private static SkeletonFurnitureSet INSTANCE = null;

    public SkeletonFurnitureSet(IEventBus modBus) {
        super(modBus, ID, "skeleton");

        INSTANCE = this;
    }

    public static SkeletonFurnitureSet get() {
        return Objects.requireNonNull(INSTANCE);
    }
}
