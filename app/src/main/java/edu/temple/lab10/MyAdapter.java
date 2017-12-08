package edu.temple.lab10;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class MyAdapter extends BaseAdapter {

    private List<String> symbols;
    private Context context;
    public MyAdapter(Context context, List<String> symbols){
        this.context = context;
        this.symbols = symbols;

    }



    @Override
    public int getCount() {
        return symbols.size();
    }

    @Override
    public Object getItem(int i) {
        return symbols.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        TextView textView = new TextView(context);
        textView.setText(symbols.get(i));

        return textView;
    }
}
