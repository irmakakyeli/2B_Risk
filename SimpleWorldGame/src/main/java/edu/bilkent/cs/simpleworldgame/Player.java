/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bilkent.cs.simpleworldgame;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.Iterator;
import java.util.HashMap;

import edu.bilkent.cs.simpleworldgame.Attack.*;

public class Player {
    Integer id;
    String name;
    ConcurrentSkipListSet<Integer> assigned_Regions;
    AtomicInteger score;
    boolean isActive, isWinner;
    AttackStrategy strategy;
    Card card;
    HashMap<String, Integer> hand; // player's hand
    int cCount, aCount, iCount; // soldier cards count
    String colour;

    public Player(Integer pid) {
        id = pid;
        isActive = false;
        isWinner = false;
        assigned_Regions = new ConcurrentSkipListSet<Integer>();
        name = "Player-" + id.toString();
        hand = new HashMap<>();// init empty hand as a hashmap,
        hand.put("Infantry", 0);
        hand.put("Artillery", 0);
        hand.put("Cavalry", 0);
        card = new Card(20);

    }
    
    public String getColor(){
        return colour;
    }
    
    public void setColor(int playerId){
        switch(playerId){
            case 0:
                colour = "#808080";
                break;
            case 1:
                colour = "#809cb6";
                break;
            case 2:
                colour = "#c8b2d3";
                break;
            case 3:
                colour = "#c9f898";
                break;
            case 4:
                colour = "#f6f173";
                break;
            case 5:
                colour = "#f7bd88";
                break;
            case 6:
                colour = "#f34b3f";
                break;
            default:
                colour = "#000000";
                break;
        }
                
    }

    public boolean getIsWinner(){
        return false;
    }
    
    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String nm) {
        name = nm;
    }

    public void setActive(boolean act_state) {
        isActive = act_state;
    }

    public boolean attack(Region attacking, Region defending, boolean once) {
        boolean getTheRegion;
        if (defending.isCapital) {
            strategy = new DisadvantageousAttack();
            if (assigned_Regions.size() == 47) {
                isWinner = true;
            }
            getTheRegion = strategy.attack(attacking, defending);

            if (getTheRegion && once) {
                if (null != card.getRandomCardName())
                    switch (card.getRandomCardName()) {
                        case "Infantry":
                            hand.put(card.getRandomCardName(), iCount++);
                            break;
                        case "Artillery":
                            hand.put(card.getRandomCardName(), aCount++);
                            break;
                        case "Cavalry":
                            hand.put(card.getRandomCardName(), cCount++);
                            break;
                        default:
                            break;
                    }
            }
            return getTheRegion;
        } else if (attacking.isCapital || attacking.isSpecial) {
            strategy = new AdvantageousAttack();
            if (assigned_Regions.size() == 47) {
                isWinner = true;
            }
            getTheRegion = strategy.attack(attacking, defending);
            if (getTheRegion && once) {
                if (null != card.getRandomCardName())
                    switch (card.getRandomCardName()) {
                        case "Infantry":
                            hand.put(card.getRandomCardName(), iCount++);
                            break;
                        case "Artillery":
                            hand.put(card.getRandomCardName(), aCount++);
                            break;
                        case "Cavalry":
                            hand.put(card.getRandomCardName(), cCount++);
                            break;
                        default:
                            break;
                    }
            }
            return getTheRegion;
        } else {
            strategy = new NormalAttack();
            if (assigned_Regions.size() == 47) {
                isWinner = true;
            }
            getTheRegion = strategy.attack(attacking, defending);
            if (getTheRegion && once) {
                if (null != card.getRandomCardName())
                    switch (card.getRandomCardName()) {
                        case "Infantry":
                            hand.put(card.getRandomCardName(), iCount++);
                            break;
                        case "Artillery":
                            hand.put(card.getRandomCardName(), aCount++);
                            break;
                        case "Cavalry":
                            hand.put(card.getRandomCardName(), cCount++);
                            break;
                        default:
                            break;
                    }
            }
            return getTheRegion;
        }
    }

    public void reinforcement(Region initial, Region finalregion, int armyNumber) {
        int army1, army2;
        army1 = initial.totalArmyForce();
        army2 = finalregion.totalArmyForce();

        army1 -= armyNumber;
        army2 += armyNumber;

        initial.setArmies(army1);
        finalregion.setArmies(army2);

    }

    public void fortification(Region gcountry, int armyNumber) {
        int army;
        army = gcountry.totalArmyForce();
        army += armyNumber;
        gcountry.setArmies(army);
    }

    public void removeRegion(Region gcountry) {
        assigned_Regions.remove(gcountry.getId());
        gcountry.setPlayer(null);
        gcountry.setArmies(0);
    }

    public void addRegion(Region gcountry, int armyNumber) {
        gcountry.setArmies(armyNumber);
        gcountry.setPlayer(this);
        assigned_Regions.add(gcountry.getId());
    }

    public int cartIntegration() {
        int bonus = 0;
        if (hand.get("Artillery") == 3) {
            bonus = 4;
        }
        if (hand.get("Infantry") == 3) {
            bonus = 3;
        }
        if (hand.get("Cavalry") == 3) {
            bonus = 5;
        }
        return bonus;

    }

    public boolean resign() {
        while (!assigned_Regions.isEmpty()) {
            Integer region = assigned_Regions.first();                        
            assigned_Regions.remove(region);
        }
        return true;
    }

    public int[] getRegionsAndDelete() {
        Iterator<Integer> iterate = this.assigned_Regions.iterator();
        int[] region_array = new int[47];
        for(int i = 0; i < 47; i++)
            region_array[i] = -1;
        
        // Write every region to the array
        int count = 0;
        while (iterate.hasNext()) { 
            region_array[count] = iterate.next();
            count++;
        }
        // Remove assigned regions
        resign();
        return region_array;
    }
    
    public HashMap getHand() {
        return hand;
    }

    public int armyToGain() {
        return assigned_Regions.size() / 3;
    }

    public int getCavalryAmount() {
        return cCount;
    }
    
    public int getArtilleryAmount(){
        return aCount;
    }
    
    public int getInfantryAmount(){
        return iCount;
    }
    
}
