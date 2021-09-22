package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.ground.Valley;
import game.interfaces.Resettable;
import game.interfaces.Soul;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, Resettable {

	private final Menu menu = new Menu();
	protected int currentSouls;
	private Location playerLocation;
	private Location bonfireLocation;

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

		ResetManager.getInstance().appendResetInstance(this);

		// set initial bonfire location
		this.bonfireLocation = bonfireLocation;





		//creating Estus flask that is stored in Player's inventory
		this.addItemToInventory(new EstusFlask());
		//creating TokenOfSouls that is stored in Player's inventory
		this.addItemToInventory(new TokenOfSouls());
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// display hp
		display.println("Health" + "(" + hitPoints + "/" + maxHitPoints + ")");

		if (!this.isConscious()) {
			//todo reset here
//			display.println("Player is dead.");
//			System.exit(0);
			// resetManager
			return new ResetAction(this, bonfireLocation, playerLocation);
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();


		// kill if on valley
//		playerLocation = map.locationOf(this);
//		Ground ground = playerLocation.getGround();
//		if (ground instanceof Valley) {
//			ground.allowableActions(this, playerLocation, "");
//		}

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
	}

	/**
	 * Allows any classes that use this interface to reset abilities, attributes, and items.
	 * TODO: Use this method in a reset manager to run the soft-reset.
	 */
	public void resetInstance() {
		heal(Integer.MAX_VALUE); // heal to max health

	}

	/**
	 * A useful method to clean up the list of instances in the ResetManager class
	 * @return the existence of the instance in the game.
	 * for example, true to keep it permanent, or false if instance needs to be removed from the reset list.
	 */
	public boolean isExist() {
		return true;

	}

	/**
	 * a default interface method that register current instance to the Singleton manager.
	 * TODO: Use this method at the constructor of `this` instance.
	 */
	public void registerInstance(){
		ResetManager.getInstance().appendResetInstance(this);
	}
}
