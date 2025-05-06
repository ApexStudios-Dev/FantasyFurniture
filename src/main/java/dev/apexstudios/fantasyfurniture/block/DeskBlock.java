package dev.apexstudios.fantasyfurniture.block;

public class DeskBlock extends InventoryBlock {
    protected final boolean left;

    public DeskBlock(Properties properties, boolean left) {
        super(properties);

        this.left = left;
    }
}
