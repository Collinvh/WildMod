package collinvht.wild.entity.entities;


import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class NurseSharkEntity extends WaterMobEntity {
    public NurseSharkEntity(EntityType<? extends NurseSharkEntity> type, World worldIn) {
        super(type, worldIn);
        this.setPathPriority(PathNodeType.WATER, 0.0F);
        this.moveController = new MoveHelperController(this);
    }

    public static AttributeModifierMap.MutableAttribute func_234204_eW_() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.15F).createMutableAttribute(Attributes.ATTACK_DAMAGE, 6.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RandomSwimmingGoal(this, 1D, 1));
    }

    public void travel(Vector3d travelVector) {
        if (this.isServerWorld() && this.isInWater()) {
            this.moveRelative(this.getAIMoveSpeed(), travelVector);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale(0.9D));
        } else {
            this.moveRelative(this.getAIMoveSpeed(), travelVector);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale(0.5D));
        }

    }

    @Override
    public boolean isPushedByWater() {
        return false;
    }

    static class MoveHelperController extends MovementController {
        private final NurseSharkEntity shark;

        public MoveHelperController(NurseSharkEntity dolphinIn) {
            super(dolphinIn);
            this.shark = dolphinIn;
        }

        public void tick() {
            LivingEntity livingentity = this.shark.getAttackTarget();
            if (this.shark.isInWater()) {
                if (livingentity != null && livingentity.getPosY() > this.shark.getPosY() || this.shark.getRNG().nextBoolean()) {
                    this.shark.setMotion(this.shark.getMotion().add(0.0D, 0.003D, 0.0D));
                }

                if (this.action != MovementController.Action.MOVE_TO || this.shark.getNavigator().noPath()) {
                    this.shark.setAIMoveSpeed(0.0F);
                    return;
                }

                double d0 = this.posX - this.shark.getPosX();
                double d1 = this.posY - this.shark.getPosY();
                double d2 = this.posZ - this.shark.getPosZ();
                double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.shark.rotationYaw = this.limitAngle(this.shark.rotationYaw, f, 40.0F);
                this.shark.renderYawOffset = this.shark.rotationYaw;
                float f1 = (float)(this.speed * this.shark.getAttributeValue(Attributes.MOVEMENT_SPEED));
                float f2 = MathHelper.lerp(0.125F, this.shark.getAIMoveSpeed(), f1);
                this.shark.setAIMoveSpeed(f2);
                this.shark.setMotion(this.shark.getMotion().add((double)f2 * d0 * 0.005D, (double)f2 * d1 * 0.15D, (double)f2 * d2 * 0.005D));
            } else {
                if (!this.shark.onGround) {
                    this.shark.setMotion(this.shark.getMotion().add(0.0D, -0.008D, 0.0D));
                }

                super.tick();
            }

        }
    }



    static class RandomSwimmingGoal extends RandomWalkingGoal {
        public RandomSwimmingGoal(CreatureEntity creature, double speed, int chance) {
            super(creature, speed, chance);
        }

        @Nullable
        protected Vector3d getPosition() {
            Vector3d vector3d = RandomPositionGenerator.findRandomTarget(this.creature, 10, 7);

            for(int i = 0; vector3d != null && !this.creature.world.getBlockState(new BlockPos(vector3d)).allowsMovement(this.creature.world, new BlockPos(vector3d), PathType.WATER) && i++ < 10; vector3d = RandomPositionGenerator.findRandomTarget(this.creature, 10, 7)) {
            }

            return vector3d;
        }
    }


}
