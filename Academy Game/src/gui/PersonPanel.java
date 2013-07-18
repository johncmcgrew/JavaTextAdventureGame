package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import model.Gamestate;
import nodes.GameElement;
import nodes.GameNode;

public class PersonPanel extends Box implements Observer {

	final static boolean		DEBUG						= false;

	final static int			PREFERRED_WIDTH		= 200;
	final static Dimension	PREFERRED_DIMENSION	= new Dimension(
																		PREFERRED_WIDTH, 1);

	public PersonPanel(){
		super(BoxLayout.Y_AXIS);
		setBorder(BorderFactory.createTitledBorder("People"));
		setPreferredSize(PREFERRED_DIMENSION);
		Gamestate.current.addObserver(this);
		update(null, null);
	}

	public void update(Observable g, Object arg1) {

		removeAll();
		add(Box.createRigidArea(PREFERRED_DIMENSION));

		// debug
		if (DEBUG) System.out
				.println("Attemnpting to populate persons in the player location");

		GameNode currentLocation = Gamestate.pcLocNode();

		JLabel presentLabel = new JLabel("People here:");
		presentLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(presentLabel);

		GameNode[] persons = currentLocation.getPeople();

		int visiblePeople = 0;

		ArrayList<GameElement> invisibleList = new ArrayList<GameElement>();

		for (int x = 0; x < persons.length; x++) {

			GameElement m = persons[x].getUserObject();

			if (m.isVisible()) {

				visiblePeople++;

				// debug
				if (DEBUG) System.out.println(m.getName());

				StandardizedButton b = new StandardizedButton(m.getName());
				b.setToolTipText(m.getDescription());
				b.setActionCommand(m.getName());
				b.addActionListener(Gamestate.personListener);
				b.setAlignmentX(Component.CENTER_ALIGNMENT);

				add(b);
				add(Box.createVerticalStrut(4));
			} else {
				invisibleList.add(m);
			}
		}

		if (visiblePeople == 0) {
			presentLabel.setText("No visible people.");
		}

		if (invisibleList.size() > 0) {

			JLabel invisLabel = new JLabel("Invisible People:");
			invisLabel.setAlignmentX(CENTER_ALIGNMENT);
			add(invisLabel);

			for (GameElement e : invisibleList) {
				JLabel l = new JLabel(e.getName());
				l.setAlignmentX(CENTER_ALIGNMENT);
				l.setToolTipText(e.getDescription());
				add(l);
			}

			revalidate();
			repaint();

		}

	}
}
