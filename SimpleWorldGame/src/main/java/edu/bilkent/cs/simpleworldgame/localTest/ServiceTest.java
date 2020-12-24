package edu.bilkent.cs.simpleworldgame.localTest;
import edu.bilkent.cs.simpleworldgame.Attack.*;
import edu.bilkent.cs.simpleworldgame.Distribution.*;
import edu.bilkent.cs.simpleworldgame.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject; 
import org.json.JSONArray;
import org.json.JSONException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 *
 * @author yalpd
 */
public class ServiceTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameEngine gmEngine = new GameEngine();
        // This map holds the associations between region names and ids.
        Map<String, Integer> clientRegionMap = new HashMap<String, Integer>();
                
        
        class MyTimerTask extends TimerTask  {
            GameEngine pEngine;

            //public MyTimerTask(GameEngine pEngine, ) {
            //    this.pEngine = pEngine;
            //}

            @Override
            public void run() {
                if(pEngine.isGameActive() && !pEngine.isGameOver()){ // To pause this, add a isNotPaused button method
                        String currentGameStats = pEngine.getGameStatistics();
                        JSONObject gameStatsJson = new JSONObject(currentGameStats);
                        Integer currentPlayerId = gameStatsJson.getInt("CURRENT_PLAYER_ID");                        
                        System.out.println(currentGameStats);
                }  
            }
        }
 

        
        
        //gmEngine.registerPlayer("asd");
        
        // Get all the regions from the server        
        String allRegions = gmEngine.getRegions();
        // Now we will populate clientRegionMap using the regions obtained from the server
        // each client should use this map to get the region id when a user attacks or fortifies or reinforces.
        JSONArray regions = new JSONArray(allRegions);   
        String list = "";
        String line = "";
        for (int i = 0; i < regions.length(); i++) {
            line = "";
            try {
                JSONObject regionObj = (JSONObject) regions.get(i);                
                String regionName = regionObj.getString("NAME");
                String regionId = regionObj.getString("ID");                
                clientRegionMap.put(regionName, Integer.parseInt(regionId));

            } catch (JSONException ex) {
                System.out.println(ex);
            } 
            list += line;
        }
        
        String setupInfo = gmEngine.setupGame("Yusuf",4, "AUTO");
        
        //MyTimerTask timert = new MyTimerTask(gmEngine);
        //Timer timer = new Timer();
        //timer.schedule(timert, 0, 4000); 
        
        if(setupInfo != "ERROR"){
            JSONObject json =  new JSONObject(setupInfo);
            int hostId = json.getInt("HOST_PLAYER_ID");
            int roomId = json.getInt("ROOM_ID");  
            int player1Id = gmEngine.joinGame(roomId, "Ayse");
            int player2Id = gmEngine.joinGame(roomId, "Fatma");
            int player3Id = gmEngine.joinGame(roomId, "Mehmet");
            
            String allPlayers = gmEngine.getPlayers();
            JSONArray allPlayersJson = new JSONArray(allPlayers);

            //if(allPlayersJson.getJSONObject("0") != null)
            //JSONArray userObj1 = allPlayersJson.JSONArray("0");
            
            //JSONObject userObj2 = allPlayersJson.getJSONObject("1");
            //JSONObject userObj3 = allPlayersJson.getJSONObject("2");
            //JSONObject userObj4 = allPlayersJson.getJSONObject("3");
            //JSONObject userObj5 = allPlayersJson.getJSONObject("4");
            //JSONObject userObj6 = allPlayersJson.getJSONObject("5");
            
            for (int i = 0 ; i < allPlayersJson.length(); i++) {
                JSONObject obj = allPlayersJson.getJSONObject(i);
                String A = obj.getString("name");
                System.out.println(A + " " + i);
            }
            
            gmEngine.resetGame();
            System.out.println(gmEngine.getPlayers());
            
            //System.out.println(userObj1.getString("name"));
            
            Integer firstAttackerPlayerId = player1Id;
            Integer secondAttackerPlayerId = player2Id;
            Integer thirdAttackerPlayerId = player3Id;
            // Now Ayse attacks the region Peru(NOTE: Actually we don't know wether Ayse has Brazil but this should not be an issue in actual GUI)
            System.out.println("Ayse is attacking to Peru from Brazil");
            //gmEngine.attackControl(firstAttackerPlayerId, clientRegionMap.get("Brazil").toString(), clientRegionMap.get("Peru").toString());
            
            //gmEngine.attackControl(secondAttackerPlayerId, clientRegionMap.get("Alaska").toString(), clientRegionMap.get("NorthWest").toString());
            
            //gmEngine.attackControl(thirdAttackerPlayerId, clientRegionMap.get("Spain").toString(), clientRegionMap.get("France").toString());
            
            System.out.println(gmEngine.getCurrentPlayer().getName());
            gmEngine.changeCurrentPlayer();
            System.out.println(gmEngine.getCurrentPlayer().getName());
            gmEngine.changeCurrentPlayer();
            System.out.println(gmEngine.getCurrentPlayer().getName());
            
            if(gmEngine.resignRequest(3))
                System.out.println("Player with id 3 has successfully resigned");
            
            gmEngine.changeCurrentPlayer();
            System.out.println(gmEngine.getCurrentPlayer().getName());
            
            System.out.println(gmEngine.getPlayers());
            
            if(gmEngine.isGameOver()){
                System.out.println("Game Over");
            } else {
                System.out.println("Game ON");
            }
        }                    
              
    }
    
}