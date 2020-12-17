/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bilkent.cs.simpleworldgame.Attack;

import edu.bilkent.cs.simpleworldgame.Region;

public interface AttackStrategy {
    
    public boolean attack(Region attacking, Region defending);
    
}
