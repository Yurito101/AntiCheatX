package me.yurito.anticheatx.managers.profile;

import me.yurito.anticheatx.AntiCheatX;
import me.yurito.anticheatx.enums.Permissions;
import me.yurito.anticheatx.files.Config;
import me.yurito.anticheatx.managers.threads.ProfileThread;
import me.yurito.anticheatx.playerdata.data.impl.ActionData;
import me.yurito.anticheatx.playerdata.data.impl.CombatData;
import me.yurito.anticheatx.playerdata.data.impl.ConnectionData;
import me.yurito.anticheatx.playerdata.data.impl.MovementData;
import me.yurito.anticheatx.playerdata.data.impl.RotationData;
import me.yurito.anticheatx.playerdata.data.impl.TeleportData;
import me.yurito.anticheatx.playerdata.data.impl.VehicleData;
import me.yurito.anticheatx.playerdata.data.impl.VelocityData;
import me.yurito.anticheatx.processors.Packet;
import me.yurito.anticheatx.utils.ChatUtils;
import me.yurito.anticheatx.utils.TaskUtils;
import me.yurito.anticheatx.utils.custom.CheckHolder;
import me.yurito.anticheatx.utils.custom.Exempt;
import me.yurito.anticheatx.utils.versionutils.ClientVersion;
import me.yurito.anticheatx.utils.versionutils.VersionUtils;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * A profile class containing every single information we need
 */
public class Profile {

    //-------------------------------------------
    private final ActionData actionData;
    private final CombatData combatData;
    private final ConnectionData connectionData;
    private final MovementData movementData;
    private final RotationData rotationData;
    private final TeleportData teleportData;
    private final VelocityData velocityData;
    private final VehicleData vehicleData;
    //-------------------------------------------

    //--------------------------------------
    private final CheckHolder checkHolder;
    //--------------------------------------

    //--------------------------------------
    private final ClientVersion version;
    private String client = "Unknown";
    private final boolean bypass;
    //--------------------------------------

    //------------------------------------------
    private final ProfileThread profileThread;
    private final Player player;
    private final UUID uuid;
    //------------------------------------------

    //---------------------------
    private final Exempt exempt;
    //---------------------------

    public Profile(Player player) {

        //Player Object
        this.player = player;

        //UUID
        this.uuid = player.getUniqueId();

        //Version
        this.version = VersionUtils.getClientVersion(player);

        //Bypass
        this.bypass = !Config.Setting.DISABLE_BYPASS_PERMISSION.getBoolean() && player.hasPermission(Permissions.BYPASS.getPermission());

        //Data
        this.actionData = new ActionData(this);
        this.combatData = new CombatData();
        this.connectionData = new ConnectionData();
        this.movementData = new MovementData(this);
        this.rotationData = new RotationData(this);
        this.teleportData = new TeleportData();
        this.velocityData = new VelocityData();
        this.vehicleData = new VehicleData();

        //Check Holder
        this.checkHolder = new CheckHolder(this);

        //Exempt
        this.exempt = new Exempt(this);

        //Thread
        this.profileThread = AntiCheatX.getInstance().getThreadManager().getAvailableProfileThread();

        //Initialize Checks
        reloadChecks();
    }

    public boolean isBypassing() {
        return bypass;
    }

    public void handle(Packet packet) {

        if (this.player == null) return;

        this.connectionData.process(packet);
        this.actionData.process(packet);
        this.combatData.process(packet);
        this.movementData.process(packet);
        this.rotationData.process(packet);
        this.teleportData.process(packet);
        this.velocityData.process(packet);
        this.vehicleData.process(packet);

        if (skip(packet.getTimeStamp())) return;

        this.exempt.handleExempts(packet.getTimeStamp());

        this.checkHolder.runChecks(packet);
    }

    public void kick(String reason) {

        if (this.player == null) return;

        TaskUtils.task(() -> this.player.kickPlayer(ChatUtils.format(reason)));
    }

    public ClientVersion getVersion() {
        return version;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Player getPlayer() {
        return this.player;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public void reloadChecks() {
        this.checkHolder.registerAll();
    }

    private boolean skip(long currentTime) {

        //You can add more conditions here
        return this.bypass;
    }

    public void handleTick(long currentTime) {
        //Handle the tick here
    }

    public TeleportData getTeleportData() {
        return teleportData;
    }

    public ActionData getActionData() {
        return actionData;
    }

    public CombatData getCombatData() {
        return combatData;
    }

    public ConnectionData getConnectionData() {
        return connectionData;
    }

    public MovementData getMovementData() {
        return movementData;
    }

    public RotationData getRotationData() {
        return rotationData;
    }

    public VelocityData getVelocityData() {
        return velocityData;
    }

    public VehicleData getVehicleData() {
        return vehicleData;
    }

    public CheckHolder getCheckHolder() {
        return checkHolder;
    }

    public Exempt isExempt() {
        return exempt;
    }

    public ProfileThread getProfileThread() {
        return profileThread;
    }
}