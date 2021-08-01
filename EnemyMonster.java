/*  
*   EnemyMonster class
*
*   This class extends Monster, it is used to handle all logic for the monsters that the player fights.
*
*   An Enum is created to keep track of the multiple monsters a player can fight.
*
*   This class contains logic for the attacks that all basic enemies can perform.
*       Each type has 2 moves, a normal attack and a special attack.
*   
*
*   Author: Isaac Perez
*   Date First Created: 7-24-2021
*
*/

public class EnemyMonster extends Monster{
    
    public enum BasicEnemies
    {
        WASP, //low health, high damage, low defense, low crit, high dodge - % health damage special

        TOAD, //medium health, low damage, medium defense, high crit, low dodge - increase damage special

        CAT, //medium every stat, but high dodge - increase dodge special

        ELEPHANT, //high health, medium damage, high defense, no crit, no dodge - lower defense special

        SNAIL, //low health, low damage, very high defense, low crit, no dodge - poison special

        CROCODILE, //med health, medium damage, medium dodge, low crit, low dodge - increase crit chance special

        RHINO; //high health, high damage, low defense, low crit, no dodge - bleeding special
    }


    private BasicEnemies enemyType;

    public EnemyMonster(String n, float h, float dam, int crit, float def, int dod, int att, BasicEnemies t)
    {
        super(n, h, dam, crit, def, dod, att); // don't need choice, a basic monster has 2 attacks
        enemyType = t;
    }

    public BasicEnemies getEnemyType() {return enemyType;}


    @Override
    public void attack(int attackNumber, Monster target)
    {
        //the integer passed to this method is the attack, 1-2, the enemy is going to do
        //whenever it is an enemies turn to attack, the number (1 or 2), of that attack will be sent here

        //the target is the monster that will defend against the attack...

        if(enemyType == BasicEnemies.WASP) 
        {
 


            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1:
                {

                }
                case 2:
                {

                }
                
            }


        }
        else if( enemyType == BasicEnemies.TOAD)
        {



            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1:
                {

                }
                case 2:
                {

                }

            }

        }
        else if(enemyType == BasicEnemies.CAT)
        {




            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1:
                {

                }
                case 2:
                {

                }

            }
        }
        else if(enemyType == BasicEnemies.ELEPHANT)
        {




            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1:
                {

                }
                case 2:
                {

                }

            }
        }
        else if(enemyType == BasicEnemies.SNAIL)
        {




            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1:
                {

                }
                case 2:
                {

                }
            }
        }

        else if(enemyType == BasicEnemies.CROCODILE)
        {




            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1:
                {

                }
                case 2:
                {

                }
            }
        }

        else if(enemyType == BasicEnemies.RHINO)
        {




            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1:
                {

                }
                case 2:
                {

                }
            }
        }

        
        
    }
}
