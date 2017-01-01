package subside.plugins.koth.hooks.plugins;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kitteh.vanish.VanishManager;
import org.kitteh.vanish.VanishPlugin;

import lombok.Getter;
import lombok.Setter;
import subside.plugins.koth.hooks.AbstractHook;
import subside.plugins.koth.hooks.HookManager;

public class VanishHook extends AbstractHook {
    private @Getter @Setter boolean enabled = false;
    private VanishManager vanishManager;
    
    public VanishHook(HookManager hookManager){
        super(hookManager); // First call the constructor of the parent class
        
        if(Bukkit.getServer().getPluginManager().isPluginEnabled("VanishNoPacket")){
            if(getPlugin().getConfigHandler().getHooks().isVanishNoPacket()){
                enabled = true;
            }
            vanishManager = ((VanishPlugin)Bukkit.getServer().getPluginManager().getPlugin("VanishNoPacket")).getManager();
        }
        getPlugin().getLogger().log(Level.INFO, "Vanish hook: "+(enabled?"Enabled":"Disabled"));
    }
    
    @Override
    public boolean canCap(Player player) {
        return !vanishManager.isVanished(player);
    }

}
