package collinvht.wild.item;

import collinvht.wild.WildRegistry;
import collinvht.wild.entity.BeetleType;
import net.minecraft.block.Blocks;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemHandler {
    private static final AirSackItem air_sack = new AirSackItem(new Item.Properties().maxStackSize(1).group(ItemGroup.MISC));
    private static final TourmalineTotem tourmaline_totem = new TourmalineTotem(new Item.Properties().maxStackSize(1).group(ItemGroup.MISC));
    private static final Item silt = new Item(new Item.Properties().group(ItemGroup.MATERIALS));

    private static final HashMap<BeetleType, BeetleItem> beetles = new HashMap<>();

    public static void init() {
        registerItem(air_sack, "air_sack");
        registerItem(tourmaline_totem, "tourmaline_totem");
        registerItem(silt, "silt");

        for (BeetleType type : BeetleType.values()) {
            BeetleItem item = new BeetleItem(new Item.Properties().group(ItemGroup.FOOD), type);
            beetles.put(type, item);
            registerItem(item, type.getBeetleName());
        }
    }

    public static BeetleItem getBeetleItem(BeetleType type) {
        return beetles.get(type);
    }

    public static void registerItem(Item item, String name) {
        WildRegistry.register(ForgeRegistries.ITEMS, name, item);
    }
}
