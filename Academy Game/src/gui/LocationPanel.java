package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import model.Gamestate;
import nodes.GameNode;
import nodes.GameElement;
import nodes.NodeObject.NodeType;

public class LocationPanel extends Box implements Observer {

	final static boolean		DEBUG						= true;

	final static int			PREFERRED_WIDTH		= 200;
	final static Dimension	PREFERRED_DIMENSION	= new Dimension(
																		PREFERRED_WIDTH, 1);

	public LocationPanel(){
		super(BoxLayout.Y_AXIS);
		setBorder(BorderFactory.createTitledBorder("Locations"));
		setPreferredSize(PREFERRED_DIMENSION);
		Gamestate.current.addObserver(this);
		update(null, null);
	}

	public void update(Observable g, Object arg1) {
		
		removeAll();
		
		add(Box.createRigidArea(PREFERRED_DIMENSION));

		// debug
		if (DEBUG) System.out
				.println("Attemnpting to populate player location values");

		GameNode currentLocation = Gamestate.pcLocNode();

		if (!currentLocation.isRoot()) {
			JLabel returnLabel = new JLabel("Return");
			returnLabel.setAlignmentX(CENTER_ALIGNMENT);
			add(returnLabel);

			StandardizedButton b = new StandardizedButton("Back to "
					+ currentLocation.getParent().getUserObject().getName());
			b.setActionCommand("parent");
			b.setToolTipText(currentLocation.getParent().getDescription());
			b.addActionListener(Gamestate.locationListener);
			b.setAlignmentX(Component.CENTER_ALIGNMENT);
			add(b);
			add(Box.createVerticalStrut(4));
		}

		JLabel connectedLabel = new JLabel("Locations connected here:");
		connectedLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(connectedLabel);

		GameNode[] locations = currentLocation.getLocations();

		if (locations.length == 0) {
			connectedLabel.setText("No other locations.");
		}

		for (int x = 0; x < locations.length; x++) {

			GameElement m = locations[x].getUserObject();

			// if (m.getType().equals(NodeType.LOCATION)) {

			// debug
			if (DEBUG) System.out.println(m.getName());

			StandardizedButton b = new StandardizedButton(m.getName());
			b.setToolTipText(m.getDescription());
			b.setActionCommand(m.getName());
			b.addActionListener(Gamestate.locationListener);
			b.setAlignmentX(Component.CENTER_ALIGNMENT);

			add(b);
			add(Box.createVerticalStrut(4));
			// }
		}
		
		revalidate();
		repaint();
	
	}

}
