package me.yurito.anticheatx.commands.subcommands;

import me.yurito.anticheatx.AntiCheatX;
import me.yurito.anticheatx.commands.SubCommand;
import me.yurito.anticheatx.enums.MsgType;
import me.yurito.anticheatx.enums.Permissions;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AlertsCommand extends SubCommand {

    private final AntiCheatX plugin;

    public AlertsCommand(AntiCheatX plugin) {
        this.plugin = plugin;
    }

    @Override
    protected String getName() {
        return "alerts";
    }

    @Override
    protected String getDescription() {
        return "Toggle the alerts";
    }

    @Override
    protected String getSyntax() {
        return "alerts";
    }

    @Override
    protected String getPermission() {
        return Permissions.COMMAND_ALERTS.getPermission();
    }

    @Override
    protected int maxArguments() {
        return 1;
    }

    @Override
    protected boolean canConsoleExecute() {
        return false;
    }

    @Override
    protected void perform(CommandSender sender, String[] args) {

        final UUID uuid = ((Player) sender).getUniqueId();

        if (this.plugin.getAlertManager().hasAlerts(uuid)) {

            this.plugin.getAlertManager().removePlayerFromAlerts(uuid);

            sender.sendMessage(MsgType.PREFIX.getMessage() + "You have disabled the Alerts");

        } else {

            this.plugin.getAlertManager().addPlayerToAlerts(uuid);

            sender.sendMessage(MsgType.PREFIX.getMessage() + "You have enabled the Alerts");
        }
    }
}