package org.redcastlemedia.multitallented.instantarenas.builder;

import java.util.ArrayList;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.ItemStack;
import org.redcastlemedia.multitallented.instantarenas.InstantArenas;
import org.redcastlemedia.multitallented.instantarenas.model.Arena;
import org.redcastlemedia.multitallented.instantarenas.model.GameType;
import org.redcastlemedia.multitallented.instantarenas.model.TeamType;

/**
 *
 * @author Multitallented
 */
public class RTSArenaBuilder extends Arena {

    @Override
    public HashSet<TeamType> getTeamTypes() {
        HashSet<TeamType> types = new HashSet<>();
        types.add(TeamType.ONE_V_ONE);
        types.add(TeamType.TWO_V_TWO);
        return types;
    }

    @Override
    public boolean isAnythingGoes() {
        return false;
    }
    
    @Override
    public int getLives() {
        return 1;
    }
    
    @Override
    public boolean canBuild() {
        return true;
    }

    @Override
    public GameType getGameType() {
        return GameType.RTS;
    }
    
    @Override
    public boolean hasFriendlyFire() {
        return false;
    }
    
    @Override
    public boolean hasDamage() {
        return true;
    }

    @Override
    public ArrayList<ItemStack> getStartingItems() {
        return new ArrayList<>();
    }

    @Override
    public void build() {
        super.loadChunks();
        Location loc = super.getLocation();
        Location l = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
        World world = l.getWorld();
        int x0 = (int) l.getX();
        int y0 = (int) l.getY();
        int z0 = (int) l.getZ();
        
        for (int x=0; x<21;x++) {
            for (int y=0; y<7; y++) {
                for (int z=0; z<21; z++) {
                    Material mat = Material.AIR;
                    if (y == 0 || y == 6 || x == 0 || x==20 || z==0 || z==20) {
                        mat = Material.BEDROCK;
                    } else if ((x==3 && z==3) || (x==17 && z==17)) {
                        mat = Material.OAK_LOG;
                    } else if ((y>2 && y<6) && (x<6 || x>14) && (z<6 || z>14 ) && (x + z > 29 || x + z < 9)) {
                        mat = Material.OAK_LEAVES;
                    } else if ((x<3 && z > 17) || (x>17 && z<3)) {
                        mat = Material.DIRT;
                    } else if (x + z == 20) {
                        mat = Material.STONE_BRICKS;
                    }
                    if ((y==1 || y==2)) {
                        if ((x==8 && z==9) || (x==9 && z==8) || (x==12 && z==11) || (x==11 && z==12)) {
                            mat = Material.IRON_BARS;
                        } else if ((x>7 && x<11) && (z>7 && z<11)) {
                            mat = Material.COBBLESTONE;
                        } else if ((x>9 && x<13) && (z>9 && z<13)) {
                            mat = Material.COBBLESTONE;
                        }
                    }
                    
                    
                    
                    world.getBlockAt(x0 + x, y0 + y, z0 + z).setType(mat);
                }
            }
        }
        world.getBlockAt(x0 + 9, y0 + 1, z0 + 9).setType(Material.CHEST);
        world.getBlockAt(x0 + 11, y0 + 1, z0 + 11).setType(Material.CHEST);
        world.getBlockAt(x0 + 2, y0 + 1, z0 + 5).setType(Material.CHEST);
        world.getBlockAt(x0 + 18, y0 + 1, z0 + 15).setType(Material.CHEST);
        world.getBlockAt(x0 + 10, y0 + 3, z0 + 10).setType(Material.FURNACE);
        world.getBlockAt(x0 + 3, y0 + 1, z0 + 5).setType(Material.CRAFTING_TABLE);
        world.getBlockAt(x0 + 17, y0 + 1, z0 + 15).setType(Material.CRAFTING_TABLE);
        world.getBlockAt(x0 + 11, y0 + 1, z0 + 10).setType(Material.IRON_ORE);
        world.getBlockAt(x0 + 9, y0 + 1, z0 + 10).setType(Material.IRON_ORE);
        world.getBlockAt(x0 + 10, y0 + 1, z0 + 11).setType(Material.IRON_ORE);
        world.getBlockAt(x0 + 10, y0 + 4, z0 + 10).setType(Material.IRON_ORE);
        world.getBlockAt(x0 + 10, y0 + 1, z0 + 9).setType(Material.IRON_ORE);
        world.getBlockAt(x0 + 18, y0 + 2, z0 + 2).setType(Material.DIAMOND_ORE);
        world.getBlockAt(x0 + 2, y0 + 2, z0 + 18).setType(Material.DIAMOND_ORE);
        world.getBlockAt(x0 + 10, y0 + 5, z0 + 10).setType(Material.DIAMOND_ORE);
        world.getBlockAt(x0 + 15, y0 + 3, z0 + 5).setType(Material.GLOWSTONE);
        world.getBlockAt(x0 + 5, y0 + 3, z0 + 15).setType(Material.GLOWSTONE);
        world.getBlockAt(x0 + 3, y0 + 5, z0 + 3).setType(Material.GLOWSTONE);
        world.getBlockAt(x0 + 17, y0 + 5, z0 + 17).setType(Material.GLOWSTONE);
        world.getBlockAt(x0 + 12, y0 + 1, z0 + 9).setType(Material.STONE_BRICKS);
        world.getBlockAt(x0 + 9, y0 + 1, z0 + 12).setType(Material.STONE_BRICKS);
        world.getBlockAt(x0 + 11, y0 + 1, z0 + 8).setType(Material.STONE_BRICKS);
        world.getBlockAt(x0 + 8, y0 + 1, z0 + 11).setType(Material.STONE_BRICKS);
        
        try {
            Chest chest1 = (Chest) world.getBlockAt(x0 + 9, y0 + 1, z0 + 9).getState();
            Chest chest2 = (Chest) world.getBlockAt(x0 + 11, y0 + 1, z0 + 11).getState();
            Chest chest3 = (Chest) world.getBlockAt(x0 + 2, y0 + 1, z0 + 5).getState();
            Chest chest4 = (Chest) world.getBlockAt(x0 + 18, y0 + 1, z0 + 15).getState();
            Furnace furnace1 = (Furnace) world.getBlockAt(x0 + 10, y0 + 3, z0 + 10).getState();
            chest1.getInventory().clear();
            chest1.getInventory().addItem(new ItemStack(Material.BOW, 1));
            chest1.getInventory().addItem(new ItemStack(Material.ARROW, 64));
            chest1.getInventory().addItem(new ItemStack(Material.OBSIDIAN, 8));
            chest1.update();
            chest2.getInventory().clear();
            chest2.getInventory().addItem(new ItemStack(Material.BOW, 1));
            chest2.getInventory().addItem(new ItemStack(Material.ARROW, 64));
            chest2.getInventory().addItem(new ItemStack(Material.OBSIDIAN, 8));
            chest2.update();
            chest3.getInventory().clear();
            chest3.getInventory().addItem(new ItemStack(Material.STONE_BRICKS, 8));
            chest3.update();
            chest4.getInventory().clear();
            chest4.getInventory().addItem(new ItemStack(Material.STONE_BRICKS, 8));
            chest4.update();
            furnace1.getInventory().clear();
            furnace1.getInventory().setFuel(new ItemStack(Material.COAL, 64));
            furnace1.update();
        } catch (Exception e) {
            
        }
    }

    @Override
    public Location getStartPoint(int i) {
        Location l = super.getLocation();
        if (i==0) {
            return new Location(l.getWorld(), l.getX() + 8.5, l.getY() + 2.5, l.getZ() + 5);
        } else if (i==1) {
            return new Location(l.getWorld(), l.getX() + 11.5, l.getY() + 2.5, l.getZ() + 15);
        } else if (i==2) {
            return new Location(l.getWorld(), l.getX() + 8.5, l.getY() + 2.5, l.getZ() + 5);
        } else if (i==3) {
            return new Location(l.getWorld(), l.getX() + 11.5, l.getY() + 2.5, l.getZ() + 15);
        } else {
            return new Location(l.getWorld(), l.getX() + 8.5, l.getY() + 2.5, l.getZ() + 5);
        }
    }

    @Override
    public int getSize() {
        return 20;
    }
}
