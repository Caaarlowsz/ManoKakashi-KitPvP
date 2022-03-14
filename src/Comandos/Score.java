package Comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.caaarlowsz.manokakashimc.kitpvp.ManoKakashiPvP;

public class Score implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Comando de Jogador!");
			return true;
		}
		Player p = (Player) sender;
		if (ManoKakashiPvP.score.contains(p.getName())) {
			ManoKakashiPvP.score.remove(p.getName());
			p.sendMessage("�4�lKIT�F�LPVP �a: � �aSCORE ATIVADA");

		} else {
			ManoKakashiPvP.score.add(p.getName());
			p.sendMessage("�4�lKIT�F�LPVP �a: � �cSCORE DESATIVADA");
		}

		return false;
	}

}
