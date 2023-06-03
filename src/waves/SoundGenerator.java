package waves;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.*;
import javax.sound.sampled.*;

public class SoundGenerator implements Runnable{
	public Boolean czynny=true;
	public static Clip clip;
	static AudioInputStream stream;
	public static File out, doZapisu;
	 SoundGenerator(){
		 for (Wave wave : MainFrame.waves) {
					try {
						generate(wave, "wave-freq-" + wave.getFreq() + "-sound");
					} catch (IOException e) {
						e.printStackTrace();
					}
	 }
	 }
	 
	public static void generate(Wave wave, String fileName) throws IOException {
		final double frequency = wave.getFreq();
		final double amplitude = wave.getAmp();
		final double seconds = 2.0;
		final double twoPiF = 2 * Math.PI * frequency;
		final double sampleRate = 2 * twoPiF;

		// kod zapożyczony z https://stackoverflow.com/

		float[] buffer = new float[(int) (seconds * sampleRate)];

		for (int sample = 0; sample < buffer.length; sample++) {
			double time = sample / sampleRate;
			buffer[sample] = (float) (amplitude * Math.sin(twoPiF * time));
		}

		final byte[] byteBuffer = new byte[buffer.length * 2];

		int bufferIndex = 0;
		for (int i = 0; i < byteBuffer.length; i++) {
			final int x = (int) (buffer[bufferIndex++] * 32767.0);

			byteBuffer[i++] = (byte) x;
			byteBuffer[i] = (byte) (x >>> 8);
		}

		out= new File("generated/" + fileName + ".wav");
		doZapisu=new File("generated/" + fileName + ".wav");
		final boolean bigEndian = false;
		final boolean signed = true;

		final int bits = 16;
		final int channels = 1;

		AudioFormat format = new AudioFormat((float) sampleRate, bits, channels, signed, bigEndian);
		ByteArrayInputStream bais = new ByteArrayInputStream(byteBuffer);
		AudioInputStream audioInputStream = new AudioInputStream(bais, format, buffer.length);
		AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, out);
		audioInputStream.close();
		
		   
	}
	@Override
	public void run() {

		while(czynny) {
			try {
		    AudioFormat format2;
		    DataLine.Info info;
		    stream = AudioSystem.getAudioInputStream(out);
		    format2 = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format2);
		    clip = (Clip) AudioSystem.getLine(info);
		    clip.open(stream);
		    clip.start();
			}
		
		catch (Exception e) {
		}
			 try {
				 Thread.sleep(2000);   
		        } catch (InterruptedException e2) {
		            e2.printStackTrace();
		        }	
		 }     
		
}
}


