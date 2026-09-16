package dev.apexstudios.fantasyfurniture.data;

import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.station.FurnitureStationSetup;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.level.block.Block;

final class FFBlockLootSubProvider extends VanillaBlockLoot {
    FFBlockLootSubProvider(LootTableSubProvider.Context context) {
        super(context);
    }

    @Override
    protected void generate() {
        dropSelf(FurnitureStationSetup.BLOCK.value());
    }

    @Override
    public Iterable<Block> getKnownBlocks() {
        return FantasyFurniture.REGISTREE.listElements(Registries.BLOCK).map(Holder::value).toList();
    }
}
