package collinvht.old.wild.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import java.util.Collection;

public class TourmalineTotem extends Item {
    public TourmalineTotem(Properties properties) {
        super(properties.defaultMaxDamage(100));
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!worldIn.isRemote) {
            if (entityIn instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) entityIn;
                if (player.getHeldItemOffhand().getItem() == this || player.getHeldItemMainhand().getItem() == this) {
                    if (!player.getCooldownTracker().hasCooldown(this)) {
                        Collection<EffectInstance> list = player.getActivePotionEffects();

                        if (!list.isEmpty()) {
                            try {
                                for (EffectInstance effectInstance : list) {
                                    if (stack.getDamage() < 100) {
                                        if (!effectInstance.getPotion().isBeneficial() && effectInstance.getPotion() != Effects.BAD_OMEN) {
                                            player.removePotionEffect(effectInstance.getPotion());
                                            stack.setDamage(stack.getDamage() + 2);
                                            player.getCooldownTracker().setCooldown(this, 100);
                                            break;
                                        }
                                    } else {
                                        stack.shrink(1);
                                    }
                                }
                            } catch (Exception ignored) {
                            }
                        }
                    }
                }
            }
        }
    }
}
