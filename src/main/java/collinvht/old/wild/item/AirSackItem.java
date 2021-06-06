package collinvht.old.wild.item;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AirSackItem extends Item {
    public AirSackItem(Properties properties) {
        super(properties.defaultMaxDamage(10));
    }


    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        CompoundNBT nbt = new CompoundNBT();
        nbt.putDouble("Air", 0.0D);
        stack.getOrCreateTag().put("Air", nbt);
        return stack;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        CompoundNBT nbt = stack.getOrCreateTag();

        double air = nbt.getDouble("Air");
        if(playerIn.isInWater()) {
            if(playerIn.getAir() < playerIn.getMaxAir() && air >= 0.1D) {
                nbt.putDouble("Air", Math.max(air - 0.3D, 0.0D));

                for(int i = 0; i<random.nextInt(40); i++)
                    playerIn.getEntityWorld().addParticle(ParticleTypes.BUBBLE_COLUMN_UP,false, playerIn.getPosXRandom(0.4), (playerIn.getPosYHeight(0.4) )- 0.5D, playerIn.getPosZRandom(0.5D),  0.5, 2, 0.5);
                playerIn.getCooldownTracker().setCooldown(this, 5);

                playerIn.setAir(Math.min(playerIn.getAir() + 50, playerIn.getMaxAir()));
            }
        }
        if (air != 1.0) {
            if (playerIn.getEntityWorld().getBlockState(new BlockPos(playerIn.getPosition().getX(), playerIn.getPosition().getY() + 1, playerIn.getPosition().getZ())).getBlock().matchesBlock(Blocks.AIR) || playerIn.world.getBlockState(playerIn.getPosition()).isIn(Blocks.BUBBLE_COLUMN)) {
                nbt.putDouble("Air", Math.min(air + 0.3D, 1.0D));
                playerIn.getCooldownTracker().setCooldown(this, 25);
            }
        }

        if(air > 1) {
            nbt.putDouble("Air", 1.0D);
        }
        air = nbt.getDouble("Air");

        if(air < 0.7 && air > 0.2) {
            air = 0.4;
        } else if(air < 0.2) {
            air = 0.1;
        }

        updateDamage(stack, air, 1, 0);
        updateDamage(stack, air, 0.7, 3);
        updateDamage(stack, air, 0.4, 6);
        updateDamage(stack, air, 0.1, 10);

        if(air == 0.1 || air == 1) {
            return ActionResult.resultPass(stack);
        }

        return ActionResult.resultSuccess(stack);
    }

    void updateDamage(ItemStack stack, double air, double targetAir, int damage) {
        if(air == targetAir) {
            stack.setDamage(damage);
        }
    }
}