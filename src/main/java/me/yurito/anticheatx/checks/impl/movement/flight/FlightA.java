package me.yurito.anticheatx.checks.impl.movement.flight;

import me.yurito.anticheatx.checks.enums.CheckCategory;
import me.yurito.anticheatx.checks.enums.CheckType;
import me.yurito.anticheatx.checks.types.Check;
import me.yurito.anticheatx.managers.profile.Profile;
import me.yurito.anticheatx.processors.Packet;

public class FlightA extends Check {
    public FlightA(Profile profile) {
        super(profile, CheckType.FLIGHT, "A", CheckCategory.MOVEMENT);
    }

    @Override
    public void handle(Packet packet) {
        if (packet.isFlying()) {
            debug(packet.isFlying());
            if (increaseBuffer() > 2) {
                fail();
            }
        }
        else {
            decreaseBufferBy(0.25);
            fail();
        }
    }
}