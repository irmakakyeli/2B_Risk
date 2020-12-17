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
         boolean isActive;
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
        
        public boolean attack(int gcontinent, int acountry, int dcountry)
        {
            Continent continent = engine.getContinent(gcontinent);
            Region attacking = continent.getCountry(acountry);
            Region defending = continent.getCountry(dcountry);
            int adv;
            
            if(defending.isCapital)
            {
                strategy = new DisadvantageousAttack();
                return strategy.attack(attacking, defending);
            } 
            else if (attacking.isCapital || attacking.isSpecial)
            {
                strategy = new AdvantageousAttack();
                return strategy.attack(attacking, defending);
            }
            else 
            {
                strategy = new NormalAttack();
                return strategy.attack(attacking, defending);
            }
                
        }
        
        public void fortification()
        {
            
        }
        
        public void reinforcement()
        {
            
        }
        
        public void cartIntegration()
        {
            
        }
        
        public void resign()
        {
            
        }
}
