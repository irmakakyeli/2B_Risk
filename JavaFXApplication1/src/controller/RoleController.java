package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafxapplication1.GameManager;
import view.ViewFactory;

public class RoleController extends BaseController {

    @FXML
    private Label roleLabel;

    public RoleController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
    }


    @FXML
    void helpBtnAction() {
        viewFactory.showHelp();
        Stage stage = (Stage) roleLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void hostBtnAction() {
        viewFactory.showHostPage();
        Stage stage = (Stage) roleLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void joinBtnAction() {
        viewFactory.showJoinPage();
        Stage stage = (Stage) roleLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}


