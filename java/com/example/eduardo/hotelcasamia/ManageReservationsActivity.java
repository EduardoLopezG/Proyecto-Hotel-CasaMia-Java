package com.example.eduardo.hotelcasamia;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ManageReservationsActivity extends AppCompatActivity implements View.OnClickListener{

    /* Declare this */
    private Button btnDateIn;
    private Button btnDateOut;
    private Button btnUpdateReservation;
    private Button btnDeleteReservation;
    private EditText edtName;
    private EditText edtPhoneNumber;
    private TextView txvDateIn;
    private TextView txvDateOut;
    private int year,month,day;
    Spinner spiNumberChildren;
    Spinner spiAdultsNumber;
    Spinner spiRoomType;

    /* Reservations List */
    ListView listReservations;
    /* Declare this Firebase database items */
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private List<Reservation> listReservation = new ArrayList<Reservation>();
    private ArrayAdapter<Reservation> arrayAdapterReservation;

    Reservation reservationSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_reservations);

        /* Initializaing objects */
        btnDateIn = (Button) findViewById(R.id.admin_btn_date_in);
        btnDateOut = (Button) findViewById(R.id.admin_btn_date_out);
        btnUpdateReservation = (Button) findViewById(R.id.admin_btn_update_reservation);
        btnDeleteReservation = (Button) findViewById(R.id.admin_btn_delete_reservation);
        edtName = (EditText) findViewById(R.id.admin_edt_name);
        edtPhoneNumber = (EditText) findViewById(R.id.admin_edt_phone_number);
        txvDateIn = (TextView) findViewById(R.id.admin_Txv_date_in);
        txvDateOut = (TextView) findViewById(R.id.admin_Txv_date_out);
        /* Childre Number Spinner */
        spiNumberChildren = (Spinner) findViewById(R.id.admin_spi_children_number);
        ArrayAdapter<CharSequence> adapterSpnChildren = ArrayAdapter.createFromResource(this,
                R.array.arrayNumberChildren, android.R.layout.simple_spinner_item);
        spiNumberChildren.setAdapter(adapterSpnChildren);
        /* Adults Number Spinner */
        spiAdultsNumber = (Spinner) findViewById(R.id.admin_spi_adults_number);
        ArrayAdapter<CharSequence> adapterSpnAdults = ArrayAdapter.createFromResource(this,
                R.array.arrayAdultsNumber, android.R.layout.simple_spinner_item);
        spiAdultsNumber.setAdapter(adapterSpnAdults);
        /* Room Type Spinner */
        spiRoomType = (Spinner) findViewById(R.id.admin_spi_rooms_type);
        ArrayAdapter<CharSequence> adapterRoomType = ArrayAdapter.createFromResource(this,
                R.array.arrayRoomType, android.R.layout.simple_spinner_item);
        spiRoomType.setAdapter(adapterRoomType);

        /* ListView Resrvations */
        listReservations = (ListView) findViewById(R.id.list_reservations);

        /* Call the methods */
        initializeFirebase();
        eventsDataBase();

        /* This to complete the places in the form with the selected item information */
        listReservations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                reservationSelected = (Reservation)adapterView.getItemAtPosition(i);
                edtName.setText(reservationSelected.getName());
                edtPhoneNumber.setText(reservationSelected.getPhone());
                txvDateIn.setText(reservationSelected.getDateIn());
                txvDateOut.setText(reservationSelected.getDateOut());
                /* Spinners */
                spiNumberChildren.setSelected(Boolean.parseBoolean(reservationSelected.getChildrenNumber()));
                spiAdultsNumber.setSelected(Boolean.parseBoolean(reservationSelected.getAdultsNumber()));
                spiRoomType.setSelected(Boolean.parseBoolean(reservationSelected.getRoomType()));
            }
        });

        /* This to the Buttons */
        btnDateIn.setOnClickListener(this);
        btnDateOut.setOnClickListener(this);
        btnUpdateReservation.setOnClickListener(this);
        btnDeleteReservation.setOnClickListener(this);
    }/* End onCreate */

    /* Create this method - To the List -*/
    private void eventsDataBase() {
        databaseReference.child("Reservaciones").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listReservation.clear();
                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Reservation reservation = objSnapshot.getValue(Reservation.class);
                    listReservation.add(reservation);
                }
                arrayAdapterReservation = new ArrayAdapter<Reservation>(ManageReservationsActivity.this,
                        android.R.layout.simple_list_item_1, listReservation);
                listReservations.setAdapter(arrayAdapterReservation);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /* Method to initialize Firebase */
    private void initializeFirebase() {
        FirebaseApp.initializeApp(ManageReservationsActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public void onClick(View view) {
        /* To the date Buttons */
        /* DateIn Button */
        if(view==btnDateIn){
            final Calendar calendar = Calendar.getInstance();
            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);
            month = month+1;

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month+1;
                    txvDateIn.setText(day+"/"+month+"/"+year);
                }
            }
                    ,year,month,day);/* In this order to correct date */
            datePickerDialog.show();
        }

        /* DateIn Button */
        if(view==btnDateOut){
            final Calendar calendar = Calendar.getInstance();
            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);
            month = month+1;

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monh, int day) {
                    monh = monh+1;
                    txvDateOut.setText(day+"/"+monh+"/"+year);
                }
            }
                    ,year,month,day);/* In this order to correct date */
            datePickerDialog.show();
        }

        /* Update Button */
        if(view==btnUpdateReservation){
            /* Obtain data */
            String name = edtName.getText().toString();
            String phone = edtPhoneNumber.getText().toString();
            String dateIn = txvDateIn.getText().toString();
            String dateOut = txvDateOut.getText().toString();
            /* Verification to empty places */
            if(TextUtils.isEmpty(name)){
                Toast.makeText(this, R.string.selectReservationMessage,Toast.LENGTH_LONG).show();
                return;
            }
            if(TextUtils.isEmpty(phone)){
                Toast.makeText(this,R.string.selectReservationMessage,Toast.LENGTH_LONG).show();
                return;
            }
            if(TextUtils.isEmpty(dateIn)){
                Toast.makeText(this,R.string.selectReservationMessage,Toast.LENGTH_LONG).show();
                return;
            }
            if(TextUtils.isEmpty(dateOut)){
                Toast.makeText(this,R.string.selectReservationMessage,Toast.LENGTH_LONG).show();
                return;
            }
            if(spiNumberChildren.getSelectedItem().equals(getString(R.string.default_value_spinner))){
                Toast.makeText(this, R.string.selectChildrenNumberMessage,Toast.LENGTH_LONG).show();
                return;
            }
            if(spiAdultsNumber.getSelectedItem().equals(getString(R.string.default_value_spinner))){
                Toast.makeText(this, R.string.selectAdultsNumberMessage,Toast.LENGTH_LONG).show();
                return;
            }
            if(spiRoomType.getSelectedItem().equals(getString(R.string.default_value_spinner))){
                Toast.makeText(this, R.string.selectRoomTypeMessage,Toast.LENGTH_LONG).show();
                return;
            }

            /* Updating Reservation */
            Reservation reservation = new Reservation();
            reservation.setUid(reservationSelected.getUid());
            reservation.setName(edtName.getText().toString());
            reservation.setPhone(edtPhoneNumber.getText().toString().trim());
            reservation.setDateIn(txvDateIn.getText().toString());
            reservation.setDateOut(txvDateOut.getText().toString());
            reservation.setChildrenNumber(spiNumberChildren.getSelectedItem().toString());
            reservation.setAdultsNumber(spiAdultsNumber.getSelectedItem().toString());
            reservation.setRoomType(spiRoomType.getSelectedItem().toString());
            databaseReference.child("Reservaciones").child(reservation.getUid()).setValue(reservation);
            Toast.makeText(ManageReservationsActivity.this, R.string.reservationModifiquedMessage,Toast.LENGTH_LONG).show();
            finish();
        }

        /* Delete Button */
        if(view==btnDeleteReservation){
            /* Obtain data */
            String name = edtName.getText().toString();
            String phone = edtPhoneNumber.getText().toString();
            String dateIn = txvDateIn.getText().toString();
            String dateOut = txvDateOut.getText().toString();
            /* Verification to empty places */
            if(TextUtils.isEmpty(name)){
                Toast.makeText(this,R.string.selectReservationMessage,Toast.LENGTH_LONG).show();
                return;
            }
            if(TextUtils.isEmpty(phone)){
                Toast.makeText(this,R.string.selectReservationMessage,Toast.LENGTH_LONG).show();
                return;
            }
            if(TextUtils.isEmpty(dateIn)){
                Toast.makeText(this,R.string.selectReservationMessage,Toast.LENGTH_LONG).show();
                return;
            }
            if(TextUtils.isEmpty(dateOut)){
                Toast.makeText(this,R.string.selectReservationMessage,Toast.LENGTH_LONG).show();
                return;
            }

            /* Deleting Reservation */
            Reservation reservation = new Reservation();
            reservation.setUid(reservationSelected.getUid());
            databaseReference.child("Reservaciones").child(reservation.getUid()).removeValue();
            Toast.makeText(ManageReservationsActivity.this, R.string.reservationDeletedMessage,Toast.LENGTH_LONG).show();
            finish();
        }
    }/* End onClick */
}/* End */
