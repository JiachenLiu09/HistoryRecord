package com.jiachen.history_record.tools;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.jiachen.history_record.eneities.History;
import com.jiachen.history_record.R;

import java.util.List;

public class CusAdapter extends RecyclerView.Adapter<CusAdapter.HistoryVH> {

    private static final String TAG = "CusAdapter";
    List<History> historyList;

    public CusAdapter(List<History> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_row, parent, false);
        return new HistoryVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryVH holder, int position) {

        History history = historyList.get(position);
        //System.out.println(history);
        holder.startPointView.setText(history.getStartPoint());
        holder.endPointView.setText(history.getEndPoint());
        holder.startTimeView.setText(history.getStartTime());
        holder.durationView.setText(history.getDuration());
        holder.calView.setText(history.getCalories());

        boolean isExpanded = historyList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    class HistoryVH extends RecyclerView.ViewHolder {

        private static final String TAG = "HistoryVH";

        ConstraintLayout expandableLayout;
        TextView startPointView, endPointView, startTimeView, durationView, calView;

        public HistoryVH(@NonNull final View itemView) {
            super(itemView);

            startTimeView = itemView.findViewById(R.id.tv_start_time);
            endPointView = itemView.findViewById(R.id.tv_end_point);
            startPointView = itemView.findViewById(R.id.tv_start_point);
            durationView = itemView.findViewById(R.id.tv_duration);
            calView = itemView.findViewById(R.id.tv_cal);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);


            startTimeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    History history = historyList.get(getAdapterPosition());
                    history.setExpanded(!history.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
