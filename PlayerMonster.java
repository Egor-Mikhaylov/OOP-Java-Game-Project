public class PlayerMonster extends Monster{

    int monsterChoice; //monsterChoice can be either 1, 2, or 3 corresponding to the monster chosen at the start of the game

    public PlayerMonster(String n, float h, float dam, int crit, float def, int dod, int att, int choice)
    {
        super(n, h, dam, crit, def, dod, att);
        monsterChoice = choice;
    }


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

        if(monsterChoice == 1) 
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
        else if( monsterChoice == 2)
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
        else if(monsterChoice == 3)
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
