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
import java.util.HashMap;
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
    SimpleRegion[] regions;
    Player p;
    boolean gameOver, configuration;
    public String selectedRegion1, selectedRegion2;
    int roomID;

    DistributionFactory df;
    Distribution distribution;
    String distributionMethod;

    Player winner;
    int hostPlayerId;
    boolean isGameActive = false;
    boolean isGameSetup = false;

    public GameEngine() {
        playerIDgen = new AtomicInteger();
        player_map = new ConcurrentHashMap<Integer, Player>();
        regions = new SimpleRegion[47];
        winner = null;
        
        /*Map<Integer, Integer> area = new HashMap<Integer, Integer>();
        area.put(1,2);*/
       
        regions[0] = new SimpleRegion("Alaska", 0);
        regions[1] = new SimpleRegion("WesternAmerica", 1);
        regions[2] = new SimpleRegion("CentralAmerica", 2);
        regions[3] = new SimpleRegion("EasternUS", 3);
        regions[4] = new SimpleRegion("Greenland", 4);
        regions[5] = new SimpleRegion("NorthWest", 5);
        regions[6] = new SimpleRegion("CentralCanada", 6);
        regions[7] = new SimpleRegion("EasternCanada", 7);
        regions[8] = new SimpleRegion("WesternUS", 8);
        regions[9] = new SimpleRegion("Argentina", 9);
        regions[10] = new SimpleRegion("Brazil", 10);
        regions[11] = new SimpleRegion("Peru", 11);
        regions[12] = new SimpleRegion("Venezuela", 12);
        regions[13] = new SimpleRegion("Colombia", 13);
        regions[14] = new SimpleRegion("Bolivia", 14);
        regions[15] = new SimpleRegion("UnitedKingdom", 15);
        regions[16] = new SimpleRegion("Iceland", 16);
        regions[17] = new SimpleRegion("Germany", 17);
        regions[18] = new SimpleRegion("Skandinavia", 18);
        regions[19] = new SimpleRegion("SouthernEurope", 19);
        regions[20] = new SimpleRegion("Russia", 20);
        regions[21] = new SimpleRegion("Spain", 21);
        regions[22] = new SimpleRegion("France", 22);
        regions[23] = new SimpleRegion("Italia", 23);
        regions[24] = new SimpleRegion("Ukraine", 24);
        regions[25] = new SimpleRegion("Afghanistan", 25);
        regions[26] = new SimpleRegion("China", 26);
        regions[27] = new SimpleRegion("India", 27);
        regions[28] = new SimpleRegion("Irkutsk", 28);
        regions[29] = new SimpleRegion("Japan", 29);
        regions[30] = new SimpleRegion("Kamchatka", 30);
        regions[31] = new SimpleRegion("MiddleEast", 31);
        regions[32] = new SimpleRegion("Mongolia", 32);
        regions[33] = new SimpleRegion("Sian", 33);
        regions[34] = new SimpleRegion("Siberia", 34);
        regions[35] = new SimpleRegion("Ural", 35);
        regions[36] = new SimpleRegion("Yakutsk", 36);
        regions[37] = new SimpleRegion("Congo", 37);
        regions[38] = new SimpleRegion("EastAfrica", 38);
        regions[39] = new SimpleRegion("Egypt", 39);
        regions[40] = new SimpleRegion("Madagaskar", 40);
        regions[41] = new SimpleRegion("NorthAfrica", 41);
        regions[42] = new SimpleRegion("SouthAfrica", 42);
        regions[43] = new SimpleRegion("EasternAustralia", 43);
        regions[44] = new SimpleRegion("Indonesia", 44);
        regions[45] = new SimpleRegion("NewGuinea", 45);
        regions[46] = new SimpleRegion("WesternAustralia", 46);
        
        gameOver = false;
        
        //InputStream reader = Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/service-config.json");
        InputStream reader = Thread.currentThread().getContextClassLoader().getResourceAsStream("service-config.json");
        //Read JSON file
        JSONTokener parser = new JSONTokener(reader);

        /*config = new JSONObject(parser);
        System.out.println(config.toString());
        JSONArray players = config.getJSONArray("PLAYERS");
        Iterator playerIt = players.iterator();
        while (playerIt.hasNext()) {
            JSONObject ply = (JSONObject) playerIt.next();
            p = new Player(playerIDgen.incrementAndGet());
            p.setName(ply.getString("name"));
            player_map.put(p.getId(), p);

        }*/
        //JSONObject worldJson = config.getJSONObject("WORLD");
        //gameWorld.InitializeWorld(worldJson);
    }
    
    /*@WebMethod
    public void registerPlayer(String name) {
        Player p1 = new Player(playerIDgen.incrementAndGet());
        p1.setName(name);
        player_map.put(p1.getId(), p1);
    }*/
    
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
    
    @WebMethod
    public boolean attackControl(Integer playerId, String attackerRegionId, String defenderRegionId){
        if(gameOver)
            return false;
        //SimpleRegion attackerRegion = findRegion(attackerRegionId);
        //SimpleRegion defenderRegion = findRegion(defenderRegionId);
        SimpleRegion attackerRegion = regions[Integer.parseInt(attackerRegionId)];
        SimpleRegion defenderRegion = regions[Integer.parseInt(defenderRegionId)];
        Player p = player_map.get(playerId);
        boolean didWin;
        if(attackerRegion.getPlayer() == playerId && defenderRegion.getPlayer() != playerId && attackerRegion.totalArmyForce() > 1)
        {
            //REGION CONTROL
            didWin = p.attack(attackerRegion, defenderRegion);
            
            if(didWin)
            {
                Integer loserPlayerId = defenderRegion.getPlayer();
                player_map.get(loserPlayerId).removeRegion(defenderRegion);                
                p.addRegion(defenderRegion, attackerRegion.totalArmyForce() - 1);
                attackerRegion.setArmies(1);
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
    
    @WebMethod
    public boolean distributeRegions(String distributionMethod, int numberOfPlayers){
        try{
            if(distributionMethod.toUpperCase() == "AUTO"){
                Automatic distributor = new Automatic(numberOfPlayers, regions.length);
                distributor.distribution();
                int[][] dist = distributor.getDistribution();
                int r_size = dist.length;
                for(int i = 0; i < r_size; i++){
                    SimpleRegion reg = regions[i];
                    int playerId = 0;
                    int troopCount = 0;
                    int p_size = dist[0].length;
                    for(int j = 0; j < p_size; j++){                                                                               
                        if(dist[i][j] != 0){
                            playerId = j;
                            troopCount = dist[i][j];
                        }
                    }
                    
                    Player pl = player_map.get(playerId);                    
                    pl.addRegion(reg, troopCount);
                    reg.setPlayer(pl.getId());
                }
                
                return true;
            } else {
                // TODO Manual: Will be completed when the information to be taken from the cllients has been specified
                return false;
            }
        } catch(Exception e){
            System.out.println("Error in distributeRegions method      " + e.getMessage());
        }
        return false;
    }
    
    @WebMethod
    public String setupGame(String hostPlayerName, int numberOfPlayers, String distMethod){
        if(isGameSetup){
            return "ERROR";
        } else {
            isGameSetup = true;
            distributionMethod = distMethod;

            int id = playerIDgen.getAndIncrement();
            p = new Player(id);
            p.setName(hostPlayerName);
            player_map.put(id, p);
            roomID = 1; // Since there will only be one room for now
            playerNumber = numberOfPlayers;
            active_player_num++;
            return "{HOST_PLAYER_ID: " + id + ", ROOM_ID: " + roomID + "}" ;  // To be converted to a JSON oject in the client
        }
    }
    
    @WebMethod
    public boolean reinforcementControl(Integer playerId, String n1, String n2, int army){
        SimpleRegion r1 = findRegion(n1);
        SimpleRegion r2 = findRegion(n2);
        p = player_map.get(playerId);
        if(r1.getPlayer() == playerId && r2.getPlayer() == playerId && r1.totalArmyForce() > 1)
        {
            //REGION CONTROL
            p.reinforcement(r1, r2, army);
            return true;
        } else {
            return false;
        }
    }
    
    @WebMethod
    public int joinGame(int roomId, String hostPlayerName){
        if(active_player_num >= playerNumber){
            return -1;
        }
        if(isGameSetup == false){
            return -1;
        } else {
            int id = playerIDgen.getAndIncrement();
            p = new Player(id);
            p.setName(hostPlayerName);
            player_map.put(id, p);
            active_player_num++;
            
            // Check if we have reached the number of players specified by the host
            if(active_player_num == playerNumber){
                distributeRegions(distributionMethod, playerNumber);
                isGameActive = true;
            }
            
            return id;
        }
    }
    
    @WebMethod
    public boolean fortificationControl(Integer playerId, Integer regionId, int army){
        SimpleRegion r = regions[regionId];
        if(r.getPlayer() == playerId)
        {
            //REGION CONTROL
            Player p = player_map.get(playerId);
            p.fortification(r, army);
            return true;
        } else {
            return false;
        }
    }
    
    @WebMethod
    public SimpleRegion getRegion(int index){
        return regions[index];
    }

    @WebMethod
    public boolean isGameOver() {
        return gameOver;
    }

    @WebMethod
    public boolean isGameActive() {
        return isGameActive;
    }
    
    @WebMethod
    public String getRegions() {
        JSONArray JS_RegionArray = new JSONArray();
        for (int i = 0; i < 47; i++) 
        {   JSONObject JS_PlayerObj = new JSONObject();
            String id = regions[i].getId().toString();
            String name = regions[i].getName();
            JS_PlayerObj.put("ID", id);
            JS_PlayerObj.put("NAME", name);
            JS_RegionArray.put(JS_PlayerObj);
        }
        return JS_RegionArray.toString();
    }
    
    @WebMethod
    public void handOutCard(){
        
    }
    
    @WebMethod
    public SimpleRegion findRegion(String name){
        for (int i = 0; i < 47; i++)
        {
            if(regions[i].getName().equals(name)) {
                return regions[i];
            }
        }
        return null;
    }
    
    /*private String tellRegion(int x, int y) {
        for(int i = 0; i < getRegions().length(); i++) {
            if( contains(regions[i].getArea(), x, y) ) {
                return regions[i].getName();
            }
        }
        return null;
    }*/

    @WebMethod
    private boolean contains(Map<Integer, Integer> area, int x, int y) {
        for (Map.Entry<Integer, Integer> entry : area.entrySet()) {
            if(entry.getKey() == x && entry.getValue() == y) {
                return true;
            }
        }

        return false;
    }

    @WebMethod
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

    @WebMethod
    public String getSelectedRegion1(){
        return selectedRegion1;
    }

    @WebMethod
    public String getSelectedRegion2(){
        return selectedRegion2;
    }

    @WebMethod
    public void setSelectedRegion1(String r){
        selectedRegion1 = r;
    }

    @WebMethod
    public void setSelectedRegion2(String r){
        selectedRegion2 = r;
    }
    
    @WebMethod
    public void setUserName(String nm){
        p.setName(nm);
    }
    
    @WebMethod
    public String getUserName(){
        return p.getName();
    }

    @WebMethod
    public int getRoomID() {
        return roomID;
    }

    @WebMethod
    public boolean getConfiguration(){
        return configuration;
    }
    
    @WebMethod
    public void setConfiguration(boolean con){
        configuration = con;
        
        if (con) {
            df = new CreateAutomatic();
            distribution = df.createProduct(player_map.size(), regions.length);
            distribution.distribution();
        }
        else{
            df = new CreateManuel();
            distribution = df.createProduct(player_map.size(), regions.length);
            distribution.distribution();
        }
        
        updatePlayerList(distribution);
    }
    
    @WebMethod
    private void updatePlayerList(Distribution dt) { // use only for distribution phase, not gameplay phase.

       int[][] distribution = dt.getDistribution();

       
       for (int i = 0; i < regions.length; i++) {
           int playerIndex = findWhoseRegion(distribution[i]);
           
           player_map.get(playerIndex).addRegion(regions[i], distribution[i][playerIndex]);
       }
    }

    @WebMethod
    public String getGameStatistics(){
        JSONArray JS_RegionArray = new JSONArray();
        for (int i = 0; i < 47; i++) 
        {   JSONObject JS_PlayerObj = new JSONObject();
            String id = regions[i].getId().toString();
            String name = regions[i].getName();
            JS_PlayerObj.put("ID", id);
            JS_PlayerObj.put("NAME", name);            
            JS_PlayerObj.put("CAVALRY_AMOUNT", regions[i].getCavalryAmount());
            JS_PlayerObj.put("ARTILLERY_AMOUNT", regions[i].getArtilleryAmount());
            JS_PlayerObj.put("INFANTRY_AMOUNT", regions[i].getInfantryAmount());
            JS_PlayerObj.put("TOTAL_ARMY_FORCE", regions[i].totalArmyForce());
            JS_PlayerObj.put("PLAYER_BELONG_TO", regions[i].playerBelongTo);
            JS_RegionArray.put(JS_PlayerObj);
        }
        return JS_RegionArray.toString();
    }
    
    @WebMethod
    public int findWhoseRegion(int[] region) { // use only for distribution phase, not gameplay phase.

        
        for (int i = 0; i < player_map.size(); i++) {
            if (region[i] > 0) {
                return i;
            }
        }  
        return -1; // unreachable statement, inş.
    }
    
        public boolean isDistributionFinished() {
        int troopCount;
        int currentTroopCount = 0;
        
        switch (player_map.size()) {
                case 3:
                    troopCount = 35 * player_map.size();
                    break;
                case 4:
                    troopCount = 30 * player_map.size();
                    break;
                case 5:
                    troopCount = 25 * player_map.size();
                    break;
                case 6:
                    troopCount = 20 * player_map.size();
                    break;
                default:
                    troopCount = 120;
            }
        
        for (int i = 0; i < regions.length; i++) {
            currentTroopCount += regions[i].totalArmy;
        }
        
        return currentTroopCount >= troopCount;
    }

    /*@WebMethod
    public boolean checkGameCode(String code){
        return (roomID.equals(code));
    }*/
    
    @WebMethod
    public boolean isRoomFull(){
        return player_map.size() == playerNumber;
    }
    
    @WebMethod
    public int getSoldier(String nm){
        SimpleRegion r = findRegion(nm);
        return r.totalArmyForce();
    }
    
    @WebMethod
    public void setSoldier(String nm, int army){
        SimpleRegion r = findRegion(nm);
        r.setArmies(army);
    }
    
    @WebMethod
    public boolean resignRequest(){
        return p.resign();
    }
    
    @WebMethod
    public String getWinner(){
        return winner.getName();
    }
    
}
    
