package com.example.eduardo.hotelcasamia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    /* Declare the Buttons */
    private Button btnReservations;
    private Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        /* Reservation Buttons */
        btnReservations = (Button) findViewById(R.id.btn_manage_Reservations);
        btnReservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentManageReserve = new Intent(getApplication(), ManageReservationsActivity.class);
                startActivity(intentManageReserve);
            }
        });

        /* LogOut Button */
        btnLogOut = (Button) findViewById(R.id.btn_Admin_Log_Out);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //firebaseAuth.signOut();
                Toast.makeText(AdminActivity.this, R.string.Log_out_message,Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(getApplication(),LogInActivity.class));
            }
        });
    }
}/* End */
