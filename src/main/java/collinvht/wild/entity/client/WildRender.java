package collinvht.wild.entity.client;

import collinvht.wild.WildMod;
import collinvht.wild.entity.obj.builder.WildEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

public class WildRender extends MobRenderer {
    private final WildEntity<?> entity;

    public WildRender(EntityRendererManager renderManager, WildEntity<?> entity, AdvancedEntityModel model) {
        super(renderManager, model, entity.getBuilder().getShadowSize());

        this.entity = entity;
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return  new ResourceLocation(WildMod.MODID, "textures/entities/" + this.entity.getName() + "/" + this.entity.getName() + ".png");
    }

    @Override
    protected void preRenderCallback(LivingEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
        matrixStackIn.scale(entity.getBuilder().getScale()[0], entity.getBuilder().getScale()[1],entity.getBuilder().getScale()[2]);
    }
}
