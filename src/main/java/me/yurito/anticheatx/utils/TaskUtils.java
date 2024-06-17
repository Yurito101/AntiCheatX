package me.yurito.anticheatx.utils;

import me.yurito.anticheatx.AntiCheatX;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

/**
 * A small utility class that we can use in order to create and run tasks quickly
 */
public final class TaskUtils {

    private TaskUtils() {
    }

    public static BukkitTask taskTimer(Runnable runnable, long delay, long interval) {
        return Bukkit.getScheduler().runTaskTimer(AntiCheatX.getInstance(), runnable, delay, interval);
    }

    public static BukkitTask taskTimerAsync(Runnable runnable, long delay, long interval) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(AntiCheatX.getInstance(), runnable, delay, interval);
    }

    public static BukkitTask task(Runnable runnable) {
        return Bukkit.getScheduler().runTask(AntiCheatX.getInstance(), runnable);
    }

    public static BukkitTask taskAsync(Runnable runnable) {
        return Bukkit.getScheduler().runTaskAsynchronously(AntiCheatX.getInstance(), runnable);
    }

    public static BukkitTask taskLater(Runnable runnable, long delay) {
        return Bukkit.getScheduler().runTaskLater(AntiCheatX.getInstance(), runnable, delay);
    }

    public static BukkitTask taskLaterAsync(Runnable runnable, long delay) {
        return Bukkit.getScheduler().runTaskLaterAsynchronously(AntiCheatX.getInstance(), runnable, delay);
    }
}