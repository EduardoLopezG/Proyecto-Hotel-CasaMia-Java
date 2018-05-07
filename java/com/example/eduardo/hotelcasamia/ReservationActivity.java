package com.example.eduardo.hotelcasamia;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.UUID;

public class ReservationActivity extends AppCompatActivity implements View.OnClickListener{

    /* Declare this */
    private Button btnDateIn;
    private Button btnDateOut;
    private Button btnMakeReservation;
    private EditText edtName;
    private EditText edtPhoneNumber;
    private TextView txvDateIn;
    private TextView txvDateOut;
    private int year,month,day;
    Spinner spiNumberChildren;
    Spinner spiAdultsNumber;
    Spinner spiRoomType;

    /* Declare this Firebase database items */
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        /* Initializig the objects */
        btnDateIn = (Button) findViewById(R.id.idbtnDateIn);
        btnDateOut = (Button) findViewById(R.id.idbtnDateOut);
        btnMakeReservation = (Button) findViewById(R.id.idbtnMakeReservation);
        edtName = (EditText) findViewById(R.id.idEdtName);
        edtPhoneNumber = (EditText) findViewById(R.id.idEdtPhoneNumber);
        txvDateIn = (TextView) findViewById(R.id.idTvDateIn);
        txvDateOut = (TextView) findViewById(R.id.idTvDateOut);
        /* Childre Number Spinner */
        spiNumberChildren = (Spinner) findViewById(R.id.idSpnChildrenNumber);
        ArrayAdapter<CharSequence> adapterSpnChildren = ArrayAdapter.createFromResource(this,
                R.array.arrayNumberChildren, android.R.layout.simple_spinner_item);
        spiNumberChildren.setAdapter(adapterSpnChildren);
        /* Adults Number Spinner */
        spiAdultsNumber = (Spinner) findViewById(R.id.idSpnAdultsNumber);
        ArrayAdapter<CharSequence> adapterSpnAdults = ArrayAdapter.createFromResource(this,
                R.array.arrayAdultsNumber, android.R.layout.simple_spinner_item);
        spiAdultsNumber.setAdapter(adapterSpnAdults);
        /* Room Type Spinner */
        spiRoomType = (Spinner) findViewById(R.id.idSpnRoomType);
        ArrayAdapter<CharSequence> adapterRoomType = ArrayAdapter.createFromResource(this,
                R.array.arrayRoomType, android.R.layout.simple_spinner_item);
        spiRoomType.setAdapter(adapterRoomType);

        /* This to the Buttons */
        btnDateIn.setOnClickListener(this);
        btnDateOut.setOnClickListener(this);
        btnMakeReservation.setOnClickListener(this);

        /* Call the method */
        initializeFirebase();
    }

    /* Method to the reservation */
    private void initializeFirebase() {
        FirebaseApp.initializeApp(ReservationActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        /* DateIn Calendar */
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

        /* DateOut Calendar */
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

        /* Make Reservation Button */
        if(view==btnMakeReservation){
            /* Call the Method */
            makeReservations();//<-------
        }
    }

    public void makeReservations(){
        /* Obtain data */
        String name = edtName.getText().toString();
        String phone = edtPhoneNumber.getText().toString();
        String dateIn = txvDateIn.getText().toString();
        String dateOut = txvDateOut.getText().toString();

        /* Verification to empty places */
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, R.string.emptyNameMessage,Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, R.string.emptyPhoneNumberMessage,Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(dateIn)){
            Toast.makeText(this, R.string.emptyDateInMessage,Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(dateOut)){
            Toast.makeText(this, R.string.emptyDateOutMessage,Toast.LENGTH_LONG).show();
            return;
        }
        if(spiNumberChildren.getSelectedItem().equals(getString(R.string.default_value_spinner))){
            Toast.makeText(this,R.string.selectChildrenNumberMessage,Toast.LENGTH_LONG).show();
            return;
        }
        if(spiAdultsNumber.getSelectedItem().equals(getString(R.string.default_value_spinner))){
            Toast.makeText(this,R.string.selectAdultsNumberMessage,Toast.LENGTH_LONG).show();
            return;
        }
        if(spiRoomType.getSelectedItem().equals(getString(R.string.default_value_spinner))){
            Toast.makeText(this, R.string.userEmptyRoomTypeMessage,Toast.LENGTH_LONG).show();
            return;
        }

        /* Making Reservation.. */
        Reservation reservation = new Reservation();
        reservation.setUid(UUID.randomUUID().toString());
        reservation.setName(edtName.getText().toString());
        reservation.setPhone(edtPhoneNumber.getText().toString().trim());
        reservation.setDateIn(txvDateIn.getText().toString());
        reservation.setDateOut(txvDateOut.getText().toString());
        reservation.setChildrenNumber(spiNumberChildren.getSelectedItem().toString());
        reservation.setAdultsNumber(spiAdultsNumber.getSelectedItem().toString());
        reservation.setRoomType(spiRoomType.getSelectedItem().toString());
        databaseReference.child("Reservaciones").child(reservation.getUid()).setValue(reservation);
        Toast.makeText(ReservationActivity.this, R.string.resgistredReservationMessage,Toast.LENGTH_LONG).show();
        finish();
    }
}/* End */
