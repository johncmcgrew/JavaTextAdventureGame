package gui;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Gamestate;

public class GameGui implements Runnable {
	public void run() {

		JFrame frame = new JFrame("Academy Game");
		frame.setSize(700, 700);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// the rest of the code

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		frame.add(mainPanel);

		Box northPanel = Box.createHorizontalBox();
		{
			PlayerCharacterPanel pcPanel = new PlayerCharacterPanel();

			StatusPanel statusPanel = new StatusPanel();

			northPanel.add(pcPanel);

			northPanel.add(statusPanel);
		}
		mainPanel.add(northPanel, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();

		DescriptionPanel descriptionPanel = new DescriptionPanel();

		centerPanel.add(descriptionPanel, BorderLayout.NORTH);

		TreeViewPanel treeViewPanel = new TreeViewPanel();
		centerPanel.add(treeViewPanel, BorderLayout.CENTER);

		mainPanel.add(centerPanel, BorderLayout.CENTER);

		Box westPanel = Box.createVerticalBox();
		{
			LocationPanel locationPanel = new LocationPanel();
			PersonPanel personPanel = new PersonPanel();
			ItemPanel itemPanel = new ItemPanel();

			westPanel.add(locationPanel);
			westPanel.add(personPanel);
			westPanel.add(itemPanel);
		}
		mainPanel.add(westPanel, BorderLayout.WEST);

		Box eastPanel = Box.createVerticalBox();
		{
			TimeKeeperPanel timePanel = new TimeKeeperPanel();
			eastPanel.add(timePanel);
		}
		mainPanel.add(eastPanel, BorderLayout.EAST);

		Gamestate.current.notifyObservers();

		// finish

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
}
