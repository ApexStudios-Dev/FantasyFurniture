package dev.apexstudios.fantasyfurniture.necrolord;

import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredParticleType;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordBedDoubleBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordBedSingleBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordBenchBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordBookshelfBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordChairBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordChandelierBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordChestBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordCushionBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordDeskBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordDrawerBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordDresserBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordFloorLightBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordLockBoxBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordPaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordPaintingWideBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordShelfBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordSofaBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordStoolBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordTableBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordWallLightBlock;
import dev.apexstudios.fantasyfurniture.necrolord.block.NecrolordWardrobeBlock;
import dev.apexstudios.fantasyfurniture.set.BlockTypes;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import dev.apexstudios.fantasyfurniture.set.function.BlockFactory;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NecrolordFurnitureSet.ID)
public class NecrolordFurnitureSet {
    public static final String ID = "fantasyfurniture_necrolord";
    public static final Registree REGISTREE = new Registree(ID);

    public static final DeferredParticleType<SimpleParticleType, SimpleParticleType> FLAME_PARTICLE = REGISTREE.registerSimpleParticle("flame", false);

    public static final FurnitureSet FURNITURE_SET = FurnitureSet.createStoneLike(REGISTREE, "necrolord", $ -> $
            .with(BlockTypes.BED_DOUBLE.extend(BlockFactory.wrapping(NecrolordBedDoubleBlock::new)))
            .with(BlockTypes.BED_SINGLE.extend(BlockFactory.wrapping(NecrolordBedSingleBlock::new)))
            .with(BlockTypes.BENCH.extend(BlockFactory.wrapping(NecrolordBenchBlock::new)))
            .with(BlockTypes.BOOKSHELF.extend(BlockFactory.wrapping(NecrolordBookshelfBlock::new)))
            .with(BlockTypes.CHAIR.extend(BlockFactory.wrapping(NecrolordChairBlock::new)))
            .with(BlockTypes.CHANDELIER.extend(BlockFactory.wrapping(NecrolordChandelierBlock::new)))
            .with(BlockTypes.CHEST.extend(BlockFactory.wrapping(NecrolordChestBlock::new)))
            .with(BlockTypes.CUSHION.extend(BlockFactory.wrapping(NecrolordCushionBlock::new)))
            .with(BlockTypes.DESK_LEFT.extend((furnitureSet, properties) -> new NecrolordDeskBlock(properties, true)))
            .with(BlockTypes.DESK_RIGHT.extend((furnitureSet, properties) -> new NecrolordDeskBlock(properties, false)))
            .with(BlockTypes.DRAWER.extend(BlockFactory.wrapping(NecrolordDrawerBlock::new)))
            .with(BlockTypes.DRESSER.extend(BlockFactory.wrapping(NecrolordDresserBlock::new)))
            .with(BlockTypes.FLOOR_LIGHT.extend(BlockFactory.wrapping(NecrolordFloorLightBlock::new)))
            .with(BlockTypes.LOCKBOX.extend(BlockFactory.wrapping(NecrolordLockBoxBlock::new)))
            .with(BlockTypes.PAINTING_SMALL.extend(BlockFactory.wrapping(NecrolordPaintingSmallBlock::new)))
            .with(BlockTypes.PAINTING_WIDE.extend(BlockFactory.wrapping(NecrolordPaintingWideBlock::new)))
            .with(BlockTypes.SHELF.extend(BlockFactory.wrapping(NecrolordShelfBlock::new)))
            .with(BlockTypes.SOFA.extend(BlockFactory.wrapping(NecrolordSofaBlock::new)))
            .with(BlockTypes.STOOL.extend(BlockFactory.wrapping(NecrolordStoolBlock::new)))
            .with(BlockTypes.TABLE.extend(BlockFactory.wrapping(NecrolordTableBlock::new)))
            .with(BlockTypes.WALL_LIGHT.extend(BlockFactory.wrapping(NecrolordWallLightBlock::new)))
            .with(BlockTypes.WARDROBE.extend(BlockFactory.wrapping(NecrolordWardrobeBlock::new)))
            .flame(FLAME_PARTICLE)
    );

    public NecrolordFurnitureSet(IEventBus modBus) {
        FURNITURE_SET.register(modBus);
        REGISTREE.registerEvents(modBus);
    }
}
