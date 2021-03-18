package collinvht.wild.entity;

import java.util.Random;

public enum BeetleType {
    FLEA(0, "flea"),
    TIGER(1, "tiger"),
    STAG(2, "stag"),
    TITAN(3, "titan"),
    TRILOBITE(4, "trilobyte"),
    LEAF(5, "walking_leaf"),
    GYRINIDAE(6, "gyrinidae"),
    FIREFLY(7, "firefly");

    private final int beetleID;
    private final String beetleName;

    BeetleType(int beetleID, String beetleName) {
        this.beetleID = beetleID;
        this.beetleName = beetleName;
    }

    public static BeetleType getBeetleTypeFromID(int beetleID) {
        for (BeetleType value : BeetleType.values()) {
            if(value.beetleID == beetleID) {
                return value;
            }
        }
        return FLEA;
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
