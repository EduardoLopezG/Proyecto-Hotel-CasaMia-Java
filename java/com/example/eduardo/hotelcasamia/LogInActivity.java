package com.example.eduardo.hotelcasamia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    /* Declare the objects */
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnRegister;
    private Button btnLogIn;
    private ProgressDialog progressDialog;

    /* Declare object FirebaseAuth */
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        /* Initializaing FirebaseAuth Object */
        firebaseAuth = FirebaseAuth.getInstance();

        /* Referencing the views */
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        btnRegister = (Button) findViewById(R.id.btn_registrer);
        btnLogIn = (Button) findViewById(R.id.btn_log_in);
        progressDialog = new ProgressDialog(this);

        /* attaching listener to the Buttons */
        btnRegister.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);
    }

    /* Method to add a new user */
    private void addUser(){
        /* Obtain the Email and Password from the EditText */
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        /* Verifing that have not empty places */
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, R.string.emptyEmailMessage,Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, R.string.emptyPasswordMessage,Toast.LENGTH_LONG).show();
            return;
        }
        /* Progress Message */
        progressDialog.setMessage(getString(R.string.MakingReservationProgressMessage));
        progressDialog.show();

        /* Creating a new user */
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LogInActivity.this,getString(R.string.first_part_sucessful_user_register)+ edtEmail.getText(),Toast.LENGTH_LONG).show();
                }else{
                    /* Method to compare if have same users */
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(LogInActivity.this, R.string.emailRegistredMessage,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LogInActivity.this, R.string.doNotRegistredUserMessage,Toast.LENGTH_LONG).show();
                    }
                }
                progressDialog.dismiss();
            }
        });
    }


    /* Method to LogIn in the Aplication */
    private void logUser(){
        /* Obtain the Email and Password from the EditText */
        final String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        /* Verifing that have not empty places */
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,R.string.emptyPasswordMessage,Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,R.string.emptyPasswordMessage,Toast.LENGTH_LONG).show();
            return;
        }
        /* Progress Message */
        progressDialog.setMessage(getString(R.string.LogingInProgressMassage));
        progressDialog.show();

        /* LogIn user */
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    /* This to send the email to the next activity */
                    int pos = email.indexOf("@");
                    String user = email.substring(0,pos);
                    /* Welcome Message */
                    Toast.makeText(LogInActivity.this,getString(R.string.first_part_message_welcome_log_in)+ edtEmail.getText(),Toast.LENGTH_LONG).show();
                    /* Pass to the next Screen */
                    /* Detition to know the kind of user */
                    if(email.equals("hotelcasamia@yahoo.com")){
                        Intent intent = new Intent(getApplication(),AdminActivity.class);
                        finish();
                        startActivity(intent);
                    }else{
                        Intent intentLogIn = new Intent(getApplication(),UserActivity.class);//<------
                        /* Pass the user to te next activity */
                        intentLogIn.putExtra(UserActivity.user,user);
                        /* Finish the actual screen */
                        finish();
                        /* Launch the new activity */
                        startActivity(intentLogIn);
                    }
                }else{
                    /* Method to compare if have same users */
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(LogInActivity.this, R.string.userDoExistMessageLogIn,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LogInActivity.this, R.string.checkUserAndPasswordMessageLogIn,Toast.LENGTH_LONG).show();
                    }
                }
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {
        /* Identifing the type of Button */
        switch (view.getId()){
            case R.id.btn_registrer:
                /* Call the method */
                addUser();
                break;
            case R.id.btn_log_in:
                /* Call the method */
                logUser();
                break;
        }
    }
}/* End */
