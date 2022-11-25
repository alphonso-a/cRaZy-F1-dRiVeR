import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JPanel;

public class Car {
	private boolean overtaken, horn;
	
	private static int carCount = 0;
	private static int traffic = 0;
	
	private GamePanel gamePanel;
	
	private Image carLivery;
	
	private ImageManager imageManager;
	private SoundManager soundManager;
	
	private int x, y;
	
	private int width, height;
	
	private static int dy;
	
	private F1Car f1Car;
	
	private Random rand;
	
	public Car(GamePanel gPanel, F1Car f1Car) {
		
		gamePanel = gPanel;
		
		this.f1Car = f1Car;
		
		dy = 5;
		
		soundManager = SoundManager.getInstance();
		imageManager = ImageManager.getInstance();
		
		carLivery = imageManager.loadImage("images/Porsche_911_Carrera_1998.png");
		
		width = carLivery.getWidth(null);
		height = carLivery.getHeight(null);
		
		rand = new Random();
	}
	
	public void setLoc() {
		int startLoc = -650;
		int lane = rand.nextInt(3);
		
		if(lane == 0) {
			x = 0;
		}else if(lane == 1) {
			x = (gamePanel.getWidth() / 3);
		}else if(lane == 2) {
			x = ((gamePanel.getWidth() / 3) * 2);
		}
		
		y = (++traffic * startLoc);
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(carLivery, x, y, width, height, null);
	}
	
	public void move() {
		if(!gamePanel.isVisible())
			return;
		
		if(y <= gamePanel.getHeight()) {
			y += dy;
		}
		
		if(y > gamePanel.getHeight() && !overtaken) {
			
			overtaken = true;
			
			gamePanel.score();
			
			carCount++;
			if(carCount%5 == 0) {
				
				dy++;
				
				gamePanel.increaseIncrement();
				
			}
		}
		
		if((y > 0) && (y < gamePanel.getHeight() && !horn)) {
			
			horn = true;
			
			if(carCount == 0) {
				soundManager.playClip("carHorn1", true);
			}
			
			if(carCount/2 == 1) {
				soundManager.playClip("carHorn2", true);
			}
			
			if(carCount/4 == 1) {
				soundManager.playClip("carHorn3", true);
			}
		}
		
		if(detectCollision()) {
			
			soundManager.playClip("crash", false);
			
			gamePanel.endGame();
			
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public Rectangle2D.Double getBoundingRectangle() {
		return new Rectangle2D.Double(x, y, width-20, height-20);
	}
	
	public boolean detectCollision() {
    	return getBoundingRectangle().intersects(f1Car.getBoundingRectangle());
    }
}
