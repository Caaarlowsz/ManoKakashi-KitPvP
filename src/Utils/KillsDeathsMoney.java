package Utils;

import org.bukkit.entity.Player;

import com.github.caaarlowsz.manokakashimc.kitpvp.ManoKakashiPvP;

public class KillsDeathsMoney {

	public static void addKill(Player p, int i) {
		int Value = ManoKakashiPvP.getInstace().stats.getInt(p.getUniqueId() + ".Kills");
		ManoKakashiPvP.getInstace().stats.set(p.getUniqueId() + ".Kills", Integer.valueOf(Value + 1));
		ManoKakashiPvP.getInstace().save();
	}

	public static void addDeaths(Player p, int i) {
		int Value = ManoKakashiPvP.getInstace().stats.getInt(p.getUniqueId() + ".Deaths");
		ManoKakashiPvP.getInstace().stats.set(p.getUniqueId() + ".Deaths", Integer.valueOf(Value + 1));
		ManoKakashiPvP.getInstace().save();
	}

	public static void addMoney(Player p, int i) {
		int Value = ManoKakashiPvP.getInstace().stats.getInt(p.getUniqueId() + ".Money");
		ManoKakashiPvP.getInstace().stats.set(p.getUniqueId() + ".Money", Integer.valueOf(Value + 100));
		ManoKakashiPvP.getInstace().save();
	}

	public static void removermoney(Player p, int i) {
		int Value = ManoKakashiPvP.getInstace().stats.getInt(p.getUniqueId() + ".Money");
		if ((Value - 60) > 0) {
			ManoKakashiPvP.getInstace().stats.set(p.getUniqueId() + ".Money", Integer.valueOf(Value - 60));
		} else {
			ManoKakashiPvP.getInstace().stats.set(p.getUniqueId() + ".Money", Integer.valueOf(0));
		}
		ManoKakashiPvP.getInstace().save();
	}

	public static int getKills(Player p) {
		return ManoKakashiPvP.getInstace().stats.getInt(p.getUniqueId() + ".Kills");
	}

	public static int getDeaths(Player p) {
		return ManoKakashiPvP.getInstace().stats.getInt(p.getUniqueId() + ".Deaths");
	}

	public static int getMoney(Player p) {
		return ManoKakashiPvP.getInstace().stats.getInt(p.getUniqueId() + ".Money");
	}

}
