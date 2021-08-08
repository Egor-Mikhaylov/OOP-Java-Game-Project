
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
*
*   Editor: Egor Mikhaylov Jasmine Torres
*
*/

import javax.swing.JTextArea;

public class PlayerMonster extends Monster{

    public enum PlayerMonsterChoices 
    {
        DRAGON,
        VIPER,
        MINOTAUR;
    }
    //This method is used to display the monster choices

    private PlayerMonsterChoices monsterChoice; //monsterChoice is the monster chosen at the start of the game

    public PlayerMonster(String n, float h, float dam, int crit, float def, int dod, int att, PlayerMonsterChoices choice, JTextArea ta)
    {
        super(n, h, dam, crit, def, dod, att, ta);
        monsterChoice = choice;
    }


    public PlayerMonsterChoices getMonsterChoice(){return monsterChoice;}


    //this method is called after every won fight, all stats are increased by 25%
    public void levelUp()
    {
        //when a player monster levels up, their health is restored and boosted also
        //they gain another attack

        this.setHealth((float)(this.getMaxHealth() * 1.25)); //cast the result to a float
        this.setMaxHealth(this.getHealth());

        this.setDamage((float)(this.getDamage() * 1.25));
        this.setCritChance((int)(this.getCritChance() * 1.25));
        this.setDefense((float)(this.getDefense() * 1.25));
        this.setDodge((int)(this.getDodge() * 1.25));


        this.setAttacks(this.getAttacks() < 4 ? (this.getAttacks() + 1) : (this.getAttacks()));
        //if attacks is less than 4, add another, else remain at 4

    }
    
    @Override
    public boolean attack(int attackNumber, Monster target, Monster self)
    {
        //the integer passed to this method is the attack, 1-4, that the player wants to do
        //when an attack button is pressed, whatever number that button is passed here

        //the target is the monster that will defend against the attack...

        boolean hit = false;

        if(monsterChoice == PlayerMonsterChoices.DRAGON)
        {
            //attacks for the first player monster type, each case is an attack

            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1: //Normal: Bite (Med DMG + Burn)
                {
                  if(!target.defend(self ,(float) (1.0*self.getDamage()))) {
                    target.getEffectsVector().add(new Effect(target, Effect.EffectType.debuffBurn, 2));
                    hit = true;
                  }

                  break;
                }
                case 2: //Special: Scaly Menace (Def+, enemy Def-)
                {
                  self.getEffectsVector().add(new Effect(self, Effect.EffectType.ATTACK2_COOLDOWN, 2));
                  target.getEffectsVector().add(new Effect(self, Effect.EffectType.debuffDefense, 3));
                  self.getEffectsVector().add(new Effect(self, Effect.EffectType.buffDefense, 3));
                  hit = true;

                  break;

                }
                case 3: //Special: Dragon's Rage! (Damage+, Crit+)
                {
                  self.getEffectsVector().add(new Effect(self, Effect.EffectType.ATTACK3_COOLDOWN, 4));
                  self.getEffectsVector().add(new Effect(self, Effect.EffectType.buffDamage, 3));
                  self.getEffectsVector().add(new Effect(self, Effect.EffectType.buffCrit, 3));
                  hit = true;


                  break;
                }
                case 4: //Ultimate: Flames of Destruction (High DMG + Strong Burn)
                {
                  if(!target.defend(self ,(float) (1.5*self.getDamage()))) {
                    target.getEffectsVector().add(new Effect(target, Effect.EffectType.debuffStrongBurn, 2));
                    hit = true;
                  }
                  self.getEffectsVector().add(new Effect(self, Effect.EffectType.ATTACK4_COOLDOWN, 6));
                  
                  break;
                }
            }


        }
        else if( monsterChoice == PlayerMonsterChoices.VIPER)
        {

            //attacks for the second player monster type, each case is an attack

            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1: //Normal: Bite (Low DMG + Bleed)
                {
                  if(!target.defend(self ,(float) (0.5*self.getDamage()))) {
                    target.getEffectsVector().add(new Effect(target, Effect.EffectType.debuffBleed, 6));
                    hit = true;
                  }
                  
                  break;

                }
                case 2: //Special: Venomous Bite (Low DMG + Poison)
                {
                  if(!target.defend(self ,(float) (0.5*self.getDamage()))) {
                    target.getEffectsVector().add(new Effect(target, Effect.EffectType.debuffPoison, 3));
                    hit = true;
                  }
                  self.getEffectsVector().add(new Effect(self, Effect.EffectType.ATTACK2_COOLDOWN, 2));
                  
                  break;

                }
                case 3: //Special: Sharp Fangs (Low DMG + Enemy Attack- + Attack+)
                {
                if(!target.defend(self ,(float) (0.5*self.getDamage()))) {
                  target.getEffectsVector().add(new Effect(target, Effect.EffectType.debuffDamage, 2));
                  hit = true;
                }
                self.getEffectsVector().add(new Effect(self, Effect.EffectType.ATTACK3_COOLDOWN, 4));
                self.getEffectsVector().add(new Effect(self, Effect.EffectType.buffDamage, 3));
                
                break;

                }
                case 4: //Ultimate: Venom Burst! (No initial DMG, 2x Base Damage for each debuff on the Enemy)
                {
                  self.getEffectsVector().add(new Effect(self, Effect.EffectType.ATTACK4_COOLDOWN, 6));
                  target.getEffectsVector().add(new Effect(target, Effect.EffectType.debuffVenomBurst, 1));
                  hit = true;
                  
                  break;
                }
            }

        }
        else if(monsterChoice == PlayerMonsterChoices.MINOTAUR)
        {


            //attacks for the third player monster type, each case is an attack

            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1: //Normal: Charge (Med DMG + Enemy Def-)
                {
                  if(!target.defend(self ,(float) (1.0*self.getDamage()))) {
                    target.getEffectsVector().add(new Effect(target, Effect.EffectType.debuffDefense, 2));
                    hit = true;
                  }
                  
                  break;

                }
                case 2: //Special: Howl (Low DMG + Enemy DMG-)
                {
                  if(!target.defend(self ,(float)  (0.5*self.getDamage()))) {
                    target.getEffectsVector().add(new Effect(target, Effect.EffectType.debuffDamage, 2));
                    hit = true;
                  }
                  self.getEffectsVector().add(new Effect(self, Effect.EffectType.ATTACK2_COOLDOWN, 2));
                  
                  break;

                }
                case 3: //Special: Adrenaline (Heal 25% of health)
                {
                  self.getEffectsVector().add(new Effect(self, Effect.EffectType.ATTACK3_COOLDOWN, 4));
                  self.getEffectsVector().add(new Effect(self, Effect.EffectType.buffHeal, 2));
                  hit = true;
                  
                  break;

                }
                case 4: //Ultimate: Berserk (3x Damage + Armor Debuff) 
                {
                  if(!target.defend(self ,(float) (3.0*self.getDamage()))) {
                    self.getEffectsVector().add(new Effect(self, Effect.EffectType.debuffDefense, 3));
                    hit = true;
                  }
                  self.getEffectsVector().add(new Effect(self, Effect.EffectType.ATTACK4_COOLDOWN, 6));
                  
                  
                  break;
                }
            }
        }

        
        return hit;
    }
}
