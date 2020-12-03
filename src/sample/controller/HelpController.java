package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import sample.GameManager;
import sample.view.ViewFactory;

public class HelpController extends BaseController{

    @FXML
    private ScrollPane scrollPane;

    public HelpController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
    }

    @FXML
    void backBtnAction() {
        viewFactory.showMainMenu();
        Stage stage = (Stage) scrollPane.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}
