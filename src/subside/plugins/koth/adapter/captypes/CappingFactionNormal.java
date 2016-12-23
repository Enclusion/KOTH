package subside.plugins.koth.adapter.captypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayerColl;

import subside.plugins.koth.adapter.Capable;
import subside.plugins.koth.hooks.HookManager;

public class CappingFactionNormal extends CappingGroup {
private Faction faction;
    
    public CappingFactionNormal(Faction faction){
        this.faction = faction;
    }
    
    @Override
    public boolean isInOrEqualTo(OfflinePlayer oPlayer){
        try {
            return MPlayerColl.get().get(oPlayer).getFactionId().equals(faction.getId());
        } catch(Exception e){
            return false;
        }
    }

    @Override
    public String getUniqueClassIdentifier(){
        return "faction";
    }
    
    @Override
    public String getUniqueObjectIdentifier(){
        return faction.getId();
    }
    
    @Override
    public String getName(){
        return faction.getName();
    }
    
    public CappingFactionNormal(List<Player> playerList2){
        List<Player> playerList = new ArrayList<Player>(playerList2);
        Collections.shuffle(playerList);
        for(Player player : playerList){
            Faction fac = MPlayerColl.get().get(player).getFaction();
            if(fac.isNormal()){
                faction = fac;
                break;
            }
        }
    }
    
    public Faction getObject(){
        return faction;
    }

    @Override
    public boolean areaCheck(Capable cap) {
        for(Player player : faction.getOnlinePlayers()){
            if(HookManager.getHookManager().canCap(player) && cap.isInArea(player)){
                return true;
            }
        }
        return false;
    }
    
    @Override 
    public List<Player> getAllOnlinePlayers(){
        return faction.getOnlinePlayers();
    }
    

    public static Capper getFromUniqueName(String name){
        return new CappingFactionNormal(FactionColl.get().get(name));
    }
}
