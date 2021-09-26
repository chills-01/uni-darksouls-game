package game.enums;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this capability to be hostile towards something (e.g., to be attacked by enemy)
    DISARM, // use this capability to disarm an actor temporarily
    STUNNED, // used this capability to temporarily stun actor (Yhorm the Giant)
    DISABLE_CRITICAL_STRIKE,
    STORMRULER_FULLY_CHARGED
}
