package me.yurito.anticheatx.managers.logs;

import me.yurito.anticheatx.AntiCheatX;
import me.yurito.anticheatx.files.Config;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class LogExporter {

    protected static final long DELETE_DAYS = TimeUnit.DAYS.toMillis(Config.Setting.LOGS_CLEAR_DAYS.getInt());

    protected final AntiCheatX plugin;

    public LogExporter(AntiCheatX plugin) {
        this.plugin = plugin;
    }

    public abstract void initialize();

    public abstract void shutdown();

    public abstract void logMultiple(Collection<PlayerLog> logs);

    public abstract void log(PlayerLog log);

    public abstract List<PlayerLog> getLogs();

    public abstract List<PlayerLog> getLogsForPlayer(String player);
}