package collinvht.wild.client.models;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * CloudedLeopard - TheMapleMoose
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class CloudedLeopard<T extends Entity> extends AgeableModel<T> {
    public ModelRenderer Body;
    public ModelRenderer frontlegleft;
    public ModelRenderer backlegleft;
    public ModelRenderer frontlegright;
    public ModelRenderer Head;
    public ModelRenderer tail1;
    public ModelRenderer backlegright;
    public ModelRenderer snoot;
    public ModelRenderer leftear;
    public ModelRenderer RightEar;
    public ModelRenderer tail2;

    public CloudedLeopard() {
        this.textureWidth = 100;
        this.textureHeight = 60;
        this.frontlegright = new ModelRenderer(this, 22, 25);
        this.frontlegright.setRotationPoint(-1.7F, 2.0F, -4.5F);
        this.frontlegright.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.frontlegleft = new ModelRenderer(this, 0, 25);
        this.frontlegleft.setRotationPoint(1.7F, 2.0F, -4.5F);
        this.frontlegleft.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 40, 5);
        this.Head.setRotationPoint(0.0F, -2.2F, -6.3F);
        this.Head.addBox(-2.5F, -2.5F, -5.0F, 5.0F, 5.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.tail1 = new ModelRenderer(this, 0, 0);
        this.tail1.setRotationPoint(0.0F, -1.3F, 5.9F);
        this.tail1.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail1, -0.6255260065779288F, 0.0F, 0.0F);
        this.leftear = new ModelRenderer(this, 80, 11);
        this.leftear.setRotationPoint(1.4F, -2.1F, -2.0F);
        this.leftear.addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.1F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftear, -0.35185837453889574F, 0.0F, 0.5082398928281348F);
        this.backlegright = new ModelRenderer(this, 33, 25);
        this.backlegright.setRotationPoint(-1.7F, 2.0F, 4.5F);
        this.backlegright.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.snoot = new ModelRenderer(this, 62, 9);
        this.snoot.setRotationPoint(0.0F, 0.4F, -5.0F);
        this.snoot.addBox(-1.5F, -1.0F, -2.0F, 3.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.backlegleft = new ModelRenderer(this, 11, 25);
        this.backlegleft.setRotationPoint(1.7F, 2.0F, 4.5F);
        this.backlegleft.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.tail2 = new ModelRenderer(this, 0, 12);
        this.tail2.setRotationPoint(0.0F, 0.1F, 9.5F);
        this.tail2.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail2, 0.3909537457888271F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 0, 39);
        this.Body.setRotationPoint(0.0F, 15.0F, 0.0F);
        this.Body.addBox(-3.0F, -3.5F, -7.0F, 6.0F, 7.0F, 14.0F, 0.0F, 0.0F, 0.0F);
        this.RightEar = new ModelRenderer(this, 80, 15);
        this.RightEar.setRotationPoint(-1.4F, -2.1F, -2.0F);
        this.RightEar.addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.1F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(RightEar, -0.35185837453889574F, 0.0F, -0.5082398928281348F);
        this.Body.addChild(this.frontlegright);
        this.Body.addChild(this.frontlegleft);
        this.Body.addChild(this.Head);
        this.Body.addChild(this.tail1);
        this.Head.addChild(this.leftear);
        this.Body.addChild(this.backlegright);
        this.Head.addChild(this.snoot);
        this.Body.addChild(this.backlegleft);
        this.tail1.addChild(this.tail2);
        this.Head.addChild(this.RightEar);
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
