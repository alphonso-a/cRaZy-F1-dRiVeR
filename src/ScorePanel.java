import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel{
	JPanel panel;
	
	JLabel scoreLabel;
	JLabel distanceLabel;
	
	private int score = 0;
	
	private double distance = 0;
	private double increment = 0.01;
	
	private static final DecimalFormat df = new DecimalFormat("0.0");
	
	public ScorePanel() {
		this.setLayout(new GridLayout());
		
		scoreLabel = new JLabel("passed: " + score);
		distanceLabel = new JLabel("travelled: " + distance);
		
		ImageIcon scoreLogo = new ImageIcon("images/Porsche_911_Carrera_1998_1.png");
		scoreLabel.setIcon(scoreLogo);
		
		ImageIcon distanceLogo = new ImageIcon("images/background_1.png");
		distanceLabel.setIcon(distanceLogo);
		
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		distanceLabel.setHorizontalAlignment(JLabel.CENTER);
		
		scoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		distanceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		
		this.add(scoreLabel);
		this.add(distanceLabel);
	}
	
	public void score() {
		score++;
		
		scoreLabel.setText("passed: " + score);
	}
	
	public void distance() {
		distance = distance + increment;
		
		distanceLabel.setText("travelled: " + df.format(distance) + " km"); 
	}

	public void increaseIncrement() {
		increment = increment + 0.01;
	}
}
