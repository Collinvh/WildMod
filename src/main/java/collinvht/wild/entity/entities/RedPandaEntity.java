package collinvht.wild.entity.entities;

import collinvht.wild.entity.EntityHandler;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PandaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class RedPandaEntity extends AnimalEntity {
    public RedPandaEntity(EntityType<? extends RedPandaEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute func_234204_eW_() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4F).createMutableAttribute(Attributes.ATTACK_DAMAGE, 6.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1D));
        this.goalSelector.addGoal(0, new FollowParentGoal(this, 0.4D));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 0.4D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 0.5, Ingredient.fromItems(Items.BAMBOO), true));
        this.goalSelector.addGoal(4, new FleeSunGoal(this, 0.5D));
        this.goalSelector.addGoal(6, new AvoidEntityGoal<>(this, MonsterEntity.class, 12.0F, 0.8D, 1D));
        this.goalSelector.addGoal(6, new AvoidEntityGoal<>(this, PandaEntity.class, 14.0F, 0.8D, 1D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, AnimalEntity.class, 10));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 10));
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        return EntityHandler.red_panda.create(p_241840_1_);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == Items.BAMBOO.getItem();
    }
}
