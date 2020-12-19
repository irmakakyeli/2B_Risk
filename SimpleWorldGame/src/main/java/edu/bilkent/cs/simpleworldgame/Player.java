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
         ConcurrentSkipListSet assigned_Regions;
	 AtomicInteger score;
         boolean isActive, isWinner;
         GameEngine engine;
         Dice dice;
         AttackStrategy strategy;
	
	 public Player(Integer pid) {
		id = pid;
                isActive = false;
		assigned_Regions = new ConcurrentSkipListSet<Integer>();
		name = "Player-" + id.toString();
                engine = new GameEngine();
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
        
        public boolean attack(String nmattack, String nmdef)
        {
            Region attacking, defending;
            attacking = engine.findRegion(nmattack);
            defending = engine.findRegion(nmdef);
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
        
        public void reinforcement ( String nminitial, String nmfinalregion, int armyNumber)
        {
            Region initial, finalregion;
            initial = engine.findRegion(nminitial);
            finalregion = engine.findRegion(nmfinalregion);
            
            int army1, army2;
            army1 = initial.totalArmyForce();
            army2 = finalregion.totalArmyForce();
            
            army1 -= armyNumber;
            army2 += armyNumber;
            
            initial.setArmies(army1);
            finalregion.setArmies(army2);
            
        }
        
        public void fortification(String nmcountry, int armyNumber)
        {
            Region gcountry = engine.findRegion(nmcountry);
            int army;
            army = gcountry.totalArmyForce();
            army += armyNumber;
            gcountry.setArmies(army);
        }
        
        public void cartIntegration()
        {
            
        }
        
        public boolean resign()
        {
           return true;
        }
}
