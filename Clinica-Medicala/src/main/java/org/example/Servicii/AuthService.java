package org.example.Servicii;

import org.example.Model.Doctor;
import org.example.Model.Patient;
import org.example.Model.User;
import org.example.Model.UserRole;
import org.example.Database.HibernateUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class AuthService {

    private final EntityManager em = new HibernateUtil().getEntityManager();

    public boolean emailExists(String email) {
        long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class).setParameter("email", email).getSingleResult();
        return count > 0;
    }

    public void registerUser(User user) {
        if(emailExists(user.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public User login(String email, String password) {
        TypedQuery<Patient> patientQuery = em.createQuery(
                "SELECT p FROM Patient p WHERE p.email = :email AND p.password = :password", Patient.class);
        patientQuery.setParameter("email", email);
        patientQuery.setParameter("password", password);

        if (!patientQuery.getResultList().isEmpty()) {
            return patientQuery.getSingleResult();
        }

        TypedQuery<Doctor> doctorQuery = em.createQuery(
                "SELECT d FROM Doctor d WHERE d.email = :email AND d.password = :password", Doctor.class);
        doctorQuery.setParameter("email", email);
        doctorQuery.setParameter("password", password);

        if (!doctorQuery.getResultList().isEmpty()) {
            return doctorQuery.getSingleResult();
        }

        return null; // user not found
    }
}




