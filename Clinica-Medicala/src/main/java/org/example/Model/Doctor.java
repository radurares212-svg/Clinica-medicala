package org.example.Model;
import jakarta.persistence.*;
import java.util.List;


@Entity
public class Doctor extends User {

    private String specialization;

    private boolean needsRecommendation;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    public Doctor() {
        this.role = UserRole.DOCTOR;
    }

    public Doctor(String name, String email, String password, UserRole role, String specialization, boolean needsRecommendation) {
        super(name, email, password, role);
        this.specialization = specialization;
        this.needsRecommendation = needsRecommendation;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public boolean isNeedsRecommendation() {
        return needsRecommendation;
    }

    public void setNeedsRecommendation(boolean needsRecommendation) {
        this.needsRecommendation = needsRecommendation;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
