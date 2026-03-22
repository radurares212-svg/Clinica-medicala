package org.example.Model;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Patient extends User{

    private String allergies;

    @Column(name = "treatment_history")
    private String treatmentHistory;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    public Patient() {
        this.role = UserRole.PATIENT;
    }

    public Patient(String name, String email, String password, String allergies, String treatmentHistory) {
        super(name, email, password, UserRole.PATIENT);
        this.allergies = allergies;
        this.treatmentHistory = treatmentHistory;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getTreatmentHistory() {
        return treatmentHistory;
    }

    public void setTreatmentHistory(String treatmentHistory) {
        this.treatmentHistory = treatmentHistory;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
