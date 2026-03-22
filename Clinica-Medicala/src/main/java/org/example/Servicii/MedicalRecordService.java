package org.example.Servicii;

import jakarta.persistence.EntityManager;
import org.example.Database.HibernateUtil;
import org.example.Model.MedicalRecord;
import org.example.Model.Patient;
import org.example.Model.Doctor;

import java.time.LocalDate;
import java.util.List;

public class MedicalRecordService {

    private final EntityManager em = new HibernateUtil().getEntityManager();

    public void addMedicalRecord(Patient patient, Doctor doctor, String diagnostic, String tratament) {
        em.getTransaction().begin();
        MedicalRecord record = new MedicalRecord(diagnostic, tratament, patient, doctor);
        record.setDate(LocalDate.now());
        em.persist(record);
        em.getTransaction().commit();
    }

    public List<MedicalRecord> getRecordsForPatient(Patient patient) {
        return em.createQuery("SELECT m FROM MedicalRecord m WHERE m.patient = :patient", MedicalRecord.class)
                .setParameter("patient", patient)
                .getResultList();
    }
}
