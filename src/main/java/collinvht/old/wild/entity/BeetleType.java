package collinvht.old.wild.entity;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.Random;

public enum BeetleType {
    FLEA(0, "flea", new EffectInstance(Effects.JUMP_BOOST, 300, 1)),
    TIGER(1, "tiger", new EffectInstance(Effects.SPEED, 150, 2)),
    STAG(2, "stag", new EffectInstance(Effects.STRENGTH, 150, 2)),
    TITAN(3, "titan", new EffectInstance(Effects.REGENERATION, 300, 1)),
    TRILOBITE(4, "trilobyte", new EffectInstance(Effects.FIRE_RESISTANCE, 150, 1)),
    LEAF(5, "walking_leaf", new EffectInstance(Effects.INVISIBILITY, 400, 1, false,false)),
    GYRINIDAE(6, "gyrinidae", new EffectInstance(Effects.WATER_BREATHING, 400, 1)),
    FIREFLY(7, "firefly", new EffectInstance(Effects.NIGHT_VISION, 650, 1));

    private final int beetleID;
    private final String beetleName;
    private final EffectInstance instance;

    BeetleType(int beetleID, String beetleName, EffectInstance instance) {
        this.beetleID = beetleID;
        this.beetleName = beetleName;
        this.instance = instance;
    }

    public static BeetleType getBeetleTypeFromID(int beetleID) {
        for (BeetleType value : BeetleType.values()) {
            if(value.beetleID == beetleID) {
                return value;
            }
        }
        return FLEA;
    }

    public EffectInstance getInstance() {
        return instance;
    }

    public static BeetleType getRandomBeetle() {
        return values()[new Random().nextInt(values().length)];
    }

    public int getBeetleID() {
        return beetleID;
    }

    public String getBeetleName() {
        return beetleName;
    }
}
