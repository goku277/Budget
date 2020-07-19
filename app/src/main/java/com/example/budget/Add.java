package com.example.budget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity {

    private EditText items, price;
    private Button click, view;
    private MediaPlayer mp;
    private Check_If_Number checkIfNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mp= MediaPlayer.create(getApplicationContext(), R.raw.button_click);

        items= (EditText) findViewById(R.id.item_Id);
        price= (EditText) findViewById(R.id.price_id);

        click= (Button) findViewById(R.id.btn_saveId);
        view= (Button) findViewById(R.id.btn_viewId);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (items.getText().toString().trim().length() == 0 || price.getText().toString().trim().length() == 0)
                        Toast.makeText(Add.this, "Fill all necessary details!", Toast.LENGTH_SHORT).show();
                else {
                    checkIfNumber= new Check_If_Number();
                    if (!checkIfNumber.check_if_Numbers(price.getText().toString().trim()))
                        Toast.makeText(Add.this, "Please input only numerical digits!", Toast.LENGTH_SHORT).show();
                    else {
                        mp.start();
                        MyDatabaseHelper myDatabase = new MyDatabaseHelper(Add.this);
                        String Hashcode = String.valueOf(items.getText().toString().hashCode());
                        System.out.println("From Add class Hashcode:" + " " + Hashcode);
                        myDatabase.add(items.getText().toString().trim(), price.getText().toString().trim(), Hashcode);
                    }
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                try {
                    new Thread().sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent= new Intent(getApplicationContext(), AddItems.class);
                startActivity(intent);
            }
        });


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
}