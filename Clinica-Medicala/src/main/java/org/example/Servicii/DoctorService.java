package org.example.Servicii;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.Model.Appointment;
import org.example.Model.Doctor;
import org.example.Database.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

public class DoctorService {
    private final EntityManager em = new HibernateUtil().getEntityManager();

    public void registerDoctor(Doctor doctor) {
        em.getTransaction().begin();
        em.persist(doctor);
        em.getTransaction().commit();
    }

    public Doctor findByEmail(String email) {
        TypedQuery<Doctor> query = em.createQuery("SELECT d FROM Doctor d WHERE d.email = :email", Doctor.class);
        query.setParameter("email", email);
        return query.getResultStream().findFirst().orElse(null);
    }

    public List<Doctor> getAllDoctors() {
        return em.createQuery("SELECT d FROM Doctor d", Doctor.class).getResultList();
    }

    public List<Appointment> getAppointmentsByDate(Doctor doctor, LocalDate date) {
        return em.createQuery(
                        "SELECT a FROM Appointment a WHERE a.doctor = :doctor AND a.date = :date", Appointment.class)
                .setParameter("doctor", doctor)
                .setParameter("date", date)
                .getResultList();
    }
    public Doctor findById(Long id) {
        return em.find(Doctor.class, id);
    }



}
