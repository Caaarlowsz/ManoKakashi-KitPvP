package Comandos;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import com.github.caaarlowsz.manokakashimc.kitpvp.ManoKakashiPvP;
import Outros.ScoreBoard;
import Utils.KitAPI;
import Utils.WarpsAPI;
import me.confuser.barapi.BarAPI;

public class Challenge extends WarpsAPI implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("�bPrecisa ser um Player para usar esse comando");
			return true;
		}
		final Player p = (Player) sender;
		if (args.length == 0) {
			if (KitAPI.getKit(p) != "Nenhum") {
				p.sendMessage("�4�lKIT�F�LPVP �a: �cVoce ja pegou um kit!");
				return true;
			}
			if (KitAPI.getKit(p) == "Nenhum") {
				segundos.add(p.getDisplayName());
				Bukkit.getScheduler().scheduleSyncDelayedTask(ManoKakashiPvP.getInstace(), new Runnable() {
					@SuppressWarnings("deprecation")
					@Override
					public void run() {
						if (segundos.contains(p.getDisplayName())) {
							p.sendMessage("�4�lKIT�F�LPVP �a: �7�lVoce entrou na arena Challenge!");
							BarAPI.setMessage(p, "�4�lKIT�F�LPVP �a: �7�lVoce entrou na arena �c�lChallenge", 3);
							p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
							ir(p, "Challenge");
							p.setLevel(0);
							segundos.remove(p.getDisplayName());
							KitAPI.Challenge(p);
							ScoreBoard.UpdateScore(p);
							for (PotionEffect effect : p.getActivePotionEffects()) {
								p.removePotionEffect(effect.getType());
							}
							p.setHealth(20);
						}
					}
				}, 2L);
			}
		} else {
			if (args[0].equalsIgnoreCase("set")) {
				if (p.hasPermission("fly.set")) {
					Set(p, "Challenge");
					p.sendMessage("�4�lKIT�F�LPVP �a: �b�lVoce setou a arena Challenge!");
				}
			}
		}
		return false;
	}
}