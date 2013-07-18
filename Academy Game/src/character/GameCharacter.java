package character;

import java.util.ArrayList;

import javax.swing.JButton;

public class GameCharacter implements Conversation {
	Stat		strength		= new Stat(StatType.STRENGTH, 1);
	Stat		dexterity	= new Stat(StatType.DEXTERITY, 1);
	Stat		stamina		= new Stat(StatType.STAMINA, 1);
	Stat		intellect	= new Stat(StatType.INTELLECT, 1);
	Stat		charisma		= new Stat(StatType.CHARISMA, 1);
	Stat		wisdom		= new Stat(StatType.WISDOM, 1);

	String	introText;
	String	greetText;

	@Override
	public ArrayList<JButton> introduction() {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public void greet() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ask() {
		// TODO Auto-generated method stub

	}

	@Override
	public void goodbye() {
		// TODO Auto-generated method stub

	}

}
