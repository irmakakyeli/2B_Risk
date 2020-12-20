/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bilkent.cs.simpleworldgame;

import edu.bilkent.cs.simpleworldgame.Distribution.*;

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
    int active_player_num = 0, playerNumber = 3;
    AtomicInteger playerIDgen;
    JSONObject config;
    Region[] regions;
    Player p;
    boolean gameOver, configuration;
    public String selectedRegion1, selectedRegion2;
    String roomID;

    DistributionFactory df;
    Distribution distribution;

    Player winner;


    public GameEngine() {
        playerIDgen = new AtomicInteger();
        player_map = new ConcurrentHashMap<Integer, Player>();
        regions = new Region[47];
        winner = null;
       
        /*regions[0] = new Region("Alaska");
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
        regions[46] = new Region("WesternAustralia");*/
        
        gameOver = false;
        
        //InputStream reader = Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/service-config.json");
        InputStream reader = Thread.currentThread().getContextClassLoader().getResourceAsStream("service-config.json");
        //Read JSON file
        JSONTokener parser = new JSONTokener(reader);

        config = new JSONObject(parser);
        System.out.println(config.toString());
        JSONArray players = config.getJSONArray("PLAYERS");
        Iterator playerIt = players.iterator();
        while (playerIt.hasNext()) {
            JSONObject ply = (JSONObject) playerIt.next();
            p = new Player(playerIDgen.incrementAndGet());
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
        boolean didWin;
        if(r1.getPlayer() == p && r1.getPlayer() != p && r1.totalArmyForce() > 1)
        {
            //REGION CONTROL
            didWin = p.attack(r1, r2);
            
            if(didWin)
            {
                Player temp = r2.playerBelongTo;
                temp.removeRegion(r2);
                p.addRegion(r2, r1.totalArmyForce() - 1);
                r1.setArmies(1);
            }
            if(p.isWinner) {
                gameOver = true;
                winner = p;
            }
            return true;
        } else {
            return false;
        }
    }
    
    public boolean reinforcementControl(String n1, String n2, int army){
        Region r1 = findRegion(n1);
        Region r2 = findRegion(n2);
        if(r1.getPlayer() == p && r1.getPlayer() == p && r1.totalArmyForce() > 1)
        {
            //REGION CONTROL
            p.reinforcement(r1, r2, army);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean fortificationControl(String n, int army){
        Region r = findRegion(n);
        if(r.getPlayer() == p)
        {
            //REGION CONTROL
            p.fortification(r, army);
            return true;
        } else {
            return false;
        }
    }
    
    public Region getRegion(int index){
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
    
    public Region findRegion(String name){
        for (int i = 0; i < 47; i++)
        {
            if(regions[i].getName().equals(name)) {
                return regions[i];
            }
        }
        return null;
    }
    
    private String tellRegion(int x, int y) {
        for(int i = 0; i < getRegions().length; i++) {
            if( contains(regions[i].getArea(), x, y) ) {
                return regions[i].getName();
            }
        }
        return null;
    }

    private boolean contains(Map<Integer, Integer> area, int x, int y) {
        for (Map.Entry<Integer, Integer> entry : area.entrySet()) {
            if(entry.getKey() == x && entry.getValue() == y) {
                return true;
            }
        }

        return false;
    }

    public void setSelectedRegion(String region){
        if (getSelectedRegion1() == null) {
            setSelectedRegion1(region);
        } else if (region.equals(getSelectedRegion1())){
            setSelectedRegion1(null);
        }
        else if (getSelectedRegion2() == null) {
            setSelectedRegion2(region);

        }
        else if (region.equals(getSelectedRegion2())){
            setSelectedRegion2(null);
        }
    }

    public String getSelectedRegion1(){
        return selectedRegion1;
    }

    public String getSelectedRegion2(){
        return selectedRegion2;
    }

    public void setSelectedRegion1(String r){
        selectedRegion1 = r;
    }

    public void setSelectedRegion2(String r){
        selectedRegion2 = r;
    }
    
    public void setUserName(String nm){
        p.setName(nm);
    }
    
    public String getUserName(){
        return p.getName();
    }

    public String getRoomID() {
        return roomID;
    }

    public boolean getConfiguration(){
        return configuration;
    }
    
    public void setConfiguration(boolean con){
        configuration = con;
        
        if (con) {
            df = new CreateAutomatic();
            distribution = df.createProduct(player_map.size(), regions.length);
        }
        
        updatePlayerList(distribution);
    }
    
    private void updatePlayerList(Distribution dt) { // use only for distribution phase, not gameplay phase.
       dt.distribution();
       int[][] gdistribution = dt.getDistribution();
       
       for (int i = 0; i < regions.length; i++) {
           int playerIndex = findWhoseRegion(gdistribution[i]);
           
           player_map.get(playerIndex).addRegion(regions[i], gdistribution[i][playerIndex]);
       }
    }
    
    private int findWhoseRegion(int[] region) {
        
        for (int i = 0; i < player_map.size(); i++) {
            if (region[i] > 0) {
                return i;
            }
        }  
        return -1; // unreachable statement, inş.
    }
    
    public boolean checkGameCode(String code){
        return (roomID.equals(code));
    }
    
    public boolean isRoomFull(){
        return player_map.size() == playerNumber;
    }
    
    public int getSoldier(String nm){
        Region r = findRegion(nm);
        return r.totalArmyForce();
    }
    
    public void setSoldier(String nm, int army){
        Region r = findRegion(nm);
        r.setArmies(army);
    }
    
    public boolean resignRequest(){
        return p.resign();
    }
    
    public String getWinner(){
        return winner.getName();
    }
}
    
