package dev.apexstudios.fantasyfurniture.royal.block;

import dev.apexstudios.apexcore.lib.component.block.BlockComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureDoorBlockComponentHolder;
import dev.apexstudios.fantasyfurniture.royal.RoyalFurnitureSet;

public final class RoyalDoorBlock extends FurnitureDoorBlockComponentHolder {
    public RoyalDoorBlock(Properties properties) {
        super(RoyalFurnitureSet.FURNITURE_SET, properties);
    }

    @Override
    protected void registerComponents(BlockComponentRegistrar registrar) {
        super.registerComponents(registrar);

        registrar.register(BlockComponentTypes.DYEABLE);
    }
}
