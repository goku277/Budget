package com.example.budget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Budget_create extends AppCompatActivity {

    private EditText set;
    private Button click, delete, view;
    private BudgetDatabaseHelper budgetDatabaseHelper;
    private TextView displayBudget;
    private MediaPlayer mp;
    private Check_If_Number checkIfNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_create);

        mp= MediaPlayer.create(getApplicationContext(), R.raw.button_click);

        set= (EditText) findViewById(R.id.set_budget_Id);
        click= (Button) findViewById(R.id.btn_Activate);
        view= (Button) findViewById(R.id.btn_display);
        displayBudget= (TextView) findViewById(R.id.display_budget_Id);

        budgetDatabaseHelper= new BudgetDatabaseHelper(getApplicationContext());

        checkIfNumber= new Check_If_Number();

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!checkIfNumber.check_if_Numbers(set.getText().toString().trim()))
                    Toast.makeText(Budget_create.this, "Please input numerical digits only!", Toast.LENGTH_SHORT).show();

                else {

                    String input = set.getText().toString().trim();
                    Cursor data = budgetDatabaseHelper.readAllData();
                    if (!input.isEmpty()) {
                        mp.start();
                        budgetDatabaseHelper.add(input);
                    } else
                        Toast.makeText(Budget_create.this, "Fill required details!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor data= budgetDatabaseHelper.readAllData();
                data.moveToLast();
                if (data.getCount() > 0) {
                     String budgett= data.getString(1);
                     mp.start();
                    try {
                        new Thread().sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    displayBudget.setText(budgett);
                }
                else Toast.makeText(Budget_create.this, "No budget to display! database is empty!", Toast.LENGTH_SHORT).show();
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