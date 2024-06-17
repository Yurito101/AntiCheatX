package me.yurito.anticheatx.tasks;

import me.yurito.anticheatx.AntiCheatX;
import me.yurito.anticheatx.checks.types.Check;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * A task that we'll be using in order to clear the profile violations.
 */
public class ViolationTask extends BukkitRunnable {

    private final AntiCheatX plugin;

    public ViolationTask(AntiCheatX plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        this.plugin.getProfileManager().getProfileMap().values().forEach(profile -> {
            for (Check check : profile.getCheckHolder().getChecks()) check.resetVl();
        });
    }
}