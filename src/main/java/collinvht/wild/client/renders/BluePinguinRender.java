package collinvht.wild.client.renders;

import collinvht.wild.WildMod;
import collinvht.wild.client.models.Blue_Pinguin;
import collinvht.wild.entity.entities.BluePinguinEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;

public class BluePinguinRender extends LivingRenderer<BluePinguinEntity, Blue_Pinguin> {
    public BluePinguinRender(EntityRendererManager rendererManager) {
        super(rendererManager, new Blue_Pinguin(), 0.2F);
    }

    @Override
    public ResourceLocation getEntityTexture(BluePinguinEntity entity) {
        return new ResourceLocation(WildMod.id, "textures/entity/blue_pinguin.png");
    }

    @Override
    protected boolean canRenderName(BluePinguinEntity entity) {
        return false;
    }
}
