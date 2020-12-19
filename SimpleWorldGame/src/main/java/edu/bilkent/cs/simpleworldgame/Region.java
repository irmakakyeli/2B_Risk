/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bilkent.cs.simpleworldgame;

import org.json.JSONObject;

public class Region {
    Integer id;
    String name;
    Integer capacity;
    boolean isCapital, isSpecial;
    int cavalryAmount, artilleryAmount, infantaryAmount, totalArmy;
    Player playerBelongTo;
    
    public Region(String gname) {
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
        totalArmy = infantaryAmount + cavalryAmount * 2 +  artilleryAmount * 5;
        return totalArmy;
    }
    
    public Player getPlayer ()
    {
        return playerBelongTo;
    }
    
    public void setPlayer (Player gplayer)
    {
        playerBelongTo = gplayer;
    }
    
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
                    infantaryAmount++;
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
                    infantaryAmount++;
                    temp--;
                }
            }
        }
    }
    
}
