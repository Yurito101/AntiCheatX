package me.yurito.anticheatx.checks.impl.combat.aim;

import me.yurito.anticheatx.checks.enums.CheckCategory;
import me.yurito.anticheatx.checks.enums.CheckType;
import me.yurito.anticheatx.checks.types.Check;
import me.yurito.anticheatx.managers.profile.Profile;
import me.yurito.anticheatx.playerdata.data.impl.RotationData;
import me.yurito.anticheatx.processors.Packet;
import me.yurito.anticheatx.utils.custom.aim.RotationHeuristics;

public class AimA1 extends Check {
    public AimA1(Profile profile) {
        super(profile, CheckType.AIM, "A1", CheckCategory.COMBAT);
    }

    private final RotationHeuristics deltaYHeuristics = new RotationHeuristics(100, 1.25F, 7.5F);
    private final RotationHeuristics deltaPHeuristics = new RotationHeuristics(100, 1.25F, 7.5F);

    @Override
    public void handle(Packet packet) {
        if (!packet.isRotation() || profile.isExempt().aim()) return;

        RotationData data = profile.getRotationData();

        this.deltaYHeuristics.process(data.getDeltaYaw());
        this.deltaPHeuristics.process(data.getDeltaPitch());

        final RotationHeuristics.HeuristicsResult deltaYResult = this.deltaYHeuristics.getResult();
        final RotationHeuristics.HeuristicsResult deltaPResult = this.deltaPHeuristics.getResult();

        if (deltaYResult == null || deltaPResult == null)
            return;

        final float deltaYAverage = deltaYResult.getAverage();
        final float deltaYMin = deltaYResult.getMin();
        final float deltaYMax = deltaYResult.getMax();
        final int deltaYLowCount = deltaYResult.getLowCount();
        final int deltaYHighCount = deltaYResult.getHighCount();
        final int deltaYDuplicates = deltaYResult.getDuplicates();
        final int deltaYRoundedCount = deltaYResult.getRoundedCount();

        final float deltaPAverage = deltaPResult.getAverage();
        final float deltaPMin = deltaPResult.getMin();
        final float deltaPMax = deltaPResult.getMax();
        final int deltaPLowCount = deltaPResult.getLowCount();
        final int deltaPHighCount = deltaPResult.getHighCount();
        final int deltaPDuplicates = deltaPResult.getDuplicates();
        final int deltaPRoundedCount = deltaPResult.getRoundedCount();
    }
}
