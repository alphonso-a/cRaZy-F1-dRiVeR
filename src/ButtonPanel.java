import java.awt.event.*;
import javax.swing.*;

public class ButtonPanel extends JPanel implements ActionListener{
	private GamePanel gPanel;
	
	private JButton startB;
	private JButton exitB;

	public ButtonPanel(GamePanel gamePanel) {
		gPanel = gamePanel;
		
		startB = new JButton("START");
		exitB = new JButton("EXIT");
		
		startB.addActionListener(this);
		exitB.addActionListener(this);
		
		add(startB);
		add(exitB);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equals(startB.getText())) {
			gPanel.startGame();
			gPanel.requestFocus(); 
		}
		
		if (command.equals(exitB.getText()))
			System.exit(0);
		
	}
}
