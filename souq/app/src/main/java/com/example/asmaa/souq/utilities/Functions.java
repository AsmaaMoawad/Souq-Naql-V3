//package com.example.asmaa.souq.utilities;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.example.asmaa.souq.R;
//import com.example.asmaa.souq.Responses.ShipmentsResponse;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import static com.example.asmaa.souq.Activities.LoginPage.CheckMap;
//import static com.example.asmaa.souq.Activities.LoginPage.Token;
//
///**
// * Created by Asmaa on 01-Nov-17.
// */
//
//public class Functions {
//    private static Context con;
//
//    public Functions(Context context){
//        con=this.context;
//
//    }
//
//
//    // get all shipments from server
//
//     public static  void shipments() {
//        RequestQueue queue = Volley.newRequestQueue(getContext());
//
//        String url = APIs.Shipments_URL;
//
//        final ProgressDialog loading = ProgressDialog.show(getContext(), null, "جارى تحميل البيانات ......", false, false);
//
//        // Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        Toast.makeText(getContext(),"رجعت الشحنات ",Toast.LENGTH_LONG).show();
//                        Log.d("shipment",response);
//                        Type listType = new TypeToken<ArrayList<ShipmentsResponse>>(){}.getType();
//                        shipmentsList = new Gson().fromJson(response,listType);
//
//
//                        for (int i=0 ; i<shipmentsList.size();i++) {
//                            mMap.addMarker(new MarkerOptions()
//                                    .position(new LatLng(shipmentsclass.getEnd_point_latitude(),shipmentsclass.getEnd_point_longitude()))
//                                    .title(shipmentsclass.type)
//                                    .snippet(shipmentsclass.type + " " + shipmentsclass.price).icon(BitmapDescriptorFactory.fromResource(R.drawable.activestar)));
//                            CheckMap=true;
//                        }
//
//
//                        loading.dismiss();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                loading.dismiss();
//                Toast.makeText(getContext(), "مقدرتش ارجع داتا من السيرفر", Toast.LENGTH_SHORT).show();
//                Log.w("response", "Response Erorr");
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Authorization", "Bearer" +" "+ Token);
//                return headers;
//            }
//        };
//
//        // Add the request to the RequestQueue.
//        queue.add(stringRequest);
//    }
//
//}
