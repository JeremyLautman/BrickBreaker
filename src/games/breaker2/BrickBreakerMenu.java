package games.breaker2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BrickBreakerMenu extends JMenuBar{
    /** This creates the menu associated with BrickBreaker
     * 
     */
    private static final long serialVersionUID = -9178822993927106853L;
    protected final static int MENUHEIGHT = 20;
    // Do not delete parent. This will be needed to set up menu operations.
    // Only delete if unnecessary.
    private BrickBreaker parent;
    
    BrickBreakerMenu(int width,int height,BrickBreaker parent){
        this.parent=parent;
        setOpaque(true);
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(width, height));
        
        JMenu filemenu = new JMenu("File");
        filemenu.setMnemonic(KeyEvent.VK_F);
        filemenu.getAccessibleContext().setAccessibleDescription("Read me");
        JMenuItem newGame = new JMenuItem("New Game",KeyEvent.VK_N); 
        JMenuItem help = new JMenuItem("Help",KeyEvent.VK_H); 
        JMenuItem exitItem = new JMenuItem("Exit",KeyEvent.VK_X);;
        filemenu.add(newGame);
        filemenu.add(help);
        filemenu.add(exitItem);
        add(filemenu);
        
        JMenu optionsmenu = new JMenu("Options");
        filemenu.setMnemonic(KeyEvent.VK_O);
        add(optionsmenu);
    }
}
// End of BrickBreakerMenu