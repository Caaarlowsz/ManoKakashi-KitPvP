package Comandos;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.caaarlowsz.manokakashimc.kitpvp.ManoKakashiPvP;
import Utils.KillsDeathsMoney;
import me.confuser.barapi.BarAPI;

public class Admin implements CommandExecutor, Listener {

	public static ArrayList<String> admin = new ArrayList<>();
	public static HashMap<String, ItemStack[]> saveinv = new HashMap<String, ItemStack[]>();
	public static HashMap<String, ItemStack[]> savearmor = new HashMap<String, ItemStack[]>();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("�bPrecisa ser um Player para usar esse comando");
			return true;
		}
		if (!(sender.hasPermission("fly.admin"))) {
			sender.sendMessage("�4�lKIT�F�LPVP �a: �c�lVoce nao tem permissao!");
			return true;
		}
		Player p = (Player) sender;
		if (args.length == 0) {
			if (!admin.contains(p.getName())) {
				p.sendMessage("�4�lKIT�F�LPVP �a: �c�bVoce entrou no modo admin!");
				BarAPI.setMessage(p, "�4�lKIT�F�LPVP �a: �c�lvoce entrou no modo admin!", 3);
				for (Player s : Bukkit.getOnlinePlayers()) {
					if (!s.hasPermission("fly.admin")) {
						s.hidePlayer(p);
					}
				}
				p.setGameMode(GameMode.CREATIVE);
				admin.add(p.getName());
				saveinv.put(p.getName(), p.getInventory().getContents());
				savearmor.put(p.getName(), p.getInventory().getArmorContents());
				p.setAllowFlight(true);
				p.getInventory().clear();

				ItemStack estrela = new ItemStack(Material.NETHER_STAR);
				ItemMeta estrelameta = estrela.getItemMeta();
				estrelameta.setDisplayName("�a�lTroca Rapida!");
				estrela.setItemMeta(estrelameta);

				ItemStack barra = new ItemStack(Material.IRON_FENCE);
				ItemMeta barrameta = barra.getItemMeta();
				barrameta.setDisplayName("�a�lPrender!");
				barra.setItemMeta(barrameta);

				ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
				ItemMeta sopameta = sopa.getItemMeta();
				sopameta.setDisplayName("�a�lTestar Auto-Soup!");
				sopa.setItemMeta(sopameta);

				ItemStack etiqueta = new ItemStack(Material.NAME_TAG);
				ItemMeta etiquetameta = etiqueta.getItemMeta();
				etiquetameta.setDisplayName("�a�lInforma�oes do player!");
				etiqueta.setItemMeta(etiquetameta);

				ItemStack kb = new ItemStack(Material.STICK);
				ItemMeta kbmeta = kb.getItemMeta();
				kbmeta.addEnchant(Enchantment.KNOCKBACK, 5, true);
				kbmeta.setDisplayName("�a�lTeste o KnockBack");
				kb.setItemMeta(kbmeta);

				p.getInventory().setItem(0, kb);
				p.getInventory().setItem(1, estrela);
				p.getInventory().setItem(4, barra);
				p.getInventory().setItem(7, sopa);
				p.getInventory().setItem(8, etiqueta);
				p.updateInventory();

			} else {
				p.sendMessage("�4�lKIT�F�LPVP �a: �a�lVoce saiu do modo admin!");
				BarAPI.setMessage(p, "�4�lKIT�F�LPVP �a:�c�lAgora voce saiu do modo admin!", 3);
				p.getInventory().clear();
				admin.remove(p.getName());
				for (Player s : Bukkit.getOnlinePlayers()) {
					if (!s.hasPermission("fly.admin")) {
						s.showPlayer(p);
					}
				}
				p.setGameMode(GameMode.SURVIVAL);
				p.getInventory().setContents(saveinv.get(p.getName()));
				p.getInventory().setArmorContents(savearmor.get(p.getName()));
			}
		}
		return false;
	}

	@EventHandler
	public void prender(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Player && e.getPlayer().getItemInHand().getType() == Material.IRON_FENCE
				&& admin.contains(e.getPlayer().getName())) {
			Player p = e.getPlayer();
			Player t = (Player) e.getRightClicked();
			p.chat("/arena " + t.getName());
		}
	}

	@EventHandler
	public void InteragirEtiqueta(PlayerInteractEntityEvent e) {
		if (e.getPlayer().getItemInHand().getType() == Material.NAME_TAG && admin.contains(e.getPlayer().getName())
				&& e.getRightClicked() instanceof Player) {
			Player p = e.getPlayer();
			Player t = (Player) e.getRightClicked();
			Damageable hp = t;
			p.sendMessage("�6�lInformacoes do Player �7" + t.getName());
			p.sendMessage("�6�lVida �7" + (int) hp.getHealth());
			p.sendMessage("�6�lSopas �7" + getMaterial(t, Material.MUSHROOM_SOUP));
			p.sendMessage("�6�lKills �7" + KillsDeathsMoney.getKills(t));
			p.sendMessage("�6�lDeaths �7" + KillsDeathsMoney.getDeaths(t));
			p.sendMessage("�6�lMoney �7" + KillsDeathsMoney.getMoney(t));
			p.sendMessage("�6�lP �7" + t.getAddress().getHostString());
		}
	}

	public static int getMaterial(Player p, Material m) {
		int value = 0;
		for (ItemStack item : p.getInventory().getContents()) {
			if (item != null && item.getType() == m && item.getAmount() > 0) {
				value += item.getAmount();

			}
		}
		return value;
	}

	@EventHandler
	public void AutoSoup(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Player && e.getPlayer().getItemInHand().getType() == Material.MUSHROOM_SOUP
				&& admin.contains(e.getPlayer().getName())) {
			Player p = e.getPlayer();
			Player t = (Player) e.getRightClicked();
			p.openInventory(t.getInventory());
			ItemStack s = new ItemStack(Material.MUSHROOM_SOUP);
			ItemMeta smeta = s.getItemMeta();
			smeta.setDisplayName("�a�lAutoSoup!");
			s.setItemMeta(smeta);
			t.getInventory().setItem(9, s);
			t.getInventory().setItem(10, new ItemStack(Material.AIR));
			t.getInventory().setItem(11, new ItemStack(Material.AIR));
			t.getInventory().setItem(12, new ItemStack(Material.AIR));
			t.getInventory().setItem(13, new ItemStack(Material.AIR));
			t.getInventory().setItem(14, new ItemStack(Material.AIR));
			t.getInventory().setItem(15, new ItemStack(Material.AIR));
			t.getInventory().setItem(16, new ItemStack(Material.AIR));
			Bukkit.getScheduler().scheduleSyncDelayedTask(ManoKakashiPvP.getInstace(), new Runnable() {
				@Override
				public void run() {
					t.getInventory().setItem(10, s);
				}
			}, 25);
			Bukkit.getScheduler().scheduleSyncDelayedTask(ManoKakashiPvP.getInstace(), new Runnable() {
				@Override
				public void run() {
					t.getInventory().setItem(11, s);
				}
			}, 50);
			Bukkit.getScheduler().scheduleSyncDelayedTask(ManoKakashiPvP.getInstace(), new Runnable() {
				@Override
				public void run() {
					t.getInventory().setItem(12, s);
				}
			}, 75);
			Bukkit.getScheduler().scheduleSyncDelayedTask(ManoKakashiPvP.getInstace(), new Runnable() {
				@Override
				public void run() {
					t.getInventory().setItem(13, s);
				}
			}, 100);
			Bukkit.getScheduler().scheduleSyncDelayedTask(ManoKakashiPvP.getInstace(), new Runnable() {
				@Override
				public void run() {
					t.getInventory().setItem(14, s);
				}
			}, 125);
			Bukkit.getScheduler().scheduleSyncDelayedTask(ManoKakashiPvP.getInstace(), new Runnable() {
				@Override
				public void run() {
					t.getInventory().setItem(15, s);
				}
			}, 150);
			Bukkit.getScheduler().scheduleSyncDelayedTask(ManoKakashiPvP.getInstace(), new Runnable() {
				@Override
				public void run() {
					t.getInventory().setItem(16, s);
				}
			}, 175);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void TrocaRapida(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getMaterial() == Material.NETHER_STAR && admin.contains(p.getName())) {
			for (Player s : Bukkit.getOnlinePlayers()) {
				if (!s.hasPermission("fly.admin")) {
					s.showPlayer(p);
					p.setGameMode(GameMode.SURVIVAL);
				}
			}
			Bukkit.getScheduler().scheduleSyncDelayedTask(ManoKakashiPvP.getInstace(), new Runnable() {
				@Override
				public void run() {
					for (Player s : Bukkit.getOnlinePlayers()) {
						if (!s.hasPermission("fly.admin")) {
							s.hidePlayer(p);
							p.setGameMode(GameMode.CREATIVE);
						}
					}
				}
			}, 15L);
		}
	}

	@EventHandler
	public void Abririnv(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Player && e.getPlayer().getItemInHand().getType() == Material.AIR
				&& admin.contains(e.getPlayer().getName())) {
			Player t = (Player) e.getRightClicked();
			e.getPlayer().openInventory(t.getInventory());
		}
	}
}