package dev.apexstudios.fantasyfurniture.decorations;

import dev.apexstudios.apexcore.lib.block.Dyeable;
import dev.apexstudios.fantasyfurniture.decorations.block.FairyLightsBlock;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.CommonColors;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import org.jetbrains.annotations.Nullable;

@Mod(value = DecorationsFurnitureModule.ID, dist = Dist.CLIENT)
public final class DecorationsFurnitureModuleClientSetup {
    public DecorationsFurnitureModuleClientSetup(IEventBus modBus) {
        modBus.addListener(FMLClientSetupEvent.class, event -> event.enqueueWork(() -> DecorationsFurnitureModule.REGISTREE
                .listElements(Registries.BLOCK)
                .map(Holder::value)
                .forEach(block -> ItemBlockRenderTypes.setRenderLayer(block, ChunkSectionLayer.CUTOUT))
        ));

        modBus.addListener(RegisterColorHandlersEvent.Block.class, event -> event.register(this::getColor, DecorationsFurnitureModule.dyeables().toArray(Block[]::new)));
    }

    private int getColor(BlockState blockState, @Nullable BlockAndTintGetter level, @Nullable BlockPos pos, int tintIndex) {
        if(blockState.hasProperty(FairyLightsBlock.COLOR)) {
            var color = blockState.getValue(FairyLightsBlock.COLOR);

            if(color == FairyLightsBlock.LightColor.NONE) {
                return CommonColors.WHITE;
            }
        }

        return tintIndex == 0 ? Dyeable.getColor(blockState).getTextureDiffuseColor() : CommonColors.WHITE;
    }
}
