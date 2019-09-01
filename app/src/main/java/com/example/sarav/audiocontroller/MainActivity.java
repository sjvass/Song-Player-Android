//Sara Vass
//CSE298 Audio Controller
//App that plays, pauses and stops audio while displaying the
//position in the audio
package com.example.sarav.audiocontroller;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    //declare a MediaPlayer object to play sound
    MediaPlayer mp;
    //create global TextView
    TextView secs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initalize mp to play song
        mp = MediaPlayer.create(this, R.raw.bohemian_rhapsody);

        //initalize secs
        secs = (TextView) findViewById(R.id.textView2);

        //create timer object to check on media every second
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //if audio is playing, updates TextView
                if (mp.isPlaying()) {
                    //makes sure timer task is running on main thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int pos = (mp.getDuration() - mp.getCurrentPosition()) / 1000;
                            secs.setText(Integer.toString(pos));
                        }
                    });
                }

            }
        }, 0, 1000);
    }

    //plays audio when play button is clicked
    public void playClick(View view) {
        mp.start();
    }

    //when pause button is clicked
    //pauses audio and displays that audio is paused
    public void pauseClick(View view) {
        mp.pause();
        secs.append(" (paused)");
    }

    //when stop button is clicked
    //pauses the audio, resets the player to the beginning of the audio and
    //displays that audio is stopped
    public void stopClick(View view) {
        mp.pause();
        mp.seekTo(0);
        secs.setText("Stopped");
    }
}
