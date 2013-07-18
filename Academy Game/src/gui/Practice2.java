package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import character.Student;
import character.Student.Personality;

public class Practice2 {

	static final Practice2	p	= new Practice2();

	Student[]					studentList;

	private Practice2(){
		studentList = new Student[3];
		studentList[0] = new Student("Alfred", "Hello there, pal!",
				Personality.NICE);
		studentList[1] = new Student("Benjamin", "How Ya doing?",
				Personality.STUDIOUS);
		studentList[2] = new Student("Charles", "Fuck off, dickhead.",
				Personality.MEAN);
	}

	public static void main(String[] args) {

		Runnable runner = new Runnable() {

			public void run() {
				JFrame frame = new JFrame("App");
				frame.setSize(500, 500);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);

				JPanel mainPanel = new JPanel();
				mainPanel.setLayout(new BorderLayout());

				Box studentListBox = new Box(BoxLayout.Y_AXIS);
				studentListBox.setBorder(BorderFactory
						.createTitledBorder("Students"));
				mainPanel.add(studentListBox, BorderLayout.WEST);

				JPanel outputPanel = new JPanel();
				outputPanel.setBorder(BorderFactory.createTitledBorder("Output"));
				mainPanel.add(outputPanel, BorderLayout.CENTER);

				abstract class OutputLabel extends JLabel implements ActionListener {

				}

				OutputLabel outputLabel = new OutputLabel() {
					public void actionPerformed(ActionEvent e) {
						this.setText(e.getActionCommand());
					}
				};

				outputPanel.add(outputLabel);

				studentListBox.add(Box.createVerticalGlue());

				for (int x = 0; x < p.studentList.length; x++) {
					JButton button = new JButton(p.studentList[x].getName());
					button.setActionCommand(p.studentList[x].getGreeting());
					button.addActionListener(outputLabel);
					studentListBox.add(button);
					studentListBox.add(Box.createVerticalGlue());
				}

				frame.add(mainPanel);
				frame.setVisible(true);
			}

		};
		EventQueue.invokeLater(runner);
	}

}
