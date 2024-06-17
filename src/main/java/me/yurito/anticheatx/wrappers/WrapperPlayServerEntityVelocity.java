package me.yurito.anticheatx.wrappers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.World;
import org.bukkit.entity.Entity;

public class WrapperPlayServerEntityVelocity extends PacketWrapper {
    public static final PacketType TYPE =
            PacketType.Play.Server.ENTITY_VELOCITY;

    public WrapperPlayServerEntityVelocity() {
        super(new PacketContainer(TYPE), TYPE);
        handle.getModifier().writeDefaults();
    }

    public WrapperPlayServerEntityVelocity(PacketContainer packet) {
        super(packet, TYPE);
    }

    public int getEntityID() {
        return handle.getIntegers().read(0);
    }

    public void setEntityID(int value) {
        handle.getIntegers().write(0, value);
    }

    public Entity getEntity(World world) {
        return handle.getEntityModifier(world).read(0);
    }

    public Entity getEntity(PacketEvent event) {
        return getEntity(event.getPlayer().getWorld());
    }

    public double getVelocityX() {
        return handle.getIntegers().read(1) / 8000.0D;
    }

    public void setVelocityX(double value) {
        handle.getIntegers().write(1, (int) (value * 8000.0D));
    }

    public double getVelocityY() {
        return handle.getIntegers().read(2) / 8000.0D;
    }

    public void setVelocityY(double value) {
        handle.getIntegers().write(2, (int) (value * 8000.0D));
    }

    public double getVelocityZ() {
        return handle.getIntegers().read(3) / 8000.0D;
    }

    public void setVelocityZ(double value) {
        handle.getIntegers().write(3, (int) (value * 8000.0D));
    }
}