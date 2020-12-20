/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bilkent.cs.simpleworldgame;

import java.util.HashMap;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Card {
    String type;
    HashMap<String, Integer> deck;
    ArrayList<String> cardNames;
    int cavalryCount, infantryCount,artilleryCount;
  
    
    public Card(int totalCardsOfOneKind){
        int cavalryCount = totalCardsOfOneKind;
        int infantryCount  = totalCardsOfOneKind;
        int artilleryCount  = totalCardsOfOneKind;
        createDeck();
        
        //typeDeterminer();
    }
    
    
    /*private void typeDeterminer(){
        double randomNumber = Math.random()*2;//create random double between 0-2
        int randomInt = (int)randomNumber; // turn the random double to int between 0-2
        String soldierType;
        deck2 = new ArrayList();
        deck2.add("Cavalry");
        deck2.add("Infantry");
        deck2.add("knight");
        deck = new HashMap<String, Integer>();
        deck.put("Cavalry  " , cavalryCount);
        deck.put("Infantry ", infantryCount);
        deck.put("Artillery", artilleryCount);
        
        soldierType = deck2.get(randomInt);
        int newSoldierCount;
        newSoldierCount = deck.get(soldierType);
        newSoldierCount--;
        deck.put(soldierType, newSoldierCount);
       
        nameOfCard =  soldierType; 
        
    }*/
    
    private void createDeck( ){
        deck = new HashMap<String, Integer>();
        deck.put("Cavalry  " , cavalryCount);
        deck.put("Infantry ", infantryCount);
        deck.put("Artillery", artilleryCount);
    }
    
    String getRandomCardName(){
        String name = "none";
        cardNames = new ArrayList();
        cardNames.add("Cavalry");
        cardNames.add("Infantry");
        cardNames.add("Artillery");
        double randomNumber = Math.random()*2;//create random double between 0-2
        int randomInt = (int)randomNumber; // turn the random double to int between 0-2
        name = cardNames.get(randomInt); // we have the name to be returned
        
        int newSoldierCount;
        newSoldierCount = deck.get(name);//decrement the
        newSoldierCount--;              // amount of the card by 1 
        deck.put(name, newSoldierCount);// that has the generated name 
        
        
        
        
        return name;
    }
}
