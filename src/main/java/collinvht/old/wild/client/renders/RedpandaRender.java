package collinvht.old.wild.client.renders;

import collinvht.old.wild.WildMod;
import collinvht.old.wild.client.models.Red_Panda;
import collinvht.old.wild.entity.entities.RedPandaEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RedpandaRender extends MobRenderer<RedPandaEntity, Red_Panda> {
    public RedpandaRender(EntityRendererManager rendererManager) {
        super(rendererManager, new Red_Panda(), 0.2F);
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
