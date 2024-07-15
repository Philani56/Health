package com.ahmedapps.geminichatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.util.Calendar;
import android.app.ProgressDialog;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

public class Appointment extends AppCompatActivity {

    EditText etDate, etTime, etPatientName, etPatientEmail;
    Spinner spinnerDoctors;
    DatabaseReference appointmentsRef;
    Button submit;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        etDate = findViewById(R.id.et_date);
        etTime = findViewById(R.id.et_time);
        etPatientName = findViewById(R.id.et_patient_name);
        etPatientEmail = findViewById(R.id.et_patient_email);
        spinnerDoctors = findViewById(R.id.spinner_doctors);
        appointmentsRef = FirebaseDatabase.getInstance().getReference("appointments");
        submit = findViewById(R.id.btn_submit);

        setupSpinner();
    }

    private void setupSpinner() {
        // Define doctor names
        String[] doctorNames = getResources().getStringArray(R.array.doctor_names);

        // Create adapter for spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, doctorNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply adapter to spinner
        spinnerDoctors.setAdapter(adapter);
    }

    public void showDatePicker(View view) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = new DatePickerDialog(this,
                (view1, year, monthOfYear, dayOfMonth) -> {
                    String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    etDate.setText(date);
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH));
        dpd.show();
    }

    public void showTimePicker(View view) {
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = new TimePickerDialog(this,
                (view1, hourOfDay, minute) -> {
                    String time = String.format("%02d:%02d", hourOfDay, minute);
                    etTime.setText(time);
                },
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                false);
        tpd.show();
    }

    public void onClickBack1(View view) {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void submit(View view) {
        String patientName = etPatientName.getText().toString().trim();
        String patientEmail = etPatientEmail.getText().toString().trim();
        String doctorName = spinnerDoctors.getSelectedItem().toString();
        String date = etDate.getText().toString().trim();
        String time = etTime.getText().toString().trim();

        if (patientName.isEmpty() || patientEmail.isEmpty() || date.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show progress dialog
        showProgressDialog();

        // Create Appointment object
        AppointmentInfo appointment = new AppointmentInfo(patientName, patientEmail, doctorName, date, time);

        // Push to Firebase Database
        String appointmentId = appointmentsRef.push().getKey();
        appointmentsRef.child(appointmentId).setValue(appointment)
                .addOnSuccessListener(aVoid -> {
                    dismissProgressDialog();
                    showSuccessDialog();
                    // Clear fields after successful submission
                    etPatientName.setText("");
                    etPatientEmail.setText("");
                    etDate.setText("");
                    etTime.setText("");
                    spinnerDoctors.setSelection(0); // Reset spinner to first item
                })
                .addOnFailureListener(e -> {
                    dismissProgressDialog();
                    Toast.makeText(Appointment.this, "Failed to book appointment. Please try again.", Toast.LENGTH_SHORT).show();
                });
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Booking appointment...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Appointment Booked Successfully")
                .setMessage("Your appointment has been booked successfully.")
                .setIcon(R.drawable.ic_check_circle) // Replace with your checkmark icon
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    // Handle OK button click if needed, or leave empty
                })
                .setOnDismissListener(dialogInterface -> {
                    // Optional: Handle dismiss listener if needed
                })
                .show();
    }
}