/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bilkent.cs.simpleworldgame;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.ConcurrentSkipListSet;
/**
 *
 * @author
 */
public class Player  {
	 Integer id;
	 String name;
         ConcurrentSkipListSet assigned_Regions;
	 AtomicInteger score;
         boolean isActive;
         GameEngine engine;
         Dice dice;
	
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
            
            int army1, army2, result, adv;
            army1 = attacking.totalArmyForce();
            army2 = defending.totalArmyForce();
            boolean done = false;
            
            if(defending.isCapital)
                adv = 2;
            else 
                adv = 0;
            
            while(!done)
            {   
                result = dice.Roll(army1, army2, adv);
                if (result > 0)
                {
                    army2 -= result; 
                    if(army2 <= 0)
                    {
                        army2 = 0;
                        done = true;
                        defending.setArmies(0);
                    }
                    else
                        defending.setArmies(army2);
                }
                if (result < 0)
                {
                    army1 -= result; 
                    if(army1 <= 1)
                    {
                        army1 = 1;
                        done = true;
                        attacking.setArmies(1);
                    }
                    else 
                        attacking.setArmies(army1);
                }
            }
            
            if(army1 > 1)
                return true;
            return false;
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
