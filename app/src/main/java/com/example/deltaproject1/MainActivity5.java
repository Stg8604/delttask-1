package com.example.deltaproject1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private GridView griddy;
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
    private List<String> keys;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        //Assigning gridview
        gv = (GridView) findViewById(R.id.idGVcourses);
        //both are grid sizes
        gridboy1=getIntent().getIntExtra("grid1",0);
        gridboy2=getIntent().getIntExtra("grid2",0);
        heart1=findViewById(R.id.hearty1);
        heart2=findViewById(R.id.hearty2);
        heart3=findViewById(R.id.hearty3);
        reset=findViewById(R.id.reset2);
        check=findViewById(R.id.check2);
        finalans=findViewById(R.id.ansy);
        btnToggleDark=findViewById(R.id.btnToggleDark);
        totalgrid=gridboy1*gridboy2;
        info=findViewById(R.id.info2);
        gv.setNumColumns(gridboy2);
        SharedPreferences sharedPreferences
                = getSharedPreferences(
                "sharedPrefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor
                = sharedPreferences.edit();
        final boolean isDarkModeOn
                = sharedPreferences
                .getBoolean(
                        "isDarkModeOn", false);

        // When user reopens the app
        // after applying dark/light mode
        if (isDarkModeOn) {
            AppCompatDelegate
                    .setDefaultNightMode(
                            AppCompatDelegate
                                    .MODE_NIGHT_YES);
            btnToggleDark.setText(
                    "Disable Dark Mode");
        }
        else {
            AppCompatDelegate
                    .setDefaultNightMode(
                            AppCompatDelegate
                                    .MODE_NIGHT_NO);
            btnToggleDark
                    .setText(
                            "Enable Dark Mode");
        }

        btnToggleDark.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view)
                    {
                        // When user taps the enable/disable
                        // dark mode button
                        if (isDarkModeOn) {

                            // if dark mode is on it
                            // will turn it off
                            AppCompatDelegate
                                    .setDefaultNightMode(
                                            AppCompatDelegate
                                                    .MODE_NIGHT_NO);
                            // it will set isDarkModeOn
                            // boolean to false
                            editor.putBoolean(
                                    "isDarkModeOn", false);
                            editor.apply();

                            // change text of Button
                            btnToggleDark.setText(
                                    "Enable Dark Mode");
                        }
                        else {

                            // if dark mode is off
                            // it will turn it on
                            AppCompatDelegate
                                    .setDefaultNightMode(
                                            AppCompatDelegate
                                                    .MODE_NIGHT_YES);

                            // it will set isDarkModeOn
                            // boolean to true
                            editor.putBoolean(
                                    "isDarkModeOn", true);
                            editor.apply();

                            // change text of Button
                            btnToggleDark.setText(
                                    "Disable Dark Mode");
                        }
                    }
                });
        int i=0;
        //hashmap containing words and clue
        timeleft=getIntent().getIntExtra("timeleft",0);
        mab= (HashMap<String, String>) getIntent().getSerializableExtra("map");
        //setting all array list
        //adapter setting up
        adapter = new CourseGVAdapter(this, courseModelArrayList);
        gv.setAdapter(adapter);
        //shuffling the map
        keys = new ArrayList<>(mab.keySet());
        Collections.shuffle(keys);
        for(int j=0;j<keys.get(currentindex).length();j++){
                temp=temp+"_ ";
                finalans.setText(temp);
        }
        for(String one:keys){
            while(i<one.length()){
                all.add(one.charAt(i));
                i++;
            }
            while(i<totalgrid){
                Random r = new Random();
                char a=(char)(r.nextInt(26) + 'a');
                all.add(a);
                i++;
            }
        }
        //setting up adapter list
        for(int j=0;j<totalgrid;j++){
            courseModelArrayList.add(new CourseModel(all.get(j)));
        }
        Collections.shuffle(courseModelArrayList);
        //setting reset
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity5.this, "Grid reset", Toast.LENGTH_SHORT).show();
                temp="";
                for(int j=0;j<keys.get(currentindex).length();j++){
                    temp=temp+"_ ";
                    finalans.setText(temp);
                }
                finalans.setText(temp);
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp2 = temp;
                while (temp2.contains(" ")) {
                    temp2 = temp2.replace(" ", "");
                }
                if (!(temp.contains("_"))) {
                    if (temp2.equals(keys.get(currentindex))) {
                        if (lives == 3) {
                            score = score + 500;
                        } else if (lives == 2) {
                            score += 300;
                        } else {
                            score += 200;
                        }
                        done+=1;
                        if(done<keys.size()){
                            currentindex+=1;
                            shuffle(keys);
                        }
                        else{
                            showDialog2(score);
                            Toast.makeText(MainActivity5.this, "done", Toast.LENGTH_SHORT).show();
                            lives=3;
                            heart1.setImageResource(R.mipmap.yellowheart);
                            heart2.setImageResource(R.mipmap.yellowheart);
                            heart3.setImageResource(R.mipmap.yellowheart);
                        }

                    }
                    else if(lives==3){
                        shuffle(keys);
                        heart3.setImageResource(R.drawable.ic_action_name);
                        Toast.makeText(MainActivity5.this, "your guess is wrong", Toast.LENGTH_SHORT).show();
                        lives-=1;
                    }
                    else if(lives==2){
                        shuffle(keys);
                        Toast.makeText(MainActivity5.this, "your guess is wrong", Toast.LENGTH_SHORT).show();
                        heart2.setImageResource(R.drawable.ic_action_name);
                        lives-=1;
                    }
                    else if(lives==1) {
                        shuffle(keys);
                        Toast.makeText(MainActivity5.this, "your guess is wrong", Toast.LENGTH_SHORT).show();
                        heart1.setImageResource(R.drawable.ic_action_name);
                        lives -= 1;
                    }

                }else{
                    Toast.makeText(MainActivity5.this, "enter appropriately", Toast.LENGTH_SHORT).show();
                }
            }
            });

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s=String.valueOf(courseModelArrayList.get(position).getC());
                texter(s);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog(mab.get(keys.get(currentindex)));
            }
        });
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
                    Intent intent=new Intent(MainActivity5.this,MainActivity3.class);
                    startActivity(intent);
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shuffle(keys);
                    dialog.dismiss();
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
