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
    
    int playerNo, regionCount, troopCount;
    
    boolean manualDistributionFinished = false;
    int[][] distribution = new int[regionCount][playerNo];
    public Manual(int playerNo, int regionCount) {
        this.playerNo = playerNo;
        this.regionCount = regionCount;
        
        distribution = new int[regionCount][playerNo];
            switch (playerNo) {
                case 3:
                    troopCount = 35 * playerNo;
                    break;
                case 4:
                    troopCount = 30 * playerNo;
                    break;
                case 5:
                    troopCount = 25 * playerNo;
                    break;
                case 6:
                    troopCount = 20 * playerNo;
                    break;
            }
    }
    
    @Override
    public void distribution()
    {
        
    }
    
    public int[][] getDistribution() {
        return null;
    }
}
