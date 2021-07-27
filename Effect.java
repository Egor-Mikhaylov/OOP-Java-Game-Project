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
*/

public class Effect {
    
    private int duration; //when this reaches 0, remove this effect from the vector list

    private Monster owner; //this holds a reference to the monster affected
    //use this variable to apply the effect to the owning monster

    public enum EffectType
    {
        ATTACK2_COOLDOWN,
        ATTACK3_COOLDOWN,
        ATTACK4_COOLDOWN;

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


    public void applyEffect()
    {
        duration--; //the duration will always be at least 1

        

        switch(thisEffect)
        {
            case ATTACK2_COOLDOWN:
            {
            //no active component to this effect, only blocks the player from using this ability while under the effect
            
            }
            case ATTACK3_COOLDOWN:
            {

            }
            case ATTACK4_COOLDOWN:
            {

            }

            //keep adding cases as more effects are created
        }

    }
    
}
