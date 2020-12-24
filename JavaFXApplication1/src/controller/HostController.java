package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import view.ViewFactory;
//import ws.client.*;
import edu.bilkent.cs.simpleworldgame.*;
import org.json.JSONObject;

public class HostController extends BaseController{

    @FXML
    private Label hostLabel;

    @FXML
    private TextField userName;
    
    @FXML
    private Rectangle rectangle;

    @FXML
    private Label label;

    @FXML
    private Button ok;
    
    private String distributionMethod = "NOT_DEFINED";

    public HostController(  GameEngine game, ViewFactory viewFactory, String fxmlName) {
        super(game, viewFactory, fxmlName);
    }


    @FXML
    void backBtnAction() {
        viewFactory.showRole();
        Stage stage = (Stage) hostLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    
     int isNameValid(String name){
        if(name.length() > 14 )
            return 0;
        else if(name.length() <= 0)
            return -1;
        return 1;
    }

    @FXML
    void forwardBtnAction() {

        // Check the validity of userName
        if( isNameValid(userName.getText()) == 0){
            label.setText("Please enter a user name under 14 characters");
            makeAppear();
          
        }
        else if(isNameValid(userName.getText()) == (-1)){
            label.setText("Please enter a user name!");
            makeAppear();
        }
        else {            
            if(distributionMethod == "NOT_DEFINED"){
                label.setText("Please choose a distribution method");
                makeAppear();

            } else if(distributionMethod == "MANUAL"){
                game.resetGame();
                String playerAndRoom = game.setupGame(userName.getText(), 3, "MANUAL"); // Host player (currentPlayer) name and id is set here
                JSONObject json =  new JSONObject(playerAndRoom);
                
                // Get player and room id to pass onto other pages
                int hostId = json.getInt("HOST_PLAYER_ID");
                int roomId = json.getInt("ROOM_ID");
                
                // Show the game code        
                label.setText("Game Code: " + roomId);
                //makeAppear();

                String userNametoPass = game.getUserName();

                viewFactory.showRoomPage(userNametoPass, hostId);
                Stage stage = (Stage) hostLabel.getScene().getWindow();
                viewFactory.closeStage(stage);
            } else {
                game.resetGame();
                String playerAndRoom = game.setupGame(userName.getText(), 3, "AUTO"); // Host player (currentPlayer) name and id is set here
                JSONObject json =  new JSONObject(playerAndRoom);
                
                // Get player and room id to pass onto other pages
                int hostId = json.getInt("HOST_PLAYER_ID");
                int roomId = json.getInt("ROOM_ID");
                
                // Show the game code        
                label.setText("Game Code: " + roomId);
                //makeAppear();
                
                String userNametoPass = game.getUserName();

                viewFactory.showRoomPage(userNametoPass, hostId);
                Stage stage = (Stage) hostLabel.getScene().getWindow();
                viewFactory.closeStage(stage);
            }

        }
    }
    
    
     @FXML
    void okBtnAction(ActionEvent event) {
        rectangle.setVisible(false);
        label.setVisible(false);
        ok.setVisible(false);
        ok.setDisable(true);
    }
    
   
    
    void makeAppear() {
        rectangle.setVisible(true);
        label.setVisible(true);
        ok.setVisible(true);
        ok.setDisable(false);
        
    }


    @FXML
    void helpBtnAction() {

        viewFactory.showHelp();
        Stage stage = (Stage) hostLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
    
    @FXML
    void toggleBtnOn() {
        distributionMethod = "AUTO";
    }
    
    @FXML
    void toggleBtnOff() {
        distributionMethod = "MANUAL";
    }
}
