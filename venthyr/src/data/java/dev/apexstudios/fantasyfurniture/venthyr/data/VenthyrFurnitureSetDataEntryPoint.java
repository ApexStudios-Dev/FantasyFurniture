package dev.apexstudios.fantasyfurniture.venthyr.data;

import dev.apexstudios.fantasyfurniture.common.data.DataGenContext;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureModels;
import dev.apexstudios.fantasyfurniture.common.data.FurnitureRecipes;
import dev.apexstudios.fantasyfurniture.venthyr.common.VenthyrFurnitureSet;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(VenthyrFurnitureSet.ID)
public final class VenthyrFurnitureSetDataEntryPoint {
    public VenthyrFurnitureSetDataEntryPoint(IEventBus modBus) {
        DataGenContext.wooden(VenthyrFurnitureSet.REGISTREE, "venthyr")
                .extraModels((blockModels, itemModels, context) -> FurnitureModels.createTableModel(VenthyrFurnitureSet.TABLE_CLOTH.value(), blockModels))
                .extraBlockLoot((prvoider, context) -> prvoider.dropSelf(VenthyrFurnitureSet.TABLE_CLOTH.value()))
                .extraRecipes((provider, context) -> FurnitureRecipes.furnitureStationRecipe(provider, context, VenthyrFurnitureSet.TABLE_CLOTH.value()))
                .extraLanguage((provider, context) -> provider.addBlock(VenthyrFurnitureSet.TABLE_CLOTH, context.englishName + " Table Cloth"))
                .extraBlockTags((provider, context) -> provider.tag(context.mineableTag).add(VenthyrFurnitureSet.TABLE_CLOTH.key()))
                .build(modBus);
    }
}
