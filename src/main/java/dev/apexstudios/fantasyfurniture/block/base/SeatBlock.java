package dev.apexstudios.fantasyfurniture.block.base;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.FacingBlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.SeatBlockComponent;
import dev.apexstudios.fantasyfurniture.set.BlockType;
import dev.apexstudios.fantasyfurniture.set.FurnitureSet;
import org.jetbrains.annotations.MustBeInvokedByOverriders;

public class SeatBlock extends FurnitureBlockComponentHolder {
    public SeatBlock(FurnitureSet furnitureSet, BlockType<?, ?> blockType, Properties properties) {
        super(furnitureSet, blockType, properties);
    }

    @MustBeInvokedByOverriders
    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        super.registerComponents(registrar);

        FacingBlockComponent.registerHorizontal(registrar);
        registrar.register(SeatBlockComponent.COMPONENT_TYPE);
    }
}
