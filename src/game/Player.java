package game;

import edu.monash.fit2099.engine.*;
import game.actions.ResetAction;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.ConsumeAbility;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.items.EstusFlask;
import game.weapons.Broadsword;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, Resettable, ConsumeAbility {

	private final Menu menu = new Menu();
	protected int currentSouls;
	private Location playerLocation;
	private Location bonfireLocation;
	private Location previousLocation;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints, Location bonfireLocation) {
		super(name, displayChar, hitPoints);
		this.currentSouls = 0;
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.REST);
		this.addCapability(Abilities.ENTER_FLOOR);
		this.addCapability(Abilities.FALL_FROM_VALLEY);
		this.addCapability(Abilities.CAN_ENTER_FOG_DOOR);

		// registers as resettable
		registerInstance();


		// set initial bonfire location
		this.bonfireLocation = bonfireLocation;


		//creating Estus flask that is stored in Player's inventory
		this.addItemToInventory(new EstusFlask());

		// create this when death occurs
		//creating TokenOfSouls that is stored in Player's inventory
//		this.addItemToInventory(new TokenOfSouls());


		//creating Broadsword that is stored in Player's inventory
		this.addItemToInventory(new Broadsword());
	}

	public Location getBonfireLocation() {
		return bonfireLocation;
	}
	public Location getPreviousLocation() {
		return previousLocation;
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// display hp and souls every turn
		display.println("Health" + "(" + hitPoints + "/" + maxHitPoints + "), " + "Souls: " + currentSouls + ", Holding: " + this.getWeapon().toString());

		playerLocation = map.locationOf(this);


		if (!this.isConscious()) {
			return new ResetAction(this, bonfireLocation, playerLocation, previousLocation);
		}

		//Check if player is disarmed
		if (this.hasCapability(Status.DISARM)) {
			removeCapability(Status.HOSTILE_TO_ENEMY);
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// store previous location
		previousLocation = playerLocation;

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public boolean addSouls(int souls) {
		currentSouls += souls;
		return true;
	}

	@Override
	public void transferSouls(Soul soulObject) {
		//TODO: transfer Player's souls to another Soul's instance.
		soulObject.addSouls(currentSouls);
		currentSouls = 0;
	}

	public boolean setHitPoints(int newHitPoints) {
		boolean flag = false;
		if (newHitPoints <= this.getMaxHitPoints()) {
			this.hitPoints = newHitPoints;
			flag = true;
		}
		return flag;
	}
	/**
	 * Allows any classes that use this interface to reset abilities, attributes, and items.
	 */
	public void resetInstance() {
		hitPoints = maxHitPoints;

	}

	/**
	 * A useful method to clean up the list of instances in the ResetManager class
	 *
	 * @return the existence of the instance in the game.
	 * for example, true to keep it permanent, or false if instance needs to be removed from the reset list.
	 */
	public boolean isExist() {
		return true;

	}

	@Override
	public int getMaxHitPoints() {
		return maxHitPoints;
	}
}
