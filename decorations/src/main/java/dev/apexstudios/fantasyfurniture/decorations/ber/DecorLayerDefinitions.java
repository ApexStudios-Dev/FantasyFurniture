package dev.apexstudios.fantasyfurniture.decorations.ber;

import dev.apexstudios.fantasyfurniture.decorations.DecorationsFurnitureModule;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.ResourceLocation;

public interface DecorLayerDefinitions {
    ModelLayerLocation WIDOW_BLOOM = create("widow_bloom", "main");
    ModelLayerLocation SKULL_BLOSSOM = create("skull_blossoms", "main");

    ResourceLocation WIDOW_BLOOM_TEXTURE = texture("widow_bloom");
    ResourceLocation SKULL_BLOSSOM_SKELETON_TEXTURE = texture("skull_blossom_skeleton");
    ResourceLocation SKULL_BLOSSOM_WITHER_TEXTURE = texture("skull_blossom_wither");

    static LayerDefinition createWidowBloom() {
        var mesh = new MeshDefinition();

        var widowBloom = mesh.getRoot().addOrReplaceChild("widow_bloom", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));
        widowBloom.addOrReplaceChild("urn", CubeListBuilder.create().texOffs(20, 20).addBox(-10.0F, 6.0F, 6.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(20, 26).addBox(-9.5F, 5.0F, 6.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 17).addBox(-10.5F, 2.0F, 5.5F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(0, 9).addBox(-11.0F, 0.0F, 5.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-11.5F, -2.0F, 4.5F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(0, 25).addBox(-6.0F, -3.0F, 5.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(21, 0).addBox(-11.0F, -3.0F, 5.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(0, 32).addBox(-10.0F, -3.0F, 10.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(29, 26).addBox(-10.0F, -3.0F, 5.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 0.0F, -8.0F));

        var bloom = widowBloom.addOrReplaceChild("widow_bloom", CubeListBuilder.create().texOffs(20, 30).addBox(-9.0F, -14.0F, 7.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 8.0F, -8.0F));
        bloom.addOrReplaceChild("leaf_6_r1", CubeListBuilder.create().texOffs(-1, 34).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3997F, -17.5092F, 10.1867F, 0.9126F, 1.2606F, 1.4594F));
        bloom.addOrReplaceChild("leaf_5_r1", CubeListBuilder.create().texOffs(19, 2).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.25F, -18.0F, 10.0F, 1.6107F, 1.2606F, 1.4594F));
        bloom.addOrReplaceChild("leaf_4_r1", CubeListBuilder.create().texOffs(15, 9).addBox(-1.5F, -0.3377F, -0.3817F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.6989F, -15.5435F, 11.3568F, -0.6273F, -0.379F, 0.2442F));
        bloom.addOrReplaceChild("leaf_3_r1", CubeListBuilder.create().texOffs(19, 4).addBox(-1.5F, -0.5066F, -2.0526F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.6989F, -15.5435F, 11.3568F, 0.1145F, -0.379F, 0.2442F));
        bloom.addOrReplaceChild("leaf_2_r1", CubeListBuilder.create().texOffs(15, 12).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9199F, -13.6992F, 5.244F, 0.3054F, -0.6545F, 0.0F));
        bloom.addOrReplaceChild("leaf_1_r1", CubeListBuilder.create().texOffs(15, 26).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, -14.0F, 6.0F, -0.5672F, -0.6545F, 0.0F));
        bloom.addOrReplaceChild("stem_6_r1", CubeListBuilder.create().texOffs(0, 9).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.4517F, -17.0188F, 8.0F, 1.3245F, -0.7409F, -0.7817F));
        bloom.addOrReplaceChild("stem_5_r1", CubeListBuilder.create().texOffs(0, 17).addBox(0.5F, -4.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, -12.5F, 8.0F, 0.0F, 0.0F, -0.7418F));
        bloom.addOrReplaceChild("stem_4_r1", CubeListBuilder.create().texOffs(29, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3008F, -19.6994F, 10.4432F, -0.3843F, 0.0829F, 0.202F));
        bloom.addOrReplaceChild("stem_3_r1", CubeListBuilder.create().texOffs(12, 30).addBox(-1.0F, -2.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.2322F, -16.7156F, 9.2072F, -0.2849F, 0.274F, 0.7459F));
        bloom.addOrReplaceChild("stem_2_r1", CubeListBuilder.create().texOffs(28, 30).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -15.927F, 8.8806F, -0.3927F, 0.0F, 0.0F));

        var bloom1 = bloom.addOrReplaceChild("bloom1", CubeListBuilder.create(), PartPose.offset(-8.5F, -13.5F, 8.0F));
        bloom1.addOrReplaceChild("5_r1", CubeListBuilder.create().texOffs(0, 2).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5755F, -4.3821F, 2.3206F, -0.6981F, 0.0F, -0.7418F));
        bloom1.addOrReplaceChild("4_r1", CubeListBuilder.create().texOffs(0, 13).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5551F, -4.3599F, -1.3536F, 0.7854F, 0.0F, -0.7418F));
        bloom1.addOrReplaceChild("3_r1", CubeListBuilder.create().texOffs(0, 2).addBox(0.0F, -0.5F, -1.5F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9217F, -3.1087F, 0.5F, 0.0F, 0.0F, -1.5272F));
        bloom1.addOrReplaceChild("2_r1", CubeListBuilder.create().texOffs(0, 3).addBox(0.0F, -0.5F, -1.5F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1885F, -5.6122F, 0.5F, 0.0F, 0.0F, 0.0436F));
        bloom1.addOrReplaceChild("1_r1", CubeListBuilder.create().texOffs(8, 25).addBox(-0.5F, -6.0F, -1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.0F, -0.7418F));

        var bloom2 = bloom.addOrReplaceChild("bloom2", CubeListBuilder.create(), PartPose.offset(-5.4517F, -19.0188F, 8.0F));
        bloom2.addOrReplaceChild("5_r2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -0.5F, -1.5F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8417F, 0.5189F, -4.0087F, 1.3898F, 0.0288F, -0.955F));
        bloom2.addOrReplaceChild("4_r2", CubeListBuilder.create().texOffs(0, 1).addBox(0.0F, -0.5F, -1.5F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.099F, -1.4074F, -1.5066F, 0.1585F, -1.3876F, 0.4599F));
        bloom2.addOrReplaceChild("3_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3538F, -1.5048F, -2.4248F, 0.5391F, -0.7409F, -0.7817F));
        bloom2.addOrReplaceChild("2_r2", CubeListBuilder.create().texOffs(0, 1).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6112F, 0.6171F, -3.091F, 2.1099F, -0.7409F, -0.7817F));
        bloom2.addOrReplaceChild("1_r2", CubeListBuilder.create().texOffs(25, 7).addBox(-1.5F, -3.5F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 2.0F, 0.0F, 1.3245F, -0.7409F, -0.7817F));

        var bloom3 = bloom.addOrReplaceChild("bloom3", CubeListBuilder.create(), PartPose.offset(-5.3008F, -19.6994F, 10.4432F));
        bloom3.addOrReplaceChild("5_r3", CubeListBuilder.create().texOffs(15, 16).addBox(1.3358F, -2.5144F, -2.16F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.068F, -3.5753F, 0.3728F, 0.0569F, 0.0206F, 0.7721F));
        bloom3.addOrReplaceChild("4_r3", CubeListBuilder.create().texOffs(15, 17).addBox(-1.313F, -2.4926F, -2.16F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.068F, -3.5753F, 0.3728F, -0.0206F, 0.0569F, -0.7999F));
        bloom3.addOrReplaceChild("3_r3", CubeListBuilder.create().texOffs(24, 12).addBox(-1.9846F, -2.3904F, 1.2108F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.068F, -3.5753F, 0.3728F, -0.7597F, 0.0548F, -0.0132F));
        bloom3.addOrReplaceChild("2_r3", CubeListBuilder.create().texOffs(29, 28).addBox(-1.9846F, -2.6167F, -1.4381F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.068F, -3.5753F, 0.3728F, 0.8111F, 0.0548F, -0.0132F));
        bloom3.addOrReplaceChild("1_r3", CubeListBuilder.create().texOffs(20, 13).addBox(-1.9846F, -0.1263F, -2.16F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.068F, -3.5753F, 0.3728F, 0.0257F, 0.0548F, -0.0132F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    static LayerDefinition createSkullBlossom() {
        var mesh = new MeshDefinition();

        var skullBlossom = mesh.getRoot().addOrReplaceChild("skull_blossoms", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        skullBlossom.addOrReplaceChild("vase", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(18, 15).addBox(-4.0F, -8.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(9, 16).addBox(3.0F, -8.0F, -4.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(0, 24).addBox(-4.0F, -8.0F, 3.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(24, 6).addBox(-4.0F, -8.0F, -4.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        var blossom = skullBlossom.addOrReplaceChild("blossom", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 0.0F));

        var roots = blossom.addOrReplaceChild("roots", CubeListBuilder.create().texOffs(24, 28).addBox(-3.3866F, 3.857F, -3.1353F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.3866F, -17.857F, 2.1353F));
        roots.addOrReplaceChild("root_7_r1", CubeListBuilder.create().texOffs(6, 32).addBox(-1.0F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4229F, -0.4549F, -1.4519F));
        roots.addOrReplaceChild("root_6_r1", CubeListBuilder.create().texOffs(32, 8).addBox(0.75F, 0.0F, -1.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.6366F, 4.857F, -2.3853F, 2.2748F, 1.1009F, -0.8549F));
        roots.addOrReplaceChild("root_5_r1", CubeListBuilder.create().texOffs(31, 21).addBox(-1.0F, -1.25F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.6366F, 4.857F, -2.3853F, -0.1605F, -0.3873F, -2.6321F));
        roots.addOrReplaceChild("root_5_r2", CubeListBuilder.create().texOffs(32, 10).addBox(-1.5F, -0.5F, -0.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.2033F, -0.1019F, -1.9659F, -0.3491F, -0.6109F, -0.6545F));
        roots.addOrReplaceChild("root_4_r1", CubeListBuilder.create().texOffs(31, 23).addBox(-1.0F, -1.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3866F, 2.357F, -2.3853F, 0.0F, -0.6109F, -0.6545F));
        roots.addOrReplaceChild("root_3_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7623F, -1.0161F, -3.0982F, 0.4363F, 0.0F, -0.4363F));
        roots.addOrReplaceChild("root_2_r1", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.3495F, 2.0138F, -2.1353F, 0.0F, 0.0F, -0.4363F));

        var skulls = blossom.addOrReplaceChild("skulls", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        skulls.addOrReplaceChild("skull_5_r1", CubeListBuilder.create().texOffs(22, 22).addBox(-1.25F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5156F, -15.6092F, -3.696F, 0.5388F, -0.005F, 0.0585F));
        skulls.addOrReplaceChild("skull_4_r1", CubeListBuilder.create().texOffs(24, 0).addBox(-1.5F, -1.25F, -1.25F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5105F, -18.8501F, -1.484F, 0.2927F, -0.063F, 0.3367F));
        skulls.addOrReplaceChild("skull_3_r1", CubeListBuilder.create().texOffs(0, 26).addBox(-1.0F, -1.75F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.232F, -20.5124F, 3.0955F, -0.2683F, -0.012F, 0.1696F));
        skulls.addOrReplaceChild("skull_2_r1", CubeListBuilder.create().texOffs(12, 26).addBox(-1.25F, -1.25F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2783F, -14.8491F, 1.4432F, -0.0358F, -0.037F, -0.3973F));
        skulls.addOrReplaceChild("skull_1_r1", CubeListBuilder.create().texOffs(0, 15).addBox(-1.25F, -1.0F, -2.5F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2484F, -22.9662F, -0.9257F, -0.0832F, 0.0262F, -0.132F));

        var leaves = blossom.addOrReplaceChild("leaves", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        leaves.addOrReplaceChild("10_r1", CubeListBuilder.create().texOffs(0, 39).addBox(-1.0F, 0.25F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -3.5F, -4.5F, 0.6545F, 0.0F, 0.0F));
        leaves.addOrReplaceChild("9_r1", CubeListBuilder.create().texOffs(0, 38).addBox(0.0F, 0.25F, -1.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -3.5455F, -4.8562F, 1.309F, 0.0F, 0.0F));
        leaves.addOrReplaceChild("8_r1", CubeListBuilder.create().texOffs(15, 15).addBox(-2.0F, 0.25F, -3.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -5.5455F, -4.8562F, 1.309F, 0.0F, 0.0F));
        leaves.addOrReplaceChild("7_r1", CubeListBuilder.create().texOffs(0, 7).addBox(-1.0F, 0.25F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -5.5F, -4.5F, 0.6545F, 0.0F, 0.0F));
        leaves.addOrReplaceChild("6_r1", CubeListBuilder.create().texOffs(9, 15).addBox(-1.5F, 0.0F, -1.5F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6278F, -12.5564F, 1.2085F, -0.3686F, -0.0971F, 1.0409F));
        leaves.addOrReplaceChild("5_r1", CubeListBuilder.create().texOffs(15, 18).addBox(-1.75F, 0.0F, -0.75F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.1043F, -14.5383F, 0.0377F, -0.2751F, -0.2664F, 0.5362F));
        leaves.addOrReplaceChild("4_r1", CubeListBuilder.create().texOffs(7, 26).addBox(-1.5F, -0.4301F, 0.1141F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1325F, -18.7706F, 2.1096F, -0.7634F, -0.1466F, -0.1888F));
        leaves.addOrReplaceChild("3_r1", CubeListBuilder.create().texOffs(23, 15).addBox(-1.5F, -0.2867F, -2.6593F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1325F, -18.7706F, 2.1096F, -0.1525F, -0.1466F, -0.1888F));
        leaves.addOrReplaceChild("2_r1", CubeListBuilder.create().texOffs(28, 28).addBox(-2.25F, 1.0F, -3.25F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0405F, -13.308F, -3.1442F, 0.829F, 0.6981F, 0.0F));
        leaves.addOrReplaceChild("1_r1", CubeListBuilder.create().texOffs(23, 18).addBox(-2.25F, 1.0F, -1.25F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -13.0F, -2.5F, 0.2618F, 0.6981F, 0.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    private static ModelLayerLocation create(String identifier, String layer) {
        return new ModelLayerLocation(DecorationsFurnitureModule.identifier(identifier), layer);
    }

    private static ResourceLocation texture(String identifier) {
        return DecorationsFurnitureModule.identifier("textures/block/" + identifier + ".png");
    }
}
