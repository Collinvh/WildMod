package collinvht.wild.item;

import collinvht.wild.WildMod;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class AirSackItem extends Item {
    public AirSackItem(Properties properties) {
        super(properties);

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
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CompoundNBT nbt = stack.getOrCreateTag().getCompound("Air");
        double air = nbt.getDouble("Air");
        tooltip.add(new TranslationTextComponent("cur.air : " + air));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        CompoundNBT nbt = stack.getOrCreateTag();

        double air = nbt.getDouble("Air");
        if(playerIn.isInWater()) {
            if(playerIn.getAir() < playerIn.getMaxAir() && air >= 0.1D) {
                nbt.putDouble("Air", Math.max(air - 0.1D, 0.0D));
                playerIn.getCooldownTracker().setCooldown(this, 5);

                playerIn.setAir(Math.min(playerIn.getAir() + 50, playerIn.getMaxAir()));
            }
        } else if(air != 1.0) {
            nbt.putDouble("Air", 1.0D);
            playerIn.getCooldownTracker().setCooldown(this, 25);
        }

        WildMod.LOGGER.warn(air);


        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }
}
