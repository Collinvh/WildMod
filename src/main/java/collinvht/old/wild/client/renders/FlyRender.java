package collinvht.old.wild.client.renders;

import collinvht.old.wild.WildMod;
import collinvht.old.wild.client.models.fly;
import collinvht.old.wild.entity.entities.FlyEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class FlyRender extends MobRenderer<FlyEntity, fly> {
    public FlyRender(EntityRendererManager renderManager) {
        super(renderManager, new fly(), 0);
    }

    @Override
    public ResourceLocation getEntityTexture(FlyEntity entity) {
        return new ResourceLocation(WildMod.id, "textures/entity/fly.png");
    }

    @Override
    protected void preRenderCallback(FlyEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
    }
}
