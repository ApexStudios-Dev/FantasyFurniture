package dev.apexstudios.fantasyfurniture.block.base;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.FacingBlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.SeatBlockComponent;
import org.jetbrains.annotations.MustBeInvokedByOverriders;

public class SeatBlock extends FurnitureBlockComponentHolder {
    public SeatBlock(Properties properties) {
        super(properties);
    }

    @MustBeInvokedByOverriders
    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        super.registerComponents(registrar);

        FacingBlockComponent.registerHorizontal(registrar);
        registrar.register(SeatBlockComponent.COMPONENT_TYPE);
    }
}
