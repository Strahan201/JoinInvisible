package com.sylvcraft.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.dynmap.DynmapAPI;

import com.sylvcraft.JoinInvisible;

public class Visible implements CommandExecutor {
	JoinInvisible plugin;
	
	public Visible(JoinInvisible instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Map<String, String> data = new HashMap<String, String>();
		
		if (args.length == 0) {
			if (!(sender instanceof Player)) {
				plugin.msg("pass-player", sender);
				return true;
			}

			if (!sender.hasPermission("joininvisible.visible")) {
				plugin.msg("access-denied", sender);
				return true;
			}
			
			Player p = (Player)sender;
			setPlayerVisible(p);
			plugin.msg("visible-self", sender);
			return true;
		}

		if (!sender.hasPermission("joininvisible.visible.others")) {
			plugin.msg("access-denied", sender);
			return true;
		}

		Player targetPlayer = plugin.getServer().getPlayer(args[0]);
		if (targetPlayer == null) {
			plugin.msg("invalid-player", sender);
			return true;
		}
		
		data.put("%player%", targetPlayer.getName());
		setPlayerVisible(targetPlayer);
		plugin.msg("visible-other", sender, data);
		return true;
	}
	
	void setPlayerVisible(Player p) {
		DynmapAPI dynmap = (DynmapAPI) Bukkit.getServer().getPluginManager().getPlugin("dynmap");
		if (dynmap == null) return;

		dynmap.assertPlayerInvisibility(p.getName(), false, "JoinInvisible");
	}
}
