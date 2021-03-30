package collinvht.wild.client.models;

import collinvht.wild.entity.entities.BeetleEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class BeetleModel extends EntityModel<BeetleEntity> {
    public ModelRenderer part1;

    public BeetleModel() {
        this.textureWidth = 32;
        this.textureHeight = 16;
        this.part1 = new ModelRenderer(this, -16, 0);
        this.part1.setRotationPoint(0.0F, 23.5F, 0.0F);
        this.part1.addBox(-8.0F, 0.0F, -8.0F, 16.0F, 0.0F, 16.0F, 0.0F, 0.0F, 0.0F);
    }

    @Override
    public void setRotationAngles(BeetleEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float trans = MathHelper.lerp(ageInTicks - (float) entityIn.ticksExisted, 90,90);

        if(entityIn.isBesideClimbableBlock()) {
            this.part1.rotateAngleX += trans;
        } else {
            this.part1.rotateAngleX = 0;
        }
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.part1).forEach((modelRenderer) -> modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
    }
}
