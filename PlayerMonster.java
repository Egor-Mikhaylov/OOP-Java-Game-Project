/*  
*   PlayerMonster class
*
*   This class extends Monster, it is used to handle all logic for the monster a player controls.
*
*   An Enum is created to keep track of the 3 monsters a player can choose from.
*
*   This class contains logic for leveling up, and for all of the various attacks that a player
*       monster can perform, based on the type of monster it is, 4 total for each type.
*
*   ONLY THE FIRST ATTACK WILL NOT HAVE A COOLDOWN...
*
*   attack 1 is a basic attack that applies a debuff
*   attack 2 is a utility skill , low cooldown
*   attack 3 is another utility skill, medium turn cooldown
*   attack 4 is an ultimate attack, high turn cooldown
*   
*
*   Author: Isaac Perez
*   Date First Created: 7-23-2021
*
*/

public class PlayerMonster extends Monster{

    public enum PlayerMonsterChoices 
    {
        DRAGON,
        VIPER,
        MINOTAUR;
    }


    private PlayerMonsterChoices monsterChoice; //monsterChoice is the monster chosen at the start of the game

    public PlayerMonster(String n, float h, float dam, int crit, float def, int dod, int att, PlayerMonsterChoices choice)
    {
        super(n, h, dam, crit, def, dod, att);
        monsterChoice = choice;
    }


    public PlayerMonsterChoices getMonsterChoice(){return monsterChoice;}


    //this method is called after every won fight, all stats are increased by 25%
    public void levelUp()
    {
        //when a player monster levels up, their health is restored and boosted also

        this.setHealth((float)(this.getMaxHealth() * 1.25)); //cast the result to a float
        this.setMaxHealth(this.getHealth());

        this.setDamage((float)(this.getDamage() * 1.25));
        this.setCritChance((int)(this.getCritChance() * 1.25));
        this.setDefense((float)(this.getDefense() * 1.25));
        this.setDodge((int)(this.getDodge() * 1.25));

    }
    
    @Override
    public void attack(int attackNumber, Monster target)
    {
        //the integer passed to this method is the attack, 1-4, that the player wants to do
        //when an attack button is pressed, whatever number that button is passed here

        //the target is the monster that will defend against the attack...

        if(monsterChoice == PlayerMonsterChoices.DRAGON)
        {
            //attacks for the first player monster type, each case is an attack

            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1:
                {

                }
                case 2:
                {

                }
                case 3:
                {

                }
                case 4:
                {

                }
            }


        }
        else if( monsterChoice == PlayerMonsterChoices.VIPER)
        {

            //attacks for the second player monster type, each case is an attack

            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1:
                {

                }
                case 2:
                {

                }
                case 3:
                {

                }
                case 4:
                {

                }
            }

        }
        else if(monsterChoice == PlayerMonsterChoices.MINOTAUR)
        {


            //attacks for the third player monster type, each case is an attack

            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1:
                {

                }
                case 2:
                {

                }
                case 3:
                {

                }
                case 4:
                {

                }
            }
        }

        
        
    }
}
