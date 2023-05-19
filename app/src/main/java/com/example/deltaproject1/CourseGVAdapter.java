package com.example.deltaproject1;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.deltaproject1.R;

import java.util.ArrayList;

public class CourseGVAdapter extends ArrayAdapter<CourseModel> {
    String hi = "5";
    TextView finalText = null;
    ArrayList<CourseModel> courseModelArrayList;

    public CourseGVAdapter(@NonNull Context context, ArrayList<CourseModel> courseModelArrayList) {
        super(context, 0, courseModelArrayList);
        this.courseModelArrayList = courseModelArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView=convertView;


        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.inter, parent, false);
        }

            CourseModel courseModel = getItem(position);
            TextView courseIV = listitemView.findViewById(R.id.buttony);
            courseIV.setText(String.valueOf(courseModel.getC()));
        return listitemView;
    }
}

