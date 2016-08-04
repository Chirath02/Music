package com.example.chirath.musicplayer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.Format;

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

    private MediaPlayer mediaPlayer;
    private Handler newHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();
    }

    private void initListeners() {
        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    playPause.setImageDrawable(getResources().getDrawable(R.drawable.play_button));
                }
                else {
                    mediaPlayer.start();
                    playPause.setImageDrawable(getResources().getDrawable(R.drawable.pause_button));
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 2000);
                }
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 2000);
                }
            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress * (mediaPlayer.getDuration()/100));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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

        mediaPlayer = MediaPlayer.create(this, R.raw.hamari);

        long totalTime = mediaPlayer.getDuration();
        totalTime /= 1000;
        endTime.setText(String.format("%02d:%02d", totalTime/60, totalTime%60));


        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        Uri mediaPath = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.hamari);
        mmr.setDataSource(this, mediaPath);

        String albumTitle = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        songTitle.setText(albumTitle);
    }
}
