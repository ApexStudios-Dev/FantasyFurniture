package dev.apexstudios.fantasyfurniture.royal;

import dev.apexstudios.apexcore.core.client.DyeColorItemTintSource;
import dev.apexstudios.apexcore.lib.data.provider.model.ApexModelTemplates;
import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.fantasyfurniture.royal.block.RoyalWoolBlock;
import dev.apexstudios.fantasyfurniture.set.BlockTypes;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import dev.apexstudios.fantasyfurniture.set.function.BlockFactory;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.DyeColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(RoyalFurnitureSet.ID)
public class RoyalFurnitureSet {
    public static final String ID = "fantasyfurniture_royal";
    public static final Registree REGISTREE = new Registree(ID);
    public static final FurnitureSet FURNITURE_SET = FurnitureSet.createWoodLike(REGISTREE, "royal", $ -> $
            .with(BlockTypes.WOOL.copy($$ -> $$
                    .blockFactory(BlockFactory.wrapping(RoyalWoolBlock::new))
                    .builder($$$ -> $$$
                            .model(() -> (context, models, furnitureSet, block) -> {
                                var model = ApexModelTemplates.Textured.CUBE_ALL_TINTED.create(block, models.modelOutput);
                                models.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, model));

                                models.registerSimpleTintedItemModel(block, model, new DyeColorItemTintSource(DyeColor.WHITE));
                            })
                            .itemProperties(properties -> properties.component(DataComponents.BASE_COLOR, DyeColor.WHITE))
                    )
            ))
    );

    public RoyalFurnitureSet(IEventBus modBus) {
        FURNITURE_SET.register(modBus);
        REGISTREE.registerEvents(modBus);
    }
}
