package me.Lebryant.bossbar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class Bar {
	
	private int taskID = -1;
	private final Main plugin;
	private BossBar bar;
	
	public Bar(Main plugin) {
		this.plugin = plugin;
		
	}

	public void addPlayer(Player player) {
		bar.addPlayer(player);
		
	}
	public BossBar getBar() {
		return bar;
		
	}
	
	public void createBar() {
		bar = Bukkit.createBossBar(format("&cDISCORD.LEBRYANTCRAFT.NET"), BarColor.BLUE, BarStyle.SOLID);
		bar.setVisible(true);		
		cast();
	}
	
	public void cast() {
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            
			int count = -1;
			double progress = 1.0;
			double time = 1.0/(20);
			
			@Override
			public void run() {				
				bar.setProgress(progress);
				
				switch(count) {
				case -1:
					break;
				case 0:
					bar.setColor(BarColor.PINK);
					bar.setTitle(format("&a&l Vote for Rewards!"));
					break;
				case 1:
					bar.setColor(BarColor.PINK);
					bar.setTitle(format("&e&l Vote for Rewards!"));
					break;
				case 2:
					bar.setColor(BarColor.PINK);
					bar.setTitle(format("&B&l Vote for Rewards!"));
					break;
				case 3:
				default:
					bar.setColor(BarColor.PINK);
					bar.setTitle(format("&cDISCORD.LEBRYANTCRAFT.NET"));
					count = -1;
					break;
				}
				
				progress = progress - time;
				if (progress <= 0) {
					count++;
				    progress = 1.0;
					
				}
			}
			
		}, 0, 20);
	}
	
	private String format(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
}
