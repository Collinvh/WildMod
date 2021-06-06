package collinvht.old.wild.client.gui;

import collinvht.old.wild.WildMod;
import collinvht.old.wild.client.gui.container.CompactorContainer;
import collinvht.old.wild.client.gui.recipe.CompactorRecipe;
import collinvht.old.wild.client.gui.screen.CompactorScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = WildMod.id, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Guis {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, WildMod.id);

    public static RegistryObject<IRecipeSerializer<CompactorRecipe>> COMPACTOR_SERIALIZER = RECIPE_SERIALIZERS.register("compactor", CompactorRecipe.Serializer::new);
    public static final IRecipeType<CompactorRecipe> COMPACTOR_RECIPE = registerRecipeType("compactor");
    public static final ContainerType<CompactorContainer> COMPACTOR = new ContainerType<>(CompactorContainer::new);

    @SubscribeEvent
    public static void registerContainerTypes(RegistryEvent.Register<ContainerType<?>> event) {
        final IForgeRegistry<ContainerType<?>> registry = event.getRegistry();

        registry.register(COMPACTOR.setRegistryName(WildMod.id, "compactor"));
    }

    @SubscribeEvent
    public static void register(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(COMPACTOR, CompactorScreen::new);
    }

    private static <T extends IRecipe<?>> IRecipeType<T> registerRecipeType(String name) {
        return IRecipeType.register(WildMod.id + ":" + name);
    }
}
