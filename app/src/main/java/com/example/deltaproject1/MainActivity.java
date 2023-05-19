package com.example.deltaproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView startbutton;
    private TextView highscore;
    private EditText edttxt1,edttxt2;
    private MediaPlayer media;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        setContentView(R.layout.activity_main);
        getWindow().setEnterTransition(new Slide());
        getWindow().setExitTransition(new Explode());
        startbutton=findViewById(R.id.startbutton);
        edttxt1=findViewById(R.id.editText);
        media=MediaPlayer.create(this,R.raw.click);
        edttxt2=findViewById(R.id.edttxt2);
        highscore=findViewById(R.id.highestscore);
        SharedPreferences sharedPreferences = getSharedPreferences("highscorefile", MODE_PRIVATE);
        highscore.setText("Highscore:"+String.valueOf(sharedPreferences.getInt("highscorekey",0)));
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                media.start();
                if(edttxt1.getText().toString().length()>0 && edttxt1.getText().toString().length()<=16 && edttxt2.getText().toString().length()>0) {
                    openNextpage();
                }else{
                    Toast.makeText(MainActivity.this, "please enter both word and clue appropriately", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void openNextpage(){
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("str1",edttxt1.getText().toString());
            intent.putExtra("str2",edttxt2.getText().toString());
            startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}