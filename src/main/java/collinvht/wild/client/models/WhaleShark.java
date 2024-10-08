package collinvht.wild.client.models;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * WhaleShark - MrMapleMoose
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class WhaleShark<T extends Entity> extends AgeableModel<T> {
    public ModelRenderer Body;
    public ModelRenderer head;
    public ModelRenderer Body2;
    public ModelRenderer dorsalfin;
    public ModelRenderer LeftFin;
    public ModelRenderer RightFin;
    public ModelRenderer tailbase;
    public ModelRenderer tail1;
    public ModelRenderer tail2;

    public WhaleShark() {
        this.textureWidth = 200;
        this.textureHeight = 200;
        this.LeftFin = new ModelRenderer(this, 100, 9);
        this.LeftFin.setRotationPoint(10.0F, 6.5F, -15.5F);
        this.LeftFin.addBox(0.0F, 0.0F, 0.0F, 20.0F, 2.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(LeftFin, 0.0F, -0.5864306020384839F, 0.23457224414434488F);
        this.head = new ModelRenderer(this, 0, 85);
        this.head.setRotationPoint(0.0F, 0.0F, -18.0F);
        this.head.addBox(-15.0F, -6.0F, -22.5F, 30.0F, 12.0F, 28.0F, 0.0F, 0.0F, 0.0F);
        this.tailbase = new ModelRenderer(this, 130, 93);
        this.tailbase.setRotationPoint(0.0F, -1.2F, 20.0F);
        this.tailbase.addBox(-5.0F, -6.0F, 0.0F, 10.0F, 12.0F, 14.0F, 0.0F, 0.0F, 0.0F);
        this.tail2 = new ModelRenderer(this, 26, 49);
        this.tail2.setRotationPoint(0.0F, 5.0F, 5.0F);
        this.tail2.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 14.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail2, 1.602910321115726F, 0.0F, 0.0F);
        this.dorsalfin = new ModelRenderer(this, 0, 16);
        this.dorsalfin.setRotationPoint(0.0F, -8.4F, 9.0F);
        this.dorsalfin.addBox(-1.0F, -10.0F, 0.0F, 2.0F, 10.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(dorsalfin, -0.3909537457888271F, 0.0F, 0.0F);
        this.Body2 = new ModelRenderer(this, 128, 131);
        this.Body2.setRotationPoint(0.0F, 0.0F, 20.0F);
        this.Body2.addBox(-8.0F, -8.0F, 0.0F, 16.0F, 15.0F, 20.0F, 0.0F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 130);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-12.0F, -8.5F, -20.0F, 24.0F, 17.0F, 40.0F, 0.0F, 0.0F, 0.0F);
        this.RightFin = new ModelRenderer(this, 100, 40);
        this.RightFin.setRotationPoint(-10.0F, 6.5F, -15.5F);
        this.RightFin.addBox(-20.0F, 0.0F, 0.0F, 20.0F, 2.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(RightFin, 0.0F, 0.5864306020384839F, -0.23457224414434488F);
        this.tail1 = new ModelRenderer(this, 0, 43);
        this.tail1.setRotationPoint(0.0F, -5.3F, 8.8F);
        this.tail1.addBox(-1.5F, -22.0F, 0.0F, 3.0F, 22.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail1, -1.0164797856562695F, 0.0F, 0.0F);
        this.Body.addChild(this.LeftFin);
        this.Body.addChild(this.head);
        this.Body2.addChild(this.tailbase);
        this.tail1.addChild(this.tail2);
        this.Body.addChild(this.dorsalfin);
        this.Body.addChild(this.Body2);
        this.Body.addChild(this.RightFin);
        this.tailbase.addChild(this.tail1);
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(Body);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entityIn.isInWater()) {
            Body.rotateAngleZ = MathHelper.cos(limbSwing * 0.2F) * 0.1F * limbSwingAmount;
            tailbase.rotateAngleZ = MathHelper.cos(limbSwing * 0.3F) * 0.05F * limbSwingAmount;
            tail1.rotateAngleZ = MathHelper.cos(limbSwing * 0.2F) * 0.03F * limbSwingAmount;
            tail2.rotateAngleZ = MathHelper.cos(limbSwing * 0.2F) * 0.02F * limbSwingAmount;

            head.rotateAngleX = MathHelper.cos((float) (limbSwing * 0.15F + Math.PI)) * 0.1F * limbSwingAmount;
            Body.rotateAngleX = MathHelper.cos(limbSwing * 0.15F) * 0.1F * limbSwingAmount;
            Body2.rotateAngleX = MathHelper.cos(limbSwing * 0.2F) * 0.15F * limbSwingAmount;
            tailbase.rotateAngleX = MathHelper.cos(limbSwing * 0.28F) * 0.16F * limbSwingAmount;

            Body.rotateAngleY = entityIn.rotationPitch;
            tailbase.rotateAngleY = MathHelper.cos((float) (limbSwing * 0.1F + Math.PI)) * 0.15F * limbSwingAmount;
        }
    }


    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
