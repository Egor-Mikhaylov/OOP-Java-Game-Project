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
*/
import javax.swing.JFrame; //for window
import javax.swing.JPanel; //for arranging componenets
import javax.swing.JLabel; //for button text, player images, and names
import javax.swing.JButton; //for player input
import javax.swing.JDialog; //for when the player wins or loses, when the game is reset
import javax.swing.JProgressBar; //for monster health bars
import javax.swing.JTextArea;
import javax.swing.SwingConstants; //constants to arrange components
import java.awt.event.*; //handling button events
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.ImageIcon;

public class GUI extends JFrame {

    //need 2 progress bars, 4 buttons, 8 labels, 2 panels

    //progress bars for player and enemy HP BAR
    //4 buttons for player input, each will have a label for player attacks and choosing their monster
    //4 labels for button text, 2 for monster images - player monster and enemy monster, 2 for monster names in window
    // 1 panel for button grid, another panel for game window, which arrange everything but the 4 buttons

    private JProgressBar playerHPBAR;
    private JProgressBar enemyHPBAR;

    private JPanel gamePanel;

    private JLabel buttonText[]; //4 button labels
    private JLabel playerMonsterName;
    private JLabel playerMonsterImage;
    private JLabel enemyMonsterName;
    private  JLabel enemyMonsterImage;

    private  JButton inputButtons[]; //four input buttons

    private JDialog alertDialogBox; //use this for when the player wins or loses, then reset the game

    private JTextArea outputTextArea; //all actions will be output here


    private final int WINDOWWIDTH = 720;
    private final int WINDOWHEIGHT = 680;

    private boolean playerIsChoosingAMonster; // true if game has just started/restarted
                                            // false if the player is going through the 5 fights
                                         // if the player dies or wins, this turns true at some point



    private Monster player; //this will hold the player's monster
    private Monster enemy; //this will hold the enemy's monster, basic or boss type


    public GUI()
    {
        super("Monster Fighter!");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOWWIDTH, WINDOWHEIGHT);
        setResizable(false);


        //arrange all components into the frame into exact spots

        gamePanel = new JPanel(null);




        inputButtons = new JButton[4];
        for(int i = 0; i < 4; i++)
        {
            inputButtons[i] = new JButton("Button " + (i+1));
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

        ImageIcon playerImage = new ImageIcon("images/dragon.png");
        playerMonsterImage = new JLabel(playerImage);
        playerMonsterImage.setBounds(17, 45, 330, 300);
        
        gamePanel.add(playerMonsterImage);


        ImageIcon enemyImage = new ImageIcon("images/snail.png");
        enemyMonsterImage = new JLabel(enemyImage);
        enemyMonsterImage.setBounds(357, 45, 330, 300);

        gamePanel.add(enemyMonsterImage);



        //SET ALL COMPONENTS OTHER THAN BUTTONS TO INVISIBLE,
        //then display the player monster choices to the player

        this.add(gamePanel);

        playerIsChoosingAMonster = true; // button logic switched to choosing a monster

        setVisible(true);
        
    }

    
}
