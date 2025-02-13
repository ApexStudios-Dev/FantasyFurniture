package dev.apexstudios.fantasyfurniture.block.base;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BaseEntityBlockComponentHolder;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.FluidLoggedBlockComponent;
import org.jetbrains.annotations.MustBeInvokedByOverriders;

public abstract class FurnitureEntityBlockComponentHolder extends BaseEntityBlockComponentHolder {
    protected FurnitureEntityBlockComponentHolder(Properties properties) {
        super(properties);
    }

    @MustBeInvokedByOverriders
    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        super.registerComponents(registrar);

        FluidLoggedBlockComponent.registerWater(registrar);
    }
}
