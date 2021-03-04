package collinvht.wild.item;

import collinvht.wild.WildMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemHandler {
    private static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, WildMod.id);
    private static final AirSackItem air_sack = new AirSackItem(new Item.Properties().maxStackSize(1).group(ItemGroup.MISC));
    private static final TourmalineTotem tourmaline_totem = new TourmalineTotem(new Item.Properties().maxStackSize(1).group(ItemGroup.MISC));
    private static final Item silt = new Item(new Item.Properties().group(ItemGroup.MATERIALS));

    public static void init() {
        registerItem(air_sack, "air_sack");
        registerItem(tourmaline_totem, "tourmaline_totem");
        registerItem(silt, "silt");    }

    public static void registerItem(Item item, String name) {
        items.register(name, () -> item);
    }

    static {
        items.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
