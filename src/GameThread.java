
public class GameThread implements Runnable {
	
	private GamePanel gPanel;
	
	private boolean isRunning;
	private boolean isPaused;
	
	public GameThread(GamePanel gamePanel) {
		gPanel = gamePanel;
		
		isRunning = false;
		isPaused = false;
	}
	
	boolean running () {
		return isRunning;
	}
	
	private void gameUpdate() {
		gPanel.gameUpdate();		
	}
	
	private void gameRender() {
		gPanel.gameRender();		
	}

	@Override
	public void run() {
		try {
			isRunning = true;
			
			while(isRunning) {
				if(!isPaused) {
					
				}
				gameUpdate();
				gameRender();
				gPanel.distance();
				Thread.sleep(10);
			}
		}catch(InterruptedException e) {
			
		}	
	}

	public void pauseGame() {
		if (isRunning) {
			if (isPaused)
				isPaused = false;
			else
				isPaused = true;
		}
	}

	public void endGame() {
		isRunning = false;	
	}
}
