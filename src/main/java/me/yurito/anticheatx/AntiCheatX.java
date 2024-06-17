package me.yurito.anticheatx;

import com.comphenix.protocol.ProtocolLibrary;
import me.yurito.anticheatx.commands.CommandManager;
import me.yurito.anticheatx.files.Checks;
import me.yurito.anticheatx.files.Config;
import me.yurito.anticheatx.files.commentedfiles.CommentedFileConfiguration;
import me.yurito.anticheatx.listeners.ClientBrandListener;
import me.yurito.anticheatx.listeners.ProfileListener;
import me.yurito.anticheatx.listeners.ViolationListener;
import me.yurito.anticheatx.managers.AlertManager;
import me.yurito.anticheatx.managers.logs.LogManager;
import me.yurito.anticheatx.managers.profile.ProfileManager;
import me.yurito.anticheatx.managers.themes.ThemeManager;
import me.yurito.anticheatx.managers.threads.ThreadManager;
import me.yurito.anticheatx.nms.NmsManager;
import me.yurito.anticheatx.processors.listeners.BukkitListener;
import me.yurito.anticheatx.processors.listeners.NetworkListener;
import me.yurito.anticheatx.tasks.LogsTask;
import me.yurito.anticheatx.tasks.TickTask;
import me.yurito.anticheatx.tasks.ViolationTask;
import me.yurito.anticheatx.utils.ChatUtils;
import me.yurito.anticheatx.utils.MiscUtils;
import me.yurito.anticheatx.utils.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class AntiCheatX extends JavaPlugin {

    private static AntiCheatX instance;

    private Config configuration;
    private Checks checks;

    private ProfileManager profileManager;
    private final NmsManager nmsManager = new NmsManager();
    private LogManager logManager;
    private ThreadManager threadManager;

    private AlertManager alertManager;
    private ThemeManager themeManager;

    @Override
    public void onEnable() {

        instance = this;

        //Initialize
        (this.configuration = new Config(this)).initialize();
        (this.checks = new Checks(this)).initialize();
        (this.profileManager = new ProfileManager()).initialize();
        (this.themeManager = new ThemeManager(this)).initialize();
        (this.logManager = new LogManager(this)).initialize();
        (this.threadManager = new ThreadManager(this)).initialize();
        (this.alertManager = new AlertManager()).initialize();

        //Tasks
        new TickTask(this).runTaskTimerAsynchronously(this, 50L, 0L);

        if (Config.Setting.LOGS_ENABLED.getBoolean()) {
            new LogsTask(this).runTaskTimerAsynchronously(this, 6000L, 6000L);
        }

        new ViolationTask(this).runTaskTimerAsynchronously(this,
                Config.Setting.CHECK_SETTINGS_VIOLATION_RESET_INTERVAL.getLong() * 1200L,
                Config.Setting.CHECK_SETTINGS_VIOLATION_RESET_INTERVAL.getLong() * 1200L);

        //Packet Listeners
        Arrays.asList(
                new NetworkListener(this),
                new ClientBrandListener(this)
        ).forEach(packetListener -> ProtocolLibrary.getProtocolManager().addPacketListener(packetListener));

        //Bukkit Listeners
        Arrays.asList(
                new ProfileListener(this),
                new ViolationListener(this),
                new BukkitListener()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));

        getCommand("anticheatx").setExecutor(new CommandManager(this));

        System.setProperty("com.viaversion.handlePingsAsInvAcknowledgements", "true");

        try {

            MiscUtils.initializeClasses(
                    "me.yurito.anticheatx.utils.fastmath.FastMath",
                    "me.yurito.anticheatx.utils.fastmath.NumbersUtils",
                    "me.yurito.anticheatx.utils.fastmath.FastMathLiteralArrays",
                    "me.yurito.anticheatx.utils.minecraft.MathHelper",
                    "utils.me.yurito.anticheatx.CollisionUtils",
                    "me.yurito.anticheatx.utils.MoveUtils"
            );

        } catch (ClassNotFoundException e) {

            ChatUtils.log("An error was thrown during initialization, The AntiCheatX may not work properly.");

            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {

        this.configuration.shutdown();
        this.checks.shutdown();
        this.profileManager.shutdown();
        this.alertManager.shutdown();
        this.threadManager.shutdown();
        this.themeManager.shutdown();

        ReflectionUtils.clear();

        HandlerList.unregisterAll(this);
        ProtocolLibrary.getProtocolManager().removePacketListeners(this);

        Bukkit.getScheduler().cancelTasks(this);

        instance = null;
    }

    public CommentedFileConfiguration getConfiguration() {
        return this.configuration.getConfig();
    }

    public CommentedFileConfiguration getChecks() {
        return this.checks.getConfig();
    }

    public ThemeManager getThemeManager() {
        return themeManager;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }

    public LogManager getLogManager() {
        return logManager;
    }

    public ThreadManager getThreadManager() {
        return threadManager;
    }

    public AlertManager getAlertManager() {
        return alertManager;
    }

    public NmsManager getNmsManager() {
        return nmsManager;
    }

    public static AntiCheatX getInstance() {
        return instance;
    }
}