package redcastlemedia.multitallented.bukkit.heromatchmaking.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.material.Lever;
import redcastlemedia.multitallented.bukkit.heromatchmaking.*;
import redcastlemedia.multitallented.bukkit.heromatchmaking.model.GameType;
import redcastlemedia.multitallented.bukkit.heromatchmaking.model.TeamType;
import redcastlemedia.multitallented.bukkit.heromatchmaking.model.User;

/**
 *
 * @author Multitallented
 */
public class HMMListener implements Listener {
    private final HeroMatchMaking controller;
    public HMMListener(HeroMatchMaking controller) {
        this.controller = controller;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        new JoinOrder(controller, event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        new QuitOrder(controller, event.getPlayer());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        new DeathOrder(controller, event.getEntity());
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        new RespawnOrder(controller, event.getPlayer());
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().equals("/suicide")) {
            return;
        }
        User u = controller.getUserManager().getUser(event.getPlayer().getName());
        if (u.getMatch() != null) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + HeroMatchMaking.NAME + " You can't use commands while in a match!");
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerTeleport(final PlayerTeleportEvent event) {
        final User user = controller.getUserManager().getUser(event.getPlayer().getName());
        if (user.getMatch() != null) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + HeroMatchMaking.NAME + " You can't teleport while in a match!");
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent event) {
        new DamageOrder(controller, event);
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        User u = controller.getUserManager().getUser(event.getPlayer().getName());
        if (u.getMatch() != null && !u.getMatch().getArena().canBuild()) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        User u = controller.getUserManager().getUser(event.getPlayer().getName());
        if (u.getMatch() != null && !u.getMatch().getArena().canBuild()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        if(!event.isCancelled() && event.getClickedBlock().getType().equals(Material.LEVER)){
            if (event.getClickedBlock().getRelative(0, 1, 0).getType().equals(Material.SIGN)){
                Sign sign = (Sign) event.getClickedBlock().getRelative(0, 1, 0).getState();
                String type = sign.getLine(1);
                for (GameType gt : GameType.values()) {
                    if (gt.name().equalsIgnoreCase(type)) {
                        User user = controller.getUserManager().getUser(event.getPlayer().getName());
                        if (user.getGType().contains(gt)) {
                            user.getGType().remove(gt);
                            //TODO tell them it changed
                        } else {
                            user.getGType().add(gt);
                            //TODO tell them it changed
                        }
                        return;
                    }
                }
                for (TeamType tt : TeamType.values()) {
                    if (tt.name().equalsIgnoreCase(type)) {
                        User user = controller.getUserManager().getUser(event.getPlayer().getName());
                        if (user.getTType().contains(tt)) {
                            user.getTType().remove(tt);
                            //TODO tell them it changed
                        } else {
                            user.getTType().add(tt);
                            //TODO tell them it changed
                        }
                        return;
                    }
                }
            }
        }
    }
}
