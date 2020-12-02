package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.GameManager;
import sample.view.ViewFactory;

public class JoinController extends BaseController{

    @FXML
    private Label joinLabel;

    public JoinController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
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
    }
}
