package dev.apexstudios.fantasyfurniture.decorations.common.plushie;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;

public class PlushieModel extends PlayerModel {
    public PlushieModel(ModelPart root, boolean slim) {
        super(root, slim);
    }

    @Override
    public void setupAnim(AvatarRenderState state) {
        super.setupAnim(state);

        root.z += 4;
        root.y += 4;

        body.xRot = rot(-20F);

        leftArm.zRot = rot(-20F);
        rightArm.zRot = rot(20F);

        leftLeg.z -= 3.5F;
        leftLeg.y -= 2;
        leftLeg.xRot = rot(-90F);
        leftLeg.yRot = rot(-20F);

        rightLeg.z -= 3.5F;
        rightLeg.y -= 2;
        rightLeg.xRot = rot(-90F);
        rightLeg.yRot = rot(20F);
    }

    private static float rot(float rot) {
        return rot * (float) (Math.PI / 180F);
    }
}
