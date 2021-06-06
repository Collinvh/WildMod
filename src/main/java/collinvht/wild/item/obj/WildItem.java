package collinvht.wild.item.obj;

import collinvht.old.wild.WildRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class WildItem extends Item {
    public WildItem(String name, Properties properties) {
        super(properties);

        WildRegistry.register(ForgeRegistries.ITEMS, name, this);
    }
}
