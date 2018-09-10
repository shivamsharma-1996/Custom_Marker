package shivam.com.custom_marker.certis_calender;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;

import shivam.com.custom_marker.R;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private HashMap<Integer, Integer> boxIdMap;
    private int startHour, endHour, startMin, endMin;

    private final static int COLUMN_COUNT = 11;
    private int boxWidth;


    public MyAdapter(Context context, HashMap<Integer, Integer> boxIdMap, int startHour, int endHour, int startMin, int endMin) {
        this.context = context;
        this.boxIdMap = boxIdMap;
        this.startHour = startHour;
        this.endHour = endHour;
        this.startMin = startMin;
        this.endMin = endMin;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        switch (CertiseItemType.lookupByCode(viewType)) {
            case FIRST_ROW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calender_row_layout, parent, false);
                return new FirstRowViewHolder(view);

            default:
            case OTHER_ROW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calender_row_layout, parent, false);
                return new SecondRowViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 1;
        else
            return 2;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (position) {
            case 0:
                FirstRowViewHolder firstRowViewHolder = (FirstRowViewHolder) holder;
                firstRowViewHolder.mOfficerName.setVisibility(View.INVISIBLE);
                firstRowViewHolder.mAttendence.setVisibility(View.INVISIBLE);
                firstRowViewHolder.mCheckOfficer.setVisibility(View.INVISIBLE);
                break;

            case 1:
                final int[] marginStart = new int[1];
                final int[] marginEnd = new int[1];
                final SecondRowViewHolder secondRowViewHolder = (SecondRowViewHolder) holder;

                final View boxView = secondRowViewHolder.itemView.findViewById(boxIdMap.get(endHour));
                ViewTreeObserver vto = boxView.getViewTreeObserver();
                vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        boxView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        boxWidth = boxView.getMeasuredWidth();

                        switch (startMin) {
                            case 15:
                                marginStart[0] = (boxWidth * 1) / 4;
                                break;
                            case 30:
                                marginStart[0] = (boxWidth * 2) / 4;
                                break;
                            case 45:
                                marginStart[0] = (boxWidth * 3) / 4;
                                break;
                            case 00:
                                marginStart[0] = (boxWidth * 4) / 4;
                                break;
                        }
                        switch (endMin) {
                            case 15:
                                marginEnd[0] = boxWidth - (boxWidth * 1) / 4;
                                break;
                            case 30:
                                marginEnd[0] = boxWidth - (boxWidth * 2) / 4;
                                break;
                            case 45:
                                marginEnd[0] = boxWidth - (boxWidth * 3) / 4;
                                break;
                            case 00:
                                marginEnd[0] = boxWidth - (boxWidth * 4) / 4;
                                break;
                        }

                        ConstraintLayout layout = secondRowViewHolder.itemView.findViewById(R.id.constraint_layout);
                        ConstraintSet set = new ConstraintSet();
                        View view = LayoutInflater.from(context).inflate(R.layout.dynamic_view, null);
                        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                        view.setLayoutParams(layoutParams);
                        layout.addView(view);

                        set.clone(layout);

                        if (endHour - 2 < COLUMN_COUNT)
                            if (marginEnd[0] > 0)
                                set.connect(view.getId(), ConstraintSet.END, boxIdMap.get(endHour + 1), ConstraintSet.START, marginEnd[0]);
                            else
                                set.connect(view.getId(), ConstraintSet.END, boxIdMap.get(endHour), ConstraintSet.START, marginEnd[0]);
                        else
                            set.connect(view.getId(), ConstraintSet.END, secondRowViewHolder.itemView.getId(), ConstraintSet.END, 0);
                        set.connect(view.getId(), ConstraintSet.START, boxIdMap.get(startHour), ConstraintSet.START, marginStart[0]);
                        set.connect(view.getId(), ConstraintSet.BOTTOM, secondRowViewHolder.itemView.getId(), ConstraintSet.BOTTOM, 0);
                        set.connect(view.getId(), ConstraintSet.TOP, secondRowViewHolder.itemView.getId(), ConstraintSet.TOP, 0);
                        set.applyTo(layout);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }


    private class FirstRowViewHolder extends RecyclerView.ViewHolder {
        TextView mOfficerName, mAttendence;
        CheckBox mCheckOfficer;

        public FirstRowViewHolder(View itemView) {
            super(itemView);
            mOfficerName = itemView.findViewById(R.id.tv_officer_name);
            mAttendence = itemView.findViewById(R.id.tv_available);
            mCheckOfficer = itemView.findViewById(R.id.check_officer);
        }
    }

    private static class SecondRowViewHolder extends RecyclerView.ViewHolder {
        public SecondRowViewHolder(View itemView) {
            super(itemView);
        }
    }
}