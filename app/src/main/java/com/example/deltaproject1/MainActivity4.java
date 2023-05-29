package com.example.deltaproject1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity4 extends AppCompatActivity {
    private Button check1,check2,stocks;
    private ImageView starting;
    private ArrayList<String> words=new ArrayList<>();
    private ArrayList<String> clues=new ArrayList<>();
    private HashMap<String,String> worclue=new HashMap<String,String>();
    private EditText edit1,edit2,edit3,edit4;
    private GridView gv;
    private TextView txttime,txttime2;
    private Button btk;
    private CardView rl2;
    private TextView highscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        edit1=findViewById(R.id.edt1);
        edit2=findViewById(R.id.edttxt2);
        edit3=findViewById(R.id.grid1);
        edit4=findViewById(R.id.griddy);
        check1=findViewById(R.id.check1);
        txttime=findViewById(R.id.txttime);
        starting=findViewById(R.id.startbutton2);
        rl2=findViewById(R.id.rl2);
        stocks=findViewById(R.id.stocks);
        highscore=findViewById(R.id.highestscore2);
        SharedPreferences sharedPreferences = getSharedPreferences("highscorefile", MODE_PRIVATE);
        highscore.setText("Highscore:"+String.valueOf(sharedPreferences.getInt("highscorekey",0)));
        check1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worclue.put(edit1.getText().toString(),edit2.getText().toString());
                edit1.setText("");
                edit2.setText("");
            }
        });
        starting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog();

            }
        });
    }

    private void showdialog() {
        getWindow().setEnterTransition(new Slide());
        final Dialog dialog=new Dialog(MainActivity4.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.popup3);
        ExtendedFloatingActionButton btn1=dialog.findViewById(R.id.classic);
        ExtendedFloatingActionButton btn2=dialog.findViewById(R.id.speedrun);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity4.this,MainActivity5.class);
                intent.putExtra("map",worclue);
                intent.putExtra("grid1",Integer.parseInt(edit3.getText().toString()));
                intent.putExtra("grid2",Integer.parseInt(edit4.getText().toString()));
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                rl2.setVisibility(View.VISIBLE);

            }
        });
        dialog.show();
        stocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity4.this,MainActivity6.class);
                intent.putExtra("map",worclue);
                intent.putExtra("timeleft",Integer.parseInt(txttime.getText().toString()));
                intent.putExtra("grid1",Integer.parseInt(edit3.getText().toString()));
                intent.putExtra("grid2",Integer.parseInt(edit4.getText().toString()));
                startActivity(intent);
            }
        });
    }

    private void showdialog3() {
        getWindow().setEnterTransition(new Slide());
        final Dialog dialog2=new Dialog(MainActivity4.this);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setCancelable(false);
        dialog2.setContentView(R.layout.popup4);
        txttime=dialog2.findViewById(R.id.txttime);
        stocks=dialog2.findViewById(R.id.stocks);
        stocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity4.this,MainActivity6.class);
                intent.putExtra("map",worclue);
                intent.putExtra("timeleft",txttime.getText().toString());
                intent.putExtra("grid1",Integer.parseInt(edit3.getText().toString()));
                intent.putExtra("grid2",Integer.parseInt(edit4.getText().toString()));
                startActivity(intent);
            }
        });
    }
}