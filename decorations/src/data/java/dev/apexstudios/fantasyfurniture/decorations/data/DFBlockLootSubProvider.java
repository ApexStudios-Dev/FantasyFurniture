package dev.apexstudios.fantasyfurniture.decorations.data;

import dev.apexstudios.fantasyfurniture.decorations.common.DecorationsFurnitureModule;
import java.util.function.Predicate;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

final class DFBlockLootSubProvider extends VanillaBlockLoot {
    DFBlockLootSubProvider(Context context) {
        super(context);
    }

    @Override
    protected void generate() {
        DecorationsFurnitureModule.REGISTREE
                .listElements(Registries.BLOCK)
                .map(Holder::value)
                .filter(Predicate.not(DecorationsFurnitureModule.PLUSHIE_BLOCK::is))
                .forEach(this::dropSelf);

        add(DecorationsFurnitureModule.PLUSHIE_BLOCK.value(), LootTable
                .lootTable()
                .withPool(applyExplosionCondition(DecorationsFurnitureModule.PLUSHIE_BLOCK.value(), LootPool
                        .lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem
                                .lootTableItem(DecorationsFurnitureModule.PLUSHIE_BLOCK.value())
                                .apply(CopyComponentsFunction
                                        .copyComponentsFromBlockEntity(LootContextParams.BLOCK_ENTITY)
                                        .include(DataComponents.PROFILE)
                                        .include(DecorationsFurnitureModule.PLUSHIE_RENDER_NAME.value())
                                )
                        )
                ))
        );
    }

    @Override
    public Iterable<Block> getKnownBlocks() {
        return DecorationsFurnitureModule.REGISTREE.listElements(Registries.BLOCK).map(Holder::value).toList();
    }
}
