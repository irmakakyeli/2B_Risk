/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bilkent.cs.simpleworldgame;

import org.json.JSONObject;

/**
 *
 * @author 
 */
public class Region {
    Integer id;
    String name;
    Integer capacity;
    boolean isCapital, isSpecial;
    int cavalryAmount, artilleryAmount, infantaryAmount, totalArmy;
    Player playerBelongTo;
    Continent continentBelongTo;
    
    public Region(Continent gcontinent) {
        isCapital = false;
        isSpecial = false; 
        continentBelongTo = gcontinent;
    }
    
    public void setName(String nm) {
        name = nm;
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
        totalArmy = infantaryAmount + cavalryAmount*2 +  artilleryAmount * 5;
        return totalArmy;
    }
    
    public void setPlayer (Player gplayer)
    {
        playerBelongTo = gplayer;
    }
    
}
