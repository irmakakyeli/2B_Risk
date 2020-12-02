package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.GameManager;
import sample.view.ViewFactory;

public class RoomController extends BaseController{
    public RoomController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
    }

    @FXML
    private Label roomLabel;

    @FXML
    void forwardBtnAction() {
        viewFactory.showBoard();
        Stage stage = (Stage) roomLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void helpBtnAction() {
        viewFactory.showHelp();
    }
}
