package shivam.com.custom_marker.resize_dialog;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import shivam.com.custom_marker.R;

public class MyHolder extends RecyclerView.ViewHolder {

    TextView nameTxt;

    public MyHolder(View itemView)
    {
        super(itemView);
        nameTxt= itemView.findViewById(R.id.nameTxt);
    }
}