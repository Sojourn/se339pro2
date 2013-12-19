package edu.iastate.pro2.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MovieTasksPanel extends JPanel {
	public interface MovieTaskListener {
		public void rentMovie();
		public void purchaseMovie();
	}
	
	private final Set<MovieTaskListener> listeners;
	private final JButton rentMovie;
	private final JButton purchaseMovie;
	
	public MovieTasksPanel() {
		listeners = new HashSet<MovieTaskListener>();
		
		rentMovie = new JButton("Rent");
		rentMovie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(MovieTaskListener listener : listeners)
					listener.rentMovie();
			}
		});
		
		purchaseMovie = new JButton("Purchase");
		purchaseMovie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(MovieTaskListener listener : listeners)
					listener.purchaseMovie();
			}
		});
		
		setLayout(new GridLayout(4, 2));
		add(rentMovie);
		add(purchaseMovie);
		
		// Ghetto spacer
		add(new JPanel());
		add(new JPanel());
		add(new JPanel());
		add(new JPanel());
		add(new JPanel());
		add(new JPanel());
	}
	
	public void addMovieTaskListener(MovieTaskListener listener) {
		listeners.add(listener);
	}
}
