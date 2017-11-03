package com.example.asmaa.souq.Fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.asmaa.souq.R;

import static com.example.asmaa.souq.Activities.LoginPage.Token;

public class DeliverShipment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_shipment);
        Toast.makeText(DeliverShipment.this, Token, Toast.LENGTH_SHORT).show();
    }


}
