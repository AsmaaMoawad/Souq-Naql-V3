package com.example.asmaa.souq.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asmaa.souq.Fragments.BalanceFragment;
import com.example.asmaa.souq.Fragments.MapFragment;
import com.example.asmaa.souq.R;
import com.example.asmaa.souq.Responses.UserDataResponse;
import com.example.asmaa.souq.utilities.APIs;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.example.asmaa.souq.Activities.LoginPage.Token;

public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String Name;
    int Age;
    TextView Age_textview,Name_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        Age_textview=(TextView)header.findViewById(R.id.age_textview_nav);
        Name_textview=(TextView)header.findViewById(R.id.age_textview_nav);
        MapFragment mapActivity=new MapFragment();
        BalanceFragment balanceFragment=new BalanceFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, balanceFragment);
        fragmentTransaction.commit();
        userdata();
//        Age_textview.setText(Age);
//        Name_textview.setText(Name);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setLocale("ar", this);//elfunction b3tlha el language w el context




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_balance) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_rate) {

        } else if (id == R.id.nav_update) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


// function for nafigation drawer to be right and change el configration to arabic
    public static void setLocale(String lang, Context c) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        c.getApplicationContext().getResources().updateConfiguration(config, null);

    }


    // get all userdata

    void userdata() {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = APIs.UserData_URL;

        final ProgressDialog loading = ProgressDialog.show(this, null, "جارى تحميل البيانات ......", false, false);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        UserDataResponse userDataResponse=new Gson().fromJson(response, UserDataResponse.class);
                        Toast.makeText(NavigationDrawer.this,"رجعت الداتا ",Toast.LENGTH_LONG).show();
                        Age=userDataResponse.Age;
                        Name=userDataResponse.Name;
                        Log.d("user",response);
                        Log.d("name",Name);
                        Log.d("Age",String.valueOf(Age));



                        loading.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(NavigationDrawer.this, "مقدرتش ارجع داتا من السيرفر", Toast.LENGTH_SHORT).show();
                Log.w("response", "Response Erorr");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer" +" "+ Token);
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}
