package com.sylvcraft.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.sylvcraft.JoinInvisible;
import org.bukkit.event.player.PlayerJoinEvent;
import org.dynmap.DynmapAPI;


public class PlayerJoin implements Listener {
  JoinInvisible plugin;
  
  public PlayerJoin(JoinInvisible instance) {
    plugin = instance;
  }

	@EventHandler
  public void onPlayerJoin(PlayerJoinEvent e) {
		if (e.getPlayer().hasPlayedBefore()) return;

		DynmapAPI dynmap = (DynmapAPI) Bukkit.getServer().getPluginManager().getPlugin("dynmap");
		if (dynmap == null) return;

		dynmap.assertPlayerInvisibility(e.getPlayer().getName(), true, "JoinInvisible");
  }
}