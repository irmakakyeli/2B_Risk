package controller;
import static controller.Mod.ATTACK;
import static controller.Mod.FORTIFICATION;
import static controller.Mod.REINFORCEMENT;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.ViewFactory;
import ws.client.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;



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
    private ImageView confetti, crown, wreath, shadow;

    @FXML
    private Label winnerName, playerTurn;

    @FXML
    private Button passBtn;

    @FXML
    private Rectangle rect;
    
    @FXML
    private Rectangle rect2, barRect;

    @FXML
    private Circle circle;

    @FXML
    private Button cardsBtn1, cardBtn2;
    
    @FXML
    private Button decreaseBtn, increaseBtn;

    @FXML
    private Label number;

    @FXML
    private ImageView iw;
    
    @FXML
    Button ok2;
    
    @FXML
    private ImageView tick;

    @FXML
    private Circle winnerCircle;

    @FXML
    private ImageView minion;

    @FXML
    private Label victory;
    
     @FXML
    private ImageView whiteFlag;
     
      @FXML
    private Rectangle rectangle;

    @FXML
    private Label label;

    @FXML
    private Button no, yes;
    
    @FXML
    private ImageView attack;

    @FXML
    private ImageView reinfo;

    @FXML
    private ImageView fortific;


    Mod action = FORTIFICATION;
    boolean act = false;
    
    @FXML
    private Label Alaska, WesternAmerica,CentralAmerica, EasternUS, Greenland, NorthWest,
            CentralCanada, EasternCanada, WesternUS, Argentina, Brazil, Peru, Venezuela, Colombia, Bolivia, UnitedKingdom,
            Iceland, Germany,Skandinavia, SouthernEurope, Russia, Spain, France, Italia, Ukraine, Afghanistan,
            China, India, Irkutsk, Japan, Kamchatka, MiddleEast, Mongolia, Sian, Siberia, Ural, Yakutsk, Congo,
            EastAfrica, Egypt, Madagaskar, NorthAfrica,SouthAfrica, EasternAustralia, Indonesia,NewGuinea,WesternAustralia;

   
    GameEngine game; // TODO
    Label[] labels;
    VBox layout= new VBox(10);
    Scene scene1= new Scene(layout, 300, 250);
    Stage popupwindow=new Stage();
    
    private void initialize(){
        
        rect2.setOpacity(0.0);
        decreaseBtn.setOpacity(0.0);
        increaseBtn.setOpacity(0.0);
        number.setOpacity(0.0);
        File file = new File("css/resources/arrowForAmount.png");
        Image image = new Image(file.toURI().toString());
        iw.setImage(image);
        iw.setOpacity(0.0);

        confetti.setVisible(false);
        crown.setVisible(false);
        wreath.setVisible(false);
        shadow.setVisible(false);
        winnerName.setVisible(false);
        tick.setVisible(false);
        minion.setVisible(false);
        victory.setVisible(false);
        winnerCircle.setVisible(false);
        ok2.setVisible(false);
        ok2.setDisable(true);
        
        rect.setOpacity(0.0);
        circle.setOpacity(0.0);
        barRect.setOpacity(0.0);
        passBtn.setVisible(false);
        passBtn.setDisable(true);

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
                game.activatePLayer().integrate(); 
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
                fortific.setVisible(true);
                rect.setHeight(rect.getHeight() + 35);
                circle.setCenterY(circle.getCenterY() + 35);
                action = ATTACK;
                break;
            case ATTACK:
                attack.setVisible(true);
                rect.setHeight(rect.getHeight() + 35);
                circle.setCenterY(circle.getCenterY() + 35);
                action = REINFORCEMENT;
                break;
            case REINFORCEMENT:
                reinfo.setVisible(true);
                rect.setHeight(rect.getHeight() - 70);
                circle.setCenterY(circle.getCenterY() - 70);
                action = FORTIFICATION;         
                break;
        }

    }
     
   @FXML
    void RegionBtnAction(MouseEvent event) throws IOException{

        //String region = game.tellRegion((int) event.getX(), (int) event.getY());
        String region = ((Button)event.getSource()).getText();

        if(region == null)
            return;

        game.setSelectedRegion(region);

        switch(action){
            case FORTIFICATION:
                if (game.getSelectedRegion1() != null) {
                    if(game.IsDistributionOver())
                        changeTheVisibility();
                    if(act || ! game.IsDistributionOver()) {
                        if(game.IsDistributionOver())
                            game.getCurrentPlayer().fortification(game.getSelectedRegion1(), number.getText());
                        else
                            game.getCurrentPlayer().fortification(game.getSelectedRegion1(), 1);
                        updateMap(game.getSelectedRegion1());
                        updateMap(game.getSelectedRegion2());
                        game.setSelectedRegion1(null);
                        game.setSelectedRegion2(null);
                        if(game.IsDistributionOver())
                            changeTheVisibility();
                        act = false;
                    }

                    if(game.IsDistributionOver()){
                        rect.setOpacity(1.0);
                        circle.setOpacity(1.0);
                        barRect.setOpacity(1.0);
                        passBtn.setVisible(true);
                        passBtn.setDisable(false);
                    }
                    fortific.setVisible(false);
                }
                
                break;
            case ATTACK:
                if (game.getSelectedRegion1() != null && game.getSelectedRegion2() != null) {
                    game.getCurrentPlayer().attack(game.getSelectedRegion1(), game.getSelectedRegion2());
                    updateMap(game.getSelectedRegion1());
                    updateMap(game.getSelectedRegion2());
                    game.setSelectedRegion1(null);
                    game.setSelectedRegion2(null);
                    attack.setVisible(false);
                    if( game.isGameOver())
                        finishTheGame();
                }
                break;
            case REINFORCEMENT:
                if (game.getSelectedRegion1() != null && game.getSelectedRegion2() != null) {
                    changeTheVisibility();
                    if(act) {
                        game.getCurrentPlayer().reinforcement(game.getSelectedRegion1(),
                                game.getSelectedRegion2(), number.getText());
                        updateMap(game.getSelectedRegion1());
                        updateMap(game.getSelectedRegion2());
                        game.setSelectedRegion1(null);
                        game.setSelectedRegion2(null);
                        game.changeCurrentPlayer();
                        changeTheVisibility();
                        showPlayerChange();
                        act = false;
                    }
                    reinfo.setVisible(false);
                }
                break;
        }
    }

    public void showPlayerChange(){
        playerTurn.setText(game.getCurrentPlayer() + " is playing");
    }

    public void finishTheGame(){

        confetti.setVisible(true);
        crown.setVisible(true);
        wreath.setVisible(true);
        shadow.setVisible(true);
        winnerName.setVisible(true);
        tick.setVisible(true);
        minion.setVisible(true);
        victory.setVisible(true);
        winnerCircle.setVisible(true);
        ok2.setVisible(true);
        ok2.setDisable(false);

        // Close the stage
        Stage stage = (Stage) number.getScene().getWindow();
        viewFactory.showMainMenu();
    }

    private void updateMap(String region) {
        // TODO
    }

    private void updateLabel(String region) {
        int index = 0;
        int i = 0;
        for( i = 0; i < 47; i++) {
            if(labels[i].equals(getRidOfBlanks(region))) {
                index = i;
                break;
            }
        }
        if(i != 47) 
        labels[index].setText(game.getArmyOf(region));
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

        int compare = 0;

        if(action == Mod.REINFORCEMENT)
            compare = game.getSoldierOfRegion2();

        if(action == Mod.FORTIFICATION)
            compare = game.getSoldierWaiting();

        if(Integer.parseInt(number.getText()) < compare) {
            int i = Integer.parseInt(number.getText()) + 1;
            number.setText(String.valueOf(i));
        }
    }

    @FXML
    void decreaseBtnAction() {
        if(Integer.parseInt(number.getText())> 0) {
           int i = Integer.parseInt(number.getText()) -1;
           number.setText(String.valueOf(i));
        }
    }

    @FXML
    void okBtnAction() {
        act = true;
    }

    @FXML
    void resignBtnAction() {
       
       label.setText("Are you sure to resign?");
       makeAppear();
    }

    private void changeTheVisibility(){
        if(rect2.getOpacity() > 0)
            rect2.setOpacity(1.0);
        else
            rect2.setOpacity(0.0);

        if(number.getOpacity() > 0)
            number.setOpacity(1.0);
        else
            number.setOpacity(0.0);

        if(iw.getOpacity() > 0)
            iw.setOpacity(1.0);
        else
            iw.setOpacity(0.0);

        if(increaseBtn.isVisible())
            increaseBtn.setVisible(false);
        else
            increaseBtn.setVisible(true);

        if(decreaseBtn.isVisible())
            decreaseBtn.setVisible(false);
        else
            decreaseBtn.setVisible(true);

        if(increaseBtn.isDisable())
            increaseBtn.setDisable(false);
        else
            increaseBtn.setDisable(true);

        if(decreaseBtn.isDisable())
            decreaseBtn.setDisable(false);
        else
            decreaseBtn.setDisable(true);
    }
    
     @FXML
    void ok2BtnAction(ActionEvent event) {
        
        viewFactory.showMainMenu();
        Stage stage = (Stage) number.getScene().getWindow();
        viewFactory.closeStage(stage);
    } 
    
    @FXML
    void noBtnAction(ActionEvent event) {
        
       rectangle.setVisible(false);
        label.setVisible(false);
        no.setVisible(false);
        no.setDisable(true);
         yes.setVisible(false);
        yes.setDisable(true);
    }
    
     @FXML
    void yesBtnAction(ActionEvent event) {
        
        // Aler the resign for the database
        game.resignRequest(true);
       viewFactory.showMainMenu();
        Stage stage = (Stage) number.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
   
    
    void makeAppear() {
        rectangle.setVisible(true);
        label.setVisible(true);
        no.setVisible(true);
        no.setDisable(false);
        yes.setVisible(true);
        yes.setDisable(false);
        
    }

    @FXML
    void mouseEnteredRegion(final ActionEvent event) {
        
    popupwindow.initModality(Modality.APPLICATION_MODAL);
    //popupwindow.setTitle("This is a pop up window");
      
    Label label1= new Label(((Button)event.getSource()).getText());
     
    Label label2= new Label("Belongs to: -Player-");
    
    //TODO: Capital
    Label label3 = new Label("");
    layout.getChildren().addAll(label1, label2, label3);
      
    layout.setAlignment(Pos.CENTER);
       
    popupwindow.setScene(scene1);
      
    popupwindow.showAndWait();
        
    }
    
     @FXML
    void mouseExitedRegion(final ActionEvent event) {
    
         popupwindow.close();
    }
}
