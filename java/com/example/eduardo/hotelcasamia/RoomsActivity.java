package com.example.eduardo.hotelcasamia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RoomsActivity extends AppCompatActivity {

    /* Declare this */
    ListView listDetailsSimpleRoom;
    ListView listDetailsDoubleRoom;
    ListView listDetailsBiggerRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        /* Simple Room Details */
        listDetailsSimpleRoom = (ListView) findViewById(R.id.list_simple_room);
        ArrayAdapter<CharSequence> adapterSimple = ArrayAdapter.createFromResource(this,R.array.array_details_simple_room,
                android.R.layout.simple_list_item_1);
        listDetailsSimpleRoom.setAdapter(adapterSimple);

        /* Double Room Details */
        listDetailsDoubleRoom = (ListView) findViewById(R.id.list_double_room);
        ArrayAdapter<CharSequence> adapterDouble = ArrayAdapter.createFromResource(this,R.array.array_double_room_details,
                android.R.layout.simple_list_item_1);
        listDetailsDoubleRoom.setAdapter(adapterDouble);

        /* Bigger Room Details */
        listDetailsBiggerRoom = (ListView) findViewById(R.id.list_bigger_room);
        ArrayAdapter<CharSequence> adapterBigger = ArrayAdapter.createFromResource(this,R.array.array_bigger_room_detail,
                android.R.layout.simple_list_item_1);
        listDetailsBiggerRoom.setAdapter(adapterBigger);
    }
}/* End */
