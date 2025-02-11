package dev.apexstudios.fantasyfurniture.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.BlockComponentTypes;
import dev.apexstudios.apexcore.lib.component.block.types.FacingBlockComponent;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;

public class BedDoubleBlock extends FurnitureBlockComponentHolder {
    public BedDoubleBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent> registrar) {
        super.registerComponents(registrar);

        FacingBlockComponent.registerHorizontal(registrar);

        registrar.register(BlockComponentTypes.MULTI_BLOCK, builder -> builder
                .with(1, 0, 0)
                .with(1, 0, 1)
                .with(0, 0, 1)
                .rotatingFromComponent()
        );

        registrar.register(BlockComponentTypes.BED, builder -> builder
                .indices(1, 0)
                .indices(2, 3)
        );

        registrar.register(BlockComponentTypes.BOUNCE);
    }
}
