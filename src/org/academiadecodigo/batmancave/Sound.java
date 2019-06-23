package org.academiadecodigo.batmancave;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Sound {

    //properties
    private Clip audioClip;

    //playThemeSound method
    public void play(String sound, boolean loop) {
        try {
            URL soundFile = getClass().getResource(sound);
            AudioInputStream audioStreamObj = AudioSystem.getAudioInputStream(soundFile);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStreamObj);
            audioClip.start();
            if (loop) {
                audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (UnsupportedAudioFileException uns) {
            System.out.println("Unsupported Audio Exception");
        } catch (IOException io) {
            System.out.println("Unsupported IO Exception");
        } catch (LineUnavailableException line) {
            System.out.println("Line Unavailable Exception");
        }
    }

    //stop
    public void stop () {
        audioClip.stop();
    }
}
