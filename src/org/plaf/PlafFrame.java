package org.plaf;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

@SuppressWarnings("serial")
public class PlafFrame extends JFrame {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new PlafFrame();
			frame.setResizable(false);
			frame.setVisible(true);
			frame.pack();
		});
	}
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 300;
	
	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private static int POSITION_X = (int) ((SCREEN_SIZE.getWidth()/2) - (DEFAULT_WIDTH/2));
	private static int POSITION_Y = (int) ((SCREEN_SIZE.getHeight()/2) - (DEFAULT_HEIGHT/2));
	
	JPanel buttonPanel;
	
	public PlafFrame() {
		setLocation(POSITION_X, POSITION_Y);

		buttonPanel = new JPanel();
		LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
		for (LookAndFeelInfo info : infos) {
			makeButton(info.getName(), info.getClassName());
		}
		
		JLabel label = new JLabel("Hello World!");
		Font font = new Font("Serif", Font.ITALIC, 60);
		label.setFont(font);
		
		buttonPanel.add(label);
		add(buttonPanel);
		
		pack();
	}
	
	public void makeButton(String name, String className) {
		// add button to panel
		JButton button = new JButton(name);
		buttonPanel.add(button);
		
		// set button action
		button.addActionListener(event -> {
			// button action: switch to the new look-and-feel
			try {
				UIManager.setLookAndFeel(className);
				SwingUtilities.updateComponentTreeUI(this);
				pack();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}
