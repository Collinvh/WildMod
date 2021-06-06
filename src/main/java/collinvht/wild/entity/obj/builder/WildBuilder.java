package collinvht.wild.entity.obj.builder;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySize;

public class WildBuilder {
    private ImmutableSet<Block> field_233603_c_ = ImmutableSet.of();
    private boolean serializable = true;
    private boolean summonable = true;
    private boolean immuneToFire;
    private boolean field_225436_f;
    private int trackingRange = 5;
    private int field_233605_i_ = 3;
    private float shadowSize = 0.5F;
    private EntitySize size = EntitySize.flexible(0.6F, 1.8F);

    private final EntityClassification classification;
    private final float[] scale = new float[3];

    public WildBuilder(EntityClassification classificationIn) {
        this.classification = classificationIn;
        scale(1,1,1);
    }

    public WildBuilder disableSerialization() {
        this.serializable = false;
        return this;
    }

    public WildBuilder immuneToFire() {
        this.immuneToFire = true;
        return this;
    }

    public WildBuilder func_233607_a_(Block... p_233607_1_) {
        this.field_233603_c_ = ImmutableSet.copyOf(p_233607_1_);
        return this;
    }

    public WildBuilder func_225435_d() {
        this.field_225436_f = true;
        return this;
    }

    public WildBuilder disableSummoning() {
        this.summonable = false;
        return this;
    }

    public WildBuilder trackingRange(int range) {
        this.trackingRange = range;
        return this;
    }

    public WildBuilder func_233608_b_(int p_233608_1_) {
        this.field_233605_i_ = p_233608_1_;
        return this;
    }

    public WildBuilder size(float width, float height) {
        this.size = EntitySize.flexible(width, height);
        return this;
    }

    public WildBuilder scale(float x, float y, float z) {
        this.scale[0] = x;
        this.scale[1] = y;
        this.scale[2] = z;
        return this;
    }

    public WildBuilder shadowSize(float size) {
        this.shadowSize = size;
        return this;
    }

    public float getShadowSize() {
        return shadowSize;
    }

    public float[] getScale() {
        return scale;
    }

    public EntityClassification getClassification() {
        return classification;
    }

    public EntitySize getSize() {
        return size;
    }

    public ImmutableSet<Block> getField_233603_c_() {
        return field_233603_c_;
    }

    public int getField_233605_i_() {
        return field_233605_i_;
    }

    public int getTrackingRange() {
        return trackingRange;
    }

    public boolean isField_225436_f() {
        return field_225436_f;
    }

    public boolean isImmuneToFire() {
        return immuneToFire;
    }

    public boolean isSerializable() {
        return serializable;
    }

    public boolean isSummonable() {
        return summonable;
    }
}
