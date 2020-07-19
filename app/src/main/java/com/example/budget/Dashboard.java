package com.example.budget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    private TextView add, budget, optimize;
    private ImageView addImg, budgetImg, optimizeImg;
    private ImageView sound, no_sound;
    private MediaPlayer mp, mp_tuturu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        add= (TextView) findViewById(R.id.add_textview_Id);
        budget= (TextView) findViewById(R.id.budget_textview_setId);
        optimize= (TextView) findViewById(R.id.optimize_textview_Id);

        mp= MediaPlayer.create(getApplicationContext(), R.raw.swordland);
        mp_tuturu= MediaPlayer.create(getApplicationContext(), R.raw.tuturu_1);

        addImg= (ImageView) findViewById(R.id.add_img_Id);
        budgetImg= (ImageView) findViewById(R.id.budget_img__setId);
        optimizeImg= (ImageView) findViewById(R.id.optimize_img_Id);
        sound= (ImageView) findViewById(R.id.sound_id);
        no_sound= (ImageView) findViewById(R.id.no_sound_Id);

        no_sound.setOnClickListener(this);
        sound.setOnClickListener(this);

        add.setOnClickListener(this);
        budget.setOnClickListener(this);
        optimize.setOnClickListener(this);

        addImg.setOnClickListener(this);
        budgetImg.setOnClickListener(this);
        optimizeImg.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_textview_Id:
                mp_tuturu.start();
                try {
                    new Thread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(getApplicationContext(), Add.class));
                finish();
                break;
            case R.id.budget_textview_setId:
                mp_tuturu.start();
                try {
                    new Thread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(getApplicationContext(), Budget_create.class));
                finish();
                break;
            case R.id.optimize_textview_Id:
                mp_tuturu.start();
                try {
                    new Thread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(getApplicationContext(), Optimizer.class));
                finish();
                break;
            case R.id.add_img_Id:
                mp_tuturu.start();
                try {
                    new Thread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(getApplicationContext(), Add.class));
                finish();
                break;
            case R.id.budget_img__setId:
                mp_tuturu.start();
                try {
                    new Thread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(getApplicationContext(), Budget_create.class));
                finish();
                break;
            case R.id.optimize_img_Id:
                mp_tuturu.start();
                try {
                    new Thread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(getApplicationContext(), Optimizer.class));
                finish();
                break;
            case R.id.sound_id:
                sound.setVisibility(View.INVISIBLE);
                no_sound.setVisibility(View.VISIBLE);
                mp.pause();
                break;
            case R.id.no_sound_Id:
                no_sound.setVisibility(View.INVISIBLE);
                sound.setVisibility(View.VISIBLE);
                mp.start();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_power1:
                  //  finish();
                   finishAffinity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
