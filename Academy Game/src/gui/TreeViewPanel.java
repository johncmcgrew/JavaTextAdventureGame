package gui;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import model.Gamestate;

public class TreeViewPanel extends JPanel implements Observer {
	
	JTree tree;

	
	public TreeViewPanel(){
		setBorder(BorderFactory.createTitledBorder("Tree View"));
		tree = new JTree(Gamestate.current.treeModel);
		
		add(new JScrollPane(tree)); 
	}
	

	public void update(Observable arg0, Object arg1) {

	

	}

}
