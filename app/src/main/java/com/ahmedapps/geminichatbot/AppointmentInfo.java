package com.ahmedapps.geminichatbot;

public class AppointmentInfo {
    public String patientName;
    public String patientEmail;
    public String doctorName;
    public String date;
    public String time;

    public AppointmentInfo() {
        // Default constructor required for Firebase
    }

    public AppointmentInfo(String patientName, String patientEmail, String doctorName, String date, String time) {
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
    }
}

