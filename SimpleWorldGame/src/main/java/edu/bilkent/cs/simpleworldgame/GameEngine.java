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
import java.util.Objects;
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
    Player currentPlayer, winner;
    boolean gameOver, configuration, onceInTurn;
    public String selectedRegion1, selectedRegion2;

    int roomID;

    DistributionFactory df;
    Distribution distribution;
    String distributionMethod;
    int hostPlayerId;
    boolean isGameActive = false;
    boolean isGameSetup = false;

    public GameEngine() {
        playerIDgen = new AtomicInteger();
        player_map = new ConcurrentHashMap<Integer, Player>();
        winner = null;
        onceInTurn = true;
        
        regions = new Region[47];
        regions[0] = new Region("Alaska", 0);
        regions[1] = new Region("WesternAmerica", 1);
        regions[2] = new Region("CentralAmerica", 2);
        regions[3] = new Region("EasternUS", 3);
        regions[4] = new Region("Greenland", 4);
        regions[5] = new Region("NorthWest", 5);
        regions[6] = new Region("CentralCanada", 6);
        regions[7] = new Region("EasternCanada", 7);
        regions[8] = new Region("WesternUS", 8);
        regions[9] = new Region("Argentina", 9);
        regions[10] = new Region("Brazil", 10);
        regions[11] = new Region("Peru", 11);
        regions[12] = new Region("Venezuela", 12);
        regions[13] = new Region("Colombia", 13);
        regions[14] = new Region("Bolivia", 14);
        regions[15] = new Region("UnitedKingdom", 15);
        regions[16] = new Region("Iceland", 16);
        regions[17] = new Region("Germany", 17);
        regions[18] = new Region("Skandinavia", 18);
        regions[19] = new Region("SouthernEurope", 19);
        regions[20] = new Region("Russia", 20);
        regions[21] = new Region("Spain", 21);
        regions[22] = new Region("France", 22);
        regions[23] = new Region("Italia", 23);
        regions[24] = new Region("Ukraine", 24);
        regions[25] = new Region("Afghanistan", 25);
        regions[26] = new Region("China", 26);
        regions[27] = new Region("India", 27);
        regions[28] = new Region("Irkutsk", 28);
        regions[29] = new Region("Japan", 29);
        regions[30] = new Region("Kamchatka", 30);
        regions[31] = new Region("MiddleEast", 31);
        regions[32] = new Region("Mongolia", 32);
        regions[33] = new Region("Sian", 33);
        regions[34] = new Region("Siberia", 34);
        regions[35] = new Region("Ural", 35);
        regions[36] = new Region("Yakutsk", 36);
        regions[37] = new Region("Congo", 37);
        regions[38] = new Region("EastAfrica", 38);
        regions[39] = new Region("Egypt", 39);
        regions[40] = new Region("Madagaskar", 40);
        regions[41] = new Region("NorthAfrica", 41);
        regions[42] = new Region("SouthAfrica", 42);
        regions[43] = new Region("EasternAustralia", 43);
        regions[44] = new Region("Indonesia", 44);
        regions[45] = new Region("NewGuinea", 45);
        regions[46] = new Region("WesternAustralia", 46);

        gameOver = false;

        // InputStream reader =
        // Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/service-config.json");
        InputStream reader = Thread.currentThread().getContextClassLoader().getResourceAsStream("service-config.json");
        // Read JSON file
        JSONTokener parser = new JSONTokener(reader);

        /*
         * config = new JSONObject(parser); System.out.println(config.toString());
         * JSONArray players = config.getJSONArray("PLAYERS"); Iterator playerIt =
         * players.iterator(); while (playerIt.hasNext()) { JSONObject ply =
         * (JSONObject) playerIt.next(); p = new Player(playerIDgen.incrementAndGet());
         * p.setName(ply.getString("name")); player_map.put(p.getId(), p); }
         */
        // JSONObject worldJson = config.getJSONObject("WORLD");
        // gameWorld.InitializeWorld(worldJson);

    }

    /*
     * @WebMethod public void registerPlayer(String name) { Player p1 = new
     * Player(playerIDgen.incrementAndGet()); p1.setName(name);
     * player_map.put(p1.getId(), p1); }
     */

    @WebMethod
    public Player activatePlayer(Integer id) {
        currentPlayer = (Player) player_map.get(id);
        currentPlayer.setActive(true);
        active_player_num++;
        return currentPlayer;
    }

    @WebMethod
    public String getPlayers() {
        JSONObject JS_ParentObj = new JSONObject();
        for (Map.Entry<Integer, Player> entry : player_map.entrySet()) {
            JSONObject JS_PlayerObj = new JSONObject();
            String key = entry.getKey().toString();
            Player value = entry.getValue();
            JS_PlayerObj.put("name", value.name);
            JS_PlayerObj.put("isActive", Boolean.toString(value.isActive));
            JS_ParentObj.put(key, JS_PlayerObj);
        }
        return JS_ParentObj.toString();
    }

    @WebMethod
    public String getWorld() {
        return config.toString();
    }

    @WebMethod
    public boolean attackControl (String n1, String n2){
        Region attackerRegion = findRegion(n1);
        Region defenderRegion = findRegion(n2);
        boolean didWin;
        if (attackerRegion.getPlayer() == currentPlayer && defenderRegion.getPlayer() != currentPlayer
                && attackerRegion.totalArmyForce() > 1) {
            // REGION CONTROL

            onceInTurn = false;
            didWin = currentPlayer.attack(attackerRegion, defenderRegion, onceInTurn);

            if (didWin) {
                Player loserPlayer = defenderRegion.getPlayer();
                loserPlayer.removeRegion(defenderRegion);
                currentPlayer.addRegion(defenderRegion, attackerRegion.totalArmyForce() - 1);
                attackerRegion.setArmies(1);
            }
            if (currentPlayer.isWinner) {
                gameOver = true;
                winner = currentPlayer;
            }
            return true;
        } else {
            return false;
        }
    }

    @WebMethod
    public boolean distributeRegions(String distributionMethod, int numberOfPlayers) {
        try {
            if (distributionMethod.toUpperCase() == "AUTO") {
                Automatic distributor = new Automatic(numberOfPlayers, regions.length);
                distributor.distribution();
                int[][] dist = distributor.getDistribution();
                int r_size = dist.length;
                for (int i = 0; i < r_size; i++) {
                    Region reg = regions[i];
                    int playerId = 0;
                    int troopCount = 0;
                    int p_size = dist[0].length;
                    for (int j = 0; j < p_size; j++) {
                        if (dist[i][j] != 0) {
                            playerId = j;
                            troopCount = dist[i][j];
                        }
                    }

                    Player pl = player_map.get(playerId);
                    pl.addRegion(reg, troopCount);
                    reg.setPlayer(pl);
                }

                return true;
            } else {
                // TODO Manual: Will be completed when the information to be taken from the
                // cllients has been specified
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error in distributeRegions method      " + e.getMessage());
        }
        return false;
    }

    @WebMethod
    public String setupGame(String hostPlayerName, int numberOfPlayers, String distMethod) {
        if (isGameSetup) {
            return "ERROR";
        } else {
            isGameSetup = true;
            distributionMethod = distMethod;

            int id = playerIDgen.getAndIncrement();
            currentPlayer = new Player(id);
            currentPlayer.setName(hostPlayerName);
            player_map.put(id, currentPlayer);
            roomID = 1; // Since there will only be one room for now
            playerNumber = numberOfPlayers;
            active_player_num++;
            return "{HOST_PLAYER_ID: " + id + ", ROOM_ID: " + roomID + "}"; // To be converted to a JSON oject in the
                                                                            // client
        }
    }

    @WebMethod
    public boolean reinforcementControl(String n1, String n2, int army) {
        Region r1 = findRegion(n1);
        Region r2 = findRegion(n2);
        if (r1.getPlayer() == currentPlayer && r2.getPlayer() == currentPlayer && r1.totalArmyForce() > 1) {
            // REGION CONTROL
            currentPlayer.reinforcement(r1, r2, army);
            return true;
        } else {
            return false;
        }
    }

    @WebMethod
    public int integration() {
        return currentPlayer.cartIntegration();
    }

    @WebMethod
    public boolean fortificationControl(String n, int army) {
        Region r = findRegion(n);
        if (Objects.equals(r.getPlayer(), currentPlayer.getId())) {
            currentPlayer.fortification(r, army);
            return true;
        } else {
            return false;
        }
    }

    @WebMethod
    public int joinGame(int roomId, String PlayerName) {
        if (active_player_num >= playerNumber) {
            return -1;
        }
        if (isGameSetup == false) {
            return -1;
        } else {
            int id = playerIDgen.getAndIncrement();
            currentPlayer = new Player(id);
            currentPlayer.setName(PlayerName);
            player_map.put(id, currentPlayer);
            active_player_num++;

            // Check if we have reached the number of players specified by the host
            if (active_player_num == playerNumber) {
                distributeRegions(distributionMethod, playerNumber);
                isGameActive = true;
            }

            return id;
        }
    }

    @WebMethod
    public boolean fortificationControl(Integer playerId, Integer regionId, int army) {
        Region r = regions[regionId];
        if (Objects.equals(r.getPlayer(), playerId)) {
            Player currentPlayer = player_map.get(playerId);
            return true;
        }
        return false;
    }

    @WebMethod
    public Region getRegion(int index) {
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
        for (int i = 0; i < 47; i++) {
            JSONObject JS_PlayerObj = new JSONObject();
            String id = regions[i].getId().toString();
            String name = regions[i].getName();
            JS_PlayerObj.put("ID", id);
            JS_PlayerObj.put("NAME", name);
            JS_RegionArray.put(JS_PlayerObj);
        }
        return JS_RegionArray.toString();
    }

    @WebMethod
    public Region findRegion(String name) {
        for (int i = 0; i < 47; i++) {
            if (regions[i].getName().equals(name)) {
                return regions[i];
            }
        }
        return null;
    }

   /* @WebMethod
    private boolean contains(Map<Integer, Integer> area, int x, int y) {
        for (Map.Entry<Integer, Integer> entry : area.entrySet()) {
            if (entry.getKey() == x && entry.getValue() == y) {
                return true;
            }
        }
        return false;
    }*/

    @WebMethod
    public void setSelectedRegion(String region) {
        if (getSelectedRegion1() == null) {
            setSelectedRegion1(region);
        } else if (region.equals(getSelectedRegion1())) {
            setSelectedRegion1(null);
        } else if (getSelectedRegion2() == null) {
            setSelectedRegion2(region);

        } else if (region.equals(getSelectedRegion2())) {
            setSelectedRegion2(null);
        }
    }

    @WebMethod
    public String getSelectedRegion1() {
        return selectedRegion1;
    }

    @WebMethod
    public String getSelectedRegion2() {
        return selectedRegion2;
    }

    @WebMethod
    public void setSelectedRegion1(String r) {
        selectedRegion1 = r;
    }

    @WebMethod
    public void setSelectedRegion2(String r) {
        selectedRegion2 = r;
    }

    @WebMethod
    public void setUserName(String nm) {
        currentPlayer.setName(nm);
    }

    @WebMethod
    public String getUserName() {
        return currentPlayer.getName();
    }

    @WebMethod
    public int getRoomID() {
        return roomID;
    }

    @WebMethod
    public boolean getConfiguration() {
        return configuration;
    }

    @WebMethod
    public void setConfiguration(boolean con) {
        configuration = con;

        if (con) {
            df = new CreateAutomatic();
            distribution = df.createProduct(player_map.size(), regions.length);
            distribution.distribution();
        } else {
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
    public String getGameStatistics() {
        JSONObject gameStats = new JSONObject();
        JSONArray JS_RegionArray = new JSONArray();        
        
        gameStats.put("CURRENT_PLAYER_ID", currentPlayer.getId());
        for (int i = 0; i < 47; i++) {
            JSONObject JS_RegionObj = new JSONObject();
            String id = regions[i].getId().toString();
            String name = regions[i].getName();
            JS_RegionObj.put("ID", id);
            JS_RegionObj.put("NAME", name);
            JS_RegionObj.put("CAVALRY_AMOUNT", regions[i].getCavalryAmount());
            JS_RegionObj.put("ARTILLERY_AMOUNT", regions[i].getArtilleryAmount());
            JS_RegionObj.put("INFANTRY_AMOUNT", regions[i].getInfantryAmount());
            JS_RegionObj.put("TOTAL_ARMY_FORCE", regions[i].totalArmyForce());
            JS_RegionObj.put("PLAYER_BELONG_TO", regions[i].playerBelongTo.getId());
            JS_RegionArray.put(JS_RegionObj);
        }
        gameStats.put("REGIONS", JS_RegionArray); // To get the regions first get "REGIONS" then iterate through the regions array
        
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

    @WebMethod
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

    @WebMethod 
    public boolean checkGameCode(String code){ 
        return (Integer.toString(roomID).equals(code)); 
    }
    
    @WebMethod 
    public String getGameCode(){ 
        String s;
        s = Integer.toString(roomID);
        return s;
    }

    @WebMethod
    public boolean isRoomFull() {
        return player_map.size() == playerNumber;
    }

    @WebMethod
    public int getSoldier(String nm) {
        Region r = findRegion(nm);
        return r.totalArmyForce();
    }

    @WebMethod
    public void setSoldier(String nm, int army) {
        Region r = findRegion(nm);
        r.setArmies(army);
    }

    @WebMethod
    public boolean resignRequest(Integer userId) {
        Player p = player_map.get(userId);
        if(p == null)
            return false;
        
        int[] player_regions = p.getRegionsAndDelete();
        int count = 0;
        
        while (player_regions[count] != -1) {
            Region r = regions[player_regions[count]];
            r.setArmies(1);
            r.setPlayer(null);            
            count++;
        }
        player_map.remove(userId);
        
        System.out.println("Player " + p.getName() + " has resigned. So deleted from player map");
        return true;        
    }

    @WebMethod
    public String getWinner() {
        return winner.getName();
    }

    @WebMethod
    public boolean nextTurn() {
        onceInTurn = true;
        return true;
    }

    @WebMethod
    public HashMap getCards() {
        return currentPlayer.getHand();
    }

    @WebMethod
    public int getSoldierWaiting() {
        return currentPlayer.armyToGain();
    }

    public void resetGame() {

    }

    @WebMethod
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    
    @WebMethod
    public void changeCurrentPlayer(){
        int nextPlayerId = currentPlayer.getId()+1;
        if(nextPlayerId >= player_map.size())
            currentPlayer = player_map.get(0);
        else
            currentPlayer = player_map.get(currentPlayer.getId()+1);
    }
    
    @WebMethod
    public int getArmyOf(String n){
        Region r = findRegion(n);
        return r.totalArmyForce();
    }
    
    @WebMethod
    public int getArmyOfRegion(Region r){
        return r.totalArmyForce();
    }
}
