package collinvht.wild.client.renders;

import collinvht.wild.WildMod;
import collinvht.wild.client.models.CloudedLeopard;
import collinvht.wild.entity.entities.CloudedLeopardEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class CloudedLeopardRender extends MobRenderer<CloudedLeopardEntity, CloudedLeopard<CloudedLeopardEntity>> {
    public CloudedLeopardRender(EntityRendererManager rendererManager) {
        super(rendererManager, new CloudedLeopard<>(), 0.2F);
    }

    @Override
    public ResourceLocation getEntityTexture(CloudedLeopardEntity entity) {
        return new ResourceLocation(WildMod.id, "textures/entity/clouded_leopard.png");
    }
}
