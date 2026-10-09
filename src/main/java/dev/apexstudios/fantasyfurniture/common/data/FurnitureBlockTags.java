package dev.apexstudios.fantasyfurniture.common.data;

import dev.apexstudios.apexcore.api.data.BlockTagsProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.Nullable;

public interface FurnitureBlockTags {
    static void tag(BlockTagsProvider provider, Block element, @Nullable Object... tags) {
        for(var obj : tags) {
            TagKey<Block> tag = null;

            if(obj instanceof TagKey<?> key) {
                tag = key.cast(Registries.BLOCK).orElse(null);
            } else if(obj instanceof BlockItemTagId blockItemTag) {
                tag = blockItemTag.block();
            }

            if(tag != null) {
                provider.tag(tag).add(element.builtInRegistryHolder().key());
            }
        }
    }
}
