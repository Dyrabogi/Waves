package waves;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.*;
import javax.sound.sampled.*;

public class SoundGenerator implements Runnable{
	public Boolean czynny=true;
	public static Clip clip;
	static AudioInputStream stream;
	public static File out, doZapisu;
//	static ArrayList<String> fileNames = new ArrayList();
	static ArrayList<Wave> wavesList = new ArrayList();
	 SoundGenerator(){
//		 for (Wave wave : MainFrame.waves) {
					try {
						playSounds(MainFrame.waves);
					} catch (IOException e) {
						e.printStackTrace();
					}
	 }
//	 }
	 
//	public static void generate(Wave wave, String fileName, boolean saveCheck) throws IOException {
//		final double frequency = wave.getFreq();
//		final double amplitude = 5;//wave.getAmp();
//		final double seconds = 2.0;
//		final double twoPiF = 2 * Math.PI * frequency;
//		final double sampleRate = 2 * twoPiF;
//
//		// kod zapożyczony z https://stackoverflow.com/
//
//		float[] buffer = new float[(int) (seconds * sampleRate)];
//
//		for (int sample = 0; sample < buffer.length; sample++) {
//			double time = sample / sampleRate;
//			buffer[sample] = (float) (amplitude * Math.sin(twoPiF * time));
//		}
//
//		final byte[] byteBuffer = new byte[buffer.length * 2];
//
//		int bufferIndex = 0;
//		for (int i = 0; i < byteBuffer.length; i++) {
//			final int x = (int) (buffer[bufferIndex++] * 32767.0);
//
//			byteBuffer[i++] = (byte) x;
//			byteBuffer[i] = (byte) (x >>> 8);
//		}
//		
//		out= new File("generated/" + fileName + ".wav");
//		doZapisu=new File("generated/" + fileName + ".wav");
//		final boolean bigEndian = false;
//		final boolean signed = true;
//
//		final int bits = 16;
//		final int channels = 1;
//		if(saveCheck) {
//			AudioFormat format = new AudioFormat((float) sampleRate, bits, channels, signed, bigEndian);
//			ByteArrayInputStream bais = new ByteArrayInputStream(byteBuffer);
//			AudioInputStream audioInputStream = new AudioInputStream(bais, format, buffer.length);
//			AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, out);
//			audioInputStream.close();
//			
//		}
//		   
//	}
	
	public static void saveSounds(ArrayList<Wave> waveList) throws IOException {
		final ArrayList<Double> freqs = new ArrayList();
		for(Wave wave : waveList){
			freqs.add(wave.getFreq());
		}
		
		final ArrayList<Double> amps = new ArrayList();
		for(Wave wave : waveList){
			amps.add(wave.getAmp());
		}
	
		final double seconds = 2.0;

		final ArrayList<Double> twoPiFs = new ArrayList();
		for(double freq : freqs) {
			twoPiFs.add(freq*Math.PI*2);
		}
		final double sampleRate = 3 * Collections.max(twoPiFs);

		// kod zapożyczony z https://stackoverflow.com/

		float[] buffer = new float[(int) (seconds * sampleRate)];

		for (int sample = 0; sample < buffer.length; sample++) {
			double time = sample / sampleRate;
			double funValue =0;
			for(int j = 0; j<waveList.size();j++)
				funValue += amps.get(j)*Math.sin(twoPiFs.get(j) * time + waveList.get(j).getPhase());
			float fFunValue = (float) funValue;
			buffer[sample] = fFunValue;
		}

		final byte[] byteBuffer = new byte[buffer.length * 2];

		int bufferIndex = 0;
		for (int i = 0; i < byteBuffer.length; i++) {
			final int x = (int) (buffer[bufferIndex++] * 32767.0);

			byteBuffer[i++] = (byte) x;
			byteBuffer[i] = (byte) (x >>> 8);
		}
		
		doZapisu= new File("generated/" + "combinedFuncof" +waveList.size()+ ".wav");
//		doZapisu=new File("generated/" + fileName + ".wav");
		final boolean bigEndian = false;
		final boolean signed = true;

		final int bits = 16;
		final int channels = 1;
//		if(saveCheck) {
			AudioFormat format = new AudioFormat((float) sampleRate, bits, channels, signed, bigEndian);
			ByteArrayInputStream bais = new ByteArrayInputStream(byteBuffer);
			AudioInputStream audioInputStream = new AudioInputStream(bais, format, buffer.length);
			AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, doZapisu);
			audioInputStream.close();
//			
//		}
		
	}
	
	public static void playSounds(ArrayList<Wave> waveList) throws IOException {
		final ArrayList<Double> freqs = new ArrayList();
		for(Wave wave : waveList){
			freqs.add(wave.getFreq());
		}
		
		final ArrayList<Double> amps = new ArrayList();
		for(Wave wave : waveList){
			amps.add(wave.getAmp());
		}
	
		final double seconds = 2.0;

		final ArrayList<Double> twoPiFs = new ArrayList();
		for(double freq : freqs) {
			twoPiFs.add(freq*Math.PI*2);
		}
		final double sampleRate = 3 * Collections.max(twoPiFs);

		// kod zapożyczony z https://stackoverflow.com/

		float[] buffer = new float[(int) (seconds * sampleRate)];

		for (int sample = 0; sample < buffer.length; sample++) {
			double time = sample / sampleRate;
			double funValue =0;
			for(int j = 0; j<waveList.size();j++)
				funValue += amps.get(j)*Math.sin(twoPiFs.get(j) * time + waveList.get(j).getPhase());
			float fFunValue = (float) funValue;
			buffer[sample] = fFunValue;
		}

		final byte[] byteBuffer = new byte[buffer.length * 2];

		int bufferIndex = 0;
		for (int i = 0; i < byteBuffer.length; i++) {
			final int x = (int) (buffer[bufferIndex++] * 32767.0);

			byteBuffer[i++] = (byte) x;
			byteBuffer[i] = (byte) (x >>> 8);
		}
		
		out= new File("soundtmp"+ ".wav");
//		doZapisu=new File("generated/" + fileName + ".wav");
		final boolean bigEndian = false;
		final boolean signed = true;

		final int bits = 16;
		final int channels = 1;
//		if(saveCheck) {
			AudioFormat format = new AudioFormat((float) sampleRate, bits, channels, signed, bigEndian);
			ByteArrayInputStream bais = new ByteArrayInputStream(byteBuffer);
			AudioInputStream audioInputStream = new AudioInputStream(bais, format, buffer.length);
			AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, out);
			audioInputStream.close();
//			
//		}
		
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


