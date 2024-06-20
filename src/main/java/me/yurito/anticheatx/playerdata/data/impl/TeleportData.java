package me.yurito.anticheatx.playerdata.data.impl;

import me.yurito.anticheatx.playerdata.data.Data;
import me.yurito.anticheatx.processors.Packet;

public class TeleportData implements Data {

    private int teleportTicks;

    @Override
    public void process(Packet packet) {
    }

    public int getTeleportTicks() {
        return teleportTicks;
    }
}