package sample;

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
     * @param advantage shows if either has advantage is 0 for none 1 for attacker advantage and 2 for defender advantage
     * @return can return + or - :
     *                           + means the attacker won and the number is the troops lost by defender
     *                           - means the defender won and the number is the troops lost by the attacker
     *
     * TODO önemli: tur bitene kadar devam edilmeli ve - ye düşmemeli askerler
     * */
    public int Roll(int AttackingArmy, int AttackedArmy, int advantage)
    {
        resultattacker = (int)(Math.random() * (FACE + 1));
        resultdefender = (int)(Math.random() * (FACE + 1));
        if(advantage == 0) //means there is no advantage to either side
        {
            if(resultattacker > resultdefender)
            {
                return (resultattacker - resultdefender) / 10 +1;
            }
            else
            {
                return -(( resultdefender - resultattacker) / 10 +1);
            }
        }
        else if (advantage == 1) //means there is advantage to attacker
        {
            int temp = (int)(Math.random() * (FACE + 1));
            if (temp > resultattacker)
            {
                resultattacker = temp;
            }
            if(resultattacker > resultdefender)
            {
                return (resultattacker - resultdefender) / 10 +1;
            }
            else
            {
                return -(( resultdefender - resultattacker) / 10 +1);
            }
        }
        else if (advantage == 2) //means there is advantage to attacked
        {
            int temp = (int)(Math.random() * (FACE + 1));
            if (temp > resultdefender)
            {
                resultdefender = temp;
            }
            if(resultattacker > resultdefender)
            {
                return (resultattacker - resultdefender) / 10 +1;
            }
            else
            {
                return -(( resultdefender - resultattacker) / 10 +1);
            }
        }
        return 0;
    }

}