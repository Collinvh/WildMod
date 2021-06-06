package collinvht.wild.entity.obj;

import collinvht.wild.entity.obj.goals.SwimmingGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WhaleSharkEntity extends WaterMobEntity {
    /*
    General storage for other functions
     */
    private int bubbleCooldown;


    public WhaleSharkEntity(EntityType<? extends WaterMobEntity> type, World worldIn) {
        super(type, worldIn);
        this.setPathPriority(PathNodeType.WATER, 0.0F);

        this.bubbleCooldown = 10 + rand.nextInt(50);

        this.moveController = new Movehelpers.AquaticMoveController(this, 15);
    }

    protected PathNavigator createNavigator(World worldIn) {
        return new SwimmerPathNavigator(this, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimmingGoal(this, 1, 10, true));
    }

    @Override
    public void tick() {
        super.tick();

        if(isInWater()) {
            if (rand.nextBoolean()) {
                for (int i = 0; i < rand.nextInt(3); i++) {
                    this.getEntityWorld().addParticle(ParticleTypes.BUBBLE_COLUMN_UP, false, this.getPosXRandom(1), this.getPosYRandom(1), this.getPosZRandom(1), 0.5, 0.5, 0.5);
                }

                if(bubbleCooldown == 0) {
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
                        if(playerEntity.isCreative()) {
                            return;
                        }

                        if(rand.nextBoolean() && rand.nextInt() == 0) {
                            playerEntity.giveExperiencePoints(1000);
                        }

                        if(playerEntity.getAir() > (playerEntity.getMaxAir() - 50)) {
                            return;
                        }

                        playerEntity.setAir(Math.min(playerEntity.getAir() + 50, playerEntity.getMaxAir()));
                    });

                    bubbleCooldown = rand.nextInt(50);
                }
            }
        }
    }

    public double getPosYRandom(double p_226282_1_) {
        return this.getPosYHeight((2.0D * this.rand.nextDouble() - 1.0D) * p_226282_1_);
    }

    public static HashMap<Attribute, Double> getAttributes() {
        HashMap<Attribute, Double> map = new HashMap<>();
        map.put(Attributes.MOVEMENT_SPEED, 0.9D);
        return map;
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
}
