package collinvht.wild.client.renders;

import collinvht.wild.WildMod;
import collinvht.wild.client.models.Redpanda;
import collinvht.wild.entity.entities.RedPandaEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;

public class RedpandaRender extends LivingRenderer<RedPandaEntity, Redpanda> {
    public RedpandaRender(EntityRendererManager rendererManager) {
        super(rendererManager, new Redpanda(), 0.2F);
    }

    @Override
    public ResourceLocation getEntityTexture(RedPandaEntity entity) {
        return new ResourceLocation(WildMod.id, "textures/entity/red_panda.png");
    }

    @Override
    protected boolean canRenderName(RedPandaEntity entity) {
        return false;
    }
}
