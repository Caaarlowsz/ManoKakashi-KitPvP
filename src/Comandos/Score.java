package Comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Main.Main;

public class Score implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Comando de Jogador!");
			return true;
		}
		Player p = (Player) sender;
		if (Main.score.contains(p.getName())) {
			Main.score.remove(p.getName());
			p.sendMessage("§4§lKIT§F§LPVP §a: » §aSCORE ATIVADA");

		} else {
			Main.score.add(p.getName());
			p.sendMessage("§4§lKIT§F§LPVP §a: » §cSCORE DESATIVADA");
		}

		return false;
	}

}
