package apexstudios.fantasyfurniture.bone;

import apexstudios.fantasyfurniture.bone.block.BoneBedDoubleBlock;
import apexstudios.fantasyfurniture.bone.block.BoneBedSingleBlock;
import apexstudios.fantasyfurniture.bone.block.BoneBenchBlock;
import apexstudios.fantasyfurniture.bone.block.BoneBookshelfBlock;
import apexstudios.fantasyfurniture.bone.block.BoneChairBlock;
import apexstudios.fantasyfurniture.bone.block.BoneChandelierBlock;
import apexstudios.fantasyfurniture.bone.block.BoneChestBlock;
import apexstudios.fantasyfurniture.bone.block.BoneCushionBlock;
import apexstudios.fantasyfurniture.bone.block.BoneDeskBlock;
import apexstudios.fantasyfurniture.bone.block.BoneDrawerBlock;
import apexstudios.fantasyfurniture.bone.block.BoneDresserBlock;
import apexstudios.fantasyfurniture.bone.block.BoneFloorLightBlock;
import apexstudios.fantasyfurniture.bone.block.BoneLockBoxBlock;
import apexstudios.fantasyfurniture.bone.block.BonePaintingSmallBlock;
import apexstudios.fantasyfurniture.bone.block.BonePaintingWideBlock;
import apexstudios.fantasyfurniture.bone.block.BoneShelfBlock;
import apexstudios.fantasyfurniture.bone.block.BoneSofaBlock;
import apexstudios.fantasyfurniture.bone.block.BoneStoolBlock;
import apexstudios.fantasyfurniture.bone.block.BoneTableBlock;
import apexstudios.fantasyfurniture.bone.block.BoneWallLightBlock;
import apexstudios.fantasyfurniture.bone.block.BoneWardrobeBlock;
import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.set.BlockTypes;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import dev.apexstudios.fantasyfurniture.set.FurnitureSetBuilder;
import dev.apexstudios.fantasyfurniture.set.function.BlockFactory;
import java.util.function.Consumer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.apache.commons.lang3.function.Consumers;

@Mod(BoneFurnitureSet.ID)
public final class BoneFurnitureSet {
    public static final String ID = FantasyFurniture.ID + "_bone";
    public static final Registree REGISTREE = new Registree(ID);
    public static final FurnitureSet SKELETON = furnitureSet("skeleton", Consumers.nop());
    public static final FurnitureSet WITHER = furnitureSet("wither", builder -> builder.flame(() -> ParticleTypes.SOUL_FIRE_FLAME));

    public BoneFurnitureSet(IEventBus modBus) {
        SKELETON.register(modBus);
        WITHER.register(modBus);
        REGISTREE.registerEvents(modBus);
    }

    private static FurnitureSet furnitureSet(String name, Consumer<FurnitureSetBuilder> modifier) {
        return FurnitureSet.createStoneLike(REGISTREE, name, builder -> {
            builder.usesPrefix()
                    .baseBlock(() -> Blocks.BONE_BLOCK)
                    .with(BlockTypes.FLOOR_LIGHT.extend(BoneFloorLightBlock::new))
                    .with(BlockTypes.DRESSER.extend(BlockFactory.wrapping(BoneDresserBlock::new)))
                    .with(BlockTypes.BOOKSHELF.extend(BlockFactory.wrapping(BoneBookshelfBlock::new)))
                    .with(BlockTypes.PAINTING_SMALL.extend(BlockFactory.wrapping(BonePaintingSmallBlock::new)))
                    .with(BlockTypes.PAINTING_WIDE.extend(BlockFactory.wrapping(BonePaintingWideBlock::new)))
                    .with(BlockTypes.WARDROBE.extend(BlockFactory.wrapping(BoneWardrobeBlock::new)))
                    .with(BlockTypes.CUSHION.copy(copier -> copier.builder($ -> $.translation("\"Cushion\"")).blockFactory(BlockFactory.wrapping(BoneCushionBlock::new))))
                    .with(BlockTypes.STOOL.extend(BlockFactory.wrapping(BoneStoolBlock::new)))
                    .with(BlockTypes.LOCKBOX.extend(BlockFactory.wrapping(BoneLockBoxBlock::new)))
                    .with(BlockTypes.CHAIR.extend(BlockFactory.wrapping(BoneChairBlock::new)))
                    .with(BlockTypes.BED_SINGLE.extend(BlockFactory.wrapping(BoneBedSingleBlock::new)))
                    .with(BlockTypes.BED_DOUBLE.extend(BlockFactory.wrapping(BoneBedDoubleBlock::new)))
                    .with(BlockTypes.DESK_LEFT.extend((furnitureSet, properties) -> new BoneDeskBlock(properties, true)))
                    .with(BlockTypes.DESK_RIGHT.extend((furnitureSet, properties) -> new BoneDeskBlock(properties, false)))
                    .with(BlockTypes.CHEST.extend(BlockFactory.wrapping(BoneChestBlock::new)))
                    .with(BlockTypes.DRAWER.extend(BlockFactory.wrapping(BoneDrawerBlock::new)))
                    .with(BlockTypes.CHANDELIER.copy($ -> $
                            .blockFactory(BoneChandelierBlock::new)
                            .builder($$ -> $$.blockProperties(BlockBehaviour.Properties::noOcclusion))
                    ))
                    .with(BlockTypes.SHELF.extend(BlockFactory.wrapping(BoneShelfBlock::new)))
                    .with(BlockTypes.SOFA.extend(BlockFactory.wrapping(BoneSofaBlock::new)))
                    .with(BlockTypes.WALL_LIGHT.extend(BoneWallLightBlock::new))
                    .with(BlockTypes.BENCH.extend(BlockFactory.wrapping(BoneBenchBlock::new)))
                    .with(BlockTypes.TABLE.extend(BlockFactory.wrapping(BoneTableBlock::new)))
                    .woodType(woodType -> woodType
                            .soundType(SoundType.BONE_BLOCK)
                            .hangingSignSoundType(SoundType.BONE_BLOCK)
                    )
                    .blockSet(blockSet -> blockSet.soundType(SoundType.BONE_BLOCK))
                    .wool(Items.SOUL_SAND)
                    .remove(BlockTypes.WOOL, BlockTypes.CARPET, BlockTypes.FENCE_GATE);

            modifier.accept(builder);
        });
    }
}
