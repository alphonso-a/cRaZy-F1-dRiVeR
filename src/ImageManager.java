import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageManager {
	private static ImageManager instance = null;
	
	public static ImageManager getInstance() {
		if(instance == null) {
			instance =  new ImageManager();
		}
		
		return instance;
	}
	
	public Image loadImage (String fileName) {
		return new ImageIcon(fileName).getImage();
	}
	
	public BufferedImage loadBufferedImage(String filename) {
		BufferedImage bufferedImage = null;

		File file = new File (filename);
		try {
			bufferedImage = ImageIO.read(file);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return bufferedImage;
	}
	
	public BufferedImage copyImage(BufferedImage src) {
		
		if (src == null)
			return null;

		
		int width = src.getWidth();
		int height = src.getHeight();

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    	Graphics2D g2 = image.createGraphics();

    	g2.drawImage(src, 0, 0, null);
    	g2.dispose();

    	return image; 
    }
}
