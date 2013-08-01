package character;

import java.util.ArrayList;

import javax.swing.JButton;

public interface Conversation {

	public ArrayList<JButton> introduction();
	public void greet();
	public void ask();
	public void goodbye();
	
	// push comment test
}
