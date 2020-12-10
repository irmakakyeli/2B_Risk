/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bilkent.cs.simpleworldgame;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author 
 */
public class Continent {
    AtomicInteger id_gen;
    ConcurrentHashMap<Integer, Region> regionMap;
    String name;
    Integer id;
    Region[] Countries;
    
    public Continent() {
        regionMap = new ConcurrentHashMap<Integer, Region>();
        Countries = new Region[5];
    }
    
    public void AddRegion(Integer id, Region r) {
        regionMap.put(id, r);
    }
    
    public ConcurrentHashMap<Integer, Region> getAllRegions() {
        
        return regionMap;
    }
    
    public void loadFromJSONObject(JSONObject obj) {
        
        id = obj.getInt("id");
        name = obj.getString("name");
        JSONArray regionsArr = obj.getJSONArray("REGIONS");
        for (int i = 0; i < regionsArr.length(); i++) {
            try {
                JSONObject regionObj = (JSONObject) regionsArr.get(i);
                Region rg = new Region();
                rg.LoadFromJSON(regionObj);
                AddRegion(id, rg);
            } catch (JSONException e) {
                System.out.println(e);
            } 
            
        }
        
    }
    
    public JSONObject toJSON() {
        return new JSONObject();
    }
    
    public Region getCountry(int index)
    {
        return Countries[index];
    }
}
