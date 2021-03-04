package collinvht.wild.client.models;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * SP.eland.model - Logan
 * Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class Eland<T extends Entity> extends AgeableModel<T> {
    public ModelRenderer body_MAIN;
    public ModelRenderer shoulders;
    public ModelRenderer tail;
    public ModelRenderer left_leg_back;
    public ModelRenderer back_leg_back;
    public ModelRenderer left_leg_front;
    public ModelRenderer right_leg_front;
    public ModelRenderer neck;
    public ModelRenderer main_head;
    public ModelRenderer dulap;
    public ModelRenderer snoot;
    public ModelRenderer antler_l;
    public ModelRenderer antler_r;

    public Eland() {
        this.textureWidth = 200;
        this.textureHeight = 200;
        this.main_head = new ModelRenderer(this, 50, 180);
        this.main_head.setRotationPoint(-1.5F, -2.8F, -10.0F);
        this.main_head.addBox(-5.6F, 0.0F, -3.8F, 11.0F, 9.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(main_head, -0.8196066007575706F, 0.0F, 0.0F);
        this.right_leg_front = new ModelRenderer(this, 30, 60);
        this.right_leg_front.setRotationPoint(-6.8F, 6.0F, -0.8F);
        this.right_leg_front.addBox(-2.3F, 0.0F, -2.7F, 5.0F, 22.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.left_leg_back = new ModelRenderer(this, 30, 30);
        this.left_leg_back.setRotationPoint(8.0F, 4.0F, 2.9F);
        this.left_leg_back.addBox(-2.3F, 0.0F, -2.7F, 5.0F, 22.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.body_MAIN = new ModelRenderer(this, 0, 100);
        this.body_MAIN.setRotationPoint(-1.8F, -2.0F, 10.0F);
        this.body_MAIN.addBox(-7.7F, -8.7F, -11.4F, 19.0F, 16.0F, 17.0F, 0.0F, 0.0F, 0.0F);
        this.antler_r = new ModelRenderer(this, 80, 120);
        this.antler_r.setRotationPoint(-3.3F, 3.0F, -2.5F);
        this.antler_r.addBox(-0.8F, -0.9F, -11.0F, 3.0F, 3.0F, 15.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(antler_r, -0.7285004590772052F, 0.956091342937205F, 0.0F);
        this.shoulders = new ModelRenderer(this, 0, 140);
        this.shoulders.setRotationPoint(2.0F, -1.9F, -19.7F);
        this.shoulders.addBox(-9.7F, -9.8F, -5.7F, 19.0F, 19.0F, 14.0F, 0.0F, 0.0F, 0.0F);
        this.neck = new ModelRenderer(this, 0, 175);
        this.neck.setRotationPoint(1.4F, -2.9F, -4.1F);
        this.neck.addBox(-6.6F, -5.6F, -12.5F, 10.0F, 11.0F, 13.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(neck, -0.3642502295386026F, 0.0F, 0.0F);
        this.dulap = new ModelRenderer(this, 0, 0);
        this.dulap.setRotationPoint(-2.7F, -3.2F, -2.0F);
        this.dulap.addBox(0.0F, 0.0F, 0.0F, 2.0F, 12.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(dulap, -0.7735299191575646F, 0.0F, 0.0F);
        this.snoot = new ModelRenderer(this, 100, 185);
        this.snoot.setRotationPoint(-0.3F, 8.7F, 1.6F);
        this.snoot.addBox(-3.9F, 0.0F, -2.8F, 8.0F, 7.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 80, 90);
        this.tail.setRotationPoint(1.9F, -7.5F, 4.7F);
        this.tail.addBox(-0.9F, -1.1F, 0.0F, 2.0F, 2.0F, 15.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail, -1.2292353975059285F, 0.0F, 0.0F);
        this.left_leg_front = new ModelRenderer(this, 0, 60);
        this.left_leg_front.setRotationPoint(6.2F, 6.0F, -0.8F);
        this.left_leg_front.addBox(-2.3F, 0.0F, -2.7F, 5.0F, 22.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.back_leg_back = new ModelRenderer(this, 0, 30);
        this.back_leg_back.setRotationPoint(-4.8F, 4.0F, 2.9F);
        this.back_leg_back.addBox(-2.3F, 0.0F, -2.7F, 5.0F, 22.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.antler_l = new ModelRenderer(this, 80, 150);
        this.antler_l.setRotationPoint(2.5F, 3.1F, -3.6F);
        this.antler_l.addBox(-0.8F, -0.9F, -11.0F, 3.0F, 3.0F, 15.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(antler_l, -0.7675957970376229F, -0.956091342937205F, 0.0F);
        this.neck.addChild(this.main_head);
        this.shoulders.addChild(this.right_leg_front);
        this.body_MAIN.addChild(this.left_leg_back);
        this.main_head.addChild(this.antler_r);
        this.body_MAIN.addChild(this.shoulders);
        this.shoulders.addChild(this.neck);
        this.neck.addChild(this.dulap);
        this.main_head.addChild(this.snoot);
        this.body_MAIN.addChild(this.tail);
        this.shoulders.addChild(this.left_leg_front);
        this.body_MAIN.addChild(this.back_leg_back);
        this.main_head.addChild(this.antler_l);
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(body_MAIN);
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
