package collinvht.wild.entity.obj.goals;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;

public class SwimmingGoal extends RandomWalkingGoal {
    private final boolean swimsDeep;
    public SwimmingGoal(CreatureEntity creature, double speed, int chance, boolean deep) {
        super(creature, speed, chance);
        swimsDeep = deep;
    }

    @Nullable
    protected Vector3d getPosition() {
        Vector3d vector3d = RandomPositionGenerator.findRandomTarget(this.creature, 30, 50);

        for(int i = 0; vector3d != null && !this.creature.world.getBlockState(new BlockPos(vector3d)).allowsMovement(this.creature.world, new BlockPos(vector3d), PathType.WATER) && i++ < 10; vector3d = RandomPositionGenerator.findRandomTarget(this.creature, 10, 7)) {
        }

        if(swimsDeep) {
            if(vector3d != null) {
                if (vector3d.y < 50) {
                    return vector3d;
                } else if (creature.getRNG().nextBoolean()) {
                    return vector3d;
                }
            }
            return null;
        } else {
            return vector3d;
        }
    }
}
