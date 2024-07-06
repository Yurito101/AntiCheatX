package me.yurito.anticheatx.checks.impl.player.badpackets;

import me.yurito.anticheatx.checks.enums.CheckCategory;
import me.yurito.anticheatx.checks.enums.CheckType;
import me.yurito.anticheatx.checks.types.Check;
import me.yurito.anticheatx.managers.profile.Profile;
import me.yurito.anticheatx.processors.Packet;

public class BadPacketsA extends Check {
    public BadPacketsA(Profile profile) {
        super(profile, CheckType.BADPACKETS, "A", CheckCategory.PLAYER);
    }

    @Override
    public void handle(Packet packet) {
        float pitch = profile.getRotationData().getPitch();
        if (Math.abs(pitch) > 90F || Math.abs(pitch) < -90F) {
            fail();
        }
    }
}
