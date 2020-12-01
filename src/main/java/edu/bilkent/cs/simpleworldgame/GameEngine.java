/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bilkent.cs.simpleworldgame;

/**
 *
 * @author nedim.alpdemir
 */
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


import edu.bilkent.cs.simpleworldgame.Player;
import java.util.Map;
import org.json.JSONObject; 
import org.json.JSONArray;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public class GameEngine {
    ConcurrentHashMap<Integer, Player> player_map;
    ConcurrentHashMap<Integer, Player> region_map;
    int active_player_num = 0;
    AtomicInteger playerIDgen;

    public GameEngine() {
        playerIDgen = new AtomicInteger();
        player_map = new ConcurrentHashMap<Integer, Player>();
        //region_map = new ConcurrentHashMap<Integer, Region>();
        Player p1 = new Player(playerIDgen.incrementAndGet());
        Player p2 = new Player(playerIDgen.incrementAndGet());
        p1.setName("Yusuf Alpdemir");
        p2.setName("Mehmet Ak");
        player_map.put(p1.getId(), p1);
        player_map.put(p2.getId(), p2);

    }
    
    @WebMethod
    public void registerPLayer(String name) {
        Player p1 = new Player(playerIDgen.incrementAndGet());
        p1.setName(name);
        player_map.put(p1.getId(), p1);
    }
    
    @WebMethod
    public void activatePLayer(Integer id) {
        Player p = (Player) player_map.get(id);  
        p.setActive(true);
        active_player_num++;


    }

    @WebMethod
    public String getPLayers() {
        JSONObject JS_ParentObj = new JSONObject();
        for (Map.Entry<Integer, Player> entry : player_map.entrySet()) 
        {   JSONObject JS_PlayerObj = new JSONObject();
            String key = entry.getKey().toString(); 
            Player value = entry.getValue(); 
            JS_PlayerObj.put("name",value.name);
            JS_PlayerObj.put("isActive",Boolean.toString(value.isActive));
            
            JS_ParentObj.put(key, JS_PlayerObj);
        }

        //System.out.println(JS_ParentObj.toString());
        
        return JS_ParentObj.toString();
    }
    

}
