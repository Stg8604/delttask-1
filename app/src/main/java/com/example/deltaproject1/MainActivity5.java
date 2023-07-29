package com.example.deltaproject1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class MainActivity5 extends AppCompatActivity {
    private ArrayList<Character> all = new ArrayList<>();
    private HashMap<String, String> mab = new HashMap<>();
    private int gridboy1, gridboy2;
    private int totalgrid;
    private String word1Input_ma;
    private TextView finalans;
    private String temp2;
    private String temp="";
    private int index;
    private int currentindex=0;
    private int score=0;
    private int done=0;
    int lives=3;
    private Button btnToggleDark;
    private ArrayList<String> keys;
    private CourseGVAdapter adapter;
    private ImageView info,heart1,heart2,heart3;
    private ExtendedFloatingActionButton reset,check;
    private ArrayList<CourseModel> courseModelArrayList = new ArrayList<CourseModel>();
    private GridView gv;
    private TextView timer;
    private Button stort;
    private long timeleft;
    private boolean run;
    private CountDownTimer county;
    List<CardView> cardlist=new ArrayList<>();
    MediaPlayer media7,media1,media2,media3,media4,media5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        gv = (GridView) findViewById(R.id.idGVcourses);
        gridboy1=getIntent().getIntExtra("grid1",0);
        gridboy2=getIntent().getIntExtra("grid2",0);
        media1=MediaPlayer.create(MainActivity5.this,R.raw.endgame);
        media2=MediaPlayer.create(MainActivity5.this,R.raw.button);
        media3=MediaPlayer.create(MainActivity5.this,R.raw.info);
        media4=MediaPlayer.create(MainActivity5.this,R.raw.wrong);
        media5=MediaPlayer.create(MainActivity5.this,R.raw.back);
        heart1=findViewById(R.id.hearty1);
        heart2=findViewById(R.id.hearty2);
        heart3=findViewById(R.id.hearty3);
        reset=findViewById(R.id.reset2);
        check=findViewById(R.id.check2);
        finalans=findViewById(R.id.ansy);
        btnToggleDark=findViewById(R.id.btnToggleDark);
        media7=MediaPlayer.create(MainActivity5.this,R.raw.home);
        totalgrid=gridboy1*gridboy2;
        info=findViewById(R.id.info2);
        gv.setNumColumns(gridboy2);
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);
        if (isDarkModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            btnToggleDark.setText("Disable Dark Mode");
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            btnToggleDark.setText("Enable Dark Mode");
        }

        btnToggleDark.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view)
                    {
                        if (isDarkModeOn) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            editor.putBoolean("isDarkModeOn", false);
                            editor.apply();
                            btnToggleDark.setText("Enable Dark Mode");
                        }
                        else {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            editor.putBoolean("isDarkModeOn", true);
                            editor.apply();
                            btnToggleDark.setText("Disable Dark Mode");
                        }
                    }
                });
        int i=0;
        timeleft=getIntent().getIntExtra("timeleft",0);
        mab= (HashMap<String, String>) getIntent().getSerializableExtra("map");
        adapter = new CourseGVAdapter(this, courseModelArrayList);
        gv.setAdapter(adapter);
        keys = new ArrayList<>(mab.keySet());
        Collections.shuffle(keys);
        int p=0;
        for(int j=0;j<keys.get(currentindex).length();j++){
            temp=temp+"_ ";
            finalans.setText(temp);
        }
        for (String one : keys) {
            i = 0;
            while (i < one.length()) {
                all.add(one.charAt(i));
                i++;
            }
        }

        while (all.size() < totalgrid) {
            Random r = new Random();
            char a = (char) (r.nextInt(26) + 'a');
            all.add(a);
        }

        for (int j = 0; j < totalgrid; j++) {
            courseModelArrayList.add(new CourseModel(all.get(j)));
        }
        Collections.shuffle(courseModelArrayList);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                media5.start();
                Toast.makeText(MainActivity5.this, "Grid reset", Toast.LENGTH_SHORT).show();
                temp="";
                for(int j=0;j<keys.get(currentindex).length();j++){
                    temp=temp+"_ ";
                    finalans.setText(temp);
                }
                finalans.setText(temp);
                change();
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                media5.start();
                temp2 = temp;
                while (temp2.contains(" ")) {
                    temp2 = temp2.replace(" ", "");
                }
                if (!(temp.contains("_"))) {
                    if (temp2.equals(keys.get(currentindex))) {
                        media1.start();
                        if (lives == 3) {
                            score = score + 500;
                        } else if (lives == 2) {
                            score += 300;
                        } else {
                            score += 200;
                        }
                        done+=1;
                        if(done<keys.size()){
                            Toast.makeText(MainActivity5.this, "your guess is correct", Toast.LENGTH_SHORT).show();
                            currentindex+=1;
                            shuffle(keys);
                            change();
                        }
                        else{
                            showDialog2(score);
                            Toast.makeText(MainActivity5.this, "done", Toast.LENGTH_SHORT).show();
                            score=0;
                            lives=3;
                            heart1.setImageResource(R.mipmap.yellowheart);
                            heart2.setImageResource(R.mipmap.yellowheart);
                            heart3.setImageResource(R.mipmap.yellowheart);
                        }

                    }
                    else if(lives==3){
                        media4.start();
                        shuffle(keys);
                        heart3.setImageResource(R.drawable.ic_action_name);
                        Toast.makeText(MainActivity5.this, "your guess is wrong", Toast.LENGTH_SHORT).show();
                        lives-=1;
                        change();
                    }
                    else if(lives==2){
                        media4.start();
                        shuffle(keys);
                        Toast.makeText(MainActivity5.this, "your guess is wrong", Toast.LENGTH_SHORT).show();
                        heart2.setImageResource(R.drawable.ic_action_name);
                        lives-=1;
                        change();
                    }
                    else if(lives==1) {
                        media4.start();
                        shuffle(keys);
                        Toast.makeText(MainActivity5.this, "your guess is wrong", Toast.LENGTH_SHORT).show();
                        heart1.setImageResource(R.drawable.ic_action_name);
                        lives -= 1;
                        change();
                    }

                }else{
                    Toast.makeText(MainActivity5.this, "enter appropriately", Toast.LENGTH_SHORT).show();
                    change();
                }
            }
            });
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                media2.start();
                String s=String.valueOf(courseModelArrayList.get(position).getC());
                texter(s);
                CardView cardView = (CardView) view;
                cardView.setVisibility(View.INVISIBLE);
                cardlist.add(cardView);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog(mab.get(keys.get(currentindex)));
                media3.start();
            }
        });
        SharedPreferences sharedPreferences2 = getSharedPreferences("highscorefile", MODE_PRIVATE);
        int highscore = sharedPreferences2.getInt("highscorekey",0);
        if (highscore<score) {
            SharedPreferences.Editor editor2 = sharedPreferences.edit();
            editor2.putInt("highscorekey", score);
            editor2.commit();
        }
    }
    private void change(){
        for(CardView card:cardlist){
            card.setVisibility(View.VISIBLE);
        }
    }
    private void showDialog2(int score){
            getWindow().setEnterTransition(new Slide());
            final Dialog dialog=new Dialog(MainActivity5.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.popup2);
            TextView txt=dialog.findViewById(R.id.score);
            txt.setText("Your Score:"+Integer.toString(score));
            ExtendedFloatingActionButton btn1=dialog.findViewById(R.id.home);
            ExtendedFloatingActionButton btn2=dialog.findViewById(R.id.again);
            dialog.show();
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    media7.start();
                    Intent intent=new Intent(MainActivity5.this,MainActivity3.class);
                    startActivity(intent);
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shuffle(keys);
                    dialog.dismiss();
                    media7.start();
                    heart3.setImageResource(R.mipmap.yellowheart);
                    heart2.setImageResource(R.mipmap.yellowheart);
                    heart1.setImageResource(R.mipmap.yellowheart);
                }
            });
    }
    private void texter(String s){
        if(temp.contains("_ ")) {
            index = temp.indexOf('_');
            temp = temp.substring(0, index) + s + temp.substring(index + 1, temp.length());
            finalans.setText(temp);
        }
        else{
            Toast.makeText(MainActivity5.this, "max char inputted", Toast.LENGTH_SHORT).show();
        }
    }
    private void shuffle(List<String> keys){
        Collections.shuffle(courseModelArrayList);
        adapter.notifyDataSetChanged();
        temp="";
        for(int j=0;j<keys.get(currentindex).length();j++) {
            temp = temp + "_ ";
            finalans.setText(temp);
        }

        }
    private void showCustomDialog(String clue) {
        final Dialog dialog=new Dialog(MainActivity5.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.popup);
        TextView txt=dialog.findViewById(R.id.clue);
        ExtendedFloatingActionButton btn=dialog.findViewById(R.id.okay);
        txt.setText(clue);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
