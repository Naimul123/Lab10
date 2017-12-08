package edu.temple.lab10;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class NavigationFragment extends Fragment {
List<String> symbols;
    MyAdapter adapter;
DetailsFragment df;
TextView stockText;

OnSymbolSelectedListener listener;

    public NavigationFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        symbols = new ArrayList<String>();
        ListView listView = (ListView) view.findViewById(R.id.listview);
        adapter = new MyAdapter(getContext(), symbols);
        listView.setAdapter(adapter);

        stockText = view.findViewById(R.id.stockName);

        File file = new File(getContext().getFilesDir(), "stockFile");

        Log.d("Recreating View", "RecreatingView");
        if (file.exists()) {
            ArrayList<String> stocks = readFile(getContext());
            int size = stocks.size();
            for (int i = 0; i < size; i++) {
                symbols.add(stocks.get(i));
            }
            Toast.makeText(getActivity(), "File Exists", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "File Doesn't Exist", Toast.LENGTH_SHORT).show();
        }

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



              //  listener.setStockInfo(adapterView.getItemAtPosition(i));




            }
        });
        return view;
    }


    public void saveToFile(Context context) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("stockFile", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(symbols);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Creates an object by reading it from a file
    public static ArrayList<String> readFile(Context context) {
        ArrayList<String> symbols = null;
        try {
            FileInputStream fileInputStream = context.openFileInput("stockFile");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            symbols = (ArrayList) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return symbols;
    }

    public void addStock(String stock) {
        Log.d("New Stock Added", stock);
        symbols.add(stock);
        adapter.notifyDataSetChanged();
        saveToFile(getContext());
    }

   public interface OnSymbolSelectedListener {

        void setStockInfo(String stock);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        if(context instanceof OnSymbolSelectedListener ){
            listener = (OnSymbolSelectedListener) context;
        }
    }

}
