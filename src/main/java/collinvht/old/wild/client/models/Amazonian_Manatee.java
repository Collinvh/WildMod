package collinvht.old.wild.client.models;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * AmazonianManatee - TheMapleMoose
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class Amazonian_Manatee<T extends Entity> extends AgeableModel<T> {
    public ModelRenderer body1;
    public ModelRenderer bodyback;
    public ModelRenderer bodyfront;
    public ModelRenderer leftflipper;
    public ModelRenderer rightflipper;
    public ModelRenderer tailbase;
    public ModelRenderer fluke;
    public ModelRenderer head;

    public Amazonian_Manatee() {
        this.textureWidth = 200;
        this.textureHeight = 100;
        this.bodyback = new ModelRenderer(this, 0, 0);
        this.bodyback.setRotationPoint(0.0F, 0.5F, 13.0F);
        this.bodyback.setTextureOffset(74, 68).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(bodyback, -0.07679448875222618F, 0.0F, 0.0F);
        this.bodyfront = new ModelRenderer(this, 0, 0);
        this.bodyfront.setRotationPoint(0.0F, 0.3F, -10.0F);
        this.bodyfront.setTextureOffset(12, 31).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(bodyfront, 0.08726646259971647F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.6F, -5.0F);
        this.head.setTextureOffset(60, 46).addBox(-4.0F, -3.0F, -4.0F, 8.0F, 6.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(head, 0.17453292519943295F, 0.0F, 0.0F);
        this.leftflipper = new ModelRenderer(this, 0, 0);
        this.leftflipper.setRotationPoint(4.5F, 2.9F, -5.1F);
        this.leftflipper.setTextureOffset(80, 20).addBox(0.0F, -1.0F, -3.0F, 8.0F, 2.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftflipper, 0.0F, 0.0F, 0.8726646259971648F);
        this.tailbase = new ModelRenderer(this, 0, 0);
        this.tailbase.setRotationPoint(0.0F, 0.5F, 5.9F);
        this.tailbase.setTextureOffset(120, 70).addBox(-4.0F, -4.0F, -5.0F, 8.0F, 8.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tailbase, -0.17453292519943295F, 0.0F, 0.0F);
        this.fluke = new ModelRenderer(this, 0, 0);
        this.fluke.setRotationPoint(0.0F, 0.3F, 1.6F);
        this.fluke.setTextureOffset(158, 70).addBox(-5.0F, -1.0F, 0.0F, 10.0F, 2.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.body1 = new ModelRenderer(this, 0, 0);
        this.body1.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.body1.setTextureOffset(4, 56).addBox(-6.0F, -6.0F, -10.0F, 12.0F, 12.0F, 20.0F, 0.0F, 0.0F, 0.0F);
        this.rightflipper = new ModelRenderer(this, 0, 0);
        this.rightflipper.setRotationPoint(-4.5F, 2.9F, -5.1F);
        this.rightflipper.setTextureOffset(120, 20).addBox(-8.0F, -1.0F, -3.0F, 8.0F, 2.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightflipper, 0.0F, 0.0F, -0.8726646259971648F);
        this.body1.addChild(this.bodyback);
        this.body1.addChild(this.bodyfront);
        this.bodyfront.addChild(this.head);
        this.body1.addChild(this.leftflipper);
        this.bodyback.addChild(this.tailbase);
        this.tailbase.addChild(this.fluke);
        this.body1.addChild(this.rightflipper);
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(body1);
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
