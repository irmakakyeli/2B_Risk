package controller;

import java.util.Iterator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.json.JSONObject; 
//import ws.client.*;
import edu.bilkent.cs.simpleworldgame.*;
import view.ViewFactory;

public class JoinController extends BaseController{

    @FXML
    private Label joinLabel;

    @FXML
    private TextField userName;

    @FXML
    private TextField gameCode;
    
    @FXML
    private Rectangle rectangle;

    @FXML
    private Label label;

    @FXML
    private Button ok;

    public JoinController(  GameEngine game, ViewFactory viewFactory, String fxmlName) {
        super( game, viewFactory, fxmlName);
    }

    
    
    @FXML
    void backBtnAction() {
        viewFactory.showRole();
        Stage stage = (Stage) joinLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    int isNameValid(String name){
        if(name.length() > 14 ) {
            return 0;
        } else if(name.length() <= 0) {
            return -1;
        }
        return 1;
    }

    @FXML
    void forwardBtnAction() {

        // Check the validity of userName
        if( isNameValid(userName.getText()) == 0){
           label.setText("Please enter a user name under 14 characters!");
           makeAppear();
        }
        else if(isNameValid(userName.getText()) == (-1)){
            label.setText("Please enter a user name!");
            makeAppear();
        }

        // Store data and join the game
        Integer userId = game.joinGame(Integer.parseInt(gameCode.getText()), userName.getText());        
        this.userNameString = userName.getText();
        this.userIdInteger = userId;
        boolean isValid = game.checkGameCode(gameCode.getText());
        boolean isFull = game.isRoomFull();

        // Control validity
        if(isValid && ! isFull) {
            viewFactory.showRoomPage(userNameString, userIdInteger);
            Stage stage = (Stage) joinLabel.getScene().getWindow();
            viewFactory.closeStage(stage);
        }
        else if(! isValid){
            label.setText("Game Code is not valid!");
            makeAppear();
        }
        else{
            label.setText("The room is full, sorry, next time!");
            makeAppear();
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
        Stage stage = (Stage) joinLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}
