package com.example.asmaa.souq.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asmaa.souq.R;
import com.example.asmaa.souq.Responses.ShipmentsResponse;

import java.util.List;


/**
 * Created by Asmaa on 01-Nov-17.
 */

public class AdaptorListCurentShipments extends BaseAdapter {

    ShipmentsResponse shipmentsclass =new ShipmentsResponse();
    private LayoutInflater mInflater;
	
	List<ShipmentsResponse> mlist;

    public AdaptorListCurentShipments(Context context,List<ShipmentsResponse> myList) {
        mInflater = LayoutInflater.from(context);
		mlist = myList;

    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = mInflater.inflate(R.layout.listview_currentshipments, null);


        TextView text_getmoney=(TextView)convertView.findViewById(R.id.money_textView);
        TextView text_gettime=(TextView)convertView.findViewById(R.id.time_textView);
        TextView text_location=(TextView)convertView.findViewById(R.id.location_shipment);
        text_getmoney.setText(mlist.get(position).getPrice());
        text_gettime.setText(mlist.get(position).getArrival_time());
        text_location.setText("من"+mlist.get(position).getFrom()+"الى"+mlist.get(position).getTo());

        return null;
    }
}
