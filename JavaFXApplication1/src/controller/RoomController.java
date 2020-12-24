package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import view.ViewFactory;
//import ws.client.*;
import edu.bilkent.cs.simpleworldgame.*;
import org.json.JSONObject;

public class RoomController extends BaseController{
    String allPlayers;
    public RoomController(  GameEngine game, String userName, Integer userId, ViewFactory viewFactory, String fxmlName) {
        super( game, viewFactory, fxmlName);

        this.userNameString = userName;
        this.userIdInteger = userId;
        
        allPlayers = game.getPlayers();
        JSONObject json = new JSONObject(allPlayers);
        if(json.getJSONObject("0") != null){
            JSONObject userObj1 = json.getJSONObject("0");
            user1.setText(userObj1.getString("name"));
            
            if(json.getJSONObject("1") != null){
                JSONObject userObj2 = json.getJSONObject("1");
                user2.setText(userObj1.getString("name"));
                
                if(json.getJSONObject("2") != null){
                    JSONObject userObj3 = json.getJSONObject("2");
                    user3.setText(userObj1.getString("name"));
                    
                    if(json.getJSONObject("3") != null){
                        JSONObject userObj4 = json.getJSONObject("3");
                        user4.setText(userObj1.getString("name"));
                        
                        if(json.getJSONObject("4") != null){
                            JSONObject userObj5 = json.getJSONObject("4");
                            user5.setText(userObj1.getString("name"));
                            
                            if(json.getJSONObject("5") != null){
                                JSONObject userObj6 = json.getJSONObject("5");
                                user6.setText(userObj1.getString("name"));
                            }
                        }
                    }
                }
            }
        }
        
        
        //JSONObject userObj2 = json.getJSONObject("1");               
        
        
        //user2.setText(userObj2.getString("name"));
        // user2.setText(game.getUser(1)); This will not work since it calls a Player object
        roomLabel.setText(game.getGameCode());
    }

    @FXML
    private Label roomLabel;
    
    @FXML
    private Label user1, user2, user3, user4, user5, user6;

    @FXML
    private Button add1, add2, add3;
    
    @FXML
    void backBtnAction() {
        Stage previousStage = viewFactory.getHostOrJoin();
        previousStage.show();
        Stage stage = (Stage) roomLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addButton1Action(){
      add1.setVisible(false);
        add1.setDisable(true);
    }

@FXML
    void addButton2Action(){
      add2.setVisible(false);
        add2.setDisable(true);
    }

@FXML
    void addButton3Action(){
      add3.setVisible(false);
        add3.setDisable(true);
    }
    @FXML
    void forwardBtnAction() {
        viewFactory.showBoard(this.userNameString, this.userIdInteger);
        Stage stage = (Stage) roomLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void helpBtnAction() {
        viewFactory.showHelp();
        Stage stage = (Stage) roomLabel.getScene().getWindow();
        stage.close();
    }

}
