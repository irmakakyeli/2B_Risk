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
import java.util.Random;
public class Automatic implements Distribution{
    
     int playerNo, regionCount, troopCount;
        int[][] distribution = new int[regionCount][playerNo];

        public Automatic(int playerNo, int regionCount) {
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
        
        public void distribution()
        {
            Random rand = new Random();
            //phase 1: each player retrieves region with 1 troop
            int turn = 0;
            for (int i = 0; i < regionCount; i++) {
                turn = turn % playerNo;

                int randomRegionIndex = rand.nextInt(regionCount); // 0 <= value < regioncount

                int troopCountInRegion = 1;
                while (troopCountInRegion > 0) {
                    System.out.println("-");
                    troopCountInRegion = 1;
                    for (int a = 0; a < playerNo; a++) {
                        troopCountInRegion += this.distribution[randomRegionIndex % regionCount][a];
                    }
                    if (troopCountInRegion == 1) {
                        troopCountInRegion = 0;
                    } else {
                        randomRegionIndex++;
                    }

                }


                this.distribution[randomRegionIndex % regionCount][turn]++; // put a solider

                turn++;
            }
            troopCount -= regionCount;

            // phase 2: add remaining troops to regions

            while(troopCount > 0) {

                turn = turn % playerNo;


                int randomRegionIndex = rand.nextInt(regionCount);

                while (this.distribution[randomRegionIndex % regionCount][turn] == 0) { // try to find the region of that person.
                    randomRegionIndex++;
                }
                //System.out.print("troop count: " + troopCount);
                this.distribution[randomRegionIndex % regionCount][turn]++; // put a solider
                turn++;
                troopCount--;
            }
        }
        
        
        public int[][] getDistribution() {
            return distribution;
            
        }
        
    }
