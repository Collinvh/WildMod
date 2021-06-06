package collinvht.old.wild.client.renders;

import collinvht.old.wild.WildMod;
import collinvht.old.wild.client.models.WhaleShark;
import collinvht.old.wild.entity.entities.WhaleSharkEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class WhaleSharkRender extends MobRenderer<WhaleSharkEntity, WhaleShark<WhaleSharkEntity>> {
    public WhaleSharkRender(EntityRendererManager rendererManager) {
        super(rendererManager, new WhaleShark<>(), 0.2F);
    }

    @Override
    public ResourceLocation getEntityTexture(WhaleSharkEntity entity) {
        return new ResourceLocation(WildMod.id, "textures/entity/whale_shark.png");
    }

    @Override
    protected void preRenderCallback(WhaleSharkEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
        matrixStackIn.translate(0, 1.85F, 0);
        matrixStackIn.scale(1.5F, 1.5F,1.5F);
    }
}
