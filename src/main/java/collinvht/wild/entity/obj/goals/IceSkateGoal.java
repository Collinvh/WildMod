package collinvht.wild.entity.obj.goals;

import collinvht.wild.entity.obj.BluePinguinEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

import java.util.EnumSet;
import java.util.Random;

public class IceSkateGoal extends Goal {
    protected double x;
    protected double y;
    protected double z;
    private final BluePinguinEntity entity;
    private final Random random = new Random();
    public IceSkateGoal(BluePinguinEntity entity) {
        this.entity = entity;
        this.setMutexFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public void startExecuting() {
        this.entity.setSliding(true);
        this.entity.getNavigator().tryMoveToXYZ(this.x, this.y, this.z, 1F);
    }

    @Override
    public boolean shouldExecute() {
        BlockPos pos = new BlockPos(entity.getPosX(), entity.getPosY() -1, entity.getPosZ());
        if(!(entity.getEntityWorld().getBlockState(pos).getBlock().getSlipperiness() > 0.90)) {
            if(!(entity.getEntityWorld().getBlockState(pos).getBlock() == Blocks.ICE)) {
                return false;
            }
        }
        if (this.entity.isBeingRidden()) {
            return false;
        } else {
            Vector3d vector3d = this.getPosition();
            if (vector3d == null) {
                return false;
            }
            else if(entity.getEntityWorld().getBlockState(new BlockPos(vector3d)).getBlock() == Blocks.AIR || entity.getEntityWorld().getBlockState(new BlockPos(vector3d)).getBlock() == Blocks.WATER) {
                return false;
            } else {
                this.x = vector3d.x;
                this.y = vector3d.y;
                this.z = vector3d.z;
                if(!entity.isSliding()) {
                    entity.setSliding(true);
                }
                return true;
            }
        }
    }

    @Override
    public void resetTask() {
        this.entity.setSliding(false);
        this.entity.getNavigator().clearPath();
    }

    protected Vector3d getPosition() {
        double x = entity.getPosX() + (random.nextInt(4) - random.nextInt(5));
        double y = entity.getPosY() + (random.nextInt(1) - random.nextInt(2));
        double z = entity.getPosZ() + (random.nextInt(4) - random.nextInt(5));
        return new Vector3d(x, y, z);
    }

    public boolean shouldContinueExecuting() {
        return !this.entity.getNavigator().noPath();
    }
}
