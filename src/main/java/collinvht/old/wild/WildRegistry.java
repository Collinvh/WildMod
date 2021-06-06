package collinvht.old.wild;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.HashMap;

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
