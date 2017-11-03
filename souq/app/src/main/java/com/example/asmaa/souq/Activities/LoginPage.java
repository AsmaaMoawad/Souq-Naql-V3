package com.example.asmaa.souq.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asmaa.souq.R;
import com.example.asmaa.souq.Responses.LoginResponse;
import com.example.asmaa.souq.utilities.APIs;
import com.example.asmaa.souq.utilities.Prefmanager;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends AppCompatActivity {
    Button login;
    EditText Phone;
    EditText pass;
    ImageView join;
    TextView back;
    ImageView help;
    public static Boolean CheckMap=false;
    public static String Token;
    Prefmanager pref;




    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_login_page);
        Phone = (EditText) findViewById(R.id.phone_login_Edittext);
        pass=(EditText)findViewById(R.id.pass_login_Edittext);
        login=(Button)findViewById(R.id.login_btn);
        join=(ImageView)findViewById(R.id.btn_join);
        back=(TextView)findViewById(R.id.back_login);
        help=(ImageView)findViewById(R.id.hep_login);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:01143050128"));
                if (ActivityCompat.checkSelfPermission(LoginPage.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                if (ActivityCompat.checkSelfPermission(LoginPage.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }
        });





        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginPage.this,StartPage.class);
                startActivity(intent);
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginPage.this,ProfileRegister.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }


    void login() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        String url= APIs.Login_URL;

        final ProgressDialog loading = ProgressDialog.show(this, null, "استنى شويه عقبال مااحمل ", false, false);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        LoginResponse loginResponse=new Gson().fromJson(response, LoginResponse.class);
                        Token=loginResponse.getToken();
                        Log.d("token",Token);
                        Log.d("res",String.valueOf(loginResponse));
//                        pref.saveUserDataLogin(loginResponse);
                        Toast.makeText(LoginPage.this, "عملت لوجن خلاص", Toast.LENGTH_SHORT).show();
                        if (CheckMap=true) {
                            Intent intent = new Intent(LoginPage.this, testdialog_listview.class);
                            startActivity(intent);

                            loading.dismiss();
                        }
                        else {
                            Toast.makeText(LoginPage.this," مش عارف اجهز الماب يابنتى والله شوفى المشكله فين  ",Toast.LENGTH_LONG).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(LoginPage.this, "مش قادر ادخل ع السيرفر ولا ارجع حاجه", Toast.LENGTH_SHORT).show();
                Log.w("response", "Response Erorr");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("phone_number", Phone.getText().toString());
                params.put("password",pass.getText().toString());

                return params;

            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }







}
