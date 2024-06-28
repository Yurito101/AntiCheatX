package me.yurito.anticheatx.checks.impl.movement.speed;

import me.yurito.anticheatx.checks.enums.CheckCategory;
import me.yurito.anticheatx.checks.enums.CheckType;
import me.yurito.anticheatx.checks.types.Check;
import me.yurito.anticheatx.managers.profile.Profile;
import me.yurito.anticheatx.playerdata.data.impl.MovementData;
import me.yurito.anticheatx.processors.Packet;

public class SpeedA extends Check {
    public SpeedA(Profile profile) {
       super(profile, CheckType.SPEED, "A", CheckCategory.MOVEMENT);
    }

    @Override
    public void handle(Packet packet) {
        if (!packet.isRotation()) return;

        MovementData data = profile.getMovementData();

        final float deltaY = profile.getRotationData().getDeltaYaw();

        final double deltaXZ = data.getDeltaXZ();
        final double lastdeltaXZ = data.getLastDeltaXZ();

        final double accel = Math.abs(deltaXZ - lastdeltaXZ);

        final double squaredAccel = accel * 100;

        if (deltaY > 1.5F && deltaXZ > .15D && squaredAccel < 1.0E-5) {

            fail("Speed Detections");
        }
    }
}