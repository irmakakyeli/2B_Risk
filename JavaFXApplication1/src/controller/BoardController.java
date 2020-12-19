package controller;
import static controller.Mod.ATTACK;
import static controller.Mod.FORTIFICATION;
import static controller.Mod.REINFORCEMENT;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.ViewFactory;
import ws.client.GameEngine;
import javafx.scene.image.ImageView;


enum Mod {
    ATTACK,
    REINFORCEMENT,
    FORTIFICATION
}

public class BoardController extends BaseController{
    public BoardController( ViewFactory viewFactory, String fxmlName) {
        super( viewFactory, fxmlName);
        initialize();
        
    }

    @FXML
    private Button passBtn;

    @FXML
    private Rectangle rect;
    
    @FXML
    private Rectangle rect2;

    @FXML
    private Circle circle;

    @FXML
    private Button cardsBtn;
    
    @FXML
    private Button decreaseBtn, increaseBtn;

    @FXML
    private Label number;

    @FXML
    private ImageView iw;


    Mod action = FORTIFICATION;
    
    @FXML
    private Label Alaska, WesternAmerica,CentralAmerica, EasternUS, Greenland, NorthWest,
            CentralCanada, EasternCanada, WesternUS, Argentina, Brazil, Peru, Venezuela, Colombia, Bolivia, UnitedKingdom,
            Iceland, Germany,Skandinavia, SouthernEurope, Russia, Spain, France, Italia, Ukraine, Afghanistan,
            China, India, Irkutsk, Japan, Kamchatka, MiddleEast, Mongolia, Sian, Siberia, Ural, Yakutsk, Congo,
            EastAfrica, Egypt, Madagaskar, NorthAfrica,SouthAfrica, EasternAustralia, Indonesia,NewGuinea,WesternAustralia;

   
    GameEngine game; // TODO
    Label[] labels;
    
    private void initialize(){
        
        rect2.setOpacity(0.0);
        decreaseBtn.setOpacity(0.0);
        increaseBtn.setOpacity(0.0);
        number.setOpacity(0.0);
        iw.setOpacity(0.0);

        labels = new Label[47];
        labels[0] = Alaska;
        labels[1] = WesternAmerica;
        labels[2] = CentralAmerica;
        labels[3] = EasternUS;
        labels[4] = Greenland;
        labels[5] = NorthWest;
        labels[6] = CentralCanada;
        labels[7] = EasternCanada;
        labels[8] = WesternUS;
        labels[9] = Argentina;
        labels[10] = Brazil;
        labels[11] = Peru;
        labels[12] = Venezuela;
        labels[13] = Colombia;
        labels[14] = Bolivia;
        labels[15] = UnitedKingdom;
        labels[16] = Iceland;
        labels[17] = Germany;
        labels[18] = Skandinavia;
        labels[19] = SouthernEurope;
        labels[20] = Russia;
        labels[21] = Spain;
        labels[22] = France;
        labels[23] = Italia;
        labels[24] = Ukraine;
        labels[25] = Afghanistan;
        labels[26] = China;
        labels[27] = India;
        labels[28] = Irkutsk;
        labels[29] = Japan;
        labels[30] = Kamchatka;
        labels[31] = MiddleEast;
        labels[32] = Mongolia;
        labels[33] = Sian;
        labels[34] = Siberia;
        labels[35] = Ural;
        labels[36] = Yakutsk;
        labels[37] = Congo;
        labels[38] = EastAfrica;
        labels[39] = Egypt;
        labels[40] = Madagaskar;
        labels[41] = NorthAfrica;
        labels[42] = SouthAfrica;
        labels[43] = EasternAustralia;
        labels[44] = Indonesia;
        labels[45] = NewGuinea;
        labels[46] = WesternAustralia;
    }

    @FXML
    void helpBtnAction() {
        viewFactory.showHelp();
        Stage stage = (Stage) passBtn.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
    @FXML
    void cardsBtnAction(ActionEvent event) {

        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("CardList");

        Label cardsTitle= new Label("CARDS");

        Button integrate = new Button("INTEGRATE");
        integrate.setStyle(" -fx-background-color:  #b80000;");
        integrate.setStyle("-fx-background-radius:  100;");
        integrate.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                game.activatePlayer().integrate(); 
            }
        });

        integrate.setLayoutX(250);
        integrate.setLayoutY(220);

        VBox layout= new VBox(100);

        // TODO -- get cards and show them

        layout.getChildren().addAll(cardsTitle, integrate);
        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 300, 250);
        scene1.setFill(Color.TRANSPARENT);

        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }
    

    @FXML
    void playBtnAction(ActionEvent event) {

        switch(action){
            case FORTIFICATION:
                rect.setHeight(rect.getHeight() + 35);
                circle.setCenterY(circle.getCenterY() + 35);
                action = ATTACK;
                break;
            case ATTACK:
                rect.setHeight(rect.getHeight() + 35);
                circle.setCenterY(circle.getCenterY() + 35);
                action = REINFORCEMENT;
                break;
            case REINFORCEMENT:
                rect.setHeight(rect.getHeight() - 70);
                circle.setCenterY(circle.getCenterY() - 70);
                action = FORTIFICATION;         
                break;
        }

    }
     
    @FXML
    void imagePaneMouseClicked(MouseEvent event) throws IOException{

        String region = game.tellRegion((int) event.getX(), (int) event.getY());

        if(region == null)
            return;

        game.setSelectedRegion(region);

        switch(action){
            case FORTIFICATION:
                if (game.getSelectedRegion1() != null) {
                    rect2.setOpacity(1.0);
                    number.setOpacity(1.0);
                    iw.setOpacity(1.0);

                    increaseBtn.setDisable(false);
                    increaseBtn.setVisible(true);

                    decreaseBtn.setDisable(false);
                    decreaseBtn.setVisible(true);
                    //TODO: LABELDAN SAYI AL GAMEENGİNE E GÖNDER
                    game.getCurrentPlayer().fortification(game.getSelectedRegion1());
                    updateMap(game.getSelectedRegion1());
                    updateMap(game.getSelectedRegion2());
                    game.setSelectedRegion1(null);
                    game.setSelectedRegion2(null);
                    rect2.setOpacity(0.0);
                    number.setOpacity(0.0);
                    iw.setOpacity(0.0);

                    increaseBtn.setDisable(true);
                    increaseBtn.setVisible(false);

                    decreaseBtn.setDisable(true);
                    decreaseBtn.setVisible(false);
                }
                break;
            case ATTACK:
                if (game.getSelectedRegion1() != null && game.getSelectedRegion2() != null) {
                    game.getCurrentPlayer().attack(game.getSelectedRegion1(), game.getSelectedRegion2());
                    updateMap(game.getSelectedRegion1());
                    updateMap(game.getSelectedRegion2());
                    game.setSelectedRegion1(null);
                    game.setSelectedRegion2(null);
                    game.isGameFinished(); // TODO
                }
                break;
            case REINFORCEMENT:
                if (game.getSelectedRegion1() != null && game.getSelectedRegion2() != null) {
                    rect2.setOpacity(1.0);
                    number.setOpacity(1.0);
                    iw.setOpacity(1.0);

                    increaseBtn.setDisable(false);
                    increaseBtn.setVisible(true);

                    decreaseBtn.setDisable(false);
                    decreaseBtn.setVisible(true);
                    //TODO: LABELDAN SAYI AL GAMEENGİNE E GÖNDER
                    game.getCurrentPlayer().reinforcement(game.getSelectedRegion1(), game.getSelectedRegion2());
                    updateMap(game.getSelectedRegion1());
                    updateMap(game.getSelectedRegion2());
                    game.setSelectedRegion1(null);
                    game.setSelectedRegion2(null);
                    game.changeCurrentPlayer(); // TODO
                    rect2.setOpacity(0.0);
                    number.setOpacity(0.0);
                    iw.setOpacity(0.0);

                    increaseBtn.setDisable(true);
                    increaseBtn.setVisible(false);

                    decreaseBtn.setDisable(true);
                    decreaseBtn.setVisible(false);
                }
                break;
            }
        }

    

    private void updateMap(String region) {
        // TODO
    }

    private void updateLabel(String region) {
        int index, i;
        for( i = 0; i < 47; i++) {
            if(labels[i].equals(getRidOfBlanks(region))) {
                index = i;
                break;
            }
        }
        if(i != 47) {
            labels[index].setText(game.getCurrentPlayer().getCountry(region).getTotalArmyForce()); // TODO -- get army number
        }
    }
        
    private String getRidOfBlanks(String region) {
        String r = "";
        for(int i = 0; i < region.length(); i++){
            if(region.charAt(i) == ' ')
                continue;
            r += region.charAt(i);
        }
        return r;
    }
    
    @FXML
    void increaseBtnAction() {
        int i = Integer.parseInt(number.getText()) + 1;
        number.setText(String.valueOf(i));
    }

    @FXML
    void decreaseBtnAction() {
        if(Integer.parseInt(number.getText())> 0) {
           int i = Integer.parseInt(number.getText()) -1;
           number.setText(String.valueOf(i));
        }
    }
}




