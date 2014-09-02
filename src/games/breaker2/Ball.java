package games.breaker2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

/**The <em>Ball</em> class describes a ball. A ball has location, velocity,
 * and multiplier, and can hit a wall, paddle, or brick.
 */
class Ball extends JPanel implements Updatable {
    /**Default variables for a newly created ball.
     */
    public static final int DEFAULTYVEL = 4, DEFAULTXVEL = 3,
            DEFAULTRADIUS = 10;
    /**Serial Version UID.
     */
    private static final long serialVersionUID = 7570721548823893789L;
    /**Velocity variables. They are initialized to the default values.
     */
    private int xVelocity, yVelocity;
    /**Holds the Ball's score multiplier. It gets set to 1 every time the
     * ball hits the paddle and doubles every time it hits a brick.
     */
    private int scoreMultiplier = 1;

    /** The ball's radius.
     */
    private final int radius;

    /** Constructor for a Ball given an x location and a y location.
     *  @param xLoc The new X location.
     *  @param yLoc The new Y location.
     *  @param rad  The new Ball's radius.
     */
    public Ball(final int xLoc, final int yLoc, final int rad) {
        setLocation(xLoc, yLoc);
        radius = rad;
        setSize(rad * 2, rad * 2);
        xVelocity = DEFAULTXVEL;
        yVelocity = DEFAULTYVEL;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fill(new Ellipse2D.Double(0, 0, 2 * radius, 2 * radius));
    }

    @Override
    public String toString() {
        return String.format(
                "Ball at (%d, %d) with radius %d and velocity (%d, %d)",
                getLocation().x, getLocation().y,
                radius, xVelocity, yVelocity);
    }

    /** Gets the X component of the velocity.
     *  @return The X component of the velocity.
     */
    public int getXVelocity() { return xVelocity; }

    /** Gets the Y component of the velocity.
     *  @return The Y component of the velocity.
     */
    public int getYVelocity() { return yVelocity; }

    /**Function for querying a ball for it's radius.
     * @return The ball's radius.*/
    protected int getRadius() { return radius; }

    /**Sets the X component of the ball velocity.
     * @param xVel The new x velocity.
     * */
    public void setXVelocity(final int xVel) { this.xVelocity = xVel; }

    /**Sets the Y component of the ball velocity.
     * @param yVel The new y velocity.
     * */
    public void setYVelocity(final int yVel) { this.yVelocity = yVel; }

    /**Function for getting the current score multiplier.
     * @return The current score multiplier.*/
    protected int getScoreMultiplier() { return scoreMultiplier; }

    /**Function for setting a score multiplier for the ball.
     * @param newMultiplier The new multiplier for the ball.
     * */
    protected void setScoreMultiplier(final int newMultiplier) {
        scoreMultiplier = newMultiplier;
    }

    /**Update function called each timer cycle.
     * */
    public void update() {
        // Move the ball.
        setLocation(getX() + getXVelocity(), getY() + getYVelocity());

        // Then do any corrections that result from collisions.
        // Each of these are negative if a collision occurs
        int distanceFromLeft = getX() - this.radius;
        int distanceFromRight = BrickBreaker.GAMEWIDTH - (getX() + this.radius);
        // NOTE! The top of the screen is defined as 0, not the bottom.
        int distanceFromTop = getY() - this.radius;

        if (distanceFromRight <= 0) {
            // Correct by moving the ball left by 2x the overlap
            setLocation(getX() + (2 * distanceFromRight), getY());
            if (getXVelocity() > 0) { setXVelocity(getXVelocity() * -1); }
        }
        if (distanceFromLeft <= 0) {
            // Correct by moving the ball right by 2x the overlap.
            setLocation(getX() - (2 * distanceFromLeft), getY());
            if (getXVelocity() < 0) { setXVelocity(getXVelocity() * -1); }
        }
        // NOTE! The top of the screen is defined as 0, not the bottom.
        if (distanceFromTop <= 0) {
            // Correct by moving the ball down by 2x the overlap.
            setLocation(getX(), getY() - (2 * distanceFromTop));
            if (getYVelocity() < 0) { setYVelocity(getYVelocity() * -1); }
        }
        // Removing the ball is handled by the timer.
        // Bouncing off the paddle is handled by the timer.
        // Bouncing and destroying bricks is handled by the timer.
    }
}
