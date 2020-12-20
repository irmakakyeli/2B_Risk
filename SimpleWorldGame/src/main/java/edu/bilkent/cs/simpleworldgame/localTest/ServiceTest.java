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
        Map<String, Integer> clientRegionMap = new HashMap<String, Integer>();
        
        //gmEngine.registerPlayer("asd");
        String allRegions = gmEngine.getRegions();
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
        if(setupInfo != "ERROR"){
            JSONObject json =  new JSONObject(setupInfo);
            int hostId = json.getInt("HOST_PLAYER_ID");
            int roomId = json.getInt("ROOM_ID");  
            int player1Id = gmEngine.joinGame(roomId, "Ayse");
            int player2Id = gmEngine.joinGame(roomId, "Fatma");
            int player3Id = gmEngine.joinGame(roomId, "Mehmet");
            
            Integer id = player1Id;
            gmEngine.attackControl(id, clientRegionMap.get("Brazil").toString(), clientRegionMap.get("Peru").toString());
        }            
        
    }
    
}