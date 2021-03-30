package collinvht.wild.client.renders;

import collinvht.wild.WildMod;
import collinvht.wild.client.models.BeetleModel;
import collinvht.wild.client.models.fly;
import collinvht.wild.entity.BeetleType;
import collinvht.wild.entity.entities.BeetleEntity;
import collinvht.wild.entity.entities.FlyEntity;
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
