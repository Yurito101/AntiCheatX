package me.yurito.anticheatx.utils.versionutils.impl;

import com.comphenix.protocol.ProtocolLibrary;
import me.yurito.anticheatx.utils.versionutils.ClientVersion;
import me.yurito.anticheatx.utils.versionutils.VersionInstance;
import org.bukkit.entity.Player;

public class ProtocolLib implements VersionInstance {
    @Override
    public ClientVersion getClientVersion(Player player) {
        return ClientVersion.getClientVersion(ProtocolLibrary.getProtocolManager().getProtocolVersion(player));
    }
}