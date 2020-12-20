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

public class RoomController extends BaseController{
    public RoomController( ViewFactory viewFactory, String fxmlName) {
        super( viewFactory, fxmlName);

        roomLabel.setText(game.getGameCode());
        user1.setText(game.getUserList[0]);
        user2.setText(game.getUserList[1]);
        user3.setText(game.getUserList[2]);
        user4.setText(game.getUserList[3]);
        user5.setText(game.getUserList[4]);
        user6.setText(game.getUserList[5]);
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
