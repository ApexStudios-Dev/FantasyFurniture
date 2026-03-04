package dev.apexstudios.fantasyfurniture.common.util;

import dev.apexstudios.registree.api.Registree;
import java.util.function.Supplier;
import net.minecraft.client.renderer.block.BuiltInBlockModels;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterBlockModelsEvent;

interface FurnitureClientUtil {
    static void registerEvents(IEventBus modBus, Registree registree, Supplier<WoodType> woodType) {
        modBus.addListener(RegisterBlockModelsEvent.class, event -> {
            FurnitureUtil.Names.block(registree, FurnitureUtil.Names.SIGN, block -> event.register(BuiltInBlockModels.createStandingSign(woodType.get()), block));
            FurnitureUtil.Names.block(registree, FurnitureUtil.Names.WALL_SIGN, block -> event.register(BuiltInBlockModels.createWallSign(woodType.get()), block));
            FurnitureUtil.Names.block(registree, FurnitureUtil.Names.HANGING_SIGN, block -> event.register(BuiltInBlockModels.createCeilingHangingSign(woodType.get()), block));
            FurnitureUtil.Names.block(registree, FurnitureUtil.Names.WALL_HANGING_SIGN, block -> event.register(BuiltInBlockModels.createWallHangingSign(woodType.get()), block));
        });
    }
}
