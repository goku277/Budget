package com.example.budget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Optimizer extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private ImageView automatic, manual;
    private TextView txtAutomatic, txtManual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimizer);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        automatic= (ImageView) findViewById(R.id.automatic_Id);
        manual= (ImageView) findViewById(R.id.manual_Id);

        txtAutomatic= (TextView) findViewById(R.id.automatic_textview_Id);
        txtManual= (TextView) findViewById(R.id.manual_textview_Id);

        automatic.setOnClickListener(this);
        manual.setOnClickListener(this);
        txtAutomatic.setOnClickListener(this);
        txtManual.setOnClickListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
                finish();
                break;
            case R.id.navigation_back:
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
                finish();
                break;
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu_11, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_back_1:
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.automatic_Id:
                startActivity(new Intent(getApplicationContext(), Automatic.class));
                finish();
                break;
            case R.id.manual_Id:
                startActivity(new Intent(getApplicationContext(), AddItems.class));
                finish();
                break;
            case R.id.automatic_textview_Id:
                startActivity(new Intent(getApplicationContext(), Automatic.class));
                finish();
                break;
            case R.id.manual_textview_Id:
                startActivity(new Intent(getApplicationContext(), AddItems.class));
                finish();
                break;
        }
    }
}