package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.ground.Valley;
import game.interfaces.Soul;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul {

	private final Menu menu = new Menu();
	protected int currentSouls;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.currentSouls = 0;
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.REST);
		this.addCapability(Abilities.ENTER_FLOOR);

		//creating Estus flask that is stored in Player's inventory
		this.addItemToInventory(new EstusFlask());
		//creating TokenOfSouls that is stored in Player's inventory
		this.addItemToInventory(new TokenOfSouls());
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if (!this.isConscious()) {
			//todo reset here
			System.out.println("Player is dead.");
			System.exit(0);
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// kill if on valley
		Location playerLocation = map.locationOf(this);
		Ground groundType = playerLocation.getGround();
		if (groundType instanceof Valley){
			this.hurt(1000); // do a lot of damage

		}



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



}
