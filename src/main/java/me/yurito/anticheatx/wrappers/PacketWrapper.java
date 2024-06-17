package me.yurito.anticheatx.wrappers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.entity.Player;

public abstract class PacketWrapper {
    protected PacketContainer handle;

    protected PacketWrapper(PacketContainer handle, PacketType type) {
        if (handle == null)
            throw new IllegalArgumentException("Packet handle cannot be NULL.");

        this.handle = handle;
    }

    public PacketContainer getHandle() {
        return handle;
    }

    public void sendPacket(Player receiver) {
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(receiver,
                    getHandle());
        } catch (Exception ignored) {
        }
    }

    public void broadcastPacket() {
        ProtocolLibrary.getProtocolManager().broadcastServerPacket(getHandle());
    }

    public void receivePacket(Player sender) {
        try {
            ProtocolLibrary.getProtocolManager().receiveClientPacket(sender,
                    getHandle());
        } catch (Exception ignored) {
        }
    }
}