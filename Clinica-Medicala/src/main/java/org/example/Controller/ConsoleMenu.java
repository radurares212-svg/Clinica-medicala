package org.example.Controller;

import org.example.Model.*;
import org.example.Servicii.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AppointmentService appointmentService = new AppointmentService();
    private static final DoctorService doctorService = new DoctorService();
    private static final MedicalRecordService medicalRecordService = new MedicalRecordService();
    private static final PatientService patientService = new PatientService();

    private static final AuthService authService = new AuthService();

    public static void start() {
        while (true) {
            System.out.println("=== Clinica Medicala ===");
            System.out.println("Selecteaza rolul:");
            System.out.println("1. Doctor");
            System.out.println("2. Pacient");
            System.out.println("0. Iesire");
            System.out.print("Optiune: ");

            int rolOptiune = Integer.parseInt(scanner.nextLine());

            if (rolOptiune == 0) {
                System.out.println("La revedere!");
                break;
            }

            UserRole rol;
            if (rolOptiune == 1) {
                rol = UserRole.DOCTOR;
            } else if (rolOptiune == 2) {
                rol = UserRole.PATIENT;
            } else {
                System.out.println("Optiune invalida.");
                continue;
            }

            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.print("Optiune: ");
            int authOptiune = Integer.parseInt(scanner.nextLine());

            if (authOptiune == 1) {
                login(rol);
            } else if (authOptiune == 2) {
                register(rol);
            } else {
                System.out.println("Optiune invalida.");
            }
        }
    }

    private static void login(UserRole rol) {
        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Parola: ");
        String parola = scanner.nextLine();

        User user = authService.login(email, parola);

        if (user == null) {
            System.out.println("Date invalide.");
            return;
        }

        if (user.getRole() != rol) {
            System.out.println("Nu ai acces cu acest rol.");
            return;
        }

        System.out.println("Login reusit! Bine ai venit, " + user.getName());
        if (rol == UserRole.DOCTOR) {
            doctorMenu((Doctor) user);
        } else {
            patientMenu((Patient) user);
        }
    }

    private static void register(UserRole rol) {
        System.out.print("Nume complet: ");
        String nume = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Parola: ");
        String parola = scanner.nextLine();

        try {
            if (rol == UserRole.DOCTOR) {
                Doctor doctor = new Doctor();
                doctor.setName(nume);
                doctor.setEmail(email);
                doctor.setPassword(parola);
                doctor.setRole(UserRole.DOCTOR);
                authService.registerUser(doctor);
            } else {
                Patient pacient = new Patient();
                pacient.setName(nume);
                pacient.setEmail(email);
                pacient.setPassword(parola);
                pacient.setRole(UserRole.PATIENT);
                authService.registerUser(pacient);
            }
            System.out.println("Inregistrare reusita!");
        } catch (IllegalArgumentException ex) {
            System.out.println("Eroare: " + ex.getMessage());
        }
    }

    private static void doctorMenu(Doctor doctor) {
        while (true) {
            System.out.println("\n=== Meniu Doctor ===");
            System.out.println("1. Vizualizeaza toate programarile");
            System.out.println("2. Vizualizeaza programari pentru o zi");
            System.out.println("3. Adauga diagnostic si tratament");
            System.out.println("0. Logout");

            System.out.print("Alege optiunea: ");
            int opt = Integer.parseInt(scanner.nextLine());

            switch (opt) {
                case 1 -> {
                    List<Appointment> all = appointmentService.getAppointmentsByDoctorId(doctor.getId());
                    all.forEach(a -> System.out.println(a.getDate() + " " + a.getTimeSlot() + " - Pacient: " + a.getPatient().getName()));
                }
                case 2 -> {
                    System.out.print("Introdu data (yyyy-MM-dd): ");
                    String dataStr = scanner.nextLine();
                    LocalDate zi = LocalDate.parse(dataStr);
                    List<Appointment> filtrate = doctorService.getAppointmentsByDate(doctor, zi);
                    filtrate.forEach(a -> System.out.println(a.getDate() + " " + a.getTimeSlot() + " - Pacient: " + a.getPatient().getName()));
                }
                case 3 -> {
                    System.out.print("ID-ul pacientului: ");
                    int patientId = Integer.parseInt(scanner.nextLine());

                    Patient pacient = patientService.findById((long) patientId);
                    if (pacient == null) {
                        System.out.println("Pacient inexistent.");
                        break;
                    }

                    System.out.print("Diagnostic: ");
                    String diagnostic = scanner.nextLine();

                    System.out.print("Tratament: ");
                    String tratament = scanner.nextLine();

                    medicalRecordService.addMedicalRecord(pacient, doctor, diagnostic, tratament);
                    System.out.println("✅ Diagnostic si tratament adaugate cu succes!");
                }

                case 0 -> {
                    System.out.println("Logout...");
                    return;
                }
                default -> System.out.println("Optiune invalida.");
            }
        }
    }


    private static void patientMenu(Patient patient) {
        while (true) {
            System.out.println("\n--- Meniu Pacient ---");
            System.out.println("1. Solicita programare");
            System.out.println("2. Vezi istoricul programarilor");
            System.out.println("0. Logout");
            System.out.print("Optiune: ");

            int opt = Integer.parseInt(scanner.nextLine());

            if (opt == 0) {
                System.out.println("Logout...");
                break;
            }

            switch (opt) {
                case 1 -> {
                    // Afișează toți doctorii
                    List<Doctor> doctori = doctorService.getAllDoctors();
                    if (doctori.isEmpty()) {
                        System.out.println("Nu există doctori disponibili.");
                        break;
                    }

                    System.out.println("Doctori disponibili:");
                    for (Doctor d : doctori) {
                        System.out.println(d.getId() + ". " + d.getName());
                    }

                    // Solicită informațiile de la pacient
                    System.out.print("Introdu ID-ul doctorului: ");
                    long doctorId = Long.parseLong(scanner.nextLine());
                    Doctor doctor = doctorService.findById(doctorId);

                    if (doctor == null) {
                        System.out.println("Doctor inexistent.");
                        break;
                    }

                    System.out.print("Introdu data dorită (yyyy-MM-dd): ");
                    LocalDate data = LocalDate.parse(scanner.nextLine());

                    System.out.print("Introdu intervalul orar (ex: 09:00-10:00): ");
                    String timeSlot = scanner.nextLine();

                    // Creează programarea fără verificări suplimentare
                    Appointment programare = new Appointment();
                    programare.setDoctor(doctor);
                    programare.setPatient(patient);
                    programare.setDate(data);
                    programare.setTimeSlot(timeSlot);

                    appointmentService.saveAppointment(programare);
                    System.out.println("✅ Programare creată cu succes!");
                }

                case 2 -> {
                    showMedicalHistory(patient);

                }
                default -> System.out.println("Optiune invalida.");
            }

        }
    }
    private static void showMedicalHistory(Patient patient) {
        List<MedicalRecord> records = medicalRecordService.getRecordsForPatient(patient);

        if (records.isEmpty()) {
            System.out.println("📭 Nu ai inregistrari medicale.");
            return;
        }

        System.out.println("\n=== Istoric Medical ===");
        for (MedicalRecord r : records) {
            System.out.println("📅 Data: " + r.getDate());
            System.out.println("👨‍⚕️ Doctor: " + r.getDoctor().getName());
            System.out.println("🩺 Diagnostic: " + r.getDiagnosis());
            System.out.println("💊 Tratament: " + r.getTreatment());
            System.out.println("---------------------------");
        }
    }

}
