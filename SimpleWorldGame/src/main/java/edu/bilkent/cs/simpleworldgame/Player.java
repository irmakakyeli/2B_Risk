/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bilkent.cs.simpleworldgame;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.ConcurrentSkipListSet;

import edu.bilkent.cs.simpleworldgame.Attack.*;

public class Player  {
	Integer id;
	String name;
        ConcurrentSkipListSet<Integer> assigned_Regions;
	AtomicInteger score;
        boolean isActive, isWinner;
        Dice dice;
        AttackStrategy strategy;
	
	public Player(Integer pid) {
            id = pid;
            isActive = false;
            assigned_Regions = new ConcurrentSkipListSet<Integer>();
            name = "Player-" + id.toString();
            dice = new Dice();
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
        
        public boolean attack(SimpleRegion attacking, SimpleRegion defending){
            if(defending.isCapital)
            {
                strategy = new DisadvantageousAttack();
                if(assigned_Regions.size() == 47) {
                    isWinner = true;
                }
                return strategy.attack(attacking, defending);
            } 
            else if (attacking.isCapital || attacking.isSpecial)
            {
                strategy = new AdvantageousAttack();
                if(assigned_Regions.size() == 47) {
                    isWinner = true;
                }
                return strategy.attack(attacking, defending);
            }
            else 
            {
                strategy = new NormalAttack();
                if(assigned_Regions.size() == 47) {
                    isWinner = true;
                }
                return strategy.attack(attacking, defending);
            }      
        }
        
        public void reinforcement ( SimpleRegion initial, SimpleRegion finalregion, int armyNumber){
            int army1, army2;
            army1 = initial.totalArmyForce();
            army2 = finalregion.totalArmyForce();
            
            army1 -= armyNumber;
            army2 += armyNumber;
            
            initial.setArmies(army1);
            finalregion.setArmies(army2);
            
        }
        
        public void fortification(SimpleRegion gcountry, int armyNumber){
            int army;
            army = gcountry.totalArmyForce();
            army += armyNumber;
            gcountry.setArmies(army);
        }
        
        public void removeRegion(SimpleRegion gcountry){
            assigned_Regions.remove(gcountry.getId());
            gcountry.setPlayer(null);
            gcountry.setArmies(0);
        }
        
        public void addRegion(SimpleRegion gcountry, int armyNumber){
            gcountry.setArmies(armyNumber);
            //gcountry.setPlayer(this);
            assigned_Regions.add(gcountry.getId());
        }
        
        public void cartIntegration()
        {
            
        }
        
        public boolean resign()
        {
           return true;
        }
}
