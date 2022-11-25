import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class F1Car {
	private JPanel panel;
	
	private Image f1Livery;
	
	private int x, y;
	
	private int width, height;
	
	private int dx;
	
	private ImageManager imageManager;
	private SoundManager soundManager;
	
	public F1Car(JPanel gPanel, int xPos) {
		panel = gPanel;
		
		dx = 15;
		
		imageManager = ImageManager.getInstance();
		soundManager = SoundManager.getInstance();
		
		f1Livery = imageManager.loadImage("images/pitstop_car_6.png");
		
		width = f1Livery.getWidth(null);
		height = f1Livery.getHeight(null);
		
		x = xPos;
		y = gPanel.getHeight() - height;
	}

	public void draw(Graphics2D g2) {
		g2.drawImage(f1Livery, x, y, width, height, null);
	}
	
	public void move(int direction) {
		if(!panel.isVisible())
			return;
		
		if(direction == 1) {
			x -= dx;
			if(x < 0) {
    			x = 0;
    		}
		}else if(direction == 2) {
			x += dx;
			if((x + width) > panel.getWidth()) {
    			x = panel.getWidth() - width;
    		}
		}
		
		 soundManager.playClip("f1CarStrafe", false);
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setDX(int dx) {
		this.dx = dx;
	}

	public Rectangle2D.Double getBoundingRectangle(){
		return new Rectangle2D.Double(x, y, width-20, height-20);
	}
}
