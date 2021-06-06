package collinvht.old.wild.item;

import collinvht.old.wild.entity.BeetleType;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class BeetleItem extends Item {
    public BeetleItem(Properties properties, BeetleType type) {
        super(properties.food((new Food.Builder().effect(type.getInstance(), 0.9F).build())));
    }


}
