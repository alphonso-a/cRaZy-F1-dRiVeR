import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

public class GameWindow extends JFrame implements KeyListener{
	
	private ButtonPanel buttonPanel;
	private ScorePanel scorePanel;
	private GamePanel gamePanel;
	
	public GameWindow() {
		setTitle("cRaZy F1 dRiVeR");
		setSize(350, 800);
		
		scorePanel = new ScorePanel();
		gamePanel = new GamePanel(scorePanel);
		buttonPanel = new ButtonPanel(gamePanel);
		
		add(buttonPanel, BorderLayout.NORTH);
		add(gamePanel, BorderLayout.CENTER);
		add(scorePanel, BorderLayout.SOUTH);
		
		gamePanel.addKeyListener(this);
		
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
    	int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_LEFT) {
			gamePanel.updateUserEntities(1);
		}
		
		if(keyCode == KeyEvent.VK_RIGHT) {
			gamePanel.updateUserEntities(2);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
