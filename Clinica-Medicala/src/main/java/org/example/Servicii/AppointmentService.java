package org.example.Servicii;

import jakarta.persistence.EntityManager;
import org.example.Model.Appointment;
import org.example.Database.HibernateUtil;

import java.util.List;

public class AppointmentService {
    private final EntityManager em = new HibernateUtil().getEntityManager();

    public void createAppointment(Appointment appointment) {
        em.getTransaction().begin();
        em.persist(appointment);
        em.getTransaction().commit();
    }

    public List<Appointment> getAppointmentsByDoctorId(Integer doctorId) {
        return em.createQuery("SELECT a FROM Appointment a WHERE a.doctor.id = :id", Appointment.class)
                .setParameter("id", doctorId)
                .getResultList();
    }

    public List<Appointment> getAppointmentsByPatientId(Integer patientId) {
        return em.createQuery("SELECT a FROM Appointment a WHERE a.patient.id = :id", Appointment.class)
                .setParameter("id", patientId)
                .getResultList();
    }
    public void saveAppointment(Appointment appointment) {
        createAppointment(appointment);
    }

    public Appointment findById(Long id) {
        return em.find(Appointment.class, id);
    }

}
