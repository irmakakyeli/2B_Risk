package controller;

import javafxapplication1.GameManager;
import view.ViewFactory;

public class BoardController extends BaseController{
    public BoardController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
    }
}
