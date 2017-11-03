package com.example.asmaa.souq.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asmaa.souq.Adaptors.AdaptorListCurentShipments;
import com.example.asmaa.souq.R;
import com.example.asmaa.souq.Responses.ShipmentsResponse;
import com.example.asmaa.souq.utilities.APIs;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.asmaa.souq.Activities.LoginPage.Token;

public class testdialog_listview extends AppCompatActivity {

    public static List<ShipmentsResponse> shipmentsList;
    ListView ListShipments;
    AdaptorListCurentShipments adaptorListCurentShipments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testdialog_listview);
        shipments();
        ListShipments= (ListView) findViewById(R.id.list_currentshipment);







    }

    // get all shipments from server

    public   void shipments() {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = APIs.Shipments_URL;

        final ProgressDialog loading = ProgressDialog.show(testdialog_listview.this, null, "جارى تحميل البيانات ......", false, false);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(testdialog_listview.this,"رجعت الشحنات ",Toast.LENGTH_LONG).show();
                        Log.d("response",response);
                        Type listType = new TypeToken<ArrayList<ShipmentsResponse>>(){}.getType();
                        shipmentsList = new Gson().fromJson(response,listType);
                        Log.d("shipmentlist", String.valueOf(shipmentsList));
                        adaptorListCurentShipments=new AdaptorListCurentShipments(testdialog_listview.this,shipmentsList);
                        ListShipments.setAdapter(adaptorListCurentShipments);
//                        int i =ListShipments.getCount();
//                        Log.d("i", String.valueOf(i));






                        loading.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(testdialog_listview.this, "مقدرتش ارجع داتا من السيرفر", Toast.LENGTH_SHORT).show();
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
