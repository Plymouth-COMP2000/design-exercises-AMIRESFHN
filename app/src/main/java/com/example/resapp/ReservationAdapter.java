package com.example.resapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.resapp.model.ReservationEntity;
import com.example.resapp.model.AppDatabase;
import java.util.List;


public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ResViewHolder> {
    private List<ReservationEntity> reservationList;
    private AppDatabase db;

    public ReservationAdapter(List<ReservationEntity> reservationList, AppDatabase db) {
        this.reservationList = reservationList;
        this.db = db;
    }

    @Override
    public ResViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reservation_staff, parent, false);
        return new ResViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ResViewHolder holder, int position) {
        ReservationEntity res = reservationList.get(position);
        holder.tvName.setText(res.customerName);
        holder.tvDate.setText(res.date);
        holder.tvTime.setText(res.time);

        holder.btnCancel.setOnClickListener(v -> {
            db.reservationDao().delete(res);
            reservationList.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    static class ResViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDate, tvTime;
        Button btnCancel;


        public ResViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            btnCancel = itemView.findViewById(R.id.btnCancel);


        }
    }
    private boolean isStaff;

    public ReservationAdapter(List<ReservationEntity> reservationList, AppDatabase db, boolean isStaff) {
        this.reservationList = reservationList;
        this.db = db;
        this.isStaff = isStaff;
    }

}
