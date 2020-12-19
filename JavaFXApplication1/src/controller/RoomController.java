package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import view.ViewFactory;

public class RoomController extends BaseController{
    public RoomController( ViewFactory viewFactory, String fxmlName) {
        super( viewFactory, fxmlName);
    }

    @FXML
    private Label roomLabel;
    
    @FXML
    private TextArea userList;
    
    public TextArea getUserList(){
        return userList;
    }
    
    @FXML
    void backBtnAction() {
        Stage previousStage = viewFactory.getHostOrJoin();
        previousStage.show();
        Stage stage = (Stage) roomLabel.getScene().getWindow();
        stage.close();
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
