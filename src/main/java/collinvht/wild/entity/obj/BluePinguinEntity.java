package collinvht.wild.entity.obj;
import collinvht.old.wild.entity.entities.goal.PinguinJumpGoal;
import collinvht.wild.entity.WildEntities;
import collinvht.wild.entity.obj.goals.IceSkateGoal;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.DebugPacketSender;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.WalkAndSwimNodeProcessor;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.HashMap;

public class BluePinguinEntity extends AnimalEntity {
    public static final float ROLL_SPEED = 35.0F;

    public BarrelRollGoal barrelRollGoal;
    public float prevRotationRoll;
    public float rotationRoll;

    private float swimAnimationPrev;
    private float swimAnimation;

    private static final DataParameter<Boolean> SLIDING = EntityDataManager.createKey(AgeableEntity.class, DataSerializers.BOOLEAN);
    public BluePinguinEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
        this.setPathPriority(PathNodeType.WATER, 0.0F);
        this.moveController = new MoveHelperController(this);
        this.stepHeight = 1.0F;
    }


    public static HashMap<Attribute, Double> getAttributes() {
        HashMap<Attribute, Double> map = new HashMap<>();
        map.put(Attributes.MOVEMENT_SPEED, 0.5D);
        map.put(Attributes.ATTACK_DAMAGE, 6.0D);
        return map;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new Wander(this, 0.4D));
        this.goalSelector.addGoal(0, new IceSkateGoal(this));
        this.goalSelector.addGoal(1, new TravelGoal(this, 0.8D));
        this.goalSelector.addGoal(2, new GoToWaterGoal(this, 0.4D));
        this.goalSelector.addGoal(2, new PinguinJumpGoal(this, 6));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 10));
        this.goalSelector.addGoal(3, new BreedGoal(this, 0.5D));
        this.goalSelector.addGoal(0, new FollowParentGoal(this, 0.2D));
        this.barrelRollGoal = new BarrelRollGoal(this);
        //this.goalSelector.addGoal(2, this.barrelRollGoal);
    }

    @Override
    protected void updateAITasks() {
        this.prevRotationRoll = this.rotationRoll;
        this.rotationRoll = this.barrelRollGoal.getBarrelRollRotation();
    }

    @Override
    public void livingTick()
    {
        if (this.world.isRemote)
        {
            this.prevRotationRoll = this.rotationRoll;

            if (this.rotationRoll == 360.0F || (this.rotationRoll > 0.0F && this.rotationRoll % (10.0F * ROLL_SPEED) == 0))
            {
                Vector3d look = this.getLook(0.0F);
                float dx = MathHelper.cos(this.rotationYaw * ((float) Math.PI / 180F)) * 0.3F;
                float dz = MathHelper.sin(this.rotationYaw * ((float) Math.PI / 180F)) * 0.3F;
                float dRand = 1.2F - this.rand.nextFloat() * 0.7F;

                for (int i = 0; i < 2; ++i)
                {
                    this.world.addParticle(ParticleTypes.BUBBLE, this.getPosX() - look.x * (double) dRand + (double) dx, this.getPosY() - look.y, this.getPosZ() - look.z * (double) dRand + (double) dz, 0.0D, 0.0D, 0.0D);
                    this.world.addParticle(ParticleTypes.BUBBLE, this.getPosX() - look.x * (double) dRand - (double) dx, this.getPosY() - look.y, this.getPosZ() - look.z * (double) dRand - (double) dz, 0.0D, 0.0D, 0.0D);
                }
            }

            this.rotationRoll = Math.max(0.0F, this.rotationRoll - ROLL_SPEED);
        }

        super.livingTick();
    }
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == Items.SALMON || stack.getItem() == Items.COD;
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(SLIDING, false);
    }

    @Override
    public boolean isOnGround() {
        return super.isOnGround();
    }

    public void setSliding(boolean sliding) {
        dataManager.set(SLIDING, sliding);
    }

    public boolean isSliding() {
        return dataManager.get(SLIDING);
    }

    @Override
    public void tick()
    {
        if (this.world.isRemote)
        {
            // It would be good to change the bounding box
            if (this.swimAnimation != this.swimAnimationPrev)
                this.recalculateSize();

            this.swimAnimationPrev = this.swimAnimation;
            float transitionSpeed = 0.1F;
            if (this.isInWater() || isSliding())
                this.swimAnimation = MathHelper.clamp(this.swimAnimation + transitionSpeed, 0.0F, 1.0F);
            else
                this.swimAnimation = MathHelper.clamp(this.swimAnimation - transitionSpeed, 0.0F, 1.0F);
        }
        super.tick();
    }

    @Override
    public boolean isPushedByWater() {
        return false;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    protected PathNavigator createNavigator(World worldIn) {
        return new Navigator(this, worldIn);
    }

    @Override
    public EntitySize getSize(Pose poseIn)
    {
        if (this.swimAnimation > 0.0F)
        {
            float heightFactor = 0.5F;
            return super.getSize(poseIn).scale(1.0F + (heightFactor * swimAnimation), 1.0F - (heightFactor * this.swimAnimation));
        }
        else
            return super.getSize(poseIn);
    }

    @Override
    public boolean canBeLeashedTo(PlayerEntity player) {
        return false;
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.WATER;
    }

    @Override
    public void travel(Vector3d travelVector) {
        if (this.isServerWorld() && this.isInWater()) {
            this.moveRelative(0.1F, travelVector);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale(0.9D));
            if (this.getAttackTarget() == null) {
                this.setMotion(this.getMotion().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(travelVector);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public float getSwimmingAnimationTransition(float partialTick)
    {
        return MathHelper.lerp(partialTick, this.swimAnimationPrev, this.swimAnimation);
    }

    @Override
    protected float getWaterSlowDown() {
        return  0.05F;
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return (AgeableEntity) WildEntities.BLUE_PINGUIN.getEntity().getEntityType().create(p_241840_1_);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 10)
            this.rotationRoll = 360.0F;
        else
            super.handleStatusUpdate(id);
    }

    @OnlyIn(Dist.CLIENT)
    public float getBarrelRollAnimation(float partialTick)
    {
        if (this.rotationRoll > 0.0F && this.rotationRoll < 360.0F)
            return MathHelper.lerp(partialTick, this.prevRotationRoll, this.rotationRoll);
        else
            return 0.0F;
    }


    static class GoToWaterGoal extends MoveToBlockGoal {
        private final BluePinguinEntity turtle;

        private GoToWaterGoal(BluePinguinEntity turtle, double speedIn) {
            super(turtle, turtle.isChild() ? 2.0D : speedIn, 24);
            this.turtle = turtle;
            this.field_203112_e = -1;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return !this.turtle.isInWater() && this.timeoutCounter <= 1200 && this.shouldMoveTo(this.turtle.world, this.destinationBlock);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            if(this.turtle.isSliding()) {
                return false;
            }
            if (this.turtle.isChild() && !this.turtle.isInWater()) {
                return super.shouldExecute();
            } else {
                return !this.turtle.isInWater() && super.shouldExecute();
            }
        }

        public boolean shouldMove() {
            return this.timeoutCounter % 160 == 0;
        }

        /**
         * Return true to set given position as destination
         */
        protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
            return worldIn.getBlockState(pos).isIn(Blocks.WATER);
        }
    }

    static class MoveHelperController extends MovementController {
        private final BluePinguinEntity entity;

        MoveHelperController(BluePinguinEntity entity) {
            super(entity);
            this.entity = entity;
        }

        private void updateSpeed() {
            if (this.entity.isInWater()) {
                this.entity.setMotion(this.entity.getMotion().add(0.0D, 0.005D, 0.0D));

                if (this.entity.isChild()) {
                    this.entity.setAIMoveSpeed(Math.max(this.entity.getAIMoveSpeed() / 3.0F, 0.06F));
                }
            }
        }

        public void tick() {
            this.updateSpeed();
            if (this.action == Action.MOVE_TO && !this.entity.getNavigator().noPath()) {
                double d0 = this.posX - this.entity.getPosX();
                double d1 = this.posY - this.entity.getPosY();
                double d2 = this.posZ - this.entity.getPosZ();
                double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.entity.rotationYaw = this.limitAngle(this.entity.rotationYaw, f, 90.0F);
                this.entity.renderYawOffset = this.entity.rotationYaw;
                float f1 = (float)(this.speed * this.entity.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.entity.setAIMoveSpeed(MathHelper.lerp(0.125F, this.entity.getAIMoveSpeed(), f1));
                this.entity.setMotion(this.entity.getMotion().add(0.0D, (double)this.entity.getAIMoveSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.entity.setAIMoveSpeed(0.0F);
            }
        }
    }

    static class TravelGoal extends Goal {
        private final BluePinguinEntity turtle;
        private final double speed;
        private boolean field_203139_c;

        TravelGoal(BluePinguinEntity turtle, double speedIn) {
            this.turtle = turtle;
            this.speed = speedIn;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return this.turtle.isInWater();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.turtle.getNavigator().noPath()) {
                Vector3d vector3d = RandomPositionGenerator.findRandomTarget(turtle, 20, 5);
                if(vector3d == null) {
                    return;
                }

                Vector3d vector3d1 = RandomPositionGenerator.findRandomTargetTowardsScaled(this.turtle, 16, 3, vector3d, (float)Math.PI / 10F);
                if (vector3d1 == null) {
                    vector3d1 = RandomPositionGenerator.findRandomTargetBlockTowards(this.turtle, 8, 7, vector3d);
                }

                if (vector3d1 != null) {
                    int i = MathHelper.floor(vector3d1.x);
                    int j = MathHelper.floor(vector3d1.z);
                    if (!this.turtle.world.isAreaLoaded(i - 34, 0, j - 34, i + 34, 0, j + 34)) {
                        vector3d1 = null;
                    }
                }

                if (vector3d1 == null) {
                    this.field_203139_c = true;
                    return;
                }

                this.turtle.getNavigator().tryMoveToXYZ(vector3d1.x, vector3d1.y, vector3d1.z, this.speed);
            }

        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return !this.turtle.getNavigator().noPath() && !this.field_203139_c  && !this.turtle.isInLove() && super.shouldContinueExecuting();
        }
    }

    static class Navigator extends PathNavigator {

        public Navigator(MobEntity entitylivingIn, World worldIn) {
            super(entitylivingIn, worldIn);
        }

        protected PathFinder getPathFinder(int p_179679_1_) {
            this.nodeProcessor = new WalkAndSwimNodeProcessor();
            return new PathFinder(this.nodeProcessor, p_179679_1_);
        }

        /**
         * If on ground or swimming and can swim
         */
        protected boolean canNavigate() {
            return true;
        }

        protected Vector3d getEntityPosition() {
            return new Vector3d(this.entity.getPosX(), this.entity.getPosYHeight(0.5D), this.entity.getPosZ());
        }

        public void tick() {
            if(entity.isInWater()) {
                ++this.totalTicks;
                if (this.tryUpdatePath) {
                    this.updatePath();
                }

                if (!this.noPath()) {
                    if (this.canNavigate()) {
                        this.pathFollow();
                    } else if (this.currentPath != null && !this.currentPath.isFinished()) {
                        Vector3d vector3d = this.currentPath.getPosition(this.entity);
                        if (MathHelper.floor(this.entity.getPosX()) == MathHelper.floor(vector3d.x) && MathHelper.floor(this.entity.getPosY()) == MathHelper.floor(vector3d.y) && MathHelper.floor(this.entity.getPosZ()) == MathHelper.floor(vector3d.z)) {
                            this.currentPath.incrementPathIndex();
                        }
                    }

                    DebugPacketSender.sendPath(this.world, this.entity, this.currentPath, this.maxDistanceToWaypoint);
                    if (!this.noPath()) {
                        Vector3d vector3d1 = this.currentPath.getPosition(this.entity);
                        this.entity.getMoveHelper().setMoveTo(vector3d1.x, vector3d1.y, vector3d1.z, this.speed);
                    }
                }
            } else {
                super.tick();
            }
        }

        protected void pathFollow() {
            if (entity.isInWater()) {
                if (this.currentPath != null) {
                    Vector3d vector3d = this.getEntityPosition();
                    float f = this.entity.getWidth();
                    float f1 = f > 0.75F ? f / 2.0F : 0.75F - f / 2.0F;
                    Vector3d vector3d1 = this.entity.getMotion();
                    if (Math.abs(vector3d1.x) > 0.2D || Math.abs(vector3d1.z) > 0.2D) {
                        f1 = (float) ((double) f1 * vector3d1.length() * 6.0D);
                    }

                    int i = 6;
                    Vector3d vector3d2 = Vector3d.copyCenteredHorizontally(this.currentPath.func_242948_g());
                    if (Math.abs(this.entity.getPosX() - vector3d2.x) < (double) f1 && Math.abs(this.entity.getPosZ() - vector3d2.z) < (double) f1 && Math.abs(this.entity.getPosY() - vector3d2.y) < (double) (f1 * 2.0F)) {
                        this.currentPath.incrementPathIndex();
                    }

                    for (int j = Math.min(this.currentPath.getCurrentPathIndex() + 6, this.currentPath.getCurrentPathLength() - 1); j > this.currentPath.getCurrentPathIndex(); --j) {
                        vector3d2 = this.currentPath.getVectorFromIndex(this.entity, j);
                        if (!(vector3d2.squareDistanceTo(vector3d) > 36.0D) && this.isDirectPathBetweenPoints(vector3d, vector3d2, 0, 0, 0)) {
                            this.currentPath.setCurrentPathIndex(j);
                            break;
                        }
                    }

                    this.checkForStuck(vector3d);
                }
            } else {
                super.pathFollow();
            }
        }

        protected void checkForStuck(Vector3d positionVec3) {
            if(entity.isInWater()) {
                if (this.totalTicks - this.ticksAtLastPos > 100) {
                    if (positionVec3.squareDistanceTo(this.lastPosCheck) < 2.25D) {
                        this.clearPath();
                    }

                    this.ticksAtLastPos = this.totalTicks;
                    this.lastPosCheck = positionVec3;
                }

                if (this.currentPath != null && !this.currentPath.isFinished()) {
                    Vector3i vector3i = this.currentPath.func_242948_g();
                    if (vector3i.equals(this.timeoutCachedNode)) {
                        this.timeoutTimer += Util.milliTime() - this.lastTimeoutCheck;
                    } else {
                        this.timeoutCachedNode = vector3i;
                        double d0 = positionVec3.distanceTo(Vector3d.copyCentered(this.timeoutCachedNode));
                        this.timeoutLimit = this.entity.getAIMoveSpeed() > 0.0F ? d0 / (double) this.entity.getAIMoveSpeed() * 100.0D : 0.0D;
                    }

                    if (this.timeoutLimit > 0.0D && (double) this.timeoutTimer > this.timeoutLimit * 2.0D) {
                        this.timeoutCachedNode = Vector3i.NULL_VECTOR;
                        this.timeoutTimer = 0L;
                        this.timeoutLimit = 0.0D;
                        this.clearPath();
                    }

                    this.lastTimeoutCheck = Util.milliTime();
                }
            } else {
                super.checkForStuck(positionVec3);
            }

        }

        /**
         * Checks if the specified entity can safely walk to the specified location.
         */
        protected boolean isDirectPathBetweenPoints(Vector3d posVec31, Vector3d posVec32, int sizeX, int sizeY, int sizeZ) {

            Vector3d vector3d = new Vector3d(posVec32.x, posVec32.y + (double)this.entity.getHeight() * 0.5D, posVec32.z);
            return this.world.rayTraceBlocks(new RayTraceContext(posVec31, vector3d, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this.entity)).getType() == RayTraceResult.Type.MISS;
        }

        public boolean canEntityStandOnPos(BlockPos pos) {
            if(entity.isInWater()) return !this.world.getBlockState(pos).isOpaqueCube(this.world, pos);
            else return super.canEntityStandOnPos(pos);
        }

        public void setCanSwim(boolean canSwim) {
        }
    }

    static class Wander extends RandomWalkingGoal {
        protected final float probability;
        private BluePinguinEntity entityF;
        public Wander(BluePinguinEntity creature, double speedIn) {
            this(creature, speedIn, 0.001F);
        }

        public Wander(BluePinguinEntity creature, double speedIn, float probabilityIn) {
            super(creature, speedIn);
            this.probability = probabilityIn;
            this.entityF = creature;
        }

        @Nullable
        protected Vector3d getPosition() {
            if (this.creature.isInWaterOrBubbleColumn()) {
                Vector3d vector3d = RandomPositionGenerator.getLandPos(this.creature, 15, 7);
                return vector3d == null ? super.getPosition() : vector3d;
            } else {
                return this.creature.getRNG().nextFloat() >= this.probability ? RandomPositionGenerator.getLandPos(this.creature, 10, 7) : super.getPosition();
            }
        }

        @Override
        public boolean shouldExecute() {
            return super.shouldExecute() && !this.entityF.isSliding();
        }
    }


    public static class BarrelRollGoal extends Goal
    {
        private final CreatureEntity entity;
        private float rotationRoll;

        public BarrelRollGoal(CreatureEntity entity)
        {
            this.entity = entity;
            this.setMutexFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean shouldExecute()
        {
            int chance = 2;
            if (!this.entity.isInWater())
                return false;
            else
                return this.entity.getRNG().nextInt(chance) == 0;
        }

        @Override
        public boolean shouldContinueExecuting()
        {
            return this.entity.isInWater() && this.rotationRoll > 0.0F && !this.entity.collidedHorizontally && !this.entity.collidedVertically;
        }

        @Override
        public void startExecuting()
        {
            this.rotationRoll = 360.0F;
            this.entity.world.setEntityState(this.entity, (byte) 10);
            this.entity.getNavigator().clearPath();
        }

        @Override
        public void tick()
        {
            this.rotationRoll = Math.max(0.0F, this.rotationRoll - ROLL_SPEED);
        }

        @Override
        public void resetTask()
        {
            this.rotationRoll = 0.0F;
        }

        public float getBarrelRollRotation()
        {
            return this.rotationRoll;
        }
    }
}
