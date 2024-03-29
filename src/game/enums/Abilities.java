package game.enums;

/**
 * Enum that represents an ability of Actor, Item, or Ground.
 */
public enum Abilities {
    REST,
    ENTER_FLOOR, // can stand on floor
    FALL_FROM_VALLEY, // actor dies from falling
    CRITICAL_HIT, // weapons passive skill
    WEAK_TO_STORM_RULER, // Yhorm the Giant weak to weapon
    CAN_ENTER_FOG_DOOR, // player only can move through maps
    CAN_DROP_TOKEN_OF_SOULS //actor can drop the token
}
