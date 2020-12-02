package sample.controller;

import sample.GameManager;
import sample.view.ViewFactory;

public class BoardController extends BaseController{
    public BoardController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
    }
}
