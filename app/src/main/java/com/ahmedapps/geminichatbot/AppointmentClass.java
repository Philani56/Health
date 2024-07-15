package com.ahmedapps.geminichatbot;

public class AppointmentClass {
    public String patientName;
    public String email;
    public String doctorName;
    public String date;
    public String time;

    public AppointmentClass() {
        // Default constructor required for calls to DataSnapshot.getValue(Appointment.class)
    }

    public AppointmentClass(String patientName, String email, String doctorName, String date, String time) {
        this.patientName = patientName;
        this.email = email;
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
    }
}

