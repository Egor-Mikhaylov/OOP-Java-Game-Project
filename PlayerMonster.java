
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
*   Editor: Egor Mikhaylov
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
    public void attack(int attackNumber, Monster target, Monster self)
    {
        //the integer passed to this method is the attack, 1-4, that the player wants to do
        //when an attack button is pressed, whatever number that button is passed here

        //the target is the monster that will defend against the attack...

        if(monsterChoice == PlayerMonsterChoices.DRAGON)
        {
            //attacks for the first player monster type, each case is an attack

            switch(attackNumber) //the attack number is passed as a parameter
            {
                case 1: //Normal: Bite (Med DMG + Burn)
                {
                  target.defend(1.0*self.getDamage());
                  target.effectsVector.add(new Effect(Effect.EffectType.debuffBurn, 1));
                }
                case 2: //Special: Polish Scales (Def+)
                {
                  self.effectsVector.add(new Effect(Effect.EffectType.ATTACK2_COOLDOWN, 2));
                  self.effectsVector.add(new Effect(Effect.EffectType.buffDefense, 3));

                }
                case 3: //Special: Stomp! (Enemy Def-)
                {
                  self.effectsVector.add(new Effect(Effect.EffectType.ATTACK3_COOLDOWN, 4));
                  target.effectsVector.add(new Effect(Effect.EffectType.debuffDefense, 4));

                }
                case 4: //Ultimate: Flames of Destruction (High DMG + Strong Burn)
                {
                  target.defend(1.5*self.getDamage());
                  self.effectsVector.add(new Effect(Effect.EffectType.ATTACK4_COOLDOWN, 6));
                  target.effectsVector.add(new Effect(Effect.EffectType.debuffStrongBurn, 2));
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
                  target.defend(0.5*self.getDamage());
                  target.effectsVector.add(new Effect(Effect.EffectType.debuffBleed, 6));

                }
                case 2: //Special: Venomous Bite (Low DMG + Poison)
                {
                  target.defend(0.5*self.getDamage());
                  self.effectsVector.add(new Effect(Effect.EffectType.ATTACK2_COOLDOWN, 2));
                  target.effectsVector.add(new Effect(Effect.EffectType.debuffPoison, 3));

                }
                case 3: //Special: Sharp Fangs (Low DMG + Enemy Attack- + Attack+)
                {
                target.defend(0.5*self.getDamage());
                self.effectsVector.add(new Effect(Effect.EffectType.ATTACK3_COOLDOWN, 4));
                target.effectsVector.add(new Effect(Effect.EffectType.debuffDamage, 2));
                self.effectsVector.add(new Effect(Effect.EffectType.buffDamage, 3));

                }
                case 4: //Ultimate: Venom Burst! (No Base DMG, 2x Base Damage for each debuff on the Enemy)
                {
                  self.effectsVector.add(new Effect(Effect.EffectType.ATTACK4_COOLDOWN, 6));
                  target.effectsVector.add(new Effect(Effect.EffectType.debuffVenomBurst, 1));
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
                  target.defend(1.0*self.getDamage());
                  target.effectsVector.add(new Effect(Effect.EffectType.debuffDefense, 1));

                }
                case 2: //Special: Howl (Low DMG + Enemy DMG-)
                {
                  target.defend(0.5*self.getDamage());
                  self.effectsVector.add(new Effect(Effect.EffectType.ATTACK2_COOLDOWN, 2));
                  target.effectsVector.add(new Effect(Effect.EffectType.debuffDamage, 2));

                }
                case 3: //Special: Heal (Heal 25% of health)
                {
                  self.effectsVector.add(new Effect(Effect.EffectType.ATTACK3_COOLDOWN, 4));
                  self.effectsVector.add(new Effect(Effect.EffectType.buffHeal, 1));

                }
                case 4: //Ultimate: Berserk (5x Damage + Armor Debuff) 
                {
                  target.defend(5.0*self.getDamage());
                  self.effectsVector.add(new Effect(Effect.EffectType.ATTACK4_COOLDOWN, 6));
                  self.effectsVector.add(new Effect(Effect.EffectType.debuffDefense, 3));
                }
            }
        }

        
        
    }
}
