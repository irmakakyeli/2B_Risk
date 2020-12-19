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
public class Manual implements Distribution{
    
    int playerNo, regionCount;

    int[][] distribution = new int[regionCount][playerNo];
    public Manual(int playerNo, int regionCount) {
        this.playerNo = playerNo;
        this.regionCount = regionCount;
        
        distribution = new int[regionCount][playerNo];
   
    }
    
    @Override
    public void distribution()
    {
        // no need for anything, already zero.
    }
    
    @Override
    public int[][] getDistribution() {
        return distribution;
    }
}
