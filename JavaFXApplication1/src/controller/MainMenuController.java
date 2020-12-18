package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import view.ViewFactory;

public class MainMenuController extends BaseController{

    @FXML
    private Label mainLabel;

    public MainMenuController( ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    public void playBtnAction() {
        viewFactory.showRole();
        Stage stage = (Stage) mainLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    public void quitBtnAction() {
        Stage stage = (Stage) mainLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void helpBtnAction() {
        viewFactory.showHelp();
        Stage stage = (Stage) mainLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}
