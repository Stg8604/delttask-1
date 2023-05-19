package com.example.deltaproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    private String one,two;
    private String temp="";
    private String temp2="";
    int index;
    int score=0;
    private ArrayList<Character> elements=new ArrayList<>();
    private TextView ansbox;
    private Button txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,txt11,txt12,txt13,txt14,txt15,txt16;
    private ExtendedFloatingActionButton reset,check;
    private ImageView info,heart1,heart2,heart3;
    private int lives=3;
    MediaPlayer media1,media2,media3,media4,media5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getWindow().setEnterTransition(new Slide());
        media1=MediaPlayer.create(MainActivity2.this,R.raw.endgame);
        media2=MediaPlayer.create(MainActivity2.this,R.raw.button);
        media3=MediaPlayer.create(MainActivity2.this,R.raw.info);
        media4=MediaPlayer.create(MainActivity2.this,R.raw.wrong);
        media5=MediaPlayer.create(MainActivity2.this,R.raw.back);
        one=getIntent().getStringExtra("str1");
        two=getIntent().getStringExtra("str2");
        int i=0;
        while(i<one.length()){
            elements.add(one.charAt(i));
                i++;
        }
        while(i<16){
            Random r = new Random();
            char a=(char)(r.nextInt(26) + 'a');
                elements.add(a);
                i++;

        }
        Collections.shuffle(elements);
        Assign();
        ansbox.setText(temp);
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texter(txt1);
                txt1.setBackgroundResource(R.drawable.grayedout);
            }

        });
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texter(txt2);
                txt2.setBackgroundResource(R.drawable.grayedout);
            }

        });
        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texter(txt3);
                txt3.setBackgroundResource(R.drawable.grayedout);
            }

        });
        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texter(txt4);
                txt4.setBackgroundResource(R.drawable.grayedout);
            }

        });
        txt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texter(txt5);
                txt5.setBackgroundResource(R.drawable.grayedout);
            }

        });
        txt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texter(txt6);
                txt6.setBackgroundResource(R.drawable.grayedout);
            }

        });
        txt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texter(txt7);
                txt7.setBackgroundResource(R.drawable.grayedout);
            }

        });
        txt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texter(txt8);
                txt8.setBackgroundResource(R.drawable.grayedout);
            }

        });
        txt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texter(txt9);
                txt9.setBackgroundResource(R.drawable.grayedout);
            }

        });
        txt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texter(txt10);
                txt10.setBackgroundResource(R.drawable.grayedout);
            }

        });
        txt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texter(txt11);
                txt11.setBackgroundResource(R.drawable.grayedout);
            }

        });
        txt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texter(txt12);
                txt12.setBackgroundResource(R.drawable.grayedout);
            }

        });
        txt13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texter(txt13);
                txt13.setBackgroundResource(R.drawable.grayedout);
            }

        });
        txt14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    texter(txt14);
                txt14.setBackgroundResource(R.drawable.grayedout);
                }
        });
        txt15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texter(txt15);
                txt15.setBackgroundResource(R.drawable.grayedout);
            }

        });
        txt16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texter(txt16);
                txt16.setBackgroundResource(R.drawable.grayedout);
            }

        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                media5.start();
                Toast.makeText(MainActivity2.this, "Grid Reset", Toast.LENGTH_SHORT).show();
                temp="";
                for(int i=0;i<one.length();i++){
                    temp=temp+"_ ";
                }
                ansbox.setText(temp);
                normal();
            }
        });


            info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                media3.start();
                showCustomDialog();
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checker();
            }
        });

    }

    private void checker() {

        temp2=temp;
        while(temp2.contains(" ")){
            temp2=temp2.replace(" ","");
        }
        if(!(temp.contains("_"))){
        if(temp2.equals(one.toLowerCase())){
            Toast.makeText(this, "Your Guess is correct", Toast.LENGTH_SHORT).show();
            media1.start();
            if(lives==3){
                score=score+500;
            }
            else if(lives==2){
                score+=300;
            }
            else{
                score+=200;
            }
            SharedPreferences sharedPreferences = getSharedPreferences("highscorefile", MODE_PRIVATE);
            int highscore = sharedPreferences.getInt("highscorekey",0);

            if (highscore<score) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("highscorekey", score);
                editor.commit();
            }
            showDialog1(score);
            lives=3;
            heart1.setBackground(getDrawable(R.mipmap.yellowheart));
            heart2.setBackground(getDrawable(R.mipmap.yellowheart));
            heart3.setBackground(getDrawable(R.mipmap.yellowheart));
        }
        else if(lives==3){
            media4.start();
            Collections.shuffle(elements);
            change();
            heart3.setImageResource(R.drawable.ic_action_name);
            Toast.makeText(this, "Your guess is wrong", Toast.LENGTH_SHORT).show();
            lives-=1;
        }
        else if(lives==2){
            media4.start();
            Collections.shuffle(elements);
            change();
            Toast.makeText(this, "Your guess is wrong", Toast.LENGTH_SHORT).show();
            heart2.setImageResource(R.drawable.ic_action_name);
            lives-=1;
        }
        else if(lives==1) {
            media4.start();
            Collections.shuffle(elements);
            change();
            Toast.makeText(this, "Your guess is wrong", Toast.LENGTH_SHORT).show();
            heart1.setImageResource(R.drawable.ic_action_name);
            lives -= 1;
            showDialog1(score);
        }
        }else{
            media4.start();
            Toast.makeText(this, "enter everything correctly", Toast.LENGTH_SHORT).show();
        }
    }
    private void showDialog1(int score) {
        getWindow().setEnterTransition(new Slide());
        final Dialog dialog=new Dialog(MainActivity2.this);
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
                media5.start();
                Intent intent=new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                media5.start();
                Collections.shuffle(elements);
                change();
                dialog.dismiss();
            }
        });
    }

    private void texter(TextView txt){
        if(temp.contains("_ ")) {
            index = temp.indexOf('_');
            temp = temp.substring(0, index) + txt.getText().toString() + temp.substring(index + 1, temp.length());
            ansbox.setText(temp);
        }
        else{
            Toast.makeText(MainActivity2.this, "max char inputted", Toast.LENGTH_SHORT).show();
        }
        media2.start();
    }

    private void showCustomDialog() {
        final Dialog dialog=new Dialog(MainActivity2.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.popup);
        TextView txt=dialog.findViewById(R.id.clue);
        ExtendedFloatingActionButton btn=dialog.findViewById(R.id.okay);
        txt.setText(two);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private void Assign(){
        txt1=findViewById(R.id.textView1);
        txt2=findViewById(R.id.textView2);
        txt3=findViewById(R.id.textView3);
        txt4=findViewById(R.id.textView4);
        txt5=findViewById(R.id.textView5);
        txt6=findViewById(R.id.textView6);
        txt7=findViewById(R.id.textView7);
        txt8=findViewById(R.id.textView8);
        txt9=findViewById(R.id.textView9);
        txt10=findViewById(R.id.textView10);
        txt11=findViewById(R.id.textView11);
        txt12=findViewById(R.id.textView12);
        txt13=findViewById(R.id.textView13);
        txt14=findViewById(R.id.textView14);
        txt15=findViewById(R.id.textView15);
        txt16=findViewById(R.id.textView16);
        ansbox=findViewById(R.id.textView17);
        reset=findViewById(R.id.reset);
        check=findViewById(R.id.check);
        info=findViewById(R.id.info);
        heart1=findViewById(R.id.hearty1);
        heart2=findViewById(R.id.hearty2);
        heart3=findViewById(R.id.hearty3);
        change();
    }
    private void change(){
        txt1.setText(elements.get(0).toString());
        txt2.setText(elements.get(1).toString());
        txt3.setText(elements.get(2).toString());
        txt4.setText(elements.get(3).toString());
        txt5.setText(elements.get(4).toString());
        txt6.setText(elements.get(5).toString());
        txt7.setText(elements.get(6).toString());
        txt8.setText(elements.get(7).toString());
        txt9.setText(elements.get(8).toString());
        txt10.setText(elements.get(9).toString());
        txt11.setText(elements.get(10).toString());
        txt12.setText(elements.get(11).toString());
        txt13.setText(elements.get(12).toString());
        txt14.setText(elements.get(13).toString());
        txt15.setText(elements.get(14).toString());
        txt16.setText(elements.get(15).toString());
        temp="";
        normal();
        for(int i=0;i<one.length();i++){
            temp=temp+"_ ";
            ansbox.setText(temp);
        }
        score=0;

    }
    private void normal(){
        txt1.setBackgroundResource(R.drawable.pinky);
        txt2.setBackgroundResource(R.drawable.pinky);
        txt3.setBackgroundResource(R.drawable.pinky);
        txt4.setBackgroundResource(R.drawable.pinky);
        txt5.setBackgroundResource(R.drawable.pinky);
        txt6.setBackgroundResource(R.drawable.pinky);
        txt7.setBackgroundResource(R.drawable.pinky);
        txt8.setBackgroundResource(R.drawable.pinky);
        txt9.setBackgroundResource(R.drawable.pinky);
        txt10.setBackgroundResource(R.drawable.pinky);
        txt11.setBackgroundResource(R.drawable.pinky);
        txt12.setBackgroundResource(R.drawable.pinky);
        txt13.setBackgroundResource(R.drawable.pinky);
        txt14.setBackgroundResource(R.drawable.pinky);
        txt15.setBackgroundResource(R.drawable.pinky);
        txt16.setBackgroundResource(R.drawable.pinky);

    }
}