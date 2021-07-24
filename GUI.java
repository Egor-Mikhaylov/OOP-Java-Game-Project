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

import javax.swing.SwingConstants; //constants to arrange components
import java.awt.event.*; //handling button events
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class GUI extends JFrame {

    //need 2 progress bars, 4 buttons, 8 labels, 2 panels

    //progress bars for player and enemy HP BAR
    //4 buttons for player input, each will have a label for player attacks and choosing their monster
    //4 labels for button text, 2 for monster images - player monster and enemy monster, 2 for monster names in window
    // 1 panel for button grid, another panel for game window, which arrange everything but the 4 buttons

    private JProgressBar playerHPBAR;
    private JProgressBar enemyHPBAR;

    private JPanel topGamePanel;
    private JPanel bottomButtonPanel;

    private JLabel buttonText[]; //4 button labels
    private JLabel playerMonsterName;
    private JLabel playerMonsterImage;
    private JLabel enemyMonsterName;
    private  JLabel enemyMonsterImage;

    private  JButton inputButons[]; //four input buttons

    private JDialog alertDialogBox; //use this for when the player wins or loses, then reset the game





    public GUI()
    {
        super("Monster Fighter!");
    }

    
}
