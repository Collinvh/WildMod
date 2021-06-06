package collinvht.old.wild.client.models;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Ring-necked_Pheasant_Vanilla - SpiggitySpag
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class Ring_necked_Pheasant<T extends Entity> extends AgeableModel<T> {
    public ModelRenderer BodyMain;
    public ModelRenderer Head;
    public ModelRenderer LeftWing;
    public ModelRenderer RightWing;
    public ModelRenderer BodySecondary;
    public ModelRenderer LeftLeg;
    public ModelRenderer RightLeg;
    public ModelRenderer Beak;
    public ModelRenderer HeadFeather;
    public ModelRenderer Tail;

    public Ring_necked_Pheasant() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.BodyMain = new ModelRenderer(this, 0, 48);
        this.BodyMain.setRotationPoint(0.0F, 16.7F, 0.0F);
        this.BodyMain.addBox(-4.0F, -3.5F, -4.5F, 8.0F, 7.0F, 9.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(BodyMain, -0.0911061832922575F, 0.0F, 0.0F);
        this.Tail = new ModelRenderer(this, 15, 27);
        this.Tail.setRotationPoint(0.0F, -3.2F, 0.6F);
        this.Tail.addBox(-3.0F, 0.0F, 0.0F, 6.0F, 0.0F, 15.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Tail, 0.04555309164612875F, 0.0F, 0.0F);
        this.HeadFeather = new ModelRenderer(this, 58, 38);
        this.HeadFeather.setRotationPoint(0.0F, -7.3F, -1.5F);
        this.HeadFeather.addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.RightLeg = new ModelRenderer(this, 5, 14);
        this.RightLeg.setRotationPoint(-2.0F, 3.2F, 0.8F);
        this.RightLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(RightLeg, 0.0911061832922575F, 0.0F, 0.0F);
        this.Beak = new ModelRenderer(this, 51, 45);
        this.Beak.setRotationPoint(0.0F, -5.5F, -4.0F);
        this.Beak.addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 39, 51);
        this.Head.setRotationPoint(0.0F, 0.3F, -2.6F);
        this.Head.addBox(-2.0F, -8.0F, -4.0F, 4.0F, 9.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(Head, 0.0911061832922575F, 0.0F, 0.0F);
        this.BodySecondary = new ModelRenderer(this, 0, 36);
        this.BodySecondary.setRotationPoint(0.0F, 0.0F, 4.5F);
        this.BodySecondary.addBox(-3.5F, -3.5F, 0.0F, 7.0F, 6.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.LeftWing = new ModelRenderer(this, 44, 2);
        this.LeftWing.setRotationPoint(4.0F, -3.5F, -4.1F);
        this.LeftWing.addBox(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 5, 4);
        this.LeftLeg.setRotationPoint(2.0F, 3.2F, 0.8F);
        this.LeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(LeftLeg, 0.0911061832922575F, 0.0F, 0.0F);
        this.RightWing = new ModelRenderer(this, 21, 2);
        this.RightWing.setRotationPoint(-4.0F, -3.5F, -4.1F);
        this.RightWing.addBox(-1.0F, 0.0F, 0.0F, 1.0F, 5.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.BodySecondary.addChild(this.Tail);
        this.Head.addChild(this.HeadFeather);
        this.BodyMain.addChild(this.RightLeg);
        this.Head.addChild(this.Beak);
        this.BodyMain.addChild(this.Head);
        this.BodyMain.addChild(this.BodySecondary);
        this.BodyMain.addChild(this.LeftWing);
        this.BodyMain.addChild(this.LeftLeg);
        this.BodyMain.addChild(this.RightWing);
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(BodyMain);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
