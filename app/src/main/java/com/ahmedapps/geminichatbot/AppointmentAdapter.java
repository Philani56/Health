package com.ahmedapps.geminichatbot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    private List<AppointmentClass> appointments;

    public AppointmentAdapter(List<AppointmentClass> appointments) {
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppointmentClass appointment = appointments.get(position);
        holder.tvPatientName.setText(appointment.patientName);
        holder.tvEmail.setText(appointment.email);
        holder.tvDoctorName.setText(appointment.doctorName);
        holder.tvDate.setText(appointment.date);
        holder.tvTime.setText(appointment.time);
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPatientName;
        TextView tvEmail;
        TextView tvDoctorName;
        TextView tvDate;
        TextView tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPatientName = itemView.findViewById(R.id.tv_patient_name);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvDoctorName = itemView.findViewById(R.id.tv_doctor_name);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}


