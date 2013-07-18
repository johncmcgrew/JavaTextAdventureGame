package gui;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

public class StandardizedButton extends JButton implements Observer {

	final static int			PREFERRED_WIDTH		= 200;
	final static int			PREFERRED_HEIGHT		= 30;

	final static Dimension	PREFERRED_DIMENSION	= new Dimension(
																		PREFERRED_WIDTH,
																		PREFERRED_HEIGHT);

	public StandardizedButton( String s ){
		super(s);
		setPreferredSize(PREFERRED_DIMENSION);
		setMaximumSize(PREFERRED_DIMENSION);
		setMinimumSize(PREFERRED_DIMENSION);
	}

	public void update(Observable arg0, Object arg1) {

	}

}
