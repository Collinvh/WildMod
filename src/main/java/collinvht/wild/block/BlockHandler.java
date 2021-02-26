package collinvht.wild.block;

import collinvht.wild.WildMod;
import collinvht.wild.item.ItemHandler;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockHandler {
    private static final DeferredRegister<Block> blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, WildMod.id);

    public static void init() {
        registerBlock(new Block(AbstractBlock.Properties.from(Blocks.POLISHED_DIORITE)), "test");
    }


    public static void registerBlock(Block block, String name) {
        registerBlock(block, name, new Item.Properties());
    }

    public static void registerBlock(Block block, String name, Item.Properties properties) {
        blocks.register(name, () -> block);
        ItemHandler.registerItem(new BlockItem(block, properties), name);
    }

    static {
        blocks.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
