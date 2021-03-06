package edu.augsburg.calfit;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DashboardFragment extends Fragment {

    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
    ArrayList<ActivityItem> activityList;
    View myView;
    Double totalCalorie, burntCalorie, percentage;
    Double maxCalorie = 1200.0;
    Double goalCalorie = 400.0;
    Calendar dt;
    boolean checked;
    ImageView waterToggle1, waterToggle2, waterToggle3, waterToggle4, waterToggle5, waterToggle6, waterToggle7;

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
        activityList = new ArrayList<>();
        checked = true;

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

        ActivityItem banana = new ActivityItem("Snack", R.drawable.food_blue, "Ate banana", 105.0);
        ActivityItem lasagna = new ActivityItem("Lunch", R.drawable.food_blue, "Ate lasagna at Pizza Luce", 408.0);

        activityList.add(banana);
        activityList.add(lasagna);

        ToggleButton dashboard_togglebutton = (ToggleButton) myView.findViewById(R.id.dashboard_toggleButton);
        dashboard_togglebutton.setText("");
        dashboard_togglebutton.setTextOff("");
        dashboard_togglebutton.setTextOn("");
        dashboard_togglebutton.setChecked(true);
        updateInfo();
        dashboard_togglebutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checked = true;
                    updateInfo();
                } else {
                    checked = false;
                    updateInfo();
                }
            }
        });

        waterToggle1 = myView.findViewById(R.id.dashboard_water_toggle_1);
        waterToggle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterToggle1.setActivated(!waterToggle1.isActivated());
            }
        });

        waterToggle2 = myView.findViewById(R.id.dashboard_water_toggle_2);
        waterToggle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterToggle2.setActivated(!waterToggle2.isActivated());
            }
        });

        waterToggle3 = myView.findViewById(R.id.dashboard_water_toggle_3);
        waterToggle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterToggle3.setActivated(!waterToggle3.isActivated());
            }
        });

        waterToggle4 = myView.findViewById(R.id.dashboard_water_toggle_4);
        waterToggle4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterToggle4.setActivated(!waterToggle4.isActivated());
            }
        });

        waterToggle5 = myView.findViewById(R.id.dashboard_water_toggle_5);
        waterToggle5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterToggle5.setActivated(!waterToggle5.isActivated());
            }
        });

        waterToggle6 = myView.findViewById(R.id.dashboard_water_toggle_6);
        waterToggle6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterToggle6.setActivated(!waterToggle6.isActivated());
            }
        });

        waterToggle7 = myView.findViewById(R.id.dashboard_water_toggle_7);
        waterToggle7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterToggle7.setActivated(!waterToggle7.isActivated());
            }
        });

        final FloatingActionButton addExercise = myView.findViewById(R.id.dashboard_addAction_exercise);
        addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityItem running = new ActivityItem("Exercise", R.drawable.fire_orange, "Went running for 30 minutes", -146.0);
                activityList.add(running);
                updateInfo();
            }
        });

        final FloatingActionButton addFood = myView.findViewById(R.id.dashboard_addAction_food);
        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityItem beer = new ActivityItem("Beer", R.drawable.food_blue, "Had a can of beer", 154.0);
                activityList.add(beer);
                updateInfo();
            }
        });



        return myView;
    }

    private void updateTime(Calendar dt) {
        Date date = dt.getTime();
        Button currentDate = myView.findViewById(R.id.currentDateButton);
        currentDate.setText(dateFormat.format(date));
    }

    private void updateInfo() {
        totalCalorie = 0.0; burntCalorie = 0.0;
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (ActivityItem fI : activityList) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", fI.getName());
            hm.put("listview_description", fI.getDescription());
            hm.put("listview_image", Integer.toString(fI.getImage()));
            hm.put("listview_calorie", fI.toString());
            aList.add(hm);
            if (fI.getCalorie() > 0) {
                totalCalorie += fI.getCalorie();
            } else {
                burntCalorie -= fI.getCalorie();
            }
        }

        String[] from = {"listview_image", "listview_title", "listview_description", "listview_calorie"};
        int[] to = {R.id.dashboard_listview_image, R.id.dashboard_listview_item_title, R.id.dashboard_listview_item_description, R.id.dashboard_listview_item_calorie};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), aList, R.layout.dashboard_listview_activity, from, to);
        ListView dashboardListView = (ListView) myView.findViewById(R.id.dashboard_listview);
        dashboardListView.setAdapter(simpleAdapter);

        CircularProgressBar circularProgressBar = (CircularProgressBar) myView.findViewById(R.id.dashboard_circularProgressBar);
        circularProgressBar.setColor(getResources().getColor(R.color.progressBarColor));
        circularProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.progressBarWidth));
        circularProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundProgressBarWidth));
        int animationDuration = 1500; // 2500ms = 2,5s

        if (checked == true) {
            percentage = totalCalorie * 100 / maxCalorie;

            circularProgressBar.setBackgroundColor(getResources().getColor(R.color.backgroundProgressBarColor));
            circularProgressBar.setProgressWithAnimation(percentage.floatValue(), animationDuration); // Default duration = 1500ms

            LinearLayout linearLayout = myView.findViewById(R.id.currentDashboardStatus);
            linearLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

            TextView cal = myView.findViewById(R.id.dashboard_calorie);
            Double difference = maxCalorie - totalCalorie;
            cal.setText(Integer.toString(difference.intValue()));

            TextView calLeftOver = myView.findViewById(R.id.dashboard_calorie_leftover);
            calLeftOver.setText("of " + Integer.toString(maxCalorie.intValue()));

            TextView calLeft = myView.findViewById(R.id.dashboard_calorie_left);
            calLeft.setAlpha(1f);
            calLeft.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

            TextView calUsed = myView.findViewById(R.id.dashboard_calorie_used);
            calUsed.setAlpha(0.3f);
            calUsed.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

            ToggleButton toggleButton = myView.findViewById(R.id.dashboard_toggleButton);
            toggleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else {
            percentage = burntCalorie * 100 / goalCalorie;

            circularProgressBar.setBackgroundColor(getResources().getColor(R.color.backgroundProgressBarColorAlt));
            circularProgressBar.setProgressWithAnimation(percentage.floatValue(), animationDuration); // Default duration = 1500ms

            LinearLayout linearLayout = myView.findViewById(R.id.currentDashboardStatus);
            linearLayout.setBackgroundColor(getResources().getColor(R.color.dashboard_foodColor));

            TextView cal = myView.findViewById(R.id.dashboard_calorie);
            Double difference = goalCalorie - burntCalorie;
            cal.setText(Integer.toString(difference.intValue()));

            TextView calLeftOver = myView.findViewById(R.id.dashboard_calorie_leftover);
            calLeftOver.setText("of " + Integer.toString(goalCalorie.intValue()));

            TextView calLeft = myView.findViewById(R.id.dashboard_calorie_left);
            calLeft.setAlpha(0.3f);
            calLeft.setBackgroundColor(getResources().getColor(R.color.dashboard_foodColor));

            TextView calUsed = myView.findViewById(R.id.dashboard_calorie_used);
            calUsed.setAlpha(1f);
            calUsed.setBackgroundColor(getResources().getColor(R.color.dashboard_foodColor));

            ToggleButton toggleButton = myView.findViewById(R.id.dashboard_toggleButton);
            toggleButton.setBackgroundColor(getResources().getColor(R.color.dashboard_foodColor));
        }


    }

}
