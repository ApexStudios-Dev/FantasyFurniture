package dev.apexstudios.fantasyfurniture.bone.common;

import dev.apexstudios.apexcore.api.util.WoodTypeBuilder;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneBedDoubleBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneBedSingleBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneBenchBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneBookshelfBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneChairBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneChandelierBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneChestBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneCounterBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneCushionBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneDeskLeftBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneDeskRightBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneDrawerBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneDresserBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneFloorLightBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneLockBoxBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneOvenBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BonePaintingSmallBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BonePaintingWideBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneShelfBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneSofaBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneStoolBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneTableBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneWallLightBlock;
import dev.apexstudios.fantasyfurniture.bone.common.block.BoneWardrobeBlock;
import dev.apexstudios.fantasyfurniture.common.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.common.block.FurnitureDoorBlock;
import dev.apexstudios.fantasyfurniture.common.util.FurnitureUtil;
import dev.apexstudios.registree.Registree;
import dev.apexstudios.registree.registrar.BlockRegistrar;
import java.util.function.Supplier;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.registries.DeferredBlock;

public final class BoneFurnitureSet {
    public static final String ID = FantasyFurniture.ID + "_bone";

    public final String id;
    public final Registree registree;
    public final BlockRegistrar blocks;
    public final Supplier<WoodType> woodType;
    public final Supplier<BlockSetType> blockSetType;
    public final DeferredBlock<Block> bricks;
    public final DeferredBlock<Block> wool;
    public final DeferredBlock<CarpetBlock> carpet;
    public final DeferredBlock<BoneDresserBlock> dresser;
    public final DeferredBlock<BoneStoolBlock> stool;
    public final DeferredBlock<BoneCushionBlock> cushion;
    public final DeferredBlock<BoneLockBoxBlock> lockbox;
    public final DeferredBlock<BoneDrawerBlock> drawer;
    public final DeferredBlock<BoneChairBlock> chair;
    public final DeferredBlock<BoneBookshelfBlock> bookshelf;
    public final DeferredBlock<BoneBedSingleBlock> bedSingle;
    public final DeferredBlock<BoneBedDoubleBlock> bedDouble;
    public final DeferredBlock<FurnitureDoorBlock> doorSingle;
    public final DeferredBlock<FurnitureDoorBlock> doorDouble;
    public final DeferredBlock<BoneDeskLeftBlock> deskLeft;
    public final DeferredBlock<BoneDeskRightBlock> deskRight;
    public final DeferredBlock<BonePaintingWideBlock> paintingWide;
    public final DeferredBlock<BonePaintingSmallBlock> paintingSmall;
    public final DeferredBlock<BoneOvenBlock> oven;
    public final DeferredBlock<BoneChestBlock> chest;
    public final DeferredBlock<BoneFloorLightBlock> floorLight;
    public final DeferredBlock<BoneChandelierBlock> chandelier;
    public final DeferredBlock<BoneShelfBlock> shelf;
    public final DeferredBlock<BoneSofaBlock> sofa;
    public final DeferredBlock<BoneCounterBlock> counter;
    public final DeferredBlock<BoneWallLightBlock> wallLight;
    public final DeferredBlock<BoneBenchBlock> bench;
    public final DeferredBlock<BoneWardrobeBlock> wardrobe;
    public final DeferredBlock<BoneTableBlock> table;
    public final DeferredBlock<StairBlock> stairs;
    public final DeferredBlock<SlabBlock> slab;
    public final DeferredBlock<FenceBlock> fence;
    public final DeferredBlock<FenceGateBlock> fenceGate;
    public final DeferredBlock<TrapDoorBlock> trapdoor;
    public final DeferredBlock<PressurePlateBlock> pressurePlate;
    public final FurnitureUtil.SignPair<CeilingHangingSignBlock, WallHangingSignBlock> hangingSign;
    public final FurnitureUtil.SignPair<StandingSignBlock, WallSignBlock> sign;
    public final ResourceKey<CreativeModeTab> creativeModeTab;

    protected BoneFurnitureSet(String modId, String id) {
        this.id = id;

        registree = Registree.create(modId);
        blocks = registree.blocks();

        woodType = WoodTypeBuilder.builder()
                .copy(WoodType.OAK)
                .blockSetType(blockSet -> blockSet
                        .copy(BlockSetType.STONE)
                )
                .build(registree.registryId(id));

        blockSetType = () -> woodType.get().setType();

        bricks = FurnitureUtil.bricks(blocks, Block::new).register();
        wool = FurnitureUtil.wool(blocks, Block::new).register();
        carpet = FurnitureUtil.carpet(blocks, CarpetBlock::new).register();
        dresser = FurnitureUtil.dresser(blocks, BoneDresserBlock::new).register();
        stool = FurnitureUtil.stool(blocks, BoneStoolBlock::new).register();
        cushion = FurnitureUtil.cushion(blocks, BoneCushionBlock::new).register();
        lockbox = FurnitureUtil.lockbox(blocks, BoneLockBoxBlock::new).register();
        drawer = FurnitureUtil.drawer(blocks, BoneDrawerBlock::new).register();
        chair = FurnitureUtil.chair(blocks, BoneChairBlock::new).register();
        bookshelf = FurnitureUtil.bookshelf(blocks, BoneBookshelfBlock::new).register();
        bedSingle = FurnitureUtil.bedSingle(blocks, BoneBedSingleBlock::new).register();
        bedDouble = FurnitureUtil.bedDouble(blocks, BoneBedDoubleBlock::new).register();
        doorSingle = FurnitureUtil.doorSingle(blocks, blockSetType, FurnitureDoorBlock::new).register();
        doorDouble = FurnitureUtil.doorDouble(blocks, blockSetType, FurnitureDoorBlock::new).register();
        deskLeft = FurnitureUtil.desk(blocks, true, BoneDeskLeftBlock::new).register();
        deskRight = FurnitureUtil.desk(blocks, false, BoneDeskRightBlock::new).register();
        paintingWide = FurnitureUtil.paintingWide(blocks, BonePaintingWideBlock::new).register();
        paintingSmall = FurnitureUtil.paintingSmall(blocks, BonePaintingSmallBlock::new).register();
        oven = FurnitureUtil.oven(blocks, BoneOvenBlock::new).register();
        chest = FurnitureUtil.chest(blocks, BoneChestBlock::new).register();
        floorLight = FurnitureUtil.floorLight(blocks, BoneFloorLightBlock::new).register();
        chandelier = FurnitureUtil.chandelier(blocks, BoneChandelierBlock::new).register();
        shelf = FurnitureUtil.shelf(blocks, BoneShelfBlock::new).register();
        sofa = FurnitureUtil.sofa(blocks, BoneSofaBlock::new).register();
        counter = FurnitureUtil.counter(blocks, BoneCounterBlock::new).register();
        wallLight = FurnitureUtil.wallLight(blocks, BoneWallLightBlock::new).register();
        bench = FurnitureUtil.bench(blocks, BoneBenchBlock::new).register();
        wardrobe = FurnitureUtil.wardrobe(blocks, BoneWardrobeBlock::new).register();
        table = FurnitureUtil.table(blocks, BoneTableBlock::new).register();
        stairs = FurnitureUtil.stairs(blocks, bricks).register();
        slab = FurnitureUtil.slab(blocks).register();
        fence = FurnitureUtil.fence(blocks).register();
        fenceGate = FurnitureUtil.fenceGate(blocks, woodType).register();
        trapdoor = FurnitureUtil.trapdoor(blocks, blockSetType).register();
        pressurePlate = FurnitureUtil.pressurePlate(blocks, blockSetType).register();
        hangingSign = FurnitureUtil.hangingSign(registree, woodType);
        sign = FurnitureUtil.sign(registree, woodType);

        creativeModeTab = FurnitureUtil.creativeModeTab(registree, () -> new ItemStack(bedSingle.value()));

        registree.event(AddPackFindersEvent.class, event -> {
            addPackFinder(event, PackType.CLIENT_RESOURCES);
            addPackFinder(event, PackType.SERVER_DATA);
        });
    }

    public void register(IEventBus modBus) {
        FurnitureUtil.registerEvents(modBus, registree);
    }

    private void addPackFinder(AddPackFindersEvent event, PackType packType) {
        event.addPackFinders(
                registree.registryName(packPath(packType, this)),
                packType,
                Component.literal("Fantasy's Furniture - Bone"),
                PackSource.DEFAULT,
                true,
                Pack.Position.BOTTOM
        );
    }

    public static String packPath(PackType packType, BoneFurnitureSet furnitureSet) {
        return "built-in/" + packType.getDirectory() + '/' + furnitureSet.id;
    }
}
