package redcastlemedia.multitallented.bukkit.heromatchmaking.manager;

import com.herocraftonline.heroes.characters.Hero;
import java.util.ArrayList;
import org.bukkit.entity.Player;
import redcastlemedia.multitallented.bukkit.heromatchmaking.HeroMatchMaking;
import redcastlemedia.multitallented.bukkit.heromatchmaking.builder.LobbyArenaBuilder;
import redcastlemedia.multitallented.bukkit.heromatchmaking.builder.RTSArenaBuilder;
import redcastlemedia.multitallented.bukkit.heromatchmaking.model.*;

/**
 *
 * @author Multitallented
 */
public class ArenaManager {
    private final HeroMatchMaking controller;
    public ArenaManager(HeroMatchMaking controller) {
        this.controller = controller;
    }
    
    public Arena getArena(GameType gType, TeamType tType) {
        switch(gType) {
            default:
            case ANYTHING_GOES:
            case ASSAULT:
            case CTF:
            case DOMINATION:
                return null;
            case LOBBY:
                switch(tType) {
                    case SOLO:
                        return new LobbyArenaBuilder();
                    default:
                        return null;
                }
            case PITFALL:
                return null;
            case RTS:
                switch(tType) {
                    case TWO_V_TWO:
                    case ONE_V_ONE:
                        return new RTSArenaBuilder();
                    default:
                        return null;
                }
            case SPLEEF:
            case TDM:
            case VANILLA:
                return null;
            
        }
    }
    
    public void preparePlayers(Match match) {
        Arena arena = match.getArena();
        ArrayList<User> users = match.getRawPlayers();
        for (User u : users) {
            Player p = u.getPlayer();
            p.getInventory().clear();
            p.getInventory().setHelmet(null);
            p.getInventory().setChestplate(null);
            p.getInventory().setLeggings(null);
            p.getInventory().setBoots(null);
            try {
                p.getInventory().setHelmet(arena.getStartingItems().get(0));
                p.getInventory().setChestplate(arena.getStartingItems().get(1));
                p.getInventory().setLeggings(arena.getStartingItems().get(2));
                p.getInventory().setBoots(arena.getStartingItems().get(3));
                for (int i = 4; i<arena.getStartingItems().size(); i++) {
                    p.getInventory().addItem(arena.getStartingItems().get(i));
                }
            } catch (Exception e) {
                
            }
            p.setFoodLevel(20);
            if (HeroMatchMaking.heroes != null) {
                Hero h = HeroMatchMaking.heroes.getCharacterManager().getHero(p);
                h.setHealth(h.getMaxHealth());
                h.setMana(h.getMaxMana());
                if (arena.getHeroClass() != null) {
                    h.setHeroClass(arena.getHeroClass(), false);
                }
                if (arena.getProf() != null) {
                    h.setHeroClass(arena.getProf(), true);
                }
            }
        }
        if (match.getRawPlayers().size() == 1) {
            match.getRawPlayers().get(0).getPlayer().teleport(arena.getStartPoint(0));
            return;
        }
        try {
            int k = 0;
            for (int i=0; i< match.getPlayers().get(0).size(); i++) {
                match.getPlayers().get(0).get(i).getPlayer().teleport(arena.getStartPoint(k));
                k+=2;
            }
            k=1;
            for (int i=0; i< match.getPlayers().get(1).size(); i++) {
                match.getPlayers().get(1).get(i).getPlayer().teleport(arena.getStartPoint(k));
                k+=2;
            }
        } catch (Exception e) {
            
        }
    }
    
}
