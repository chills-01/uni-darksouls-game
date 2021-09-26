package game.interfaces;

/**
 * Interface that enforces actors consuming items to have certain methods
 */
public interface ConsumeAbility {
    /**
     * gets the maximum hit points available to the actor
     * @return integer - maximum hit points
     */
    int getMaxHitPoints();
}
