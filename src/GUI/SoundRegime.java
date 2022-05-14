/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Kouki
 */
public class SoundRegime {
        
    private AudioInputStream audioStream;
    private Clip audioClip;

    public SoundRegime(String song) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File audioFile = new File(song);
        audioStream = AudioSystem.getAudioInputStream(audioFile);

        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class,format);
        audioClip = (Clip) AudioSystem.getLine(info);
    }
    public void playAudio() throws IOException, LineUnavailableException {
        audioClip.open(audioStream);
        audioClip.start();
        audioClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stopAudio() throws IOException {
        audioClip.close();
        audioStream.close();
    }
}
