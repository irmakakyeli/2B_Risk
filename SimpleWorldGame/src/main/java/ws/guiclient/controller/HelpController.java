package ws.guiclient.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import ws.guiclient.GameManager;
import ws.guiclient.view.ViewFactory;

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
