package me.yurito.anticheatx.playerdata.data.impl;

import me.yurito.anticheatx.AntiCheatX;
import me.yurito.anticheatx.managers.profile.Profile;
import me.yurito.anticheatx.playerdata.data.Data;
import me.yurito.anticheatx.processors.Packet;
import me.yurito.anticheatx.utils.MiscUtils;
import me.yurito.anticheatx.utils.custom.PlacedBlock;
import me.yurito.anticheatx.utils.custom.desync.Desync;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ActionData implements Data {

    private GameMode gameMode;

    private boolean allowFlight, sneaking;

    private final Desync desync;

    private PlacedBlock placedBlock;

    private ItemStack itemInMainHand = MiscUtils.EMPTY_ITEM, itemInOffHand = MiscUtils.EMPTY_ITEM;

    private int lastAllowFlightTicks, lastSleepingTicks, lastRidingTicks;

    /*
     * 1.9+
     */
    private int lastDuplicateOnePointSeventeenPacketTicks = 100;

    public ActionData(Profile profile) {

        this.desync = new Desync(profile);

        //Initialize

        Player player = profile.getPlayer();

        this.gameMode = player.getGameMode();

        this.allowFlight = AntiCheatX.getInstance().getNmsManager().getNmsInstance().getAllowFlight(player);

        this.lastAllowFlightTicks = this.allowFlight ? 0 : 100;
    }

    @Override
    public void process(Packet packet) {
    }

    public int getLastRidingTicks() {
        return lastRidingTicks;
    }

    public PlacedBlock getPlacedBlock() {
        return placedBlock;
    }

    public boolean isSneaking() {
        return sneaking;
    }

    public ItemStack getItemInMainHand() {
        return itemInMainHand;
    }

    public ItemStack getItemInOffHand() {
        return itemInOffHand;
    }

    public Desync getDesync() {
        return desync;
    }

    public int getLastSleepingTicks() {
        return lastSleepingTicks;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public int getLastDuplicateOnePointSeventeenPacketTicks() {
        return lastDuplicateOnePointSeventeenPacketTicks;
    }
}