package edu.augsburg.calfit;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DashboardFragment extends Fragment {

    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
    ArrayList<FoodItem> foodList;
    View myView;
    double totalFat, totalSugar, totalCalorie;
    double maxCalorie = 1200.0;
    Calendar dt;

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dt.set(Calendar.YEAR, year);
            dt.set(Calendar.MONTH, month);
            dt.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateTime(dt);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.dashboard_layout, container, false);
        foodList = new ArrayList<>();

        dt = Calendar.getInstance();
        updateTime(dt);

        ImageButton decreaseDate = (ImageButton) myView.findViewById(R.id.decreaseDate);
        decreaseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dt.add(Calendar.DATE, -1);
                updateTime(dt);
            }
        });

        ImageButton increaseDate = (ImageButton) myView.findViewById(R.id.increaseDate);
        increaseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dt.add(Calendar.DATE, 1);
                updateTime(dt);
            }
        });

        Button currentDate = (Button) myView.findViewById(R.id.currentDateButton);
        currentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date, dt.get(Calendar.YEAR), dt.get(Calendar.MONTH), dt.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        FoodItem banana = new FoodItem("Banana", R.drawable.banana);
        banana.setCalorie(105.0);
        banana.setFat(0.4);
        banana.setSugar(14.3);

        FoodItem lasagna = new FoodItem("Lasagna", R.drawable.lasagna);
        lasagna.setCalorie(408.0);
        lasagna.setFat(22.1);
        lasagna.setSugar(5.0);

        foodList.add(banana);
        foodList.add(lasagna);


        updateInfo();

        ListView dashboardListView = (ListView) myView.findViewById(R.id.dashboard_listview);
        TextView textView = new TextView(getActivity());
        textView.setText("   Current Consumption");
        dashboardListView.addHeaderView(textView);

        FloatingActionButton dashboard_fab = (FloatingActionButton) myView.findViewById(R.id.dashboard_floating_button);
        dashboard_fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FoodItem beer = new FoodItem("Beer", R.drawable.beer);
                beer.setCalorie(154.0);
                beer.setFat(0.0);
                beer.setSugar(0.0);

                foodList.add(beer);
                updateInfo();
            }
        });

        return myView;
    }

    private void updateTime(Calendar dt) {
        Date date = dt.getTime();
        Button currentDate = (Button) myView.findViewById(R.id.currentDateButton);
        currentDate.setText(dateFormat.format(date));
    }

    private void updateInfo() {
        totalFat = 0; totalSugar = 0; totalCalorie = 0;
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (FoodItem fI : foodList) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", fI.getName());
            hm.put("listview_description", fI.generateDescription());
            hm.put("listview_image", Integer.toString(fI.getImage()));
            aList.add(hm);
            totalCalorie += fI.getCalorie();
            totalFat += fI.getFat();
            totalSugar += fI.getSugar();
        }

        String[] from = {"listview_image", "listview_title", "listview_description"};
        int[] to = {R.id.dashboard_listview_image, R.id.dashboard_listview_item_title, R.id.dashboard_listview_item_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), aList, R.layout.dashboard_listview_activity, from, to);
        ListView dashboardListView = (ListView) myView.findViewById(R.id.dashboard_listview);
        dashboardListView.setAdapter(simpleAdapter);

        TextView cal = (TextView) myView.findViewById(R.id.dashboard_calorie);
        cal.setText(Double.toString(maxCalorie-totalCalorie));

        TextView summ = (TextView) myView.findViewById(R.id.dashboard_summary);

        summ.setText("Daily goal of " + maxCalorie + " - " + totalCalorie + " (food)");
    }

}
