package collinvht.wild.entity.obj;

import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.util.math.MathHelper;

public class Movehelpers {
    static class AquaticMoveController extends MovementController {
        private final WaterMobEntity entity;
        private final int rotationAngle;

        public AquaticMoveController(WaterMobEntity entity, int rotationAngle) {
            super(entity);
            this.entity = entity;
            this.rotationAngle = rotationAngle;
        }

        public void tick() {
            if (this.entity.isInWater()) {
                this.entity.setMotion(this.entity.getMotion().add(0.0D, 0.005D, 0.0D));
            }

            if (this.action == MovementController.Action.MOVE_TO && !this.entity.getNavigator().noPath()) {
                double d0 = this.posX - this.entity.getPosX();
                double d1 = this.posY - this.entity.getPosY();
                double d2 = this.posZ - this.entity.getPosZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (d3 < (double)2.5000003E-7F) {
                    this.mob.setMoveForward(0.0F);
                } else {
                    float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.entity.rotationYaw = this.limitAngle(this.entity.rotationYaw, f, 2.4F);
                    this.entity.renderYawOffset = this.entity.rotationYaw;
                    this.entity.rotationYawHead = this.entity.rotationYaw;
                    float f1 = (float)(this.speed * this.entity.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    if (this.entity.isInWater()) {
                        this.entity.setAIMoveSpeed(f1 * 0.02F);
                        float f2 = -((float)(MathHelper.atan2(d1, MathHelper.sqrt(d0 * d0 + d2 * d2)) * (double)(180F / (float)Math.PI)));
                        f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
                        this.entity.rotationPitch = this.limitAngle(this.entity.rotationPitch, f2, rotationAngle);
                        float f3 = MathHelper.cos(this.entity.rotationPitch * ((float)Math.PI / 180F));
                        float f4 = MathHelper.sin(this.entity.rotationPitch * ((float)Math.PI / 180F));
                        this.entity.moveForward = f3 * f1;
                        this.entity.moveVertical = -f4 * f1;
                    } else {
                        this.entity.setAIMoveSpeed(f1 * 0.1F);
                    }

                }
            } else {
                this.entity.setAIMoveSpeed(0.0F);
                this.entity.setMoveStrafing(0.0F);
                this.entity.setMoveVertical(0.0F);
                this.entity.setMoveForward(0.0F);
            }
        }
    }
}
