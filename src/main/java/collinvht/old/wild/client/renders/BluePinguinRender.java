package collinvht.old.wild.client.renders;

import collinvht.old.wild.WildMod;
import collinvht.old.wild.client.models.Blue_Pinguin;
import collinvht.old.wild.entity.entities.BluePinguinEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BluePinguinRender extends MobRenderer<BluePinguinEntity, Blue_Pinguin> {
    public BluePinguinRender(EntityRendererManager rendererManager) {
        super(rendererManager, new Blue_Pinguin(), 0.2F);
    }

    @Override
    public ResourceLocation getEntityTexture(BluePinguinEntity entity) {
        return new ResourceLocation(WildMod.id, "textures/entity/blue_pinguin.png");
    }

}
