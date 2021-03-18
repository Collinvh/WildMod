package collinvht.wild.client.renders;

import collinvht.wild.WildMod;
import collinvht.wild.client.models.BeetleModel;
import collinvht.wild.entity.BeetleType;
import collinvht.wild.entity.entities.BeetleEntity;
import collinvht.wild.entity.entities.WhaleSharkEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.util.ResourceLocation;

public class BeetleRender extends MobRenderer<BeetleEntity, BeetleModel> {
    public BeetleRender(EntityRendererManager renderManager) {
        super(renderManager, new BeetleModel(), 0);
    }

    @Override
    public ResourceLocation getEntityTexture(BeetleEntity entity) {
        BeetleType type = entity.getBeetleType();
        String baseString = "textures/entity/beetle/";
        return new ResourceLocation(WildMod.id, baseString + type.getBeetleName() + ".png");
    }

    @Override
    protected void preRenderCallback(BeetleEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
        matrixStackIn.scale(0.3F, 0.3F,0.3F);
    }
}
