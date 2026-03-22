package org.example.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String timeSlot;
    private boolean confirmed;

    @ManyToOne
    @JoinColumn(name = "pacient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Appointment() {}

    public Appointment(LocalDate date, String timeSlot, Patient patient, Doctor doctor) {
        this.date = date;
        this.timeSlot = timeSlot;
        this.patient = patient;
        this.doctor = doctor;
        this.confirmed = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient pacient) {
        this.patient = pacient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
