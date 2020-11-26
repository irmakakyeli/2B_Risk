/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bilkent.cs.client;

/**
 *
 * @author nedim.alpdemir
 */
public class TestClientConsole {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        GameEngineService service = new GameEngineService();
        GameEngine gmEngine = service.getGameEnginePort();
         
        String response = gmEngine.getPLayers();
        System.out.println(response);
        
        gmEngine.registerPLayer("Mahmut Nedim Alpdemir");
        gmEngine.registerPLayer("Ibrahim Alpdemir");
        System.out.println(gmEngine.getPLayers());
        gmEngine.activatePLayer(1);
        gmEngine.activatePLayer(2);
        System.out.println(gmEngine.getPLayers());
    }
    
}
