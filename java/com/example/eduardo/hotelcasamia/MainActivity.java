package com.example.eduardo.hotelcasamia;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        IComunicationFragments {

    DetailPlacesFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /* ADD THIS LINES TO THE FRAGMENTS */
        if(Utilities.validatingScreen = true){
            FragmentManager fragmentManager = getSupportFragmentManager();//<-----------
            fragmentManager.beginTransaction().replace(R.id.container, new MainFragment()).commit();//<-----------
            Utilities.validatingScreen = false;
        }
    }//End "onCreate"

    /* Confirmation LogOut Message */
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.logOutApplicationMessage);
        builder.setTitle(R.string.TitleLogOutApplicationMessage);
        builder.setPositiveButton(R.string.positive_option_text_log_out, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton(R.string.negative_option_text_log_out, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

   /* Option in ToolBar - Action - */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            startActivity(new Intent(this, LogInActivity.class)); //<--------
        }
        return super.onOptionsItemSelected(item);
    }

                    /* MENU OPTIONS */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();//Add this line

        if (id == R.id.nav_main) {
            fragmentManager.beginTransaction().replace(R.id.container, new MainFragment()).commit();//<------
        } else if (id == R.id.nav_yourHotel) {
            fragmentManager.beginTransaction().replace(R.id.container, new YourHotelFragment()).commit();//<------
        } else if (id == R.id.nav_visits) {
            fragmentManager.beginTransaction().replace(R.id.container, new VisitsFragment()).commit();//<------
        } else if (id == R.id.nav_arrive) {
            HowArriveFragment howArriveFragment = new HowArriveFragment();//<------
            FragmentManager manager = getSupportFragmentManager();//<------
            manager.beginTransaction().replace(R.id.container, howArriveFragment).commit();//<------
        } else if (id == R.id.nav_experience) {
            fragmentManager.beginTransaction().replace(R.id.container, new ShareExperienceFragment()).commit();//<------
        }else if (id == R.id.nav_logout){
            onBackPressed();//<--------
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void sendPlace(PlacesVO place) {
        detailFragment = new DetailPlacesFragment();
        Bundle bundleSend = new Bundle();
        bundleSend.putSerializable("object",place);
        detailFragment.setArguments(bundleSend);

        /* Load Fragment Detail */
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragment,detailFragment).commit();
    }
}//End class
