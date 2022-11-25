import java.awt.Image;

public final class AnimationLoader {
	
	private GamePanel panel;
	
	private Animation firstAnimation;
	
	private ImageManager imageManager;
	
	public AnimationLoader(GamePanel gPanel) {
		panel = gPanel;
		
		imageManager = ImageManager.getInstance();
	}
	
	public Animation explosion() {

		String prefix = "images/explosion/E000";
		String suffix = ".png";

		firstAnimation = new Animation(panel);

		for (int i=1; i<=8; i++) {
			String path = prefix + i + suffix;
			Image animationImage = imageManager.loadImage(path);
			firstAnimation.addFrame(animationImage, 100);
		}
		
		return firstAnimation;
	}
}
