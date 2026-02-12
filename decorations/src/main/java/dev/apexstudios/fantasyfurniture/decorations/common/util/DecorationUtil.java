package dev.apexstudios.fantasyfurniture.decorations.common.util;

import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.decorations.common.block.BerryBasketBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.BonePileBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.BookStackBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.BowlBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.CandelabraBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.CandlesBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.ChalicesBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.CoinStackBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.FoodBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.Lightable;
import dev.apexstudios.fantasyfurniture.decorations.common.block.MuffinsBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.MushroomsBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.PlatterBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.PotteryBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.SoulGemsBlock;
import dev.apexstudios.fantasyfurniture.decorations.common.block.TankardsBlock;
import dev.apexstudios.registree.builder.BlockBuilder;
import dev.apexstudios.registree.registrar.BlockRegistrar;
import java.util.function.Function;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public interface DecorationUtil {
    static BlockBuilder<BerryBasketBlock> berryBasket(BlockRegistrar blocks, String berryType) {
        return FurnitureUtil.simpleBlock(blocks, berryType + "_basket", BerryBasketBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    }

    static BlockBuilder<BowlBlock> bowl(BlockRegistrar blocks, @Nullable String soup) {
        var name = "bowl";

        if(soup != null && !soup.isBlank()) {
            name = soup + '_' + name;
        }

        return FurnitureUtil.simpleBlock(blocks, name, BowlBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    }

    static BlockBuilder<CoinStackBlock> coinStack(BlockRegistrar blocks, String material) {
        return FurnitureUtil.simpleBlock(blocks, material + "_coin_stack", CoinStackBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    }

    static BlockBuilder<TankardsBlock> tankards(BlockRegistrar blocks, @Nullable String drink) {
        var name = "tankards";

        if(drink != null && !drink.isBlank()) {
            name = name + '_' + drink;
        }

        return FurnitureUtil.simpleBlock(blocks, name, TankardsBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    }

    static BlockBuilder<MushroomsBlock> mushrooms(BlockRegistrar blocks, boolean red) {
        var name = "mushrooms_" + (red ? "red" : "brown");
        return FurnitureUtil.simpleBlock(blocks, name, properties -> new MushroomsBlock(properties, red), FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    }

    static BlockBuilder<MuffinsBlock> muffins(BlockRegistrar blocks, String berry) {
        var name = "muffins_" + berry;
        return FurnitureUtil.simpleBlock(blocks, name, MuffinsBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    }

    static BlockBuilder<SoulGemsBlock> soulGems(BlockRegistrar blocks, String type) {
        return FurnitureUtil.simpleBlock(blocks, "soul_gems_" + type, SoulGemsBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    }

    static BlockBuilder<FoodBlock> food(BlockRegistrar blocks, int id) {
        return FurnitureUtil.simpleBlock(blocks, "food_" + id, FoodBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    }

    static BlockBuilder<PlatterBlock> platter(BlockRegistrar blocks, int id) {
        return FurnitureUtil.simpleBlock(blocks, "platter_" + id, PlatterBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    }

    static BlockBuilder<BookStackBlock> bookStack(BlockRegistrar blocks, int id) {
        return FurnitureUtil.simpleBlock(blocks, "book_stack_" + id, BookStackBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    }

    static <TBlock extends ChalicesBlock> BlockBuilder<TBlock> chalices(BlockRegistrar blocks, int id, Function<BlockBehaviour.Properties, TBlock> factory) {
        return FurnitureUtil.simpleBlock(blocks, "chalices_" + id, factory, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    }

    static BlockBuilder<CandlesBlock> candles(BlockRegistrar blocks, int id) {
        return FurnitureUtil.simpleBlock(blocks, "candles_" + id, CandlesBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, properties -> properties.noCollision().lightLevel(
                blockState -> ((Lightable) blockState.getBlock()).isLit(blockState) ? AbstractCandleBlock.LIGHT_PER_CANDLE * 4 : 0
        )));
    }

    static BlockBuilder<BonePileBlock> bonePile(BlockRegistrar blocks, String type) {
        return FurnitureUtil.simpleBlock(blocks, "bone_pile_" + type, BonePileBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, properties -> properties.noOcclusion().noCollision()));
    }

    static BlockBuilder<CandelabraBlock> candelabra(BlockRegistrar blocks, int id, VoxelShape baseShape) {
        return FurnitureUtil.simpleBlock(blocks, "candelabra_" + id, properties -> new CandelabraBlock(properties, baseShape), FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, properties -> properties.noCollision().lightLevel(
                blockState -> ((Lightable) blockState.getBlock()).isLit(blockState) ? AbstractCandleBlock.LIGHT_PER_CANDLE * 3 : 0
        )));
    }

    static BlockBuilder<PotteryBlock> pottery(BlockRegistrar blocks, int id, VoxelShape baseShape) {
        return FurnitureUtil.simpleBlock(blocks, "pottery_" + id, properties -> new PotteryBlock(properties, baseShape), FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    }
}
