package com.example.myapplication2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;

public class Main3Activity extends AppCompatActivity {
    VideoView videoView;
    MediaController mediaController;
    SurfaceHolder surfaceHolder;
    MediaPlayer mediaPlayer;
    LinearLayoutCompat videoLayout;
    SeekBar seekBar;
    TextView repeat_textView;
    TextView sura_name;
    SwitchCompat switch_only_aya;
    EditText aya_number;
    CardView fatiha_card;
    CardView annas_card;
    ImageButton play_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        videoView = findViewById(R.id.videoView);
        videoLayout = findViewById(R.id.videoLayout);
        seekBar = findViewById(R.id.progress_bar);
        repeat_textView = findViewById(R.id.repeat);
        sura_name = findViewById(R.id.sura_name);
        aya_number = findViewById(R.id.aya_number);
        switch_only_aya = findViewById(R.id.chip4);
        fatiha_card = findViewById(R.id.fatiha_card);
        annas_card = findViewById(R.id.annas_card);
        play_button = findViewById(R.id.play_button);
        mediaController = new MediaController(this);
        videoView.setOnCompletionListener(mediaPlayer1 -> {
            seekBar.setProgress(seekBar.getProgress()-1);
            repeat(mediaPlayer1);
//            play_button.setImageResource(R.drawable.pause_icon);
//            play_button.setTag("pause");

        });
        switch_only_aya.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                aya_number.setEnabled(true);
                aya_number.setBackgroundColor(Color.parseColor("#ffffff"));
            } else {
                aya_number.setText("");
                aya_number.setEnabled(false);
                aya_number.setBackgroundColor(Color.parseColor("#999999"));
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                repeat_textView.setText(String.valueOf(progress));
            }
        });
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.fatiha);
        initializeCard();
        play_button.setOnClickListener(this::play_or_pause);

        mediaController.setAnchorView(videoLayout);
        mediaController.setVisibility(View.INVISIBLE);
        videoView.setMediaController(mediaController);
    }

    private void play_or_pause(View view) {
        if (((String) play_button.getTag()).equals("play")) {
            play(view);
        } else {
            videoView.pause();
            play_button.setImageResource(R.drawable.play_icon);
            play_button.setTag("play");
        }
    }
    private void play(View view) {
        if (switch_only_aya.isChecked()) {
            // ?????????? ???????????? ????????????????
            switch (sura_name.getText().toString()) {
                case "???????????? ????????????????????": {
                    // ?????????? ?????? ?????????? ????????????????
                    switch (aya_number.getText().toString()) {
                        case "1":
                            videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.fatiha_1);
                            break;
                        default:
                            Toast.makeText(this, "???????? ?????????? ?????? ?????? 1 ?? 8  ", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
                case "???????????? ??????????????": {

                }
                break;
            }
        }

            videoView.start();
            play_button.setImageResource(R.drawable.pause_icon);
            play_button.setTag("pause");

    }

    private void repeat(MediaPlayer mediaPlayer) {
        int progress = seekBar.getProgress();
        if (progress > 0) {
//            seekBar.setProgress(--progress);
            mediaPlayer.start();
        } else {
            play_button.setImageResource(R.drawable.play_icon);
            play_button.setTag("play");
        }

    }
    private void initializeCard() {
        fatiha_card.setOnClickListener(view -> {
            animateCard(view);
            sura_name.setText("???????????? ????????????????????");
            videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.fatiha);
            if (seekBar.getProgress() > 0) {
                seekBar.setProgress(seekBar.getProgress() - 1);
//                videoView.start();
            }
        });
        annas_card.setOnClickListener(view -> {
            animateCard(view);
            sura_name.setText("???????????? ??????????????");
            videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.annas);
            if (seekBar.getProgress() > 0) {
                seekBar.setProgress(seekBar.getProgress() - 1);
//                videoView.start();
            }
        });
    }

    private void animateCard(View view) {
        view.animate()
                .scaleX(1.2F).scaleY(1.2F)
                .setDuration(500)
                .setInterpolator(new LinearInterpolator()).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                view.setScaleX(1);
                view.setScaleY(1);
            }
        });
    }

//    public void pause(View view) {
//        if (videoView.isPlaying()) {
//            videoView.pause();
//            playButton.setImageResource(R.drawable.play_icon);
//        } else {
//            videoView.start();
//            playButton.setImageResource(R.drawable.pause_icon);
//        }
//    }

}
