package dev.apexstudios.fantasyfurniture.decorations.common.util;

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
import dev.apexstudios.registree.BaseRegistree;
import dev.apexstudios.registree.builder.BlockBuilder;
import java.util.function.Function;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public interface DecorationUtil {
    static BlockBuilder<BerryBasketBlock> berryBasket(BaseRegistree<?> registree, String berryType) {
        return registree.block(berryType + "_basket", BerryBasketBlock::new)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .item();
    }

    static BlockBuilder<BowlBlock> bowl(BaseRegistree<?> registree, @Nullable String soup) {
        var name = "bowl";

        if(soup != null && !soup.isBlank()) {
            name = soup + '_' + name;
        }

        return registree.block(name, BowlBlock::new)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .properties(BlockBehaviour.Properties::noCollision)
                .item();
    }

    static BlockBuilder<CoinStackBlock> coinStack(BaseRegistree<?> registree, String material) {
        return registree.block(material + "_coin_stack", CoinStackBlock::new)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .properties(BlockBehaviour.Properties::noCollision)
                .item();
    }

    static BlockBuilder<TankardsBlock> tankards(BaseRegistree<?> registree, @Nullable String drink) {
        var name = "tankards";

        if(drink != null && !drink.isBlank()) {
            name = name + '_' + drink;
        }

        return registree.block(name, TankardsBlock::new)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .properties(BlockBehaviour.Properties::noCollision)
                .item();
    }

    static BlockBuilder<MushroomsBlock> mushrooms(BaseRegistree<?> registree, boolean red) {
        return registree.block("mushrooms_" + (red ? "red" : "brown"), properties -> new MushroomsBlock(properties, red))
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .properties(BlockBehaviour.Properties::noCollision)
                .item();
    }

    static BlockBuilder<MuffinsBlock> muffins(BaseRegistree<?> registree, String berry) {
        return registree.block("muffins_" + berry, MuffinsBlock::new)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .properties(BlockBehaviour.Properties::noCollision)
                .item();
    }

    static BlockBuilder<SoulGemsBlock> soulGems(BaseRegistree<?> registree, String type) {
        return registree.block("soul_gems_" + type, SoulGemsBlock::new)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .properties(BlockBehaviour.Properties::noCollision)
                .item();
    }

    static BlockBuilder<FoodBlock> food(BaseRegistree<?> registree, int id) {
        return registree.block("food_" + id, FoodBlock::new)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .properties(BlockBehaviour.Properties::noCollision)
                .item();
    }

    static BlockBuilder<PlatterBlock> platter(BaseRegistree<?> registree, int id) {
        return registree.block("platter_" + id, PlatterBlock::new)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .item();
    }

    static BlockBuilder<BookStackBlock> bookStack(BaseRegistree<?> registree, int id) {
        return registree.block("book_stack_" + id, BookStackBlock::new)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .item();
    }

    static <TBlock extends ChalicesBlock> BlockBuilder<TBlock> chalices(BaseRegistree<?> registree, int id, Function<BlockBehaviour.Properties, TBlock> factory) {
        return registree.block("chalices_" + id, factory)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .properties(BlockBehaviour.Properties::noCollision)
                .item();
    }

    static BlockBuilder<CandlesBlock> candles(BaseRegistree<?> registree, int id) {
        return registree.block("candles_" + id, CandlesBlock::new)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .properties(properties -> properties
                        .noCollision()
                        .lightLevel(blockState -> ((Lightable) blockState.getBlock()).isLit(blockState) ? AbstractCandleBlock.LIGHT_PER_CANDLE * 4 : 0)
                )
                .item();
    }

    static BlockBuilder<BonePileBlock> bonePile(BaseRegistree<?> registree, String type) {
        return registree.block("bone_pile_" + type, BonePileBlock::new)
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .properties(properties -> properties
                        .noOcclusion()
                        .noCollision()
                )
                .item();
    }

    static BlockBuilder<CandelabraBlock> candelabra(BaseRegistree<?> registree, int id, VoxelShape baseShape) {
        return registree.block("candelabra_" + id, properties -> new CandelabraBlock(properties, baseShape))
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .properties(properties -> properties
                        .noCollision()
                        .lightLevel(blockState -> ((Lightable) blockState.getBlock()).isLit(blockState) ? AbstractCandleBlock.LIGHT_PER_CANDLE * 3 : 0)
                )
                .item();
    }

    static BlockBuilder<PotteryBlock> pottery(BaseRegistree<?> registree, int id, VoxelShape baseShape) {
        return registree.block("pottery_" + id, properties -> new PotteryBlock(properties, baseShape))
                .initialProperties(() -> BlockBehaviour.Properties.ofLegacyCopy(Blocks.OAK_PLANKS))
                .properties(BlockBehaviour.Properties::noCollision)
                .item();
    }
}
