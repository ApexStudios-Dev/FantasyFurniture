package dev.apexstudios.fantasyfurniture.common.data;

import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class FurnitureLanguageProviderUS extends LanguageProvider {
    protected final DataGenContext furniture;

    public FurnitureLanguageProviderUS(PackOutput output, DataGenContext furniture) {
        super(output, furniture.registree().namespace(), "en_us");

        this.furniture = furniture;
    }

    @Override
    protected void addTranslations() {
        addFurnitureTranslations();
    }

    protected void addFurnitureTranslations() {
        FurnitureUtil.Names.creativeModeTab(furniture.registree(), key -> addKey(key, "itemGroup", "Fantasy's Furniture - " + furniture.englishName()));

        addFurniture(FurnitureUtil.Names.PLANKS, "Planks");
        addFurniture(FurnitureUtil.Names.BRICKS, "Bricks");
        addFurniture(FurnitureUtil.Names.WOOL, "Wool");
        addFurniture(FurnitureUtil.Names.CARPET, "Carpet");
        addFurniture(FurnitureUtil.Names.DRESSER, "Dresser");
        addFurniture(FurnitureUtil.Names.STOOL, "Stool");
        addFurniture(FurnitureUtil.Names.CUSHION, "Cushion");
        addFurniture(FurnitureUtil.Names.LOCKBOX, "Lockbox");
        addFurniture(FurnitureUtil.Names.DRAWER, "Drawer");
        addFurniture(FurnitureUtil.Names.CHAIR, "Chair");
        addFurniture(FurnitureUtil.Names.BOOKSHELF, "Bookshelf");
        addFurniture(FurnitureUtil.Names.BED_SINGLE, "Bed Single");
        addFurniture(FurnitureUtil.Names.BED_DOUBLE, "Bed Double");
        addFurniture(FurnitureUtil.Names.DOOR_SINGLE, "Door Single");
        addFurniture(FurnitureUtil.Names.DOOR_DOUBLE, "Door Double");
        addFurniture(FurnitureUtil.Names.DESK_LEFT, "Desk Left");
        addFurniture(FurnitureUtil.Names.DESK_RIGHT, "Desk Right");
        addFurniture(FurnitureUtil.Names.PAINTING_WIDE, "Painting Wide");
        addFurniture(FurnitureUtil.Names.PAINTING_SMALL, "Painting Small");
        addFurniture(FurnitureUtil.Names.OVEN, "Oven");
        addFurniture(FurnitureUtil.Names.CHEST, "Chest");
        addFurniture(FurnitureUtil.Names.FLOOR_LIGHT, "Floor Light");
        addFurniture(FurnitureUtil.Names.CHANDELIER, "Chandelier");
        addFurniture(FurnitureUtil.Names.SHELF, "Shelf");
        addFurniture(FurnitureUtil.Names.SOFA, "Sofa");
        addFurniture(FurnitureUtil.Names.COUNTER, "Counter");
        addFurniture(FurnitureUtil.Names.WALL_LIGHT, "Wall Light");
        addFurniture(FurnitureUtil.Names.BENCH, "Bench");
        addFurniture(FurnitureUtil.Names.WARDROBE, "Wardrobe");
        addFurniture(FurnitureUtil.Names.TABLE, "Table");
        addFurniture(FurnitureUtil.Names.STAIRS, "Stairs");
        addFurniture(FurnitureUtil.Names.SLAB, "Slab");
        addFurniture(FurnitureUtil.Names.FENCE, "Fence");
        addFurniture(FurnitureUtil.Names.FENCE_GATE, "Fence Gate");
        addFurniture(FurnitureUtil.Names.TRAPDOOR, "Trapdoor");
        addFurniture(FurnitureUtil.Names.PRESSURE_PLATE, "Pressure Plate");
        addFurniture(FurnitureUtil.Names.BUTTON, "Button");
        addFurniture(FurnitureUtil.Names.HANGING_SIGN, "Hanging Sign");
        addFurniture(FurnitureUtil.Names.WALL_HANGING_SIGN, "Wall Hanging Sign");
        addFurniture(FurnitureUtil.Names.SIGN, "Sign");
        addFurniture(FurnitureUtil.Names.WALL_SIGN, "Wall Sign");
    }

    protected void addFurniture(String name, String englishName) {
        furniture.block(DataGenType.LANGUAGE, name, block -> add(block, furniture.englishName() + ' ' + englishName));
    }
}
