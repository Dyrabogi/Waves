package waves;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class SoundGenerator {
	public static void generate(Wave wave, String fileName) throws IOException {
		final double frequency = wave.getFreq();
		final double amplitude = wave.getAmp();
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
		

        File out = new File("generated/" + fileName + ".wav");

        final boolean bigEndian = false;
        final boolean signed = true;

        final int bits = 16;
        final int channels = 1;

        AudioFormat format = new AudioFormat((float)sampleRate, bits, channels, signed, bigEndian);
        ByteArrayInputStream bais = new ByteArrayInputStream(byteBuffer);
        AudioInputStream audioInputStream = new AudioInputStream(bais, format, buffer.length);
        AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, out);
        audioInputStream.close();
	}
}
