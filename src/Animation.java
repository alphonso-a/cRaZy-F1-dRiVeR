import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;


public class Animation implements Runnable{

	private GamePanel panel;
	
	private GrayScaleF1Car grayScaleF1Car;
	
	private ArrayList<AnimationFrame> frames;
	
	private int frameIndex;
	
	private long elapsed;
	private long start;
	private long total;
	
	private boolean isRunning;
	
	public Animation(GamePanel gPanel) {
		panel = gPanel;
		
		isRunning = false;
		
		frames = new ArrayList<AnimationFrame>();
		
		total = 0;
		
		start();
	}
	
	public void setGrayScaleF1Car(GrayScaleF1Car grayScaleF1Car) {
		this.grayScaleF1Car = grayScaleF1Car;
	}
	
	public synchronized void addFrame(Image image, long duration) {
		total += duration;
		frames.add(new AnimationFrame(image, total));
	}
	
	
	public synchronized void start() {
		
		elapsed = 0;
		
		frameIndex = 0;
		
		start = System.currentTimeMillis();
	}
	
	public synchronized void update() {
		long time = System.currentTimeMillis();
		long elapsed = time - start;
		start = time;
		
		if(frames.size() > 1) {
			this.elapsed += elapsed;
			if(this.elapsed >= total) {
				this.elapsed = this.elapsed % total;
				frameIndex = 0;
			}
			
			while(this.elapsed > getFrame(frameIndex).total) {
				frameIndex++;
			}
		}
	}
	
	public synchronized Image getImage() {
		if(frames.size() == 0) {
			return null;
		}else {
			return getFrame(frameIndex).image;
		}
	}
	
	public void draw (Graphics2D g2, int x, int y) {
		g2.drawImage(getImage(), x-220, y-150, 500, 500, null);
	}
	
	
	private AnimationFrame getFrame(int i) {
		return frames.get(i);
	}
	
	public int getFrameCount() {
		return frames.size();
	}
	
	private class AnimationFrame {
		
		Image image;
		
		long total;

		public AnimationFrame(Image image, long total) {
			this.image = image;
			this.total = total;
		}

	}
	
	public boolean getState() {
		return isRunning;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			isRunning = true;
			
			while(isRunning) {
				update();
				grayScaleF1Car.update();
				panel.gameRender();
				Thread.sleep(10);
			}
		}catch(InterruptedException e) {
			
		}
	}
}
