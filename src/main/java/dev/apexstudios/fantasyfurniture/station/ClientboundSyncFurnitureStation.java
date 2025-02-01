package dev.apexstudios.fantasyfurniture.station;

import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import java.util.List;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ClientboundSyncFurnitureStation(List<RecipeHolder<?>> recipes) implements CustomPacketPayload {
    public static final Type<ClientboundSyncFurnitureStation> TYPE = new Type<>(FantasyFurniture.identifier("sync_furniture_station"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundSyncFurnitureStation> STREAM_CODEC = StreamCodec.composite(
            RecipeHolder.STREAM_CODEC.apply(ByteBufCodecs.list()), ClientboundSyncFurnitureStation::recipes,
            ClientboundSyncFurnitureStation::new
    );

    public void handle(IPayloadContext context) {
        context.enqueueWork(() -> {
            if(context.player().containerMenu instanceof FurnitureStationMenu menu)
                menu.syncRecipes(recipes);
        });
    }

    @Override
    public Type<ClientboundSyncFurnitureStation> type() {
        return TYPE;
    }
}
