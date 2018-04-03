package edu.augsburg.calfit;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class RecipesFragment extends Fragment {

    View myView;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
    Calendar dt;
    ArrayList<ActivityItem> activityList;

    String[] items;
    ArrayList<String> listitems;
    ArrayAdapter adapter;
    ListView listView;
    EditText editText;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        activityList = new ArrayList<ActivityItem>();
        //dt = Calendar.getInstance();
        //updateTime(dt);
        myView = inflater.inflate(R.layout.recipes_layout, container, false);




        final FloatingActionButton addChicken = myView.findViewById(R.id.addChicken);
        addChicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 ActivityItem chicken = new ActivityItem("GrilledChicken", R.drawable.food_blue, "HomeMadeGrilledChicken", 378.0);
                activityList.add(chicken);
                updateInfo();

            }
        });

        final FloatingActionButton addRecipe = myView.findViewById(R.id.addRecipe);
        addRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityItem running = new ActivityItem("Custom", R.drawable.food_white, "Recipe User Enters", -0);
                activityList.add(running);
                updateInfo();
            }
        });


        updateInfo();

        listView = (ListView)myView.findViewById(R.id.listview);
        editText = (EditText)myView.findViewById(R.id.txtsearch1);

        initList();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals(" ")){
                    initList();
                }else{
                    searhItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return myView;
    }

    public void searhItem(String textToSearch){
        for(String item:items){
            if(!item.contains(textToSearch)){
                listitems.remove(item);
            }
            adapter.notifyDataSetChanged();
        }
    }


    public void initList(){
        items = new String [] {"Chick Tikka","Ramen Noodles","Fish Fingers","Beef Stew","Chilli Chicken"};
        listitems = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item,R.id.txtitem,listitems);
        listView.setAdapter(adapter);
    }


    private void updateInfo() {

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (ActivityItem fI : activityList) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", fI.getName());
            hm.put("listview_description", fI.getDescription());
            hm.put("listview_image", Integer.toString(fI.getImage()));
            hm.put("listview_calorie", fI.toString());
            aList.add(hm);
        }

        int[] to = {R.id.recipe_listview_image, R.id.recipe_listview_item_title, R.id.recipe_listview_item_description, R.id.recipe_listview_item_calorie};
        String[] from = {"listview_image", "listview_title", "listview_description", "listview_calorie"};
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), aList, R.layout.recipe_listview_activity, from, to);
        ListView recipeListView = (ListView) myView.findViewById(R.id.recipe_listview);

        recipeListView.setAdapter(simpleAdapter);

    }
}
