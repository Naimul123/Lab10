package edu.temple.lab10;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class DetailsFragment extends Fragment {



TextView text;
String data;
    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_details, container, false);

        text = (TextView) v.findViewById(R.id.stockName);









        return v;

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


   /* public void displaytext(String stockInfo){
        
        info.setText(stockInfo);
    }*/


}
