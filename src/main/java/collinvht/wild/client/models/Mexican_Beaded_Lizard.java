package collinvht.wild.client.models;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Beaded_Lizard - TheMapleMoose
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class Mexican_Beaded_Lizard<T extends Entity> extends AgeableModel<T> {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer tail1;
    public ModelRenderer frontleftleg;
    public ModelRenderer FrontLegRight;
    public ModelRenderer BackLegLeft;
    public ModelRenderer backlegRight;
    public ModelRenderer Tail2;

    public Mexican_Beaded_Lizard() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.FrontLegRight = new ModelRenderer(this, 0, 15);
        this.FrontLegRight.setRotationPoint(-1.8F, 1.6F, -4.8F);
        this.FrontLegRight.addBox(-1.0F, 0.0F, -3.0F, 2.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(FrontLegRight, 0.0F, 0.8726646259971648F, 0.0F);
        this.tail1 = new ModelRenderer(this, 24, 13);
        this.tail1.setRotationPoint(0.0F, 0.0F, 4.5F);
        this.tail1.addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.Tail2 = new ModelRenderer(this, 46, 16);
        this.Tail2.setRotationPoint(0.0F, 0.0F, 6.5F);
        this.Tail2.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 35, 24);
        this.head.setRotationPoint(0.0F, 0.0F, -6.5F);
        this.head.addBox(-2.0F, -1.5F, -5.0F, 4.0F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.BackLegLeft = new ModelRenderer(this, 0, 10);
        this.BackLegLeft.setRotationPoint(1.8F, 1.6F, 3.8F);
        this.BackLegLeft.addBox(-1.0F, 0.0F, -3.0F, 2.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(BackLegLeft, 0.0F, -0.8726646259971648F, 0.0F);
        this.backlegRight = new ModelRenderer(this, 0, 5);
        this.backlegRight.setRotationPoint(-1.8F, 1.6F, 3.8F);
        this.backlegRight.addBox(-1.0F, 0.0F, -3.0F, 2.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(backlegRight, 0.0F, 0.8726646259971648F, 0.0F);
        this.frontleftleg = new ModelRenderer(this, 0, 20);
        this.frontleftleg.setRotationPoint(1.8F, 1.6F, -4.8F);
        this.frontleftleg.addBox(-1.0F, 0.0F, -3.0F, 2.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(frontleftleg, 0.0F, -0.8726646259971648F, 0.0F);
        this.body = new ModelRenderer(this, 0, 16);
        this.body.setRotationPoint(0.0F, 21.4F, 0.0F);
        this.body.addBox(-2.5F, -2.0F, -7.0F, 5.0F, 4.0F, 12.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.FrontLegRight);
        this.body.addChild(this.tail1);
        this.tail1.addChild(this.Tail2);
        this.body.addChild(this.head);
        this.body.addChild(this.BackLegLeft);
        this.body.addChild(this.backlegRight);
        this.body.addChild(this.frontleftleg);
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(body);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

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
