/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bilkent.cs.simpleworldgame;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import static org.graalvm.compiler.hotspot.amd64.AMD64HotSpotMathIntrinsicOp.IntrinsicOpcode.LOG;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author 
 */
public class World {
    AtomicInteger id_gen;
    ConcurrentHashMap<Integer, Continent> continentMap;
    
    public World() {
        continentMap = new ConcurrentHashMap <Integer, Continent> ();
    }
    
    public void InitializeWorld(JSONObject worldJson) {
        JSONArray continentsArr = worldJson.getJSONArray("CONTINENTS");
        for (int i = 0; i < continentsArr.length(); i++) {
            try {
                JSONObject continentObj = (JSONObject) continentsArr.get(i);
            } catch (JSONException e) {
                System.out.println(e);
            } 
            
        }
        
    }
    
    public JSONObject toJSON() {
        JSONObject JS_ParentObj = new JSONObject();
        JSONArray JS_ContArr = new JSONArray();
        for (Map.Entry<Integer, Continent> entry : continentMap.entrySet()) 
        {   JSONObject JS_ConObj = entry.getValue().toJSON(); 
            JS_ContArr.put(JS_ConObj);
            
            
        }
        //System.out.println(JS_ParentObj.toString());
        JS_ParentObj.put("CONTINENTS", JS_ContArr);
        return JS_ParentObj;
    }
}
