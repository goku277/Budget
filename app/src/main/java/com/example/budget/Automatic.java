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
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class Automatic extends AppCompatActivity {

    private ImageView imageView;
    private TextView info;
    private int progress = 0, counter = 100, progress1=0;
    private static MyDatabaseHelper db;
    private BudgetDatabaseHelper db1;
    private static Map<String, String> map1 = new HashMap<>();
    private ArrayList<Long> list1 = new ArrayList<>();
    private static ArrayList<Integer> list5= new ArrayList<Integer>();
    private static boolean isMapped= false;
    private boolean isBudgetMaintaned= true;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automatic);

        db = new MyDatabaseHelper(getApplicationContext());
        db1 = new BudgetDatabaseHelper(getApplicationContext());

        info= (TextView) findViewById(R.id.info_budget_status_Id);

        mp= MediaPlayer.create(getApplicationContext(), R.raw.machine_rotating_sound);

        imageView = (ImageView) findViewById(R.id.bolt_Id);

        Cursor data= db.readAllData();
        Cursor cursor= db1.readAllData();
        String dataItems="";
        long price=0, total=0;
        if (data.getCount() > 0) {
            while (data.moveToNext()) {
                dataItems= data.getString(1);
                price= Long.valueOf(data.getString(2));
                map1.put(dataItems, data.getString(2));
                list1.add(price);
            }
        }
        else Toast.makeText(this, "Your database is empty!", Toast.LENGTH_SHORT).show();
        for (long l1: list1) total+= l1;
        cursor.moveToLast();
        long budget= Long.valueOf(cursor.getString(1));
        if (total >= budget) {
            isBudgetMaintaned= false;
            do_knapsack_and_subset_sum(budget);
        }
        else Toast.makeText(this, "Budget is maintaned!", Toast.LENGTH_SHORT).show();

        if (!isBudgetMaintaned) {

            RotateAnimation rotate = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(400);
            rotate.setInterpolator(new LinearInterpolator());

            rotate.setRepeatCount(Animation.INFINITE);

            ImageView image = (ImageView) findViewById(R.id.bolt_Id);
            mp.start();
            image.startAnimation(rotate);

            final ProgressBar simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar); // initiate the progress bar
            simpleProgressBar.setMax(100); // 100 maximum value for the progress value
            final Timer t = new Timer();
            TimerTask tt = new TimerTask() {
                @Override
                public void run() {
                    progress++;
                    simpleProgressBar.setProgress(progress);
                    if (progress == 100) {
                        t.cancel();
                        isBudgetMaintaned= true;
                        startActivity(new Intent(getApplicationContext(), AddItems.class));
                    }
                }
            };
            t.schedule(tt, 0, 100);
        }
        else {
            info.setText("Hurray! your budget plan is stable!");
        }
    }

    private void do_knapsack_and_subset_sum(long capacity) {
        int i1=0;
        long cost[]= new long[list1.size()];
        for (long l1: list1) cost[i1++]=l1;
        long max= Collections.min(list1);
        long alarm_range= (3 * max);
        capacity-=alarm_range;
        if (capacity < 0) capacity+=alarm_range;
        long sum = knapsack(cost, cost.length, capacity, map1);

        System.out.println(sum);

        printAllSubsets(cost, cost.length, sum);
    }

     void printAllSubsetsRec(long arr[], int n, Vector<Integer> v,
                                   long sum)
    {
        // If remaining sum is 0, then print all
        // elements of current subset.
        if (sum == 0 && !isMapped) {
            list5.addAll(v);
            do_mapping(list5);
            isMapped= true;
            for (int i=0;i<v.size();i++) {
                System.out.print(v.get(i) + " ");
            }
            System.out.println("");

            return;
        }

        // If no remaining elements,
        if (n == 0)
            return;

        // We consider two cases for every element.
        // a) We do not include last element.
        // b) We include last element in current subset.
        printAllSubsetsRec(arr, n - 1, v, sum);
        Vector<Integer> v1=new Vector<Integer>(v);
        v1.add((int) arr[n - 1]);
        printAllSubsetsRec(arr, n - 1, v1, (int) (sum - arr[n - 1]));
    }

    private void do_mapping(ArrayList<Integer> list5) {
        for (int i: list5) System.out.print(i + " ");
        Set<Integer> set1= new HashSet<>();
        set1.addAll(list5);
        list5.clear();
        list5.addAll(set1);
        Cursor data= db.readAllData();
        ArrayList<Integer> list7= new ArrayList<>();
        if (data.getCount() > 0) {
            while (data.moveToNext()) {
                list7.add(Integer.valueOf(data.getString(2)));
            }
        }
        list7.removeAll(list5);
        System.out.println(list7);
        for (int i: list7) {
            db.deleteTitle_1(String.valueOf(i));
        }

        final Timer t1= new Timer();
        TimerTask tt1= new TimerTask() {
            @Override
            public void run() {
                progress1++;
                if (progress1==100) t1.cancel();
            }
        };
        t1.schedule(tt1, 5, 200);

        Toast.makeText(this, "App is optimizing the data!", Toast.LENGTH_SHORT).show();
    }

    // Wrapper over printAllSubsetsRec()
     void printAllSubsets(long arr[], int n, long sum)
    {
        Vector<Integer> v= new Vector<Integer>();
        printAllSubsetsRec(arr, n, v, sum);
    }


    private long knapsack(long[] cost, int no, long capacity, Map<String, String> map1) {

            long matrix[][] = new long[no + 1][(int) capacity + 1];
        try {
            ArrayList<Integer> list1 = new ArrayList<>();
            for (int i = 0; i <= capacity; i++) matrix[0][i] = 0;
            for (int i = 0; i <= no; i++) matrix[i][0] = 0;
            for (int i = 1; i <= no; i++) {
                for (int i1 = 1; i1 <= capacity; i1++) {
                    if (cost[i - 1] <= i1) {
                        matrix[i][i1] = Math.max(cost[i - 1] + matrix[i - 1][(int) (i1 - cost[i - 1])], matrix[i - 1][i1]);
                    } else matrix[i][i1] = matrix[i - 1][i1];
                }
            }
            //  System.out.println(list1 + " ");

        }
        catch (Exception e){}

        return matrix[no][(int) capacity];
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu_11, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== R.id.navigation_back_1) {
            startActivity(new Intent(getApplicationContext(), Dashboard.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}