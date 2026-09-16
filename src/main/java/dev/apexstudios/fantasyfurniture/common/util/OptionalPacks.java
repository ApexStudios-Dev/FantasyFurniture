package dev.apexstudios.fantasyfurniture.common.util;

import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import org.jspecify.annotations.Nullable;

public enum OptionalPacks {
    CONTEX("contex", "ctm-contex", "ConTex", "Fantasy's Furniture ConTex CTM support"),
    CONTEXT_MATTERS("context_matters", "ctm-context-matters", "ConText Matters", "Fantasy's Furniture ConText Matters CTM support"),
    LEGACY_DOORS(null, "3d-doors", "3D Doors", "Restores Fantasy's Furniture old 3D Door Item Models");

    public final @Nullable String modId;
    public final String id;
    public final String displayName;
    public final String description;

    OptionalPacks(@Nullable String modId, String id, String displayName, String description) {
        this.modId = modId;
        this.id = id;
        this.displayName = displayName;
        this.description = description;
    }

    public String packPath() {
        return "packs/" + id;
    }

    public void register(AddPackFindersEvent event) {
        if(modId != null && !ModList.get().isLoaded(modId)) {
            return;
        }

        event.addPackFinders(FantasyFurniture.identifier(packPath()), PackType.CLIENT_RESOURCES, Component.literal(displayName), PackSource.BUILT_IN, false, Pack.Position.TOP);
    }
}
