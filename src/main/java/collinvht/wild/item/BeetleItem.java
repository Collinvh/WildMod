package collinvht.wild.item;

import collinvht.wild.entity.BeetleType;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public class BeetleItem extends Item {
    public BeetleItem(Properties properties, BeetleType type) {
        super(properties.food((new Food.Builder().effect(type.getInstance(), 0.9F).build())));
    }


}
