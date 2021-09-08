package Comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.confuser.barapi.BarAPI;

public class Morrer
  implements Listener, CommandExecutor
{
@SuppressWarnings("deprecation")
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    Player p = (Player)sender;
    if (cmd.getName().equalsIgnoreCase("morrer"))
    {
      p.setHealth(0.0D);
      p.sendMessage("§4§lKIT§F§LPVP §a: §4§lVoce se matou!");
      BarAPI.setMessage(p, "§4§lKIT§F§LPVP §a: §c§lVoce se matou!", 5);
    }
    return false;
  }
}