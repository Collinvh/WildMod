package collinvht.wild;

import collinvht.wild.WildMod;
import it.unimi.dsi.fastutil.Hash;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;

public class WildRegistry {
    private static final HashMap<IForgeRegistry<?>, DeferredRegister<?>> registers = new HashMap<>();

    public static <I extends IForgeRegistryEntry<I>> void register(IForgeRegistry<I> registry, String name, I obj) {
        DeferredRegister<I> register = getRegistry(registry, name);
        try {
            register.register(name, () -> obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static <I extends IForgeRegistryEntry<I>> DeferredRegister<I> getRegistry(IForgeRegistry<I> registry, String name) {
        DeferredRegister<I> register = (DeferredRegister<I>) registers.get(registry);
        if (register == null) {
            register = DeferredRegister.create(registry, WildMod.id);
            registers.put(registry, register);
            register.register(FMLJavaModLoadingContext.get().getModEventBus());

        }
        return register;
    }
}
