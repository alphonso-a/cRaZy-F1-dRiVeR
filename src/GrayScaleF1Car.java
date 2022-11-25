import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GrayScaleF1Car implements ImageFX{
	
	private GamePanel panel;
	
	private ImageManager imageManager;
	
	private BufferedImage f1Car;
	private BufferedImage f1CarGrayScale;
	
	private Graphics2D g2;
	
	private int time, timeChange;
	
	private boolean original, grayScale;
	
	
	public GrayScaleF1Car() {
		
		time = 0;
		timeChange = 1;
		
		original = true;
		grayScale = false;
		
		imageManager = ImageManager.getInstance();
		
		f1Car = imageManager.loadBufferedImage("images/pitstop_car_6.png");
	}
	
	private int toGrayScale(int pixel) {
		int alpha, red, green, blue, gray;
		int pixelGray;

		alpha = (pixel >> 24) & 255;
		red = (pixel >> 16) & 255;
		green = (pixel >> 8) & 255;
		blue = pixel & 255;

		gray = (red + green + blue) / 3;

		red = green = blue = gray;

		pixelGray = blue | (green << 8) | (red << 16) | (alpha << 24);

		return pixelGray;
	}
	
	@Override
	public void update() {
		
		time = time + timeChange;

		if (time < 150) {			
			original = true;
			grayScale = false;
		}
		else{			
			original = false;
			grayScale = true;
		}
	}

	@Override
	public void draw(Graphics2D g2, int x, int y) {
		
		f1CarGrayScale = imageManager.copyImage(f1Car);
		
		int width = f1CarGrayScale.getWidth();
		int height = f1CarGrayScale.getHeight();
		
		if (original) {	
			g2.drawImage(f1CarGrayScale, x, y, width, height, null);
			return;
		}
		
		int[] pixels = new int[width * height];
		f1CarGrayScale.getRGB(0, 0, width, height, pixels, 0, width);
		
		for (int i=0; i<pixels.length; i++) {
			if (grayScale) {
				pixels[i] = toGrayScale(pixels[i]);
			}
		}
		
		f1CarGrayScale.setRGB(0, 0, width, height, pixels, 0, width);
		g2.drawImage(f1CarGrayScale, x, y, width, height, null);
	}
}
