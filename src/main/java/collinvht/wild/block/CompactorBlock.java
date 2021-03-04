package collinvht.wild.block;

import collinvht.wild.client.gui.container.CompactorContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class CompactorBlock extends Block {
    private static final ITextComponent CONTAINER_NAME = new TranslationTextComponent("container.compactor");

    public CompactorBlock(Properties properties) {
        super(properties);
    }


    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
//        if(worldIn.isRemote) {
//            return ActionResultType.SUCCESS;
//        } else {
//            ItemStack stack = player.getHeldItem(handIn);
//            addPricePool(player,stack, Items.SAND, Items.SANDSTONE, 2, 1);
//            return ActionResultType.CONSUME;
//        }
        if (worldIn.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            player.openContainer(state.getContainer(worldIn, pos));
            return ActionResultType.CONSUME;
        }
    }

    @Nullable
    public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
        return new SimpleNamedContainerProvider((id, inventory, player) -> {
            return new CompactorContainer(id, inventory, IWorldPosCallable.of(worldIn, pos));
        }, CONTAINER_NAME);
    }

    private void addPricePool(PlayerEntity player, ItemStack handItem, Item requiredItem, Item returnedItem, int cost, int resultAmount) {
        if(handItem.getItem() == requiredItem) {
            if(handItem.getCount() >= cost) {
                handItem.shrink(cost);
                ItemStack stack = new ItemStack(returnedItem);
                if(player.isSneaking()) {
                    stack.setCount(resultAmount * Math.floorDiv(handItem.getCount(), cost));
                } else {
                    stack.setCount(resultAmount);
                }
                player.addItemStackToInventory(stack);
            }
        }
    }
}
