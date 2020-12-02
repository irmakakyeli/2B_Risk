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
    
    public Region() {
    }
    
    public void setName(String nm) {
        name = nm;
    }
    public void LoadFromJSON(JSONObject jsonObj) {
        id = jsonObj.getInt("id");
        capacity = jsonObj.getInt("capacity");
        
    }
    
}
