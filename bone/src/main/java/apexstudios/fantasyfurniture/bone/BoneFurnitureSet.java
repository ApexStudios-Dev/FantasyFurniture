package apexstudios.fantasyfurniture.bone;

import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.set.BlockTypes;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(BoneFurnitureSet.ID)
public final class BoneFurnitureSet {
    public static final String ID = FantasyFurniture.ID + "_bone";
    public static final Registree REGISTREE = new Registree(ID);
    public static final FurnitureSet SKELETON = furnitureSet("skeleton");
    public static final FurnitureSet WITHER = furnitureSet("wither");

    public BoneFurnitureSet(IEventBus modBus) {
        SKELETON.register(modBus);
        WITHER.register(modBus);
        REGISTREE.registerEvents(modBus);
    }

    private static FurnitureSet furnitureSet(String name) {
        return FurnitureSet.createStoneLike(REGISTREE, name, builder -> builder
                .usesPrefix()
                .baseBlock(() -> Blocks.BONE_BLOCK)
                .woodType(woodType -> woodType
                        .soundType(SoundType.BONE_BLOCK)
                        .hangingSignSoundType(SoundType.BONE_BLOCK)
                )
                .blockSet(blockSet -> blockSet.soundType(SoundType.BONE_BLOCK))
                .wool(Items.SOUL_SAND)
                .remove(BlockTypes.WOOL, BlockTypes.CARPET)
        );
    }
}
