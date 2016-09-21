package Sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JOptionPane;

import javazoom.jl.player.Player;

public class SoundMissile {
	private static boolean loop;
	
	public boolean getLoop() {
		return loop;
	}
	
	
	public void setLoop(boolean l) {
		loop = l;
	}
	
	public static void main(String[] args) {
		String path = "res/Sound/HanSoloBlaster.mp3";
		File mp3File = new File(path);
		
		mp3Sound music = new mp3Sound();
		music.playMusic(mp3File);
		
		music.start();
		
		
	}
	
	public static class mp3Sound extends Thread{
		private File mp3;
		
		private Player player;
		
		public void playMusic(File mp3){
			this.mp3 = mp3;
			
		}
		
		public void run(){
			try{
				do{
					FileInputStream fis = new FileInputStream(mp3);
					BufferedInputStream bis = new BufferedInputStream(fis);
					this.player = new Player(bis);
					
					this.player.play();
				}while(loop);
				
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Problen to play! " + mp3);
				e.printStackTrace();
			
				
			}
		}
		
		public void close(){
			loop = false;
			player.close();
			this.interrupt();
		}
	}
}
