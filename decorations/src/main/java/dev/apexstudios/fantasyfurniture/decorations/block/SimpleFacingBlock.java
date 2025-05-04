package dev.apexstudios.fantasyfurniture.decorations.block;

import dev.apexstudios.apexcore.lib.component.ComponentRegistrar;
import dev.apexstudios.apexcore.lib.component.block.BlockComponent;
import dev.apexstudios.apexcore.lib.component.block.types.FacingBlockComponent;
import dev.apexstudios.fantasyfurniture.block.base.FurnitureBlockComponentHolder;
import net.minecraft.world.level.block.Block;

public class SimpleFacingBlock extends FurnitureBlockComponentHolder {
    public SimpleFacingBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void registerComponents(ComponentRegistrar<BlockComponent, Block> registrar) {
        super.registerComponents(registrar);

        FacingBlockComponent.registerHorizontal(registrar);
    }
}
