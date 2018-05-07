package com.example.eduardo.hotelcasamia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity {

    /* Declate the Button */
    private Button btnReserve;
    private Button btnLogOut;

    /* Var to know the email */
    public static final String user="names";
    TextView txvUser;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        /* Initializing object */
        txvUser = (TextView) findViewById(R.id.txv_Welcome);
        String user = getIntent().getStringExtra("names");
        /* Welcome Message */
        txvUser.setText(getString(R.string.first_part_log_out_user_message)+ user +getString(R.string.admiration_symbol_user_log_out));

        /* Button Reserve */
        btnReserve = (Button) findViewById(R.id.btn_Reserve);
        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReserve = new Intent(getApplication(), ReservationActivity.class);
                startActivity(intentReserve);
            }
        });

        /* Button LogOut */
        btnLogOut = (Button) findViewById(R.id.btn_log_out);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //firebaseAuth.signOut();
                Toast.makeText(UserActivity.this,R.string.Log_out_message,Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(getApplication(),LogInActivity.class));
            }
        });
    }
}/* End */
