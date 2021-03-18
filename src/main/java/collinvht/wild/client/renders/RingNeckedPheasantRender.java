package collinvht.wild.client.renders;

import collinvht.wild.WildMod;
import collinvht.wild.client.models.Ring_necked_Pheasant;
import collinvht.wild.entity.entities.RingNeckedPheasantEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RingNeckedPheasantRender extends MobRenderer<RingNeckedPheasantEntity, Ring_necked_Pheasant<RingNeckedPheasantEntity>> {
    public RingNeckedPheasantRender(EntityRendererManager rendererManager) {
        super(rendererManager, new Ring_necked_Pheasant<>(), 0.2F);
    }

    @Override
    public ResourceLocation getEntityTexture(RingNeckedPheasantEntity entity) {
        return new ResourceLocation(WildMod.id, "textures/entity/rn_pheasant.png");
    }
}
