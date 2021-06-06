package collinvht.old.wild;

import collinvht.old.wild.block.BlockHandler;
import collinvht.old.wild.client.gui.Guis;
import collinvht.old.wild.entity.EntityHandler;
import collinvht.old.wild.entity.entities.*;
import collinvht.old.wild.item.ItemHandler;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
public class WildMod
{
    public static final String id = "wild";

    public static final Logger LOGGER = LogManager.getLogger();

    public WildMod() {
        BlockHandler.init();
        ItemHandler.init();
        EntityHandler.init();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        Guis.RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


    private void setup(final FMLCommonSetupEvent event) {



        GlobalEntityTypeAttributes.put(EntityHandler.red_panda, RedPandaEntity.func_234204_eW_().create());
        GlobalEntityTypeAttributes.put(EntityHandler.blue_pinguin, BluePinguinEntity.func_234204_eW_().create());
        GlobalEntityTypeAttributes.put(EntityHandler.nurse_shark, NurseSharkEntity.func_234204_eW_().create());
        GlobalEntityTypeAttributes.put(EntityHandler.whale_shark, WhaleSharkEntity.func_234204_eW_().create());
        GlobalEntityTypeAttributes.put(EntityHandler.eland, ElandEntity.createAttributeMap().create());
        GlobalEntityTypeAttributes.put(EntityHandler.cleopard, CloudedLeopardEntity.createAttributeMap().create());
        GlobalEntityTypeAttributes.put(EntityHandler.ringneckedpheasant, RingNeckedPheasantEntity.createAttributeMap().create());
        GlobalEntityTypeAttributes.put(EntityHandler.beetleEntity, BeetleEntity.createAttributeMap().create());
        GlobalEntityTypeAttributes.put(EntityHandler.flyEntity, FlyEntity.createAttributeMap().create());
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        EntityHandler.clientInit();
    }
}
