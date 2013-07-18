package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import model.Gamestate;
import model.Time.Hour;

public class TimeKeeperPanel extends Box implements Observer {

	public TimeKeeperPanel(){
		super(BoxLayout.Y_AXIS);
		this.setBorder(BorderFactory.createTitledBorder("Time"));
		Gamestate.current.addObserver(this);
		update(null, null);
	}

	public void update(Observable arg0, Object arg1) {
		removeAll();

		// add(Box.createRigidArea(new Dimension(200, 1)));

		Hour[] listOfHours = Hour.values();

		add(Box.createVerticalGlue());
		JLabel dayLabel = new JLabel("Day " + Gamestate.current.time.currentDay());
		dayLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(dayLabel);
		add(Box.createVerticalGlue());

		for (Hour h : listOfHours) {
			JLabel l = new JLabel(h.toString());
			l.setAlignmentX(CENTER_ALIGNMENT);
			l.setHorizontalAlignment(JLabel.CENTER);
			l.setPreferredSize(new Dimension(100, 25));
			l.setMaximumSize(new Dimension(100, 30));
			l.setMinimumSize(new Dimension(100, 20));

			if (h.equals(Gamestate.currentTime())) {
				l.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			}
			add(l);

			add(Box.createVerticalGlue());
		}

		revalidate();
		repaint();

	}
}
