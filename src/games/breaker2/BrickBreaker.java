/****************************************************************************** 
 * "Brickbreaker" game.
 *  @author Jeremy Lautman
 *  Last updated 08/10/2014
 * 
 *****************************************************************************/

package games.breaker2;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

/**This class creates a BrickBreaker game attached to a GameFrame.
 * The game frame also contains a BrickBreakerMenu and a BrickBreakerPanel,
 * but for ease of access references to both are saved as instance
 * variables.
 */
public final class BrickBreaker extends JPanel {
    /**Required variable for extending the JPanel class. Unknown functionality.
     * */
    private static final long serialVersionUID = -7692503649521779173L;
    /**Game invariants.*/
    protected static final int BRICKHEADSPACE = 100, GAMEWIDTH = 900,
            GAMEHEIGHT = 700, BRICKROWS = 6, BRICKCOLS = 10;
    /**Static variable to control the time between each timer update.
     * */
    private static final long TIMERINTERVAL = 20L;

// Instance variables
    /** Instance variables for score, lives, and level number.*/
    private int score, lives, level;

    /**Game GameFrame.*/
    private GameFrame frame; // Do I need this?

    /**Game status panel.*/
    private BrickBreakerPanel panel;

    /**Boolean describing whether the game is currently active.*/
    private boolean gameActive;

    /**Collection of the game's bricks.*/
    private Collection<Brick> bricks;

    /**Collection of the game's balls.*/
    private Collection<Ball> balls;

    /**Timer that fires to update the game.*/
    private Timer gameTimer;

    /** Paddle for the game. */
    private Paddle paddle;

    /**Constructor for a BrickBreaker game.
     */
    public BrickBreaker() {
        panel = new BrickBreakerPanel();
        //build the frame and attach everything to it.
        frame = new GameFrame(
                "Brickbreaker by Jeremy",
                GAMEHEIGHT,
                GAMEWIDTH,
                new BrickBreakerMenu(
                        GAMEWIDTH + BrickBreakerPanel.PANELWIDTH,
                        BrickBreakerMenu.MENUHEIGHT,
                        this),
                panel,
                this);
        gameActive = false;
        gameTimer = new Timer();
        // This section creates all of the default game objects which includes
        // bricks and a paddle.
        // Use absolute positioning for the game objects
        this.setLayout(null);

        //make a paddle
        paddle = new Paddle();
        add(paddle);
        paddle.repaint();
        //make the bricks
        bricks = new LinkedList<Brick>();
        for (int row = 0; row < BRICKROWS; row++) {
            for (int col = 0; col < BRICKCOLS; col++) {
                bricks.add(new Brick(col * Brick.BRICKWIDTH,
                        row * Brick.BRICKHEIGHT + BRICKHEADSPACE,
                        Color.RED));
            }
        }
        for (Brick x:bricks) {
            add(x);
            x.repaint();
        }
        balls = new LinkedList<Ball>();
        // Set up the mouse listener to place the game ball. The on-click sets
        // the ball's location and starts the game
        this.addMouseListener(
                new MouseListener() {
                    public void mouseClicked(final MouseEvent arg0) { return; }
                    public void mouseEntered(final MouseEvent arg0) { return; }
                    public void mouseExited(final MouseEvent arg0) { return; }
                    public void mousePressed(final MouseEvent arg0) {
                        if (arg0.getButton() != MouseEvent.BUTTON1) {
                            return; //only care about button 1
                        }
                        // create the new ball at the click location.
                        Ball newBall =
                                new Ball(arg0.getX() - Ball.DEFAULTRADIUS,
                                        arg0.getY() - Ball.DEFAULTRADIUS,
                                        Ball.DEFAULTRADIUS);
                        balls.add(newBall);
                        add(newBall);
                        newBall.repaint();
                        startGame();
                    }
                    public void mouseReleased(final MouseEvent arg0) { return; }
                }
        );
    }

    /**Function called to start the Brickbreaker game.
     * */
    private void startGame() {
        if (!gameActive) {
            gameActive = true;
            // Start the timer
            gameTimer.schedule(new TimerTask() {
                /** Update the game by moving all objects as needed. The timer
                 *  fires every interval defined in TIMERINTERVAL.
                 */
                public synchronized void run() {
                    paddle.update();
                    for (Iterator<Ball> iterator = balls.iterator();
                            iterator.hasNext();) {
                        Ball x = iterator.next();
                        x.update();
                        // See if the ball needs to be removed.
                        if (x.getY() > BrickBreaker.GAMEHEIGHT) {
                            // The ball is off of the screen
                            // WARNING!!! THIS WILL BREAK IF THE COLLECTION'S
                            // ITERATOR DOES NOT SUPPORT REMOVE.
                            iterator.remove();
                            continue;
                        }
                        // Check if the Ball hit the Paddle and update accordingly.
                        paddle.hitBall(x);
                    }
                    //System.out.println(String.format("%d Balls in play.", balls.size()));
                    //balls.removeAll(toRemove);
                    if (balls.isEmpty()) {
                        gameActive = false;
                        this.cancel();
                    }
                }
            },
            0L, // Start immediately
            BrickBreaker.TIMERINTERVAL
            );
        }
    }

    /**Main function for BrickBreaker. Swing is not thread safe so the program
     * must be run using invokeLater.
     * @param args Parameters passed in when the program is run. Not used.
     */
    public static void main(final String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BrickBreaker();
            }
        });
    }
}
// End of BrickBreaker.java