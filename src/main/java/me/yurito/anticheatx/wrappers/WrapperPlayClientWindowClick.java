package me.yurito.anticheatx.wrappers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import me.yurito.anticheatx.utils.ServerVersion;

public class WrapperPlayClientWindowClick extends PacketWrapper {

    public static final PacketType TYPE = PacketType.Play.Client.WINDOW_CLICK;

    private final int slot;

    public WrapperPlayClientWindowClick(PacketContainer packet) {
        super(packet, TYPE);

        this.slot = handle.getIntegers().read(ServerVersion.getVersion().isLowerThan(ServerVersion.v1_17_R1) ? 2 : 1);
    }

    public int getSlot() {
        return slot;
    }
}