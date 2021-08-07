
/*  
*   Effect class
*
*   This class handles all logic pertaining to effects that happen after every turn.
*       All Effects have a "duration", every turn this is decreased by 1, when it reaches zero,
*       the effect is removed.
*
*   But, not every effect will affect the monster every turn, such as player ability cooldowns. 
*       All effects will have their duration decreased,
*       however, DOT will damage the monster and some effects will lower the stats of the monster every turn.
*
*   An effect can be a Debuff, a Buff, an ability cooldown (such as player attacks), or
*       really any effect that goes away after a certain amount of turns.
*
*   Every Monster object will have a vector of Effects objects. And, after every turn both monsters
*       in the battle will go through all Effects in that vector and take action based on the effect.
*   
*   For example, a PlayerMonster may have ability cooldowns, buffs (like increased damage), or debuffs(like poison DOT)
*       that will be in their vector.
*
*   The Effects vector is cleared after every fight.  
*
*   Author: Isaac Perez
*   Date First Created: 7-24-2021
*
*   Editor: Egor Mikhaylov
*
*
*/

public class Effect {
    
    private int duration; //when this reaches 0, remove this effect from the vector list

    private Monster owner; //this holds a reference to the monster affected
    //use this variable to apply the effect to the owning monster

    public enum EffectType
    {
        ATTACK2_COOLDOWN,
        ATTACK3_COOLDOWN,
        ATTACK4_COOLDOWN,
        debuffPercentageHealthLost,
        debuffDodge,
        debuffPoison,
        debuffBleed,
        debuffBurn,
        debuffStrongBurn,
        debuffDefense,
        debuffDamage,
        debuffVenomBurst,
        buffDamage,
        buffDefense,
        buffDodge,
        buffHeal,
        buffCrit,
        BOSS_BUFF_DAMAGE,
        BOSS_BUFF_CRIT,
        BOSS_DEBUFF_DEFENSE,
        BOSS_DEBUFF_DODGE,
        BOSS_DEBUFF_BLEED;

        //add more effects to this enum as they are created, then add their effect per turn below.   
    }

    private EffectType thisEffect; //this determines what action will be taken every turn


    public Effect(Monster m, EffectType et, int dur) //when an effet is created / added, the owner monster, type, and duration is sent over
    {
        owner = m;
        thisEffect = et;

        if(dur < 1)
        {
            duration = 1;
        }
        else
        {
            duration = dur; 
        }
        
    }

    public void setDuration(int dur) {duration = dur;}
    public int getDuration(){return duration;}
    
    public EffectType getThisEffect() {return thisEffect;}


    public String applyEffect()
    {
        duration--; //the duration will always be at least 1
        String outputEffectsText = "";

        switch(thisEffect)
        {
            case ATTACK2_COOLDOWN:
            {
              //no active component to this effect, only blocks the player from using this ability while under the effect
              if(duration != 0) 
              {
                outputEffectsText += "Your first special is still under cooldown. \n";
              }
              else {
                outputEffectsText += "Your first special is no longer under cooldown. \n";
              }
              break;
            
            }
            case ATTACK3_COOLDOWN:
            {
              //no active component to this effect, only blocks the player from using this ability while under the effect
              if(duration != 0) 
              {
                outputEffectsText += "Your second special is still under cooldown. \n";
              }
              else {
                outputEffectsText += "Your second special is no longer under cooldown. \n";
              }
              break;

            }
            case ATTACK4_COOLDOWN:
            {
              //no active component to this effect, only blocks the player from using this ability while under the effect
              if(duration != 0) 
              {
                outputEffectsText += "Your ultimate is still under cooldown. \n";
              }
              else {
                outputEffectsText += "Your ultimate is no longer under cooldown. \n";
              }
              break;
            }

            case debuffPercentageHealthLost: 
            {
              owner.setHealth((float) (owner.getHealth()-(owner.getMaxHealth()*.1))); //10% health per turn
              //unused at the moment
              break;
            }

            case debuffDodge: 
            {
              owner.setDodge((int) (owner.getDodge()*.75)); //25% decrease to dodge
              break;
            }

            case debuffPoison: 
            {
              owner.setHealth((float) (owner.getHealth()-(8))); //-8 health per turn (medium duration)
              outputEffectsText += "You have taken 8 damage from poison. \n";
              break;
            }

            case debuffBleed: 
            {
              owner.setHealth((float) (owner.getHealth()-(4))); //-4 health per turn (long duration)
              outputEffectsText += "You have taken 4 damage from your bleeding. \n";
              break;
            }

            case debuffBurn: 
            {
              owner.setHealth((float) (owner.getHealth()-(12))); //-12 health per turn(short duration)
              outputEffectsText += "You have taken 12 damage from burning. \n";
              break;
            }

            case debuffStrongBurn: 
            {
              owner.setHealth((float) (owner.getHealth()-(25))); //-25 health per turn (short but deadly)
              outputEffectsText += "You have taken 25 damage from your Strong Burn. \n";
              break;
            }

            case debuffDefense: 
            {
              owner.setDefense((float) (owner.getDefense()*.75)); //% decrease to defense
              
              break;
            }

            case debuffDamage: 
            {
              owner.setDamage((float) (owner.getDamage()*.90)); //10% decrease to damage
              
              break;
            }

            case buffDamage: 
            {
              owner.setDamage((float) (owner.getDamage()*1.3));//30% increase to damage
              
              break;
            }

            case buffDefense: 
            {
              owner.setDefense((float) (owner.getDefense()* 1.30)); //30% increase to defense
              
              break;
            }

            case buffDodge: 
            {
              owner.setDodge((int) (owner.getDodge()*1.5)); //50% increase to dodge
              
              break;
            }

            case buffHeal: 
            {
              if(owner.getHealth()+(owner.getMaxHealth()*.10) >= owner.getMaxHealth()) {
                owner.setHealth(owner.getMaxHealth()); //If adding 25% health will go over the cap, set the health to full instead.
              }
              else {
                owner.setHealth((float) (owner.getHealth()+(owner.getMaxHealth()*.10))); //25% health for 1 turn
              }

              
              break;
            }

            case debuffVenomBurst:
            {
              float baseDmg = (float) 100; //change this to balance

              //go through all debuffs in this monster's vector, ignoring cooldowns, and double baseDmg for every one
              for(Effect e : owner.getEffectsVector())
              {
                  if(e.getThisEffect() == EffectType.ATTACK2_COOLDOWN)
                  {
                    //ignore
                  }
                  else if(e.getThisEffect() == EffectType.ATTACK3_COOLDOWN)
                  {
                    //ignore
                  }
                  else if(e.getThisEffect() == EffectType.ATTACK4_COOLDOWN)
                  {
                    //ignore
                  }
                  else if(e.getThisEffect() == EffectType.debuffVenomBurst)
                  {
                    //ignore itself?
                  }
                  else
                  {
                    baseDmg *= 1.75; //multiply base damage for every debuff on this monster
                  }
              }

              owner.setHealth(owner.getHealth() - baseDmg);

              
              break;
            }

            case BOSS_BUFF_CRIT:
            {
              owner.setCritChance((int) (owner.getCritChance() * 1.1)); //boss increases crit by 10%
              
              break;
            }

            case BOSS_DEBUFF_DEFENSE:
            {
              owner.setDefense((float)(owner.getDefense() * .5)); //decrease defense by 50%
              break;
            }

            case BOSS_DEBUFF_DODGE:
            {
              owner.setDodge((int) (owner.getDodge() * .5)); //decrease dodge by 50%
              
              break;
            }

            case BOSS_BUFF_DAMAGE:
            {
              owner.setDamage((float)(owner.getDamage() * 1.025)); //increase damage by 2.5%, lasts a bunch of turns
              
              break;
            }
            
            case BOSS_DEBUFF_BLEED:
            {
              owner.setHealth((float) (owner.getHealth() - 40.0)); //40 damage per tick
              outputEffectsText += "You have taken 40 damage from the boss's bleed. \n";
              break;
            }

            case buffCrit:
            {
              owner.setCritChance((int)(owner.getCritChance() * 1.05)); //5% crit chance increase
            }

            //keep adding cases as more effects are created
        }
      return outputEffectsText;

    }
    
}
