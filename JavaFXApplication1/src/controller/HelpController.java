package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import view.ViewFactory;

public class HelpController extends BaseController{

    @FXML
    private ScrollPane scrollPane;

    public HelpController( ViewFactory viewFactory, String fxmlName) {
        super( viewFactory, fxmlName);
    }

    @FXML
    void backBtnAction() {
        Stage previousStage = viewFactory.getCurrentStage();
        previousStage.show();
        Stage stage = (Stage) scrollPane.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}
