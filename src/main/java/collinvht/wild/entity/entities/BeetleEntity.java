package collinvht.wild.entity.entities;

import collinvht.wild.WildMod;
import collinvht.wild.entity.BeetleType;
import collinvht.wild.item.ItemHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.ClimberPathNavigator;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Attr;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class BeetleEntity extends CreatureEntity {
    private static final DataParameter<Byte> CLIMBING = EntityDataManager.createKey(SpiderEntity.class, DataSerializers.BYTE);
    private static final DataParameter<Integer> BEETLE_TYPE_ID = EntityDataManager.createKey(AgeableEntity.class, DataSerializers.VARINT);
    public BeetleEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        this.stepHeight = 1.1F;

        this.navigator = new ClimberPathNavigator(this, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute createAttributeMap() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.1F).createMutableAttribute(Attributes.MAX_HEALTH, 5).createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new WaterAvoidingRandomWalkingGoal(this, 1));
        this.goalSelector.addGoal(0, new PanicGoal(this, 2.3));
    }

    @Override
    protected void registerData() {
        this.dataManager.register(BEETLE_TYPE_ID, 0);
        this.dataManager.register(CLIMBING, (byte)0);
        super.registerData();
    }

    @Override
    public void onDeath(DamageSource cause) {
        if(!world.isRemote) {
            world.addEntity(new ItemEntity(world, this.lastTickPosX, this.lastTickPosY, this.lastTickPosZ, new ItemStack(ItemHandler.getBeetleItem(this.getBeetleType()))));
        }
        super.onDeath(cause);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.world.isRemote) {
            this.setBesideClimbableBlock(this.collidedHorizontally);
        }
    }

    public void setBesideClimbableBlock(boolean climbing) {
        byte b0 = this.dataManager.get(CLIMBING);
        if (climbing) {
            b0 = (byte)(b0 | 1);
        } else {
            b0 = (byte)(b0 & -2);
        }

        this.dataManager.set(CLIMBING, b0);
    }


    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("beetle_type", this.dataManager.get(BEETLE_TYPE_ID));
    }

    public boolean isOnLadder() {
        return this.isBesideClimbableBlock();
    }

    public boolean isBesideClimbableBlock() {
        return (this.dataManager.get(CLIMBING) & 1) != 0;
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setBeetleTypeId(compound.getInt("beetle_type"));
    }

    public void setBeetleTypeId(int id) {
        this.dataManager.set(BEETLE_TYPE_ID, id);
    }

    public static boolean func_223317_c(EntityType<BeetleEntity> parrotIn, IWorld worldIn, SpawnReason reason, BlockPos p_223317_3_, Random random) {
        BlockState blockstate = worldIn.getBlockState(p_223317_3_.down());
        return (blockstate.isIn(BlockTags.LEAVES) || blockstate.isIn(Blocks.GRASS_BLOCK) || blockstate.isIn(BlockTags.LOGS));
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        ILivingEntityData data = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.setBeetleTypeId(BeetleType.getRandomBeetle().getBeetleID());
        return data;
    }

    public BeetleType getBeetleType() {
        return BeetleType.getBeetleTypeFromID(this.dataManager.get(BEETLE_TYPE_ID));
    }
}

