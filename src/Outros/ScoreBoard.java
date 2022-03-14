package Outros;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import APIs.SimpleScoreboard;
import com.github.caaarlowsz.manokakashimc.kitpvp.ManoKakashiPvP;
import Utils.KillsDeathsMoney;
import Utils.KitAPI;

public class ScoreBoard implements Listener {

	public static Scoreboard getScoreBoard() {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		return board;
	}

	public static ManoKakashiPvP plugin;

	@SuppressWarnings({ "deprecation" })
	public static void UpdateScore(Player p) {
		int online = Bukkit.getOnlinePlayers().length;
		int kills = KillsDeathsMoney.getKills(p);
		int deaths = KillsDeathsMoney.getDeaths(p);
		int coins = KillsDeathsMoney.getMoney(p);
		Bukkit.getScheduler().scheduleSyncDelayedTask(ManoKakashiPvP.getInstace(), new Runnable() {
			@Override
			public void run() {
				SimpleScoreboard scoreboard = new SimpleScoreboard("§4§lKIT§F§LPVP");
				scoreboard.add("     §7KitPvP.com.br");
				scoreboard.blankLine();
				scoreboard.add("§4§lKills ➟ §7" + kills);
				scoreboard.add("§4§lDeaths ➟ §7" + deaths);
				scoreboard.add("§4§lRank ➟ §8✗UnRanked");
				scoreboard.blankLine();
				scoreboard.add("§4§lKit ➟ §7" + KitAPI.getKit(p));
				scoreboard.blankLine();
				scoreboard.add("§4§lCoins ➟ §7" + coins);
				scoreboard.add("§4§lClan ➟ §7Nenhum");
				scoreboard.add("§4ⓚ§4§lOnline ➟ §7" + online + "/" + Bukkit.getMaxPlayers());
				scoreboard.blankLine();
				scoreboard.add("§7/score");
				scoreboard.build();
				scoreboard.send(p);
			}
		}, 5L);

	}

	public static void clearScoreboard(Player p) {
		getScoreBoard().clearSlot(DisplaySlot.SIDEBAR);
		p.setScoreboard(getScoreBoard());
	}
}
