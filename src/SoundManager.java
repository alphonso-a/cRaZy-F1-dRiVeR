import java.util.HashMap;
import javax.sound.sampled.*;
import java.io.File;

public final class SoundManager {
	private static SoundManager instance = null;
	
	HashMap<String, Clip> clips;
	
	private SoundManager() {
		clips = new HashMap<String, Clip>();
		
		Clip clip;
		
		clip = loadClip("sounds/4_36.wav");
		clips.put("carHorn1", clip);
		
		clip = loadClip("sounds/225-2t-stereo.wav");
		clips.put("carHorn2", clip);
		
		clip = loadClip("sounds/250-2t-260-2t-stereo.wav");
		clips.put("carHorn3", clip);
		
		clip = loadClip("sounds/engine-loop-1-normalized_1.wav");
		clips.put("f1Engine", clip);
		
		clip = loadClip("sounds/CARCR111.wav");
		clips.put("crash", clip);
	}

	public static SoundManager getInstance() {
		
		if(instance == null) {
			instance = new SoundManager();
		}
		
		return instance;
		
	}
	
	public Clip getClip (String title) {
		return clips.get(title);
	}
	
	public Clip loadClip(String fileName) {
		
		AudioInputStream audioInput;
		Clip clip = null;
		
		try {
			File file = new File(fileName);
			audioInput = AudioSystem.getAudioInputStream(file.toURI().toURL());
			clip = AudioSystem.getClip();
			clip.open(audioInput);
		}catch(Exception e){
			
		}
		
		return clip;	
	}
	
	public void playClip(String title, boolean repeat) {
		
		Clip clip = getClip(title);
		
		if (clip != null) {
			clip.setFramePosition(0);
			if(repeat)
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			else
				clip.start();
		}
	}
	
	public void stopClip(String title) {
		
		Clip clip = getClip(title);
		
		if(clip != null) {
			clip.stop();
		}
	}
	
}
