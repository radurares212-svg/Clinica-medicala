package org.example.Servicii;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.Model.Patient;
import org.example.Database.HibernateUtil;

import java.util.List;

public class PatientService {
    private final EntityManager em = new HibernateUtil().getEntityManager();

    public void registerPatient(Patient patient) {
        em.getTransaction().begin();
        em.persist(patient);
        em.getTransaction().commit();
    }

    public Patient findByEmail(String email) {
        TypedQuery<Patient> query = em.createQuery("SELECT p FROM Patient p WHERE p.email = :email", Patient.class);
        query.setParameter("email", email);
        return query.getResultStream().findFirst().orElse(null);
    }

    public List<Patient> getAllPatients() {
        return em.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();
    }

    public Patient findById(Long id) {
        return em.find(Patient.class, id);
    }

}
