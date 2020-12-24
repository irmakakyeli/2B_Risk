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
import org.json.*;

public class RoomController extends BaseController{
    String allPlayers;
    public RoomController(  GameEngine game, String userName, Integer userId, ViewFactory viewFactory, String fxmlName) {
        super( game, viewFactory, fxmlName);

        this.userNameString = userName;
        this.userIdInteger = userId;
        
        allPlayers = game.getPlayers();
        JSONArray json = new JSONArray(allPlayers);
        for (int i = 0 ; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            String A = obj.getString("name");
            switch(i){
                case 0:
                    user1.setText(A);
                    break;
                case 1:
                    user2.setText(A);
                    break;
                case 2:
                    user3.setText(A);
                    break;
                case 3:
                    user4.setText(A);
                    break;
                case 4:
                    user5.setText(A);
                    break;
                case 5:
                    user6.setText(A);
                    break;
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
