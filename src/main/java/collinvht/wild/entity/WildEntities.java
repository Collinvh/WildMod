package collinvht.wild.entity;

import collinvht.wild.entity.client.model.BluePinguinModel;
import collinvht.wild.entity.client.model.WhaleSharkModel;
import collinvht.wild.entity.obj.BluePinguinEntity;
import collinvht.wild.entity.obj.WhaleSharkEntity;
import collinvht.wild.entity.obj.builder.WildBuilder;
import collinvht.wild.entity.obj.builder.WildEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;

import java.util.HashMap;

public enum WildEntities {
    WHALE_SHARK(WhaleSharkEntity.getAttributes(), new WhaleSharkModel<>(), new WildEntity<>("whale_shark", WhaleSharkEntity::new, new WildBuilder(EntityClassification.WATER_AMBIENT).scale(1.5F, 1.5F, 1.5F))),
    BLUE_PINGUIN(BluePinguinEntity.getAttributes(), new BluePinguinModel<>(), new WildEntity<>("blue_pinguin", BluePinguinEntity::new, new WildBuilder(EntityClassification.CREATURE)));

    private final WildEntity<?> entity;
    private final HashMap<Attribute, Double> mutableAttribute;
    private final AdvancedEntityModel<?> model;

    WildEntities(HashMap<Attribute, Double> mutableAttribute, AdvancedEntityModel<?> model, WildEntity<?> entity) {
        this.entity = entity;
        this.mutableAttribute = mutableAttribute;
        this.model = model;
    }

    public AdvancedEntityModel<?> getModel() {
        return model;
    }

    public WildEntity<?> getEntity() {
        return entity;
    }

    public HashMap<Attribute, Double> getMutableAttribute() {
        return mutableAttribute;
    }

    public static void init() {

    }
}
