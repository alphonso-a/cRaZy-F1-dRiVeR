import java.awt.Graphics;
import java.awt.Image;

public class Roadway {
	private GamePanel gPanel;
	
	private ImageManager imageManager;
	
	private Image background;
	
	private int imageHeight;
	
	private int y1, y2;
	
	private int dy;
	
	public Roadway(GamePanel gamePanel, String fileName, int dy) {
		gPanel = gamePanel;
		
		imageManager = ImageManager.getInstance();
		
		background = imageManager.loadImage("images/background.png");
		
		imageHeight = gPanel.getHeight();
		
		this.dy = dy;
		
		y1 = 0;
		y2 = y1 - imageHeight;
	}
	
	public void move() {
		y1 += dy;
		y2 += dy;
		
		if(y1 > imageHeight) {
			y1 = y2 - imageHeight;
		}else if(y2 > imageHeight) {
			y2 = y1 - imageHeight;
		}
	}
	
	public void draw(Graphics g2) {
		g2.drawImage(background, 0, y1, gPanel.getWidth(), gPanel.getHeight(), null);
		g2.drawImage(background, 0, y2, gPanel.getWidth(), gPanel.getHeight(), null);
	}
}
