package com.example.fragment_example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView nameOfNoteOne,nameOfNoteTwo,nameOfNoteThree;
    private EditText currentNoteOne,currentNoteTwo,currentNoteThree;
    private ImageView save;
    private StringBuilder buffer_note_one;
    private StringBuilder buffer_note_two;
    private StringBuilder buffer_note_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        addOnClickListener();
    }

    private void initView(){
        nameOfNoteOne = findViewById(R.id.nameOfFirstNote);
        nameOfNoteTwo = findViewById(R.id.nameOfSecondNote);
        nameOfNoteThree = findViewById(R.id.nameOfThirdNote);
        currentNoteOne = findViewById(R.id.current_note_one);
        currentNoteTwo = findViewById(R.id.current_note_two);
        currentNoteThree = findViewById(R.id.current_note_three);
        save = findViewById(R.id.save);

    }

    private void addOnClickListener(){
        nameOfNoteOne.setOnClickListener(v -> {
            CurrentNoteOne note_one = new CurrentNoteOne();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_container,note_one);
            ft.commit();
            try {
                currentNoteOne.setText(String.valueOf(buffer_note_one.toString()));
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        });
        nameOfNoteTwo.setOnClickListener(v->{
            CurrentNoteTwo note_two = new CurrentNoteTwo();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_container,note_two);
            ft.commit();
            try {
                currentNoteTwo.setText(String.valueOf(buffer_note_two.toString()));
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        });
        nameOfNoteThree.setOnClickListener(v->{
            CurrentNoteThree note_three = new CurrentNoteThree();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_container,note_three);
            ft.commit();
            try {
                currentNoteThree.setText(String.valueOf(buffer_note_three.toString()));
            }catch (NullPointerException e){
                e.printStackTrace();
            }

        });
        save.setOnClickListener(v->{
            try{
                buffer_note_one = new StringBuilder();
                buffer_note_two = new StringBuilder();
                buffer_note_three = new StringBuilder();
                buffer_note_one.replace(0,buffer_note_one.length(),String.valueOf(currentNoteOne.getText()));
                buffer_note_two.replace(0,buffer_note_two.length(),String.valueOf(currentNoteTwo.getText()));
                buffer_note_three.replace(0,buffer_note_three.length(),String.valueOf(currentNoteThree.getText()));
            }catch (NullPointerException e){
                e.printStackTrace();
            }

        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState){
        super.onSaveInstanceState(instanceState);
        instanceState.putString(String.valueOf(buffer_note_one), String.valueOf(currentNoteOne.getText()));
        instanceState.putString(String.valueOf(buffer_note_two), String.valueOf(currentNoteTwo.getText()));
        instanceState.putString(String.valueOf(buffer_note_three), String.valueOf(currentNoteThree.getText()));

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState){
        super.onRestoreInstanceState(instanceState);
        currentNoteOne.setText(instanceState.getString(String.valueOf(buffer_note_one)));
        currentNoteTwo.setText(instanceState.getString(String.valueOf(buffer_note_two)));
        currentNoteThree.setText(instanceState.getString(String.valueOf(buffer_note_three)));

    }

}

