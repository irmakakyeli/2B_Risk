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

public class Player  {
	Integer id;
	String name;
        ConcurrentSkipListSet<Region> assigned_Regions;
	AtomicInteger score;
        boolean isActive, isWinner;
        AttackStrategy strategy;
	Card card;
        HashMap<String,Integer> hand; //player's hand
            
	public Player(Integer pid) {
            id = pid;
            isActive = false;
            assigned_Regions = new ConcurrentSkipListSet<Region>();
            name = "Player-" + id.toString();
            hand = new HashMap<>();//init empty hand as a hashmap,
            card = new Card(20);
	}

	public String getName() {
		return name;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setName(String nm) {
		name =  nm;
	}
        
        public void setActive(boolean act_state) {
		isActive = act_state;
	}
        
        public boolean attack(Region attacking, Region defending, boolean once){
            boolean getTheRegion;
            if(defending.isCapital)
            {
                strategy = new DisadvantageousAttack();
                if(assigned_Regions.size() == 47) {
                    isWinner = true;
                }
                getTheRegion = strategy.attack(attacking, defending);
                if(getTheRegion && once){
                    hand.put(card.getRandomCardName() , hand.size());
                }
                return getTheRegion;
            } 
            else if (attacking.isCapital || attacking.isSpecial)
            {
                strategy = new AdvantageousAttack();
                if(assigned_Regions.size() == 47) {
                    isWinner = true;
                }
                getTheRegion = strategy.attack(attacking, defending);
                if(getTheRegion && once){
                    hand.put(card.getRandomCardName() , hand.size());
                }
                return getTheRegion;
            }
            else 
            {
                strategy = new NormalAttack();
                if(assigned_Regions.size() == 47) {
                    isWinner = true;
                }
                getTheRegion = strategy.attack(attacking, defending);
                if(getTheRegion && once){
                    hand.put(card.getRandomCardName(), hand.size());
                }
                return getTheRegion;
            }      
        }
        
        public void reinforcement ( Region initial, Region finalregion, int armyNumber){
            int army1, army2;
            army1 = initial.totalArmyForce();
            army2 = finalregion.totalArmyForce();
            
            army1 -= armyNumber;
            army2 += armyNumber;
            
            initial.setArmies(army1);
            finalregion.setArmies(army2);
            
        }
        
        public void fortification(Region gcountry, int armyNumber){
            int army;
            army = gcountry.totalArmyForce();
            army += armyNumber;
            gcountry.setArmies(army);
        }
        
        public void removeRegion(Region gcountry){
            assigned_Regions.remove(gcountry);
            gcountry.setPlayer(null);
            gcountry.setArmies(0);
        }
        
        public void addRegion(Region gcountry, int armyNumber){
            gcountry.setArmies(armyNumber);
            gcountry.setPlayer(this);
            assigned_Regions.add(gcountry);
        }
        
        public int cartIntegration()
        {
            int bonus = 0;
            if(hand.get("Artillery")== 3){
                bonus = 4;
            }
            if(hand.get("Infantry")== 3){
                bonus = 3;
            }
            if(hand.get("Cavalry")== 3){
                bonus = 5;
            }
            return bonus;
            
        }
        
        public boolean resign()
        {
           while(!assigned_Regions.isEmpty()){
                Region r = assigned_Regions.first();
                r.setArmies(1);
                r.setPlayer(null);
                assigned_Regions.remove(r);
            }
           return true;
        }
}
