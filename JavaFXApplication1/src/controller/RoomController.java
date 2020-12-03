package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafxapplication1.GameManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import view.ViewFactory;

public class RoomController extends BaseController{
    public RoomController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
    }

    @FXML
    private Label roomLabel;
    
    @FXML public static TextArea userList;

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
