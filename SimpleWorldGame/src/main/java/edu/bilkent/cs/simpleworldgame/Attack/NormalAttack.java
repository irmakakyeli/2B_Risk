/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bilkent.cs.simpleworldgame.Attack;

import edu.bilkent.cs.simpleworldgame.Dice;
import edu.bilkent.cs.simpleworldgame.Region;
import edu.bilkent.cs.simpleworldgame.Region;

public class NormalAttack implements AttackStrategy{

    Dice dice;
    
    public NormalAttack() {
        dice = new Dice();
    }

    @Override
    public boolean attack(Region attacking, Region defending)
    {
        int result;
        int army1, army2;
        boolean done = false;
        army1 = attacking.totalArmyForce();
        army2 = defending.totalArmyForce();
        
        while(!done)
        {   
            result = dice.Roll(army1, army2);
            
            if (result > 0)
            {
                army2 -= result / 10 + 1; 
                if(army2 <= 0)
                {
                    army2 = 0;
                    done = true;
                    defending.setArmies(0);
                }
                else {
                    defending.setArmies(army2);
                }
            }
            if (result < 0)
            {
                army1 -= result / 10 + 1; 
                if(army1 <= 1)
                {
                    army1 = 1;
                    done = true;
                    attacking.setArmies(1);
                }
                else {
                    attacking.setArmies(army1);
                }
            }
        }
            
        return army1 > 1;
    }
    
    
    
    
    
}
