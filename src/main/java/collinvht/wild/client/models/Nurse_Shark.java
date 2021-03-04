package collinvht.wild.client.models;

import collinvht.wild.entity.entities.NurseSharkEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/**
 * Nurse_shark - Loxures
 * Created using Tabula 7.1.0
 */
public class Nurse_Shark extends AgeableModel<NurseSharkEntity> {
    public ModelRenderer Body;
    public ModelRenderer Tail;
    public ModelRenderer FrontRightFin;
    public ModelRenderer FrontLeftFin;
    public ModelRenderer BackRightFin;
    public ModelRenderer BackLeftFin;
    public ModelRenderer Head;
    public ModelRenderer TopFin;
    public ModelRenderer TopFin2;
    public ModelRenderer BottomFin;
    public ModelRenderer Tail2;
    public ModelRenderer BottomFin2;

    public Nurse_Shark() {
        this.textureWidth = 200;
        this.textureHeight = 100;
        this.FrontRightFin = new ModelRenderer(this, 40, 67);
        this.FrontRightFin.setRotationPoint(3.7F, 2.5F, 0.0F);
        this.FrontRightFin.addBox(-0.5F, 0.0F, 0.0F, 1, 6, 4, 0.0F);
        this.setRotateAngle(FrontRightFin, 0.31869712141416456F, 0.04537856055185257F, -0.8424704299376629F);
        this.Head = new ModelRenderer(this, 3, 51);
        this.Head.setRotationPoint(0.0F, -0.1F, -3.8F);
        this.Head.addBox(-3.5F, -2.5F, -3.5F, 7, 5, 8, 0.0F);
        this.Tail = new ModelRenderer(this, 90, 49);
        this.Tail.setRotationPoint(0.0F, 0.2F, 13.7F);
        this.Tail.addBox(-2.5F, -3.0F, 0.0F, 5, 5, 11, 0.0F);
        this.setRotateAngle(Tail, -0.08552113334772216F, 0.0F, 0.0F);
        this.BottomFin = new ModelRenderer(this, 130, 69);
        this.BottomFin.setRotationPoint(-0.5F, 2.0F, 6.6F);
        this.BottomFin.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3, 0.0F);
        this.setRotateAngle(BottomFin, 0.6829473363053812F, 0.0F, 0.0F);
        this.BottomFin2 = new ModelRenderer(this, 160, 45);
        this.BottomFin2.setRotationPoint(0.0F, 0.2F, 2.2F);
        this.BottomFin2.addBox(-0.5F, -0.7F, 0.0F, 1, 5, 17, 0.0F);
        this.setRotateAngle(BottomFin2, 0.40142572795869574F, 0.0F, 0.0F);
        this.TopFin = new ModelRenderer(this, 100, 29);
        this.TopFin.setRotationPoint(0.0F, -3.0F, -0.6F);
        this.TopFin.addBox(-0.5F, -5.0F, 0.0F, 1, 5, 4, 0.0F);
        this.setRotateAngle(TopFin, -0.42743113381341125F, 0.0F, 0.0F);
        this.BackLeftFin = new ModelRenderer(this, 70, 80);
        this.BackLeftFin.setRotationPoint(-3.6F, 3.2F, 10.1F);
        this.BackLeftFin.addBox(-0.5F, 0.0F, 0.1F, 1, 4, 4, 0.0F);
        this.setRotateAngle(BackLeftFin, 0.5918411493512771F, -0.026354471705114374F, 0.8738863564735608F);
        this.Body = new ModelRenderer(this, 40, 44);
        this.Body.setRotationPoint(0.0F, 17.0F, -7.0F);
        this.Body.addBox(-4.0F, -3.5F, 0.0F, 8, 7, 14, 0.0F);
        this.setRotateAngle(Body, 0.05061454830783556F, 0.0F, 0.0F);
        this.Tail2 = new ModelRenderer(this, 130, 53);
        this.Tail2.setRotationPoint(0.0F, -0.6F, 10.8F);
        this.Tail2.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 8, 0.0F);
        this.setRotateAngle(Tail2, 0.061086523819801536F, 0.0F, 0.0F);
        this.FrontLeftFin = new ModelRenderer(this, 40, 78);
        this.FrontLeftFin.setRotationPoint(-3.7F, 2.5F, 0.1F);
        this.FrontLeftFin.addBox(-0.5F, 0.0F, 0.0F, 1, 6, 4, 0.0F);
        this.setRotateAngle(FrontLeftFin, 0.31869712141416456F, 0.04537856055185257F, 0.8424704299376629F);
        this.TopFin2 = new ModelRenderer(this, 130, 30);
        this.TopFin2.setRotationPoint(0.0F, -3.1F, 6.1F);
        this.TopFin2.addBox(-0.5F, -3.0F, 0.0F, 1, 3, 3, 0.0F);
        this.setRotateAngle(TopFin2, -0.618021088131192F, 0.0F, 0.0F);
        this.BackRightFin = new ModelRenderer(this, 70, 68);
        this.BackRightFin.setRotationPoint(3.6F, 3.2F, 10.1F);
        this.BackRightFin.addBox(-0.5F, 0.0F, 0.1F, 1, 4, 4, 0.0F);
        this.setRotateAngle(BackRightFin, 0.5918411493512771F, -0.026354471705114374F, -0.8738863564735608F);
        this.Body.addChild(this.FrontRightFin);
        this.Body.addChild(this.Head);
        this.Body.addChild(this.Tail);
        this.Tail.addChild(this.BottomFin);
        this.Tail2.addChild(this.BottomFin2);
        this.Tail.addChild(this.TopFin);
        this.Body.addChild(this.BackLeftFin);
        this.Tail.addChild(this.Tail2);
        this.Body.addChild(this.FrontLeftFin);
        this.Tail.addChild(this.TopFin2);
        this.Body.addChild(this.BackRightFin);
    }

    @Override
    public void setRotationAngles(NurseSharkEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        float animationSpeed = 0.5F;
        float time = animationSpeed * ageInTicks;

        this.Body.rotateAngleX = headPitch * 0.01745329251F;
        this.Body.rotateAngleY = netHeadYaw * 0.01745329251F;

        if (entityIn.isInWater())
        {
            // Normalize speed + natural wave
            float swimSpeed = limbSwingAmount > animationSpeed ? 1.0F : 0.25F + 0.75F * limbSwingAmount / animationSpeed;

            // Body Wave
            float[] swimAnimation = new float[5];
            float angleBody = -0.25F * swimSpeed;
            for (int i = 0; i < 3; i++)
                swimAnimation[i] = angleBody * MathHelper.cos(time - i);

            this.Body.rotateAngleY = -0.125F * swimAnimation[1];
            this.Tail.rotateAngleY = swimAnimation[0];
            this.Tail2.rotateAngleY = swimAnimation[1];
            this.BottomFin2.rotateAngleY = swimAnimation[2];

            // Fins
            float angleFin = 0.2F * swimSpeed;

            float frontFinAnimation = angleFin - angleFin * MathHelper.cos(time);
            this.FrontLeftFin.rotateAngleX = frontFinAnimation;
            this.FrontRightFin.rotateAngleX = frontFinAnimation;

            float backFinAnimation = angleFin - angleFin * MathHelper.cos(time - 1.57079633F);
            this.BackLeftFin.rotateAngleX = backFinAnimation;
            this.BackRightFin.rotateAngleX = backFinAnimation;
        }
        else
        {
            this.Tail.rotateAngleY = 0.0F;
            this.Tail2.rotateAngleY = 0.0F;
            this.BottomFin2.rotateAngleY = 0.0F;

            float frontFinAnimation = 0.2F - 0.2F * MathHelper.cos(time);
            this.FrontLeftFin.rotateAngleX = frontFinAnimation;
            this.FrontRightFin.rotateAngleX = frontFinAnimation;

            float backFinAnimation = 0.2F - 0.2F * MathHelper.cos(time - 1.57079633F);
            this.BackLeftFin.rotateAngleX = backFinAnimation;
            this.BackRightFin.rotateAngleX = backFinAnimation;
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

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(Body);
    }
}