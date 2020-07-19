package com.example.budget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddItems extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, RecyclerViewClickListener  {
    private RecyclerView recyclerView;
    private FloatingActionButton fb;
    private ArrayList<String> items, price, Hashcode;
    private MyDatabaseHelper myDb;
    private CustomArrayAdapter customArrayAdapter;
    private TextView Total;
    private long sum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        fb= (FloatingActionButton) findViewById(R.id.floatingActionButton);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Add.class));
                finish();
            }
        });

        Total= (TextView) findViewById(R.id.Total_Id);

        myDb= new MyDatabaseHelper(AddItems.this);
        items= new ArrayList<>();
        price= new ArrayList<>();
        Hashcode= new ArrayList<>();

        storeDataInList();
        Total.setText("Total"+ " " + String.valueOf(sum));
        customArrayAdapter= new CustomArrayAdapter(AddItems.this, items, price, Hashcode, this);
        recyclerView.setAdapter(customArrayAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AddItems.this));

    }

    void storeDataInList() {
        Cursor cursor= myDb.readAllData();
        if (cursor.getCount()==0) Toast.makeText(this, "No data found!", Toast.LENGTH_SHORT).show();
        else {
            while (cursor.moveToNext()) {
                String item11= cursor.getString(1);
                String price11= cursor.getString(2);
                if (!price11.isEmpty()) {
                    try {
                        sum += Long.valueOf(price11);
                    } catch (Exception e) {}
                }
                items.add(item11);
                price.add(price11);
            }
        }
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
                startActivity(new Intent(getApplicationContext(), Add.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_home:
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
                finish();
                break;

            case R.id.navigation_delete_record:
                myDb.deleteAll();
                break;

            case R.id.navigation_back:
                startActivity(new Intent(getApplicationContext(), Add.class));
                finish();
                break;
        }
        return true;
    }

    @Override
    public void recyclerViewListClicked(int position) {
        Toast.makeText(this, items.get(position), Toast.LENGTH_SHORT).show();
        System.out.println("position" + " " + position);

    }

    @Override
    public void onLongClick(int position) {
        Intent intent= getIntent();
        String Hashcode= intent.getStringExtra("hashcode"), reqdHash="", hash="";
        System.out.println("Hashcode" + " " + Hashcode);
        Cursor data= myDb.readAllData();
        System.out.println("data"+ " " + data);
        int count=0;
        while (data.moveToNext()) {
            String id_generated= data.getString(0);
            if (count==position) {
                boolean isDeleted= myDb.deleteTitle(id_generated);
                if (isDeleted) Toast.makeText(this, "Data deleted successfully!", Toast.LENGTH_SHORT).show();
                else Toast.makeText(this, "Data not deleted!", Toast.LENGTH_SHORT).show();
                break;
            }
            count++;
        }
        items.remove(position);
        price.remove(position);
        customArrayAdapter.notifyItemRemoved(position);
    }
}