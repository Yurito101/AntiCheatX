package me.yurito.anticheatx.checks.impl.movement.groundspoof;

import me.yurito.anticheatx.checks.enums.CheckCategory;
import me.yurito.anticheatx.checks.enums.CheckType;
import me.yurito.anticheatx.checks.types.Check;
import me.yurito.anticheatx.managers.profile.Profile;
import me.yurito.anticheatx.processors.Packet;

public class GroundSpoofA extends Check {
    public GroundSpoofA(Profile profile) {
        super(profile, CheckType.GROUNDSPOOF, "A", CheckCategory.MOVEMENT);
    }

    @Override
    public void handle(Packet packet) {

        final double deltaY = profile.getMovementData().getDeltaY();

        final boolean ground = profile.getMovementData().isOnGround();
        final boolean math = profile.getMovementData().getDeltaY() % 0.015625 == 0.0;
        final boolean step = profile.getMovementData().getDeltaY() % 0.015625 == 0.0 && deltaY > 0.0;

        if (!step && deltaY > 1.0) {
            fail();
        }

        if (math != ground) {
            if (increaseBufferBy(1.0) > 4) {
                fail();
            }
        }
        else {
            decreaseBufferBy(0.25F);
            fail();
        }
    }
}
