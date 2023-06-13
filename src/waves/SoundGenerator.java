package waves;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.JFileChooser;

import java.awt.*;

public class SoundGenerator implements Runnable{
	public Boolean czynny=true;
	public static Clip clip;
	static AudioInputStream stream;
	public static File out, doZapisu;
	static ArrayList<Wave> wavesList = new ArrayList();
	 SoundGenerator(){
		 for (Wave wave : MainFrame.waves) {
					try {
						generate(wave, "wave-freq-" + wave.getFreq() + "-sound",false);
					} catch (IOException e) {
						e.printStackTrace();
					}
		
	 }
	 }
	 
	public static void generate(Wave wave, String fileName, boolean saveCheck) throws IOException {
		final double frequency = wave.getFreq();
		final double amplitude = 1;
		final double seconds = 2.0;
		final double twoPiF = 2 * Math.PI * frequency;
		final double sampleRate = 2 * twoPiF;
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
		doZapisu=new File("generated/" + fileName + ".wav");
		final boolean bigEndian = false;
		final boolean signed = true;

		final int bits = 16;
		final int channels = 1;
		if(saveCheck) {
			AudioFormat format = new AudioFormat((float) sampleRate, bits, channels, signed, bigEndian);
			ByteArrayInputStream bais = new ByteArrayInputStream(byteBuffer);
			AudioInputStream audioInputStream = new AudioInputStream(bais, format, buffer.length);
			AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, out);
			audioInputStream.close();
			
		}
		   
	}
	
	public static void saveSounds(ArrayList<Wave> waveList) throws IOException {
		final ArrayList<Double> freqs = new ArrayList();
		if(waveList.size()!=0) {
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
			double totalAmp = 0;
			for(double val : amps)   
				totalAmp += val;
			for(double val : amps)
				val /= totalAmp;
			
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
		JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Wybierz plik");
        int result = chooser.showDialog(null, "Wybierz");
        if (JFileChooser.APPROVE_OPTION == result){
        System.out.println("Wybrano plik: " + chooser.getSelectedFile());
        doZapisu= new File(chooser.getSelectedFile().getPath()+".wav");
		final boolean bigEndian = false;
		final boolean signed = true;
		final int bits = 16;
		final int channels = 1;
			AudioFormat format = new AudioFormat((float) sampleRate, bits, channels, signed, bigEndian);
			ByteArrayInputStream bais = new ByteArrayInputStream(byteBuffer);
			AudioInputStream audioInputStream = new AudioInputStream(bais, format, buffer.length);
			AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, doZapisu);
			audioInputStream.close();
		
        }else {
            System.out.println("Nie wybrano pliku");
        }

	
		}
		
	
		
	}
	
	public static void playSounds(ArrayList<Wave> waveList) throws IOException {
		if(waveList.size()!=0) {
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
		float[] buffer = new float[(int) (seconds * sampleRate)];

		for (int sample = 0; sample < buffer.length; sample++) {
			double time = sample / sampleRate;
			double funValue =0;
			double totalAmp = 0;
			for(double val : amps)   
				totalAmp += val;
			for(double val : amps)
				val /= totalAmp;
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
		final boolean bigEndian = false;
		final boolean signed = true;

		final int bits = 16;
		final int channels = 1;
			AudioFormat format = new AudioFormat((float) sampleRate, bits, channels, signed, bigEndian);
			ByteArrayInputStream bais = new ByteArrayInputStream(byteBuffer);
			AudioInputStream audioInputStream = new AudioInputStream(bais, format, buffer.length);
			AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, out);
			audioInputStream.close();
			out.deleteOnExit();
		}
		
		
	}
	public void delFile() {
		out.deleteOnExit();
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


