import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
	private ScorePanel scorePanel;
	
	private static int TRAFFIC = 100;
	
	private GameThread gameThread;
	
	private AnimationLoader animationLoader;
	private SoundManager soundManager;
	
	private BufferedImage image;
	private Roadway background;
	
	
	private GrayScaleF1Car grayScaleF1Car;
	private Animation explosion;
	
	private F1Car f1Car;
	private Car[] cars;
	
	public GamePanel(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
		
		f1Car = null;
		
		grayScaleF1Car = new GrayScaleF1Car();
		animationLoader = new AnimationLoader(this);
		
		soundManager = SoundManager.getInstance();
		
		cars = new Car[TRAFFIC];
		
		explosion = animationLoader.explosion();
		explosion.setGrayScaleF1Car(grayScaleF1Car);
	}
	
	public void createGameEntities() {
		background = new Roadway(this, "images/background.png", 8);
		
		f1Car = new F1Car(this, 50);
		
		for (int i=0; i<TRAFFIC; i++) {
			cars[i] = new Car(this, f1Car);
			cars[i].setLoc();
		}
	}
	
	
	public static int getTRAFFIC() {
		return TRAFFIC;
	}

	public void gameUpdate() {
		background.move();
		
		for (int i=0; i<TRAFFIC; i++) {
			cars[i].move();
		}
		
	}
	
	public void gameRender() {
		image = new BufferedImage (this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		Graphics2D imageContext = (Graphics2D) image.getGraphics();
		
		background.draw(imageContext);
		
		if(!explosion.getState()) {
			if(f1Car != null) {
				f1Car.draw(imageContext);
			}
		}
		
		
		if(explosion.getState()) {
			grayScaleF1Car.draw(imageContext, f1Car.getX(), f1Car.getY());
			explosion.draw(imageContext, f1Car.getX(), f1Car.getY());
		}
		
		if (cars != null) {
			for (int i=0; i<TRAFFIC; i++)
				cars[i].draw(imageContext);
       	}
		
		Graphics2D g2 = (Graphics2D) getGraphics();
		g2.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
		
		imageContext.dispose();
		g2.dispose();
	}
	
	public void updateUserEntities(int direction) {
		if(f1Car != null) {
			f1Car.move(direction);
		}
	}
	
	public void score() {
		scorePanel.score();
	}
	
	public void distance() {
		scorePanel.distance();
	}
	
	public void increaseIncrement() {
		scorePanel.increaseIncrement();
	}
	
	public void stopSoundEffects() {
		
		soundManager.stopClip("carHorn1");
		soundManager.stopClip("carHorn2");
		soundManager.stopClip("carHorn3");
		soundManager.stopClip("f1Engine");
		
	}
	
	public void startGame() {
		Thread thread;
		
		if(gameThread == null) {
			soundManager.playClip("f1Engine", true);
			
			createGameEntities();
			
			gameThread = new GameThread(this);
			thread = new Thread(gameThread);
			
			thread.start();
		}
	}
	
	public void endGame() {
		
		f1Car.setDX(0);
		
		stopSoundEffects();
		
		gameThread.endGame();
		
		explosion.run();
	}
}
