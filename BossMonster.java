
/*  
*   BossMonster class
*
*   This class extends Monster, it is used to handle all logic for the final boss monster.
*
*   This class contains logic for the attacks that the final boss does.
*   The final boss is a giant gorilla (silverback?), and will have 4 attacks, just like a playerMonster
*   
*
*   Author: Isaac Perez
*   Date First Created: 8-1-2021
*
*
*   Editor: Egor Mikhaylov
*
*/

public class BossMonster extends EnemyMonster{
    
    public BossMonster(String n, float h, float dam, int crit, float def, int dod, int att)
    {
        super(n, h, dam, crit, def, dod, att, BasicEnemies.GORILLA); //a boss has 4 attacks
    }

    //the gorilla boss will have its own attacks here, 4 total
    @Override
    public void attack(int attackNumber, Monster target, Monster self)
    {
        switch(attackNumber) //the attack number is passed as a parameter
        {
            case 1: //Normal: Monkey's Fist (High DMG + buff crit)
            {
                target.defend(self ,(float) (1.5*self.getDamage()));
                self.getEffectsVector().add(new Effect(self, Effect.EffectType.BOSS_BUFF_CRIT, 1)); //gorilla adds to its crit chance every use
                
                break;
            }
            case 2: //Special: Pummel (medium DMG + debuff def and dodge)
            {
                target.defend(self, (float)(1.0*self.getDamage()));
                target.getEffectsVector().add(new Effect(target, Effect.EffectType.BOSS_DEBUFF_DEFENSE, 1));
                target.getEffectsVector().add(new Effect(target, Effect.EffectType.BOSS_DEBUFF_DODGE, 1));
                self.getEffectsVector().add(new Effect(self, Effect.EffectType.ATTACK2_COOLDOWN, 4));
                
                break;

            }
            case 3: //Special: Rising Wrath! (long duration, short cooldown damage buff)
            {
                self.getEffectsVector().add(new Effect(self, Effect.EffectType.BOSS_BUFF_DAMAGE, 5)); //lasts for 5 turns, insane scaling
                self.getEffectsVector().add(new Effect(self, Effect.EffectType.ATTACK3_COOLDOWN, 1)); //basically NO COOLDOWN

                
                break;
            }
            case 4: //Ultimate: Primal Supremacy (Extreme DMG, inflicts long term strong bleed)
            {
              target.defend(self ,(float) (3.0*self.getDamage()));
              self.getEffectsVector().add(new Effect(self, Effect.EffectType.ATTACK4_COOLDOWN, 7));
              target.getEffectsVector().add(new Effect(target, Effect.EffectType.BOSS_DEBUFF_BLEED, 5));

              
              break;
            }
        }
    }

}
