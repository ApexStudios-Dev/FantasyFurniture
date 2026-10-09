package dev.apexstudios.fantasyfurniture.common.data;

import dev.apexstudios.apexcore.api.data.ItemTagsProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.Nullable;

public interface FurnitureItemTags {
    static void tag(ItemTagsProvider provider, Item element, @Nullable Object... tags) {
        for(var obj : tags) {
            TagKey<Item> tag = null;

            if(obj instanceof TagKey<?> key) {
                tag = key.cast(Registries.ITEM).orElse(null);
            } else if(obj instanceof BlockItemTagId blockItemTag) {
                tag = blockItemTag.item();
            }

            if(tag != null) {
                provider.tag(tag).add(element.builtInRegistryHolder().key());
            }
        }
    }
}
