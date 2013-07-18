package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.tree.DefaultTreeModel;

import model.Time.Hour;
import nodes.Dialogue;
import nodes.GameElement;
import nodes.GameNode;
import nodes.Item;
import nodes.Location;
import nodes.Person;
import nodes.PlayerCharacter;

public class Gamestate extends Observable {

	final static boolean				DEBUG		= false;

	/**
	 * Singleton static gamestate object from which all ui elements can pull from
	 */

	public static Gamestate			current	= new Gamestate();

	public Time							time;

	public GameNode					rootNode;
	public DefaultTreeModel			treeModel;
	
	public GameNode					pcNode;
	public GameNode					focus;
	public PlayerCharacter			playerCharacter;

	public static ActionListener	locationListener;

	public void createLocationListener() {
		locationListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("parent")) {
					treeModel.insertNodeInto(pcNode, pcLocNode().getParent(), 0);
				} else {
					GameNode[] options = pcLocNode().getLocations();
					for (int x = 0; x < options.length; x++) {
						if (options[x].getName().equals(e.getActionCommand())) {
							treeModel.insertNodeInto(pcNode, options[x], 0);
						}
					}
				}
				focus = pcLocNode();
				time.nextHour();
				Gamestate.current.setChanged();
				Gamestate.current.notifyObservers();
			}
		};
	}

	public static ActionListener	personListener;

	public void createPersonListener() {
		personListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameNode[] options = pcLocNode().getPeople();
				for (int x = 0; x < options.length; x++) {
					if (options[x].getName().equals(e.getActionCommand())) {
						focus = options[x];
					}
				}
				Gamestate.current.setChanged();
				Gamestate.current.notifyObservers();
			}
		};
	}

	public static ActionListener	itemListener;

	public void createItemListener() {
		itemListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameNode[] options = pcLocNode().getItems();
				for (int x = 0; x < options.length; x++) {
					if (options[x].getName().equals(e.getActionCommand())) {
						focus = options[x];
					}
				}
				Gamestate.current.setChanged();
				Gamestate.current.notifyObservers();
			}
		};
	}

	public static ActionListener	pcListener;

	public void createPcListener() {
		pcListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				focus = pcNode;
				Gamestate.current.setChanged();
				Gamestate.current.notifyObservers();
			}
		};
	}

	/**
	 * Constructor. Builds the world. Gamestate is a singleton object, so this is
	 * called only once to create the static object Gamestate.current.
	 */
	private Gamestate(){
		createSampleSetup01();
		createPcListener();
		createLocationListener();
		createPersonListener();
		createItemListener();
	}

	public static GameNode pcLocNode() {
		return current.pcNode.getParent();
	}

	public static GameElement pcLocObj() {
		return pcLocNode().getUserObject();
	}

	public static String pcLocName() {
		return pcLocObj().getName();
	}

	public static String pcLocDesc() {
		return pcLocObj().getDescription();
	}

	public void movePC(GameNode l) {
		pcNode.go(l);
	}

	public static Hour currentTime() {
		return current.time.currentTime();
	}

	/**
	 * Creates a sample setup consisting of a hub containing two locations, a
	 * house and road. The house contains a living room and a kitchen, while the
	 * road contains a woods, a cliffs, and a shore. The cliffs contains a cave.
	 */
	private void createSampleSetup01() {
		{

			time = new Time();

			playerCharacter = new PlayerCharacter("You");
			playerCharacter.setDescription("Player Character");
			playerCharacter
					.setVerbose("The node which represents the player."
							+ " The player has access to nodes in the same location as itself.");

			System.out.println(playerCharacter.getNode());
			pcNode = new GameNode(playerCharacter);
			System.out.println(playerCharacter.getNode());

			Location hub = new Location("Hub");
			hub.setDescription("The central hub of the game.");
			hub.setVerbose("This node acts as the root of the tree which contains all other nodes.");
			rootNode = new GameNode(hub);

			treeModel = new DefaultTreeModel(rootNode);

			// add the player to the hub
			movePC(rootNode);
			focus = rootNode;

			{
				Location house = new Location("House");
				house.setDescription("A small house.");
				house.setVerbose("This acts as a sample location to travel to.");
				GameNode houseNode = new GameNode(house);
				rootNode.add(houseNode);

				{
					Item pictureOfWoman = new Item("Woman portrait");
					pictureOfWoman
							.setDescription("A picture hanging on the wall of a pretty young woman.");
					pictureOfWoman.setVerbose("This acts as an Item in the house.");
					GameNode pictureOfWomanNode = new GameNode(pictureOfWoman);
					houseNode.add(pictureOfWomanNode);

					Person ghostWoman = new Person("Ghost of woman");
					ghostWoman
							.setDescription("A ghostly woman haunting the hallway");
					ghostWoman
							.setVerbose("This is an invisible person in the House.");
					ghostWoman.setVisible(false);
					GameNode ghostWomanNode = new GameNode(ghostWoman);
					houseNode.add(ghostWomanNode);

					Person ghostMan = new Person("Ghost of man");
					ghostMan.setDescription("A ghostly woman haunting the hallway");
					ghostMan.setVerbose("This is an invisible person in the House.");
					ghostMan.setVisible(false);
					GameNode ghostManNode = new GameNode(ghostMan);
					houseNode.add(ghostManNode);

					Location house_LivingRoom = new Location("Living room");
					house_LivingRoom
							.setDescription("The main entryway of the house.");
					house_LivingRoom
							.setVerbose("This acts as a sub-location of the house.");
					GameNode livingRoomNode = new GameNode(house_LivingRoom);
					houseNode.add(livingRoomNode);

					{
						Person dad = new Person("Dad");
						dad.setDescription("Your dead old dad is reclining in his easy chair.");
						dad.setVerbose("This is a person in the living room.");
						GameNode dadNode = new GameNode(dad);
						livingRoomNode.add(dadNode);

						Item telephone = new Item("Telephone");
						telephone.setDescription("The old family telephone.");
						telephone
								.setVerbose("This acts as an item in the living room.");
						// the trouble with adding nodes in the following way is that
						// you canned reference the node again by name
						// although you could call the getNode() method of the data
						// object telephone
						livingRoomNode.add(new GameNode(telephone));

						Person dog = new Person("Bundles the Pup");
						dog.setDescription("Your faithful pet dog Bundles.");
						dog.setVerbose("This acts as a second person node in the lviing room.");
						livingRoomNode.add(new GameNode(dog));
					}

					Location house_Kitchen = new Location("Kitchen");
					house_Kitchen.setDescription("A cozy place to prepare food.");
					house_Kitchen.setVerbose("This acts as a second sub-location.");
					GameNode kitchenNode = new GameNode(house_Kitchen);
					houseNode.add(kitchenNode);
				}
			}

			{
				Person man = new Person("Old man");
				man.setDescription("An old man out for a walk.");
				man.setVerbose("This acts as a person node in the hub.");

				GameNode manNode = new GameNode(man);
				rootNode.add(manNode);

				Person rabbit = new Person("Hiding rabbit");
				rabbit.setDescription("A small rabbit hiding in its hole.");
				rabbit.setVerbose("This person is initially hidden.");
				rabbit.setVisible(false);
				GameNode rabbitNode = new GameNode(rabbit);
				rootNode.add(rabbitNode);
			}

			{
				Location road = new Location("Road");
				road.setDescription("A winding country road.");
				road.setVerbose("This acts as another location in the hub.");
				GameNode roadNode = new GameNode(road);
				rootNode.add(roadNode);

				{
					Location woods = new Location("Woods");
					woods.setDescription("A spookey woods.");
					woods.setVerbose("This acts as a sub-location of road.");
					GameNode woodsNode = new GameNode(woods);
					roadNode.add(woodsNode);

					{
						Person lumberjack = new Person("LumberJack");
						lumberjack
								.setDescription("A burley lumberjack chopping wood.");
						lumberjack
								.setVerbose("This acts as a person in the woods with a dialogue and an item.");
						GameNode lumberjackNode = new GameNode(lumberjack);
						woodsNode.add(lumberjackNode);

						Item axe = new Item("Lumberjack's axe");
						axe.setDescription("A heavy iron axe for chopping trees.");
						axe.setVerbose("This is an item on the lumberjack.");
						GameNode axeNode = new GameNode(axe);
						lumberjackNode.add(axeNode);

						Dialogue lumberjackDialogue1 = new Dialogue("Ask name");
						lumberjackDialogue1.setDescription("\"What is your name?\"");
						lumberjackDialogue1
								.setVerbose("This acts as a question you can ask the lumberjack.");
						GameNode lumberjackDia1Node = new GameNode(
								lumberjackDialogue1);
						lumberjackNode.add(lumberjackDia1Node);

					}

					Location cliffs = new Location("Cliffs");
					cliffs.setDescription("Some craggy cliffs.");
					cliffs.setVerbose("This acts as a second sub-location of road.");
					GameNode cliffsNode = new GameNode(cliffs);
					roadNode.add(cliffsNode);

					{
						Location cave = new Location("Cave");
						cave.setDescription("A small cave.");
						cave.setVerbose("This acts as a single node of the cliffs.");
						GameNode caveNode = new GameNode(cave);
						cliffsNode.add(caveNode);

						Location rocks = new Location("Rocks");
						rocks.setDescription("A pile of rocks.");
						rocks.setVerbose("This is a second node of the cliffs.");
						GameNode rocksNode = new GameNode(rocks);
						cliffsNode.add(rocksNode);
					}

					Location shore = new Location("Shore");
					shore.setDescription("Some sandy coastline");
					shore.setVerbose("This acts as a third sub-location of road.");
					GameNode shoreNode = new GameNode(shore);
					roadNode.add(shoreNode);
				}
			}

		}
	}
}
