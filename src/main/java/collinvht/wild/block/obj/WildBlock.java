package collinvht.wild.block.obj;

import collinvht.wild.WildMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class WildBlock extends Block {
    public WildBlock(String name, Properties properties) {
        this(name, properties, new Item.Properties());
    }

    public WildBlock(String name, Properties properties, Item.Properties itemProperties) {
        super(properties);

        WildMod.register(ForgeRegistries.BLOCKS, name, this);
        WildMod.register(ForgeRegistries.ITEMS, name, new BlockItem(this, itemProperties));
    }
}
