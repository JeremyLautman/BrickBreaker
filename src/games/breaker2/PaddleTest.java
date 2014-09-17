package games.breaker2;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PaddleTest {

    /**Testing game of BrickBreaker*/
    static BrickBreaker testgame;
    private static final long SLEEPDELAY = 100;

    @Before
    public final void createGame() {
        // Create a testgame to be used for running tests.
        try {
            javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                public void run() { testgame = new BrickBreaker(); }
            });
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**Tests the update function's functionality. Update is called on every game
     * timer firing.
     *
     * The update function has to move the paddle by the paddle's x velocity
     * and not move in the y direction. It also can not move the paddle past
     * the game window's boundaries.
     * */
    @Test
    public final void testUpdate() {
        // Test that the update function can move a paddle.
        Robot robot;
        try {
            robot = new Robot();
            Field paddleField, directionField;
            Paddle paddle;

            // Check that direction starts at 0
            {
                // Gain access to the paddle
                paddleField = testgame.getClass().getDeclaredField("paddle");
                paddleField.setAccessible(true);
                paddle = (Paddle) paddleField.get(testgame);
                // Gain access to the direction field of the paddle
                directionField =
                        paddle.getClass().getDeclaredField("direction");
                directionField.setAccessible(true);

                assertEquals(0, directionField.get(paddle));
            }

            // Check that when 'a' is pressed direction is set to -1
            {
                // Press 'A'
                robot.keyPress(KeyEvent.VK_A);
                // The game needs a little time to register the key
                Thread.sleep(SLEEPDELAY);

                // Gain access to the paddle
                paddleField = testgame.getClass().getDeclaredField("paddle");
                paddleField.setAccessible(true);
                paddle = (Paddle) paddleField.get(testgame);
                directionField =
                        paddle.getClass().getDeclaredField("direction");
                directionField.setAccessible(true);

                assertEquals(-1, directionField.get(paddle));
            }

            // Check that when 'a' is released direction is reset to 0
            {
                // Release 'A'
                robot.keyRelease(KeyEvent.VK_A);
                // The game needs a little time to register the key
                Thread.sleep(SLEEPDELAY);

                // Gain access to the paddle
                paddleField = testgame.getClass().getDeclaredField("paddle");
                paddleField.setAccessible(true);
                paddle = (Paddle) paddleField.get(testgame);
                directionField =
                        paddle.getClass().getDeclaredField("direction");
                directionField.setAccessible(true);

                assertEquals(0, directionField.get(paddle));
            }

            // Check that when 'd' is pressed with 'a' released direction is set
            // to 1
            {
                // Press 'D'
                robot.keyPress(KeyEvent.VK_D);
                // The game needs a little time to register the key
                Thread.sleep(SLEEPDELAY);

                // Gain access to the paddle after a change
                paddleField = testgame.getClass().getDeclaredField("paddle");
                paddleField.setAccessible(true);
                paddle = (Paddle) paddleField.get(testgame);
                directionField =
                        paddle.getClass().getDeclaredField("direction");
                directionField.setAccessible(true);

                assertEquals(1, directionField.get(paddle));
            }

            // Check that when 'd' is released direction is reset to 0
            {
                // Release 'D'
                robot.keyRelease(KeyEvent.VK_D);
                // The game needs a little time to register the key
                Thread.sleep(SLEEPDELAY);

                // Gain access to the paddle after a change
                paddleField = testgame.getClass().getDeclaredField("paddle");
                paddleField.setAccessible(true);
                paddle = (Paddle) paddleField.get(testgame);
                directionField =
                        paddle.getClass().getDeclaredField("direction");
                directionField.setAccessible(true);
                assertEquals(0, directionField.get(paddle));
            }

            // Check that when two direction buttons are pressed, the first
            // one pressed is dominant
            {

                // Press keys, a first.
                robot.keyPress(KeyEvent.VK_A);
                // The game needs a little time to register the key
                Thread.sleep(SLEEPDELAY);
                robot.keyPress(KeyEvent.VK_D);
                // The game needs a little time to register the key
                Thread.sleep(SLEEPDELAY);

                // Gain access to the paddle after a change
                paddleField = testgame.getClass().getDeclaredField("paddle");
                paddleField.setAccessible(true);
                paddle = (Paddle) paddleField.get(testgame);
                directionField =
                        paddle.getClass().getDeclaredField("direction");
                directionField.setAccessible(true);

                assertEquals(-1, directionField.get(paddle));

//                // Release D. A should stay active.
//                robot.keyRelease(KeyEvent.VK_D);
//                // The game needs a little time to register the key. This case
//                // may take a little longer than usual
//                Thread.sleep(SLEEPDELAY);
//
//                // Gain access to the paddle after a change
//                paddleField = testgame.getClass().getDeclaredField("paddle");
//                paddleField.setAccessible(true);
//                paddle = (Paddle) paddleField.get(testgame);
//                directionField =
//                        paddle.getClass().getDeclaredField("direction");
//                directionField.setAccessible(true);
//
//                assertEquals(-1, directionField.get(paddle));
//
//                // Reapply D and then release A. D should go active.
//                robot.keyPress(KeyEvent.VK_D);
//                // The game needs a little time to register the key
//                Thread.sleep(SLEEPDELAY);
//                robot.keyRelease(KeyEvent.VK_A);
//                // The game needs a little time to register the key
//                Thread.sleep(SLEEPDELAY);
//
//                // Gain access to the paddle after a change
//                paddleField = testgame.getClass().getDeclaredField("paddle");
//                paddleField.setAccessible(true);
//                paddle = (Paddle) paddleField.get(testgame);
//                directionField =
//                        paddle.getClass().getDeclaredField("direction");
//                directionField.setAccessible(true);
//
//                assertEquals(1, directionField.get(paddle));
            }

        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            fail("Can't access the direction field.");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("The direction field does not exist.");
        } catch (AWTException e) {
            e.printStackTrace();
            fail("Can't create the testing robot.");
        } catch (InterruptedException e1) {
            // Fired when the sleep function fails
            e1.printStackTrace();
        } finally {
            robot = null;
        }
    }

}
