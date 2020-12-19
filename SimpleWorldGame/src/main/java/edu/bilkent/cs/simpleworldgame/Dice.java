package edu.bilkent.cs.simpleworldgame;

public class Dice {
    final int FACE = 100;
    int resultattacker, resultdefender, result;

    public Dice()
    {
        resultattacker = 0;
        resultdefender = 0;
    }

    /*
     * @param Attacking Army is the number of troops attacking army has
     * @param Attacked Army is the number of troops defending army has
     * @return can return + or - :
     *                           + means the attacker won and the number is the difference
     *                           - means the defender won and the number is the difference
     * TODO önemli: tur bitene kadar devam edilmeli ve - ye düşmemeli askerler
     * */
    public int Roll(int AttackingArmy, int AttackedArmy)
    {
        resultattacker = (int)(Math.random() * (FACE + 1));
        resultdefender = (int)(Math.random() * (FACE + 1));
        
        if(AttackingArmy - AttackedArmy > 10)
        {
            resultattacker += AttackingArmy;
            if(resultattacker > 100) {
                resultattacker = 100;
            }
        }
        else if (AttackingArmy - AttackedArmy > 5)
        {
            resultattacker += AttackingArmy / 2;
            if(resultattacker > 100) {
                resultattacker = 100;
            }
        }
        else if (AttackedArmy - AttackingArmy > 10)
        {
            resultdefender += AttackedArmy;
            if(resultdefender > 100) {
                resultdefender = 100;
            }
        }
        else if (AttackedArmy - AttackingArmy > 5)
        {
            resultdefender += AttackedArmy / 2;
            if(resultdefender > 100) {
                resultdefender = 100;
            }
        }
        
        if(resultattacker > resultdefender)
        {
            return (resultattacker - resultdefender);
        }
        else
        {
            return -( resultdefender - resultattacker);
        }
    }

}