package nodes;

public class Item extends NodeObject implements ItemInterface {

	
	public Item( String aName){
		super(aName,  NodeType.ITEM);
	}

	public void use() {

	}

}
