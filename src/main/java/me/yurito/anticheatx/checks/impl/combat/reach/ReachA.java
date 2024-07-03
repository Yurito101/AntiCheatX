package me.yurito.anticheatx.checks.impl.combat.reach;

import me.yurito.anticheatx.checks.enums.CheckCategory;
import me.yurito.anticheatx.checks.enums.CheckType;
import me.yurito.anticheatx.checks.types.Check;
import me.yurito.anticheatx.managers.profile.Profile;
import me.yurito.anticheatx.processors.Packet;
import me.yurito.anticheatx.utils.ReachUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReachA extends Check implements Listener {
    @Override
    public void handle(Packet packet) {
    }

    public ReachA(Profile profile) {
        super(profile, CheckType.REACH, "A", CheckCategory.COMBAT);
    }

    private static final ExecutorService ASYNC_CHECK_EXECUTOR = Executors.newFixedThreadPool(1337);

    private final double MAX_REACH = 3.000000001D;

    @EventHandler
    public void onReach(final EntityDamageByEntityEvent e) {

        ASYNC_CHECK_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                Player player = (Player) e.getDamager();

                try {
                    final Method inReach = Player.class.getMethod("isUsingReach");
                    inReach.setAccessible(true);

                    final boolean isUsingReach = (boolean) inReach.invoke(player);
                    if (isUsingReach) return;
                } catch (final NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {}

                Entity target = e.getEntity();

                final double distance = player.getLocation().distance(target.getLocation());

                final byte[] ReachDistance = ReachUtils.Reach(distance);

                if (ReachDistance.length > MAX_REACH) {
                    for (int i = 0; i < 5000; i++) {

                        fail();
                    }
                }
            }
        });
    }
}