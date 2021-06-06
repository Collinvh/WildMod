package collinvht.wild;

import collinvht.wild.block.WildBlocks;
import collinvht.wild.entity.WildEntities;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

@Mod("wild")
public class WildMod {
    private static final HashMap<IForgeRegistry<?>, DeferredRegister<?>> registers = new HashMap<>();

    public static final String MODID = "wild";


    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public WildMod() {
        MinecraftForge.EVENT_BUS.register(new EventBus());
        WildEntities.init();
        WildBlocks.init();
    }

    public static <I extends IForgeRegistryEntry<I>> void register(IForgeRegistry<I> registry, String name, I obj) {
        DeferredRegister<I> register = getRegistry(registry);
        try {
            register.register(name, () -> obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static <I extends IForgeRegistryEntry<I>> DeferredRegister<I> getRegistry(IForgeRegistry<I> registry) {
        DeferredRegister<I> register = (DeferredRegister<I>) registers.get(registry);
        if (register == null) {
            register = DeferredRegister.create(registry, MODID);
            registers.put(registry, register);
            register.register(FMLJavaModLoadingContext.get().getModEventBus());
        }
        return register;
    }
}
