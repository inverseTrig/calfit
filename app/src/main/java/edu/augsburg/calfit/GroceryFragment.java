package edu.augsburg.calfit;

//import android.support.v4.app.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class GroceryFragment extends Fragment{
    View myView;
    private ArrayList<String> itemsList;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    private EditText input;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.grocery_layout, container, false);

        super.onCreate(savedInstanceState);
        // Varaiables and stuff
        lvItems = (ListView) myView.findViewById(R.id.lvItems);
        itemsList = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, itemsList);
        lvItems.setAdapter(itemsAdapter);
        //Deafult To This, an example of canned data
        itemsList.add("Rice");
        itemsList.add("Ground Chicken");

        input = (EditText)myView.findViewById(R.id.etNewItem);
        Button add = (Button)myView.findViewById(R.id.btnAddItem);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsList.add(input.getText().toString());
                itemsAdapter.notifyDataSetChanged();
            }
        });
        /*
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                SparseBooleanArray pos = lvItems.getCheckedItemPositions(); //posistion of cursor

                int count  =  lvItems.getCount();

                for(int item = count-1; item>=0; item--){

                    if(pos.get(item)){
                        itemsAdapter.remove(itemsList.get(item));
                    }
                }

                pos.clear();
                itemsAdapter.notifyDataSetChanged();
                return false;
            }
        });
        */
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemsList.remove(position);
                itemsAdapter.notifyDataSetChanged();
            }
        });


        return myView;
    }



}

