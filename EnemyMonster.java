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
*
*   Editor: Egor Mikhaylov
*
*/

import java.util.Random;

public class EnemyMonster extends Monster{
    
    public enum BasicEnemies
    {
        WASP, //low health, high damage, low defense, low crit, high dodge - % health damage special

        TOAD, //medium health, low damage, medium defense, high crit, low dodge - increase damage special

        CAT, //medium every stat, but high dodge - increase dodge special

        ELEPHANT, //high health, medium damage, high defense, no crit, no dodge - lower defense special

        SNAIL, //low health, low damage, very high defense, low crit, no dodge - poison special

        CROCODILE, //med health, medium damage, medium dodge, low crit, low dodge - increase crit chance special

        RHINO, //high health, high damage, low defense, low crit, no dodge - bleeding special

        GORILLA; //final boss, maxed stats pretty much, all abilities explained in BossMonster class
    }


    private BasicEnemies enemyType;

    public EnemyMonster(String n, float h, float dam, int crit, float def, int dod, int att, BasicEnemies t)
    {
        super(n, h, dam, crit, def, dod, att); // don't need choice, a basic monster has 2 attacks
        enemyType = t;
    }

    public void setEnemyType(BasicEnemies enem){enemyType = enem;}

    public BasicEnemies getEnemyType() {return enemyType;}


    @Override
    public void attack(int attackNumber, Monster target, Monster self)
    {
        //the integer passed to this method is the attack, 1-2, the enemy is going to do
        //whenever it is an enemies turn to attack, the number (1 or 2), of that attack will be sent here

        //the target is the monster that will defend against the attack...

        //Needs Rebalance:
        //(Damage)
        //Very High: 300% dmg
        //High: 150% dmg
        //Med: 100% dmg
        //Low: 50% dmg

        if(enemyType == BasicEnemies.WASP) 
        {
 


            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1: //Normal: Sting (high damage)
                {
                  target.defend(self ,(float) (1.5*self.getDamage())); //"self" is the enemy that is attacking, the attacker
                }
                case 2: //Special: Slash (Medium damage + bleed)
                {
                  self.getEffectsVector().add(new Effect(self, Effect.EffectType.ATTACK2_COOLDOWN, 2));
                  if(!target.defend(self ,(float) (1.0*self.getDamage()))) {
                    target.getEffectsVector().add(new Effect(target, Effect.EffectType.debuffBleed, 6));
                  }
                }
                
            }


        }
        else if( enemyType == BasicEnemies.TOAD)
        {



            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1: //Normal: Croak (low damage)
                {
                  target.defend(self ,(float) (0.5*self.getDamage()));
                  break;
                }
                case 2: //Special: Sudden Attack (50% chance to deal 1x damage, 25% to deal 0x, 20% to deal 1.5x, 5% to deal 3x)
                {
                  self.getEffectsVector().add(new Effect(self, Effect.EffectType.ATTACK2_COOLDOWN, 2));

                  Random random = new Random();
                  int rand = random.nextInt(101);
                  
                  if(rand >= 0 && rand <= 49) {
                    target.defend(self ,(float) (1.0*self.getDamage()));
                  }
                  if(rand >= 50 && rand <= 74) {
                    target.defend(self ,(float) (0.0*self.getDamage()));
                  }
                  if(rand >= 75 && rand <= 95) {
                    target.defend(self ,(float) (1.5*self.getDamage()));
                  }
                  if(rand >= 96 && rand <= 100) {
                    target.defend(self ,(float) (3.0*self.getDamage()));
                  }

                  break;
                }

            }

        }
        else if(enemyType == BasicEnemies.CAT)
        {




            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1: //Normal: Bite (Med DMG)
                {
                  target.defend(self ,(float) (1.0*self.getDamage()));
                  break;
                }
                case 2: //Special: Hide in Shadows (Dodge+)
                {
                  self.getEffectsVector().add(new Effect(self, Effect.EffectType.ATTACK2_COOLDOWN, 2));
                  self.getEffectsVector().add(new Effect(self, Effect.EffectType.buffDodge, 3));
                  break;
                }

            }
        }
        else if(enemyType == BasicEnemies.ELEPHANT)
        {




            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1: //Normal: Charge (Med DMG)
                {
                  target.defend(self ,(float) (1.0*self.getDamage()));
                  break;
                }
                case 2: //Special: Rampage! (Lower Enemy Def for 2 Turns)
                {
                  self.getEffectsVector().add(new Effect(self, Effect.EffectType.ATTACK2_COOLDOWN, 2));
                  target.getEffectsVector().add(new Effect(target, Effect.EffectType.debuffDefense, 2));
                  break;
                }

            }
        }
        else if(enemyType == BasicEnemies.SNAIL)
        {




            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1: //Normal: Fling Slime (Low DMG)
                {
                  target.defend(self ,(float) (0.5*self.getDamage()));
                  break;
                }
                case 2: //Special: Poison Spit (No DMG + Poison effect on Enemy)
                {
                  self.getEffectsVector().add(new Effect(self, Effect.EffectType.ATTACK2_COOLDOWN, 2));
                  if(!target.defend(self ,(float) (0.0*self.getDamage()))) {
                    target.getEffectsVector().add(new Effect(target, Effect.EffectType.debuffPoison, 4));
                  }
                  break;
                }
            }
        }

        else if(enemyType == BasicEnemies.CROCODILE)
        {




            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1: //normal Chomp (med damage)
                {
                  target.defend(self ,(float) (1.0*self.getDamage()));
                  break;
                }
                case 2:// special Enrage (DMG+)
                {
                  self.getEffectsVector().add(new Effect(self, Effect.EffectType.ATTACK2_COOLDOWN, 2));
                  self.getEffectsVector().add(new Effect(self, Effect.EffectType.buffDamage, 3));
                  break;
                }
            }
        }

        else if(enemyType == BasicEnemies.RHINO)
        {




            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1: //normal Pierce (High DMG)
                {
                  target.defend(self ,(float) (1.5*self.getDamage()));
                  break;
                }
                case 2: //special Gore (High DMG, bleed, dodge+)
                {
                if(!target.defend(self ,(float) (1.5*self.getDamage()))) {
                  target.getEffectsVector().add(new Effect(target, Effect.EffectType.debuffBleed, 3));
                }
                self.getEffectsVector().add(new Effect(self, Effect.EffectType.ATTACK2_COOLDOWN, 4));
                self.getEffectsVector().add(new Effect(self, Effect.EffectType.buffDodge, 3));
                  break;
                }
            }
        }

        
        
    }
}
