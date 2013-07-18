package nodes;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import model.Gamestate;
import nodes.NodeObject.NodeType;

@SuppressWarnings("serial")
public class GameNode extends DefaultMutableTreeNode implements GameElement {

	public GameNode( NodeObject o ){
		this.userObject = o;
		o.node = this;
	}

	public void go(GameNode n) {
		n.add(this);
	}

	public NodeObject getUserObject() {
		return (NodeObject) super.getUserObject();
	}

	public GameNode getParent() {
		return (GameNode) super.getParent();
	}

	// just casts each element of a path to a gamenode
	public GameNode[] getPath() {
		TreeNode[] tree = super.getPath();
		GameNode[] output = new GameNode[tree.length];
		for (int x = 0; x < tree.length; x++) {
			output[x] = (GameNode) tree[x];
		}
		return output;
	}

	public GameNode getRoot() {
		return (GameNode) super.getRoot();
	}

	// gets an array of gamenodes for children
	// easier than an enumeration
	public GameNode[] getChildren() {
		int numberOfChildren = getChildCount();
		GameNode[] output = new GameNode[numberOfChildren];
		for (int x = 0; x < numberOfChildren; x++) {
			output[x] = (GameNode) getChildAt(x);
		}
		return output;
	}

	public GameNode find(int search) {
		Enumeration<GameNode> e = breadthFirstEnumeration();

		while (e.hasMoreElements()) {
			GameNode next = e.nextElement();
			if (next.getUserObject().getID() == search) { return next; }
		}

		return null;
	}

	public GameNode[] getLocations() {
		GameNode[] allChildren = getChildren();
		ArrayList<GameNode> justLocations = new ArrayList<GameNode>();

		for (int x = 0; x < allChildren.length; x++) {
			if (allChildren[x].getType().equals(NodeType.LOCATION)) {
				justLocations.add(allChildren[x]);
			}
		}

		GameNode[] output = new GameNode[justLocations.size()];

		for (int x = 0; x < output.length; x++) {
			output[x] = justLocations.get(x);
		}

		return output;
	}

	public GameNode[] getPeople() {
		GameNode[] allChildren = getChildren();
		ArrayList<GameNode> justPersons = new ArrayList<GameNode>();

		for (int x = 0; x < allChildren.length; x++) {
			if (allChildren[x].getType().equals(NodeType.PERSON)) {
				justPersons.add(allChildren[x]);
			}
		}

		GameNode[] output = new GameNode[justPersons.size()];

		for (int x = 0; x < output.length; x++) {
			output[x] = justPersons.get(x);
		}

		return output;
	}

	public GameNode[] getItems() {
		GameNode[] allChildren = getChildren();
		ArrayList<GameNode> justItems = new ArrayList<GameNode>();

		for (int x = 0; x < allChildren.length; x++) {
			if (allChildren[x].getType().equals(NodeType.ITEM)) {
				justItems.add(allChildren[x]);
			}
		}

		GameNode[] output = new GameNode[justItems.size()];

		for (int x = 0; x < output.length; x++) {
			output[x] = justItems.get(x);
		}

		return output;
	}

	// /////////////////////////////////////////////////////////////////////////
	// implementation of GameElement interface
	// mostly just returns the values of the userObject
	// ///////////////////////////////////////////////////////////////

	//@formatter:off
	
	public int getID() 							{return getUserObject().getID();}
	public NodeType getType()					{return getUserObject().getType();}
	public void setVisible(boolean b)		{getUserObject().setVisible(b);}
	public boolean isVisible()					{return getUserObject().isVisible();}
	public void setOpen(boolean b)			{getUserObject().setOpen(b);}
	public boolean isOpen()						{return getUserObject().isOpen();}
	public void setName(String s) 			{getUserObject().setName(s);}
	public String getName() 					{return getUserObject().getName();}
	public void setDescription(String s) 	{getUserObject().setDescription(s);}
	public String getDescription() 			{return getUserObject().getDescription();}
	public void setVerbose(String s) 		{getUserObject().setVerbose(s);}
	public String getVerbose() 				{return getUserObject().getVerbose();}
	public GameNode getNode() 					{return this;}
	
	public GameNode getCurrentLocation() {
		if (this.isRoot()) { return this; }
		GameNode output = getParent();
		while (!output.getType().equals(NodeType.LOCATION) && !output.isRoot()) {
			output.getCurrentLocation();
		}
		return output;
	}
	
	//@formatter:on

}
