package collinvht.wild.client.models;

import collinvht.wild.entity.entities.RedPandaEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/**
 * Red panda - Loxures
 * Created using Tabula 7.1.0
 */
public class Red_Panda extends AgeableModel<RedPandaEntity> {
    public ModelRenderer RightLeg;
    public ModelRenderer Body;
    public ModelRenderer LeftLeg;
    public ModelRenderer Tail;
    public ModelRenderer RightArm;
    public ModelRenderer LeftArm;
    public ModelRenderer Neck;
    public ModelRenderer Head;
    public ModelRenderer RightEar;
    public ModelRenderer Mouth;
    public ModelRenderer Head2;
    public ModelRenderer LeftEar;

    public Red_Panda() {
        super(true, 8.0F, 3.35F);
        this.textureWidth = 90;
        this.textureHeight = 100;
        this.LeftEar = new ModelRenderer(this, 38, 15);
        this.LeftEar.setRotationPoint(-2.0F, -0.4F, 0.9F);
        this.LeftEar.addBox(-0.5F, -2.0F, 0.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(LeftEar, 0.14905111812031574F, -0.5614724203665759F, -0.20734511513692636F);
        this.RightLeg = new ModelRenderer(this, 77, 3);
        this.RightLeg.setRotationPoint(3.0F, 17.2F, 3.8F);
        this.RightLeg.addBox(-1.4F, 0.0F, -1.5F, 2, 7, 3, 0.0F);
        this.RightArm = new ModelRenderer(this, 0, 21);
        this.RightArm.setRotationPoint(2.6F, 1.8F, -4.3F);
        this.RightArm.addBox(-1.3F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(RightArm, -0.019198621771937624F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 25, 22);
        this.Head.setRotationPoint(0.0F, -0.1F, -3.1F);
        this.Head.addBox(-2.5F, -2.0F, -1.5F, 5, 4, 3, 0.0F);
        this.setRotateAngle(Head, 0.022165681500327983F, 0.0F, 0.0F);
        this.Mouth = new ModelRenderer(this, 46, 24);
        this.Mouth.setRotationPoint(0.0F, 1.9F, -1.1F);
        this.Mouth.addBox(-1.5F, -2.0F, -2.0F, 3, 2, 2, 0.0F);
        this.setRotateAngle(Mouth, -0.09250245035569946F, 0.0F, 0.0F);
        this.LeftArm = new ModelRenderer(this, 10, 21);
        this.LeftArm.setRotationPoint(-2.6F, 1.7F, -4.3F);
        this.LeftArm.addBox(-0.7F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(LeftArm, -0.019198621771937624F, 0.0F, 0.0F);
        this.Head2 = new ModelRenderer(this, 60, 25);
        this.Head2.setRotationPoint(0.0F, -0.11F, -2.9F);
        this.Head2.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(Head2, 0.6162757588791978F, 0.0F, 0.0F);
        this.Tail = new ModelRenderer(this, 42, 34);
        this.Tail.setRotationPoint(0.0F, -0.5F, 4.7F);
        this.Tail.addBox(-2.0F, -2.0F, -0.3F, 4, 4, 11, 0.0F);
        this.setRotateAngle(Tail, -0.20472712125893486F, 0.0F, 0.0F);
        this.RightEar = new ModelRenderer(this, 22, 15);
        this.RightEar.setRotationPoint(2.0F, -0.4F, 0.9F);
        this.RightEar.addBox(-0.5F, -2.0F, 0.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(RightEar, 0.14905111812031574F, 0.5614724203665759F, 0.20734511513692636F);
        this.Body = new ModelRenderer(this, 0, 32);
        this.Body.setRotationPoint(0.0F, 17.2F, 0.0F);
        this.Body.addBox(-3.0F, -3.0F, -5.5F, 6, 6, 11, 0.0F);
        this.setRotateAngle(Body, 0.019198621771937624F, 0.0F, 0.0F);
        this.Neck = new ModelRenderer(this, 74, 39);
        this.Neck.setRotationPoint(0.0F, -0.4F, -5.6F);
        this.Neck.addBox(-2.0F, -2.0F, -1.7F, 4, 4, 2, 0.0F);
        this.setRotateAngle(Neck, 0.03665191429188092F, 0.0F, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 62, 3);
        this.LeftLeg.setRotationPoint(-3.0F, 17.2F, 3.8F);
        this.LeftLeg.addBox(-0.6F, 0.0F, -1.5F, 2, 7, 3, 0.0F);
        this.Head.addChild(this.LeftEar);
        this.Body.addChild(this.RightArm);
        this.Neck.addChild(this.Head);
        this.Head.addChild(this.Mouth);
        this.Body.addChild(this.LeftArm);
        this.Head.addChild(this.Head2);
        this.Body.addChild(this.Tail);
        this.Head.addChild(this.RightEar);
        this.Body.addChild(this.Neck);
    }

    @Override
    public void setRotationAngles(RedPandaEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.LeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.7F) * 1.4F * limbSwingAmount;
        this.RightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.7F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.LeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.8F) * 1.4F * limbSwingAmount;
        this.RightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.8F + (float)Math.PI) * 1.4F * limbSwingAmount;

        this.Head.rotateAngleY = netHeadYaw * 0.015F;
        this.Head.rotateAngleX = headPitch * 0.015F;

        this.Tail.rotateAngleY = MathHelper.cos(limbSwing) * 0.4F * limbSwingAmount;
        this.Tail.rotateAngleX = MathHelper.cos(limbSwing) * 0.1F * limbSwingAmount;

        this.LeftEar.rotateAngleX = MathHelper.cos(limbSwing) * 0.1F * limbSwingAmount;
        this.RightEar.rotateAngleX = MathHelper.cos(limbSwing + (float)Math.PI) * 0.1F * limbSwingAmount;

        this.Neck.rotateAngleY = MathHelper.cos(limbSwing * 0.3F) * 0.3F * limbSwingAmount;
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(this.Body, this.LeftLeg, this.RightLeg);
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
