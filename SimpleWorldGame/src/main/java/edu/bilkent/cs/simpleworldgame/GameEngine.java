/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bilkent.cs.simpleworldgame;

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
    int active_player_num = 0;
    AtomicInteger playerIDgen;
    int num_allowed_games;
    JSONObject config;
    Region[] regions;
    Player p;
    boolean gameOver;

    public GameEngine() {
        playerIDgen = new AtomicInteger();
        player_map = new ConcurrentHashMap<Integer, Player>();
        regions = new Region[47];
       
        regions[0] = new Region("Alaska");
        regions[1] = new Region("WesternAmerica");
        regions[2] = new Region("CentralAmerica");
        regions[3] = new Region("EasternUS");
        regions[4] = new Region("Greenland");
        regions[5] = new Region("NorthWest");
        regions[6] = new Region("CentralCanada");
        regions[7] = new Region("EasternCanada");
        regions[8] = new Region("WesternUS");
        regions[9] = new Region("Argentina");
        regions[10] = new Region("Brazil");
        regions[11] = new Region("Peru");
        regions[12] = new Region("Venezuela");
        regions[13] = new Region("Colombia");
        regions[14] = new Region("Bolivia");
        regions[15] = new Region("UnitedKingdom");
        regions[16] = new Region("Iceland");
        regions[17] = new Region("Germany");
        regions[18] = new Region("Skandinavia");
        regions[19] = new Region("SouthernEurope");
        regions[20] = new Region("Russia");
        regions[21] = new Region("Spain");
        regions[22] = new Region("France");
        regions[23] = new Region("Italia");
        regions[24] = new Region("Ukraine");
        regions[25] = new Region("Afghanistan");
        regions[26] = new Region("China");
        regions[27] = new Region("India");
        regions[28] = new Region("Irkutsk");
        regions[29] = new Region("Japan");
        regions[30] = new Region("Kamchatka");
        regions[31] = new Region("MiddleEast");
        regions[32] = new Region("Mongolia");
        regions[33] = new Region("Sian");
        regions[34] = new Region("Siberia");
        regions[35] = new Region("Ural");
        regions[36] = new Region("Yakutsk");
        regions[37] = new Region("Congo");
        regions[38] = new Region("EastAfrica");
        regions[39] = new Region("Egypt");
        regions[40] = new Region("Madagaskar");
        regions[41] = new Region("NorthAfrica");
        regions[42] = new Region("SouthAfrica");
        regions[43] = new Region("EasternAustralia");
        regions[44] = new Region("Indonesia");
        regions[45] = new Region("NewGuinea");
        regions[46] = new Region("WesternAustralia");
        
        gameOver = false;
        
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
    public void registerPlayer(String name) {
        Player p1 = new Player(playerIDgen.incrementAndGet());
        p1.setName(name);
        player_map.put(p1.getId(), p1);
    }
    
    @WebMethod
    public Player activatePlayer(Integer id) {
        p = (Player) player_map.get(id);  
        p.setActive(true);
        active_player_num++;
        return p;
    }

    @WebMethod
    public String getPlayers() {
        JSONObject JS_ParentObj = new JSONObject();
        for (Map.Entry<Integer, Player> entry : player_map.entrySet()) 
        {   JSONObject JS_PlayerObj = new JSONObject();
            String key = entry.getKey().toString(); 
            Player value = entry.getValue(); 
            JS_PlayerObj.put("name",value.name);
            JS_PlayerObj.put("isActive",Boolean.toString(value.isActive));
            JS_ParentObj.put(key, JS_PlayerObj);
        }
        return JS_ParentObj.toString();
    }
    
    @WebMethod
    public String getWorld() {      
        return config.toString();
    }
    
    public boolean attackControl(String n1, String n2){
        Region r1 = findRegion(n1);
        Region r2 = findRegion(n2);
        if(r1.getPlayer() == p && r1.getPlayer() != p && r1.totalArmyForce() > 1)
        {
            //REGION CONTROL
            p.attack(r1, r2);
            if(p.isWinner) {
                gameOver = true;
            }
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean fortificationControl(String n1, String n2, int army){
        Region r1 = findRegion(n1);
        Region r2 = findRegion(n2);
        if(r1.getPlayer() == p && r1.getPlayer() == p && r1.totalArmyForce() > 1)
        {
            //REGION CONTROL
            p.fortification(r1, r2, army);
            return true;
        }
        else {
            return false;
        }
    }
    
    public Region getRegion(int index)
    {
        return regions[index];
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Region[] getRegions() {
        return regions;
    }
    
    public void handOutCard(){
        
    }
    
    private Region findRegion(String name){
        for (int i = 0; i < 47; i++)
        {
            if(regions[i].getName().equals(name)) {
                return regions[i];
            }
        }
        return null;
    }
}
    
