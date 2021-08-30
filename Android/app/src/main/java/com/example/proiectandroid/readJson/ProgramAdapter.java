package com.example.proiectandroid.readJson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;

import android.view.ViewGroup;

import android.widget.TextView;

import com.example.proiectandroid.R;
import com.example.proiectandroid.utils.Activitati;

import java.util.ArrayList;

public class ProgramAdapter extends BaseAdapter {
    private ArrayList<Activitati> activitati;
    private Context context;
    private LayoutInflater inflater;

    public ProgramAdapter(ArrayList<Activitati> activitati, Context context) {
        this.activitati = activitati;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return activitati.size();
    }

    @Override
    public Object getItem(int position) {
        return activitati.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final View item=inflater.inflate(R.layout.activitate_item, viewGroup, false);

        TextView tvZi=item.findViewById(R.id.tv_zi);
        TextView tvDenumire=item.findViewById(R.id.tv_denumire);
        TextView tvInterval=item.findViewById(R.id.tv_interval);
        TextView tvPrioritate=item.findViewById(R.id.tv_prioritate);

        Activitati activitate=activitati.get(position);
        tvZi.setText(activitate.getZi());
        tvDenumire.setText(activitate.getDenumire());
        tvInterval.setText(activitate.getInterval());
        tvPrioritate.setText("Prioritate: " + activitate.getPrioritate());
        return item;
    }

    public void addElement(Activitati activitate){
        activitati.add(activitate);
        notifyDataSetChanged();
    }

}
