package games.breaker2;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6938312496164114923L;

	GameFrame(String title, int gameHeight, int gameWidth, BrickBreakerMenu menu, BrickBreakerPanel panel,BrickBreaker game){
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(gameWidth+((menu!=null)?BrickBreakerPanel.PANELWIDTH:0),
				gameHeight+((menu!=null)?BrickBreakerMenu.MENUHEIGHT:0));
		setJMenuBar(menu);
		getContentPane().add(panel,BorderLayout.LINE_START);
		getContentPane().add(game,BorderLayout.CENTER);
		setResizable(false);
		pack();
        setVisible(true);
	}
}
