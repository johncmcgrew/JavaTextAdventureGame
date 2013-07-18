package gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

import model.Gamestate;

public class PlayerCharacterPanel extends Box implements Observer {

	public PlayerCharacterPanel(){
		super(BoxLayout.X_AXIS);
		setBorder(BorderFactory.createTitledBorder("Player Character"));
		Gamestate.current.addObserver(this);
		update(null, null);
	}

	public void update(Observable arg0, Object arg1) {

		removeAll();

		StandardizedButton b = new StandardizedButton(
				Gamestate.current.pcNode.getName());
		b.setToolTipText(Gamestate.current.pcNode.getDescription());
		b.addActionListener(Gamestate.pcListener);
		add(b);

		revalidate();
		repaint();

	}

}
