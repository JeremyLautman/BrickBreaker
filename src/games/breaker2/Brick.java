package games.breaker2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**Brick class for a Brickbreaker game. Each brick has a color. They also have
 * a location and size which is handled by JPanel.
 * */
public class Brick extends JPanel {
    /**Required variable for a JPanel.
     */
    private static final long serialVersionUID = 2149794133964748669L;

    /**Private Brick parameters.
     * */
    private static final int CORNERROUND = 10, BRICKSPACING = 1;

    /**Public Brick parameters.
     * */
    public static final int BRICKWIDTH = 90, BRICKHEIGHT = 20;

    /**Instance variable for the color of the brick.
     * */
    private Color color;

    /**Constructor for a Brick.
     * @param xloc The Brick's x location.
     * @param yloc The Brick's y location.
     * @param newColor The Brick's color.
     * */
    public Brick(final int xloc, final int yloc, final Color newColor) {
        this.color = newColor;
        //Note: can pull location using getLocation()
        setLocation(xloc, yloc);
        setSize(new Dimension(BRICKWIDTH, BRICKHEIGHT));
        //setBorder(BorderFactory.createLineBorder(Color.black));
    }

    @Override
    public final void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.fillRoundRect(BRICKSPACING, BRICKSPACING,
                BRICKWIDTH - (2 * BRICKSPACING),
                BRICKHEIGHT - (2 * BRICKSPACING),
                CORNERROUND, CORNERROUND);
    }

    @Override
    public final String toString() {
        StringBuilder result = new StringBuilder();
        result.append(BRICKWIDTH);
        result.append("x");
        result.append(BRICKHEIGHT);
        result.append(" Brick at (");
        result.append(getLocation().toString());
        result.append(") with color ");
        result.append(color.toString());
        return result.toString();
    }
}
