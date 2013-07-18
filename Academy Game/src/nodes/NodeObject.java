package nodes;

public abstract class NodeObject implements GameElement {

	public enum NodeType {
		PLAYER_CHARACTER, LOCATION, PERSON, ITEM, DIALOGUE, ACTIVITY, NONE;
	}

	public NodeObject( String aName ){
		ID = serial++;
		name = aName;

	}

	public NodeObject( String aName,NodeType aType ){
		this(aName);
		this.type = aType;
	}

	private static int	serial	= 0;

	final int				ID;

	private NodeType		type		= NodeType.NONE;
	GameNode					node;

	boolean					visible = true;
	boolean					open = true;

	String					name;
	String					description;
	String					verbose;

	public String toString() {
		return "" + type + " " + name;
	}

	public int getID() {
		return ID;
	}

	public NodeType getType() {
		return type;
	}

	public void setVisible(boolean input) {
		visible = input;
	}

	public boolean isVisible() {
		return Boolean.valueOf(visible);
	}
	
	public void setOpen(boolean input){
		open = input;
	}
	
	public boolean isOpen(){
		return Boolean.valueOf(open);
	}

	public void setName(String s) {
		name = s;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String s) {
		description = s;
	}

	public String getDescription() {
		return description;
	}

	public void setVerbose(String s) {
		verbose = s;
	}

	public String getVerbose() {
		return verbose;
	}

	public GameNode getCurrentLocation() {
		return null;
	}

	public GameNode getNode() {

		return node;
	}

}
