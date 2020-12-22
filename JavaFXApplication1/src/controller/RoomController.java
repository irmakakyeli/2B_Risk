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
import ws.client.GameEngine;

public class RoomController extends BaseController{
    public RoomController(  GameEngine game, ViewFactory viewFactory, String fxmlName) {
        super( game, viewFactory, fxmlName);

        roomLabel.setText(game.getGameCode());
        user1.setText(game.getUser(0));
        user2.setText(game.getUser(1));
        user3.setText(game.getUser(2));
        user4.setText(game.getUser(3));
        user5.setText(game.getUser(4));
        user6.setText(game.getUser(5));
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
        viewFactory.showBoard();
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
