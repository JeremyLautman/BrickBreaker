package games.breaker2;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BrickBreakerPanel extends JPanel {
    /**This is the side panel on a BrickBreaker game. It includes sections for
     * score, lives, and level, as well as a block of space for game
     * information.
     */
    private static final long serialVersionUID = 5142621745879229306L;
    protected final static int PANELWIDTH=200;
    JLabel scoreLabel,livesLabel,levelLabel;
    public BrickBreakerPanel(){
        //setSize(PANELWIDTH,BrickBreaker.GAMEHEIGHT);
        setPreferredSize(new Dimension(PANELWIDTH,BrickBreaker.GAMEHEIGHT));
        //setBackground(Color.GREEN);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        JPanel infopane = new JPanel();
        //infopane.setSize(PANELWIDTH, BrickBreaker.GAMEHEIGHT/2);
        infopane.setPreferredSize(new Dimension(PANELWIDTH,BrickBreaker.GAMEHEIGHT/2+BrickBreaker.GAMEHEIGHT%2));
        infopane.add(new JLabel("BrickBreaker, made"));
        infopane.add(new JLabel("by Jeremy Lautman"));
        add(infopane);
        JPanel dataDisplay = new JPanel();
        //dataDisplay.setSize(PANELWIDTH,BrickBreaker.GAMEHEIGHT/2+BrickBreaker.GAMEHEIGHT%2);
        dataDisplay.setPreferredSize(new Dimension(PANELWIDTH,BrickBreaker.GAMEHEIGHT/2+BrickBreaker.GAMEHEIGHT%2));
        dataDisplay.setLayout(new BoxLayout(dataDisplay,BoxLayout.Y_AXIS));
        scoreLabel = new JLabel("score: null");
        dataDisplay.add(scoreLabel);
        livesLabel = new JLabel("lives: null");
        dataDisplay.add(livesLabel);
        levelLabel = new JLabel("level: null");
        dataDisplay.add(levelLabel);
        add(dataDisplay);
    }
    protected void updatePanel(int score,int lives,int level){
        scoreLabel.setText("score: "+score);
        livesLabel.setText("lives: "+lives);
        levelLabel.setText("level: "+level);
    }
}
