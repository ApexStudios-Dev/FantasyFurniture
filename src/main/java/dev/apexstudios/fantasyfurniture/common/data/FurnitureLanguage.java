package dev.apexstudios.fantasyfurniture.common.data;

import net.neoforged.neoforge.common.data.LanguageProvider;

public interface FurnitureLanguage {
    static void addFurniture(LanguageProvider provider, DataGenContext context, String blockName, String blockEnglishName) {
        context.block(DataGenType.LANGUAGE, blockName, block -> provider.add(block, context.englishName + ' ' + blockEnglishName));
    }
}
