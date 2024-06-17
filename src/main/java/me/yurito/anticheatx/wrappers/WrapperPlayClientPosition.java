package me.yurito.anticheatx.wrappers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.StructureModifier;

public class WrapperPlayClientPosition extends PacketWrapper {

    public static final PacketType TYPE = PacketType.Play.Client.POSITION;

    private final double x, y, z;
    private final boolean onGround;

    public WrapperPlayClientPosition(PacketContainer packet) {
        super(packet, TYPE);

        StructureModifier<Double> doubles = handle.getDoubles();

        this.x = doubles.read(0);
        this.y = doubles.read(1);
        this.z = doubles.read(2);

        this.onGround = handle.getBooleans().read(0);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public boolean getOnGround() {
        return onGround;
    }
}