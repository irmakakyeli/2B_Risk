package sample.controller;

import sample.GameManager;
import sample.view.ViewFactory;

public class HelpController extends BaseController{
    public HelpController(GameManager gameManager, ViewFactory viewFactory, String fxmlName) {
        super(gameManager, viewFactory, fxmlName);
    }
}
