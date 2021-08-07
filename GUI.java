/*  
*   GUI class
*
*   This class extends the JFrame class in order to create a Swing Application.
*   
*
*
*   Author: Isaac Perez
*   Date First Created: 7-23-2021
*
*   Editor: Egor Mikhaylov
*
*
*/
import javax.swing.JFrame; //for window
import javax.swing.JPanel; //for arranging componenets
import javax.swing.JLabel; //for button text, player images, and names
import javax.swing.JButton; //for player input
import javax.swing.JProgressBar; //for monster health bars
import javax.swing.JTextArea;
import javax.swing.SwingConstants; //constants to arrange components
import javax.swing.JOptionPane;////for when the player wins or loses, when the game is reset


import java.util.Random;
import java.awt.event.*; //handling button events

import java.awt.Color;

import javax.swing.ImageIcon;

public class GUI extends JFrame {

    //need 2 progress bars, 4 buttons, 8 labels, 2 panels

    //progress bars for player and enemy HP BAR
    //4 buttons for player input, each will have a label for player attacks and choosing their monster
    //4 labels for button text, 2 for monster images - player monster and enemy monster, 2 for monster names in window
    // 1 panel for button grid, another panel for game window, which arrange everything but the 4 buttons

    private JProgressBar playerHPBAR; //Players health/progress bar
    private JProgressBar enemyHPBAR; //Enemy health/progress bar

    private JPanel gamePanel; 

    private JLabel playerMonsterName;
    private JLabel playerMonsterImage;
    private JLabel enemyMonsterName;
    private  JLabel enemyMonsterImage;

    private  JButton inputButtons[]; //four input buttons

    private JTextArea outputTextArea; //all actions will be output here


    private final int WINDOWWIDTH = 720;
    private final int WINDOWHEIGHT = 680;

    private boolean playerIsChoosingAMonster; // true if game has just started/restarted
                                            // false if the player is going through the 5 fights
                                         // if the player dies or wins, this turns true at some point



    private Monster player; //this will hold the player's monster
    private Monster enemy; //this will hold the enemy's monster, basic or boss type

    private Monster playerBackup; //holds the player's monster stats from before a fight
        //this replaces the player before a level up

    private final String spaces = "\n\n\n\t\t                 ";



    private JLabel dragon;
    private JLabel viper;
    private JLabel minotaur;

    private ButtonLogic bLogic;

    private int fightCounter; //holds the fight number, fight == 1 when the first fight of the game is ongoing

    private ImageIcon image; //holds a png.

    private Random random; //use to create a seed to generate random numbers
    private int randomNumber; //holds the randomly generated number


    public GUI()
    {
        super("Monster Fighter!");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOWWIDTH, WINDOWHEIGHT);
        setResizable(false);


        //arrange all components into the frame into exact spots

        gamePanel = new JPanel(null);




        inputButtons = new JButton[4];
        bLogic = new ButtonLogic();
        for(int i = 0; i < 4; i++)
        {
            inputButtons[i] = new JButton("Button " + (i+1));
            inputButtons[i].addActionListener(bLogic);
        }

        //position buttons at the bottom
        inputButtons[0].setBounds(17, 520, 330, 50);
        inputButtons[1].setBounds(357, 520, 330, 50);
        inputButtons[2].setBounds(17, 580, 330, 50);
        inputButtons[3].setBounds(357, 580, 330, 50);
        

        gamePanel.add(inputButtons[0]);
        gamePanel.add(inputButtons[1]);
        gamePanel.add(inputButtons[2]);
        gamePanel.add(inputButtons[3]);


        
        outputTextArea = new JTextArea("All output will be sent here.");
        outputTextArea.setBounds(17, 400, 670, 110);
        outputTextArea.setEditable(false);
        gamePanel.add(outputTextArea);


        playerHPBAR = new JProgressBar();
        playerHPBAR.setBounds(27, 365, 310, 25);
        playerHPBAR.setValue(100);
        playerHPBAR.setForeground(new Color((float)0, (float) 0.8, (float) 0));
        gamePanel.add(playerHPBAR);
   

        enemyHPBAR = new JProgressBar();
        enemyHPBAR.setBounds(367, 365, 310, 25);
        enemyHPBAR.setValue(100);
        enemyHPBAR.setForeground(new Color((float)0, (float) 0.8, (float) 0));
        gamePanel.add(enemyHPBAR);

        image = new ImageIcon("images/minotaur.png");
        playerMonsterImage = new JLabel(image);
        playerMonsterImage.setBounds(17, 45, 330, 300);
        
        gamePanel.add(playerMonsterImage);


        image = new ImageIcon("images/gorilla.png");
        enemyMonsterImage = new JLabel(image);
        enemyMonsterImage.setBounds(357, 45, 330, 300);

        gamePanel.add(enemyMonsterImage);




        playerMonsterName = new JLabel("----------------Player Name------------------");
        playerMonsterName.setBounds(35, 10, 300, 20);
        playerMonsterName.setHorizontalAlignment(SwingConstants.CENTER);
        gamePanel.add(playerMonsterName);


        enemyMonsterName = new JLabel("----------------Enemy Name------------------");
        enemyMonsterName.setBounds(380, 10, 300, 20);
        enemyMonsterName.setHorizontalAlignment(SwingConstants.CENTER);
        gamePanel.add(enemyMonsterName);





        //SET ALL COMPONENTS OTHER THAN BUTTONS TO INVISIBLE,

        playerMonsterName.setVisible(false);
        enemyMonsterName.setVisible(false);
        playerMonsterImage.setVisible(false);
        enemyMonsterImage.setVisible(false);
        playerHPBAR.setVisible(false);
        enemyHPBAR.setVisible(false);





        //display the 3 choices
        outputTextArea.setText(spaces + "Choose the monster you will fight with!");


        image = new ImageIcon("images/dragon_half.png");
        dragon = new JLabel(image);
        dragon.setText("Dragon");
        dragon.setHorizontalTextPosition(SwingConstants.CENTER);
        dragon.setVerticalTextPosition(SwingConstants.BOTTOM);
        dragon.setBounds(40, 120, 165, 170);
        gamePanel.add(dragon);

        image = new ImageIcon("images/viper_half.png");
        viper = new JLabel(image);
        viper.setText("Viper");
        viper.setHorizontalTextPosition(SwingConstants.CENTER);
        viper.setVerticalTextPosition(SwingConstants.BOTTOM);
        viper.setBounds(268, 120, 165, 170);
        gamePanel.add(viper);

        image = new ImageIcon("images/minotaur_half.png");
        minotaur = new JLabel(image);
        minotaur.setText("Minotuar");
        minotaur.setHorizontalTextPosition(SwingConstants.CENTER);
        minotaur.setVerticalTextPosition(SwingConstants.BOTTOM);
        minotaur.setBounds(500, 120, 165, 170);
        gamePanel.add(minotaur);


        inputButtons[0].setText("Choose Dragon");
        inputButtons[1].setText("Choose Viper");
        inputButtons[2].setText("Choose Minotaur");
        inputButtons[3].setText(" ");







        this.add(gamePanel);

        playerIsChoosingAMonster = true; // button logic switched to choosing a monster

        setVisible(true);
        
    }



    private class ButtonLogic implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event) // a button was pushed
        {

            if(playerIsChoosingAMonster == true) //game is currently in the choose a starter phase
            {
                if(event.getSource() == inputButtons[0]) //first button pressed, Dragon chosen
                {
                    //create a new player monster
                    player = new PlayerMonster("Dragon",750,100,10,45,12,4, PlayerMonster.PlayerMonsterChoices.DRAGON);
                    playerIsChoosingAMonster = false;

                    changeGameToFighting(); //change GUI format

                    //create first enemy, set game to fight 1
                    fightCounter = 1;

                    enemy = createEnemy(fightCounter); //create a random enemy to fight

                    playerMonsterName.setText(player.getName());
                    enemyMonsterName.setText(enemy.getName());

                    image = new ImageIcon("images/dragon.png");
                    playerMonsterImage.setIcon(image);

                    enemyMonsterImage.setIcon(getEnemyImage((EnemyMonster)enemy));


                    player.setAttacks(1); //player only has 1 attack right now
                    //update buttons to ability moves
                    updateAbilityButtons((PlayerMonster) player);

                    outputTextArea.setText(spaces); //reset output


                    //create a backup of lvl 1 player, to preserve the initial stats 
                    playerBackup = player;


                }

                else if(event.getSource() == inputButtons[1]) //second button pressed, Viper chosen
                {
                    player = new PlayerMonster("Viper",500,75,20,40,20,4, PlayerMonster.PlayerMonsterChoices.VIPER);
                    playerIsChoosingAMonster = false;

                    
                    changeGameToFighting(); //change GUI format

                    //create first enemy, set game to fight 1
                    fightCounter = 1;

                    enemy = createEnemy(fightCounter); //create a random enemy to fight

                    playerMonsterName.setText(player.getName());
                    enemyMonsterName.setText(enemy.getName());

                    image = new ImageIcon("images/viper.png");
                    playerMonsterImage.setIcon(image);

                    enemyMonsterImage.setIcon(getEnemyImage((EnemyMonster)enemy));
                   
                   
                    player.setAttacks(1);
                    //update buttons to ability moves
                    updateAbilityButtons((PlayerMonster) player);

                    outputTextArea.setText(spaces); //reset output



                    //create a backup of lvl 1 player, to preserve the initial stats 
                    playerBackup = player;
                    
                }

                else if(event.getSource() == inputButtons[2]) //third button pressed, Minotaur chosen
                {
                    player = new PlayerMonster("Minotaur",900,125,15,10,8,4, PlayerMonster.PlayerMonsterChoices.MINOTAUR);
                    playerIsChoosingAMonster = false;

                    
                    changeGameToFighting(); //change GUI format

                    //create first enemy, set game to fight 1
                    fightCounter = 1;

                    enemy = createEnemy(fightCounter); //create a random enemy to fight

                    playerMonsterName.setText(player.getName());
                    enemyMonsterName.setText(enemy.getName());

                    image = new ImageIcon("images/minotaur.png");
                    playerMonsterImage.setIcon(image);

                    enemyMonsterImage.setIcon(getEnemyImage((EnemyMonster)enemy));


                    player.setAttacks(1);
                    //update buttons to ability moves
                    updateAbilityButtons((PlayerMonster) player);

                    outputTextArea.setText(spaces); //reset output



                    //create a backup of lvl 1 player, to preserve the initial stats 
                    playerBackup = player;
                }


            }
            else if(playerIsChoosingAMonster == false) //game is in fighting phase
            {
                //clear text log
                outputTextArea.setText(" ");

                boolean eligibleAttack = false; //true if attack is not on cooldown
                boolean attackWorked = false;
                float prevHealth;
                float dmgDone = 0;

                if(event.getSource() == inputButtons[0]) //first button pressed
                {
                    eligibleAttack = true;

                    //basic ability used, player attacks first, update enemy HPBAR after attack

                    prevHealth = enemy.getHealth();
                    attackWorked = player.attack(1, enemy, player); //use ability 1
                    dmgDone = prevHealth-enemy.getHealth();
                    if(player.getName().equals("Dragon")) 
                    {
                      if(attackWorked) {
                        outputTextArea.append(String.format("Your attack has hit for %.2f damage!\n", dmgDone));
                        outputTextArea.append("You have applied a burning effect on the enemy for 2 turns.\n");
                      }
                      else 
                      {
                        outputTextArea.append("Your attack has missed.\n");
                      }
                    }
                    else if(player.getName().equals("Viper")) 
                    {
                      if(attackWorked) {
                        outputTextArea.append(String.format("Your attack has hit for %.2f damage!\n", dmgDone));
                        outputTextArea.append("You have applied a bleed effect on the enemy for 6 turns.\n");
                      }
                      else 
                      {
                        outputTextArea.append("Your attack has missed.\n");
                      }
                    }
                    else if(player.getName().equals("Minotaur"))
                    {
                      if(attackWorked) {
                        outputTextArea.append(String.format("Your attack has hit for %.2f damage!\n", dmgDone));
                        outputTextArea.append("You have decreased the enemy's defense for 2 turns.\n");
                      }
                      else 
                      {
                        outputTextArea.append("Your attack has missed.\n");
                      }
                    }
                }


                else if(event.getSource() == inputButtons[1]) //second button pressed
                {

                    if(player.getAttacks() < 2)
                    {
                        //this attack is not unlocked
                    }
                    else
                    {
                        //attack is available, check if on cooldown
                        boolean cooldown = false;

                        for(Effect e : player.getEffectsVector())
                        {
                            if(e.getThisEffect() == Effect.EffectType.ATTACK2_COOLDOWN)
                            {
                                cooldown = true;//cooldown found
                            }
                        }

                        if(cooldown == false)
                        {
                            eligibleAttack = true;
                            prevHealth = enemy.getHealth();
                            attackWorked = player.attack(2, enemy, player); //use ability 2
                            dmgDone = prevHealth-enemy.getHealth();
                            if(player.getName().equals("Dragon")) 
                            {
                              outputTextArea.append("You have decreased the enemy's defense for 3 turns and boosted your defense for 3 turns.\n");
                              outputTextArea.append("Your first special is now on a 2 turn cooldown.\n");
                            }
                            else if(player.getName().equals("Viper")) 
                            {
                              if(attackWorked) {
                                outputTextArea.append(String.format("Your attack has hit for %.2f damage!\n", dmgDone));
                                outputTextArea.append("You have applied posion to the enemy for 3 turns.\n");
                              }
                              else 
                              {
                                outputTextArea.append("Your attack has missed.\n");
                              }
                              outputTextArea.append("Your first special is now on a 2 turn cooldown.\n");
                            }
                            else if(player.getName().equals("Minotaur"))
                            {
                              if(attackWorked) {
                                outputTextArea.append(String.format("Your attack has hit for %.2f damage!\n", dmgDone));
                                outputTextArea.append("You have decreased the enemy's damage for 2 turns.\n");
                              }
                              else 
                              {
                                outputTextArea.append("Your attack has missed.");
                              }
                              outputTextArea.append("Your first special is now on a 2 turn cooldown.\n");

                            }
                        }

                    }

 
                }


                else if(event.getSource() == inputButtons[2]) //third button pressed
                {
                    if(player.getAttacks() < 3)
                    {
                        //this attack is not unlocked
                    }
                    else
                    {
                        //attack is available, check if on cooldown
                        boolean cooldown = false;

                        for(Effect e : player.getEffectsVector())
                        {
                            if(e.getThisEffect() == Effect.EffectType.ATTACK3_COOLDOWN)
                            {
                                cooldown = true;//cooldown found
                            }
                        }

                        if(cooldown == false)
                        {
                            eligibleAttack = true;
                            prevHealth = enemy.getHealth();
                            attackWorked = player.attack(3, enemy, player); //use ability 3
                            dmgDone = prevHealth-enemy.getHealth();
                            if(player.getName().equals("Dragon")) 
                            {
                              outputTextArea.append("You have increaed your damage and crit chance for 3 turns.\n");
                              outputTextArea.append("Your second special is now on a 4 turn cooldown.\n");
                            }
                            else if(player.getName().equals("Viper")) 
                            {
                              if(attackWorked) {
                                outputTextArea.append(String.format("Your attack has hit for %.2f damage!\n", dmgDone));
                                outputTextArea.append("You have decreased the enemy's damage for 2 turns and boosted your damage for 3 turns.\n");
                              }
                              else 
                              {
                                outputTextArea.append("Your attack has missed.\n");
                              }
                              outputTextArea.append("Your second special is now on a 4 turn cooldown.\n");

                            }
                            else if(player.getName().equals("Minotaur"))
                            {
                              outputTextArea.append("You have healed yourself by 25%.\n");
                              outputTextArea.append("Your second special is now on a 4 turn cooldown.\n");
                            }
                        }

                    }  
                }


                else if(event.getSource() == inputButtons[3]) //fourth button pressed
                {
                    if(player.getAttacks() < 4)
                    {
                        //this attack is not unlocked
                    }
                    else
                    {
                        //attack is available, check if on cooldown
                        boolean cooldown = false;

                        for(Effect e : player.getEffectsVector())
                        {
                            if(e.getThisEffect() == Effect.EffectType.ATTACK4_COOLDOWN)
                            {
                                cooldown = true;//cooldown found
                            }
                        }

                        if(cooldown == false)
                        {
                            eligibleAttack = true;
                            prevHealth = enemy.getHealth();
                            attackWorked = player.attack(4, enemy, player); //use ability 4
                            dmgDone = prevHealth-enemy.getHealth();
                            if(player.getName().equals("Dragon")) 
                            {
                              if(attackWorked) {
                                outputTextArea.append(String.format("Your ultimate has wrecked the enemy and hit for %.2f damage!\n", dmgDone));
                                outputTextArea.append("You have applied a strong burn on the enemy's defense for 2 turns.\n");
                              }
                              else 
                              {
                                outputTextArea.append("Your attack has missed.\n");
                              }
                              outputTextArea.append("Your ultimate is now on a 6 turn cooldown.\n");
                            }
                            else if(player.getName().equals("Viper")) 
                            {
                              outputTextArea.append("Your venom is primed to burst and will explode at the end of this round.\n");
                              outputTextArea.append("Your ultimate is now on a 6 turn cooldown.\n");

                            }
                            else if(player.getName().equals("Minotaur"))
                            {
                              if(attackWorked) {
                                outputTextArea.append(String.format("You have gone beserk and dealt %.2f damage!\n", dmgDone));
                                outputTextArea.append("You now have decreased defense for 3 turns.\n");
                              }
                              else 
                              {
                                outputTextArea.append("Your attack has missed.\n");
                              }
                              outputTextArea.append("Your ultimate is now on a 6 turn cooldown.\n");
                            }
                        }

                    }   
                }


                if(eligibleAttack == true)
                {

                    
                    enemyHPBAR.setValue((int) ((enemy.getHealth() / enemy.getMaxHealth()) * 100)); //update enemy HPBar

                    //then the enemy chooses a random attack (1 or 2 for basic enemies) (1-4 for the boss), update player hpbar
                    random = new Random();

                    if(((EnemyMonster) enemy).getEnemyType() == EnemyMonster.BasicEnemies.GORILLA) //if fighting the boss
                    {
                        randomNumber = random.nextInt(4); //generate a number from 0-3
                        randomNumber++; //make that number 1-4
                    }
                    else//the enemy is a basic enemy
                    {
                        randomNumber = random.nextInt(2); //generate a number from 0-1
                        randomNumber++; //make that number 1-2
                    }


                    if(randomNumber == 4)
                    {
                        //check if the boss can use this attack, if not, use a normal attack
                        
                        for(Effect e : enemy.getEffectsVector())
                        {
                            if(e.getThisEffect() == Effect.EffectType.ATTACK4_COOLDOWN)
                            {
                                randomNumber = 1;// if this attack is on cooldown, use a normal
                            }
                            else {
                              prevHealth = player.getHealth();
                              attackWorked = enemy.attack(randomNumber, player, enemy); 
                              //boss uses their 4th attack on the player
                              dmgDone = prevHealth-player.getHealth();
                              if(attackWorked) {
                                outputTextArea.append(String.format("The boss has hit you with its ultimate attack for %.2f damage!\n", dmgDone));
                                outputTextArea.append("The boss has applied a strong bleed on you for 7 turns.\n");
                              }
                              else 
                              {
                                outputTextArea.append("The boss has missed its ultimate attack.\n");
                              }
                            }
                        }
                    }

                    if(randomNumber == 3)
                    {
                        //check if the boss can use this attack, if not, use a normal attack
                        
                        for(Effect e : enemy.getEffectsVector())
                        {
                            if(e.getThisEffect() == Effect.EffectType.ATTACK3_COOLDOWN)
                            {
                                randomNumber = 1;// if this attack is on cooldown, use a normal
                            }
                            else {
                              prevHealth = player.getHealth();
                              attackWorked = enemy.attack(randomNumber, player, enemy); 
                              //boss uses their 3rd attack on the player
                              dmgDone = prevHealth-player.getHealth();
                              outputTextArea.append("The boss has buffed its damage for 5 turns with its second special.\n");
                            }
                        }
                    }

                    if(randomNumber == 2)
                    {
                        //check if the enemy can use this attack, if not, use a normal attack
                        
                        for(Effect e : enemy.getEffectsVector())
                        {
                            if(e.getThisEffect() == Effect.EffectType.ATTACK2_COOLDOWN)
                            {
                                randomNumber = 1;// if this attack is on cooldown, use a normal
                            }
                            else 
                            {
                              prevHealth = player.getHealth();
                              attackWorked = enemy.attack(randomNumber, player, enemy); 
                              //enemy uses their 2nd attack on the player
                              dmgDone = prevHealth-player.getHealth();
                              if(((EnemyMonster) enemy).getEnemyType() == EnemyMonster.BasicEnemies.GORILLA) //if fighting the boss
                              {
                                if(attackWorked) 
                                {
                                  outputTextArea.append(String.format("The boss has hit you with a special attack for %.2f damage!\n", dmgDone));
                                  outputTextArea.append("The boss has decreased your defense and dodge for 1 turn.\n");
                                }
                                else 
                                {
                                  outputTextArea.append("The boss has missed its special attack.\n");
                                }
                              }

                              if(((EnemyMonster) enemy).getEnemyType() == EnemyMonster.BasicEnemies.WASP)
                              {
                                if(attackWorked) {
                                  outputTextArea.append(String.format("The enemy has hit you with its special attack for %.2f damage!", dmgDone));
                                  outputTextArea.append("The enemy has applied a bleed on you for 6 turns.\n");
                                }
                                else {
                                  outputTextArea.append("The enemy's special attack missed.\n");
                                }
                              }

                              if(((EnemyMonster) enemy).getEnemyType() == EnemyMonster.BasicEnemies.TOAD)
                              {
                                if(attackWorked) {
                                  outputTextArea.append(String.format("The enemy has hit you with its special attack for %.2f damage!\n", dmgDone));
                                }
                                else {
                                  outputTextArea.append("The enemy's special attack missed.\n");
                                }
                              }

                              if(((EnemyMonster) enemy).getEnemyType() == EnemyMonster.BasicEnemies.CAT)
                              {
                                outputTextArea.append("The enemy has increased its dodge for 3 turns.\n");
                              }

                              if(((EnemyMonster) enemy).getEnemyType() == EnemyMonster.BasicEnemies.ELEPHANT)
                              {
                                outputTextArea.append("The enemy has decreased your defense for 2 turns.\n");
                              }

                              if(((EnemyMonster) enemy).getEnemyType() == EnemyMonster.BasicEnemies.SNAIL)
                              {
                                outputTextArea.append("The enemy has applied poison on you for 4 turns.\n");
                              }

                              if(((EnemyMonster) enemy).getEnemyType() == EnemyMonster.BasicEnemies.CROCODILE)
                              {
                                outputTextArea.append("The enemy has increased its damage for 3 turns.\n");
                              }

                              if(((EnemyMonster) enemy).getEnemyType() == EnemyMonster.BasicEnemies.RHINO)
                              {
                                if(attackWorked) {
                                  outputTextArea.append(String.format("The enemy has hit you with its special attack for %.2f damage!\n", dmgDone));
                                  outputTextArea.append("The enemy has applied a bleed on you for 3 turns and increased its dodge for 3 turns.\n");
                                }
                                else {
                                  outputTextArea.append("The enemy's special attack missed.\n");
                                }
                              }

                            }
                        }
                    }

                    if(randomNumber == 1)
                    {
                      prevHealth = player.getHealth();
                      attackWorked = enemy.attack(randomNumber, player, enemy); 
                      //enemy uses their 1st attack on the player
                      dmgDone = prevHealth-player.getHealth();
                      if(((EnemyMonster) enemy).getEnemyType() == EnemyMonster.BasicEnemies.GORILLA) //if fighting the boss
                      {
                        if(attackWorked) {
                          outputTextArea.append(String.format("The boss has hit you for %.2f damage!\n", dmgDone));
                          outputTextArea.append("The boss has increased its crit chance.\n");
                        }
                        else 
                        {
                          outputTextArea.append("The boss has missed.\n");
                        }
                      }
                      else {
                        if(attackWorked) 
                        {
                          outputTextArea.append(String.format("The enemy has hit you for %.2f damage!\n", dmgDone));
                        }
                        else 
                        {
                          outputTextArea.append("The enemy has missed.\n");
                        }
                      }
                    }

                    playerHPBAR.setValue((int) ((player.getHealth() / player.getMaxHealth()) * 100)); //update player HPBar


                    //afterewards cycle through effect vectors of player(update HPBar) then enemy (update HPbar)
                    String effectOutput = "";
                    effectOutput = player.applyEffects();
                    outputTextArea.append(effectOutput);
                    
                    playerHPBAR.setValue((int) ((player.getHealth() / player.getMaxHealth()) * 100)); //update player HPBar

                    enemy.applyEffects();
                    enemyHPBAR.setValue((int) ((enemy.getHealth() / enemy.getMaxHealth()) * 100)); //update enemy HPBar





                    //then check if the player is dead, display popup and reset game to 3 choice phase
                    if(player.getAlive() == false)
                    {
                         //player died, show popup, and then reset the game


                        JOptionPane.showMessageDialog(gamePanel, "You have died!");
                         

                        changeGameToThreeChoices();
                        playerIsChoosingAMonster = true;







                    }

                
                    //then check if enemy is dead, if basic enemy, restore player stats and level up
                    //increase fightCounter, and spawn another enemy and update GUI

                    if(enemy.getAlive() == false && player.getAlive() == true)
                    {
                        //enemy died, check if enemy is a boss
                        if(((EnemyMonster)enemy).getEnemyType() == EnemyMonster.BasicEnemies.GORILLA) //enemy is a boss
                        {
                         //player wins the game, show a victory popup then reset the game to the choose state



                         JOptionPane.showMessageDialog(gamePanel, "You have won!");
                         

                         changeGameToThreeChoices();
                         playerIsChoosingAMonster = true;







                        }
                        else//enemy is basic
                        {
                            //player wins the fight, increment fight counter

                            fightCounter++;
                            enemy = createEnemy(fightCounter); //create new enemy
                            //update enemy side
                            enemyMonsterName.setText(enemy.getName());
                            enemyMonsterImage.setIcon(getEnemyImage((EnemyMonster)enemy));


                            //apply the copy to the player, levelup, then make another copy , reset both hpbars
                             //BUT REMOVE ALL EFFECTS FIRST
                            player.getEffectsVector().clear();

                            player = playerBackup;
                            ((PlayerMonster)player).levelUp();
                            playerBackup = player;

                            playerHPBAR.setValue((int) ((player.getHealth() / player.getMaxHealth()) * 100)); //update player HPBar
                            enemyHPBAR.setValue((int) ((enemy.getHealth() / enemy.getMaxHealth()) * 100)); //update enemy HPBar


                            //update ability buttons
                            updateAbilityButtons(((PlayerMonster)player));


                        }
                    }




                }












            }






        }
    }



    public void changeGameToThreeChoices()
    {
        //this function hides all GUI components that are not needed to choose a starter
        //and it changes the game to the choosing a starter phase
        //also changes button text to the three choices
        dragon.setVisible(true);
        viper.setVisible(true);
        minotaur.setVisible(true);

        playerMonsterName.setVisible(false);
        enemyMonsterName.setVisible(false);
        playerMonsterImage.setVisible(false);
        enemyMonsterImage.setVisible(false);
        playerHPBAR.setVisible(false);
        enemyHPBAR.setVisible(false);

        inputButtons[0].setText("Choose Dragon");
        inputButtons[1].setText("Choose Viper");
        inputButtons[2].setText("Choose Minotaur");
        inputButtons[3].setText(" ");


        //display the 3 choices
        outputTextArea.setText(spaces + "Choose the monster you will fight with!");

        playerHPBAR.setValue(100); //reset player HPBar
        enemyHPBAR.setValue(100); //reset enemy HPBar


    }

    public void changeGameToFighting()
    {
        //this function hides all GUI components that are used in the choice
        //changes the GUI to fighting format


        dragon.setVisible(false);
        viper.setVisible(false);
        minotaur.setVisible(false);

        playerMonsterName.setVisible(true);
        enemyMonsterName.setVisible(true);
        playerMonsterImage.setVisible(true);
        enemyMonsterImage.setVisible(true);
        playerHPBAR.setVisible(true);
        enemyHPBAR.setVisible(true);

       // updateAbilityButtons(((PlayerMonster)player));

    }


    public Monster createEnemy(int fightNum)
    {
        //creates and returns a random enemy to fight based on the fight number
        //fightNum == 1, no mulitplier is used
        //for each number above 1, the multiplier increased by .33
        //for a fightNum of 5, the Gorilla Boss is created and returned
        float statMult = 0;
        random = new Random(); //create new seed
        randomNumber = 0;

        Monster createdEnemy = new EnemyMonster("Wasp", 300 * statMult, 100 * statMult, (int) (30 * statMult),10 * statMult, (int)(25 * statMult),
                                                 2, EnemyMonster.BasicEnemies.WASP);



        //the enemy's stats will be multiplied based on the current fight number
        
        if(fightNum <= 4)
        {
            if(fightNum == 1)
            {
                statMult = (float)1.0;
            }
            if(fightNum == 2)
            {
                statMult = (float)1.25;
            }
            if(fightNum == 3)
            {
                statMult = (float)1.50;
            }
            if(fightNum == 4)
            {
                statMult = (float)1.75;
            }


            //roll a random number from 0 to 69, created a basic enemy based on the roll
            //0-9 wasp
            //10-19 toad
            //20-29 cat
            //30-39 elephant
            //40-49 snail
            //50-59 crocodile
            //60-69 rhino
            randomNumber = random.nextInt(70); //roll from 0-69

            if( randomNumber < 10) //wasp
            {
                createdEnemy = new EnemyMonster("Wasp", 300 * statMult, 100 * statMult, (int) (30 * statMult),10 * statMult, (int)(25 * statMult),
                                                2, EnemyMonster.BasicEnemies.WASP);
            }
            else if( randomNumber >= 10 && randomNumber < 20) //toad
            {
                createdEnemy = new EnemyMonster("Toad", 450 * statMult, 45 * statMult, (int) (30 * statMult),20 * statMult, (int)(10 * statMult),
                                                2, EnemyMonster.BasicEnemies.TOAD);
            }
            else if( randomNumber >= 20 && randomNumber < 30) //cat
            {
                createdEnemy = new EnemyMonster("Cat", 450 * statMult, 75 * statMult, (int) (25 * statMult),17 * statMult, (int)(10 * statMult),
                                                2, EnemyMonster.BasicEnemies.CAT);
            }
            else if( randomNumber >= 30 && randomNumber < 40) //elephant
            {
                createdEnemy = new EnemyMonster("Elephant", 1000 * statMult, 90 * statMult, (int) (5 * statMult),35 * statMult, (int)(5 * statMult),
                                                2, EnemyMonster.BasicEnemies.ELEPHANT);
            }
            else if( randomNumber >= 40 && randomNumber < 50) //snail
            {
                createdEnemy = new EnemyMonster("Snail", 300 * statMult, 45 * statMult, (int) (20 * statMult),45 * statMult, (int)(5 * statMult),
                                                 2, EnemyMonster.BasicEnemies.SNAIL);
            }
            else if( randomNumber >= 50 && randomNumber < 60) //crocodile
            {
                createdEnemy = new EnemyMonster("Crocodile", 600 * statMult, 85 * statMult, (int) (20 * statMult), 20 * statMult, (int)(10 * statMult),
                                                2, EnemyMonster.BasicEnemies.CROCODILE);
            }
            else if( randomNumber >= 60 && randomNumber < 70) //rhino
            {
                createdEnemy = new EnemyMonster("Rhino", 800 * statMult, 90 * statMult, (int) (5 * statMult),7 * statMult, (int)(5 * statMult),
                                                2, EnemyMonster.BasicEnemies.RHINO);
            }



        }


        if(fightNum == 5) //gorilla boss fight
        {
            createdEnemy = new BossMonster("Gorilla", 3000, 175, 3, 45, 5, 4);
        }


        return createdEnemy;

    }








    private ImageIcon getEnemyImage(Monster monster)
    {
        if(((EnemyMonster) monster).getEnemyType() == EnemyMonster.BasicEnemies.WASP)
        {
            image = new ImageIcon("images/wasp.png");
        }

        if(((EnemyMonster) monster).getEnemyType() == EnemyMonster.BasicEnemies.TOAD)
        {
            image = new ImageIcon("images/toad.png");
        }
        
        if(((EnemyMonster) monster).getEnemyType() == EnemyMonster.BasicEnemies.CAT)
        {
            image = new ImageIcon("images/cat.png");
        }

        if(((EnemyMonster) monster).getEnemyType() == EnemyMonster.BasicEnemies.ELEPHANT)
        {
            image = new ImageIcon("images/elephant.png");
        }

        if(((EnemyMonster) monster).getEnemyType() == EnemyMonster.BasicEnemies.SNAIL)
        {
            image = new ImageIcon("images/snail.png");
        }

        if(((EnemyMonster) monster).getEnemyType() == EnemyMonster.BasicEnemies.CROCODILE)
        {
            image = new ImageIcon("images/croc.png");
        }

        if(((EnemyMonster) monster).getEnemyType() == EnemyMonster.BasicEnemies.RHINO)
        {
            image = new ImageIcon("images/rhino.png");
        }

        if(((EnemyMonster) monster).getEnemyType() == EnemyMonster.BasicEnemies.GORILLA)
        {
            image = new ImageIcon("images/gorilla.png");
        }


        return image;
    }



    private void updateAbilityButtons(PlayerMonster monster)
    {
        //updates the input buttons' text and tooltip based on the type and number of attacks available
        //if no attack is available, set text and tooltip to " "

        for(int i = 0; i < 4; i++)
        {
            inputButtons[i].setText(" ");
            inputButtons[i].setToolTipText(" ");
        }
        
        if(monster.getMonsterChoice() == PlayerMonster.PlayerMonsterChoices.DRAGON)
        {
            switch(monster.getAttacks()) //do not write break statements, it will cascade intentionally
            {
                case 4:
                {
                    inputButtons[3].setText("Flames of Destruction");
                    inputButtons[3].setToolTipText("Shoot roaring fire to engulf and overwhelm your foes!");
                }
                case 3:
                {
                    inputButtons[2].setText("Dragon's Rage");
                    inputButtons[2].setToolTipText("Let the anger amplify your flames!");
                }
                case 2:
                {
                    inputButtons[1].setText("Scaly Menace");
                    inputButtons[1].setToolTipText("Your defense rises. Your enemy's crumbles.");
                }
                case 1:
                {
                    inputButtons[0].setText("Scorching Bite");
                    inputButtons[0].setToolTipText("Flames rush as you chomp on your foe.");
                }
            }
        }

                
        if(monster.getMonsterChoice() == PlayerMonster.PlayerMonsterChoices.VIPER)
        {
            switch(monster.getAttacks()) //do not write break statements, it will cascade intentionally
            {
                case 4:
                {
                    inputButtons[3].setText("Venom Burst");
                    inputButtons[3].setToolTipText("Each toxin and debuff affecting your enemy amplifies the damage of this attack!");
                }
                case 3:
                {
                    inputButtons[2].setText("Honing Gnaw");
                    inputButtons[2].setToolTipText("With each bite, your enemy weakens and your fangs become sharper.");
                }
                case 2:
                {
                    inputButtons[1].setText("Venomous Bite");
                    inputButtons[1].setToolTipText("Chomp down and inject your deadly venom.");
                }
                case 1:
                {
                    inputButtons[0].setText("Bite");
                    inputButtons[0].setToolTipText("Use your sharp and massive fangs to assault your foe.");
                }
            }
        }

                
        if(monster.getMonsterChoice() == PlayerMonster.PlayerMonsterChoices.MINOTAUR)
        {
            switch(monster.getAttacks()) //do not write break statements, it will cascade intentionally
            {
                case 4:
                {
                    inputButtons[3].setText("Berserk");
                    inputButtons[3].setToolTipText("Ignore defense and unleash a massive assault!");
                }
                case 3:
                {
                    inputButtons[2].setText("Adrenaline");
                    inputButtons[2].setToolTipText("Use your pumping adrenaline to keep fighting and recover from damage.");
                }
                case 2:
                {
                    inputButtons[1].setText("Terrifying Howl");
                    inputButtons[1].setToolTipText("Unleash a damaging, guttural howl and lower your opponent's resolve.");
                }
                case 1:
                {
                    inputButtons[0].setText("Horned Charge");
                    inputButtons[0].setToolTipText("Charge into the enemy, tearing through their defenses.");
                }
            }
        }
    }



    
}
