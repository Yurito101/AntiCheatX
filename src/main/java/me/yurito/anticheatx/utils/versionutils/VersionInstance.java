package me.yurito.anticheatx.utils.versionutils;

import org.bukkit.entity.Player;

public interface VersionInstance {
    ClientVersion getClientVersion(Player player);
}