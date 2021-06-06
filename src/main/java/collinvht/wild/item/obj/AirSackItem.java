package collinvht.wild.item.obj;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class AirSackItem extends WildItem {
    public AirSackItem() {
        super("air_sack", new Item.Properties().maxStackSize(1).group(ItemGroup.MISC));
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        return stack;
    }
}
