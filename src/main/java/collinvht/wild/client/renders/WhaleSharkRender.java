package collinvht.wild.client.renders;

import collinvht.wild.WildMod;
import collinvht.wild.client.models.WhaleShark;
import collinvht.wild.entity.entities.WhaleSharkEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;

public class WhaleSharkRender extends LivingRenderer<WhaleSharkEntity, WhaleShark<WhaleSharkEntity>> {
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

    @Override
    protected boolean canRenderName(WhaleSharkEntity entity) {
        return false;
    }
}
