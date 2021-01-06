package any.justsiri.anything;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Anything extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        getCommand("health").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.isOp()) {
            if (args.length == 1) {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target instanceof Player && target.isOnline()) {
                    target.setMaxHealth(2);
                    sender.sendMessage(ChatColor.GRAY + "성공적으로 플레이어 " + target + "님의 체력을 2로 설정했습니다.");
                    target.sendMessage(ChatColor.GRAY + "당신은 플레이어 " + sender + "님에 의해 체력이 2로 설정되었습니다.");
                    target.playSound(target.getLocation(), Sound.BLOCK_ANVIL_FALL, 0.5f, 0.5f);
                } else {
                    sender.sendMessage(ChatColor.RED + "사용법: /heart [플레이어] -> [플레이어]의 체력을 2로 설정합니다.");
                    sender.sendMessage(ChatColor.RED + "사용법: /heart reset [플레이어] -> [플레이어]의 체력을 복구시킵니다.(20)");
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("reset")) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (target instanceof Player && target.isOnline()) {
                        target.resetMaxHealth();
                        target.setHealth(20);
                        sender.sendMessage(ChatColor.GRAY + "성공적으로 " + target + "님의 체력을 복구하였습니다.");
                        target.sendMessage(ChatColor.GRAY + "성공적으로 체력이 복구되였습니다.");
                        target.playSound(target.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 0.5f);
                    } else {
                        sender.sendMessage(ChatColor.RED + "사용법: /heart [플레이어] -> [플레이어]의 체력을 2로 설정합니다.");
                        sender.sendMessage(ChatColor.RED + "사용법: /heart reset [플레이어] -> [플레이어]의 체력을 복구시킵니다.(20)");
                    }
                }
            } else {
                sender.sendMessage(ChatColor.RED + "사용법: /heart [플레이어] -> [플레이어]의 체력을 2로 설정합니다.");
                sender.sendMessage(ChatColor.RED + "사용법: /heart reset [플레이어] -> [플레이어]의 체력을 복구시킵니다.(20)");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "당신은 이 명령어를 실행할 권한이 없습니다.");
        }
        return true;
    }
}
