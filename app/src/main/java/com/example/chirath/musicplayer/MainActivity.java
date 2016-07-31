package com.example.chirath.musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView nowPlaying;
    private ImageView songNamebackground;
    private ImageView albumArt;
    private ImageButton playPause;
    private ImageButton next;
    private ImageButton previous;
    private SeekBar seekbar;
    private TextView songTitle;
    private TextView currentTime;
    private TextView endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();
    }

    private void initListeners() {
    }

    private void initViews() {
        nowPlaying = (ImageView) findViewById(R.id.nowPlaying);
        nowPlaying.setImageDrawable(getResources().getDrawable(R.drawable.now_playing));

        songNamebackground = (ImageView) findViewById(R.id.songTextBackground);
        songNamebackground.setImageDrawable(getResources().getDrawable(R.drawable.song_title_place_holder));

        albumArt = (ImageView) findViewById(R.id.albumArt);
        albumArt.setImageDrawable(getResources().getDrawable(R.drawable.album_art));

        playPause = (ImageButton) findViewById(R.id.playPause);
        playPause.setImageDrawable(getResources().getDrawable(R.drawable.play_button));

        next = (ImageButton) findViewById(R.id.next);
        next.setImageDrawable(getResources().getDrawable(R.drawable.fast_forward_button));

        previous = (ImageButton) findViewById(R.id.previous);
        previous.setImageDrawable(getResources().getDrawable(R.drawable.rewind_button));

        seekbar = (SeekBar) findViewById(R.id.seekBar);

        songTitle = (TextView) findViewById(R.id.songTitle);
        currentTime = (TextView) findViewById(R.id.currentTime);
        endTime = (TextView) findViewById(R.id.endTime);
    }
}
