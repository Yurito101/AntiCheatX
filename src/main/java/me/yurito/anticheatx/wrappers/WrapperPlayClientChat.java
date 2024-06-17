package me.yurito.anticheatx.wrappers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;

public class WrapperPlayClientChat extends PacketWrapper {

    public static final PacketType TYPE = PacketType.Play.Client.CHAT;

    private final String message;

    public WrapperPlayClientChat(PacketContainer packet) {
        super(packet, TYPE);

        this.message = handle.getStrings().read(0);
    }

    public String getMessage() {
        return message;
    }
}