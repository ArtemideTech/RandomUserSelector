package it.artmistech.randomuserselector;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUserSelector extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("randomuser").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player)sender;
            if(player.hasPermission("select.user")) {
                List<Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers());

                while(true) {
                    int index = new Random().nextInt(playerList.size());

                    Player selected = Bukkit.getPlayerExact(playerList.get(index).getName());

                    if (selected != null && selected.isOnline()) {
                        player.sendMessage(ChatColor.GREEN + "Selected player: " + selected.getName());
                        break;
                    }
                }
            }else{
                player.sendMessage(ChatColor.RED + "No permission for this command");
            }
        }

        return true;
    }
}
