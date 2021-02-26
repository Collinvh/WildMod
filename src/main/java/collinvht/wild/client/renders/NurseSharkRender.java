package collinvht.wild.client.renders;

import collinvht.wild.WildMod;
import collinvht.wild.client.models.NurseShark;
import collinvht.wild.entity.entities.NurseSharkEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;

public class NurseSharkRender extends LivingRenderer<NurseSharkEntity, NurseShark> {
    public NurseSharkRender(EntityRendererManager rendererManager) {
        super(rendererManager, new NurseShark(), 0.2F);
    }

    @Override
    public ResourceLocation getEntityTexture(NurseSharkEntity entity) {
        return new ResourceLocation(WildMod.id, "textures/entity/nurse_shark.png");
    }

    @Override
    protected boolean canRenderName(NurseSharkEntity entity) {
        return false;
    }
}
