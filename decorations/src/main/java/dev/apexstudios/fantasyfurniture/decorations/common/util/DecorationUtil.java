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
import dev.apexstudios.registree.api.Registree;
import dev.apexstudios.registree.api.holder.DeferredBlock;
import java.util.function.Function;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public interface DecorationUtil {
    static DeferredBlock<BerryBasketBlock> berryBasket(Registree registree, String berryType) {
        return FurnitureUtil.simpleBlock(registree, berryType + "_basket", BerryBasketBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    }

    static DeferredBlock<BowlBlock> bowl(Registree registree, @Nullable String soup) {
        var name = "bowl";

        if(soup != null && !soup.isBlank()) {
            name = soup + '_' + name;
        }

        return FurnitureUtil.simpleBlock(registree, name, BowlBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    }

    static DeferredBlock<CoinStackBlock> coinStack(Registree registree, String material) {
        return FurnitureUtil.simpleBlock(registree, material + "_coin_stack", CoinStackBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    }

    static DeferredBlock<TankardsBlock> tankards(Registree registree, @Nullable String drink) {
        var name = "tankards";

        if(drink != null && !drink.isBlank()) {
            name = name + '_' + drink;
        }

        return FurnitureUtil.simpleBlock(registree, name, TankardsBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    }

    static DeferredBlock<MushroomsBlock> mushrooms(Registree registree, boolean red) {
        var name = "mushrooms_" + (red ? "red" : "brown");
        return FurnitureUtil.simpleBlock(registree, name, properties -> new MushroomsBlock(properties, red), FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    }

    static DeferredBlock<MuffinsBlock> muffins(Registree registree, String berry) {
        var name = "muffins_" + berry;
        return FurnitureUtil.simpleBlock(registree, name, MuffinsBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    }

    static DeferredBlock<SoulGemsBlock> soulGems(Registree registree, String type) {
        return FurnitureUtil.simpleBlock(registree, "soul_gems_" + type, SoulGemsBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    }

    static DeferredBlock<FoodBlock> food(Registree registree, int id) {
        return FurnitureUtil.simpleBlock(registree, "food_" + id, FoodBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    }

    static DeferredBlock<PlatterBlock> platter(Registree registree, int id) {
        return FurnitureUtil.simpleBlock(registree, "platter_" + id, PlatterBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    }

    static DeferredBlock<BookStackBlock> bookStack(Registree registree, int id) {
        return FurnitureUtil.simpleBlock(registree, "book_stack_" + id, BookStackBlock::new, FurnitureUtil.PLANK_PROPERTIES);
    }

    static <TBlock extends ChalicesBlock> DeferredBlock<TBlock> chalices(Registree registree, int id, Function<BlockBehaviour.Properties, TBlock> factory) {
        return FurnitureUtil.simpleBlock(registree, "chalices_" + id, factory, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    }

    static DeferredBlock<CandlesBlock> candles(Registree registree, int id) {
        return FurnitureUtil.simpleBlock(registree, "candles_" + id, CandlesBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, properties -> properties.noCollision().lightLevel(
                blockState -> ((Lightable) blockState.getBlock()).isLit(blockState) ? AbstractCandleBlock.LIGHT_PER_CANDLE * 4 : 0
        )));
    }

    static DeferredBlock<BonePileBlock> bonePile(Registree registree, String type) {
        return FurnitureUtil.simpleBlock(registree, "bone_pile_" + type, BonePileBlock::new, FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, properties -> properties.noOcclusion().noCollision()));
    }

    static DeferredBlock<CandelabraBlock> candelabra(Registree registree, int id, VoxelShape baseShape) {
        return FurnitureUtil.simpleBlock(registree, "candelabra_" + id, properties -> new CandelabraBlock(properties, baseShape), FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, properties -> properties.noCollision().lightLevel(
                blockState -> ((Lightable) blockState.getBlock()).isLit(blockState) ? AbstractCandleBlock.LIGHT_PER_CANDLE * 3 : 0
        )));
    }

    static DeferredBlock<PotteryBlock> pottery(Registree registree, int id, VoxelShape baseShape) {
        return FurnitureUtil.simpleBlock(registree, "pottery_" + id, properties -> new PotteryBlock(properties, baseShape), FurnitureUtil.mutating(FurnitureUtil.PLANK_PROPERTIES, BlockBehaviour.Properties::noCollision));
    }
}
