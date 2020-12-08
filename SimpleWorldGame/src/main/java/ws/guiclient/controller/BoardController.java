package ws.guiclient.controller;

import ws.guiclient.GameManager;
import ws.guiclient.view.ViewFactory;

public class BoardController extends BaseController{
    public BoardController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
    }
}
