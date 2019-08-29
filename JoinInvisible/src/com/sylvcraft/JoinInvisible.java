package com.sylvcraft;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import com.sylvcraft.events.PlayerJoin;

public class JoinInvisible extends JavaPlugin {
  @Override
  public void onEnable() {
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvents(new PlayerJoin(this), this);
  }
}