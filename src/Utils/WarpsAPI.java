package Utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import com.github.caaarlowsz.manokakashimc.kitpvp.ManoKakashiPvP;
import me.confuser.barapi.BarAPI;

public class WarpsAPI implements Listener {

	public static ArrayList<String> segundos = new ArrayList<>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void Andou(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (segundos.contains(p.getDisplayName())) {
			segundos.remove(p.getDisplayName());
			p.sendMessage("�c�lVoce se moveu seu teleport foi cancelado!");
			p.removePotionEffect(PotionEffectType.SLOW);
			p.removePotionEffect(PotionEffectType.BLINDNESS);
			BarAPI.removeBar(p);
		}
	}

	public static void Set(Player p, String w) {
		ManoKakashiPvP.getInstace().warps.set("warps." + w + ".X", p.getLocation().getX());
		ManoKakashiPvP.getInstace().warps.set("warps." + w + ".Y", p.getLocation().getY());
		ManoKakashiPvP.getInstace().warps.set("warps." + w + ".Z", p.getLocation().getZ());
		ManoKakashiPvP.getInstace().warps.set("warps." + w + ".Pitch", p.getLocation().getPitch());
		ManoKakashiPvP.getInstace().warps.set("warps." + w + ".Yam", p.getLocation().getYaw());
		ManoKakashiPvP.getInstace().warps.set("warps." + w + ".World", p.getLocation().getWorld().getName());
		ManoKakashiPvP.getInstace().save();

	}

	public static void ir(Player p, String w) {
		if (ManoKakashiPvP.getInstace().warps.contains("warps." + w)) {
			Double X = ManoKakashiPvP.getInstace().warps.getDouble("warps." + w + ".X");
			Double Y = ManoKakashiPvP.getInstace().warps.getDouble("warps." + w + ".Y");
			Double Z = ManoKakashiPvP.getInstace().warps.getDouble("warps." + w + ".Z");
			float Pitch = (float) ManoKakashiPvP.getInstace().warps.getDouble("warps." + w + ".Pitch");
			float Yam = (float) ManoKakashiPvP.getInstace().warps.getDouble("warps." + w + ".Yam");
			World world = Bukkit.getWorld(ManoKakashiPvP.getInstace().warps.getString("warps." + w + ".World"));
			Location loc = new Location(world, X, Y, Z, Yam, Pitch);
			p.teleport(loc);
		} else {
			p.sendMessage("�c�lAinda nao foi setada!");
		}
	}
}