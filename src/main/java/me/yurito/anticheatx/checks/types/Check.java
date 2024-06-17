package me.yurito.anticheatx.checks.types;

import me.yurito.anticheatx.checks.enums.CheckCategory;
import me.yurito.anticheatx.checks.enums.CheckType;
import me.yurito.anticheatx.managers.profile.Profile;
import me.yurito.anticheatx.processors.Packet;

public abstract class Check extends AbstractCheck {

    public Check(Profile profile, CheckType check, String type, CheckCategory checkCategory) {
        super(profile, check, type, String.valueOf(checkCategory));
    }

    public Check(Profile profile, CheckType check, CheckCategory checkCategory) {
        super(profile, check, "", String.valueOf(checkCategory));
    }

    public abstract void handle(Packet packet);
}