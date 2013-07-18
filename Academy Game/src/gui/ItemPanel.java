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

public class ItemPanel extends Box implements Observer {

	final static boolean		DEBUG						= true;

	final static int			PREFERRED_WIDTH		= 200;
	final static Dimension	PREFERRED_DIMENSION	= new Dimension(
																		PREFERRED_WIDTH, 1);

	public ItemPanel(){
		super(BoxLayout.Y_AXIS);
		setBorder(BorderFactory.createTitledBorder("Items"));
		setPreferredSize(PREFERRED_DIMENSION);

		// setMaximumSize(PREFERRED_DIMENSION);
		// setMinimumSize(PREFERRED_DIMENSION);
		Gamestate.current.addObserver(this);
		update(null, null);
	}

	public void update(Observable g, Object arg1) {

		removeAll();

		add(Box.createRigidArea(PREFERRED_DIMENSION));

		// debug
		if (DEBUG) System.out
				.println("Attemnpting to populate items in the player's location");

		GameNode currentLocation = Gamestate.pcLocNode();

		JLabel itemsLabel = new JLabel("Items here:");
		itemsLabel.setAlignmentX(CENTER_ALIGNMENT);
		itemsLabel.setSize(PREFERRED_DIMENSION);

		add(itemsLabel);

		GameNode[] items = currentLocation.getItems();

		if (items.length == 0) {
			itemsLabel.setText("No items.");
		}

		for (int x = 0; x < items.length; x++) {

			GameElement m = items[x].getUserObject();

			// if (m.getType().equals(NodeType.LOCATION)) {

			// debug
			if (DEBUG) System.out.println(m.getName());

			StandardizedButton b = new StandardizedButton(m.getName());
			b.setToolTipText(m.getDescription());
			b.setActionCommand(m.getName());
			b.addActionListener(Gamestate.itemListener);
			b.setAlignmentX(Component.CENTER_ALIGNMENT);

			add(b);
			add(Box.createVerticalStrut(4));
			// }
		}

		revalidate();
		repaint();

	}

}
