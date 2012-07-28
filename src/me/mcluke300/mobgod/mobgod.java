package me.mcluke300.mobgod;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class mobgod extends JavaPlugin implements Listener{

	public static mobgod plugin;
	public static HashMap<String, String> hashmap = new HashMap<String, String>();
	
	@SuppressWarnings("unchecked")
	@Override
	public void onEnable() {
		plugin = this;
		LoadConfiguration();
		System.out.println(this+" has been Enabled");
		try {
			hashmap = (HashMap<String, String>)SLAPI.load("plugins/MobGod/MobsinGod.bin");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
		    MetricsLite metrics = new MetricsLite(this);
		    metrics.start();
		} catch (IOException e) {
		    // Failed to submit the stats :-(
		}
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	@Override
	public void onDisable() {
		try {
			SLAPI.save(hashmap,"plugins/MobGod/MobsinGod.bin");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(this+" has been Disabled");
	}


	@EventHandler
	public void onPlayerEvent(PlayerInteractEntityEvent e) {
		String i = ""+ e.getPlayer().getItemInHand().getTypeId();
		String ii = plugin.getConfig().getString("MobGod.tool");
		Entity ent = e.getRightClicked();
		String uid = "" + ent.getUniqueId();
		String name = e.getPlayer().getName();
		
		if (i.equalsIgnoreCase(ii)) {
			if (e.getPlayer().hasPermission("mobgod.god")) {
				if (!(e.getRightClicked() instanceof Player)) {
				if (hashmap.containsKey(uid)) {
					hashmap.remove(uid);
					e.getPlayer().sendMessage(ChatColor.RED+"Mob removed godmode");
				} else if (!hashmap.containsKey(uid)) {
					hashmap.put(uid, name);
					e.getPlayer().sendMessage(ChatColor.GREEN+"Mob added godmode");
				}}}}}
	
	
	@EventHandler
	public void onPlayerEvent2(EntityDamageEvent e) {
		Entity ent = e.getEntity();
		UUID uid = ent.getUniqueId();
		String uid2 = "" + uid;
		if (hashmap.containsKey(uid2)) {
			e.setCancelled(true);
			}}
	
	@EventHandler
	public void onPlayerEvent3(EntityDamageByEntityEvent e) {
		Entity ent = e.getEntity();
		UUID uid = ent.getUniqueId();
		String uid2 = "" + uid;
		if (hashmap.containsKey(uid2)) {
			e.setCancelled(true);
			if (e.getDamager() instanceof Player && plugin.getConfig().getBoolean("Notify.NotifyPlayers")) {
				String msg = plugin.getConfig().getString("Notify.NotifyMessage");
				String owner = hashmap.get(uid2);
				msg = msg.replaceAll("%owner%", owner);
				Player ply = (Player) e.getDamager();
				ply.sendMessage(msg);
			}}}
	
	
	public void LoadConfiguration() {
		String path = "MobGod.tool";
		String path2 = "Notify.NotifyPlayers";
		String path3 = "Notify.NotifyMessage";
		getConfig().addDefault(path, "73");
		getConfig().addDefault(path2, true);
		getConfig().addDefault(path3, "This mob is in god by %owner%");
		getConfig().options().copyDefaults(true);
		saveConfig();

	}



}