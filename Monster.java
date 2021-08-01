/*  
*   Monster abstract class
*
*   This class is the super class for all monster objects.
*   It contains all of the abstract methods that all sub class monsters will override to function correctly.
*   
*   3 subclasses will be created from this class, a player monster will level up, gaining stats and a new ability per fight.
*   An enemy monster will only be able to use 2 attacks, a regular attack, and a special, each monster type as a speciality stat, such as dodge.
*   A boss monster will always be the same, huge stats, and super strong attacks,
*
*   Author: Isaac Perez
*   Date First Created: 7-23-2021
*
*
*   Editor: Egor Mikhaylov
*
*/
import java.util.Random;
import java.util.Vector;

public abstract class Monster {

    //all stats that deal with chance, such as dodge, are integers
    //a random number is generated from 0-100, if that random number is less than
    //the stat, then the roll succeeds.


    private String name;
    private float health;
    private float maxHealth;
    private float damage;
    private int critChance;
    private float defense;
    private int dodge;

    private int attacks; //holds the number of attacks that the monster can do
                        //goes from 1 - 4 for players and the boss, 1-2 for basic enemies, the monster attack method includes this as a parameter

    private boolean alive; //true for alive, false for dead

    private Vector<Effect> effectsVector; //this vector holds all active effects for this monster

    //setters for monster name and stats
    final void setName(String n) { name = n;}
    final void setHealth(float h) {health = h;}
    final void setMaxHealth(float h){maxHealth = h;}
    final void setDamage(float d) {damage = d;}
    final void setCritChance(int c) {critChance = c;}
    final void setDefense(float d) {defense = d;}
    final void setDodge(int d) {dodge = d;}

    final void setAttacks(int a){attacks = a;}

    final void setAlive(boolean a){ alive = a; }

    //getters for monster name and stats
    final String getName(){return name;}
    final float getHealth(){return health;}
    final float getMaxHealth(){return maxHealth;}
    final float getDamage(){return damage;}
    final int getCritChance(){return critChance;}
    final float getDefense(){return defense;}
    final int getDodge(){return dodge;}

    final int getAttacks(){return attacks;}

    final boolean getAlive(){return alive;}

    final Vector<Effect> getEffectsVector(){return effectsVector;}


    //Monster superclass constructor
    public Monster(String n, float h, float dam, int crit, float def, int dod, int att){
        name = n;
        health = h;
        maxHealth = h;
        damage = dam;
        critChance = crit;
        defense = def;
        dodge = dod;
        attacks = att;

        effectsVector.clear();

        alive = true;
    }



    public abstract void attack(int attackNumber, Monster target, Monster self); //a monster overrides this function for their attacks





    final void defend(float attackDamage)
    {   //all monsters process attacks the same way, the damage is calculated first, then passed to this function
        //this function first rolls the dodge chance, if it succeeds then no damage is dealt, if not it get processed further
        //then the damage is reduced based on the defense rating of the monster
        //a monsters defense 

        Random random = new Random();
        //random interger from 0-100, random.nextInt(101);

        boolean monsterDodged = false;

        if(random.nextInt(101) <= dodge)
        {
            monsterDodged = true;
        }

        if(monsterDodged == false) //failed to dodge
        {
            if(attackDamage - defense < 1.0)
            {
                health -= 1.0; //if defense negates damage, then 1 damage is dealt
            }
            else
            {
                health -= (attackDamage - defense);
            }
        }


        //check if health <= 0, if it is, then that monster is dead, change alive boolean to false
        //if a player monster dies, then the game has to restart
        //if a basic enemy dies, then the player wins the fight, levels up, and fights next opponent
        //if a boss enemy dies, then the player wins and the game restarts

        if(health <= 0.0)
        {
            alive = false;
        }


    }


    //only call this method at the end of a turn, and only call this once per monster in a fight
    public void applyEffects()
    {
        //go through all effect on this monster and apply them
        
        for(Effect e : effectsVector) 
        {
            e.applyEffect();
        }


        //       use this to add a new effect to the effectVector vector

        //effectsVector.add(new Effect(Effect.EffectType.ATTACK2_COOLDOWN, 2));

        
        //go through all effects and if duration == 0, remove it from the vector
        for(Effect e : effectsVector) 
        {
            if(e.getDuration() == 0)
            {
                effectsVector.remove(e);
            }
        }
        
    }



}
