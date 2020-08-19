package me.Lebryant.bossbar;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener{

	public Bar bar;
	
	@Override
	public void onEnable() {		
		this.saveDefaultConfig();
		this.getServer().getPluginManager().registerEvents(this, this);
		bar= new Bar(this);
		bar.createBar();
		
		if(Bukkit.getOnlinePlayers().size() > 0)
			for (Player on : Bukkit.getOnlinePlayers())
				bar.addPlayer(on);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("bossbar")) {
			if (!sender.hasPermission("bossbar.reload")) {
				sender.sendMessage(ChatColor.RED + "You cannot run this command!");
				return true;
			}
			if (args.length == 0) {
				// /bossbar
				sender.sendMessage(ChatColor.RED + "Usage: /bossbar reload");
				return true;
				
			}
			if (args.length > 0) {
				// /bossbar reload
				if (args[0].equalsIgnoreCase("reload")) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', 
							this.getConfig().getString("reload-messgae")));
					this.reloadConfig();
				}
			}
		}
		return false;
	}
	
	@EventHandler
    public void onJoin(PlayerJoinEvent event) {
		
		if (!bar.getBar().getPlayers().contains(event.getPlayer()))
			bar.addPlayer(event.getPlayer());
			
	}
}
