# 🏥 Sistem de Gestiune Clinică Medicală

Acesta este un sistem de management pentru o clinică medicală, dezvoltat în **Java** cu suport pentru persistența datelor folosind **Hibernate (JPA)**. Aplicația permite gestionarea fluxului de lucru dintre medici și pacienți printr-o interfață de tip consolă.

## 📋 Caracteristici principale

* **Sistem de Autentificare**: Acces securizat pe bază de roluri (ADMIN, DOCTOR, PACIENT).
* **Managementul Programărilor**: Crearea, vizualizarea și anularea programărilor medicale.
* **Dosar Medical (Electronic Health Record)**: Stocarea istoricului de consultații, diagnostice și observații pentru fiecare pacient.
* **Sistem de Prescripții**: Generarea de rețete medicale asociate consultațiilor efectuate.
* **Gestiune Utilizatori**: Administrarea bazei de date de pacienți și a personalului medical.

## 🏗️ Tehnologii și Arhitectură

* **Limbaj**: Java 8+
* **ORM**: Hibernate / JPA (pentru maparea obiectelor pe baza de date relațională).
* **Bază de date**: MySQL / PostgreSQL (configurabilă prin `persistence.xml`).
* **Build Tool**: Maven.
* **Arhitectură**: Stratificată (Model, Service, Controller, Database/Repository).

## 📂 Structura Proiectului

* `org.example.Model`: Entitățile JPA (`Patient`, `Doctor`, `Appointment`, `MedicalRecord`, `Prescription`, `User`).
* `org.example.Servicii`: Logica de business (validări, procesarea programărilor, logica de autentificare).
* `org.example.Controller`: Gestionarea meniului interactiv în consolă și a input-ului de la utilizator.
* `org.example.Database`: Configurarea conexiunii prin `HibernateUtil`.

## 🚀 Instalare și Configurare

1.  **Clonează repository-ul**:
    ```bash
    git clone [url-ul-proiectului-tau]
    ```

2.  **Configurarea Bazei de Date**:
    Mergi la `src/main/resources/META-INF/persistence.xml` și actualizează proprietățile de conexiune:
    ```xml
    <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/nume_baza_date"/>
    <property name="javax.persistence.jdbc.user" value="username"/>
    <property name="javax.persistence.jdbc.password" value="parola"/>
    ```

3.  **Compilare**:
    ```bash
    mvn clean install
    ```

4.  **Rulare**:
    Lansează aplicația din clasa `Main.java` situată în pachetul `org.example`.

## 💻 Mod de utilizare

La pornire, poți alege să te loghezi:
- **Pacienții**: Pot vedea disponibilitatea medicilor, fac programări și își consultă propriul dosar medical.
- **Medicii**: Pot vedea agenda zilnică, pot finaliza consultații și pot scrie rețete.
- **Administratorii**: Gestionează listele de utilizatori și personalul clinicii.

---
Proiect realizat de: **Radu Rareș**
