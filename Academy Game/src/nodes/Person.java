package nodes;

public class Person extends NodeObject implements PersonInterface {

	

	public Person( String aName ){
		super(aName,  NodeType.PERSON);
	}

	public void go(Location l) {
		l.getNode().add(this.getNode());
	}

	public void talk() {

	}

}
