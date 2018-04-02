package edu.augsburg.calfit;

//import android.support.v4.app.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class GroceryFragment extends Fragment implements View.OnClickListener{
    View myView;
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    private EditText input;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.grocery_layout, container, false);

        super.onCreate(savedInstanceState);
        // ADD HERE
        lvItems = (ListView) myView.findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        items.add("First Item");
        items.add("Second Item");


        return myView;
    }

    @Override
    public void onClick(View myView){

        input = (EditText)myView.findViewById(R.id.etNewItem);
        Button add = (Button)myView.findViewById(R.id.btnAddItem);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        items.add(input.getText().toString());
        itemsAdapter.notifyDataSetChanged();


    }
     /*
    @Override
    public void onClick(View myView) {
        EditText etNewItem = (EditText) myView.findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");
        items.add(itemText);
    }
    */


}

