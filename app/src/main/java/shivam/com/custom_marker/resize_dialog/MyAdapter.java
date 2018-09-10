package shivam.com.custom_marker.resize_dialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import shivam.com.custom_marker.R;


public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    String[] tvshows;

    public MyAdapter(Context c, String[] tvshows) {
        this.c = c;
        this.tvshows = tvshows;
    }

    //INITIALIE VH
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        MyHolder holder=new MyHolder(v);
        return holder;
    }

    //BIND DATA
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
       holder.nameTxt.setText(tvshows[position]);
    }

    @Override
    public int getItemCount() {
        return tvshows.length;
    }
}