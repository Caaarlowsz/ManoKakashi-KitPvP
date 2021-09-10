package EventosPrincipais;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Kits.Kangaroo;
import Kits.TimeLord;
import Outros.ScoreBoard;
import Utils.KitAPI;

public class Respawnou implements Listener {

	public static ItemStack kits;
	public static ItemMeta kitsmeta;
	public static ItemStack warps;
	public static ItemMeta warpsmeta;
	public static ItemStack loja;
	public static ItemMeta lojameta;

	@EventHandler
	public void Respawnar(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		if (Kangaroo.KangarroStats.containsKey(p.getName())) {
			Kangaroo.KangarroStats.remove(p.getName());
		}
		KitAPI.RemoveKit(p);
		p.setLevel(0);
		p.getInventory().clear();
		ScoreBoard.UpdateScore(p);
		KitAPI.KitDelay.remove(p.getName());
		TimeLord.freeze.remove(p.getName());
		kits = new ItemStack(Material.ENDER_CHEST);
		kitsmeta = kits.getItemMeta();
		kitsmeta.setDisplayName("§4§lKits");
		kits.setItemMeta(kitsmeta);

		warps = new ItemStack(Material.BOOK);
		warpsmeta = warps.getItemMeta();
		warpsmeta.setDisplayName("§4§lWarps");
		warps.setItemMeta(warpsmeta);

		loja = new ItemStack(Material.DIAMOND);
		lojameta = loja.getItemMeta();
		lojameta.setDisplayName("§4§lLoja");
		loja.setItemMeta(lojameta);

		p.getInventory().setItem(4, kits);
		p.getInventory().setItem(1, warps);
		p.getInventory().setItem(7, loja);
		p.updateInventory();
	}

}
