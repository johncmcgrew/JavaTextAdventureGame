package gui;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Gamestate;

public class DescriptionPanel extends JPanel implements Observer {

	public DescriptionPanel(){
		setBorder(BorderFactory.createTitledBorder("Description"));
		setPreferredSize(new Dimension(300, 100));
		Gamestate.current.addObserver(this);
		update(null, null);
	}

	public void update(Observable obs, Object obj) {
		removeAll();

		Box box = Box.createVerticalBox();

		JLabel descriptionLabel = new JLabel(Gamestate.current.focus.getDescription());
		JLabel verboseLabel = new JLabel(Gamestate.current.focus.getVerbose());
		

		box.add(descriptionLabel);
		box.add(verboseLabel);

		add(box);

		revalidate();
		repaint();
	}

}
