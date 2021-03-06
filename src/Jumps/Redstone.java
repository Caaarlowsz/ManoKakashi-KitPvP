package Jumps;

import java.util.ArrayList;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import com.github.caaarlowsz.manokakashimc.kitpvp.ManoKakashiPvP;

public class Redstone implements Listener {
	public static ManoKakashiPvP plugin;

	public Redstone(ManoKakashiPvP main) {
		plugin = main;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList<String> RedStone = new ArrayList();

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJump1(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.REDSTONE_BLOCK) {
			RedStone.remove(p.getName());
			Location loc = e.getTo().getBlock().getLocation();
			Vector sponge = p.getLocation().getDirection().multiply(3).setY(1);
			p.setVelocity(sponge);
			p.playSound(loc, Sound.HORSE_JUMP, 6.0F, 1.0F);
			p.playEffect(loc, Effect.ENDER_SIGNAL, null);
			RedStone.add(p.getName());
			return;
		}
	}

	@EventHandler
	public void onFall(EntityDamageEvent e) {
		if ((e.getEntity() instanceof Player)) {
			Player p = (Player) e.getEntity();
			if ((e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) && (RedStone.contains(p.getName()))) {
				e.setCancelled(true);
				RedStone.remove(p.getName());
			}
		}
	}
}
