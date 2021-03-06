package shivam.com.custom_marker.resize_dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import shivam.com.custom_marker.R;


public class TVShowFragment extends DialogFragment {

    String[] tvshows={"Crisis","Blindspot","BlackList","Game of Thrones","Gotham","Banshee", "Crisis","Blindspot","BlackList","Game of Thrones","Gotham","Banshee"};
    RecyclerView rv;
    MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.frag_layout,container);

        //RECYCER
        rv= rootView.findViewById(R.id.mRecyerID);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        //ADAPTER
        adapter=new MyAdapter(this.getActivity(),tvshows);
        rv.setAdapter(adapter);

        this.getDialog().setTitle("TV Shows");

        return rootView;
    }
}