package apexstudios.fantasyfurniture.bone;

import apexstudios.fantasyfurniture.bone.block.BoneBedDoubleBlock;
import apexstudios.fantasyfurniture.bone.block.BoneBedSingleBlock;
import apexstudios.fantasyfurniture.bone.block.BoneBenchBlock;
import apexstudios.fantasyfurniture.bone.block.BoneBookshelfBlock;
import apexstudios.fantasyfurniture.bone.block.BoneChairBlock;
import apexstudios.fantasyfurniture.bone.block.BoneChandelierBlock;
import apexstudios.fantasyfurniture.bone.block.BoneChestBlock;
import apexstudios.fantasyfurniture.bone.block.BoneCushionBlock;
import apexstudios.fantasyfurniture.bone.block.BoneDeskBlock;
import apexstudios.fantasyfurniture.bone.block.BoneDrawerBlock;
import apexstudios.fantasyfurniture.bone.block.BoneDresserBlock;
import apexstudios.fantasyfurniture.bone.block.BoneFloorLightBlock;
import apexstudios.fantasyfurniture.bone.block.BoneLockBoxBlock;
import apexstudios.fantasyfurniture.bone.block.BonePaintingSmallBlock;
import apexstudios.fantasyfurniture.bone.block.BonePaintingWideBlock;
import apexstudios.fantasyfurniture.bone.block.BoneShelfBlock;
import apexstudios.fantasyfurniture.bone.block.BoneSofaBlock;
import apexstudios.fantasyfurniture.bone.block.BoneStoolBlock;
import apexstudios.fantasyfurniture.bone.block.BoneTableBlock;
import apexstudios.fantasyfurniture.bone.block.BoneWallLightBlock;
import apexstudios.fantasyfurniture.bone.block.BoneWardrobeBlock;
import dev.apexstudios.apexcore.lib.registree.Registree;
import dev.apexstudios.apexcore.lib.registree.holder.DeferredBlock;
import dev.apexstudios.apexcore.lib.util.WoodTypeBuilder;
import dev.apexstudios.fantasyfurniture.FantasyFurniture;
import dev.apexstudios.fantasyfurniture.FurnitureUtil;
import dev.apexstudios.fantasyfurniture.block.CounterBlock;
import dev.apexstudios.fantasyfurniture.block.FurnitureDoorBlock;
import dev.apexstudios.fantasyfurniture.block.OvenBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.CreativeModeTab;
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

public sealed class BoneFurnitureSet permits SkeletonFurnitureSet, WitherFurnitureSet {
    public static final String ID = FantasyFurniture.ID + "_bone";

    public final String id;
    public final Registree registree;
    public final WoodType woodType;
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
    public final DeferredBlock<BoneDeskBlock> deskLeft;
    public final DeferredBlock<BoneDeskBlock> deskRight;
    public final DeferredBlock<BonePaintingWideBlock> paintingWide;
    public final DeferredBlock<BonePaintingSmallBlock> paintingSmall;
    public final DeferredBlock<OvenBlock> oven;
    public final DeferredBlock<BoneChestBlock> chest;
    public final DeferredBlock<BoneFloorLightBlock> floorLight;
    public final DeferredBlock<BoneChandelierBlock> chandelier;
    public final DeferredBlock<BoneShelfBlock> shelf;
    public final DeferredBlock<BoneSofaBlock> sofa;
    public final DeferredBlock<CounterBlock> counter;
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

    protected BoneFurnitureSet(IEventBus modBus, String modId, String id) {
        this.id = id;

        registree = new Registree(modId);

        woodType = WoodTypeBuilder.builder()
                .copy(WoodType.OAK)
                .blockSetType(blockSet -> blockSet
                        .copy(BlockSetType.STONE)
                )
                .build(modId + ":wood_type");

        bricks = FurnitureUtil.bricks(registree, Block::new);
        wool = FurnitureUtil.wool(registree, Block::new);
        carpet = FurnitureUtil.carpet(registree, CarpetBlock::new);
        dresser = FurnitureUtil.dresser(registree, BoneDresserBlock::new);
        stool = FurnitureUtil.stool(registree, BoneStoolBlock::new);
        cushion = FurnitureUtil.cushion(registree, BoneCushionBlock::new);
        lockbox = FurnitureUtil.lockbox(registree, BoneLockBoxBlock::new);
        drawer = FurnitureUtil.drawer(registree, BoneDrawerBlock::new);
        chair = FurnitureUtil.chair(registree, BoneChairBlock::new);
        bookshelf = FurnitureUtil.bookshelf(registree, BoneBookshelfBlock::new);
        bedSingle = FurnitureUtil.bedSingle(registree, BoneBedSingleBlock::new);
        bedDouble = FurnitureUtil.bedDouble(registree, BoneBedDoubleBlock::new);
        doorSingle = FurnitureUtil.doorSingle(registree, woodType.setType(), FurnitureDoorBlock::new);
        doorDouble = FurnitureUtil.doorDouble(registree, woodType.setType(), FurnitureDoorBlock::new);
        deskLeft = FurnitureUtil.desk(registree, true, BoneDeskBlock::new);
        deskRight = FurnitureUtil.desk(registree, false, BoneDeskBlock::new);
        paintingWide = FurnitureUtil.paintingWide(registree, BonePaintingWideBlock::new);
        paintingSmall = FurnitureUtil.paintingSmall(registree, BonePaintingSmallBlock::new);
        oven = FurnitureUtil.oven(registree, OvenBlock::new);
        chest = FurnitureUtil.chest(registree, BoneChestBlock::new);
        floorLight = FurnitureUtil.floorLight(registree, BoneFloorLightBlock::new);
        chandelier = FurnitureUtil.chandelier(registree, BoneChandelierBlock::new);
        shelf = FurnitureUtil.shelf(registree, BoneShelfBlock::new);
        sofa = FurnitureUtil.sofa(registree, BoneSofaBlock::new);
        counter = FurnitureUtil.counter(registree, CounterBlock::new);
        wallLight = FurnitureUtil.wallLight(registree, BoneWallLightBlock::new);
        bench = FurnitureUtil.bench(registree, BoneBenchBlock::new);
        wardrobe = FurnitureUtil.wardrobe(registree, BoneWardrobeBlock::new);
        table = FurnitureUtil.table(registree, BoneTableBlock::new);
        stairs = FurnitureUtil.stairs(registree, bricks);
        slab = FurnitureUtil.slab(registree);
        fence = FurnitureUtil.fence(registree);
        fenceGate = FurnitureUtil.fenceGate(registree, woodType);
        trapdoor = FurnitureUtil.trapdoor(registree, woodType.setType());
        pressurePlate = FurnitureUtil.pressurePlate(registree, woodType.setType());
        hangingSign = FurnitureUtil.hangingSign(registree, woodType);
        sign = FurnitureUtil.sign(registree, woodType);

        creativeModeTab = FurnitureUtil.creativeModeTab(registree, bedSingle);

        FurnitureUtil.registerEvents(modBus, registree, woodType);

        modBus.addListener(AddPackFindersEvent.class, event -> {
            addPackFinder(event, PackType.CLIENT_RESOURCES);
            addPackFinder(event, PackType.SERVER_DATA);
        });
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
