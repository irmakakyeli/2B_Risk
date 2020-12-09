/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bilkent.cs.simpleworldgame;

/**
 *
 * @author 
 */
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject; 
import org.json.JSONArray;
import org.json.JSONTokener;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public class GameEngine {
    ConcurrentHashMap<Integer, Player> player_map;
    World gameWorld;
    int active_player_num = 0;
    AtomicInteger playerIDgen;
    int num_allowed_games;
    JSONObject config;
    Continent[] Continents;

    public GameEngine() {
        playerIDgen = new AtomicInteger();
        player_map = new ConcurrentHashMap<Integer, Player>();
        gameWorld = new World();
        Continents = new Continent[7];
        
        //InputStream reader = Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/service-config.json");
        InputStream reader = Thread.currentThread().getContextClassLoader().getResourceAsStream("service-config.json");
        //Read JSON file
        JSONTokener parser = new JSONTokener(reader);

        config = new JSONObject(parser);
        System.out.println(config.toString());
        num_allowed_games = config.getInt("ALLOWED_GAME_NUM");
        JSONArray players = config.getJSONArray("PLAYERS");
        Iterator playerIt = players.iterator();
        while (playerIt.hasNext()) {
            JSONObject ply = (JSONObject) playerIt.next();
            Player p = new Player(playerIDgen.incrementAndGet());
            p.setName(ply.getString("name"));
            player_map.put(p.getId(), p);

        }
        //JSONObject worldJson = config.getJSONObject("WORLD");
        //gameWorld.InitializeWorld(worldJson);
        
        

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
    
    @WebMethod
    public String getWorld() {
        
               
        return config.toString();
    }

}
