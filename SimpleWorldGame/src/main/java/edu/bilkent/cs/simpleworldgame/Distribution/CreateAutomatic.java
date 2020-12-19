/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bilkent.cs.simpleworldgame.Distribution;

/**
 *
 * @author User
 */
public class CreateAutomatic extends DistributionFactory{
    
    
    
    @Override
    public Distribution createProduct(int playerNo, int regionCount)
    {
        Automatic automatic = new Automatic( playerNo, regionCount);
        return automatic;
    }
}
