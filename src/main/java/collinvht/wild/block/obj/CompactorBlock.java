package collinvht.wild.block.obj;

import collinvht.old.wild.client.gui.container.CompactorContainer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class CompactorBlock extends WildBlock {
    private static final ITextComponent CONTAINER_NAME = new TranslationTextComponent("container.compactor");

    public CompactorBlock() {
        super("compactor", AbstractBlock.Properties.from(Blocks.CRAFTING_TABLE), new Item.Properties().group(ItemGroup.MISC));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return worldIn.isRemote ? ActionResultType.SUCCESS : openContainer(player, state, worldIn, pos);
    }

    public ActionResultType openContainer(PlayerEntity player, BlockState state, World worldIn, BlockPos pos) {
        player.openContainer(state.getContainer(worldIn, pos));
        return ActionResultType.CONSUME;
    }

    @Nullable
    public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
        return new SimpleNamedContainerProvider((id, inventory, player) -> new CompactorContainer(id, inventory, IWorldPosCallable.of(worldIn, pos)), CONTAINER_NAME);
    }
}
