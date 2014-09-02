package games.breaker2;

/** Interface Updatable.
 *  All objects in the game that need to be updated by the timer must implement
 *  this interface.
 */
abstract interface Updatable {
    /** The update method is called by the Timer whenever the timer fires and
     *  the object needs to be updated.
     */
    void update();
}
