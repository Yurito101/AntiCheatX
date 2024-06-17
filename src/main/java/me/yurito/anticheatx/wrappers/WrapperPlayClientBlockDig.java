package me.yurito.anticheatx.wrappers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.EnumWrappers;

public class WrapperPlayClientBlockDig extends PacketWrapper {

    public static final PacketType TYPE = PacketType.Play.Client.BLOCK_DIG;

    private final BlockPosition location;
    private final EnumWrappers.Direction direction;
    private final EnumWrappers.PlayerDigType status;

    public WrapperPlayClientBlockDig(PacketContainer packet) {
        super(packet, TYPE);

        this.location = handle.getBlockPositionModifier().read(0);

        this.direction = handle.getDirections().read(0);

        this.status = handle.getPlayerDigTypes().read(0);
    }

    public BlockPosition getLocation() {
        return location;
    }

    public EnumWrappers.Direction getDirection() {
        return direction;
    }

    public EnumWrappers.PlayerDigType getStatus() {
        return status;
    }
}