package collinvht.old.wild.client.gui.recipe;

import collinvht.old.wild.block.BlockHandler;
import collinvht.old.wild.client.gui.Guis;
import com.google.gson.JsonObject;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class CompactorRecipe extends NewSingleItemRecipe {
    public CompactorRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result) {
        super(Guis.COMPACTOR_RECIPE, Guis.COMPACTOR_SERIALIZER.get(), id, group, ingredient, result);
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(IInventory inv, World worldIn) {
        return this.ingredient.test(inv.getStackInSlot(0));
    }

    @Override
    public IRecipeType<?> getType() {
        return Guis.COMPACTOR_RECIPE;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return Guis.COMPACTOR_SERIALIZER.get();
    }

    public ItemStack getIcon() {
        return new ItemStack(BlockHandler.COMPACTOR);
    }


    public static class Serializer implements IRecipeSerializer<CompactorRecipe> {
        private ResourceLocation registryName;

        @Nullable
        @Override
        public CompactorRecipe read(ResourceLocation recipeId, JsonObject json) {
            String s = JSONUtils.getString(json, "group", "");

            int count = JSONUtils.getInt(json, "count");

            Ingredient ingredient;
            if (JSONUtils.isJsonArray(json, "ingredient")) {
                ingredient = IngredientUtils.deserialize(JSONUtils.getJsonArray(json, "ingredient"));
            } else {
                ingredient = IngredientUtils.deserialize(JSONUtils.getJsonObject(json, "ingredient"));
            }

            String s1 = JSONUtils.getString(json, "result");
            ItemStack itemstack = new ItemStack(Registry.ITEM.getOrDefault(new ResourceLocation(s1)), count);

            return new CompactorRecipe(recipeId, s, ingredient, itemstack);
        }

        @Nullable
        @Override
        public CompactorRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            String s = buffer.readString(32767);
            Ingredient ingredient = Ingredient.read(buffer);
            ItemStack itemstack = buffer.readItemStack();
            return new CompactorRecipe(recipeId, s, ingredient, itemstack);
        }

        @Override
        public void write(PacketBuffer buffer, CompactorRecipe recipe) {
            buffer.writeString(recipe.group);
            recipe.ingredient.write(buffer);
            buffer.writeItemStack(recipe.result);
        }

        @Override
        public IRecipeSerializer<?> setRegistryName(ResourceLocation name) {
            this.registryName = name;
            return Serializer.this;
        }

        private static <G> Class<G> castClass(Class<?> cls)
        {
            return (Class<G>)cls;
        }

        @Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return registryName;
        }

        @Override
        public Class<IRecipeSerializer<?>> getRegistryType() {
            return CompactorRecipe.Serializer.castClass(IRecipeSerializer.class);
        }
    }
}
