package me.yurito.anticheatx.wrappers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.StructureModifier;

public class WrapperPlayClientLook extends PacketWrapper {

    public static final PacketType TYPE = PacketType.Play.Client.LOOK;

    private final float yaw, pitch;
    private final boolean onGround;

    public WrapperPlayClientLook(PacketContainer packet) {
        super(packet, TYPE);

        StructureModifier<Float> floats = handle.getFloat();

        this.yaw = floats.read(0);
        this.pitch = floats.read(1);
        this.onGround = handle.getBooleans().read(0);
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public boolean getOnGround() {
        return onGround;
    }
}