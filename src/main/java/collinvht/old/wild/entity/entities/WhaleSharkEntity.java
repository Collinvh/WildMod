package collinvht.old.wild.entity.entities;

import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.PathType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WhaleSharkEntity extends WaterMobEntity {
    private int cooldown;
    public WhaleSharkEntity(EntityType<? extends WhaleSharkEntity> type, World worldIn) {
        super(type, worldIn);
        this.setPathPriority(PathNodeType.WATER, 0.0F);
        this.moveController = new MoveHelperController(this);
        this.cooldown = 10 + rand.nextInt(50);
    }

    protected PathNavigator createNavigator(World worldIn) {
        return new SwimmerPathNavigator(this, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute func_234204_eW_() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.9D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new RandomDeepSwimmingGoal(this, 1.0D, 10));
    }

    @Override
    public void tick() {
        super.tick();

        if(this.isInWater()) {
            if (rand.nextBoolean()) {
                for (int i = 0; i < rand.nextInt(3); i++) {
                    this.getEntityWorld().addParticle(ParticleTypes.BUBBLE_COLUMN_UP, false, this.getPosXRandom(1), this.getPosYRandom(1), this.getPosZRandom(1), 0.5, 0.5, 0.5);
                }
            }
        }

        if(cooldown == 0) {
            BlockPos pos1 = new BlockPos(getPosX() -5, getPosY() +6, getPosZ() -5);
            BlockPos pos2 = new BlockPos(getPosX() +5, getPosY() -6, getPosZ() +5);
            List<Entity> entities = world.getLoadedEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(pos1, pos2));

            ArrayList<PlayerEntity> playerEntities = new ArrayList<>();
            entities.forEach(entity -> {
                if(entity instanceof PlayerEntity) {
                    playerEntities.add((PlayerEntity) entity);
                }
            });

            playerEntities.forEach(playerEntity -> {
                if(rand.nextBoolean() && rand.nextInt() == 0) {
                    playerEntity.giveExperiencePoints(100);
                }

                if(playerEntity.getAir() > (playerEntity.getMaxAir() - 50)) {
                    return;
                }

                playerEntity.setAir(Math.min(playerEntity.getAir() + 50, playerEntity.getMaxAir()));
            });

            cooldown = rand.nextInt(50);
        } else {
            cooldown--;
        }
    }


    public double getPosYRandom(double p_226282_1_) {
        return this.getPosYHeight((2.0D * this.rand.nextDouble() - 1.0D) * p_226282_1_);
    }

    public void travel(Vector3d travelVector) {
        if (this.isServerWorld() && this.isInWater()) {
            this.moveRelative(this.getAIMoveSpeed(), travelVector);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale(0.9D));
            if (this.getAttackTarget() == null) {
                this.setMotion(this.getMotion().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(travelVector);
        }

    }

    public static boolean func_223363_b(EntityType<? extends WhaleSharkEntity> type, IWorld worldIn, SpawnReason reason, BlockPos p_223363_3_, Random randomIn) {
        return worldIn.getBlockState(p_223363_3_).isIn(Blocks.WATER) && worldIn.getBlockState(p_223363_3_.up()).isIn(Blocks.WATER);
    }

    static class MoveHelperController extends MovementController {
        private final WhaleSharkEntity dolphin;

        public MoveHelperController(WhaleSharkEntity dolphinIn) {
            super(dolphinIn);
            this.dolphin = dolphinIn;
        }

        public void tick() {
            if (this.dolphin.isInWater()) {
                this.dolphin.setMotion(this.dolphin.getMotion().add(0.0D, 0.005D, 0.0D));
            }

            if (this.action == MovementController.Action.MOVE_TO && !this.dolphin.getNavigator().noPath()) {
                double d0 = this.posX - this.dolphin.getPosX();
                double d1 = this.posY - this.dolphin.getPosY();
                double d2 = this.posZ - this.dolphin.getPosZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (d3 < (double)2.5000003E-7F) {
                    this.mob.setMoveForward(0.0F);
                } else {
                    float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.dolphin.rotationYaw = this.limitAngle(this.dolphin.rotationYaw, f, 2.4F);
                    this.dolphin.renderYawOffset = this.dolphin.rotationYaw;
                    this.dolphin.rotationYawHead = this.dolphin.rotationYaw;
                    float f1 = (float)(this.speed * this.dolphin.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    if (this.dolphin.isInWater()) {
                        this.dolphin.setAIMoveSpeed(f1 * 0.02F);
                        float f2 = -((float)(MathHelper.atan2(d1, (double)MathHelper.sqrt(d0 * d0 + d2 * d2)) * (double)(180F / (float)Math.PI)));
                        f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
                        this.dolphin.rotationPitch = this.limitAngle(this.dolphin.rotationPitch, f2, 25);
                        float f3 = MathHelper.cos(this.dolphin.rotationPitch * ((float)Math.PI / 180F));
                        float f4 = MathHelper.sin(this.dolphin.rotationPitch * ((float)Math.PI / 180F));
                        this.dolphin.moveForward = f3 * f1;
                        this.dolphin.moveVertical = -f4 * f1;
                    } else {
                        this.dolphin.setAIMoveSpeed(f1 * 0.1F);
                    }

                }
            } else {
                this.dolphin.setAIMoveSpeed(0.0F);
                this.dolphin.setMoveStrafing(0.0F);
                this.dolphin.setMoveVertical(0.0F);
                this.dolphin.setMoveForward(0.0F);
            }
        }
    }


    class RandomDeepSwimmingGoal extends NurseSharkEntity.RandomSwimmingGoal {
        public RandomDeepSwimmingGoal(CreatureEntity creature, double speed, int chance) {
            super(creature, speed, chance);
        }

        @Nullable
        protected Vector3d getPosition() {
            Vector3d vector3d = RandomPositionGenerator.findRandomTarget(this.creature, 10, 40);

            for(int i = 0; vector3d != null && !this.creature.world.getBlockState(new BlockPos(vector3d)).allowsMovement(this.creature.world, new BlockPos(vector3d), PathType.WATER) && i++ < 10; vector3d = RandomPositionGenerator.findRandomTarget(this.creature, 10, 7)) {
                if(vector3d.y < 50) {
                   return vector3d;
                }
            }

            if(rand.nextInt(5) < 3 && rand.nextBoolean()) {
                return super.getPosition();
            }

            return null;
        }
    }
}
