package collinvht.old.wild.client.renders;

import collinvht.old.wild.WildMod;
import collinvht.old.wild.client.models.Eland;
import collinvht.old.wild.entity.entities.ElandEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class ElandRender extends MobRenderer<ElandEntity, Eland<ElandEntity>> {
    public ElandRender(EntityRendererManager rendererManager) {
        super(rendererManager, new Eland<>(), 0.2F);
    }

    @Override
    public ResourceLocation getEntityTexture(ElandEntity entity) {
        return new ResourceLocation(WildMod.id, "textures/entity/eland.png");
    }
}
