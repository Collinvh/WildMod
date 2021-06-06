package collinvht.old.wild.client.renders;

import collinvht.old.wild.WildMod;
import collinvht.old.wild.client.models.Nurse_Shark;
import collinvht.old.wild.entity.entities.NurseSharkEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class NurseSharkRender extends MobRenderer<NurseSharkEntity, Nurse_Shark> {
    public NurseSharkRender(EntityRendererManager rendererManager) {
        super(rendererManager, new Nurse_Shark(), 0.2F);
    }

    @Override
    public ResourceLocation getEntityTexture(NurseSharkEntity entity) {
        return new ResourceLocation(WildMod.id, "textures/entity/nurse_shark.png");
    }
}
