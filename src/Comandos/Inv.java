package Comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Inv implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cComando apenas para players");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("fly.admin")) {
			p.sendMessage("§4§lKIT§F§LPVP §a: §cVoce nao tem permissao a este comando");
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("§4§lKIT§F§LPVP §a: §cUse /inv <nick>");
		} else {
			Player t = Bukkit.getPlayer(args[0]);
			if (t == null) {
				p.sendMessage("§4§lKIT§F§LPVP §a: §cEste jogador nao existe ou esta offline !");
				return true;
			}
			p.openInventory(t.getInventory());
			p.sendMessage("§4§lKIT§F§LPVP §a: §aVoce esta vendo o Inventario do: §f" + t.getDisplayName());
		}
		return false;
	}
}
