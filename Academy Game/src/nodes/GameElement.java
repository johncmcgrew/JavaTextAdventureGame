package nodes;

import nodes.NodeObject.NodeType;

public interface GameElement {

	public int getID();

	public NodeType getType();

	public void setVisible(boolean newValue);

	public boolean isVisible();

	public void setOpen(boolean newValue);

	public boolean isOpen();

	public void setName(String s);

	public String getName();

	public void setDescription(String s);

	public String getDescription();

	public void setVerbose(String s);

	public String getVerbose();

	public GameNode getNode();

	public GameNode getCurrentLocation();

}
