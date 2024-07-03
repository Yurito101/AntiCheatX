package me.yurito.anticheatx.checks.impl.combat.aim;

import me.yurito.anticheatx.checks.enums.CheckCategory;
import me.yurito.anticheatx.checks.enums.CheckType;
import me.yurito.anticheatx.checks.types.Check;
import me.yurito.anticheatx.managers.profile.Profile;
import me.yurito.anticheatx.playerdata.data.impl.RotationData;
import me.yurito.anticheatx.processors.Packet;

public class AimA extends Check {
    public AimA(Profile profile) {
        super(profile, CheckType.AIM, "A", CheckCategory.COMBAT);
    }

    @Override
    public void handle(Packet packet) {
        if (!packet.isRotation() || profile.isExempt().aim()) return;

        RotationData data = profile.getRotationData();

        final float deltaY = data.getDeltaYaw();
        final float deltaP = data.getDeltaPitch();

        final boolean invaildPitch = deltaP > 0F && deltaP < .005F && deltaY > 2.5F;
        final boolean invaildYaw = deltaY > 0F && deltaY < .005F && deltaP > 2.5F;

        if (invaildPitch || invaildYaw) {

            if (increaseBuffer() > 5) {

                fail();
            }

        } else decreaseBufferBy(0.25);
    }
}
