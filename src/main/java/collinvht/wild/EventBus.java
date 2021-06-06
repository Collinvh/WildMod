package collinvht.wild;

import collinvht.wild.entity.WildEntities;
import collinvht.wild.entity.client.WildRender;
import collinvht.wild.entity.obj.builder.WildEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.world.TrackedEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.HashMap;

@Mod.EventBusSubscriber
public class EventBus {

    public EventBus() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }

    @SubscribeEvent
    public void setup(final FMLCommonSetupEvent event) {
        for (WildEntities ventity : WildEntities.values()) {
            WildEntity<?> entity = ventity.getEntity();
            HashMap<Attribute, Double> attributes = ventity.getMutableAttribute();
            AttributeModifierMap.MutableAttribute mutableAttribute = MobEntity.func_233666_p_();

            attributes.forEach(mutableAttribute::createMutableAttribute);

            WildMod.LOGGER.info(entity.getName() + " || has been registered.");

            GlobalEntityTypeAttributes.put((EntityType<? extends LivingEntity>) entity.getEntityType(), mutableAttribute.create());
        }
    }


    @SubscribeEvent
    public void clientSetup(final FMLClientSetupEvent event) {
        for(WildEntities ventity : WildEntities.values()) {
            WildEntity<?> entity = ventity.getEntity();

            WildMod.LOGGER.info(entity.getName() + " || client registered.");

            RenderingRegistry.registerEntityRenderingHandler(entity.getEntityType(), (rendererManager) -> new WildRender(rendererManager, entity, ventity.getModel()));
        }
    }
}
