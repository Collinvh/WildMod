package collinvht.wild.block;

import collinvht.wild.WildMod;
import collinvht.wild.item.ItemHandler;
import collinvht.wild.WildRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockHandler {
    public static final CompactorBlock COMPACTOR = new CompactorBlock(AbstractBlock.Properties.from(Blocks.STONE).sound(SoundType.WOOD));

    public static void init() {
        registerBlock(COMPACTOR, "compactor", new Item.Properties().group(ItemGroup.DECORATIONS));
    }


    public static void registerBlock(Block block, String name) {
        registerBlock(block, name, new Item.Properties());
    }

    public static void registerBlock(Block block, String name, Item.Properties properties) {
        WildRegistry.register(ForgeRegistries.BLOCKS, name, block);
        ItemHandler.registerItem(new BlockItem(block, properties), name);
    }
}
