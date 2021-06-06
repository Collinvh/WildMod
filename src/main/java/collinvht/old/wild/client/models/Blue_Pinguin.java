package collinvht.old.wild.client.models;

import collinvht.old.wild.entity.entities.BluePinguinEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * lilBluePenguin - TheMapleMoose Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public class Blue_Pinguin extends AgeableModel<BluePinguinEntity>
{
    public final ModelRenderer body;
    public final ModelRenderer head;
    public final ModelRenderer leftfoot;
    public final ModelRenderer rightfoot;
    public final ModelRenderer leftweeng;
    public final ModelRenderer rightweeng;
    public final ModelRenderer beeeek;

    public final float defaultXBody = 0.0781907508222411F;
    public final float defaultXLWing = -0.19547687289441354F;
    public final float defaultXRWing = -0.19547687289441354F;

    public Blue_Pinguin()
    {
        super(true, 8.0F, 3.35F);
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.leftweeng = new ModelRenderer(this, 0, 0);
        this.leftweeng.setRotationPoint(3.0F, -3.6F, -0.5F);
        this.leftweeng.addBox(0.0F, 0.0F, -2.0F, 1.0F, 6.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftweeng, -0.19547687289441354F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 25, 22);
        this.head.setRotationPoint(0.0F, -7.0F, 0.1F);
        this.head.addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(head, -0.11728612207217244F, 0.0F, 0.0F);
        this.rightweeng = new ModelRenderer(this, 11, 0);
        this.rightweeng.setRotationPoint(-4.0F, -3.6F, -0.5F);
        this.rightweeng.addBox(0.0F, 0.0F, -2.0F, 1.0F, 6.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightweeng, -0.19547687289441354F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 16);
        this.body.setRotationPoint(0.0F, 18.9F, 0.0F);
        this.body.addBox(-3.0F, -5.0F, -3.0F, 6.0F, 10.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(body, 0.0781907508222411F, 0.0F, 0.0F);
        this.rightfoot = new ModelRenderer(this, 55, 29);
        this.rightfoot.setRotationPoint(-1.8F, 4.7F, -1.2F);
        this.rightfoot.addBox(-1.0F, 0.0F, -3.0F, 2.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.beeeek = new ModelRenderer(this, 46, 29);
        this.beeeek.setRotationPoint(0.0F, 0.7F, -2.1F);
        this.beeeek.addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.leftfoot = new ModelRenderer(this, 50, 29);
        this.leftfoot.setRotationPoint(1.8F, 4.7F, -1.2F);
        this.leftfoot.addBox(-1.0F, 0.0F, -3.0F, 2.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.leftweeng);
        this.body.addChild(this.head);
        this.body.addChild(this.rightweeng);
        this.body.addChild(this.rightfoot);
        this.head.addChild(this.beeeek);
        this.body.addChild(this.leftfoot);
    }

    @Override
    public void setRotationAngles(BluePinguinEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        // TODO I added these
        this.resetRotations();
        float walk_speed = this.clampMaxValue(limbSwingAmount, 1.0F);
        float transition = entityIn.getSwimmingAnimationTransition(ageInTicks - (float)entityIn.ticksExisted);
        if (entityIn.isInWater() || entityIn.isSliding())
        {
            // TODO use radians
            //this.body.rotateAngleX += getSimplifiedRotation(ageInTicks, 0.2F, (float) (Math.PI/2), (float) (walk_speed+ (Math.PI/2)));
            this.head.rotateAngleX -= 90 * transition * Math.PI / 180F;
            this.body.rotateAngleX += 90 * transition * Math.PI / 180F + MathHelper.cos(limbSwing * 0.1F) * 0.2F * limbSwingAmount;

            if(!entityIn.isInWater() && !entityIn.isOnGround()) {
                if (Entity.horizontalMag(entityIn.getMotion()) > 1.0E-7D) {
                    this.body.rotateAngleX += -0.3F + -0.05F * MathHelper.cos(ageInTicks * 0.3F);
                }
            }

            this.body.rotateAngleY += netHeadYaw * ((float)Math.PI / 180F);

            if(entityIn.isInWater()) {
                this.leftweeng.rotateAngleZ = MathHelper.cos(limbSwing * 0.5F) * 0.7F * limbSwingAmount;

                this.rightweeng.rotateAngleZ = MathHelper.cos(limbSwing * 0.5F + (float) Math.PI) * 0.7F * limbSwingAmount;
                this.rightfoot.rotateAngleX = MathHelper.cos(limbSwing * 0.5F + (float) Math.PI) * 0.7F * limbSwingAmount;
                this.leftfoot.rotateAngleX = MathHelper.cos(limbSwing * 0.5F) * 0.7F * limbSwingAmount;

                this.body.rotateAngleZ = MathHelper.cos(limbSwing * 0.05F) * 0.3F * limbSwingAmount;
                this.body.rotateAngleZ += entityIn.getBarrelRollAnimation(ageInTicks - (float) entityIn.ticksExisted) * ((float) Math.PI / 180F);
            }
        }
        else {
            this.leftweeng.rotateAngleY = 0;
            this.rightweeng.rotateAngleY = 0;
            this.body.rotateAngleZ = MathHelper.cos(limbSwing * 0.7F) * 0.6F * limbSwingAmount;
            this.head.rotateAngleZ = MathHelper.cos(limbSwing * 0.7F + (float) Math.PI) * 0.6F * limbSwingAmount;

            this.leftweeng.rotateAngleZ = MathHelper.cos(limbSwing * 0.5F) * 0.7F * limbSwingAmount;
            this.rightweeng.rotateAngleZ = MathHelper.cos(limbSwing * 0.5F + (float) Math.PI) * 0.7F * limbSwingAmount;
        }
    }
    public void resetRotations()
    {
        // TODO
        // Copied from the constructor. Should work.
        // MUST INCLUDE THE (0.0F, 0.0F, 0.0F) ROTATIONS THAT ARE IGNORED BY TABULA
        this.setRotateAngle(body, 0.0781907508222411F, 0.0F, 0.0F);
        this.setRotateAngle(head, -0.11728612207217244F, 0.0F, 0.0F);
        this.setRotateAngle(beeeek, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftweeng, -0.19547687289441354F, 0.0F, 0.0F);
        this.setRotateAngle(rightweeng, -0.19547687289441354F, 0.0F, 0.0F);
        this.setRotateAngle(leftfoot, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightfoot, 0.0F, 0.0F, 0.0F);
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts()
    {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts()
    {
        return ImmutableList.of(body);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    /**
     * Usually, from setRotationAngles():
     *
     * limbSwing = distance walked;
     * limbSwingAmount = walking speed;
     * ageInTicks = time;
     *
     * So, you can do something like this:
     *
     * time = ageInTicks;
     * frequency = animation speed;
     * horizontalShift = animation offset ("skipping part the of animation") (e.g. for multiple leg pieces);
     * amplitude = angle;
     * amplitudeMultiplier = should use walking speed;
     * extraRotation = extra angle;
     * extraRotationMultiplier = should use walking speed;
     *
     * The way it works is that a sine function is used as a loop that goes from -ANGLE to +ANGLE.
     * This ANGLE is going to be (amplitudeMultiplier * amplitude) or (angle * walking_speed) if you follow those tips above,
     * which means that it the entity is still, walking_speed = 0, angle = 0, so no movement;
     * The faster the entity is moving, the more complete an animation loop is.
     * Therefore, it would be nice to normalize the walking_speed by a max value (see clampMaxValue() below)
     */
    public float getComplexRotation(float time, float frequency, float horizontalShift, float amplitude, float amplitudeMultiplier, float extraRotation, float extraRotationMultiplier)
    {
        return extraRotationMultiplier * extraRotation + amplitudeMultiplier * amplitude * MathHelper.sin(frequency * time + horizontalShift);
    }

    public float getSimplifiedRotation(float time, float frequency, float amplitude, float amplitudeMultiplier)
    {
        return amplitudeMultiplier * amplitude * MathHelper.sin(frequency * time);
    }

    public float clampMaxValue(float value, float maxValue)
    {
        return value > maxValue ? 1.0F : value / maxValue;
    }
}
