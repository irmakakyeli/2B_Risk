package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafxapplication1.GameManager;
import view.ViewFactory;

public class JoinController extends BaseController{

    @FXML
    private Label joinLabel;

    public JoinController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
    }

    @FXML
    void getUsersBtnAction() {
        viewFactory.showUsers();
        Stage stage = (Stage) joinLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
    
    
    @FXML
    void backBtnAction() {
        viewFactory.showRole();
        Stage stage = (Stage) joinLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void forwardBtnAction() {
        viewFactory.showRoomPage();
        Stage stage = (Stage) joinLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void helpBtnAction() {

        viewFactory.showHelp();
        Stage stage = (Stage) joinLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}
