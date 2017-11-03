package com.example.asmaa.souq.Fragments;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asmaa.souq.R;
import com.example.asmaa.souq.Responses.ShipmentsResponse;
import com.example.asmaa.souq.utilities.APIs;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.asmaa.souq.Activities.LoginPage.CheckMap;
import static com.example.asmaa.souq.Activities.LoginPage.Token;

public class MapFragment extends Fragment implements OnMapReadyCallback  , GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    public static GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    double myLatitude, myLongitude;
     ShipmentsResponse shipmentsclass =new ShipmentsResponse();
     List<ShipmentsResponse> shipmentsList;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        SupportMapFragment fragment = new SupportMapFragment();
        transaction.add(R.id.map, fragment);
        transaction.commit();



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_map, container, false);


    }




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
        public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            myLatitude = mLastLocation.getLatitude();
            myLongitude = mLastLocation.getLongitude();
            //shipments();



            LatLng myLocation = new LatLng(myLatitude, myLongitude);
            mMap.addMarker(new MarkerOptions().position(myLocation).title("My Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(myLocation).zoom(5).build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            Log.w("latitude", String.valueOf(mLastLocation.getLatitude()));
            Log.w("longitude", String.valueOf(mLastLocation.getLongitude()));

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    // get all shipments from server

    public   void shipments() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        String url = APIs.Shipments_URL;

        final ProgressDialog loading = ProgressDialog.show(getContext(), null, "جارى تحميل البيانات ......", false, false);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getContext(),"رجعت الشحنات ",Toast.LENGTH_LONG).show();
                        Log.d("shipment",response);
                        Type listType = new TypeToken<ArrayList<ShipmentsResponse>>(){}.getType();
                        shipmentsList = new Gson().fromJson(response,listType);


                        for (int i=0 ; i<shipmentsList.size();i++) {
                            mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(shipmentsclass.getEnd_point_latitude(),shipmentsclass.getEnd_point_longitude()))
                                    .title(shipmentsclass.type)
                                    .snippet(shipmentsclass.type + " " + shipmentsclass.price).icon(BitmapDescriptorFactory.fromResource(R.drawable.activestar)));
                            CheckMap=true;
                        }


                        loading.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(getContext(), "مقدرتش ارجع داتا من السيرفر", Toast.LENGTH_SHORT).show();
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

