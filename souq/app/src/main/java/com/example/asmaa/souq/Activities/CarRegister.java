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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.example.asmaa.souq.utilities.APIs;

import java.util.HashMap;
import java.util.Map;

public class CarRegister extends AppCompatActivity {
    Spinner spinner_CarType,spinner_CarModel,spinner_CarCarry,spinner_CarYear;
    ArrayAdapter<CharSequence> adapter_CarType,adapter_CarModel,adapter_CarCarry,adapter_CarYear;
    String CarCarry,CarModel,CarYear,CarType;
    ImageView registerbutton;
    int id;
    EditText plate_no;
    TextView back;
    ImageView help;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_register);
        spinner_CarCarry = (Spinner) findViewById(R.id.CarCarry_Spinner);
        spinner_CarModel = (Spinner) findViewById(R.id.CarModel_Spinner);
        spinner_CarType = (Spinner) findViewById(R.id.CarType_Spinner);
        spinner_CarYear = (Spinner) findViewById(R.id.CarYear_Spinner);
        registerbutton=(ImageView) findViewById(R.id.btn_register_car);
        plate_no=(EditText)findViewById(R.id.plate_no_edittext);
        id=Integer.parseInt(getIntent().getStringExtra("id"));
        back=(TextView)findViewById(R.id.back_CarRigster);
        help=(ImageView)findViewById(R.id.help_CarRigister);


        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:01143050128"));
                if (ActivityCompat.checkSelfPermission(CarRegister.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                if (ActivityCompat.checkSelfPermission(CarRegister.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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
                Intent intent =new Intent(CarRegister.this,ProfileRegister.class);
                startActivity(intent);
            }
        });










/////////////////////////////////////////////////////////Button_Register/////////////////////////////////////////////////////////////////////////

            registerbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registration();
                }
            });




        ////////////////////////////////////////////////////Adaptor_Spinner_CarCarry//////////////////////////////////////////////////////////////


        adapter_CarCarry = ArrayAdapter.createFromResource(this, R.array.car_carry, android.R.layout.simple_spinner_item);
        adapter_CarCarry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_CarCarry.setAdapter(adapter_CarCarry);

        spinner_CarCarry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CarCarry = parent.getItemAtPosition(position).toString();
                Toast.makeText(CarRegister.this, CarCarry, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ////////////////////////////////////////////////////Adaptor_Spinner_CarModel//////////////////////////////////////////////////////////////


        adapter_CarModel = ArrayAdapter.createFromResource(this, R.array.car_model, android.R.layout.simple_spinner_item);
        adapter_CarModel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_CarModel.setAdapter(adapter_CarModel);
        spinner_CarModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CarModel = parent.getItemAtPosition(position).toString();
                Toast.makeText(CarRegister.this, CarModel, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //////////////////////////////////////////////////////////Adaptor_Spinner_CarYear///////////////////////////////////////////////////////


        adapter_CarYear = ArrayAdapter.createFromResource(this, R.array.car_year, android.R.layout.simple_spinner_item);
        adapter_CarYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_CarYear.setAdapter(adapter_CarYear);
        spinner_CarYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CarYear = parent.getItemAtPosition(position).toString();
                Toast.makeText(CarRegister.this, CarYear, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //////////////////////////////////////////////////Adaptor_Spinner_CarType///////////////////////////////////////////////////////////////////////////////////////


        adapter_CarType = ArrayAdapter.createFromResource(this, R.array.car_type, android.R.layout.simple_spinner_item);
        adapter_CarType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_CarType.setAdapter(adapter_CarType);
        spinner_CarType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CarType = parent.getItemAtPosition(position).toString();
                Toast.makeText(CarRegister.this, CarType, Toast.LENGTH_LONG).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });
    }


    void registration() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = APIs.RegisterCar_URL + id;

        final ProgressDialog loading = ProgressDialog.show(this, null, "انتظر من فضلك ......", false, false);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.w("response", response);
                        Toast.makeText(CarRegister.this, " تم التسجيل بنجاح", Toast.LENGTH_SHORT).show();

                        loading.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(CarRegister.this, "حاول مره اخرى", Toast.LENGTH_SHORT).show();
                Log.w("response", "حاول مره اخرى ");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> params = new HashMap<String, String>();

                params.put("Weight",CarCarry);
                params.put("CarType",CarType);
                params.put("Model",CarModel);
                params.put("Year",CarYear);
                params.put("user_id",String.valueOf(id));
                params.put("plateNo",plate_no.getText().toString());







                return params;

            }
        };

        queue.add(stringRequest);
    }








    }



