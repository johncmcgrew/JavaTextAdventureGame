package gui;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Gamestate;

public class StatusPanel extends JPanel implements Observer {

	public StatusPanel(){
		setBorder(BorderFactory.createTitledBorder("Status"));
		Gamestate.current.addObserver(this);
		update(null, null);
	}

	public void update(Observable o, Object arg) {

		removeAll();

		//Box box = new Box(BoxLayout.X_AXIS);
		
		JPanel box = new JPanel(new GridLayout(1, 0));

		JLabel nameLabel = new JLabel("Name: "
				+ Gamestate.current.playerCharacter.getName());
		JLabel locationLabel = new JLabel("Current location: "
				+ Gamestate.pcLocName());

		box.add(nameLabel);
		//box.add(Box.createHorizontalStrut(10));
		box.add(locationLabel);
		
		box.add(new JLabel("Hurf durf"));

		add(box);

		revalidate();
		repaint();

	}

}
