/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.geom.Area;
import java.util.Map;
import org.json.JSONObject;

public class RegionInfo {
    Integer id;
    String name;
    Integer capacity;
    boolean isCapital, isSpecial;
    public int cavalryAmount, artilleryAmount, infantryAmount, totalArmy;  
    Integer playerBelongTo;
    
    
    public RegionInfo(String gname, Integer rid) {
        id = rid;
        name = gname;
        isCapital = false;
        isSpecial = false; 
    }
    
    public void setName(String nm) {
        name = nm;
    }

    public String getName() {
        return name;
    }
    
    public Integer getId(){
        return id;
    }
    
    public void LoadFromJSON(JSONObject jsonObj) {
        id = jsonObj.getInt("id");
        capacity = jsonObj.getInt("capacity");
    }
    
    public void makeCapital()
    {
        isCapital = true;
    }
    
    public int totalArmyForce()
    {
        totalArmy = infantryAmount + cavalryAmount * 2 +  artilleryAmount * 5;
        return totalArmy;
    }
    
    public int getCavalryAmount(){
        return cavalryAmount;
    }
    
    public int getInfantryAmount(){
        return infantryAmount;
    }
    
    public int getArtilleryAmount(){
        return artilleryAmount;
    }
    
    public Integer getPlayer ()
    {
        return playerBelongTo;
    }
    
    /*public void setPlayer (Player gplayer)
    {
        playerBelongTo = gplayer;
    }*/
    
    public void setArmies(int number)
    {
        if(number > totalArmy)
        {
            int temp = number - totalArmy;
            while (temp > 0)
            {
                if(temp > 5)
                {
                    artilleryAmount++;
                    temp -= 5;
                }
                else if (temp > 2)
                {
                    cavalryAmount++;
                    temp -= 2;
                }
                else
                {
                    infantryAmount++;
                    temp--;
                }
            }
        }
        else if(number < totalArmy)
        {
            int temp = totalArmy - number;
            while (temp > 0)
            {
                if(temp > 5 && artilleryAmount > 0)
                {
                    artilleryAmount--;
                    temp -= 5;
                }
                else if (temp > 2 && cavalryAmount > 0)
                {
                    cavalryAmount--;
                    temp -= 2;
                }
                else
                {
                    infantryAmount++;
                    temp--;
                }
            }
        }
    }
}