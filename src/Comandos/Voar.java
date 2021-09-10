package Comandos;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Voar implements Listener, CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Comando para jogadores!");
			return false;
		}
		Player p = (Player) sender;
		if ((cmd.getName().equalsIgnoreCase("voar")) && (p.hasPermission("fly.voar"))) {
			if (args.length == 0) {
				if (!p.getAllowFlight()) {
					p.setAllowFlight(true);
					p.sendMessage("§4§lKIT§F§LPVP §a: §6Voo Habilitado para: " + ChatColor.GRAY + p.getName());
				} else {
					p.setAllowFlight(false);
					p.sendMessage("§4§lKIT§F§LPVP §a: §6Voo Desabilitado para: " + ChatColor.GRAY + p.getName());
				}
			} else {
				Player t = p.getServer().getPlayer(args[0]);
				if (t != null) {
					if (!t.getAllowFlight()) {
						t.setAllowFlight(true);
						p.sendMessage("§4§lKIT§F§LPVP §a: §6Voce Ativou o Voo para §4" + t.getName());
						t.sendMessage("§4§lKIT§F§LPVP §a: §6Voo Habilitado para: " + ChatColor.RED + t.getName());
					} else {
						t.setAllowFlight(false);
						p.sendMessage("§4§lKIT§F§LPVP §a: §6Voce Desativou o Voo para §4" + t.getName());
						t.sendMessage("§4§lKIT§F§LPVP §a: §6Voo Desabilitado para: " + ChatColor.RED + t.getName());
					}
				} else {
					p.sendMessage("§4§lKIT§F§LPVP §a: §cErro: Jogador inexistente!");
				}
			}
		}
		return false;
	}
}
