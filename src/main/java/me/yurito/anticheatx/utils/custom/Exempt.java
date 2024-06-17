package me.yurito.anticheatx.utils.custom;

import me.yurito.anticheatx.managers.profile.Profile;
import me.yurito.anticheatx.playerdata.data.impl.MovementData;

public class Exempt {

    private final Profile profile;

    public Exempt(Profile profile) {
        this.profile = profile;
    }

    private boolean movement, velocity, jesus, elytra, vehicle, autoclicker, aim;

    public void handleExempts(long timeStamp) {

        MovementData movementData = profile.getMovementData();

        //Example
        this.movement = movementData.getDeltaXZ() == 0D && movementData.getDeltaY() == 0D;
    }

    public boolean movement() {
        return this.movement;
    }

    public boolean velocity() {
        return this.velocity;
    }

    public boolean jesus() {
        return this.jesus;
    }

    public boolean autoclicker() {
        return this.autoclicker;
    }

    public boolean aim() {
        return this.aim;
    }

    public boolean elytra() {
        return this.elytra;
    }

    public boolean vehicle() {
        return this.vehicle;
    }
}